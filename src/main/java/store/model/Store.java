package store.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Store {
    private final List<Product> productList = new ArrayList<>();
    private final List<Promotion> promotionList = new ArrayList<>();

    public void addProduct(Product product) {
        productList.add(product);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addPromotion(Promotion promotion) {
        promotionList.add(promotion);
    }

    public List<Promotion> getPromotionList() {
        return promotionList;
    }

    public Promotion findPromotionByName(String name) {
        return promotionList.stream()
                .filter(promo -> promo.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void deductInventory(String productName, int quantity) {
        productList.stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst()
                .ifPresent(product -> product.setQuantity(product.getQuantity() - quantity));
    }

    public Optional<Product> findProductByName(String productName) {
        return productList.stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst();
    }
}
