package christmas.domain;

public enum MenuCategory {
    APPETIZER("에피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료");

    private final String menuCategory;

    MenuCategory(String menuCategory) {
        this.menuCategory = menuCategory;
    }
}
