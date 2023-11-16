package christmas.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatUtil {

    public static String formatNumber(Integer price) {
        return NumberFormat.getNumberInstance(Locale.KOREA).format(price);
    }
}
