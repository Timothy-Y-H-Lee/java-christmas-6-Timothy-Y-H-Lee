package service;

import domain.DiscountInfo;
import domain.OrderedMenuInfo;
import java.util.Map;

public class XMasService {
    DiscountInfo discountInfo = new DiscountInfo();
    OrderedMenuInfo orderedMenuInfo = new OrderedMenuInfo();

    private XMasService() {
    }

    private static class XMasServiceHolder {
        private final static XMasService X_MAS_SERVICE = new XMasService();
    }

    public static XMasService getInstance() {
        return XMasServiceHolder.X_MAS_SERVICE;
    }

    public void setVisitDay(String visitDay) {
        discountInfo.setVisitDay(visitDay);
    }

    public void xMasDayDiscount(String visitDay) {
        discountInfo.xMasDayDiscount(visitDay);
    }
    public void weekdaysDiscount(String visitDay) {
        discountInfo.weekdaysDiscount(visitDay);
    }
    public void weekendDiscount(String visitDay) {
        discountInfo.weekendDiscount(visitDay);
    }
    public void specialDiscount(String visitDay) {
        discountInfo.specialDiscount(visitDay);
    }

    public void orderedMenu(String userInputOrderedMenu) {
        orderedMenuInfo.userInputOrderedMenu(userInputOrderedMenu);
    }

    public Map<String, Integer> getUserInputOrderedMenu() {
        return orderedMenuInfo.getUserInputOrderedMenu();
    }

    public Integer getBeforeDiscountTotalPrice() {
        return orderedMenuInfo.getBeforeDiscountTotalPrice();
    }

    public Boolean shouldGiveGiftMenu() {
        return orderedMenuInfo.shouldGiveGiftMenu();
    }

    public String getDiscountDetails(String visitDay) {
        return orderedMenuInfo.getDiscountDetails(visitDay);
    }

    public Integer getTotalDiscountDetailsPrice() {
        return orderedMenuInfo.getTotalDiscountDetailsPrice();
    }
    public Integer getAfterTotalDiscountPrice() {
        return orderedMenuInfo.getAfterTotalDiscountPrice();
    }
}
