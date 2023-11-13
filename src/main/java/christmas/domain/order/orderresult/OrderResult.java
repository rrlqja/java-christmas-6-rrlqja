package christmas.domain.order.orderresult;

import christmas.domain.menu.GiftMenu;
import christmas.domain.discount.discountbenefit.DiscountBenefit;
import christmas.domain.discount.discountbenefit.GiftDiscountBenefit;
import christmas.domain.discount.discountbenefit.NoDiscountBenefit;
import christmas.domain.order.dto.OrderMenuDto;

import java.util.List;

public class OrderResult {
    private static final int MINIMUM_FOR_GIFT = 120000;
    private final List<OrderMenuDto> orderMenus;
    private final Integer totalAmount;
    private final GiftMenu giftMenu;
    private final List<String> discountBenefits;
    private final Integer totalBenefit;
    private final Integer finalAmount;
    private final Badge badge;

    private OrderResult(List<OrderMenuDto> orderMenus, Integer totalAmount, GiftMenu giftMenu,
                        List<String> discountBenefits, Integer totalBenefit, Integer finalAmount,
                        Badge badge) {
        this.orderMenus = orderMenus;
        this.totalAmount = totalAmount;
        this.giftMenu = giftMenu;
        this.discountBenefits = discountBenefits;
        this.totalBenefit = totalBenefit;
        this.finalAmount = finalAmount;
        this.badge = badge;
    }

    public static OrderResult of(List<OrderMenuDto> orderMenus, Integer totalAmount,
                                 List<DiscountBenefit> discountBenefits) {
        GiftMenu giftMenu = getGiftMenu(totalAmount);
        List<String> benefits = getBenefits(discountBenefits);
        Integer totalBenefit = getTotalBenefit(discountBenefits);
        Integer finalAmount = getFinalAmount(totalAmount, discountBenefits);
        Badge badge = Badge.valueOfBenefit(totalBenefit);

        return new OrderResult(orderMenus, totalAmount, giftMenu, benefits, totalBenefit, finalAmount, badge);
    }


    public List<OrderMenuDto> getOrderMenus() {
        return orderMenus;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public GiftMenu getGiftMenu() {
        return giftMenu;
    }

    public List<String> getDiscountBenefits() {
        return discountBenefits;
    }

    public Integer getTotalBenefit() {
        return totalBenefit;
    }

    public Integer getFinalAmount() {
        return finalAmount;
    }

    public Badge getBadge() {
        return badge;
    }

    private static List<String> getBenefits(List<DiscountBenefit> discountBenefits) {
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

    private static GiftMenu getGiftMenu(Integer totalPrice) {
        GiftMenu giftMenu = GiftMenu.NONE;
        if (totalPrice >= MINIMUM_FOR_GIFT) {
            giftMenu = GiftMenu.CHAMPAGNE;
        }
        return giftMenu;
    }

    private static boolean ifOnlyNoBenefit(List<DiscountBenefit> benefits) {
        return benefits.stream().allMatch(benefit -> benefit instanceof NoDiscountBenefit);
    }

    private static Integer getTotalBenefit(List<DiscountBenefit> discountBenefits) {
        return discountBenefits.stream()
                .mapToInt(DiscountBenefit::getDiscountAmount)
                .sum();
    }

    private static int getFinalAmount(Integer totalPrice, List<DiscountBenefit> discountBenefits) {
        int benefitWithoutGiftMenu = getBenefitWithoutGiftMenu(discountBenefits);
        return totalPrice - benefitWithoutGiftMenu;
    }

    private static int getBenefitWithoutGiftMenu(List<DiscountBenefit> discountBenefits) {
        return discountBenefits.stream()
                .filter(discountBenefit -> !(discountBenefit instanceof GiftDiscountBenefit))
                .mapToInt(DiscountBenefit::getDiscountAmount)
                .sum();
    }
}
