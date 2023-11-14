package domain;

import static enums.EventDateRule.EVENT_MONTH;
import static enums.EventDateRule.EVENT_YEAR;
import static enums.EventDateRule.XMAS_DDAY_END_DATE;
import static enums.EventDateRule.XMAS_DDAY_START_DATE;

import java.time.DayOfWeek;
import java.time.LocalDate;
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
            Integer dayOfMonth = parseDayOfMonth(userInputDay);
            return dayOfMonth >= XMAS_DDAY_START_DATE.getValue() && dayOfMonth <= XMAS_DDAY_END_DATE.getValue();
        }  catch (DateTimeParseException e) { // 잘못된 날짜 형식입니다.
            return false;
        }  catch (Exception e) {
            return false;
        }
    }

    private Integer parseDayOfMonth(String dayOfMonth) {
        return (MonthDay.of(EVENT_MONTH.getValue(), Integer.valueOf(dayOfMonth)))
                .getDayOfMonth();
    }

    // 특별 할인(23년 12월의 일요일 및 25일)에 해당되는 날인지 체크
    public boolean isSpecialDiscountDay(String userInput) {
        try {
            LocalDate localDate = LocalDate.of(EVENT_YEAR.getValue(), EVENT_MONTH.getValue(),
                    parseDayOfMonth(userInput));
            return localDate.getDayOfWeek() == DayOfWeek.SUNDAY
                    || localDate.getDayOfMonth() == XMAS_DDAY_END_DATE.getValue();
        } catch (Exception e) {
            return false;
        }
    }

}
