package store.view;

import store.model.Product;
import store.model.Store;

import java.util.List;

public class OutputView {
    public static void printInventoryInformation(Store store) {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.");
        System.out.println();
        List<Product> productList = store.getProductList();
        productList.forEach(System.out::println);
        System.out.println();
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1]");
    }

    public static void printAddEventProduct(int number, String productName) {
        System.out.println("현재 " + productName + "은(는) " + number + "개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)");
    }

    public static void printMemberShipDiscount() {
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
    }

    public static void askForAdditionalPurchase() {
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
    }

    public static void PromotionNotApplied(Product product) {
        System.out.println("현재 " + product.getName() + " " + product.getPromotionBuy() + "개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까?");
    }
}
