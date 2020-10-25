package com.fashiona.web.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomTimestampDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy'-'MM'-'dd'T'HH':'mm':'ss.SSSSSSSZZZZZ");
        String date = jsonParser.getText();

        return LocalDateTime.parse(date, format);
    }
}
