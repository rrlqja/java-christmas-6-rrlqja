package christmas.domain.order.orderresult.strategy;

import christmas.domain.order.orderresult.Badge;
import christmas.domain.order.orderresult.OrderResult;

public class OrderResultBadgeStrategy implements OrderResultStrategy {
    private static final String TITLE = "<12월 이벤트 배지>";
    private static final String LINE_BREAK = "\n";

    @Override
    public String output(OrderResult orderResult) {
        StringBuilder sb = new StringBuilder();
        Badge badge = orderResult.getBadge();

        return sb.append(TITLE)
                .append(LINE_BREAK)
                .append(badge.getBadge())
                .toString();
    }
}
