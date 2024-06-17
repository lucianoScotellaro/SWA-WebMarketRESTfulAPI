package it.univaq.swa.webmarket.rest.business;

import it.univaq.swa.webmarket.rest.models.Answer;
import it.univaq.swa.webmarket.rest.models.Proposal;
import it.univaq.swa.webmarket.rest.models.PurchaseRequest;
import it.univaq.swa.webmarket.rest.models.User;
import it.univaq.swa.webmarket.rest.utils.DummyGenerator;

import java.util.ArrayList;
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
    public List<PurchaseRequest> getOrdererOngoingRequests(int ordererId)
    {
        ArrayList<PurchaseRequest> requests =  new ArrayList<>();
        requests.add(DummyGenerator.createDummyRequest());
        requests.add(DummyGenerator.createDummyRequest());
        return requests;
    }

    @Override
    public List<PurchaseRequest> getUnassignedRequests()
    {
        ArrayList<PurchaseRequest> requests =  new ArrayList<>();
        requests.add(DummyGenerator.createDummyRequest());
        requests.add(DummyGenerator.createDummyRequest());
        return requests;
    }

    @Override
    public Proposal getProposal(int purchaseRequestId) {
        return DummyGenerator.createDummyRequest().getProposal();
    }

    @Override
    public List<PurchaseRequest> getTechnicianRequests(int technicianId)
    {
        ArrayList<PurchaseRequest> requests =  new ArrayList<>();
        requests.add(DummyGenerator.createDummyRequest());
        requests.add(DummyGenerator.createDummyRequest());
        return requests;
    }
}
