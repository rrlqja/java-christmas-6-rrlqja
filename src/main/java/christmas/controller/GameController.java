package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.MenuQuantity;
import christmas.domain.Order;
import christmas.domain.ReservationDate;
import christmas.dto.OrderMenuDto;
import christmas.handler.InputHandler;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;
import java.util.Map;

public class GameController {
    private final InputView inputView;
    private final InputHandler inputHandler;
    private final OrderService orderService;
    private final OutputView outputView;

    public GameController(InputView inputView, InputHandler inputHandler,
                          OrderService orderService, OutputView outputView) {
        this.inputView = inputView;
        this.inputHandler = inputHandler;
        this.orderService = orderService;
        this.outputView = outputView;
    }

    public void startGame() {
        String reservationDateInput = inputView.getReservationDateInput();
        ReservationDate reservationDate = inputHandler.toReservationDate(reservationDateInput);

        String ordersInput = inputView.getOrdersInput();
        Map<Menu, MenuQuantity> orders = inputHandler.toOrders(ordersInput);

        Order order = orderService.createOrder(orders);
        List<OrderMenuDto> orderMenu = orderService.getOrderMenu(order);
        outputView.showOrderMenus(orderMenu);

        Integer totalPrice = orderService.getTotalPrice(order);
        outputView.showTotalPrice(totalPrice);
    }
}
