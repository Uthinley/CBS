package com.springapp.mvc.setup.branch;

import com.springapp.mvc.global.base.BaseService;
import com.springapp.mvc.global.dto.CurrentUser;
import com.springapp.mvc.global.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sonam Dargay on 1/3/2020.
 */
@Service
public class BranchSetupService extends BaseService {
    @Autowired
    private BranchSetupDao branchSetupDao;

    //region public methods.
    @Transactional(readOnly = false)
    public ResponseMessage save(BranchSetupDTO branchSetupDTO, CurrentUser currentUser, Character actionType) throws Exception {
        switch (actionType) {
            case CMD_FLAG_CREATE:
                responseMessage = save(branchSetupDTO, currentUser);
                break;
            case CMD_FLAG_MODIFY:
                responseMessage = update(branchSetupDTO, currentUser);
//                responseMessage = update(groupSetupDTO, currentUser);
                break;
            default:
                responseMessage.setStatus(UNSUCCESSFUL_STATUS);
                responseMessage.setText(MSG_DEFAULT_UNSUCCESSFUL);
        }
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage delete(int branchId, CurrentUser currentUser) {
        branchSetupDao.delete(branchId);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Branch is deleted successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = true)
    public List<BranchSetupDTO> getBranchList() {
        return branchSetupDao.getBranchList();
    }

    public Boolean isBranchExist(String branchName) {
        return branchSetupDao.isBranchExist(branchName);
    }

    @Transactional(readOnly = false)
    public ResponseMessage save(BranchSetupDTO branchSetupDTO, CurrentUser currentUser) {
        BranchSetup branchSetup = convertDTOToEntity(branchSetupDTO, currentUser);
//        BranchSetup groupSetup = convertDTOToEntity(branchSetupDTO, currentUser);
//        GroupSetup_A groupSetup_a = convertDTOToEntity_A(groupSetupDTO, currentUser);
//        groupSetup_a.setCmdFlag(CMD_FLAG_CREATE);
//        branchSetupDao.save(groupSetup, groupSetup_a);
        branchSetupDao.save(branchSetup);
//        branchSetupDao.save(groupSetup);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Branch is updated successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage update(BranchSetupDTO branchSetupDTO, CurrentUser currentUser) {
        BranchSetup branchSetup = branchSetupDao.getBranchInfoById(branchSetupDTO.getBranchId());
//        GroupSetup_A groupSetup_a = convertDTOToEntity_A(groupSetupDTO, currentUser);
//        groupSetup_a.setCmdFlag(CMD_FLAG_MODIFY);
        if (branchSetup == null) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("There is some problem. Please logout and try again.");
            return responseMessage;
        }
//        branchSetup = convertDTOToEntity(branchSetup, branchSetupDTO, currentUser);
//        branchSetupDao.save(branchSetup, groupSetup_a);

        branchSetup = convertDTOToEntity(branchSetup, branchSetupDTO, currentUser);
        branchSetupDao.save(branchSetup);

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Branch is updated successfully.");
        return responseMessage;
    }

    //region private methods.
    private BranchSetup convertDTOToEntity(BranchSetupDTO branchSetupDTO, CurrentUser currentUser) {
        BranchSetup branchSetup = new BranchSetup();
        branchSetup.setBranchId(branchSetupDTO.getBranchId());
        branchSetup.setBranchName(branchSetupDTO.getBranchName());
        branchSetup.setBranchStatus(branchSetupDTO.getBranchStatus());
        branchSetup.setCreatedBy(currentUser.getUserName());
        branchSetup.setCreatedDate(currentUser.getServerDate());
        return branchSetup;
    }

    private BranchSetup convertDTOToEntity(BranchSetup branchSetup, BranchSetupDTO branchSetupDTO, CurrentUser currentUser) {
        branchSetup.setBranchId(branchSetupDTO.getBranchId());
        branchSetup.setBranchName(branchSetupDTO.getBranchName());
        branchSetup.setBranchStatus(branchSetupDTO.getBranchStatus());
        branchSetup.setCreatedBy(currentUser.getUserName());
        branchSetup.setCreatedDate(currentUser.getServerDate());
        return branchSetup;
    }
}
