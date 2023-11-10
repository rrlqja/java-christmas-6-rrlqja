package christmas.handler;

import christmas.domain.ReservationDate;
import christmas.exception.BlankInputException;
import christmas.exception.ParseException;

public class InputHandler {

    public ReservationDate toReservationDate(String reservationInput) {
        validateBlank(reservationInput);

        Integer reservationDate = parseToInt(reservationInput);

        return new ReservationDate(reservationDate);
    }

    private void validateBlank(String input) {
        if (input.isBlank()) {
            throw new BlankInputException();
        }
    }

    private Integer parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ParseException();
        }
    }
}
