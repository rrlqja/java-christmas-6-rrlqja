package christmas.controller;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuQuantity;
import christmas.domain.order.Order;
import christmas.domain.ReservationDate;
import christmas.domain.orderresult.OrderResult;
import christmas.input.handler.InputHandler;
import christmas.output.handler.OutputHandler;
import christmas.domain.order.OrderService;

import java.util.Map;

public class GameController {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final OrderService orderService;

    public GameController(InputHandler inputHandler, OutputHandler outputHandler, OrderService orderService) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.orderService = orderService;
    }

    public void startGame() {
        ReservationDate reservationDate = inputHandler.getReservationDate();
        Map<Menu, MenuQuantity> orders = inputHandler.getOrders();

        Order order = orderService.createOrder(orders);

        OrderResult orderResult = orderService.createOrderResult(order, reservationDate);

        outputHandler.showOrderResult(orderResult);
    }
}
