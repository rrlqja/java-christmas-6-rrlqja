package christmas.domain.orderresult.strategy;

import christmas.domain.orderresult.OrderResult;
import christmas.domain.order.dto.OrderMenuDto;

import java.util.List;

public class OrderResultOrderMenuStrategy implements OrderResultStrategy {
    private static final String TITLE = "<주문 메뉴>";
    private static final String LINE_BREAK = System.lineSeparator();
    @Override
    public String output(OrderResult orderResult) {
        StringBuilder sb = new StringBuilder();
        List<OrderMenuDto> orderMenus = orderResult.getOrderMenus();

        sb.append(TITLE)
                .append(LINE_BREAK);
        for (OrderMenuDto orderMenu : orderMenus) {
            sb.append(orderMenu.toString())
                    .append(LINE_BREAK);
        }

        return sb.toString();
    }
}
