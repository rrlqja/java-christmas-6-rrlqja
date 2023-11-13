package christmas.input.handler;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuQuantity;
import christmas.domain.ReservationDate;
import christmas.input.convertor.InputConvertor;
import christmas.input.view.InputView;

import java.util.Map;

public class InputHandler {
    private final InputView inputView;
    private final InputConvertor inputConvertor;

    public InputHandler(InputView inputView, InputConvertor inputConvertor) {
        this.inputView = inputView;
        this.inputConvertor = inputConvertor;
    }

    public ReservationDate getReservationDate() {
        String reservationDateInput = getReservationDateInput();

        return inputConvertor.convertToReservationDate(reservationDateInput);
    }

    public Map<Menu, MenuQuantity> getOrders() {
        String ordersInput = getOrdersInput();

        return inputConvertor.convertToOrders(ordersInput);
    }

    private String getReservationDateInput() {
        return inputView.getReservationDateInput();
    }

    private String getOrdersInput() {
        return inputView.getOrdersInput();
    }
}
