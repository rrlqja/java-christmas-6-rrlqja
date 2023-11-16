package christmas.input.handler;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuQuantity;
import christmas.domain.ReservationDate;
import christmas.input.convertor.InputConvertor;
import christmas.input.view.InputView;
import christmas.utils.InputSupplier;

import java.util.Map;

public class InputHandler {
    private final InputView inputView;
    private final InputConvertor inputConvertor;

    public InputHandler(InputView inputView, InputConvertor inputConvertor) {
        this.inputView = inputView;
        this.inputConvertor = inputConvertor;
    }

    public ReservationDate getReservationDate() {
        return getInput(() -> getReservationDateInput());
    }

    public Map<Menu, MenuQuantity> getOrders() {
        return getInput(() -> getOrdersInput());
    }

    private ReservationDate getReservationDateInput() {
        String reservationDateInput = inputView.getReservationDateInput();

        return inputConvertor.convertToReservationDate(reservationDateInput);
    }

    private Map<Menu, MenuQuantity> getOrdersInput() {
        String ordersInput = inputView.getOrdersInput();

        return inputConvertor.convertToOrders(ordersInput);
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
