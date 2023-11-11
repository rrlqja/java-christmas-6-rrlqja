package christmas.service;

import christmas.domain.Menu;
import christmas.domain.MenuQuantity;
import christmas.domain.Order;
import christmas.domain.ReservationDate;
import christmas.domain.OrderResult;

import java.util.Map;

public class OrderService {

    public Order createOrder(Map<Menu, MenuQuantity> orders) {
        return new Order(orders);
    }

    public OrderResult getOrderResult(Order order, ReservationDate reservationDate) {
        return order.getOrderResult(reservationDate);
    }
}
