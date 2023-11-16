package christmas.domain.orderresult.strategy;

import christmas.domain.orderresult.OrderResult;

import java.util.List;

public class OrderResultBenefitsStrategy implements OrderResultStrategy {
    private static final String TITLE = "<혜택 내역>";
    private static final String LINE_BREAK = System.lineSeparator();
    @Override
    public String output(OrderResult orderResult) {
        List<String> benefits = orderResult.getDiscountBenefits();

        return getOutput(benefits);
    }

    private String getOutput(List<String> benefits) {
        StringBuilder sb = new StringBuilder();
        sb.append(TITLE)
                .append(LINE_BREAK);
        for (String benefit : benefits) {
            sb.append(benefit)
                    .append(LINE_BREAK);
        }

        return sb.toString();
    }
}
