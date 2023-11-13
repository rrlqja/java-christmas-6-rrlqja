package christmas.domain.order;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuQuantity;
import christmas.domain.order.orderresult.OrderResult;
import christmas.domain.ReservationDate;
import christmas.domain.discount.DiscountStrategies;
import christmas.domain.discount.DiscountStrategyFactory;
import christmas.domain.discount.discountbenefit.DiscountBenefit;
import christmas.domain.order.dto.OrderMenuDto;

import java.util.List;
import java.util.Map;

public class OrderService {

    public Order createOrder(Map<Menu, MenuQuantity> orders) {
        return new Order(orders);
    }

    public OrderResult getOrderResult(Order order, ReservationDate reservationDate) {
        OrderResult orderResult = createOrderResult(order, reservationDate);

        return orderResult;
    }

    private OrderResult createOrderResult(Order order, ReservationDate reservationDate) {
        return OrderResult.of(getOrderMenus(order), getTotalPrice(order), getDiscountBenefits(order, reservationDate));
    }

    private List<OrderMenuDto> getOrderMenus(Order order) {
        return order.getOrderMenus();
    }

    private Integer getTotalPrice(Order order) {
        return order.getTotalAmount();
    }

    private List<DiscountBenefit> getDiscountBenefits(Order order, ReservationDate reservationDate) {
        return getDiscountStrategies(reservationDate).getDiscountBenefits(order);
    }

    private DiscountStrategies getDiscountStrategies(ReservationDate reservationDate) {
        return DiscountStrategyFactory.createStrategies(reservationDate);
    }
}
