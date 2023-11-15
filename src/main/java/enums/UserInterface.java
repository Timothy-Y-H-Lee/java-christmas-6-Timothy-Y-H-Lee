package enums;

public enum UserInterface {
    ERROR_PREFIX("[ERROR] "),
    ILLEGAL_INITIALIZATION_STATE(ERROR_PREFIX.getValue() + "초기화가 되지 않았습니다. 관리자에게 문의 바랍니다."),
    REQUEST_INPUT_MENU_ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    ILLEGAL_MENU_ORDER(ERROR_PREFIX.getValue() + "유효하지 않은 주문입니다. 다시 입력해 주세요."),
    NOT_THING("없음"),
    DECEMBER("12"),
    REQUEST_INPUT_VISIT_DAY(DECEMBER.getValue() + "월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    INVALIDATE_INPUT_VISIT_DAY(ERROR_PREFIX.getValue() + "유효하지 않은 날짜입니다. 다시 입력해 주세요.")
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
