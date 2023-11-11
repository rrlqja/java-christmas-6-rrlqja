package christmas.controller;

import christmas.domain.*;
import christmas.domain.OrderResult;
import christmas.handler.InputHandler;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

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

        OrderResult orderResult = orderService.getOrderResult(order, reservationDate);

        outputView.showOrderMenus(orderResult.getOrderMenus());
        outputView.showTotalPrice(orderResult.getTotalPrice());
        outputView.showGiftMenu(orderResult.getGiftMenu());
        outputView.showBenefits(orderResult.getDiscountBenefits());
        outputView.showTotalBenefit(orderResult.getTotalBenefit());
        outputView.showFinalPrice(orderResult.getFinalPrice());
        outputView.showBadge(orderResult.getBadge());
    }
}
