package com.springapp.mvc.setup.branch;

import com.springapp.mvc.global.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Sonam Dargay on 1/3/2020.
 */
@Entity
@Table(name = "nes_branch_info")
public class BranchSetup extends BaseEntity implements Serializable {
    @Id
    @Column(name = "BR_CODE")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer branchId;

    @Column(name = "BR_NAME")
    private String branchName;

    @Column(name = "BR_STATUS")
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

    public void setBranchStatus(int branchStatus) {
        this.branchStatus = branchStatus;
    }
}
