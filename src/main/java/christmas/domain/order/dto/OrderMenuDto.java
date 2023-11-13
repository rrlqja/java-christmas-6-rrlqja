package christmas.domain.order.dto;

public class OrderMenuDto {
    private static final String SPACE = " ";
    private static final String UNIT_SUFFIX = "개";
    private final String menuName;
    private final Integer menuQuantity;

    public OrderMenuDto(String menuName, Integer menuQuantity) {
        this.menuName = menuName;
        this.menuQuantity = menuQuantity;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append(menuName)
                .append(SPACE)
                .append(menuQuantity)
                .append(UNIT_SUFFIX)
                .toString();
    }
}
