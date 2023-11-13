package christmas.domain.orderresult.strategy;

import christmas.domain.orderresult.OrderResult;

import static christmas.utils.FormatUtil.formatNumber;

public class OrderResultOutputTotalPriceStrategy implements OrderResultOutputStrategy {
    @Override
    public String output(OrderResult orderResult) {
        StringBuilder sb = new StringBuilder();
        sb.append("<할인 전 총주문 금액>")
                .append("\n")
                .append(formatNumber(orderResult.getTotalPrice()))
                .append("원")
                .append("\n");

        return sb.toString();
    }
}
