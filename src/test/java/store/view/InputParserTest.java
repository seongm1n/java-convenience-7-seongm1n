package store.view;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputParserTest {

    @Test
    void 정상_입력() {
        String input = "[사이다-3],[에너지바-2]";
        Map<String, Integer> result = InputParser.parseItems(input);

        assertEquals(2, result.size());
        assertEquals(3, result.get("사이다"));
        assertEquals(2, result.get("에너지바"));
    }

    @Test
    void 잘못된_수량_형식() {
        String input = "[사이다-three],[에너지바-2]";

        assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parseItems(input);
        }, "[ERROR] 수량은 숫자로 입력해 주세요.");
    }

    @Test
    void 잘못된_항목_형식() {
        String input = "[사이다3],[에너지바-2]";

        assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parseItems(input);
        }, "[ERROR] 각 항목은 '상품명-수량' 형식이어야 합니다.");
    }

    @Test
    void 정상_형식() {
        String input = "[사이다-3],[에너지바-2]";
        InputParser.validateInputFormat(input);
    }

    @Test
    void 잘못된_형식() {
        String input = "사이다-3],[에너지바-2]";

        assertThrows(IllegalArgumentException.class, () -> {
            InputParser.validateInputFormat(input);
        }, "[ERROR] 입력 형식이 잘못되었습니다. 예: [사이다-3],[에너지바-2]");
    }
}
