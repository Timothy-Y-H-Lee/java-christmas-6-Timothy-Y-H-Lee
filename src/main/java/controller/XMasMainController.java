package controller;

import static enums.EventDateRule.EVENT_MONTH;
import static enums.UserInterface.INVALIDATE_INPUT_VISIT_DAY;

import java.time.MonthDay;
import view.InputView;

public class XMasMainController {
    private InputView inputView = new InputView();
    private String visitDay;

    public void startXmasEvent() {
        readDate();
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
