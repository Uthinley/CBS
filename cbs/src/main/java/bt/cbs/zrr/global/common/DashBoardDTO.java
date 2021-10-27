package bt.cbs.zrr.global.common;

/**
 * ====================================================================
 * Created by nima.yoezer on 08/11/2018.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified date:
 * Purpose:
 * ====================================================================
 */
public class DashBoardDTO {
    private String yData;
    private String xData1;
    private String xData2;

    public String getyData() {
        return yData;
    }

    public void setyData(String yData) {
        this.yData = yData;
    }

    public String getxData1() {
        return xData1;
    }

    public void setxData1(String xData1) {
        this.xData1 = xData1;
    }

    public String getxData2() {
        return xData2;
    }

    public void setxData2(String xData2) {
        this.xData2 = xData2;
    }
}
