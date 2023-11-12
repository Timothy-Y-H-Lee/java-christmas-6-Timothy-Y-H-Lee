package enums;

import java.util.ArrayList;
import java.util.List;

public enum MenuPrice {
    MENU_APPETIZER_MUSHROOM_SOUP_PRICE(6_000),
    MENU_APPETIZER_TAPAS_PRICE(5_500),
    MENU_APPETIZER_CAESAR_SALAD_PRICE(8_000),
    MENU_MAIN_T_BONE_STEAK_PRICE(55_000),
    MENU_MAIN_BBQ_RIBS_PRICE(54_000),
    MENU_MAIN_SEAFOOD_PASTA_PRICE(35_000),
    MENU_MAIN_CHRISTMAS_PASTA_PRICE(25_000),
    MENU_DESSERT_CHOCOLATE_CAKE_PRICE(15_000),
    MENU_DESSERT_ICE_CREAM_PRICE(5_000),
    MENU_DRINK_ZERO_COKE_PRICE(3_000),
    MENU_DRINK_RED_WINE_PRICE(60_000),
    MENU_DRINK_CHAMPAGNE_PRICE(25_000)
    ;

    private Integer value;

    MenuPrice(Integer value) {
        this.value = value;
    }

    public String getKey() {
        return name();
    }

    public Integer getValue() {
        return value;
    }

    // Key를 기준으로 Price를 반환
    public static Integer findPriceByKey(String priceKey) {
        for (MenuPrice menuPrice : MenuPrice.values()) {
            if (menuPrice.getKey().equals(priceKey)) return menuPrice.getValue();
        }
        throw new IllegalArgumentException(UserInterface.ILLEGAL_MENU_ORDER.getValue());
    }

    public static List<String> getKeyList() {
        List<String> keyList = new ArrayList<>();
        for (MenuPrice menuPrice : MenuPrice.values()) {
            keyList.add(menuPrice.getKey());
        }
        return keyList;
    }
}
