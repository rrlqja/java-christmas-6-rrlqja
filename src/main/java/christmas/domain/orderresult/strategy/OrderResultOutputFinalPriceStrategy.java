package christmas.domain.orderresult.strategy;

import christmas.domain.orderresult.OrderResult;

import static christmas.utils.FormatUtil.formatNumber;

public class OrderResultOutputFinalPriceStrategy implements OrderResultOutputStrategy {
    @Override
    public String output(OrderResult orderResult) {
        StringBuilder sb = new StringBuilder();
        Integer finalPrice = orderResult.getFinalPrice();

        return sb.append("<할인 후 예상 결제 금액>")
                .append("\n")
                .append(formatNumber(finalPrice))
                .append("원")
                .append("\n")
                .toString();
    }
}
