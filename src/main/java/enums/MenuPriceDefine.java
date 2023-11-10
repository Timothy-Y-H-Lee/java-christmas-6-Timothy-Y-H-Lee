package enums;

public enum MenuPriceDefine {
    MENU_MUSHROOM_SOUP_PRICE(6_000),
    MENU_TAPAS_PRICE(5_500),
    MENU_CAESAR_SALAD_PRICE(8_000),
    MENU_T_BONE_STEAK_PRICE(55_000),
    MENU_BBQ_RIBS_PRICE(54_000),
    MENU_SEAFOOD_PASTA_PRICE(35_000),
    MENU_CHRISTMAS_PASTA_PRICE(25_000),
    MENU_CHOCOLATE_CAKE_PRICE(15_000),
    MENU_ICE_CREAM_PRICE(5_000),
    MENU_ZERO_COKE_PRICE(3_000),
    MENU_RED_WINE_PRICE(60_000),
    MENU_CHAMPAGNE_PRICE(25_000)
    ;

    private Integer value;

    MenuPriceDefine(Integer value) {
        this.value = value;
    }

    public String getKey() {
        return name();
    }

    public Integer getValue() {
        return value;
    }
}
