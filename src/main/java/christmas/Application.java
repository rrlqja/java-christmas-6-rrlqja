package christmas;

import christmas.controller.GameController;
import christmas.handler.InputHandler;
import christmas.service.OrderService;
import christmas.validator.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        GameController gameController = new GameController(new InputView(), new InputHandler(new InputValidator()),
                new OrderService(), new OutputView());

        gameController.startGame();
    }
}
