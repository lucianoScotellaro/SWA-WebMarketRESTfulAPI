package it.univaq.swa.webmarket.rest.jackson.proposal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import it.univaq.swa.webmarket.rest.models.Answer;
import it.univaq.swa.webmarket.rest.models.Product;
import it.univaq.swa.webmarket.rest.models.Proposal;

import java.io.IOException;

public class ProposalDeserializer extends JsonDeserializer<Proposal> {

    @Override
    public Proposal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException
    {
        JsonNode  node = jsonParser.getCodec().readTree(jsonParser);
        Proposal proposal = new Proposal();

        if(node.has("product"))
        {
            proposal.setProduct(jsonParser.getCodec().treeToValue(node.get("product"), Product.class));
        }

        if(node.has("notes"))
        {
            proposal.setNotes(node.get("notes").asText());
        }

        if(node.has("answer"))
        {
            proposal.setAnswer(jsonParser.getCodec().treeToValue(node.get("answer"), Answer.class));
        }

        return proposal;
    }
}
