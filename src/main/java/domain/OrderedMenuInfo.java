package domain;

import static enums.UserInterface.ILLEGAL_CANNOT_MENU_ORDER_ONLY_DRINK;
import static enums.UserInterface.ILLEGAL_MENU_ORDER;

import enums.MenuCategory;
import enums.MenuName;
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

    public Map<String, Integer> getUserInputOrderedMenu() {
        return userInputOrderedMenu;
    }

    public void userInputOrderedMenu(String orderedMenu) {
        Map<String, Integer> tempOrderedMenu = splitMenuAndQuantity(orderedMenu);
        userInputOrderedMenu = checkExistMenu(tempOrderedMenu);
        if (isOnlyOrderedDrink()) {
            new InputView().printMessage(ILLEGAL_CANNOT_MENU_ORDER_ONLY_DRINK.getValue());
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
}
