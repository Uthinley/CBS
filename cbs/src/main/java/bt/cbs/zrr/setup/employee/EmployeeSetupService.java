package bt.cbs.zrr.setup.employee;

import bt.cbs.zrr.global.base.BaseService;
import bt.cbs.zrr.global.dto.CurrentUser;
import bt.cbs.zrr.global.dto.ResponseMessage;
import bt.cbs.zrr.global.enumeration.StatusCode;
import bt.cbs.zrr.global.library.DateUtil;
import bt.cbs.zrr.global.library.GlobalVariable;
import bt.cbs.zrr.setup.user.UserSetup;
import bt.cbs.zrr.setup.user.UserSetupDTO;
import bt.cbs.zrr.setup.user.UserSetupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeSetupService extends BaseService {

    @Autowired
    private UserSetupDao userSetupDao;

    @Autowired
    private EmployeeSetupDAO employeeSetupDAO;

    //region public methods
    @Transactional(readOnly = false)
    public ResponseMessage save(EmployeeSetupDTO employeeSetupDTO, CurrentUser currentUser, Character actionType) {
        if (employeeSetupDTO == null) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Please enter the details properly.");
            return responseMessage;
        }
        switch (actionType) {
            case CMD_FLAG_CREATE:
                responseMessage = save(employeeSetupDTO, currentUser);
                break;
            case CMD_FLAG_MODIFY:
//                responseMessage = update(userSetupDTO, currentUser);
                break;
            default:
                responseMessage.setStatus(UNSUCCESSFUL_STATUS);
                responseMessage.setText(MSG_DEFAULT_UNSUCCESSFUL);
        }
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage save(EmployeeSetupDTO employeeSetupDTO, CurrentUser currentUser) {

//        EmployeeSetup employeeSetup = userSetupDao.getUser(userSetupDTO.getUserName());
        EmployeeSetup employeeSetup = employeeSetupDAO.getEmployeeId(employeeSetupDTO.getEmployeeId());
        if (employeeSetup != null) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Employee ID already exist. Please choose another one.");
            return responseMessage;
        }
        employeeSetup = convertDTOToEntity(employeeSetupDTO, currentUser);
        employeeSetupDAO.save(employeeSetup);

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Employee saved successfully");
        return responseMessage;
    }

    /**
     * Convert to Entity for save
     *
     */
    private EmployeeSetup convertDTOToEntity(EmployeeSetupDTO employeeSetupDTO, CurrentUser currentUser) {
        EmployeeSetup employeeSetup = new EmployeeSetup();
        employeeSetup.setEmployeeId(employeeSetupDTO.getEmployeeId());
        employeeSetup.setAgency(employeeSetupDTO.getAgency());
        employeeSetup.setCid(employeeSetupDTO.getCid());
        employeeSetup.setEmail(employeeSetupDTO.getEmail());
        employeeSetup.setDivision(employeeSetupDTO.getDivision());
        employeeSetup.setFirstName(employeeSetupDTO.getFirstName());
        employeeSetup.setSecondName(employeeSetupDTO.getSecondName());
        employeeSetup.setLastName(employeeSetupDTO.getLastName());
        employeeSetup.setPhoneNumber(employeeSetupDTO.getPhoneNumber());
        employeeSetup.setPositionLevel(employeeSetupDTO.getPositionLevel());
        employeeSetup.setPositionTitle(employeeSetupDTO.getPositionTitle());
        employeeSetup.setCreatedBy(currentUser.getUserName());
        employeeSetup.setCreatedDate(currentUser.getServerDate());
        return employeeSetup;
    }

    @Transactional(readOnly = true)
    public List<EmployeeSetupDTO> getEmployeeList() {
        return employeeSetupDAO.getEmployeeList();
    }

    @Transactional(readOnly = false)
    public ResponseMessage delete(String employeeId, CurrentUser currentUser) {
        employeeSetupDAO.delete(employeeId);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Employee is deleted successfully.");
        return responseMessage;

    }
}
