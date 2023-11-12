package christmas.domain.discount.discountbenefit;

import java.util.Objects;

public class NoDiscountBenefit implements DiscountBenefit {

    public static final String NO_DISCOUNT = "없음";
    private final String description;
    private final Integer discountAmount;

    public NoDiscountBenefit() {
        this.description = NO_DISCOUNT;
        this.discountAmount = 0;
    }

    @Override
    public String getBenefit() {
        return description;
    }

    @Override
    public Integer getDiscountAmount() {
        return this.discountAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(description);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof NoDiscountBenefit)) return false;
        NoDiscountBenefit other = (NoDiscountBenefit) obj;
        return Objects.equals(description, other.description);
    }
}
