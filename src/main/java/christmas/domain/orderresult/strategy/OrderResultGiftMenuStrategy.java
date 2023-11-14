package christmas.domain.orderresult.strategy;

import christmas.domain.menu.GiftMenu;
import christmas.domain.orderresult.OrderResult;

public class OrderResultGiftMenuStrategy implements OrderResultStrategy {
    private static final String TITLE = "<증정 메뉴>";
    private static final String LINE_BREAK = System.lineSeparator();
    @Override
    public String output(OrderResult orderResult) {
        GiftMenu giftMenu = orderResult.getGiftMenu();

        return getOutput(giftMenu);
    }

    private String getOutput(GiftMenu giftMenu) {
        StringBuilder sb = new StringBuilder();
        return sb.append(TITLE)
                .append(LINE_BREAK)
                .append(giftMenu.toString())
                .append(LINE_BREAK)
                .toString();
    }
}
