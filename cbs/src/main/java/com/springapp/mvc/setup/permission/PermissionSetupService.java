package com.springapp.mvc.setup.permission;

import com.springapp.mvc.global.base.BaseService;
import com.springapp.mvc.global.common.CommonService;
import com.springapp.mvc.global.dto.CurrentUser;
import com.springapp.mvc.global.dto.DropdownDTO;
import com.springapp.mvc.global.dto.ResponseMessage;
import com.springapp.mvc.global.enumeration.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ====================================================================
 * Created by nimayoezer on 21/08/2018.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified date:
 * Purpose:
 * ====================================================================
 */
@Service
public class PermissionSetupService extends BaseService {

    //region local variables
    @Autowired
    private PermissionSetupDao permissionSetupDao;

    @Autowired
    private CommonService commonService;
    //endregion

    //region public methods
    @Transactional(readOnly = false)
    public ResponseMessage save(PermissionSetupDTO permissionSetupDTO, CurrentUser currentUser) throws Exception {
        if (permissionSetupDTO == null) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please provide the valid data.");
            return responseMessage;
        }
        if (permissionSetupDTO.getPermissionList() == null) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please provide the valid data.");
            return responseMessage;
        }

        List<PermissionListDTO> currentPLFromScreen = permissionSetupDTO.getPermissionList().stream().
                filter(permission -> permission.getIsView() != null || permission.getIsSave() != null ||
                        permission.getIsEdit() != null || permission.getIsDelete() != null)
                .collect(Collectors.toList());

        List<PermissionListDTO> previousPLFromDB = getPermissionList(permissionSetupDTO.getGroupId());

        BigDecimal nextPermissionID = permissionSetupDao.getNextPermissionId();
        for (PermissionListDTO permission : currentPLFromScreen) {
            PermissionSetup permissionSetup = convertDTOToEntity(permission, currentUser);
            if (permissionSetup.getPermissionId() == null) {
                permissionSetup.setPermissionId(nextPermissionID);
            }
            permissionSetupDao.save(permissionSetup);
            nextPermissionID = nextPermissionID.add(BigDecimal.ONE);
        }

        //region to delete extra permission
        if (previousPLFromDB != null && !previousPLFromDB.isEmpty()) {
            previousPLFromDB.removeAll(currentPLFromScreen);
            deletePermission(previousPLFromDB, currentUser);
        }
        //endregion

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Permission set successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = true)
    public List<PermissionListDTO> getPermissionList(Integer groupId) {
        List<PermissionListDTO> permissions = new ArrayList<>();
        try {
            permissions = permissionSetupDao.getPermissionList(groupId, StatusCode.ACTIVE.getValue());
        } catch (Exception e) {
            for (DropdownDTO obj : commonService.getScreenList()) {
                PermissionListDTO permission = new PermissionListDTO();
                permission.setGroupId(groupId);
                permission.setScreenId(obj.getValue().toString());
                permission.setIsView('Y');
                permission.setIsEdit('Y');
                permission.setIsSave('Y');
                permission.setIsDelete('Y');
                permissions.add(permission);
            }
        }
        return permissions;
    }

    @Transactional
    public void deletePermission(List<PermissionListDTO> permissionsToDelete, CurrentUser currentUser) {
        if (permissionsToDelete != null && !permissionsToDelete.isEmpty()) {
            permissionsToDelete.forEach(permission -> {
                PermissionSetup permissionSetup = convertDTOToEntity(permission, currentUser);
                permissionSetupDao.delete(permissionSetup);
            });
        }

    }
    //endregion

    //region private methods
    private PermissionSetup convertDTOToEntity(PermissionListDTO permissionSetupDTO, CurrentUser currentUser) {
        BigDecimal permissionId = permissionSetupDao.getPermissionId(permissionSetupDTO.getGroupId(),
                permissionSetupDTO.getScreenId());

        PermissionSetup permissionSetup = new PermissionSetup();
        permissionSetup.setPermissionId(permissionId);
        permissionSetup.setGroupId(permissionSetupDTO.getGroupId());
        permissionSetup.setScreenId(permissionSetupDTO.getScreenId());
        permissionSetup.setIsView(permissionSetupDTO.getIsView() == null ? "N" : permissionSetupDTO.getIsView().toString());
        permissionSetup.setIsSave(permissionSetupDTO.getIsSave() == null ? "N" : permissionSetupDTO.getIsSave().toString());
        permissionSetup.setIsEdit(permissionSetupDTO.getIsEdit() == null ? "N" : permissionSetupDTO.getIsEdit().toString());
        permissionSetup.setIsDelete(permissionSetupDTO.getIsDelete() == null ? "N" : permissionSetupDTO.getIsDelete().toString());
        permissionSetup.setCreatedBy(currentUser.getUserName());
        permissionSetup.setCreatedDate(currentUser.getServerDate());
        return permissionSetup;
    }
    //endregion
}
