package christmas.validator;

import christmas.exception.BlankInputException;
import christmas.exception.InvalidInputPatternException;

public class InputValidator {
    private static final String PATTERN = "^[가-힣]+-\\d+(,\\s*[가-힣]+-\\d+)*$";


    public void validateBlank(String input) {
        if (input.isBlank()) {
            throw new BlankInputException();
        }
    }

    public void validatePattern(String ordersInput) {
        if (!ordersInput.matches(PATTERN)) {
            throw new InvalidInputPatternException();
        }
    }
}
