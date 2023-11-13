package christmas.domain.orderresult;

import christmas.domain.Badge;
import christmas.domain.GiftMenu;
import christmas.domain.discount.discountbenefit.DiscountBenefit;
import christmas.domain.discount.discountbenefit.GiftDiscountBenefit;
import christmas.domain.discount.discountbenefit.NoDiscountBenefit;
import christmas.dto.OrderMenuDto;

import java.util.List;

public class OrderResult {
    private static final String NOTHING = "없음";
    private static final int MINIMUM_FOR_GIFT = 120000;
    private final List<OrderMenuDto> orderMenus;
    private final Integer totalPrice;
    private final GiftMenu giftMenu;
    private final List<String> discountBenefits;
    private final Integer totalBenefit;
    private final Integer finalPrice;
    private final Badge badge;

    private OrderResult(List<OrderMenuDto> orderMenus, Integer totalPrice, GiftMenu giftMenu,
                        List<String> discountBenefits, Integer totalBenefit, Integer finalPrice,
                        Badge badge) {
        this.orderMenus = orderMenus;
        this.totalPrice = totalPrice;
        this.giftMenu = giftMenu;
        this.discountBenefits = discountBenefits;
        this.totalBenefit = totalBenefit;
        this.finalPrice = finalPrice;
        this.badge = badge;
    }

    public static OrderResult of(List<OrderMenuDto> orderMenus, Integer totalPrice,
                                 List<DiscountBenefit> discountBenefits) {
        GiftMenu giftMenu = getGiftMenu(totalPrice);
        List<String> benefits = getBenefits(discountBenefits);
        Integer totalBenefit = getTotalBenefit(discountBenefits);
        Integer finalPrice = getFinalPrice(totalPrice, discountBenefits);
        Badge badge = Badge.valueOfBenefit(totalBenefit);

        return new OrderResult(orderMenus, totalPrice, giftMenu, benefits, totalBenefit, finalPrice, badge);
    }


    public List<OrderMenuDto> getOrderMenus() {
        return orderMenus;
    }

    public Integer getTotalPrice() {
        return totalPrice;
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

    public Integer getFinalPrice() {
        return finalPrice;
    }

    public Badge getBadge() {
        return badge;
    }

    private static List<String> getBenefits(List<DiscountBenefit> discountBenefits) {
        if (ifOnlyNoBenefit(discountBenefits)) {
            return List.of(NOTHING);
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

    private static int getFinalPrice(Integer totalPrice, List<DiscountBenefit> discountBenefits) {
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
