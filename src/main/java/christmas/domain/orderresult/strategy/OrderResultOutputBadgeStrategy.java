package christmas.domain.orderresult.strategy;

import christmas.domain.Badge;
import christmas.domain.orderresult.OrderResult;

public class OrderResultOutputBadgeStrategy implements OrderResultOutputStrategy {
    @Override
    public String output(OrderResult orderResult) {
        StringBuilder sb = new StringBuilder();
        Badge badge = orderResult.getBadge();

        return sb.append("<12월 이벤트 배지>")
                .append("\n")
                .append(badge.getBadge())
                .toString();
    }
}
