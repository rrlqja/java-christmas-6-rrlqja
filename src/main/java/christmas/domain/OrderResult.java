package christmas.domain;

import christmas.dto.OrderMenuDto;

import java.util.List;

public class OrderResult {
    private final List<OrderMenuDto> orderMenus;
    private final Integer totalPrice;
    private final GiftMenu giftMenu;
    private final List<DiscountBenefit> discountBenefits;
    private final Integer totalBenefit;
    private final Integer finalPrice;
    private final Badge badge;

    private OrderResult(List<OrderMenuDto> orderMenus, Integer totalPrice, GiftMenu giftMenu,
                        List<DiscountBenefit> discountBenefits, Integer totalBenefit, Integer finalPrice,
                        Badge badge) {
        this.orderMenus = orderMenus;
        this.totalPrice = totalPrice;
        this.giftMenu = giftMenu;
        this.discountBenefits = discountBenefits;
        this.totalBenefit = totalBenefit;
        this.finalPrice = finalPrice;
        this.badge = badge;
    }

    public static OrderResult of(List<OrderMenuDto> orderMenus, Integer totalPrice, Integer giftDiscountMoney,
                                 List<DiscountBenefit> discountBenefits, Integer totalBenefit, Integer finalPrice) {

        GiftMenu giftMenu = GiftMenu.NONE;
        if (giftDiscountMoney != 0) {
            giftMenu = GiftMenu.CHAMPAGNE;
        }

        Badge badge = Badge.valueOfBenefit(totalBenefit);

        return new OrderResult(orderMenus, totalPrice, giftMenu, discountBenefits, totalBenefit, finalPrice, badge);
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

    public List<DiscountBenefit> getDiscountBenefits() {
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
}
