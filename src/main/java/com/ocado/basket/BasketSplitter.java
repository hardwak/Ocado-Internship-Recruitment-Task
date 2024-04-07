package com.ocado.basket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketSplitter {

    private final Map<String, List<String>> products;

    public BasketSplitter(String absolutePathToConfigFile) {
        products = JsonParser.loadDeliveryOptions(absolutePathToConfigFile);
    }

    public Map<String, List<String>> split(List<String> items) {
        /*
         * 1 step - map preparation
         * 2 step - find the biggest group
         * 3 step - erase products from other delivery methods
         * 4 step - delete empty delivery groups
         * 5 step - repeat steps 2-4 until 0 groups left
         * */
        //Creates map as (key, value) - (Delivery Method, List of Products)
        Map<String, List<String>> deliveries = getDeliveriesMap(items);

        return splitterAlgorithm(deliveries);
    }

    private Map<String, List<String>> splitterAlgorithm(Map<String, List<String>> deliveries) {

        Map<String, List<String>> deliveryGroups = new HashMap<>();

        while (!deliveries.isEmpty()) {
            //Set first group as the biggest one
            String biggestGroup = deliveries.keySet().iterator().next();

            //find the biggest group
            for (String deliveryMethod : deliveries.keySet())
                if (deliveries.get(deliveryMethod).size() > deliveries.get(biggestGroup).size())
                    biggestGroup = deliveryMethod;

            //Add the biggest group to final result
            deliveryGroups.put(biggestGroup, new ArrayList<>(deliveries.get(biggestGroup)));

            //remove the biggest group from map
            deliveries.remove(biggestGroup);

            //remove products of the biggest group from other groups
            String finalBiggestGroup = biggestGroup; //Variable in lambda expression should be final
            deliveries.forEach((deliveryMethod, products) -> products.removeAll(deliveryGroups.get(finalBiggestGroup)));

            //removes empty delivery groups
            deliveries.keySet().removeIf(deliveryMethod -> deliveries.get(deliveryMethod).isEmpty());

        }

        return deliveryGroups;
    }

    private Map<String, List<String>> getDeliveriesMap(List<String> items) {

        if (items.isEmpty()) {
            System.out.println("Provided basket is empty");
            return new HashMap<>();
        }

        Map<String, List<String>> deliveries = new HashMap<>();

        for (String item : items) { //iterating over items in basket
            if (products.get(item) == null) {
                System.out.println("Provided basket file has products that are not absent in config file");
                return new HashMap<>();
            }

            for (String deliveryMethod : products.get(item)) {  //iterating over delivery method of item
                if (deliveries.containsKey(deliveryMethod)) {   //if delivery method exists - add new item to it
                    deliveries.get(deliveryMethod).add(item);
                } else {                                        //if not, add delivery method and item to it
                    List<String> products = new ArrayList<>(List.of(item));
                    deliveries.put(deliveryMethod, products);
                }
            }

        }
        return deliveries;
    }
}
