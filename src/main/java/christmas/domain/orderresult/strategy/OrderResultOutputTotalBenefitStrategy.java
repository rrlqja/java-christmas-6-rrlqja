package christmas.domain.orderresult.strategy;

import christmas.domain.orderresult.OrderResult;

import static christmas.utils.FormatUtil.formatNumber;

public class OrderResultOutputTotalBenefitStrategy implements OrderResultOutputStrategy {
    @Override
    public String output(OrderResult orderResult) {
        StringBuilder sb = new StringBuilder();
        Integer totalBenefit = orderResult.getTotalBenefit();

        return sb.append("<총혜택 금액>")
                .append("\n")
                .append(formatNumber(totalBenefit))
                .append("원")
                .append("\n")
                .toString();
    }
}
