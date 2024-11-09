package store.model;

import java.text.NumberFormat;

public class Product {
    private final String name;
    private int price;
    private int quantity;
    private Promotion promotion;

    public Product(String name, int price, int quantity, Promotion promotion) {
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

    public int getPrice() {
        return price;
    }

    public int addPromotions(int number) {
        int buy = promotion.getBuy();
        int get = promotion.getGet();
        if (number % (buy + get) >= buy) {
            return get - (number % (buy + get) - buy);
        }
        return 0;
    }

    public int applyPromotions(int number) {
        int buy = promotion.getBuy();
        int get = promotion.getGet();
        return (number / (get + buy)) * get;
    }

    public boolean isPromotion() {
        return promotion != null;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        NumberFormat formatter = NumberFormat.getInstance();
        StringBuilder result = new StringBuilder("- " + name + " " + formatter.format(price) + "원");
        if (quantity > 0) result.append(" ").append(quantity).append("개");
        if (quantity == 0) result.append(" 재고 없음");
        if (promotion != null) result.append(" ").append(promotion.getName());
        return result.toString();
    }
}
