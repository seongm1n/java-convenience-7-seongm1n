package store.model;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Product> productList = new ArrayList<>();
    private List<Promotion> promotionList = new ArrayList<>();

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
}
