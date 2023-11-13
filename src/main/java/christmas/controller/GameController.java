package christmas.controller;

import christmas.domain.*;
import christmas.domain.orderresult.OrderResult;
import christmas.handler.InputHandler;
import christmas.handler.OutputHandler;
import christmas.service.OrderService;
import christmas.utils.InputSupplier;

import java.util.Map;

public class GameController {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final OrderService orderService;

    public GameController(InputHandler inputHandler, OutputHandler outputHandler,
                          OrderService orderService) {
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
        return inputHandler.getReservationDate();
    }

    private Map<Menu, MenuQuantity> getOrders() {
        return inputHandler.getOrders();
    }

    private  <T> T getInput(InputSupplier<T> inputSupplier) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
