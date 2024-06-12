package it.univaq.swa.webmarket.rest.jackson.purchaseRequest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import it.univaq.swa.webmarket.rest.models.Proposal;
import it.univaq.swa.webmarket.rest.models.PurchaseRequest;
import it.univaq.swa.webmarket.rest.models.User;

import java.io.IOException;
import java.util.HashMap;

public class PurchaseRequestDeserializer extends JsonDeserializer<PurchaseRequest> {

    @Override
    public PurchaseRequest deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException
    {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        PurchaseRequest request = new PurchaseRequest();

        if(node.has("orderer"))
        {
            request.setOrderer(jsonParser.getCodec().treeToValue(node.get("orderer"), User.class));
        }

        if(node.has("technician"))
        {
            request.setTechnician(jsonParser.getCodec().treeToValue(node.get("technician"), User.class));
        }

        if(node.has("category"))
        {
            request.setCategory(node.get("category").asText());
        }

        if(node.has("characteristics"))
        {
            request.setCharacteristics(jsonParser.getCodec().treeToValue(node.get("characteristics"), HashMap.class));
        }

        if(node.has("notes"))
        {
            request.setNotes(node.get("notes").asText());
        }

        if(node.has("proposal"))
        {
            request.setProposal(jsonParser.getCodec().treeToValue(node.get("proposal"), Proposal.class));
        }

        if(node.has("ongoing"))
        {
            request.setOngoing(node.get("ongoing").asBoolean());
        }

        if(node.has("outcome"))
        {
            request.setOutcome(node.get("outcome").asText());
        }

        return request;
    }
}
