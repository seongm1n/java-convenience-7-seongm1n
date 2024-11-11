package store.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.Map;

public class InputView {
    public static Map<String, Integer> purchasedItems() {
        while (true) {
            try {
                String input = Console.readLine();
                InputParser.validateInputFormat(input);
                return InputParser.parseItems(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static boolean yOrN() {
        String input = Console.readLine();
        if (input.equals("Y")) return true;
        if (input.equals("N")) return false;
        System.out.println("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");
        return yOrN();
    }
}
