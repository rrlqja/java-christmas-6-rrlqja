package christmas.domain;

import christmas.dto.OrderMenuDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

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

    public List<DiscountBenefit> getDiscountBenefits(ReservationDate reservationDate) {
        List<DiscountBenefit> discountBenefits = new ArrayList<>();

        addBenefit(discountBenefits, "크리스마스 디데이 할인", () -> getDateDiscountMoney(reservationDate));
        addBenefit(discountBenefits, "주말 할인", () -> getWeekendDiscountMoney(reservationDate));
        addBenefit(discountBenefits, "평일 할인", () -> getWeekdayDiscountMoney(reservationDate));
        addBenefit(discountBenefits, "특별 할인", () -> getStarDayDiscount(reservationDate));
        addBenefit(discountBenefits, "증정 이벤트", () -> getGiftDiscountMoney());

        return discountBenefits;
    }

    public Integer getDateDiscountMoney(ReservationDate reservationDate) {
        int dateDiscountMoney = 1000;
        for (int i = 1; i < reservationDate.getReservationDate(); i++) {
            dateDiscountMoney += 100;
        }
        return dateDiscountMoney;
    }

    public Integer getWeekdayDiscountMoney(ReservationDate reservationDate) {
        Integer weekdayDiscountMoney = calculatorWeekDiscountMoney(
                ReservationDate::isWeekday, reservationDate, MenuCategory.DESSERT);

        return weekdayDiscountMoney;
    }

    public Integer getWeekendDiscountMoney(ReservationDate reservationDate) {
        Integer weekendDiscountMoney = calculatorWeekDiscountMoney(
                ReservationDate::isWeekend, reservationDate, MenuCategory.MAIN);

        return weekendDiscountMoney;
    }

    public Integer getStarDayDiscount(ReservationDate reservationDate) {
        if (!reservationDate.isStarDay()) {
            return 0;
        }

        return 1000;
    }

    public Integer getGiftDiscountMoney() {
        Integer totalPrice = getTotalPrice();
        if (totalPrice >= 120000) {
            return 25000;
        }

        return 0;
    }

    public Menu getGiftMenu() {
        return Menu.CHAMPAGNE;
    }

    public Integer getTotalDiscountMoney(ReservationDate reservationDate) {
        Integer totalDiscountMoney = 0;

        totalDiscountMoney += calculateTotalPriceMoney(reservationDate);

        return totalDiscountMoney;
    }

    private Integer calculatorWeekDiscountMoney(Predicate<ReservationDate> dateCondition,
                                                ReservationDate reservationDate, MenuCategory menuCategory) {
        if (!dateCondition.test(reservationDate)) {
            return 0;
        }

        Integer discountMoney = 0;
        for (Menu menu : orders.keySet()) {
            if (menu.getMenuCategory().equals(menuCategory)) {
                discountMoney += orders.get(menu).getMenuQuantity() * 2023;
            }
        }

        return discountMoney;
    }

    private void addBenefit(List<DiscountBenefit> discountBenefits, String description,
                            Supplier<Integer> discountSupplier) {
        Integer amount = discountSupplier.get();
        if (amount > 0) {
            discountBenefits.add(new DiscountBenefit(description, amount));
        }
    }

    private int calculateTotalPriceMoney(ReservationDate reservationDate) {
        return getDateDiscountMoney(reservationDate) + getWeekdayDiscountMoney(reservationDate)
                + getWeekendDiscountMoney(reservationDate) + getStarDayDiscount(reservationDate)
                + getGiftDiscountMoney();
    }
}
