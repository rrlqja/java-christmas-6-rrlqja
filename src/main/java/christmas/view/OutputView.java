package christmas.view;

import christmas.dto.OrderMenuDto;

import java.util.List;

public class OutputView {

    public void showOrderMenus(List<OrderMenuDto> orderMenus) {
        System.out.println("\n<주문 메뉴>");
        for (OrderMenuDto orderMenu : orderMenus) {
            System.out.println(orderMenu.getMenuName() + " " + orderMenu.getMenuQuantity() + "개");
        }
    }
}
