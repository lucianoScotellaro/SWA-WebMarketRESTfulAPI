package it.univaq.swa.webmarket.rest.utils;

import com.github.javafaker.Faker;
import it.univaq.swa.webmarket.rest.models.*;

import java.util.HashMap;

public class DummyGenerator {

    private final static Faker faker = new Faker();

    public static PurchaseRequest createDummyRequest(){
        PurchaseRequest request = new PurchaseRequest();
        request.setID(1);
        request.setOrderer(createDummyUser());
        request.setTechnician(createDummyUser());
        request.setCategory("Informatics");
        request.setCharacteristics(new HashMap<>());
        request.setNotes("Un bel computer");
        request.setProposal(createDummyProposal());
        request.setOngoing(true);
        request.setOutcome("");

        return request;
    }

    public static User createDummyUser(){
        User user = new User();
        user.setId(faker.random().nextInt(1,100));
        user.setUsername("pippo");
        user.setEmail(faker.internet().emailAddress());

        return user;
    }

    public static Proposal createDummyProposal(){
        Proposal proposal =  new Proposal();
        proposal.setProduct(createDummyProduct());
        proposal.setNotes(faker.lorem().sentence());
        proposal.setAnswer(createDummyAnswer());

        return proposal;
    }

    public static Product createDummyProduct(){
        Product product = new Product();
        product.setId(faker.random().nextInt(1, 100));
        product.setName(faker.book().title());
        product.setProducer(faker.company().name());
        product.setPrice(faker.random().nextDouble());
        product.setURL(faker.internet().url());

        return product;
    }

    public static Answer createDummyAnswer(){
        Answer answer = new Answer();
        answer.setAccepted(faker.random().nextBoolean());
        answer.setReason(faker.lorem().sentence());

        return answer;
    }
}
