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
}
