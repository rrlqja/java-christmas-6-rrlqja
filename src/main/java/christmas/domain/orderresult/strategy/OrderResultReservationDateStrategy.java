package christmas.domain.orderresult.strategy;

import christmas.domain.orderresult.OrderResult;

public class OrderResultReservationDateStrategy implements OrderResultStrategy {
    private static final String EVENT_PREFIX = "12월 ";
    private static final String EVENT_SUFFIX = "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    @Override
    public String output(OrderResult orderResult) {
        Integer reservationDate = orderResult.getReservationDate();

        return getOutput(reservationDate);
    }

    private String getOutput(Integer reservationDate) {
        StringBuilder sb = new StringBuilder();
        return sb.append(EVENT_PREFIX)
                .append(reservationDate)
                .append(EVENT_SUFFIX)
                .toString();
    }
}
