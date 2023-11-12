package enums;

import java.util.ArrayList;
import java.util.List;

public enum MenuName {
    MENU_APPETIZER_MUSHROOM_SOUP("양송이수프"),
    MENU_APPETIZER_TAPAS("타파스"),
    MENU_APPETIZER_CAESAR_SALAD("시저샐러드"),
    MENU_MAIN_T_BONE_STEAK("티본스테이크"),
    MENU_MAIN_BBQ_RIBS("바비큐립"),
    MENU_MAIN_SEAFOOD_PASTA("해산물파스타"),
    MENU_MAIN_CHRISTMAS_PASTA("크리스마스파스타"),
    MENU_DESSERT_CHOCOLATE_CAKE("초코케이크"),
    MENU_DESSERT_ICE_CREAM("아이스크림"),
    MENU_DRINK_ZERO_COKE("제로콜라"),
    MENU_DRINK_RED_WINE("레드와인"),
    MENU_DRINK_CHAMPAGNE("샴페인")
    ;

    private String value;

    MenuName(String value) {
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
        for (MenuName menuName : MenuName.values()) {
            keyList.add(menuName.getName());
        }
        return keyList;
    }
}
