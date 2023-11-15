package controller;

import static enums.EventDateRule.EVENT_MONTH;
import static enums.UserInterface.ILLEGAL_MENU_ORDER;
import static enums.UserInterface.PREVIEW_EVENT_MESSAGE;
import static enums.UserInterface.INVALIDATE_INPUT_VISIT_DAY;
import static enums.UserInterface.WELLCOME_MESSAGE;

import java.time.MonthDay;
import service.XMasService;
import view.InputView;
import view.OutputView;

public class XMasController {
    private InputView inputView = new InputView();
    private OutputView outputView = new OutputView();

    private XMasService xMasService = XMasService.getInstance();

    private String visitDay;

    public void startXmasEvent() {
        inputView.printMessage(WELLCOME_MESSAGE.getValue());
        readDate();
        xMasService.setVisitDay(visitDay);
        checkDiscountDays();
        readOrderedMenu();
        inputView.printMessage(String.format(PREVIEW_EVENT_MESSAGE.getValue(), visitDay));
        outputView.printOrderMenu(xMasService.getUserInputOrderedMenu());
        processDiscount();
    }

    private void processDiscount() {
        outputView.printBeforeDiscountTotalPrice(xMasService.getBeforeDiscountTotalPrice());
        outputView.printGiftMenu(xMasService.shouldGiveGiftMenu());
        outputView.printDiscountDetails(xMasService.getDiscountDetails(visitDay));
        outputView.printTotalDiscountDetails(xMasService.getTotalDiscountDetailsPrice());
        outputView.printAfterTotalDiscountPrice(xMasService.getAfterTotalDiscountPrice());
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
            String strReadDate = inputView.readDate();
            MonthDay.of(EVENT_MONTH.getValue(), Integer.valueOf(strReadDate));
            this.visitDay = strReadDate;
        } catch (IllegalStateException | IllegalArgumentException e) {
            inputView.printMessage(INVALIDATE_INPUT_VISIT_DAY.getValue());
            readDate();
        }
    }

    private void readOrderedMenu() {
        try {
            xMasService.orderedMenu(inputView.readOrderedMenu());
        } catch (IllegalStateException | IllegalArgumentException e) {
            inputView.printMessage(ILLEGAL_MENU_ORDER.getValue());
            readOrderedMenu();
        }
    }
}
