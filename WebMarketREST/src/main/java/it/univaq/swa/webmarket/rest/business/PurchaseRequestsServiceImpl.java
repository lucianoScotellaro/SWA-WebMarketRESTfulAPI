package it.univaq.swa.webmarket.rest.business;

import it.univaq.swa.webmarket.rest.models.Answer;
import it.univaq.swa.webmarket.rest.models.Proposal;
import it.univaq.swa.webmarket.rest.models.PurchaseRequest;
import it.univaq.swa.webmarket.rest.models.User;
import it.univaq.swa.webmarket.rest.utils.DummyGenerator;

import java.util.List;

public class PurchaseRequestsServiceImpl implements PurchaseRequestsService{

    @Override
    public PurchaseRequest getRequest(int purchaseRequestId) {
        return DummyGenerator.createDummyRequest();
    }

    @Override
    public int addRequest(PurchaseRequest request) {
        return 0;
    }

    @Override
    public void setTechnician(int purchaseRequestId, User technician) {

    }

    @Override
    public int setProposal(int purchaseRequestId, Proposal proposal) {
        return 0;
    }

    @Override
    public void setAnswer(int purchaseRequestId, Answer answer) {

    }

    @Override
    public void deleteRequest(int purchaseRequestId) {

    }

    @Override
    public List<PurchaseRequest> getOrdererOngoingRequests(int ordererId) {
        return null;
    }

    @Override
    public List<PurchaseRequest> getUnassignedRequests() {
        return null;
    }

    @Override
    public Proposal getProposal(int purchaseRequestId) {
        return DummyGenerator.createDummyRequest().getProposal();
    }

    @Override
    public List<PurchaseRequest> getTechnicianRequests(int technicianId) {
        return null;
    }

}
