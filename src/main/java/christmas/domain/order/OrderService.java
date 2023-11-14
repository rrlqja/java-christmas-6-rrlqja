package christmas.domain.order;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuQuantity;
import christmas.domain.orderresult.OrderResult;
import christmas.domain.ReservationDate;
import christmas.domain.order.discount.DiscountStrategies;
import christmas.domain.order.discount.DiscountStrategyFactory;
import christmas.domain.order.discount.discountbenefit.DiscountBenefit;
import christmas.domain.order.dto.OrderMenuDto;

import java.util.List;
import java.util.Map;

public class OrderService {

    public Order createOrder(Map<Menu, MenuQuantity> orders) {
        return new Order(orders);
    }

    public OrderResult getOrderResult(Order order, ReservationDate reservationDate) {
        return createOrderResult(order, reservationDate);
    }

    private OrderResult createOrderResult(Order order, ReservationDate reservationDate) {
        return new OrderResult(getOrderMenus(order), getTotalAmount(order), getDiscountBenefits(order, reservationDate));
    }

    private List<OrderMenuDto> getOrderMenus(Order order) {
        return order.getOrderMenus();
    }

    private Integer getTotalAmount(Order order) {
        return order.getTotalAmount();
    }

    private List<DiscountBenefit> getDiscountBenefits(Order order, ReservationDate reservationDate) {
        return getDiscountStrategies(reservationDate).getDiscountBenefits(order);
    }

    private DiscountStrategies getDiscountStrategies(ReservationDate reservationDate) {
        return DiscountStrategyFactory.createStrategies(reservationDate);
    }
}
