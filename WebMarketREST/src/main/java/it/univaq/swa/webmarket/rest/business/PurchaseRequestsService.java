package it.univaq.swa.webmarket.rest.business;

import it.univaq.swa.webmarket.rest.models.Answer;
import it.univaq.swa.webmarket.rest.models.Proposal;
import it.univaq.swa.webmarket.rest.models.PurchaseRequest;
import it.univaq.swa.webmarket.rest.models.User;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

public  interface PurchaseRequestsService
{
    PurchaseRequest getRequest(int purchaseRequestId) throws NotFoundException;
    int addRequest(PurchaseRequest request);
    void deleteRequest(int purchaseRequestId);
    Proposal getProposal(int purchaseRequestId);
    int setProposal(int purchaseRequestId, Proposal proposal);
    void setAnswer(int purchaseRequestId, Answer answer);
    void setTechnician(int purchaseRequestId, User technician);
    List<PurchaseRequest> getUnassignedRequests();
    List<PurchaseRequest> getTechnicianRequests(int technicianId) throws NotFoundException;
    List<PurchaseRequest> getOrdererOngoingRequests(int ordererId) throws NotFoundException;
}
