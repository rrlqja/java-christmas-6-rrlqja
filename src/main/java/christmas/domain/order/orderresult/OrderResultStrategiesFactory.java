package christmas.domain.order.orderresult;

import christmas.domain.order.orderresult.strategy.OrderResultGiftMenuStrategy;
import christmas.domain.order.orderresult.strategy.OrderResultOrderMenuStrategy;
import christmas.domain.order.orderresult.strategy.OrderResultStrategy;
import christmas.domain.order.orderresult.strategy.OrderResultTotalPriceStrategy;
import christmas.domain.order.orderresult.strategy.OrderResultBenefitsStrategy;
import christmas.domain.order.orderresult.strategy.OrderResultTotalBenefitStrategy;
import christmas.domain.order.orderresult.strategy.OrderResultFinalPriceStrategy;
import christmas.domain.order.orderresult.strategy.OrderResultBadgeStrategy;

import java.util.ArrayList;
import java.util.List;

public class OrderResultStrategiesFactory {
    public static OrderResultStrategies createStrategies() {
        List<OrderResultStrategy> strategies = new ArrayList<>();
        strategies.add(new OrderResultOrderMenuStrategy());
        strategies.add(new OrderResultTotalPriceStrategy());
        strategies.add(new OrderResultGiftMenuStrategy());
        strategies.add(new OrderResultBenefitsStrategy());
        strategies.add(new OrderResultTotalBenefitStrategy());
        strategies.add(new OrderResultFinalPriceStrategy());
        strategies.add(new OrderResultBadgeStrategy());

        return new OrderResultStrategies(strategies);
    }
}
