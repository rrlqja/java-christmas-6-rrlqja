package christmas.domain.orderresult;

import christmas.domain.menu.GiftMenu;
import christmas.domain.order.discount.discountbenefit.DiscountBenefit;
import christmas.domain.order.discount.discountbenefit.GiftDiscountBenefit;
import christmas.domain.order.discount.discountbenefit.NoDiscountBenefit;
import christmas.domain.order.dto.OrderMenuDto;

import java.util.List;

public class OrderResult {
    private static final int MINIMUM_FOR_GIFT = 120000;
    private final List<OrderMenuDto> orderMenus;
    private final Integer totalAmount;
    private final List<DiscountBenefit> discountBenefits;

    public OrderResult(List<OrderMenuDto> orderMenus, Integer totalAmount, List<DiscountBenefit> discountBenefits) {
        this.orderMenus = orderMenus;
        this.totalAmount = totalAmount;
        this.discountBenefits = discountBenefits;
    }

    public List<OrderMenuDto> getOrderMenus() {
        return orderMenus;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public GiftMenu getGiftMenu() {
        return getGiftMenu(totalAmount);
    }

    public List<String> getDiscountBenefits() {
        return getBenefits(discountBenefits);
    }

    public Integer getTotalBenefit() {
        return getTotalBenefit(discountBenefits);
    }

    public Integer getFinalAmount() {
        return getFinalAmount(totalAmount, discountBenefits);
    }

    public Badge getBadge() {
        return Badge.valueOfBenefit(getTotalBenefit(discountBenefits));
    }

    private GiftMenu getGiftMenu(Integer totalAmount) {
        GiftMenu giftMenu = GiftMenu.NONE;
        if (totalAmount >= MINIMUM_FOR_GIFT) {
            giftMenu = GiftMenu.CHAMPAGNE;
        }
        return giftMenu;
    }

    private List<String> getBenefits(List<DiscountBenefit> discountBenefits) {
        if (ifOnlyNoBenefit(discountBenefits)) {
            return discountBenefits.stream()
                    .map(DiscountBenefit::getBenefit)
                    .toList();
        }
        return discountBenefits.stream()
                .filter(discountBenefit -> !(discountBenefit instanceof NoDiscountBenefit))
                .map(DiscountBenefit::getBenefit)
                .toList();
    }

    private boolean ifOnlyNoBenefit(List<DiscountBenefit> benefits) {
        return benefits.stream().allMatch(benefit -> benefit instanceof NoDiscountBenefit);
    }

    private Integer getTotalBenefit(List<DiscountBenefit> discountBenefits) {
        return discountBenefits.stream()
                .mapToInt(DiscountBenefit::getDiscountAmount)
                .sum();
    }

    private int getFinalAmount(Integer totalAmount, List<DiscountBenefit> discountBenefits) {
        int benefitWithoutGiftMenu = getBenefitWithoutGiftMenu(discountBenefits);
        return totalAmount - benefitWithoutGiftMenu;
    }

    private int getBenefitWithoutGiftMenu(List<DiscountBenefit> discountBenefits) {
        return discountBenefits.stream()
                .filter(discountBenefit -> !(discountBenefit instanceof GiftDiscountBenefit))
                .mapToInt(DiscountBenefit::getDiscountAmount)
                .sum();
    }
}
