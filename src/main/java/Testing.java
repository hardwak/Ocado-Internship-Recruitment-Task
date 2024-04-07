import com.ocado.basket.BasketSplitter;
import com.ocado.basket.JsonParser;

import java.io.IOException;

public class Testing {
    public static void main(String[] args) {
        try {
            System.out.println(JsonParser.loadDeliveryOptions("src/main/resources/config.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println(JsonParser.getItemsFromBasket("src/main/resources/basket-1.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BasketSplitter splitter = new BasketSplitter("src/main/resources/config.json");
        try {
            splitter.split(JsonParser.getItemsFromBasket("src/main/resources/basket-3.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
