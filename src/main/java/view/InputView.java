package view;

import static enums.UserInterface.*;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String readDate() {
        System.out.println(REQUEST_INPUT_VISIT_DAY.getValue());
        return Console.readLine();
    }

    public String readOrderedMenu() {
        System.out.println(REQUEST_INPUT_MENU_ORDER.getValue());
        return Console.readLine();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}
