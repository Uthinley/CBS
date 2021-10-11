package com.springapp.mvc.setup.group;

import com.springapp.mvc.global.base.BaseService;
import com.springapp.mvc.global.dto.CurrentUser;
import com.springapp.mvc.global.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sonam Dargay on 12/20/2019.
 */
@Service
public class GroupSetupService extends BaseService {
    //region private variables.
    @Autowired
    private GroupSetupDao groupSetupDao;
    //endregion

    //region public methods.
    @Transactional(readOnly = false)
    public ResponseMessage save(GroupSetupDTO groupSetupDTO, CurrentUser currentUser, Character actionType) throws Exception {
        switch (actionType) {
            case CMD_FLAG_CREATE:
                responseMessage = save(groupSetupDTO, currentUser);
                break;
            case CMD_FLAG_MODIFY:
                responseMessage = update(groupSetupDTO, currentUser);
                break;
            default:
                responseMessage.setStatus(UNSUCCESSFUL_STATUS);
                responseMessage.setText(MSG_DEFAULT_UNSUCCESSFUL);
        }
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage save(GroupSetupDTO groupSetupDTO, CurrentUser currentUser) {
        GroupSetup groupSetup = convertDTOToEntity(groupSetupDTO, currentUser);
        GroupSetup_A groupSetup_a = convertDTOToEntity_A(groupSetupDTO, currentUser);
        groupSetup_a.setGroupID(groupSetup.getGroupID());

        groupSetup_a.setCmdFlag(CMD_FLAG_CREATE);
        groupSetupDao.save(groupSetup, groupSetup_a);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Group is saved successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage delete(int groupId, CurrentUser currentUser) {
        groupSetupDao.delete(groupId);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Group is deleted successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage update(GroupSetupDTO groupSetupDTO, CurrentUser currentUser) {
        GroupSetup groupSetup = groupSetupDao.getGroupInfoById(groupSetupDTO.getGroupID());
        GroupSetup_A groupSetup_a = convertDTOToEntity_A(groupSetupDTO, currentUser);
        groupSetup_a.setCmdFlag(CMD_FLAG_MODIFY);
        if (groupSetup == null) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("There is some problem. Please logout and try again.");
            return responseMessage;
        }
        groupSetup = convertDTOToEntity(groupSetup, groupSetupDTO, currentUser);
        groupSetupDao.save(groupSetup, groupSetup_a);

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Group is updated successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = true)
    public List<GroupSetupDTO> getGroupList() {
        return groupSetupDao.getGroupList();
    }

    public Boolean isGroupExist(String groupName) {
        return groupSetupDao.isGroupExist(groupName);
    }
    //endregion

    //region private methods.
    private GroupSetup convertDTOToEntity(GroupSetupDTO groupSetupDTO, CurrentUser currentUser) {
        GroupSetup groupSetup = new GroupSetup();
        groupSetup.setGroupName(groupSetupDTO.getGroupName());
        groupSetup.setGroupStatus(groupSetupDTO.getGroupStatus());
        groupSetup.setCreatedBy(currentUser.getUserName());
        groupSetup.setCreatedDate(currentUser.getServerDate());
        return groupSetup;
    }

    private GroupSetup convertDTOToEntity(GroupSetup groupSetup, GroupSetupDTO groupSetupDTO, CurrentUser currentUser) {
        groupSetup.setGroupID(groupSetupDTO.getGroupID());
        groupSetup.setGroupName(groupSetupDTO.getGroupName());
        groupSetup.setGroupStatus(groupSetupDTO.getGroupStatus());
        groupSetup.setCreatedBy(currentUser.getUserName());
        groupSetup.setCreatedDate(currentUser.getServerDate());
        return groupSetup;
    }

    private GroupSetup_A convertDTOToEntity_A(GroupSetupDTO groupSetupDTO,
                                              CurrentUser currentUser) {
        GroupSetup_A groupSetup_a = new GroupSetup_A();
        groupSetup_a.setGroupID(groupSetupDTO.getGroupID());
        groupSetup_a.setGroupName(groupSetupDTO.getGroupName());
        groupSetup_a.setGroupStatus(groupSetupDTO.getGroupStatus());
        groupSetup_a.setCmdFlag(CMD_FLAG_CREATE);//Todo need to chnage
        groupSetup_a.setCreatedBy(currentUser.getUserName());
        groupSetup_a.setCreatedDate(currentUser.getServerDate());
        return groupSetup_a;
    }
    //endregion
}
