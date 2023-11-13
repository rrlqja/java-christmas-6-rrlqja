package christmas;

import christmas.controller.GameController;
import christmas.input.handler.InputHandler;
import christmas.output.handler.OutputHandler;
import christmas.domain.order.OrderService;
import christmas.input.convertor.InputConvertor;
import christmas.input.validator.InputValidator;
import christmas.input.view.InputView;
import christmas.output.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        GameController gameController = new GameController(
                new InputHandler(
                        new InputView(), new InputConvertor(new InputValidator())),
                new OutputHandler(
                        new OutputView()), new OrderService());

        gameController.startGame();
    }
}
