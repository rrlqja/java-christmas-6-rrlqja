package christmas.domain.orderresult.strategy;

import christmas.domain.orderresult.OrderResult;

import java.util.List;

public class OrderResultBenefitsStrategy implements OrderResultStrategy {
    private static final String TITLE = "<혜택 내역>";
    private static final String LINE_BREAK = System.lineSeparator();
    @Override
    public String output(OrderResult orderResult) {
        StringBuilder sb = new StringBuilder();
        List<String> benefits = orderResult.getDiscountBenefits();

        sb.append(LINE_BREAK).append(TITLE)
                .append(LINE_BREAK);
        for (String benefit : benefits) {
            sb.append(benefit)
                    .append(LINE_BREAK);
        }

        return sb.toString();
    }
}
