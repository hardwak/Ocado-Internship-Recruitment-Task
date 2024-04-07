package com.ocado.basket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 1 step - map preparation
 * 2 step - find the biggest group
 * 3 step - erase products from other delivery methods
 * 4 step - delete empty delivery groups
 * 5 step - repeat steps 2-4 until 0 groups left
 * */

public class SplitterAlgorithm {
    public static Map<String, List<String>> splitterAlgorithm(Map<String, List<String>> deliveries) {

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

            //remove products of the biggest group from other groups and
            //collects empty groups to delete later
            List<String> emptyGoups = new ArrayList<>();

            String finalBiggestGroup = biggestGroup; //Variable in lambda expression should be final
            deliveries.forEach((deliveryMethod, products) -> {
                products.removeAll(deliveryGroups.get(finalBiggestGroup));
                if (products.isEmpty())
                    emptyGoups.add(deliveryMethod);
            });

            //removes empty delivery groups
            emptyGoups.forEach(deliveries::remove);

        }

        return deliveryGroups;
    }
}
