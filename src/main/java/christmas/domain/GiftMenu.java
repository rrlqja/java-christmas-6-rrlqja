package christmas.domain;

public enum GiftMenu {
    CHAMPAGNE("샴페인"),
    NONE("없음");

    private final String menuName;

    GiftMenu(String menuName) {
        this.menuName = menuName;
    }

    @Override
    public String toString() {
        if (CHAMPAGNE.equals(this)) {
            return menuName + " 1개";
        }
        return menuName;
    }
}
