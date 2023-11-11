package christmas.view;

import christmas.domain.Badge;
import christmas.domain.DiscountBenefit;
import christmas.domain.GiftMenu;
import christmas.dto.OrderMenuDto;

import java.util.List;

import static christmas.utils.FormatUtil.*;

public class OutputView {

    public void showOrderMenus(List<OrderMenuDto> orderMenus) {
        System.out.println("\n<주문 메뉴>");
        for (OrderMenuDto orderMenu : orderMenus) {
            System.out.println(orderMenu.toString());
        }
    }

    public void showTotalPrice(Integer totalPrice) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(formatNumber(totalPrice) + "원");
    }

    public void showGiftMenu(GiftMenu giftMenu) {
        System.out.println("\n<증정 메뉴>");
        System.out.println(giftMenu.toString());
    }

    public void showBenefits(List<DiscountBenefit> benefits) {
        System.out.println("\n<혜택 내역>");
        if (benefits.isEmpty()) {
            System.out.println("없음");
        }
        for (DiscountBenefit benefit : benefits) {
            System.out.println(benefit.toString());
        }
    }

    public void showTotalBenefit(Integer totalBenefit) {
        System.out.println("\n<총혜택 금액>");
        System.out.println(formatNumber(totalBenefit) + "원");
    }

    public void showFinalPrice(Integer finalPrice) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(formatNumber(finalPrice) + "원");
    }

    public void showBadge(Badge badge) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(badge.getBadge());
    }
}
