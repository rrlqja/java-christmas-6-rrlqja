package christmas.domain.order.orderresult;


import christmas.domain.order.orderresult.strategy.*;
import christmas.domain.orderresult.strategy.*;

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
