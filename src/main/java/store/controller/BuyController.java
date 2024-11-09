package store.controller;

import store.model.Product;
import store.model.Receipt;
import store.model.Store;
import store.view.InputView;
import store.view.OutputView;

import java.util.List;
import java.util.Map;

public class BuyController {
    public void buyProduct(Store store) {
        Receipt receipt = new Receipt();
        while (true) {
            try {
                Map<String, Integer> purchasedItems = InputView.purchasedItems();
                for (String key : purchasedItems.keySet()) {
                    int number = purchasedItems.get(key);
                    Product product = checkInventory(store, key, number);
                    if (product.isPromotion()) {
                        int eventProduct = product.addPromotions(number);
                        if (eventProduct != 0) {
                            OutputView.printAddEventProduct(eventProduct, product.getName());
                            if (InputView.yOrN()) {
                                number += eventProduct;
                                purchasedItems.put(key, number);
                            }
                        }
                        receipt.addItem(product, number, product.applyPromotions(number));
                    }
                    if (!product.isPromotion()) {
                        receipt.addItem(product, number, 0);
                    }
                }
                OutputView.printMemberShipDiscount();
                if (InputView.yOrN()) receipt.applyMembershipDiscount();
                receipt.print();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Product checkInventory(Store store, String product, int number) {
        if (product == null || number <= 0) {
            throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");
        }
        List<Product> productList = store.getProductList();
        for (Product p : productList) {
            if (p.getName().equals(product) && p.getQuantity() < number) {
                throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
            }
            if (p.getName().equals(product)) {
                return p;
            }
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.");
    }
}
