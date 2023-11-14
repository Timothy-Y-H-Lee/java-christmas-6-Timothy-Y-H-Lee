package enums;

public enum EventDateRule {
    EVENT_START_MONTH_DATE(1),
    EVENT_END_MONTH_DATE(31),
    XMAS_DDAY_START_DATE(1),
    XMAS_DDAY_END_DATE(25),
    EVENT_YEAR(2023),
    EVENT_MONTH(12)
    ;
    private Integer value;

    EventDateRule(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name();
    }

    public Integer getValue() {
        return value;
    }
}
