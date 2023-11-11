package christmas.domain;

import christmas.utils.FormatUtil;

public class DiscountBenefit {
    private final String descriptions;
    private final Integer amount;

    public DiscountBenefit(String descriptions, Integer amount) {
        this.descriptions = descriptions;
        this.amount = amount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append(descriptions)
                .append(": -")
                .append(getFormattedAmount())
                .append("원")
                .toString();
    }

    private String getFormattedAmount() {
        return FormatUtil.formatNumber(amount);
    }
}
