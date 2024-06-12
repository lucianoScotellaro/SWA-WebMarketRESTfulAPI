package it.univaq.swa.webmarket.rest.jackson.proposal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import it.univaq.swa.webmarket.rest.models.Proposal;

import java.io.IOException;

public class ProposalSerializer extends JsonSerializer<Proposal> {

    @Override
    public void serialize(Proposal proposal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException
    {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("product", proposal.getProduct());
        jsonGenerator.writeStringField("notes", proposal.getNotes());
        jsonGenerator.writeObjectField("answer", proposal.getAnswer());
        jsonGenerator.writeEndObject();
    }
}
