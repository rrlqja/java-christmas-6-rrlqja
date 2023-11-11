package christmas.domain;

public class DiscountBenefit {
    private final String descriptions;
    private final Integer amount;

    public DiscountBenefit(String descriptions, Integer amount) {
        this.descriptions = descriptions;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return descriptions + " " + amount + "원";
    }
}
