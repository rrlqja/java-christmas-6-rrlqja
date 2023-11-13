package christmas.domain.orderresult.strategy;

import christmas.domain.orderresult.OrderResult;

import java.util.List;

public class OrderResultOutputBenefitsStrategy implements OrderResultOutputStrategy {
    @Override
    public String output(OrderResult orderResult) {
        StringBuilder sb = new StringBuilder();
        List<String> benefits = orderResult.getDiscountBenefits();

        sb.append("<혜택 내역>")
                .append("\n");
        for (String benefit : benefits) {
            sb.append(benefit)
                    .append("\n");
        }

        return sb.toString();
    }
}
