package christmas.domain.menu;

import christmas.exception.InvalidMenuQuantityException;

public class MenuQuantity {
    public static final int MINIMUM_MENU_QUANTITY = 1;
    private final Integer menuQuantity;

    public MenuQuantity(Integer menuQuantity) {
        validateMenuQuantity(menuQuantity);
        this.menuQuantity = menuQuantity;
    }

    private void validateMenuQuantity(Integer menuQuantity) {
        if (menuQuantity < MINIMUM_MENU_QUANTITY) {
            throw new InvalidMenuQuantityException();
        }
    }

    public Integer getMenuQuantity() {
        return menuQuantity;
    }
}
