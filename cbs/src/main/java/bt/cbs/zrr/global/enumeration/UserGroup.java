package bt.cbs.zrr.global.enumeration;

import java.math.BigInteger;

/**
 * Created by Nima Yoezer on 3/25/2020.
 * Description :
 * Purpose:
 * Modified by :
 * Modified on :
 */
public enum UserGroup {
    ADMIN(1),
    APPROVER(2),
    REVIEWER(3),
    RESEARCHER(4);

    private final Integer value;

    private UserGroup(Integer value){
        this.value=value;
    }

    public Integer value(){
        return value;
    }
}
