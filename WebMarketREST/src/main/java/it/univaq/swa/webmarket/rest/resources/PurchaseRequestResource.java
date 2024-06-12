package it.univaq.swa.webmarket.rest.resources;

import it.univaq.swa.webmarket.rest.business.PurchaseRequestsService;
import it.univaq.swa.webmarket.rest.business.PurchaseRequestsServiceFactory;
import it.univaq.swa.webmarket.rest.models.Answer;
import it.univaq.swa.webmarket.rest.models.Proposal;
import it.univaq.swa.webmarket.rest.models.PurchaseRequest;
import it.univaq.swa.webmarket.rest.models.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

public class PurchaseRequestResource {

    private final PurchaseRequestsService business;
    private final PurchaseRequest request;

    public PurchaseRequestResource(PurchaseRequest request){
        this.business = PurchaseRequestsServiceFactory.getPurchaseRequestsService();
        this.request = request;
    }

    @GET
    @Produces({"application/json"})
    public Response getRequest()
    {
        return Response.ok().entity(request).build();
    }

    @DELETE
    public Response deleteRequest(){
        business.deleteRequest(request.getID());
        return Response.noContent().build();
    }

    @PUT
    @Path("/technician")
    @Consumes({"application/json"})
    public Response setTechnician(User technician)
    {
        business.setTechnician(request.getID(), technician);
        return Response.noContent().build();
    }

    @GET
    @Path("/proposal")
    @Produces({"application/json"})
    public Response getProposal(){
        return Response.ok(request.getProposal()).build();
    }

    @PUT
    @Path("/proposal")
    @Consumes({"application/json"})
    public Response setProposal(Proposal proposal){
        business.setProposal(request.getID(), proposal);
        return Response.noContent().build();
    }

    @PUT
    @Path("/proposal/answer")
    @Consumes({"application/json"})
    public Response setAnswer(Answer answer){
        business.setAnswer(request.getID(), answer);
        return Response.noContent().build();
    }


}