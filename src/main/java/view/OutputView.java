package view;

import java.util.Map;

public class OutputView {
    public void printOrderMenu(Map<String, Integer> orderedMenu) {
        orderedMenu.forEach((menuName, quantity) -> {
            System.out.println(menuName + " " + quantity + "개");
        });
    }
}
