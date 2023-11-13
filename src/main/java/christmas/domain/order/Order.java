package christmas.domain.order;

import christmas.domain.ReservationDate;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuCategory;
import christmas.domain.menu.MenuQuantity;
import christmas.domain.order.dto.OrderMenuDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    private final Map<Menu, MenuQuantity> orders;

    public Order(Map<Menu, MenuQuantity> orders) {
        this.orders = new HashMap<>(orders);
    }

    public List<OrderMenuDto> getOrderMenus() {
        return orders.entrySet()
                .stream()
                .map(entry -> new OrderMenuDto(entry.getKey().getMenuName(), entry.getValue().getMenuQuantity()))
                .toList();
    }

    public Integer getTotalPrice() {
        return orders.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getMenuPrice() * entry.getValue().getMenuQuantity())
                .sum();
    }

    public Integer getDateDiscountMoney(ReservationDate reservationDate) {
        if (!reservationDate.isBeforeChristmas()) {
            return 0;
        }
        int dateDiscountMoney = 1000;
        for (int i = 1; i < reservationDate.getReservationDate(); i++) {
            dateDiscountMoney += 100;
        }
        return dateDiscountMoney;
    }

    public Integer getWeekDiscountMoney(MenuCategory menuCategory) {
        return orders.entrySet()
                .stream()
                .filter(entry -> entry.getKey().getMenuCategory().equals(menuCategory))
                .mapToInt(entry -> entry.getValue().getMenuQuantity() * 2023)
                .sum();
    }

    public Integer getStarDayDiscount(ReservationDate reservationDate) {
        if (!reservationDate.isStarDay()) {
            return 0;
        }

        return 1000;
    }

    public Integer getGiftDiscountMoney() {
        Integer totalPrice = getTotalPrice();
        if (totalPrice < 120000) {
            return 0;
        }

        return 25000;
    }
}
