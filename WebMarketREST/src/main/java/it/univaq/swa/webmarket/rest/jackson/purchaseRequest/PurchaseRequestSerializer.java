package it.univaq.swa.webmarket.rest.jackson.purchaseRequest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import it.univaq.swa.webmarket.rest.models.PurchaseRequest;
import it.univaq.swa.webmarket.rest.models.User;

import java.io.IOException;

public class PurchaseRequestSerializer extends JsonSerializer<PurchaseRequest> {
    @Override
    public void serialize(PurchaseRequest purchaseRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException
    {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", purchaseRequest.getID());
        jsonGenerator.writeObjectField("orderer", purchaseRequest.getOrderer());
        jsonGenerator.writeObjectField("technician", purchaseRequest.getTechnician());
        jsonGenerator.writeStringField("category", purchaseRequest.getCategory());
        jsonGenerator.writeObjectField("characteristics", purchaseRequest.getCharacteristics());
        jsonGenerator.writeStringField("notes", purchaseRequest.getNotes());
        jsonGenerator.writeObjectField("proposal", purchaseRequest.getProposal());
        jsonGenerator.writeBooleanField("ongoing", purchaseRequest.isOngoing());
        jsonGenerator.writeStringField("outcome", purchaseRequest.getOutcome());
        jsonGenerator.writeEndObject();
    }
}
