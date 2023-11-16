package christmas.input.convertor;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuQuantity;
import christmas.domain.ReservationDate;
import christmas.exception.InvalidDateException;
import christmas.exception.InvalidInputException;
import christmas.input.validator.InputValidator;

import java.util.HashMap;
import java.util.Map;

public class InputConvertor {
    private static final String COMMA = ",";
    private static final String HYPHEN = "-";
    private static final int MENU_NAME_INDEX = 0;
    private static final int MENU_QUANTITY_INDEX = 1;
    private final InputValidator inputValidator;

    public InputConvertor(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public ReservationDate convertToReservationDate(String reservationDateInput) {
        Integer reservationDate = parseInput(reservationDateInput);

        return new ReservationDate(reservationDate);
    }

    public Map<Menu, MenuQuantity> convertToOrders(String ordersInput) {
        inputValidator.validateOrdersPattern(ordersInput);

        Map<Menu, MenuQuantity> orders = getOrders(splitInput(ordersInput, COMMA));

        inputValidator.validateOrders(orders);
        return orders;
    }

    private Integer parseInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidDateException();
        }
    }

    private Map<Menu, MenuQuantity> getOrders(String[] items) {
        Map<Menu, MenuQuantity> orders = new HashMap<>();
        for (String item : items) {
            putOrder(orders, item);
        }
        return orders;
    }

    private void putOrder(Map<Menu, MenuQuantity> orders, String item) {
        String[] order = splitInput(item, HYPHEN);
        String orderMenuName = order[MENU_NAME_INDEX].trim();
        Integer orderMenuQuantity = parseInput(order[MENU_QUANTITY_INDEX].trim());

        Menu menu = Menu.valueOfMenuName(orderMenuName);
        MenuQuantity menuQuantity = new MenuQuantity(orderMenuQuantity);

        validateDuplicateMenu(orders, menu);

        orders.put(menu, menuQuantity);
    }

    private void validateDuplicateMenu(Map<Menu, MenuQuantity> orders, Menu menu) {
        if (orders.containsKey(menu)) {
            throw new InvalidInputException();
        }
    }

    private String[] splitInput(String ordersInput, String delimiter) {
        return ordersInput.split(delimiter);
    }
}
