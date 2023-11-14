package domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {
    private VisitDate visitDate;
    @BeforeEach
    void setUp() {
        visitDate = VisitDate.getInstance();
    }

    @DisplayName("전달받은 날짜가 유효한 숫자인지 확인")
    @ParameterizedTest(name = "{index}, {displayName} userInput: 맞는 값-{0}, 틀린 값-{1}, 틀린 값-{2}")
    @CsvSource({"1, a, 0, 32"})
    void validateDate(String numberformat, String alphabet, String zero, String notDate) {
        assertAll(
                () -> assertTrue(() -> VisitDate.getInstance().validateDate(numberformat)),
                () -> assertFalse(VisitDate.getInstance().validateDate(alphabet)),
                () -> assertFalse(VisitDate.getInstance().validateDate(zero)),
                () -> assertFalse(VisitDate.getInstance().validateDate(notDate))
        );
    }
    @DisplayName("전달받은 날짜가 1일에서 25일 사이인지 체크")
    @ParameterizedTest(name = "{index}. {displayName} userInput: {0}")
    @ValueSource(strings = {"1", "10", "25"})
    void rangeIn25Day(String userInputDay) {
        assertTrue(VisitDate.getInstance().rangeIn25Day(userInputDay));
    }
    @DisplayName("전달받은 날짜가 1일에서 25일 사이가 아닌지 체크")
    @ParameterizedTest(name = "{index}. {displayName} userInput: {0}")
    @ValueSource(strings = {"0", "26", "aaa"})
    void outOfRangeIn25Day(String userInputDay) {
        assertFalse(VisitDate.getInstance().rangeIn25Day(userInputDay));
    }

    @DisplayName("특별할인에 해당되는지 확인")
    @ParameterizedTest(name = "{index}. {displayName} userInput: {0}")
    @ValueSource(strings = {"3", "17", "25"})
    void specialDiscount(String userInput) {
        assertTrue(VisitDate.getInstance().isSpecialDiscountDay(userInput));
    }

    @DisplayName("특별할인에 해당이 안되는지 확인")
    @ParameterizedTest(name = "{index}. {displayName} userInput: {0}")
    @ValueSource(strings = {"7", "12", "26"})
    void notSpecialDiscount(String userInput) {
        assertFalse(VisitDate.getInstance().isSpecialDiscountDay(userInput));
    }

}