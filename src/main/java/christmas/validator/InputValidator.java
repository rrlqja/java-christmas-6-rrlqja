package christmas.validator;

import christmas.domain.Menu;
import christmas.domain.MenuCategory;
import christmas.domain.MenuQuantity;
import christmas.exception.InvalidInputPatternException;
import christmas.exception.InvalidOrderException;

import java.util.Map;

public class InputValidator {
    private static final String PATTERN = "^[가-힣]+-\\d+(,\\s*[가-힣]+-\\d+)*$";

    public void validateOrdersPattern(String ordersInput) {
        if (!ordersInput.matches(PATTERN)) {
            throw new InvalidInputPatternException();
        }
    }

    public void validateOrders(Map<Menu, MenuQuantity> orders) {
        validateTotalQuantity(orders);
        validateDrinkOnlyOrder(orders);
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
}
