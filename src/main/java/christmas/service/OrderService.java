package christmas.service;

import christmas.domain.Menu;
import christmas.domain.MenuQuantity;
import christmas.domain.Order;
import christmas.domain.OrderResult;
import christmas.domain.ReservationDate;
import christmas.domain.discount.DiscountStrategies;
import christmas.domain.discount.DiscountStrategyFactory;
import christmas.domain.discount.discountbenefit.DiscountBenefit;
import christmas.domain.discount.discountstrategy.DiscountStrategy;
import christmas.dto.OrderMenuDto;

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
        return order.getTotalPrice();
    }

    public List<DiscountBenefit> getDiscountBenefits(Order order, ReservationDate reservationDate) {
        return getDiscountStrategies(reservationDate).getDiscountBenefits(order);
    }

    private DiscountStrategies getDiscountStrategies(ReservationDate reservationDate) {
        List<DiscountStrategy> strategies = DiscountStrategyFactory.createStrategies(reservationDate);
        return new DiscountStrategies(strategies);
    }
}
