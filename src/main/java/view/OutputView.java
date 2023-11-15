package view;

import static enums.UserInterface.BEFORE_DISCOUNT_TOTAL_PRICE_TITLE;
import static enums.UserInterface.ORDER_MENU_TITLE;

import java.util.Map;

public class OutputView {
    public void printOrderMenu(Map<String, Integer> orderedMenu) {
        System.out.println(ORDER_MENU_TITLE.getValue());
        orderedMenu.forEach((menuName, quantity) -> {
            System.out.println(menuName + " " + quantity + "개");
        });
        System.out.println();
    }

    public void printBeforeDiscountTotalPrice(Integer price) {
        System.out.println(BEFORE_DISCOUNT_TOTAL_PRICE_TITLE.getValue());
        System.out.println(String.format("%,d원", price) + System.lineSeparator());
    }
}
