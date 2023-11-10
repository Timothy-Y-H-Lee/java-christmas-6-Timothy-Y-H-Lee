package enums;

public enum MenuNameDefine {
    MENU_MUSHROOM_SOUP("양송이수프"),
    MENU_TAPAS("타파스"),
    MENU_CAESAR_SALAD("시저샐러드"),
    MENU_T_BONE_STEAK("티본스테이크"),
    MENU_BBQ_RIBS("바비큐립"),
    MENU_SEAFOOD_PASTA("해산물파스타"),
    MENU_CHRISTMAS_PASTA("크리스마스파스타"),
    MENU_CHOCOLATE_CAKE("초코케이크"),
    MENU_ICE_CREAM("아이스크림"),
    MENU_ZERO_COKE("제로콜라"),
    MENU_RED_WINE("레드와인"),
    MENU_CHAMPAGNE("샴페인")
    ;

    private String value;

    MenuNameDefine(String value) {
        this.value = value;
    }

    public String getName() {
        return name();
    }

    public String getValue() {
        return value;
    }
}
