package christmas.handler;

import christmas.domain.orderresult.OrderResult;
import christmas.domain.orderresult.OrderResultStrategiesFactory;
import christmas.view.OutputView;

public class OutputHandler {
    private final OutputView outputView;

    public OutputHandler(OutputView outputView) {
        this.outputView = outputView;
    }

    public void showOrderResult(OrderResult orderResult) {
        OrderResultStrategiesFactory.createStrategies()
                .getOutputs(orderResult)
                .forEach(outputView::showOutput);
    }

}
