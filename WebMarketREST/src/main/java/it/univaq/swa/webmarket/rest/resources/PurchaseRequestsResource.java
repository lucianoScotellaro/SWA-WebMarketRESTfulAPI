package it.univaq.swa.webmarket.rest.resources;

import it.univaq.swa.webmarket.rest.business.PurchaseRequestsService;
import it.univaq.swa.webmarket.rest.business.PurchaseRequestsServiceFactory;
import it.univaq.swa.webmarket.rest.models.PurchaseRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Path("/requests")
public class PurchaseRequestsResource {

    private final PurchaseRequestsService business;

    public PurchaseRequestsResource(){
        this.business = PurchaseRequestsServiceFactory.getPurchaseRequestsService();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response addRequest(PurchaseRequest request, @Context UriInfo uriInfo)
    {
        String requestId = String.valueOf(business.addRequest(request));

        URI uri = uriInfo.getBaseUriBuilder()
                .path(getClass())
                .path(getClass(), "getRequest")
                .build(requestId);
        return Response.created(uri).entity(uri.toString()).build();
    }

    @GET
    @Path("/unassigned")
    @Produces({"application/json"})
    public Response getUnassignedRequests(@Context UriInfo uriInfo){
        return Response.ok(mapRequests(business.getUnassignedRequests(), "unassigned",uriInfo)).build();
    }

    @GET
    @Path("/ongoing")
    @Produces({"application/json"})
    public Response getOrdererOngoingRequests(@QueryParam("ordererid") int ordererId, @Context UriInfo uriInfo){
        return Response.ok(mapRequests(business.getOrdererOngoingRequests(ordererId), "orderer",uriInfo)).build();
    }

    @GET
    @Produces({"application/json"})
    public Response getTechnicianRequests(@QueryParam("techid") int techId, @Context UriInfo uriInfo){
        return Response.ok(mapRequests(business.getTechnicianRequests(techId), "technician",uriInfo)).build();
    }

    @Path("/{id: [0-9]+}")

    public PurchaseRequestResource getRequest(@PathParam("id") int id)
    {
        return new PurchaseRequestResource(business.getRequest(id));
    }

    private List<Map<String, Object>> mapRequests(List<PurchaseRequest> requests, String type, UriInfo uriInfo)
    {
        return switch (type) {
            case "orderer" -> requests.stream()
                    .map(request -> {
                        URI uri = uriInfo.getBaseUriBuilder()
                                .path(getClass())
                                .path(getClass(), "getRequest")
                                .build(request.getID());
                        return Map.of("id", request.getID(), "technician", request.getTechnician(), "url", uri);
                    })
                    .toList();
            case "technician" -> requests.stream()
                    .map(request -> {
                        URI uri = uriInfo.getBaseUriBuilder()
                                .path(getClass())
                                .path(getClass(), "getRequest")
                                .build(request.getID());
                        return Map.of("id", request.getID(), "orderer", request.getOrderer(), "ongoing", request.isOngoing(), "url", uri);
                    })
                    .toList();
            case "unassigned" -> requests.stream()
                    .map(request -> {
                        URI uri = uriInfo.getBaseUriBuilder()
                                .path(getClass())
                                .path(getClass(), "getRequest")
                                .build(request.getID());
                        return Map.of("id", request.getID(), "orderer", request.getTechnician(), "url", uri);
                    })
                    .toList();
            default -> null;
        };
    }
}
