package christmas.service;

import christmas.domain.*;
import christmas.dto.OrderMenuDto;

import java.util.List;
import java.util.Map;

public class OrderService {

    public Order createOrder(Map<Menu, MenuQuantity> orders) {
        return new Order(orders);
    }

    public List<OrderMenuDto> getOrderMenu(Order order) {
        return order.getOrderMenus();
    }

    public Integer getTotalPrice(Order order) {
        return order.getTotalPrice();
    }

    public List<DiscountBenefit> getBenefits(Order order, ReservationDate reservationDate) {
        return order.getDiscountBenefits(reservationDate);
    }
}
