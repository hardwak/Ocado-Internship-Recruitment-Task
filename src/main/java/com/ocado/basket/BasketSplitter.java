package com.ocado.basket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* Shipping methods:
*   Pick-up point
*   Parcel locker
*   Courier
*   Same day delivery
*   Next day shipping
*   Mailbox delivery
*   In-store pick-up
*   Express Collection
*
*
**/

public class BasketSplitter {

    private final Map<String, List<String>> products;

    public BasketSplitter(String absolutePathToConfigFile) {
        try {
            products = JsonParser.loadDeliveryOptions(absolutePathToConfigFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Map<String, List<String>> split(List<String> items) {
//        Map<String, List<String>> basket = new HashMap<>();
//        for (String item : items) {
//            if (products.containsKey(item)) {
//                basket.put(item, products.get(item));
//            }
//        }

        //Creates map as (key, value) - (Delivery Method, List of Products)
        Map<String, List<String>> deliveries = new HashMap<>();
        for (String item : items) {
            for (String deliveryMethod : products.get(item)) {
                if (deliveries.containsKey(deliveryMethod)) {
                    deliveries.get(deliveryMethod).add(item);
                }
                else {
                    List<String> products = new ArrayList<>();
                    products.add(item);
                    deliveries.put(deliveryMethod, products);
                }
            }
        }
        
//        for (String item : basket.keySet()){
//            System.out.println("\"" + item + "\" : \"" + basket.get(item)+"\"");
//        }
//        System.out.println();
//        for (String deliveryMethod : deliveries.keySet()) {
//            System.out.println("\"" + deliveryMethod +  "\" : \"" + deliveries.get(deliveryMethod)+"\"");
//        }

        return deliveries;
    }


}
