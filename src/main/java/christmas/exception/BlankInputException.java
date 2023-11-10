package christmas.exception;

public class BlankInputException extends IllegalArgumentException {
    public BlankInputException() {
        super("[ERROR] 입력값이 공백입니다.");
    }
}
