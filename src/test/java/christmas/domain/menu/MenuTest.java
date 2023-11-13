package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class MenuTest {

    @DisplayName("메뉴가 존재하지 않을때 예외 발생 테스트")
    @ValueSource(strings = {"안녕하세요", "라면", "제육볶음", "칭따오"})
    @ParameterizedTest
    void findMenuExceptionTest(String menuName) {
        assertThatThrownBy(() -> Menu.valueOfMenuName(menuName))
                .isInstanceOf(IllegalArgumentException.class);
    }

}