package enums;

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

    public static List<String> getKeyList() {
        List<String> keyList = new ArrayList<>();
        for (MenuCategory category : MenuCategory.values()) {
            keyList.add(category.getName());
        }
        return keyList;
    }
}
