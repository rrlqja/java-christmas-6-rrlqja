package christmas.handler;

import christmas.domain.Menu;
import christmas.domain.MenuCategory;
import christmas.domain.MenuQuantity;
import christmas.domain.ReservationDate;
import christmas.exception.*;
import christmas.validator.InputValidator;

import java.util.HashMap;
import java.util.Map;

public class InputHandler {

    private final InputValidator inputValidator;

    public InputHandler(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public ReservationDate toReservationDate(String reservationInput) {
        inputValidator.validateBlank(reservationInput);

        Integer reservationDate = parseToInt(reservationInput);

        return new ReservationDate(reservationDate);
    }

    public Map<Menu, MenuQuantity> toOrders(String ordersInput) {
        inputValidator.validatePattern(ordersInput);

        String[] items = ordersInput.split(",");
        Map<Menu, MenuQuantity> orders = getOrders(items);

        validateTotalQuantity(orders);
        validateDrinkOnlyOrder(orders);

        return orders;
    }

    private Integer parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ParseException();
        }
    }

    private Map<Menu, MenuQuantity> getOrders(String[] items) {
        Map<Menu, MenuQuantity> orders = new HashMap<>();
        for (String item : items) {
            inputValidator.validateBlank(item);
            putOrder(orders, item);
        }
        return orders;
    }

    private void putOrder(Map<Menu, MenuQuantity> orders, String item) {
        String[] order = item.split("-");
        String orderMenuName = order[0].trim();
        String orderMenuQuantity = order[1].trim();

        Menu menu = Menu.valueOfMenuName(orderMenuName);
        MenuQuantity menuQuantity = new MenuQuantity(parseToInt(orderMenuQuantity));

        validateDuplicateMenu(orders, menu);

        orders.put(menu, menuQuantity);
    }

    private void validateTotalQuantity(Map<Menu, MenuQuantity> orderMap) {
        int totalQuantity = orderMap.values()
                .stream()
                .mapToInt(MenuQuantity::getMenuQuantity)
                .sum();
        if (totalQuantity > 20) {
            throw new InvalidOrderException();
        }
    }

    private void validateDrinkOnlyOrder(Map<Menu, MenuQuantity> orderMap) {
        boolean onlyDrinks = orderMap.keySet()
                .stream()
                .allMatch(menu -> menu.getMenuCategory() == MenuCategory.DRINK);
        if (onlyDrinks) {
            throw new InvalidOrderException();
        }
    }

    private void validateDuplicateMenu(Map<Menu, MenuQuantity> orders, Menu menu) {
        if (orders.containsKey(menu)) {
            throw new InvalidOrderException();
        }
    }
}
