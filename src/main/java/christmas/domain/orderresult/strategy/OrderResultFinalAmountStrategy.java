package christmas.domain.orderresult.strategy;

import christmas.domain.orderresult.OrderResult;

import static christmas.utils.FormatUtil.formatNumber;

public class OrderResultFinalAmountStrategy implements OrderResultStrategy {
    private static final String TITLE = "<할인 후 예상 결제 금액>";
    private static final String LINE_BREAK = System.lineSeparator();
    private static final String CURRENCY_SUFFIX = "원";
    @Override
    public String output(OrderResult orderResult) {
        StringBuilder sb = new StringBuilder();
        Integer finalPrice = orderResult.getFinalAmount();

        return sb.append(TITLE)
                .append(LINE_BREAK)
                .append(formatNumber(finalPrice))
                .append(CURRENCY_SUFFIX)
                .append(LINE_BREAK)
                .toString();
    }
}
