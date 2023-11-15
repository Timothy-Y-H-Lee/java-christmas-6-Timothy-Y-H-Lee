package domain;

import static enums.UserInterface.ILLEGAL_MENU_ORDER;

import enums.MenuName;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderedMenuInfo {
    private Map<String, Integer> userInputOrderedMenu = new HashMap<>();

    public Map<String, Integer> getUserInputOrderedMenu() {
        return userInputOrderedMenu;
    }

    public void userInputOrderedMenu(String orderedMenu) {
        Map<String, Integer> tempOrderedMenu = splitMenuAndQuantity(orderedMenu);
        userInputOrderedMenu = checkExistMenu(tempOrderedMenu);
    }

    // "유효하지 않은 메뉴가 포함되어 있습니다: " + orderedMenu
    private Map<String, Integer> checkExistMenu(Map<String, Integer> orderedMenuMap) {
        Set<String> validMenuNames = Set.of(MenuName.values()).stream().map(MenuName::getValue)
                .collect(Collectors.toSet());
        for (String orderedMenu : orderedMenuMap.keySet()) {
            if (!validMenuNames.contains(orderedMenu)) {
                throw new IllegalArgumentException(ILLEGAL_MENU_ORDER.getValue());
            }
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

}
