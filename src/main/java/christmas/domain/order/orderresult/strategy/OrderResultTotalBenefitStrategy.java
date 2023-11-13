package christmas.domain.order.orderresult.strategy;

import christmas.domain.order.orderresult.OrderResult;

import static christmas.utils.FormatUtil.formatNumber;

public class OrderResultTotalBenefitStrategy implements OrderResultStrategy {
    private static final String TITLE = "<총혜택 금액>";
    private static final String LINE_BREAK = System.lineSeparator();
    private static final String CURRENCY_SUFFIX = "원";
    @Override
    public String output(OrderResult orderResult) {
        StringBuilder sb = new StringBuilder();
        Integer totalBenefit = orderResult.getTotalBenefit();

        return sb.append(TITLE)
                .append(LINE_BREAK)
                .append(formatNumber(totalBenefit))
                .append(CURRENCY_SUFFIX)
                .append(LINE_BREAK)
                .toString();
    }
}
