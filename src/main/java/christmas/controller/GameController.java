package christmas.controller;

import christmas.domain.*;
import christmas.domain.orderresult.OrderResult;
import christmas.handler.InputHandler;
import christmas.handler.OutputHandler;
import christmas.service.OrderService;
import christmas.utils.InputSupplier;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class GameController {
    private final InputView inputView;
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final OrderService orderService;

    public GameController(InputView inputView, InputHandler inputHandler, OutputHandler outputHandler,
                          OrderService orderService) {
        this.inputView = inputView;
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.orderService = orderService;
    }

    public void startGame() {
        ReservationDate reservationDate = getInput(this::getReservationDate);
        Map<Menu, MenuQuantity> orders = getInput(this::getOrders);
        Order order = orderService.createOrder(orders);

        OrderResult orderResult = orderService.getOrderResult(order, reservationDate);

        outputHandler.showOrderResult(orderResult);
    }

    private ReservationDate getReservationDate() {
        String reservationDateInput = inputView.getReservationDateInput();
        return inputHandler.toReservationDate(reservationDateInput);
    }

    private Map<Menu, MenuQuantity> getOrders() {
        String ordersInput = inputView.getOrdersInput();
        return inputHandler.toOrders(ordersInput);
    }

    public <T> T getInput(InputSupplier<T> inputSupplier) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
