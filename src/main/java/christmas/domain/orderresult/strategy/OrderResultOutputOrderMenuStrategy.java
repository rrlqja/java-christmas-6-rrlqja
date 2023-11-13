package christmas.domain.orderresult.strategy;

import christmas.domain.orderresult.OrderResult;
import christmas.dto.OrderMenuDto;

import java.util.List;

public class OrderResultOutputOrderMenuStrategy implements OrderResultOutputStrategy {
    @Override
    public String output(OrderResult orderResult) {
        StringBuilder sb = new StringBuilder();
        List<OrderMenuDto> orderMenus = orderResult.getOrderMenus();

        sb.append("<주문 메뉴>")
                .append("\n");
        for (OrderMenuDto orderMenu : orderMenus) {
            sb.append(orderMenu.toString())
                    .append("\n");
        }

        return sb.toString();
    }
}
