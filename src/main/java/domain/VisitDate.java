package domain;

import static enums.EventDateRule.EVENT_MONTH;
import static enums.EventDateRule.XMAS_DDAY_END_DATE;
import static enums.EventDateRule.XMAS_DDAY_START_DATE;

import java.time.MonthDay;
import java.time.format.DateTimeParseException;

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
            MonthDay.of(EVENT_MONTH.getValue(), Integer.valueOf(day));
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public boolean rangeIn25Day(String userInputDay) {
        try {
            Integer dayOfMonth = (MonthDay.of(EVENT_MONTH.getValue(), Integer.valueOf(userInputDay))).getDayOfMonth();
            return dayOfMonth >= XMAS_DDAY_START_DATE.getValue() && dayOfMonth <= XMAS_DDAY_END_DATE.getValue();
        }  catch (DateTimeParseException e) { // 잘못된 날짜 형식입니다.
            return false;
        }  catch (Exception e) {
            return false;
        }
    }
}
