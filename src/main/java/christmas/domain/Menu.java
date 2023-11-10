package christmas.domain;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6000, MenuCategory.APPETIZER),
    TAPAS("타파스", 5500, MenuCategory.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, MenuCategory.APPETIZER),

    T_BONE_STEAK("티본스테이크", 55000, MenuCategory.MAIN),
    BBQ_RIBS("바비큐립", 54000, MenuCategory.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MenuCategory.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MenuCategory.MAIN),

    CHOCOLATE_CAKE("초코케이크", 15000, MenuCategory.DESSERT),
    ICE_CREAM("아이스크림", 5000, MenuCategory.DESSERT),

    ZERO_COLA("제로콜라", 3000, MenuCategory.DRINK),
    RED_WINE("레드와인", 60000, MenuCategory.DRINK),
    CHAMPAGNE("샴페인", 25000, MenuCategory.DRINK);

    private final String menuName;
    private final Integer menuPrice;
    private final MenuCategory menuCategory;

    Menu(String menuName, Integer menuPrice, MenuCategory menuCategory) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuCategory = menuCategory;
    }

    public String getMenuName() {
        return menuName;
    }

    public Integer getMenuPrice() {
        return menuPrice;
    }

    public MenuCategory getMenuCategory() {
        return menuCategory;
    }
}
