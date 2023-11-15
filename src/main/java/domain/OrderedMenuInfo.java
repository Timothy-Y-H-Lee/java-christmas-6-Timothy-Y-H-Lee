package domain;

import static enums.DiscountDaysMenu.WEEKDAYS_DISCOUNT_MENU;
import static enums.DiscountDaysMenu.WEEKEND_DISCOUNT_MENU;
import static enums.DiscountDaysMenuPrice.WEEKDAYS_DISCOUNT_MENU_PER_PRICE;
import static enums.DiscountDaysMenuPrice.WEEKEND_DISCOUNT_MENU_PER_PRICE;
import static enums.GiftMenuPrice.GIFT_MENU_PRICE;
import static enums.UserInterface.DISCOUNT_DETAILS_GIFT_EVENT;
import static enums.UserInterface.DISCOUNT_DETAILS_SPECIAL_DISCOUNT;
import static enums.UserInterface.DISCOUNT_DETAILS_WEEKEND_DISCOUNT;
import static enums.UserInterface.DISCOUNT_DETAILS_WEEK_DAYS_DISCOUNT;
import static enums.UserInterface.DISCOUNT_DETAILS_XMAS_DAY_DISCOUNT;
import static enums.UserInterface.ILLEGAL_CANNOT_MENU_ORDER_ONLY_DRINK;
import static enums.UserInterface.ILLEGAL_CANNOT_MENU_ORDER_OVER_20;
import static enums.UserInterface.ILLEGAL_MENU_ORDER;

import enums.GiftMenuPrice;
import enums.MenuCategory;
import enums.MenuName;
import enums.MenuPrice;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import view.InputView;

public class OrderedMenuInfo {
    private Map<String, Integer> userInputOrderedMenu = new HashMap<>(); // Map<"메뉴명", 주문수량>
    private Integer beforeDiscountTotalPrice = 0;

    private Boolean shouldGiveGiveMenu = false; // 증정메뉴의 증정 여부

    private String discountDetails = "";
    private Boolean isSetxMasDayDiscountPrice = false;
    private Boolean isSetWeekDaysyDiscountPrice = false;
    private Boolean isSetWeekEndDiscountPrice = false;
    private Boolean isSpecialDiscountDayPrice = false;
    private Integer xMasDayDiscountPrice = 0; // 크리스마스 디데이 할인
    private Integer weekDaysyDiscountPrice = 0; // 평일 할인
    private Integer weekEndDiscountPrice = 0; // 주말 할인
    private Integer specialDayDiscountPrice = 0; // 특별 할인
    private Integer giftEventDiscountPrice = 0; // 증정 이벤트 여부에 따른 금액

    private Integer totalDiscountDetailsPrice = 0; // 총 혜택 금액 + 증정 이벤트
    private Integer beforeTotalDiscountDetailsPrice = 0; // 총 혜택 금액 - 증정 이벤트
    private Integer afterTotalDiscountPrice = 0; // 할인 후 예상 결제 금액

    public Map<String, Integer> getUserInputOrderedMenu() {
        return userInputOrderedMenu;
    }

    public void userInputOrderedMenu(String orderedMenu) {
        Map<String, Integer> tempOrderedMenu = splitMenuAndQuantity(orderedMenu);
        userInputOrderedMenu = checkExistMenu(tempOrderedMenu);
        moreRequestOrderValidator();
    }

    private void moreRequestOrderValidator() {
        if (isOnlyOrderedDrink()) { // 음료만 주문 시, 주문할 수 없습니다.
            new InputView().printMessage(ILLEGAL_CANNOT_MENU_ORDER_ONLY_DRINK.getValue());
            throw new IllegalArgumentException(ILLEGAL_MENU_ORDER.getValue());
        }
        if (isMenuOrderOver20()) { // 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.(제로콜라-3, 아이스크림-1 : 4개)
            new InputView().printMessage(ILLEGAL_CANNOT_MENU_ORDER_OVER_20.getValue());
            throw new IllegalArgumentException(ILLEGAL_MENU_ORDER.getValue());
        }
    }

    // "유효하지 않은 메뉴가 포함되어 있습니다: " + orderedMenu
    private Map<String, Integer> checkExistMenu(Map<String, Integer> orderedMenuMap) {
        Set<String> validMenuNames = Set.of(MenuName.values()).stream().map(MenuName::getValue)
                .collect(Collectors.toSet());
        for (String orderedMenu : orderedMenuMap.keySet()) {
            if (!validMenuNames.contains(orderedMenu)) throw new IllegalArgumentException(ILLEGAL_MENU_ORDER.getValue());
        }
        return orderedMenuMap;
    }

