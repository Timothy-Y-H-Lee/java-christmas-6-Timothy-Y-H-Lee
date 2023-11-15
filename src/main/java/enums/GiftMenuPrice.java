package enums;

import static enums.MenuPrice.MENU_DRINK_CHAMPAGNE_PRICE;

public enum GiftMenuPrice {
    GIFT_MENU_PRICE(MENU_DRINK_CHAMPAGNE_PRICE.getValue()),
    GIFT_MENU_PRICE_CRITERIAL(120_000) // '증정메뉴' 증정 여부의 기준금액
    ;
    private Integer value;

    GiftMenuPrice(Integer value) {
        this.value = value;
    }
    public String getName() {
        return name();
    }

    public Integer getValue() {
        return value;
    }
}
