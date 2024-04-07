package com.ocado.basket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonParser {
    public static Map<String, List<String>> loadDeliveryOptions(String path) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(new File(path), new TypeReference<Map<String, List<String>>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getItemsFromBasket(String path) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(new File(path), new TypeReference<List<String>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
