package store.controller;

import store.model.Product;
import store.model.Store;
import store.view.OutputView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

public class StoreController {

    public void run() {
        Store store = new Store();
        productListUp(store);
        OutputView.printInventoryInformation(store);
    }

    private void productListUp(Store store) {
        String productsContent = readMarkdownFile("products.md");
        String[] lines = productsContent.split("\n");
        Arrays.stream(lines)
                .map(this::parseProduct)
                .filter(product -> product != null)
                .forEach(store::addProduct);
    }

    private Product parseProduct(String line) {
        String[] parts = line.split(",");
        String name = parts[0];
        if (name.equals("name")) return null;
        int price = Integer.parseInt(parts[1]);
        int quantity = Integer.parseInt(parts[2]);
        String promotion = parts[3];
        return new Product(name, price, quantity, promotion);
    }

    private String readMarkdownFile(String fileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException(fileName + " 파일을 찾을 수 없습니다.");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
