package christmas.domain.orderresult;


import christmas.domain.orderresult.strategy.*;

import java.util.ArrayList;
import java.util.List;

public class OrderResultOutputFactory {
    public static OutputStrategies createStrategies() {
        List<OrderResultOutputStrategy> strategies = new ArrayList<>();
        strategies.add(new OrderResultOutputOrderMenuStrategy());
        strategies.add(new OrderResultOutputTotalPriceStrategy());
        strategies.add(new OrderResultOutputGiftMenuStrategy());
        strategies.add(new OrderResultOutputBenefitsStrategy());
        strategies.add(new OrderResultOutputTotalBenefitStrategy());
        strategies.add(new OrderResultOutputFinalPriceStrategy());
        strategies.add(new OrderResultOutputBadgeStrategy());

        return new OutputStrategies(strategies);
    }
}
