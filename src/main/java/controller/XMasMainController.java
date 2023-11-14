package controller;

import static enums.EventDateRule.EVENT_MONTH;
import static enums.UserInterface.INVALIDATE_INPUT_VISIT_DAY;

import domain.VisitDate;
import java.time.MonthDay;
import view.InputView;

public class XMasMainController {
    private InputView inputView = new InputView();
    private String visitDay;
    // 크리스마스 디데이 할인 여부
    private Boolean isXMasDayDiscount = false;
    // 평일 할인 여부
    private Boolean isWeekdaysDiscount = false;
    // 주말 할인 여부
    private Boolean isWeekendDiscount = false;
    // 특별 할인 여부
    private Boolean isSpecialDiscount = false;


    public void startXmasEvent() {
        readDate();
        checkDiscountDays();
    }

    private void checkDiscountDays() {
        this.isXMasDayDiscount();
    }

    private void isXMasDayDiscount() {
        isXMasDayDiscount = VisitDate.getInstance().rangeIn25Day(visitDay);
    }

    public void readDate() {
        try {
            String readDate = inputView.readDate();
            MonthDay.of(EVENT_MONTH.getValue(), Integer.valueOf(readDate));
            this.visitDay = readDate;
        } catch (Exception e) {
            inputView.printMessage(INVALIDATE_INPUT_VISIT_DAY.getValue());
            readDate();
        }
    }
}
