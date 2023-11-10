package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String getReservationDateInput() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");

        return getReadLine();
    }

    private String getReadLine() {
        return Console.readLine();
    }
}