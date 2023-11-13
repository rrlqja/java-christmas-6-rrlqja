package christmas.input.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static final String INPUT_START = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public static final String INPUT_RESERVATION_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String INPUT_ORDERS_INPUT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. " +
            "(e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public String getReservationDateInput() {
        System.out.println(INPUT_START);
        System.out.println(INPUT_RESERVATION_DATE);

        return getReadLine();
    }

    public String getOrdersInput() {
        System.out.println(INPUT_ORDERS_INPUT);

        return getReadLine();
    }
    private String getReadLine() {
        return Console.readLine();
    }
}
