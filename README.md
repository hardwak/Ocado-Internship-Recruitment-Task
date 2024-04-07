# Basket Splitter - Recruitment task for Ocado Technology
Basket Splitter is a Java-based library designed to classify items in a customer's basket into separate shipping clusters based on predefined shipping preferences. Using a configuration file, it determines the available delivery modes for each product and efficiently distributes products into delivery groups. This approach aims to reduce the total number of groups and ensure that each group contains as many elements as possible.
## Installation
For application installation you can use:
```bash
  mvn clean install
```
## Algorithm
The `split` method in the `BasketSplitter` splits the basket of products into delivery groups. Algorithm works as follows:
1. Based on product list creates a Map where Key is a delivery method.
2. Finds the biggest group of products and removes it to final result.
3. Removes from other delivery groups products, that are absent in the biggest group.
4. Removes empty delivery groups.
5. Repeats steps 2-4 until no delivery groups are left.
## Usage Example
The following code creates an instance of BasketSplitter with the provided configuration file, then splits the provided products into optimal shipping methods groups.
```java
BasketSplitter basketSplitter = new BasketSplitter("/path/to/json/file.json");  
List<String> items = List.of("Cocoa Butter", "Tart - Raisin And Pecan", "Table Cloth 54x72 White", "Flower - Daisies", "Fond - Chocolate", "Cookies - Englishbay Wht");
basketSplitter.split(items);
```
or
```java
BasketSplitter basketSplitter = new BasketSplitter("/path/to/json/file.json");  
List<String> items = getItemsFromBasket("path/to/json/basket.json");
basketSplitter.split(items);
```
basket.json:
```json
["Cocoa Butter", "Tart - Raisin And Pecan", "Table Cloth 54x72 White", "Flower - Daisies", "Fond - Chocolate", "Cookies - Englishbay Wht"]
```
## Library Methods
#### `BasketSplitter`
 - ##### `split(List<String> items)`
   Takes List of products, separates them into optimal delivery groups and returns them as Map.\
   When `items` are empty or have a products that are absent in config file returns empty Map.
   ## Solution after deadline
   Because of lack of time I did not have enough time to change method, but in 15 minutes before deadline I find improvement that will make `split` method return files that are not absent
   in delivery group with key "No Delivery method".\
   Old Version:
   ```java
           Map<String, List<String>> deliveries = new HashMap<>();

        for (String item : items) { //iterating over items in basket
            if (products.get(item) == null) {
                System.out.println("Provided basket file has products that are not absent in config file");
                return new HashMap<>();
            }

            for (String deliveryMethod : products.get(item)) {  //iterating over delivery method of item
   ```
   New Version:
   ```java
   Map<String, List<String>> deliveries = new HashMap<>();

        for (String item : items) { //iterating over items in basket
            products.computeIfAbsent(item, k -> List.of("No Delivery Method"));
   
            for (String deliveryMethod : products.get(item)) {  //iterating over delivery method of item
   ```
#### `JsonParser`
 - ##### `loadDeliveryOptions(String path)`
   Loads from JSON file list of products with available delivery methods and then returns Map according to the pattern: `Key - Product, Value - List of delivery methods`.
 - ##### `getItemsFromBasket(String path)`
   Loads from JSON file list of products and then returns as List of products.
## Tests
All tests were made with JUnit 5.
 - #### `BasketSplitterTest`
   Test methods compares expected and actual result to make sure that algorithm works correctly.
- #### `JsonParserTest`
  This tests checks if JSON files in different scenarios are read properly and conversion was successful.
## Build with:
- `Java 17` - Project base
- `Maven` - Build tool
- `JUnit 5` - Unit testing
- `JetBrains IntellijIDEA` - IDE
- `Jackson` - working with JSON files
