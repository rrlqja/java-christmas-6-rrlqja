package christmas.handler;

import christmas.domain.Menu;
import christmas.domain.MenuQuantity;
import christmas.domain.ReservationDate;
import christmas.utils.InputConvertor;
import christmas.view.InputView;

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
