package christmas.domain;

import christmas.exception.InvalidMenuQuantityException;

public class MenuQuantity {
    private final Integer menuQuantity;

    public MenuQuantity(Integer menuQuantity) {
        validateMenuQuantity(menuQuantity);
        this.menuQuantity = menuQuantity;
    }

    private void validateMenuQuantity(Integer menuQuantity) {
        if (menuQuantity < 1) {
            throw new InvalidMenuQuantityException();
        }
    }

    public Integer getMenuQuantity() {
        return menuQuantity;
    }
}
