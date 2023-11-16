package christmas.domain.menu;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GiftMenuTest {

    @Test
    void getGiftMenuTest() {
        String giftMenu = GiftMenu.CHAMPAGNE.toString();

        assertThat(giftMenu).isEqualTo("샴페인 1개");
    }

}