package it.univaq.swa.webmarket.rest.models;

public class Proposal {

    private Product product;
    private String notes;
    private Answer answer;

    public Object getProduct()
    {
        return this.product;
    }

    public String getNotes()
    {
        return notes;
    }

    public Answer getAnswer()
    {
        return answer;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public void setAnswer(Answer answer)
    {
        this.answer = answer;
    }
}
