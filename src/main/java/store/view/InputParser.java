package store.view;

import java.util.HashMap;
import java.util.Map;

public class InputParser {
    public static void validateInputFormat(String input) {
        if (!input.startsWith("[") || !input.endsWith("]")) {
            throw new IllegalArgumentException("[ERROR] 입력 형식이 잘못되었습니다. 예: [사이다-3],[에너지바-2]");
        }
    }

    public static Map<String, Integer> parseItems(String input) {
        Map<String, Integer> itemsMap = new HashMap<>();
        String[] items = input.substring(1, input.length() - 1).split("\\],\\[");

        for (String item : items) {
            String[] parts = item.split("-");
            itemsMap.put(parts[0], parseQuantity(parts));
        }
        return itemsMap;
    }

    private static int parseQuantity(String[] parts) {
        if (parts.length != 2) {
            throw new IllegalArgumentException("[ERROR] 각 항목은 '상품명-수량' 형식이어야 합니다.");
        }
        try {
            return Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 수량은 숫자로 입력해 주세요.");
        }
    }
}
