package christmas;

import christmas.controller.GameController;
import christmas.handler.InputHandler;
import christmas.handler.OutputHandler;
import christmas.service.OrderService;
import christmas.utils.InputConvertor;
import christmas.validator.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

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
