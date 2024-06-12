package it.univaq.swa.webmarket.rest.jackson.answer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import it.univaq.swa.webmarket.rest.models.Answer;

import java.io.IOException;

public class AnswerDeserializer extends JsonDeserializer<Answer> {

    @Override
    public Answer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException
    {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Answer answer = new Answer();

        if(node.has("accepted"))
        {
            answer.setAccepted(node.get("accepted").asBoolean());
        }

        if(node.has("reason"))
        {
            answer.setReason(node.get("reason").asText());
        }

        return answer;
    }
}
