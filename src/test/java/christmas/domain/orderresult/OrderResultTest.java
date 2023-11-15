package christmas.domain.orderresult;

import christmas.domain.ReservationDate;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuQuantity;
import christmas.domain.order.Order;
import christmas.domain.order.OrderService;
import christmas.domain.order.dto.OrderMenuDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

class OrderResultTest {

    Map<Menu, MenuQuantity> orders;
    OrderService orderService;

    private OrderResult createOrderResult() {
        MenuQuantity defaultMenuQuantity = new MenuQuantity(1);
        orders = new HashMap<>();
        orders.put(Menu.MUSHROOM_SOUP, defaultMenuQuantity);
        orders.put(Menu.T_BONE_STEAK, defaultMenuQuantity);
        orders.put(Menu.CHOCOLATE_CAKE, defaultMenuQuantity);
        orders.put(Menu.ZERO_COLA, defaultMenuQuantity);

        orderService = new OrderService();

        Order order = new Order(orders);
        ReservationDate weekendReservationDate = new ReservationDate(1);

        return orderService.createOrderResult(order, weekendReservationDate);
    }

    private OrderResult createBenefitOrderResult(Integer menuQuantityInput, Integer reservationDateInput) {
        MenuQuantity defaultMenuQuantity = new MenuQuantity(menuQuantityInput);
        orders = new HashMap<>();
        orders.put(Menu.TAPAS, defaultMenuQuantity);
        orders.put(Menu.ZERO_COLA, defaultMenuQuantity);

        orderService = new OrderService();

        Order order = new Order(orders);
        ReservationDate weekendReservationDate = new ReservationDate(reservationDateInput);

        return orderService.createOrderResult(order, weekendReservationDate);
    }

    @DisplayName("사용자 주문 결과 메뉴 내역 테스트")
    @Test
    void getOrderMenuTest() {
        OrderResult orderResult = createOrderResult();
        List<OrderMenuDto> orderMenus = orderResult.getOrderMenus();

        Set<String> distinctOrderMenu = orderMenus.stream()
                .map(orderMenuDto -> orderMenuDto.toString().split(" ")[0].trim())
                .collect(Collectors.toSet());

        Set<String> distinctOrders = orders.keySet()
                .stream()
                .map(Menu::getMenuName)
                .collect(Collectors.toSet());

        for (String orderMenu : distinctOrderMenu) {
            assertThat(distinctOrders).contains(orderMenu);
        }
    }

    @DisplayName("주문 총 금액 테스트")
    @Test
    void getTotalAmountTest() {
        OrderResult orderResult = createOrderResult();
        Integer totalAmount = orderResult.getTotalAmount();

        int expectTotalAmount = orders.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getMenuPrice() * entry.getValue().getMenuQuantity())
                .sum();

        assertThat(totalAmount).isEqualTo(expectTotalAmount);
    }

    @DisplayName("주문 혜택 내역 검증 테스트")
    @Test
    void getBenefitsTest() {
        OrderResult orderResult = createOrderResult();

        List<String> benefits = orderResult.getDiscountBenefits();

        List<String> expectBenefits = Arrays.asList("없음", "크리스마스 디데이 할인", "평일 할인", "주말 할인", "특별 할인");

        assertThat(benefits).anySatisfy(benefit ->
                assertThat(expectBenefits).contains(benefit.split(":")[0]));
    }

    @DisplayName("주문 금액이 만원 이하일때 혜택 없음 테스트")
    @Test
    void totalBenefitTest() {
        OrderResult orderResult = createBenefitOrderResult(1, 1);

        Integer totalBenefit = orderResult.getTotalBenefit();

        assertThat(totalBenefit).isEqualTo(0);
    }

    @DisplayName("증정메뉴 없을때 최종 금액 테스트")
    @Test
    void finalAmountTest() {
        OrderResult benefitOrderResult = createBenefitOrderResult(3, 1);

        Integer totalAmount = benefitOrderResult.getTotalAmount();
        Integer totalBenefit = benefitOrderResult.getTotalBenefit();

        Integer finalAmount = benefitOrderResult.getFinalAmount();

        assertThat(finalAmount).isEqualTo(totalAmount - totalBenefit);
    }

    @DisplayName("증정메뉴 있을때 최종 금액 테스트")
    @Test
    void finalAmountWithGiftMenuTest() {
        OrderResult benefitOrderResult = createBenefitOrderResult(100, 1);

        Integer totalAmount = benefitOrderResult.getTotalAmount();
        Integer totalBenefit = benefitOrderResult.getTotalBenefit();
        Integer giftMenuAmount = 25000;

        Integer finalAmount = benefitOrderResult.getFinalAmount();

        assertThat(finalAmount).isEqualTo(totalAmount - totalBenefit + giftMenuAmount);
    }

    @DisplayName("산타 배지 테스트")
    @Test
    void badgeTest() {
        OrderResult santanOrderResult = createBenefitOrderResult(100, 1);

        Badge badge = santanOrderResult.getBadge();

        assertThat(badge).isEqualTo(Badge.SANTA);
    }

    @DisplayName("배지 없음 테스트")
    @Test
    void noBadgeTest() {
        OrderResult santanOrderResult = createOrderResult();

        Badge badge = santanOrderResult.getBadge();

        assertThat(badge).isEqualTo(Badge.NONE);
    }
}