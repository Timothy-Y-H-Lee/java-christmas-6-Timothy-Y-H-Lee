package enums;

import static enums.UserInterface.ILLEGAL_MENU_ORDER;

import java.util.ArrayList;
import java.util.List;

public enum MenuCategory {
    MENU_APPETIZER("애피타이저"),
    MENU_MAIN("메인"),
    MENU_DESSERT("디저트"),
    MENU_DRINK("음료")
    ;

    private String value;

    MenuCategory(String value) {
        this.value = value;
    }

    public String getName() {
        return name();
    }

    public String getValue() {
        return value;
    }

    public static List<String> getStrKeyList() {
        List<String> keyList = new ArrayList<>();
        for (MenuCategory category : MenuCategory.values()) {
            keyList.add(category.getName());
        }
        return keyList;
    }

    public static List<MenuCategory> getKeyList() {
        List<MenuCategory> keyList = new ArrayList<>();
        for (MenuCategory category : MenuCategory.values()) {
            keyList.add(category);
        }
        return keyList;
    }

    public static MenuCategory findCategoryByMenuName(String strMenuName) {
        for (MenuName menuName : MenuName.values()) {
            if (menuName.getValue().equals(strMenuName)) return findCategoryByMenuNameKey(menuName);
        }
        throw new IllegalArgumentException(ILLEGAL_MENU_ORDER.getValue());
    }

    private static MenuCategory findCategoryByMenuNameKey(MenuName menuNameKey) {
        for (MenuCategory menuCategory : MenuCategory.getKeyList()) {
            if (menuNameKey.getName().contains(menuCategory.name())) return menuCategory;
        }
        throw new IllegalArgumentException(ILLEGAL_MENU_ORDER.getValue());
    }
}
