package christmas.domain;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuCategory;
import christmas.domain.menu.MenuQuantity;
import christmas.domain.order.Order;
import christmas.domain.order.dto.OrderMenuDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


class OrderTest {

    Map<Menu, MenuQuantity> orders = new HashMap<>();

    @BeforeEach
    void beforeEach() {
        orders.clear();
    }

    @DisplayName("주문한 메뉴 출력 테스트")
    @Test
    void getOrderMenusTest() {
        Order order = getDefaultOrder();

        List<OrderMenuDto> orderMenus = order.getOrderMenus();
        assertThat(orderMenus.size())
                .isEqualTo(orders.size());
    }

    @DisplayName("총 구매 금액 테스트")
    @Test
    void getTotalAmountTest() {
        Order order = getDefaultOrder();

        Integer actualTotalPrice = order.getTotalAmount();

        Integer expectTotalPrice = 0;
        for (Menu menu : orders.keySet()) {
            expectTotalPrice += menu.getMenuPrice() * orders.get(menu).getMenuQuantity();
        }

        assertThat(actualTotalPrice).isEqualTo(expectTotalPrice);
    }

    @DisplayName("디데이 할인 테스트")
    @Test
    void getDDayDiscountAmountTest() {
        ReservationDate reservationDate = new ReservationDate(1);
        Order order = getDefaultOrder();

        Integer dDayDiscountAmount = order.getDDayDiscountAmount(reservationDate);

        assertThat(dDayDiscountAmount).isEqualTo(1000);
    }

    @DisplayName("디데이 할인 없음 테스트")
    @Test
    void getNoDDayDiscountAmountTest() {
        ReservationDate reservationDate = new ReservationDate(30);
        Order order = getDefaultOrder();

        Integer dDayDiscountAmount = order.getDDayDiscountAmount(reservationDate);

        assertThat(dDayDiscountAmount).isEqualTo(0);
    }

    @DisplayName("메뉴 카테고리 별 할인 테스트")
    @Test
    void getMenuCategoryDiscountAmountTest() {
        Order order = getDefaultOrder();

        assertThat(order.getWeekDiscountAmount(MenuCategory.MAIN))
                .isEqualTo(2023);
    }

    @DisplayName("특별 할인 테스트")
    @ValueSource(ints = {3, 10, 17, 24, 31})
    @ParameterizedTest
    void getStarDayDiscountAmountTest(Integer date) {
        ReservationDate reservationDate = new ReservationDate(date);
        Order order = getDefaultOrder();

        assertThat(order.getStarDayDiscountAmount(reservationDate))
                .isEqualTo(1000);
    }

    @DisplayName("특별 할인 없음 테스트")
    @ValueSource(ints = {1, 2, 20, 21, 30})
    @ParameterizedTest
    void getNoStarDayDiscountAmountTest(Integer date) {
        ReservationDate reservationDate = new ReservationDate(date);
        Order order = getDefaultOrder();

        assertThat(order.getStarDayDiscountAmount(reservationDate))
                .isEqualTo(0);
    }

    @DisplayName("증정 메뉴 이벤트 테스트")
    @Test
    void getGiftDiscountAmountTest() {
        Order order = getMaximumOrder();

        assertThat(order.getGiftDiscountAmount())
                .isEqualTo(25000);
    }

    @DisplayName("증정 메뉴 이벤트 없음 테스트")
    @Test
    void getNoGiftDiscountAmountTest() {
        Order order = getDefaultOrder();

        assertThat(order.getGiftDiscountAmount())
                .isEqualTo(0);
    }
    private Order getDefaultOrder() {
        MenuQuantity menuQuantity = new MenuQuantity(1);
        orders.put(Menu.MUSHROOM_SOUP, menuQuantity);
        orders.put(Menu.T_BONE_STEAK, menuQuantity);
        orders.put(Menu.ZERO_COLA, menuQuantity);
        orders.put(Menu.CHOCOLATE_CAKE, menuQuantity);
        return new Order(orders);
    }

    private Order getMaximumOrder() {
        MenuQuantity menuQuantity = new MenuQuantity(20);
        orders.put(Menu.T_BONE_STEAK, menuQuantity);

        return new Order(orders);
    }
}