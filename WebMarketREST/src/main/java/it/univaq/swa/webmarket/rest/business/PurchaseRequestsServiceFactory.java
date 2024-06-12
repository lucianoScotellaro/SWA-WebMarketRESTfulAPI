package it.univaq.swa.webmarket.rest.business;

public class PurchaseRequestsServiceFactory {

    private final static PurchaseRequestsServiceImpl service = new PurchaseRequestsServiceImpl();

    public static PurchaseRequestsServiceImpl getPurchaseRequestsService()
    {
        return service;
    }
}
