package bt.cbs.zrr.global.enumeration;

/**
 * ====================================================================
 * Created by Nima Yoezer on 5/14/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
public enum WC_Type {
    TRADE(1, "Trade"),
    TOURISM(2, "Tourism"),
    INDUSTRIES(3, "Industries");

    private Integer value;
    private String text;

    WC_Type(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    static public Integer find(String text){
        for (WC_Type wc : WC_Type.values()) {
            if(wc.getText().equalsIgnoreCase(text)){
                return wc.getValue();
            }
        }
        return null;
    }
}
