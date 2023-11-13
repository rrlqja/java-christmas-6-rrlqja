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
    private static final int NO_DISCOUNT_BENEFIT = 0;
    private static final int DEFAULT_D_DAY_DISCOUNT_AMOUNT = 1000;
    private static final int DISCOUNT_INCREMENT = 100;
    private static final int FIRST_DAY = 1;
    private static final int WEEK_DISCOUNT_AMOUNT = 2023;
    private static final int STAR_DAY_DISCOUNT_AMOUNT = 1000;
    private static final int GIFT_DISCOUNT_AMOUNT = 25000;
    private static final int MINIMUM_FOR_GIFT = 120000;
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

    public Integer getTotalAmount() {
        return orders.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getMenuPrice() * entry.getValue().getMenuQuantity())
                .sum();
    }

    public Integer getDDayDiscountAmount(ReservationDate reservationDate) {
        if (!reservationDate.isBeforeChristmas()) {
            return NO_DISCOUNT_BENEFIT;
        }
        int DDayDiscountAmount = DEFAULT_D_DAY_DISCOUNT_AMOUNT;
        for (int i = FIRST_DAY; i < reservationDate.getReservationDate(); i++) {
            DDayDiscountAmount += DISCOUNT_INCREMENT;
        }
        return DDayDiscountAmount;
    }

    public Integer getWeekDiscountAmount(MenuCategory menuCategory) {
        return orders.entrySet()
                .stream()
                .filter(entry -> entry.getKey().getMenuCategory().equals(menuCategory))
                .mapToInt(entry -> entry.getValue().getMenuQuantity() * WEEK_DISCOUNT_AMOUNT)
                .sum();
    }

    public Integer getStarDayDiscountAmount(ReservationDate reservationDate) {
        if (!reservationDate.isStarDay()) {
            return NO_DISCOUNT_BENEFIT;
        }

        return STAR_DAY_DISCOUNT_AMOUNT;
    }

    public Integer getGiftDiscountAmount() {
        Integer totalPrice = getTotalAmount();
        if (totalPrice < MINIMUM_FOR_GIFT) {
            return NO_DISCOUNT_BENEFIT;
        }

        return GIFT_DISCOUNT_AMOUNT;
    }
}
