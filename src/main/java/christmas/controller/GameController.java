package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.MenuQuantity;
import christmas.domain.ReservationDate;
import christmas.handler.InputHandler;
import christmas.view.InputView;

import java.util.Map;

public class GameController {
    private final InputView inputView;
    private final InputHandler inputHandler;

    public GameController(InputView inputView, InputHandler inputHandler) {
        this.inputView = inputView;
        this.inputHandler = inputHandler;
    }

    public void startGame() {
        String reservationDateInput = inputView.getReservationDateInput();
        ReservationDate reservationDate = inputHandler.toReservationDate(reservationDateInput);

        String ordersInput = inputView.getOrdersInput();
        Map<Menu, MenuQuantity> orders = inputHandler.toOrders(ordersInput);


    }
}
