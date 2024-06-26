package it.univaq.swa.webmarket.rest.resources;

import it.univaq.swa.webmarket.rest.business.PurchaseRequestsService;
import it.univaq.swa.webmarket.rest.business.PurchaseRequestsServiceFactory;
import it.univaq.swa.webmarket.rest.models.Answer;
import it.univaq.swa.webmarket.rest.models.Proposal;
import it.univaq.swa.webmarket.rest.models.PurchaseRequest;
import it.univaq.swa.webmarket.rest.models.User;
import it.univaq.swa.webmarket.rest.security.Logged;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;

public class PurchaseRequestResource {

    private final PurchaseRequestsService business;
    private final PurchaseRequest request;

    public PurchaseRequestResource(PurchaseRequest request){
        this.business = PurchaseRequestsServiceFactory.getPurchaseRequestsService();
        this.request = request;
    }


    @GET
    @Logged
    @Produces({"application/json"})
    public Response getRequest(@Context ContainerRequestContext requestContext)
    {
        if(requestContext.getProperty("username").equals(request.getOrderer().getUsername())){
            return Response.ok().entity(request).build();
        }

        return Response.status(UNAUTHORIZED).build();
    }


    @DELETE
    @Logged
    public Response deleteRequest(){
        business.deleteRequest(request.getID());
        return Response.noContent().build();
    }

    @PUT
    @Logged
    @Path("/technician")
    @Consumes({"application/json"})
    public Response setTechnician(User technician)
    {
        business.setTechnician(request.getID(), technician);
        return Response.noContent().build();
    }

    @GET
    @Logged
    @Path("/proposal")
    @Produces({"application/json"})
    public Response getProposal()
    {
        return Response.ok(request.getProposal()).build();
    }

    @PUT
    @Logged
    @Path("/proposal")
    @Consumes({"application/json"})
    public Response setProposal(Proposal proposal, @Context ContainerRequestContext requestContext)
    {
        if(requestContext.getProperty("username").equals(request.getTechnician().getUsername()))
        {
            business.setProposal(request.getID(), proposal);
            return Response.noContent().build();
        }

        return Response.status(UNAUTHORIZED).build();
    }

    @PUT
    @Logged
    @Path("/proposal/answer")
    @Consumes({"application/json"})
    public Response setAnswer(Answer answer, @Context ContainerRequestContext requestContext)
    {
        if(requestContext.getProperty("username").equals(request.getOrderer().getUsername()))
        {
            business.setAnswer(request.getID(), answer);
            return Response.noContent().build();
        }

        return Response.status(UNAUTHORIZED).build();
    }
}
