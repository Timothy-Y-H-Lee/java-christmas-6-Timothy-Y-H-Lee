package domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderedMenuInfoTest {
    OrderedMenuInfo orderedMenuInfo;
    @BeforeEach
    void setUp() {
        orderedMenuInfo = new OrderedMenuInfo();
    }

    @DisplayName("메뉴명과 수량이 유효한 경우")
    @Test
    void testValidMenuList() {
        String userInput = "해산물파스타-2,레드와인-1,초코케이크-1"; // give
        orderedMenuInfo.userInputOrderedMenu(userInput); // when
        Map<String, Integer> result = orderedMenuInfo.getUserInputOrderedMenu(); // when
        assertAll(() -> assertEquals(3, result.size()),
                () -> assertEquals(2, result.get("해산물파스타")),
                () -> assertEquals(1, result.get("레드와인")),
                () -> assertEquals(1, result.get("초코케이크"))
        ); // then
    }

    @DisplayName("고객이 메뉴판에 없는 메뉴를 입력하는 경우")
    @Test
    void testNotValiedMenuName() {
        String userInput = "해산물파스타-2,없는 메뉴-1,초코케이크-1"; // give
        assertThrows(IllegalArgumentException.class, () -> {
            orderedMenuInfo.userInputOrderedMenu(userInput); // when
            Map<String, Integer> result = orderedMenuInfo.getUserInputOrderedMenu(); // when
        });
    }

    @DisplayName("메뉴명이 중복되어 있는 경우")
    @Test
    void testDuplicatedMenu() {
        String userInput = "해산물파스타-2,해산물파스타-1,초코케이크-1"; // give
        assertThrows(IllegalArgumentException.class, () -> {
            orderedMenuInfo.userInputOrderedMenu(userInput); // when
            Map<String, Integer> result = orderedMenuInfo.getUserInputOrderedMenu(); // when
        });
    }

    @DisplayName("주문할 메뉴의 수량이 잘못된 경우")
    @Test
    void testNotValiedMenuQuntity() {
        String userInput = "해산물파스타-2,제로콜라-a,초코케이크-1"; // give
        assertThrows(IllegalArgumentException.class, () -> {
            orderedMenuInfo.userInputOrderedMenu(userInput); // when
            Map<String, Integer> result = orderedMenuInfo.getUserInputOrderedMenu(); // when
        });
    }

    @DisplayName("메뉴의 개수는 1 이상의 숫자만 입력가능")
    @Test
    void testNotValiedMenuQuntityWithZero() {
        String userInput = "해산물파스타-2,제로콜라-0,초코케이크-0"; // give
        assertThrows(IllegalArgumentException.class, () -> {
            orderedMenuInfo.userInputOrderedMenu(userInput); // when
            Map<String, Integer> result = orderedMenuInfo.getUserInputOrderedMenu(); // when
        });
    }
}