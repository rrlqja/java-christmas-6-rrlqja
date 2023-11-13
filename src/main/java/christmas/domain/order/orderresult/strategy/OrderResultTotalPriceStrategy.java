package christmas.domain.order.orderresult.strategy;

import christmas.domain.order.orderresult.OrderResult;

import static christmas.utils.FormatUtil.formatNumber;

public class OrderResultTotalPriceStrategy implements OrderResultStrategy {
    private static final String TITLE = "<할인 전 총주문 금액>";
    private static final String LINE_BREAK = "\n";
    private static final String CURRENCY_SUFFIX = "원";

    @Override
    public String output(OrderResult orderResult) {
        StringBuilder sb = new StringBuilder();
        sb.append(TITLE)
                .append(LINE_BREAK)
                .append(formatNumber(orderResult.getTotalPrice()))
                .append(CURRENCY_SUFFIX)
                .append(LINE_BREAK);

        return sb.toString();
    }
}
