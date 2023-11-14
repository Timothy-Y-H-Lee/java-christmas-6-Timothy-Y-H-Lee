package domain;

import java.time.MonthDay;

public class VisitDate {
    private VisitDate() {
    }

    private static class VisitDateHoler {
        private final static VisitDate VISIT_DATE = new VisitDate();
    }

    public static VisitDate getInstance() {
        return VisitDateHoler.VISIT_DATE;
    }

    public boolean validateDate(String day) {
        try {
            MonthDay.of(12, Integer.valueOf(day));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
