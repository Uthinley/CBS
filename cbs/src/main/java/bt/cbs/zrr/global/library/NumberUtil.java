package bt.cbs.zrr.global.library;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by Dechen Wangdi on 12/19/2019.
 */
public class NumberUtil {

    /**
     * To add two big decimal number.
     *
     * @param param1 BigDecimal
     * @param param2 BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal plus(BigDecimal param1, BigDecimal param2) {
        if (Objects.isNull(param1)) {
            param1 = BigDecimal.ZERO;
        }
        if (Objects.isNull(param2)) {
            param2 = BigDecimal.ZERO;
        }
        return param1.add(param2);
    }

    /**
     * To check equality of two big decimal amount.
     *
     * @param param1 BigDecimal
     * @param param2 BigDecimal
     * @return boolean
     */
    public static boolean isEqual(BigDecimal param1, BigDecimal param2) {
        return param1.equals(param2);
    }

    /**
     * To subtract  pram2 from param1
     *
     * @param param1 BigDecimal
     * @param param2 BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal minus(BigDecimal param1, BigDecimal param2) {
        if (Objects.isNull(param1)) {
            param1 = BigDecimal.ZERO;
        }
        if (Objects.isNull(param2)) {
            param2 = BigDecimal.ZERO;
        }
        return param1.subtract(param2);
    }
}
