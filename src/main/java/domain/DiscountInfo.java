package domain;

public class DiscountInfo {
    private String visitDay;
    // 크리스마스 디데이 할인 여부
    private Boolean isXMasDayDiscount = false;
    // 평일 할인 여부
    private Boolean isWeekdaysDiscount = false;
    // 주말 할인 여부
    private Boolean isWeekendDiscount = false;
    // 특별 할인 여부
    private Boolean isSpecialDiscount = false;

    public void setVisitDay(String visitDay) {
        this.visitDay = visitDay;
    }

    public void xMasDayDiscount(String day) {
        isXMasDayDiscount = VisitDate.getInstance().rangeIn25Day(day);
    }

    public void weekdaysDiscount(String day) {
        isWeekdaysDiscount = VisitDate.getInstance().isWeekDaysDiscountDay(day);
    }

    public void weekendDiscount(String day) {
        isWeekendDiscount = VisitDate.getInstance().isWeekendDiscountDay(day);
    }

    public void specialDiscount(String day) {
        isSpecialDiscount = VisitDate.getInstance().isSpecialDiscountDay(day);
    }

}
