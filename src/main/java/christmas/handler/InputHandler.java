package christmas.handler;

import christmas.domain.Menu;
import christmas.domain.MenuQuantity;
import christmas.domain.ReservationDate;
import christmas.utils.InputConvertor;
import christmas.validator.InputValidator;
import christmas.view.InputView;

import java.util.Map;

public class InputHandler {
    private final InputView inputView;
    private final InputValidator inputValidator;
    private final InputConvertor inputConvertor;

    public InputHandler(InputView inputView, InputValidator inputValidator, InputConvertor inputConvertor) {
        this.inputView = inputView;
        this.inputValidator = inputValidator;
        this.inputConvertor = inputConvertor;
    }

    public ReservationDate getReservationDate() {
        String reservationDateInput = getReservationDateInput();

        inputValidator.validateBlank(reservationDateInput);

        return inputConvertor.convertToReservationDate(reservationDateInput);
    }

    public Map<Menu, MenuQuantity> getOrders() {
        String ordersInput = getOrdersInput();

        inputValidator.validatePattern(ordersInput);

        Map<Menu, MenuQuantity> orders = inputConvertor.convertToOrders(ordersInput);

        inputValidator.validateOrders(orders);
        return orders;
    }

    private String getReservationDateInput() {
        return inputView.getReservationDateInput();
    }

    private String getOrdersInput() {
        return inputView.getOrdersInput();
    }
}
