package christmas.domain.orderresult.strategy;

import christmas.domain.orderresult.Badge;
import christmas.domain.orderresult.OrderResult;

public class OrderResultBadgeStrategy implements OrderResultStrategy {
    private static final String TITLE = "<12월 이벤트 배지>";
    private static final String LINE_BREAK = System.lineSeparator();

    @Override
    public String output(OrderResult orderResult) {
        Badge badge = orderResult.getBadge();

        return getOutput(badge);
    }

    private String getOutput(Badge badge) {
        StringBuilder sb = new StringBuilder();
        return sb.append(TITLE)
                .append(LINE_BREAK)
                .append(badge.getBadge())
                .toString();
    }
}
