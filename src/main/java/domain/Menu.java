package domain;

import static enums.UserInterface.ILLEGAL_INITIALIZATION_STATE;
import static enums.UserInterface.ILLEGAL_MENU_ORDER;

import enums.MenuCategory;
import enums.MenuName;
import enums.MenuPrice;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<String, Map<String, Integer>> menuMap = new HashMap<>();

    private Menu() {
        initMenu();
    }

    private static class MenuHolder {
        private final static Menu MENU = new Menu();
    }

    public static Menu getInstance() {
        return MenuHolder.MENU;
    }

    private void initMenu() {
        this.initMenuCategory();
    }

    private void initMenuCategory() {
        for (String menuCategory : MenuCategory.getStrKeyList()) {
            menuMap.put(menuCategory, initSubCategoryMenu(menuCategory));
        }

    }

    private Map<String, Integer> initSubCategoryMenu(String menuCategory) {
        Map<String, Integer> subMenu = new HashMap<>();
        Integer subMenuPrice = 0;
        for (String subMenuKey : MenuName.getKeyList()) {
            if (subMenuKey.contains(menuCategory)) {
                subMenuPrice = initSubMenuPrice(subMenuKey);
                subMenu.put(subMenuKey, subMenuPrice);
            }
        }
        return subMenu;
    }

    private Integer initSubMenuPrice(String subMenuKey) {
        for (String subMenuPriceKey : MenuPrice.getKeyList()) {
            if (subMenuPriceKey.contains(subMenuKey)) return MenuPrice.findPriceByKey(subMenuPriceKey);
        }

        throw new IllegalStateException(ILLEGAL_INITIALIZATION_STATE.getValue());
    }

    public HashMap<String, Integer> findMenuKeyAndPriceByMenuName(String menuName) throws IllegalArgumentException {
        if (this.menuMap == null) throw new IllegalStateException(ILLEGAL_INITIALIZATION_STATE.getValue());
        return new HashMap<>(Map.of(
                findMenuKeyByMenuName(menuName), findMenuPriceByMenuName(menuName))
        );
    }

    private Integer findMenuPriceByMenuName(String menuName) throws IllegalArgumentException {
        String strMenuKey = findMenuKeyByMenuName(menuName);
        for (MenuPrice menuPrice : MenuPrice.values()) {
            if (menuPrice.getKey().startsWith(strMenuKey)) return menuPrice.getValue();
        }
        throw new IllegalArgumentException(ILLEGAL_MENU_ORDER.getValue());
    }

    public String findMenuKeyByMenuName(String menuName) {
        for (MenuName subMenu : MenuName.values()) {
            if (subMenu.getValue().equals(menuName)) return subMenu.getName();
        }
        throw new IllegalArgumentException(ILLEGAL_MENU_ORDER.getValue());
    }

}
