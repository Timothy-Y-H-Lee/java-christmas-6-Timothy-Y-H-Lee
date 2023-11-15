package enums;

// 평일, 주말 할인 금액
public enum DiscountDaysMenuPrice {
    WEEKDAYS_DISCOUNT_MENU_PER_PRICE(2_023),
    WEEKEND_DISCOUNT_MENU_PER_PRICE(2_023)
    ;
    private Integer value;

    DiscountDaysMenuPrice(Integer value) {
        this.value = value;
    }
    public String getName() {
        return name();
    }

    public Integer getValue() {
        return value;
    }
}
