package bt.cbs.zrr.global.enumeration;

/**
 * ====================================================================
 * Created by nimayoezer on 14/09/2018.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified date:
 * Purpose:
 * ====================================================================
 */
public enum ReportGenerationStatus {
    GENERATION_FAILED("F"),
    GENERATION_SUCCESS("S");

    private final String value;

    private ReportGenerationStatus(String value){
        this.value=value;
    }

    public String value(){
        return value;
    }
}
