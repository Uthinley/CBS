package bt.cbs.zrr.setup.branch;

/**
 * Created by hp on 1/3/2020.
 */
public class BranchSetupDTO {
    private Integer branchId;
    private String branchName;
    private String branchStatusName;
    private int branchStatus;

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getBranchStatus() {
        return branchStatus;
    }

    public String getBranchStatusName() {
        return branchStatusName;
    }

    public void setBranchStatusName(String branchStatusName) {
        this.branchStatusName = branchStatusName;
    }

    public void setBranchStatus(int branchStatus) {
        this.branchStatus = branchStatus;
    }
}
