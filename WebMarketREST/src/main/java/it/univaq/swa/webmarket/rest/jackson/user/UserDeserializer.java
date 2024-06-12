package it.univaq.swa.webmarket.rest.jackson.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import it.univaq.swa.webmarket.rest.models.User;

import java.io.IOException;

public class UserDeserializer extends JsonDeserializer<User> {


    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        User user = new User();

        if(node.has("id")){
            user.setId(node.get("id").asInt());
        }

        if(node.has("email")){
            user.setEmail(node.get("email").asText());
        }
        if(node.has("username")){
            user.setUsername(node.get("username").asText());
        }

        return user;
    }
}