    private Map<String, Integer> splitMenuAndQuantity(String orderedMenu) {
        List<String> menuList = Arrays.stream(orderedMenu.split(",")).map(String::trim).toList();
        return validateMenuList(menuList);
    }

    // validation : 메뉴명(키) 중복 체크, 수량이 숫자인지 체크
    public Map<String, Integer> validateMenuList(List<String> menuList) {
        return menuList.stream()
                .map(s -> s.split("-"))
                .peek(parts -> validateMenuFormat(parts))
                .collect(Collectors.toMap(parts -> parts[0].trim(), // 메뉴명
                        parts -> parseQuantity(parts[1]), // 수량
                        this::checkForDuplicateKeys)
                );
    }

    // "잘못된 형식의 메뉴 항목: " + Arrays.toString(parts)
    private void validateMenuFormat(String[] parts) {
        if (parts.length != 2) {
            throw new IllegalArgumentException(ILLEGAL_MENU_ORDER.getValue());
        }
    }

    // 메뉴의 개수는 1 이상의 숫자만 입력: "잘못된 형식의 수량: " + quantity
    private Integer parseQuantity(String quantity) {
        try {
            Integer parsedQuantity = Integer.parseInt(quantity.trim());
            if (parsedQuantity < 1) throw new IllegalArgumentException(ILLEGAL_MENU_ORDER.getValue());
            return parsedQuantity;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ILLEGAL_MENU_ORDER.getValue());
        }
    }

    // 중복된 메뉴명이 있음
    private Integer checkForDuplicateKeys(Integer existing, Integer replacement) {
        throw new IllegalArgumentException(ILLEGAL_MENU_ORDER.getValue());
    }

    // 주문한 메뉴의 갯수가 20개 초과인지 체크
    public Boolean isMenuOrderOver20() {
        return userInputOrderedMenu.values().stream().mapToInt(Integer::intValue).sum() > 20;
    }

    // 음료만 주문했는지 체크
    public Boolean isOnlyOrderedDrink() {
        Map<MenuCategory, List<MenuName>> categorizedMenus = groupMenuByCategory();
//        categorizedMenus.forEach((category, menuNames) -> { // 메뉴 카테고리별로 메뉴 출력
//            System.out.println(category.getValue() + ": " + menuNames);
//        });
        return categorizedMenus.entrySet().stream()
                .allMatch(entry -> entry.getKey() == MenuCategory.MENU_DRINK);
    }

    // 주문한 메뉴를 카테고리별 주문메뉴로 그룹핑
    public Map<MenuCategory, List<MenuName>> groupMenuByCategory() {
        return userInputOrderedMenu.keySet().stream()
                .map(this::getMenuNameByValue)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.groupingBy(
                        this::getCategoryFromMenuName
                ));
    }

    private Optional<MenuName> getMenuNameByValue(String value) {
        return Arrays.stream(MenuName.values())
                .filter(menuName -> menuName.getValue().equals(value))
                .findFirst();
    }

    private MenuCategory getCategoryFromMenuName(MenuName menuName) {
        String menuNameKey = menuName.getName();
        return Arrays.stream(MenuCategory.values())
                .filter(category -> menuNameKey.startsWith(category.getName()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("메뉴명에 해당하는 카테고리를 찾을 수 없습니다: " + menuNameKey));
    }

    public Integer getBeforeDiscountTotalPrice() {
        beforeDiscountTotalPrice = calcBeforeDiscountTotalPrice();
        return beforeDiscountTotalPrice;
    }
    public Integer calcBeforeDiscountTotalPrice() {
        return userInputOrderedMenu.entrySet().stream()
                .mapToInt(entry -> getPriceForMenuItem(entry.getKey()) * entry.getValue())
                .sum();
    }

    private Integer getPriceForMenuItem(String menuItem) {
        String menuNameValue = Arrays.stream(MenuName.values())
                .filter(menuName -> menuName.getValue().equals(menuItem))
                .findFirst().map(MenuName::getName)
                .orElseThrow(() -> new IllegalArgumentException("메뉴를 찾을 수 없습니다"));

        return Arrays.stream(MenuPrice.values())
                .filter(menuPrice -> menuPrice.getKey().startsWith(menuNameValue))
                .findFirst().map(MenuPrice::getValue)
                .orElseThrow(() -> new IllegalArgumentException("가격 정보를 찾을 수 없습니다"));
    }

    public Boolean shouldGiveGiftMenu() {
        checkShouldGiveGiftMenu();
        return shouldGiveGiveMenu;
    }

    private void checkShouldGiveGiftMenu() {
        this.shouldGiveGiveMenu = (getBeforeDiscountTotalPrice() >= GiftMenuPrice.GIFT_MENU_PRICE_CRITERIAL.getValue());
    }

    public String getDiscountDetails(String visitDay) {
        calcXMasDiscountPrice(visitDay);
        calcWeekdaysDiscountPrice(visitDay);
        calcWeekendDiscountPrice(visitDay);
        calcSpecialDayDiscountPrice(visitDay);
        calcGiveGiftMenu();
        return discountDetails;
    }

    // 크리스마스 디데이 할인
    private void calcXMasDiscountPrice(String visitDay) {
        isSetxMasDayDiscountPrice = VisitDate.getInstance().rangeIn25Day(visitDay);
        if (isSetxMasDayDiscountPrice) {
            Integer dayOfMonth = VisitDate.getInstance().parseDayOfMonth(visitDay);
            xMasDayDiscountPrice = (dayOfMonth - 1) * 100 + 1000;
            discountDetails += DISCOUNT_DETAILS_XMAS_DAY_DISCOUNT.getValue()
                    + String.format("%,d원", xMasDayDiscountPrice) + System.lineSeparator();
        }
    }

    // 평일 할인(일요일~목요일)
    private void calcWeekdaysDiscountPrice(String visitDay) {
        isSetWeekDaysyDiscountPrice = VisitDate.getInstance().isWeekDaysDiscountDay(visitDay);
        if (isSetWeekDaysyDiscountPrice) {
            weekDaysyDiscountPrice = findOrderedQuantityByMenuName(WEEKDAYS_DISCOUNT_MENU.getValue())
                    * WEEKDAYS_DISCOUNT_MENU_PER_PRICE.getValue();
            discountDetails += DISCOUNT_DETAILS_WEEK_DAYS_DISCOUNT.getValue()
                    + String.format("%,d원", weekDaysyDiscountPrice) + System.lineSeparator();
        }
    }

    // 주말 할인(금요일, 토요일)
    private void calcWeekendDiscountPrice(String visitDay) {
        isSetWeekEndDiscountPrice = VisitDate.getInstance().isWeekendDiscountDay(visitDay);
        if (isSetWeekEndDiscountPrice) {
            weekEndDiscountPrice = findOrderedQuantityByMenuName(WEEKEND_DISCOUNT_MENU.getValue())
                    * WEEKEND_DISCOUNT_MENU_PER_PRICE.getValue();
            discountDetails += DISCOUNT_DETAILS_WEEKEND_DISCOUNT.getValue()
                    + String.format("%,d원", weekEndDiscountPrice) + System.lineSeparator();
        }
    }

    // 특별 할인(이벤트 달력에 별-일요일 또는 25일-에 있으면 할인)
    private void calcSpecialDayDiscountPrice(String visitDay) {
        isSpecialDiscountDayPrice = VisitDate.getInstance().isSpecialDiscountDay(visitDay);
        if (isSpecialDiscountDayPrice) {
            specialDayDiscountPrice = 1000;
            discountDetails += DISCOUNT_DETAILS_SPECIAL_DISCOUNT.getValue()
                    + String.format("%,d원", specialDayDiscountPrice) + System.lineSeparator();
        }
    }

    // 증정 이벤트
    private void calcGiveGiftMenu() {
        checkShouldGiveGiftMenu();
        if (shouldGiveGiveMenu) {
            giftEventDiscountPrice = GIFT_MENU_PRICE.getValue();
            discountDetails += DISCOUNT_DETAILS_GIFT_EVENT.getValue()
                    + String.format("%,d원", GIFT_MENU_PRICE.getValue()) + System.lineSeparator();
        }
    }

    public Integer findOrderedQuantityByMenuName(MenuCategory category) {
        return userInputOrderedMenu.entrySet().stream()
                .filter(entry -> isMenuInCategory(entry.getKey(), category))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    private boolean isMenuInCategory(String menuName, MenuCategory category) {
        return Arrays.stream(MenuName.values())
                .filter(menu -> menu.getValue().equals(menuName))
                .anyMatch(menu -> menu.getName().startsWith(category.getName()));
    }
}
