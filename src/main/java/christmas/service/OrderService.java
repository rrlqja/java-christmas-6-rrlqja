package christmas.service;

import christmas.domain.*;
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
        return OrderResult.of(getOrderMenus(order), getTotalPrice(order), getGiftDiscountMoney(order),
                getDiscountBenefits(order, reservationDate), getTotalBenefit(order, reservationDate),
                getFinalPrice(order, reservationDate));
    }

    private List<OrderMenuDto> getOrderMenus(Order order) {
        return order.getOrderMenus();
    }

    private Integer getTotalPrice(Order order) {
        return order.getTotalPrice();
    }

    private Integer getGiftDiscountMoney(Order order) {
        return order.getGiftDiscountMoney();
    }

    private List<DiscountBenefit> getDiscountBenefits(Order order, ReservationDate reservationDate) {
        return order.getDiscountBenefits(reservationDate);
    }

    private Integer getTotalBenefit(Order order, ReservationDate reservationDate) {
        return order.getTotalDiscountMoney(reservationDate);
    }

    private Integer getFinalPrice(Order order, ReservationDate reservationDate) {
        return order.getFinalPrice(reservationDate);
    }
}
