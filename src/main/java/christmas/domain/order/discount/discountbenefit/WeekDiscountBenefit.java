package christmas.domain.order.discount.discountbenefit;

import christmas.utils.FormatUtil;

import java.util.Objects;

public class WeekDiscountBenefit implements DiscountBenefit {
    private static final String DISCOUNT_PREFIX = ": -";
    private static final String CURRENCY_SUFFIX = "원";
    private final String description;
    private final Integer discountAmount;

    public WeekDiscountBenefit(String description, Integer discountAmount) {
        this.description = description;
        this.discountAmount = discountAmount;
    }

    @Override
    public String getBenefit() {
        StringBuilder sb = new StringBuilder();
        return sb.append(description)
                .append(DISCOUNT_PREFIX)
                .append(getFormattedAmount())
                .append(CURRENCY_SUFFIX)
                .toString();
    }

    @Override
    public Integer getDiscountAmount() {
        return this.discountAmount;
    }

    private String getFormattedAmount() {
        return FormatUtil.formatNumber(discountAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(description);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WeekDiscountBenefit)) {
            return false;
        }
        WeekDiscountBenefit other = (WeekDiscountBenefit) obj;
        return Objects.equals(description, other.description);
    }
}
