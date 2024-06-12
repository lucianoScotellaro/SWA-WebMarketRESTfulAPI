package it.univaq.swa.webmarket.rest.jackson.answer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import it.univaq.swa.webmarket.rest.models.Answer;

import java.io.IOException;

public class AnswerSerializer extends JsonSerializer<Answer> {

    @Override
    public void serialize(Answer answer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeBooleanField("accepted", answer.isAccepted());
        jsonGenerator.writeStringField("reason", answer.getReason());
        jsonGenerator.writeEndObject();
    }
}
