package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import enums.MenuCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    private Menu menu = Menu.getInstance();

    @DisplayName("Menu의 카테고리 갯수 확인")
    @Test
    void check_menu_category_count() {
        // give
        int menuCatogoryCount = MenuCategory.values().length;
        // when
        int menuCatogorys = MenuCategory.getKeyList().size();
        // then
        assertEquals(menuCatogoryCount, menuCatogorys);
    }

    @DisplayName("메뉴 초기화에서 Exception을 던지지 않는지 확인")
    @Test
    void init_menu_test() {
        assertDoesNotThrow(() -> Menu.getInstance());
    }

}