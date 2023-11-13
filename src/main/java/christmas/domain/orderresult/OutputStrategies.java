package christmas.domain.orderresult;

import christmas.domain.orderresult.strategy.OrderResultOutputStrategy;

import java.util.List;

public class OutputStrategies {
    private final List<OrderResultOutputStrategy> strategies;

    public OutputStrategies(List<OrderResultOutputStrategy> strategies) {
        this.strategies = strategies;
    }

    public List<String> getOutputs(OrderResult orderResult) {
        return strategies.stream()
                .map(strategy -> strategy.output(orderResult))
                .toList();
    }
}
