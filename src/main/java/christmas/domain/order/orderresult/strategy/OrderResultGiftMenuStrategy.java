package christmas.domain.order.orderresult.strategy;

import christmas.domain.menu.GiftMenu;
import christmas.domain.order.orderresult.OrderResult;

public class OrderResultGiftMenuStrategy implements OrderResultStrategy {
    private static final String TITLE = "<증정 메뉴>";
    private static final String LINE_BREAK = "\n";
    @Override
    public String output(OrderResult orderResult) {
        StringBuilder sb = new StringBuilder();
        GiftMenu giftMenu = orderResult.getGiftMenu();

        return sb.append(TITLE)
                .append(LINE_BREAK)
                .append(giftMenu.toString())
                .append(LINE_BREAK)
                .toString();
    }
}
