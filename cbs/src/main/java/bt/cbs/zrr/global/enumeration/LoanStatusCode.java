package bt.cbs.zrr.global.enumeration;

import java.math.BigInteger;

/**
 * Created by Nima Yoezer on 3/25/2020.
 * Description :
 * Purpose:
 * Modified by :
 * Modified on :
 */
public enum LoanStatusCode {
    STANDARD(new BigInteger("1")),
    WATCH(new BigInteger("2")),
    SUBSTANDARD(new BigInteger("3")),
    DOUBTFUL(new BigInteger("4"));

    private final BigInteger value;

    private LoanStatusCode(BigInteger value){
        this.value=value;
    }

    public BigInteger value(){
        return value;
    }
}
