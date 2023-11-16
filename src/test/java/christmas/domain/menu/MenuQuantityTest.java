package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuQuantityTest {

    @DisplayName("메뉴 갯수 최소값 검증 테스트")
    @ValueSource(ints = {0, -1})
    @ParameterizedTest
    void menuQuantityExceptionTest(int quantity) {
        assertThatThrownBy(() -> new MenuQuantity(quantity))
                .isInstanceOf(IllegalArgumentException.class);
    }

}