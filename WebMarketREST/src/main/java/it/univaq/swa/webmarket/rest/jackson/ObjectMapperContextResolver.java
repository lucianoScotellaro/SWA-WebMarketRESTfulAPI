package it.univaq.swa.webmarket.rest.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import it.univaq.swa.webmarket.rest.jackson.answer.AnswerDeserializer;
import it.univaq.swa.webmarket.rest.jackson.answer.AnswerSerializer;
import it.univaq.swa.webmarket.rest.jackson.product.ProductDeserializer;
import it.univaq.swa.webmarket.rest.jackson.product.ProductSerializer;
import it.univaq.swa.webmarket.rest.jackson.proposal.ProposalDeserializer;
import it.univaq.swa.webmarket.rest.jackson.proposal.ProposalSerializer;
import it.univaq.swa.webmarket.rest.jackson.purchaseRequest.PurchaseRequestDeserializer;
import it.univaq.swa.webmarket.rest.jackson.purchaseRequest.PurchaseRequestSerializer;
import it.univaq.swa.webmarket.rest.jackson.user.UserDeserializer;
import it.univaq.swa.webmarket.rest.jackson.user.UserSerializer;
import it.univaq.swa.webmarket.rest.models.*;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public ObjectMapperContextResolver()
    {
        this.mapper = createObjectMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> type)
    {
        return mapper;
    }

    private ObjectMapper createObjectMapper()
    {
        ObjectMapper mapper = new ObjectMapper();

        //Enable indentation to best display JSON
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        //Module to register (de)serializers
        SimpleModule customSerializers = new SimpleModule("CustomSerializersModule");

        //User
        customSerializers.addSerializer(User.class, new UserSerializer());
        customSerializers.addDeserializer(User.class, new UserDeserializer());

        //PurchaseRequest
        customSerializers.addSerializer(PurchaseRequest.class, new PurchaseRequestSerializer());
        customSerializers.addDeserializer(PurchaseRequest.class, new PurchaseRequestDeserializer());

        //Proposal
        customSerializers.addSerializer(Proposal.class, new ProposalSerializer());
        customSerializers.addDeserializer(Proposal.class, new ProposalDeserializer());

        //Product
        customSerializers.addSerializer(Product.class, new ProductSerializer());
        customSerializers.addDeserializer(Product.class, new ProductDeserializer());

        //Answer
        customSerializers.addSerializer(Answer.class, new AnswerSerializer());
        customSerializers.addDeserializer(Answer.class, new AnswerDeserializer());

        mapper.registerModule(customSerializers);
        return mapper;
    }
}
