package enums;

import static enums.MenuName.MENU_DRINK_CHAMPAGNE;

public enum GiftMenuName {
    GIFT_MENU_NAME(MENU_DRINK_CHAMPAGNE.getValue());

    private String value;

    GiftMenuName(String value) {
        this.value = value;
    }

    public String getName() {
        return name();
    }

    public String getValue() {
        return value;
    }
}
