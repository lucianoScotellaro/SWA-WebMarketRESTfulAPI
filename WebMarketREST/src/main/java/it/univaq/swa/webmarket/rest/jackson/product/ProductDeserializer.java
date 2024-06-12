package it.univaq.swa.webmarket.rest.jackson.product;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import it.univaq.swa.webmarket.rest.models.Product;

import java.io.IOException;

public class ProductDeserializer extends JsonDeserializer<Product> {

    @Override
    public Product deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException
    {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Product product = new Product();

        if(node.has("name"))
        {
            product.setName(node.get("name").asText());
        }

        if(node.has("producer"))
        {
            product.setProducer(node.get("producer").asText());
        }

        if(node.has("id"))
        {
            product.setId(node.get("id").asInt());
        }

        if(node.has("price"))
        {
            product.setPrice(node.get("price").asDouble());
        }

        if(node.has("url"))
        {
            product.setURL(node.get("url").asText());
        }

        return product;
    }
}
