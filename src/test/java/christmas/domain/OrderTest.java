package christmas.domain;

import christmas.dto.OrderMenuDto;
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

    private Map<Menu, MenuQuantity> orders;
    private Order order;

    @BeforeEach
    void beforeEach() {
        orders = new HashMap<>();
        MenuQuantity menuQuantity = new MenuQuantity(1);
        orders.put(Menu.MUSHROOM_SOUP, menuQuantity);
        orders.put(Menu.T_BONE_STEAK, menuQuantity);
        orders.put(Menu.CHOCOLATE_CAKE, menuQuantity);
        orders.put(Menu.ZERO_COLA, menuQuantity);

        order = new Order(orders);
    }

    @DisplayName("주문 메뉴 출력 테스트")
    @Test
    void getOrderMenusTest() {
        List<OrderMenuDto> orderMenus = order.getOrderMenus();

        assertThat(orderMenus.stream().anyMatch(orderMenuDto -> orderMenuDto.getMenuName()
                .equals(Menu.MUSHROOM_SOUP.getMenuName())))
                .isTrue();

        assertThat(orderMenus.stream().anyMatch(orderMenuDto -> orderMenuDto.getMenuName()
                .equals(Menu.T_BONE_STEAK.getMenuName())))
                .isTrue();

        assertThat(orderMenus.stream().anyMatch(orderMenuDto -> orderMenuDto.getMenuName()
                .equals(Menu.ZERO_COLA.getMenuName())))
                .isTrue();
    }

    @DisplayName("총 주문 가격 테스트")
    @Test
    void getTotalPriceTest() {
        Integer totalPrice = order.getTotalPrice();

        assertThat(totalPrice).isEqualTo(orders.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getMenuPrice() * entry.getValue().getMenuQuantity())
                .sum()
        );
    }

    @DisplayName("예약 날짜에 따른 디데이 할인 테스트")
    @ValueSource(ints = {1, 10, 20})
    @ParameterizedTest
    void getDateDiscountTest(Integer date) {
        ReservationDate reservationDate = new ReservationDate(date);
        Integer dateDiscountMoney = order.getDateDiscountMoney(reservationDate);

        int defaultDateDiscountMoney = 1000;
        int expectDiscountMoney = defaultDateDiscountMoney;
        for (int i = 1; i < date; i++) {
            expectDiscountMoney += 100;
        }
        assertThat(dateDiscountMoney).isEqualTo(expectDiscountMoney);
    }

    @DisplayName("주말 할인 테스트")
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    @ParameterizedTest
    void weekendDiscountTest(Integer date) {
        ReservationDate weekendReservationDate = new ReservationDate(date);
        Integer weekendDiscountMoney = order.getWeekendDiscountMoney(weekendReservationDate);

        assertThat(weekendDiscountMoney).isEqualTo(2023);
    }

    @DisplayName("평일 할인 테스트")
    @ValueSource(ints = {4, 5, 6, 10, 11, 12, 24, 25, 26})
    @ParameterizedTest
    void weekdayDiscountTest(Integer date) {
        ReservationDate weekdayReservationDate = new ReservationDate(date);
        Integer weekdayDiscountMoney = order.getWeekdayDiscountMoney(weekdayReservationDate);

        assertThat(weekdayDiscountMoney).isEqualTo(2023);
    }

    @DisplayName("특별 할인 테스트")
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    @ParameterizedTest
    void starDayDiscountTest(Integer date) {
        ReservationDate startDayReservationDate = new ReservationDate(date);
        Integer starDayDiscount = order.getStarDayDiscount(startDayReservationDate);

        assertThat(starDayDiscount).isEqualTo(1000);
    }

    @DisplayName("증정 할인 성공 테스트")
    @Test
    void getGiftDiscountTest() {
        Map<Menu, MenuQuantity> giftOrders = new HashMap<>();
        MenuQuantity menuQuantity = new MenuQuantity(15);
        giftOrders.put(Menu.T_BONE_STEAK, menuQuantity);

        Order giftOrder = new Order(giftOrders);
        Integer giftDiscountMoney = giftOrder.getGiftDiscountMoney();

        assertThat(giftDiscountMoney).isEqualTo(25000);
    }

    @DisplayName("증정 할인 못받음 테스트")
    @Test
    void getNoGiftDiscountTest() {
        Integer giftDiscountMoney = order.getGiftDiscountMoney();
        assertThat(giftDiscountMoney).isEqualTo(0);
    }
}