package christmas.domain.orderresult.strategy;

import christmas.domain.orderresult.OrderResult;

import static christmas.utils.FormatUtil.formatNumber;

public class OrderResultTotalBenefitStrategy implements OrderResultStrategy {
    private static final String TITLE = "<총혜택 금액>";
    private static final String LINE_BREAK = System.lineSeparator();
    public static final String CURRENCY_PREFIX = "-";
    private static final String CURRENCY_SUFFIX = "원";

    @Override
    public String output(OrderResult orderResult) {
        StringBuilder sb = new StringBuilder();
        Integer totalBenefit = orderResult.getTotalBenefit();

        return sb.append(TITLE)
                .append(LINE_BREAK)
                .append(CURRENCY_PREFIX)
                .append(formatNumber(totalBenefit))
                .append(CURRENCY_SUFFIX)
                .append(LINE_BREAK)
                .toString();
    }
}
