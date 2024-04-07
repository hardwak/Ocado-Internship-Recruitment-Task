package com.ocado.basket;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {

    @Test
    void getItemsFromBasket_example() {
        List<String> expectedItems = List.of(
                "Steak (300g)",
                "Carrots (1kg)",
                "Soda (24x330ml)",
                "AA Battery (4 Pcs.)",
                "Espresso Machine",
                "Garden Chair"
        );

        List<String> actualItems = JsonParser.getItemsFromBasket("src/main/resources/basket-example.json");

        assertEquals(expectedItems, actualItems, "The list from the JSON file do not match the expected list of items.");
    }

    @Test
    void getItemsFromBasket_0() {
        List<String> expectedItems = List.of();

        List<String> actualItems = JsonParser.getItemsFromBasket("src/main/resources/basket-0.json");

        assertEquals(expectedItems, actualItems, "The list from the JSON file do not match the expected list of items.");
    }

    @Test
    void getItemsFromBasket_1() {
        List<String> expectedItems = List.of(
                "Cocoa Butter",
                "Tart - Raisin And Pecan",
                "Table Cloth 54x72 White",
                "Flower - Daisies",
                "Fond - Chocolate",
                "Cookies - Englishbay Wht"
        );

        List<String> actualItems = JsonParser.getItemsFromBasket("src/main/resources/basket-1.json");

        assertEquals(expectedItems, actualItems, "The list from the JSON file do not match the expected list of items.");
    }

    @Test
    void getItemsFromBasket_2() {
        List<String> expectedItems = List.of(
                "Fond - Chocolate",
                "Chocolate - Unsweetened",
                "Nut - Almond, Blanched, Whole",
                "Haggis",
                "Mushroom - Porcini Frozen",
                "Cake - Miini Cheesecake Cherry",
                "Sauce - Mint",
                "Longan",
                "Bag Clear 10 Lb",
                "Nantucket - Pomegranate Pear",
                "Puree - Strawberry",
                "Numi - Assorted Teas",
                "Apples - Spartan",
                "Garlic - Peeled",
                "Cabbage - Nappa",
                "Bagel - Whole White Sesame",
                "Tea - Apple Green Tea"
        );

        List<String> actualItems = JsonParser.getItemsFromBasket("src/main/resources/basket-2.json");

        assertEquals(expectedItems, actualItems, "The list from the JSON file do not match the expected list of items.");
    }

    @Test
    void getItemsFromBasket_3() {
        List<String> expectedItems = List.of(
                "Emulsifier",
                "Corn Syrup",
                "Oil - Olive, Extra Virgin",
                "Ocean Spray - Ruby Red",
                "Puree - Guava",
                "Fond - Chocolate",
                "Chocolate - Unsweetened",
                "Nut - Almond, Blanched, Whole",
                "Haggis",
                "Pepper - Red, Finger Hot",
                "Mushroom - Porcini Frozen",
                "Garlic - Peeled",
                "Cabbage - Nappa",
                "Bagel - Whole White Sesame",
                "Tea - Apple Green Tea"
        );

        List<String> actualItems = JsonParser.getItemsFromBasket("src/main/resources/basket-3.json");

        assertEquals(expectedItems, actualItems, "The list from the JSON file do not match the expected list of items.");
    }
}