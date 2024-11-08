package store.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashMap;
import java.util.Map;

public class InputView {
    public static Map<String, Integer> purchasedItems() {
        String input = Console.readLine();
        Map<String, Integer> itemsMap = new HashMap<>();
        String[] items = input.substring(1, input.length() - 1).split("\\],\\[");
        for (String item : items) {
            String[] parts = item.split("-");
            String name = parts[0];
            int quantity = Integer.parseInt(parts[1]);
            itemsMap.put(name, quantity);
        }
        return itemsMap;
    }
}
