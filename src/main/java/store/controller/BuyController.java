package store.controller;

import store.model.Product;
import store.model.Store;
import store.view.InputView;

import java.util.List;
import java.util.Map;

public class BuyController {

    public void buyProduct(Store store) {
        Map<String, Integer> purchasedItems = InputView.purchasedItems();
        for (String key : purchasedItems.keySet()) {
            Product product = checkInventory(store, key, purchasedItems.get(key));
        }

    }

    private Product checkInventory(Store store, String product, int number) {
        List<Product> productList = store.getProductList();
        for (Product p : productList) {
            if (p.getName().equals(product) && p.getQuantity() >= number) {
                return p;
            }
        }
        return null;
    }
}
