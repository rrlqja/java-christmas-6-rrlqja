package christmas;

import christmas.controller.GameController;
import christmas.domain.order.OrderService;
import christmas.input.convertor.InputConvertor;
import christmas.input.handler.InputHandler;
import christmas.input.validator.InputValidator;
import christmas.input.view.InputView;
import christmas.output.handler.OutputHandler;
import christmas.output.view.OutputView;

public class GameConfig {

    public GameController gameController() {
        return new GameController(inputHandler(), outputHandler(), orderService());
    }

    private InputHandler inputHandler() {
        return new InputHandler(inputView(), inputConvertor());
    }

    private OutputHandler outputHandler() {
        return new OutputHandler(outputView());
    }

    private OrderService orderService() {
        return new OrderService();
    }

    private InputView inputView() {
        return new InputView();
    }

    private InputConvertor inputConvertor() {
        return new InputConvertor(inputValidator());
    }

    private InputValidator inputValidator() {
        return new InputValidator();
    }

    private OutputView outputView() {
        return new OutputView();
    }
}
