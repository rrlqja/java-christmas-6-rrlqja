package christmas.domain.orderresult;

import christmas.domain.orderresult.strategy.OrderResultStrategy;

import java.util.List;

public class OrderResultStrategies {
    private final List<OrderResultStrategy> strategies;

    public OrderResultStrategies(List<OrderResultStrategy> strategies) {
        this.strategies = strategies;
    }

    public List<String> getOutputs(OrderResult orderResult) {
        return strategies.stream()
                .map(strategy -> strategy.output(orderResult))
                .toList();
    }
}
