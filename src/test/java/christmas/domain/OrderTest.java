package christmas.domain;

import christmas.dto.OrderMenuDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    void getTotalPriceTest() {
        Order order = getDefaultOrder();

        Integer actualTotalPrice = order.getTotalPrice();

        Integer expectTotalPrice = 0;
        for (Menu menu : orders.keySet()) {
            expectTotalPrice += menu.getMenuPrice() * orders.get(menu).getMenuQuantity();
        }

        assertThat(actualTotalPrice).isEqualTo(expectTotalPrice);
    }

    private Order getDefaultOrder() {
        MenuQuantity menuQuantity = new MenuQuantity(1);
        orders.put(Menu.MUSHROOM_SOUP, menuQuantity);
        orders.put(Menu.T_BONE_STEAK, menuQuantity);
        orders.put(Menu.ZERO_COLA, menuQuantity);
        orders.put(Menu.CHOCOLATE_CAKE, menuQuantity);
        return new Order(orders);
    }
}