package christmas.domain.menu;

public enum GiftMenu {
    CHAMPAGNE("샴페인"),
    NONE("없음");

    private static final String QUANTITY = " 1개";
    private final String menuName;

    GiftMenu(String menuName) {
        this.menuName = menuName;
    }

    @Override
    public String toString() {
        if (CHAMPAGNE.equals(this)) {
            return menuName + QUANTITY;
        }
        return menuName;
    }
}
