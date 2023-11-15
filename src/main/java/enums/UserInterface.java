package enums;

import static enums.EventDateRule.EVENT_MONTH;

public enum UserInterface {
    ERROR_PREFIX("[ERROR] "),
    ILLEGAL_INITIALIZATION_STATE(ERROR_PREFIX.getValue() + "초기화가 되지 않았습니다. 관리자에게 문의 바랍니다."),
    REQUEST_INPUT_MENU_ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    ILLEGAL_MENU_ORDER(ERROR_PREFIX.getValue() + "유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ILLEGAL_CANNOT_MENU_ORDER_ONLY_DRINK(ERROR_PREFIX.getValue() + "음료만 주문 시, 주문할 수 없습니다."),
    ILLEGAL_CANNOT_MENU_ORDER_OVER_20(ERROR_PREFIX.getValue() + "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다."),
    NOTTHING("없음"),
    DECEMBER("12"),
    REQUEST_INPUT_VISIT_DAY(EVENT_MONTH.getValue() + "월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    INVALIDATE_INPUT_VISIT_DAY(ERROR_PREFIX.getValue() + "유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    WELLCOME_MESSAGE("안녕하세요! 우테코 식당 " + EVENT_MONTH.getValue() + "월 이벤트 플래너입니다."),
    PREVIEW_EVENT_MESSAGE("12월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!" + System.lineSeparator()),
    ORDER_MENU_TITLE("<주문 메뉴>"),
    BEFORE_DISCOUNT_TOTAL_PRICE_TITLE("<할인 전 총주문 금액>"),
    GIFT_MENU_TITLE("<증정 메뉴>")
    ;

    private String value;

    UserInterface(String value) {
        this.value = value;
    }

    public String getName() {
        return name();
    }
    public String getValue() {
        return value;
    }
}
