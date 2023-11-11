package christmas.dto;

import java.text.NumberFormat;
import java.util.Locale;

public class OrderMenuDto {
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
                .append(" ")
                .append(menuQuantity)
                .append("개")
                .toString();
    }
}
