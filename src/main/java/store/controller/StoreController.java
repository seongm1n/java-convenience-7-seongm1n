package store.controller;

import store.model.Product;
import store.model.Promotion;
import store.model.Store;
import store.view.OutputView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class StoreController {

    public void run() {
        Store store = new Store();
        BuyController buyController = new BuyController();
        promotionListUp(store);
        productListUp(store);
        buyController.buyProduct(store);
    }

    private void promotionListUp(Store store) {
        String productsContent = readMarkdownFile("promotions.md");
        String[] lines = productsContent.split("\n");
        Arrays.stream(lines)
                .map(this::parsePromotion)
                .filter(Objects::nonNull)
                .forEach(store::addPromotion);
    }

    private Promotion parsePromotion(String line) {
        String[] parts = line.split(",");
        String name = parts[0];
        if (name.equals("name")) return null;
        int buy = Integer.parseInt(parts[1]);
        int get = Integer.parseInt(parts[2]);
        String startDate = parts[3];
        String endDate = parts[4];
        return new Promotion(name, buy, get, startDate, endDate);
    }

    private void productListUp(Store store) {
        String productsContent = readMarkdownFile("products.md");
        String[] lines = productsContent.split("\n");
        Arrays.stream(lines)
                .map(line -> parseProduct(line, store))
                .filter(Objects::nonNull)
                .forEach(store::addProduct);
    }

    private Product parseProduct(String line, Store store) {
        String[] parts = line.split(",");
        String name = parts[0];
        if (name.equals("name")) return null;
        int price = Integer.parseInt(parts[1]);
        int quantity = Integer.parseInt(parts[2]);
        Promotion promotion = store.findPromotionByName(parts[3]);
        return new Product(name, price, quantity, promotion);
    }

    private String readMarkdownFile(String fileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("[ERROR] 재고 파일을 찾을 수 없습니다.");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
