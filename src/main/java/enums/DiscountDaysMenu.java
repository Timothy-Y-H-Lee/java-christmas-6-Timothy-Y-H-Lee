package enums;

import static enums.MenuCategory.MENU_DESSERT;
import static enums.MenuCategory.MENU_MAIN;

public enum DiscountDaysMenu {
    WEEKDAYS_DISCOUNT_MENU(MENU_DESSERT), // 할인 대상 메뉴 : 평일에는 디저트 메뉴
    WEEKEND_DISCOUNT_MENU(MENU_MAIN) // 할인 대상 메뉴 : 주말에는 메인 메뉴
    ;

    private MenuCategory value;

    DiscountDaysMenu(MenuCategory value) {
        this.value = value;
    }

    public String getName() {
        return name();
    }

    public MenuCategory getValue() {
        return value;
    }
}
