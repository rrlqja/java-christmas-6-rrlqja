package christmas.domain.orderresult.strategy;

import christmas.domain.GiftMenu;
import christmas.domain.orderresult.OrderResult;

public class OrderResultOutputGiftMenuStrategy implements OrderResultOutputStrategy {
    @Override
    public String output(OrderResult orderResult) {
        StringBuilder sb = new StringBuilder();
        GiftMenu giftMenu = orderResult.getGiftMenu();

        return sb.append("<증정 메뉴>")
                .append("\n")
                .append(giftMenu.toString())
                .append("\n")
                .toString();
    }
}
