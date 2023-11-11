package christmas.dto;

public class OrderMenuDto {
    private final String menuName;
    private final Integer menuQuantity;

    public OrderMenuDto(String menuName, Integer menuQuantity) {
        this.menuName = menuName;
        this.menuQuantity = menuQuantity;
    }

    public String getMenuName() {
        return menuName;
    }

    public Integer getMenuQuantity() {
        return menuQuantity;
    }
}
