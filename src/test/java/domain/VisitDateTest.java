package domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class VisitDateTest {
    private VisitDate visitDate;
    @BeforeEach
    void setUp() {
        visitDate = VisitDate.getInstance();
    }

    @DisplayName("전달받은 날짜가 휴효한 숫자인지 확인")
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

}