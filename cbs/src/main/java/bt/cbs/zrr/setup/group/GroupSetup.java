package bt.cbs.zrr.setup.group;


import bt.cbs.zrr.global.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sonam Dargay on 12/20/2019.
 */
@Entity
@Table(name = "sa_user_group")
public class GroupSetup extends BaseEntity implements Serializable {

    @Id
    @Column(name = "GR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupID;

    @Column(name = "GR_NAME")
    private String groupName;

    @Column(name = "GR_STATUS")
    private int groupStatus;

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(int groupStatus) {
        this.groupStatus = groupStatus;
    }
}

