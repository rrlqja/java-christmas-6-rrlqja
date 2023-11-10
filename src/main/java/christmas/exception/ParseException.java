package christmas.exception;

public class ParseException extends IllegalArgumentException {
    public ParseException() {
        super("[ERROR] 입력값이 잘못되었습니다.");
    }
}
