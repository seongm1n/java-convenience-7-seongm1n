package store.model;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Product> productList = new ArrayList<>();

    public void addProduct(Product product) {
        productList.add(product);
    }

    public List<Product> getProducts() {
        return productList;
    }
}
