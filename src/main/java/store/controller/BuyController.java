package store.controller;

import store.model.Product;
import store.model.Receipt;
import store.model.Store;
import store.view.InputView;
import store.view.OutputView;

import java.util.Map;

public class BuyController {
    private final Store store;

    public BuyController(Store store) {
        this.store = store;
    }

    public void buyProduct() {
        boolean continueShopping = true;
        while (continueShopping) {
            Receipt receipt = new Receipt();
            try {
                Map<String, Integer> purchasedItems = InputView.purchasedItems();
                processPurchasedItems(receipt, purchasedItems);
                applyMembershipDiscount(receipt);
                receipt.print();
                OutputView.askForAdditionalPurchase();
                continueShopping = InputView.yOrN();
                if (continueShopping) OutputView.printInventoryInformation(store);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void processPurchasedItems(Receipt receipt, Map<String, Integer> purchasedItems) {
        for (String key : purchasedItems.keySet()) {
            int number = purchasedItems.get(key);
            Product product = checkInventory(key, number);
            if (product.isPromotion()) {
                processPromotionalItem(receipt, product, number, purchasedItems, key);
            } else {
                addItemToReceipt(receipt, product, number, 0);
            }
        }
    }

    private void processPromotionalItem(Receipt receipt, Product product, int number, Map<String, Integer> purchasedItems, String key) {
        int eventProduct = product.addPromotions(number);
        if (eventProduct != 0) {
            if (product.getQuantity() >= eventProduct + number) {
                OutputView.printAddEventProduct(eventProduct, product.getName());
                if (InputView.yOrN()) {
                    number += eventProduct;
                    purchasedItems.put(key, number);
                }
            }
            if (product.getQuantity() < eventProduct + number) {
                OutputView.PromotionNotApplied(product);
                if (!InputView.yOrN()) {
                    number -= product.getPromotionBuy();
                    purchasedItems.put(key, number);
                }
            }
        }
        store.deductInventory(product.getName(), number);
        addItemToReceipt(receipt, product, number, product.applyPromotions(number));
    }

    private void addItemToReceipt(Receipt receipt, Product product, int quantity, int promotionAppliedQuantity) {
        receipt.addItem(product, quantity, promotionAppliedQuantity);
    }

    private void applyMembershipDiscount(Receipt receipt) {
        OutputView.printMemberShipDiscount();
        if (InputView.yOrN()) {
            receipt.applyMembershipDiscount();
        }
    }

    private Product checkInventory(String productName, int quantity) {
        if (productName == null || quantity <= 0) {
            throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");
        }
        Product product = store.findProductByName(productName)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요."));
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }
        return product;
    }
}
