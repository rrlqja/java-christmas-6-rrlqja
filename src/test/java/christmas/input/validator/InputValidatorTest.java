package christmas.input.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class InputValidatorTest {

    InputValidator inputValidator = new InputValidator();

    @DisplayName("주문 패턴 검증 성공 테스트")
    @ValueSource(strings = {"메뉴-1", "메뉴-1,메뉴-2,메뉴-10", "안녕하세요-10,반갑습니다-10,안녕히계세요-10"})
    @ParameterizedTest
    void orderPatternTest(String order) {
        assertDoesNotThrow(() -> inputValidator.validateOrdersPattern(order));
    }

    @DisplayName("주문 패턴 검증 실패 테스트")
    @ValueSource(strings = {"안녕하세요", "메뉴1", "메뉴1-1", "메뉴-1 , 메뉴-2 , 메뉴-10"})
    @ParameterizedTest
    void orderPatternExceptionTest(String order) {
        assertThatThrownBy(() -> inputValidator.validateOrdersPattern(order))
                .isInstanceOf(IllegalArgumentException.class);
    }
}