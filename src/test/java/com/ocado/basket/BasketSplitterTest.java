package com.ocado.basket;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BasketSplitterTest {

    @Test
    void split_missingConfigProducts()  {

        BasketSplitter splitter = new BasketSplitter("src/main/resources/config.json");

        String basketFilePath = "src/main/resources/basket-example.json";
        List<String> products = JsonParser.getItemsFromBasket(basketFilePath);

        Map<String, List<String>> expectedGroups = Map.of();

        Map<String, List<String>> actualGroups = splitter.split(products);

        assertEquals(expectedGroups , actualGroups, "All products from basket file are absent in config.json");

//        assertThrows(IllegalArgumentException.class, () -> splitter.split(products), "All products from basket file are absent in config.json");
    }

    @Test
    void split_emptyBasket()  {

        BasketSplitter splitter = new BasketSplitter("src/main/resources/config.json");

        String basketFilePath = "src/main/resources/basket-0.json";
        List<String> products = JsonParser.getItemsFromBasket(basketFilePath);

        Map<String, List<String>> expectedGroups = Map.of();

        Map<String, List<String>> actualGroups = splitter.split(products);

        assertEquals(expectedGroups , actualGroups, "Actual map are not empty");

//        assertThrows(IllegalArgumentException.class, () -> splitter.split(products), "Basket is not empty");
    }

    @Test
    void split_basket_1() {

        BasketSplitter splitter = new BasketSplitter("src/main/resources/config.json");
        String basketFilePath = "src/main/resources/basket-1.json";

        List<String> products = JsonParser.getItemsFromBasket(basketFilePath);

        Map<String, List<String>> expectedGroups = Map.of(
                "Pick-up point", List.of("Fond - Chocolate"),
                "Courier", List.of("Cocoa Butter",
                        "Tart - Raisin And Pecan",
                        "Table Cloth 54x72 White",
                        "Flower - Daisies",
                        "Cookies - Englishbay Wht")
        );

        Map<String, List<String>> actualGroups = splitter.split(products);

        assertEquals(expectedGroups , actualGroups, "Maps are not equal");
    }

    @Test
    void split_basket_2() {

        BasketSplitter splitter = new BasketSplitter("src/main/resources/config.json");
        String basketFilePath = "src/main/resources/basket-2.json";

        List<String> products = JsonParser.getItemsFromBasket(basketFilePath);

        Map<String, List<String>> expectedGroups = Map.of(
                "Same day delivery", List.of("Sauce - Mint",
                                                    "Numi - Assorted Teas",
                                                    "Garlic - Peeled"),

                "Courier", List.of("Cake - Miini Cheesecake Cherry"),

                "Express Collection", List.of("Fond - Chocolate",
                                                    "Chocolate - Unsweetened",
                                                    "Nut - Almond, Blanched, Whole",
                                                    "Haggis",
                                                    "Mushroom - Porcini Frozen",
                                                    "Longan",
                                                    "Bag Clear 10 Lb",
                                                    "Nantucket - Pomegranate Pear",
                                                    "Puree - Strawberry",
                                                    "Apples - Spartan",
                                                    "Cabbage - Nappa",
                                                    "Bagel - Whole White Sesame",
                                                    "Tea - Apple Green Tea")
        );

        Map<String, List<String>> actualGroups = splitter.split(products);

        assertEquals(expectedGroups , actualGroups, "Maps are not equal");
    }

    @Test
    void split_basket_3() {

        BasketSplitter splitter = new BasketSplitter("src/main/resources/config.json");
        String basketFilePath = "src/main/resources/basket-3.json";

        List<String> products = JsonParser.getItemsFromBasket(basketFilePath);

        Map<String, List<String>> expectedGroups = Map.of(
                "Same day delivery", List.of("Pepper - Red, Finger Hot", "Garlic - Peeled"),
                "Express Collection", List.of("Emulsifier", "Corn Syrup", "Oil - Olive, Extra Virgin", "Ocean Spray - Ruby Red", "Puree - Guava", "Fond - Chocolate", "Chocolate - Unsweetened", "Nut - Almond, Blanched, Whole", "Haggis", "Mushroom - Porcini Frozen", "Cabbage - Nappa", "Bagel - Whole White Sesame", "Tea - Apple Green Tea")
        );

        Map<String, List<String>> actualGroups = splitter.split(products);

        assertEquals(expectedGroups , actualGroups, "Maps are not equal");
    }

}