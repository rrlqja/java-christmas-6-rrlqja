package christmas.utils;

import christmas.domain.Menu;
import christmas.domain.MenuQuantity;
import christmas.domain.ReservationDate;
import christmas.exception.InvalidOrderException;
import christmas.exception.ParseException;

import java.util.HashMap;
import java.util.Map;

public class InputConvertor {

    public ReservationDate convertToReservationDate(String reservationDateInput) {
        Integer reservationDate = parseInput(reservationDateInput);

        return new ReservationDate(reservationDate);
    }

    public Map<Menu, MenuQuantity> convertToOrders(String ordersInput) {
        String[] items = getSplitInput(ordersInput, ",");

         return getOrders(items);
    }

    private Integer parseInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ParseException();
        }
    }

    private String[] getSplitInput(String input, String delimiter) {
        return splitInput(input, delimiter);
    }

    private Map<Menu, MenuQuantity> getOrders(String[] items) {
        Map<Menu, MenuQuantity> orders = new HashMap<>();
        for (String item : items) {
            putOrder(orders, item);
        }
        return orders;
    }

    private void putOrder(Map<Menu, MenuQuantity> orders, String item) {
        String[] order = splitInput(item, "-");
        String orderMenuName = order[0].trim();
        Integer orderMenuQuantity = parseInput(order[1].trim());

        Menu menu = Menu.valueOfMenuName(orderMenuName);
        MenuQuantity menuQuantity = new MenuQuantity(orderMenuQuantity);

        validateDuplicateMenu(orders, menu);

        orders.put(menu, menuQuantity);
    }

    private void validateDuplicateMenu(Map<Menu, MenuQuantity> orders, Menu menu) {
        if (orders.containsKey(menu)) {
            throw new InvalidOrderException();
        }
    }

    private String[] splitInput(String ordersInput, String delimiter) {
        return ordersInput.split(delimiter);
    }
}
