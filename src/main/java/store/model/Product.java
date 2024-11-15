package store.model;

import camp.nextstep.edu.missionutils.DateTimes;

import java.text.NumberFormat;
import java.time.LocalDateTime;

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
        if (promotion == null) {
            return false;
        }
        LocalDateTime time = DateTimes.now();
        if (time.isBefore(promotion.getStartDate()) || time.isAfter(promotion.getEndDate())) {
            return false;
        }
        return true;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPromotionBuy() {
        return promotion.getBuy();
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
