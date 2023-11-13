package domain;

import static enums.MenuCategory.MENU_MAIN;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import enums.MenuCategory;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = Menu.getInstance();
    }

    @DisplayName("Menu의 카테고리 갯수 확인")
    @Test
    void check_menu_category_count() {
        // give
        int menuCatogoryCount = MenuCategory.values().length;
        // when
        int menuCatogorys = MenuCategory.getStrKeyList().size();
        // then
        assertEquals(menuCatogoryCount, menuCatogorys);
    }

    @DisplayName("메뉴 초기화에서 Exception을 던지지 않는지 확인")
    @Test
    void init_menu_test() {
        assertDoesNotThrow(() -> Menu.getInstance());
    }

    @Test
    void 메뉴명을_전달하면_Enum에_해당하는_Key와_가격을_반환() {
        HashMap<String, Integer> MenuMainSeafoodPastaHashMap
                = new HashMap<>( Map.of("MENU_MAIN_SEAFOOD_PASTA", 35_000)); // give
        HashMap<String, Integer> menuKeyPrice = menu.findMenuKeyAndPriceByMenuName("해산물파스타"); // when
        // then
        assertAll(
                () -> assertEquals(MenuMainSeafoodPastaHashMap, menuKeyPrice),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> menu.findMenuKeyAndPriceByMenuName("없는메뉴"))
        );
    }

    @DisplayName("특정 메뉴에 해당하는 판매 메뉴의 카테고리(애피타이저, 메인, 디저트, 음료)를 반환")
    @Test
    void 특정_메뉴에_해당하는_판매_메뉴의_카테고리를_반환() {
        // give
        String menuName = "티본스테이크";
        // when
        String menuCategory = MenuCategory.findCategoryByMenuName(menuName).getName();
        // then
        assertEquals(MENU_MAIN.getName(), menuCategory);
    }

    // @TODO: 음료만 주문했는지 확인하기, 단, 음료이외에 주문할 수 없는 메뉴도 포함되어 있는지 먼저 체크
}