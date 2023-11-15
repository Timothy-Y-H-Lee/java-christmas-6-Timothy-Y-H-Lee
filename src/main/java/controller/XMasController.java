package controller;

import static enums.EventDateRule.EVENT_MONTH;
import static enums.UserInterface.INVALIDATE_INPUT_VISIT_DAY;

import java.time.MonthDay;
import service.XMasService;
import view.InputView;

public class XMasController {
    private InputView inputView = new InputView();

    private XMasService xMasService = XMasService.getInstance();

    private String visitDay;

    public void startXmasEvent() {
        readDate();
        xMasService.setVisitDay(visitDay);
        checkDiscountDays();
    }

    private void checkDiscountDays() {
        this.xMasDayDiscount();
        this.weekdaysDiscount();
        this.weekendDiscount();
        this.specialDiscount();
    }

    private void specialDiscount() {
        xMasService.specialDiscount(visitDay);
    }

    private void weekendDiscount() {
        xMasService.weekendDiscount(visitDay);
    }
    private void weekdaysDiscount() {
        xMasService.weekdaysDiscount(visitDay);
    }

    private void xMasDayDiscount() {
        xMasService.xMasDayDiscount(visitDay);
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
