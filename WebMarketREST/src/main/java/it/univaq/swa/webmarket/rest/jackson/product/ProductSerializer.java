package it.univaq.swa.webmarket.rest.jackson.product;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import it.univaq.swa.webmarket.rest.models.Product;

import java.io.IOException;

public class ProductSerializer extends JsonSerializer<Product> {

    @Override
    public void serialize(Product product, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", product.getName());
        jsonGenerator.writeStringField("producer", product.getProducer());
        jsonGenerator.writeNumberField("id", product.getId());
        jsonGenerator.writeNumberField("price", product.getPrice());
        jsonGenerator.writeStringField("URL", product.getURL());
        jsonGenerator.writeEndObject();
    }
}
