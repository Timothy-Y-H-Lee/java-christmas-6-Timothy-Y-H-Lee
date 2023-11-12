package domain;

import enums.MenuCategory;
import enums.MenuName;
import enums.MenuPrice;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<String, Map<String, Integer>> menu = new HashMap<>();

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
        for (String menuCategory : MenuCategory.getKeyList()) {
            menu.put(menuCategory, initSubCategoryMenu(menuCategory));
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
            if (subMenuPriceKey.contains(subMenuKey)) return MenuPrice.getPriceByKey(subMenuPriceKey);
        }

        throw new IllegalStateException("ILLEGAL_INITIALIZATION_STATE");
    }

}
