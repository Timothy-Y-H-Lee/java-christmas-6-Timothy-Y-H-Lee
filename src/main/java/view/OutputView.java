package view;

import static enums.GiftMenuName.GIFT_MENU_NAME;
import static enums.UserInterface.AFTER_TOTAL_DISCOUNT_PRICE_TITLE;
import static enums.UserInterface.BEFORE_DISCOUNT_TOTAL_PRICE_TITLE;
import static enums.UserInterface.DISCOUNT_DETAILS_TITLE;
import static enums.UserInterface.GIFT_MENU_TITLE;
import static enums.UserInterface.NOTTHING;
import static enums.UserInterface.ORDER_MENU_TITLE;
import static enums.UserInterface.TOTAL_DISCOUNT_PRICE_TITLE;

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

    public void printGiftMenu(Boolean hasGiftMent) {
        System.out.println(GIFT_MENU_TITLE.getValue());
        if (hasGiftMent) {
            System.out.println(GIFT_MENU_NAME.getValue() + " 1개" + System.lineSeparator());
        }
        if (!hasGiftMent) {
            System.out.println(NOTTHING.getValue() + System.lineSeparator());
        }
    }

    public void printDiscountDetails(String discountDetails) {
        System.out.println(DISCOUNT_DETAILS_TITLE.getValue());
        if (discountDetails.isEmpty()) {
            System.out.println(NOTTHING.getValue() + System.lineSeparator());
        }
        if (!discountDetails.isEmpty()) {
            System.out.println(discountDetails + System.lineSeparator());
        }
    }

    public void printTotalDiscountDetails(Integer discountPrice) {
        System.out.println(TOTAL_DISCOUNT_PRICE_TITLE.getValue());
        if (discountPrice <= 0) {
            System.out.println(NOTTHING.getValue() + System.lineSeparator());
        }
        if (discountPrice > 0) {
            System.out.println(String.format("-%,d원", discountPrice) + System.lineSeparator());
        }
    }

    public void printAfterTotalDiscountPrice(Integer discountPrice) {
        System.out.println(AFTER_TOTAL_DISCOUNT_PRICE_TITLE.getValue());
        if (discountPrice <= 0) {
            System.out.println(NOTTHING.getValue() + System.lineSeparator());
        }
        if (discountPrice > 0) {
            System.out.println(String.format("%,d원", discountPrice) + System.lineSeparator());
        }
    }
}
