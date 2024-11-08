package store.controller;

import store.model.Product;
import store.model.Store;
import store.view.InputView;

import java.util.List;
import java.util.Map;

public class BuyController {

    public void buyProduct(Store store) {
        while(true) {
            Map<String, Integer> purchasedItems = InputView.purchasedItems();
            for (String key : purchasedItems.keySet()) {
                try {
                    Product product = checkInventory(store, key, purchasedItems.get(key));

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private Product checkInventory(Store store, String product, int number) {
        if (product == null || number <= 0) {
            throw new IllegalArgumentException("[Error] 잘못된 입력입니다. 다시 입력해 주세요.");
        }
        List<Product> productList = store.getProductList();
        for (Product p : productList) {
            if (p.getName().equals(product) && p.getQuantity() < number) {
                throw new IllegalArgumentException("[Error] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
            }
            if (p.getName().equals(product)) {
                return p;
            }
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.");
    }
}
