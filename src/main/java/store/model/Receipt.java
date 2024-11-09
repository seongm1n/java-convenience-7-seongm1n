package store.model;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private final List<ReceiptItem> items = new ArrayList<>();
    private int totalAmount = 0;
    private int eventDiscount = 0;
    private int membershipDiscount = 0;

    public void addItem(Product product, int quantity, int promotionAppliedQuantity) {
        int itemTotal = product.getPrice() * quantity;
        items.add(new ReceiptItem(product.getName(), product.getPrice(), quantity, promotionAppliedQuantity, itemTotal));
        totalAmount += itemTotal;
        if (promotionAppliedQuantity > 0) {
            eventDiscount += product.getPrice() * promotionAppliedQuantity;
        }
    }

    public void applyMembershipDiscount() {
        int discountedTotal = totalAmount - eventDiscount;
        membershipDiscount = (int) (discountedTotal * 0.3);
    }

    public void print() {
        System.out.println("============= W 편의점 ==============");
        System.out.printf("%-12s %-8s %-10s\n", "상품명", "수량", "금액");
        System.out.println("====================================");
        for (ReceiptItem item : items) {
            System.out.printf("%-12s %-8d %-10d\n", item.getName(), item.getQuantity(), item.getItemTotal());
        }
        System.out.println("====================================");
        System.out.printf("%-12s %-8s %-10s\n", "총구매액", "", totalAmount);
        System.out.printf("%-12s %-8s %-10s\n", "행사할인", "", "-" + eventDiscount);
        System.out.printf("%-12s %-8s %-10s\n", "멤버십할인", "", "-" + membershipDiscount);
        int finalAmount = totalAmount - eventDiscount - membershipDiscount;
        System.out.printf("%-12s %-8s %-10s\n", "내실돈", "", finalAmount);
        System.out.println("====================================");
    }

    private static class ReceiptItem {
        private final String name;
        private final int price;
        private final int quantity;
        private final int promotionAppliedQuantity;
        private final int itemTotal;

        public ReceiptItem(String name, int price, int quantity, int promotionAppliedQuantity, int itemTotal) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.promotionAppliedQuantity = promotionAppliedQuantity;
            this.itemTotal = itemTotal;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getPromotionAppliedQuantity() {
            return promotionAppliedQuantity;
        }

        public int getItemTotal() {
            return itemTotal;
        }
    }
}
