package store.model;

import java.text.NumberFormat;

public class Product {
    private final String name;
    private int price;
    private int quantity;
    private String promotion;

    public Product(String name, int price, int quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        NumberFormat formatter = NumberFormat.getInstance();
        StringBuilder result = new StringBuilder("- " + name + " " + formatter.format(price) + "원");
        if (quantity > 0) result.append(" ").append(quantity).append("개");
        if (quantity == 0) result.append(" 재고 없음");
        if (!promotion.equals("null")) result.append(" ").append(promotion);
        return result.toString();
    }
}
