package christmas.input.validator;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuCategory;
import christmas.domain.menu.MenuQuantity;
import christmas.exception.InvalidInputException;

import java.util.Map;

public class InputValidator {
    private static final String PATTERN = "^[가-힣]+-\\d+(,[가-힣]+-\\d+)*$";
    private static final int MAX_MENU_QUANTITY = 20;

    public void validateOrdersPattern(String ordersInput) {
        if (!ordersInput.matches(PATTERN)) {
            throw new InvalidInputException();
        }
    }

    public void validateOrders(Map<Menu, MenuQuantity> orders) {
        validateTotalQuantity(orders);
        validateDrinkOnlyOrder(orders);
    }

    private void validateTotalQuantity(Map<Menu, MenuQuantity> orderMap) {
        int totalQuantity = getTotalQuantity(orderMap);
        if (totalQuantity > MAX_MENU_QUANTITY) {
            throw new InvalidInputException();
        }
    }

    private int getTotalQuantity(Map<Menu, MenuQuantity> orderMap) {
        return orderMap.values()
                .stream()
                .mapToInt(MenuQuantity::getMenuQuantity)
                .sum();
    }

    private void validateDrinkOnlyOrder(Map<Menu, MenuQuantity> orderMap) {
        if (isOnlyDrinks(orderMap)) {
            throw new InvalidInputException();
        }
    }

    private boolean isOnlyDrinks(Map<Menu, MenuQuantity> orderMap) {
        return orderMap.keySet()
                .stream()
                .allMatch(menu -> menu.getMenuCategory() == MenuCategory.DRINK);
    }
}
