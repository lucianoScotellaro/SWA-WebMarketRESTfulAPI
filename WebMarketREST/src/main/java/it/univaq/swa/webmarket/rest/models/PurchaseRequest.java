package it.univaq.swa.webmarket.rest.models;

import java.util.HashMap;

public class PurchaseRequest {

    private int id;
    private User orderer;
    private User technician;
    private String category;
    private HashMap<String, String> characteristics;
    private String notes;
    private Proposal proposal;
    private boolean ongoing;
    private String outcome;

    public int getID() {
        return this.id;
    }

    public void setID(int id){
        this.id = id;
    }

    public User getOrderer() {
        return orderer;
    }

    public User getTechnician() {
        return technician;
    }

    public String getCategory() {
        return category;
    }

    public HashMap<String, String> getCharacteristics() {
        return characteristics;
    }

    public String getNotes() {
        return notes;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public boolean isOngoing() {
        return ongoing;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOrderer(User orderer) {
        this.orderer = orderer;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTechnician(User technician) {
        this.technician = technician;
    }

    public void setCharacteristics(HashMap<String, String> characteristics) {
        this.characteristics = characteristics;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public void setOngoing(boolean ongoing) {
        this.ongoing = ongoing;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }
}
