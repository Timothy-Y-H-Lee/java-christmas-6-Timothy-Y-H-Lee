package view;

import static enums.UserInterface.REQUEST_INPUT_VISIT_DAY;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String readDate() {
        System.out.println(REQUEST_INPUT_VISIT_DAY.getValue());
        String input = Console.readLine();
        return input;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}
