package controller;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static enums.UserInterface.INVALIDATE_INPUT_VISIT_DAY;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class XMasMainControllerTest extends NsTest {
    @DisplayName("InputView로 방문 일자를 입력받기")
    @Test
    void InputView로_방문_일자를_입력받기() {
        assertSimpleTest(() -> {
            run("aaa", "3");
            assertThat(INVALIDATE_INPUT_VISIT_DAY.getValue());
        });
    }

    @Override
    protected void runMain() {

    }
}