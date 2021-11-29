EmployeeSetupDAO.getEmployeeList = SELECT employee_id AS employeeId, cid AS cid, position_title AS positionTitle, position_level AS positionLevel, \
                                          agency AS agency, division AS division, phone_number AS phoneNumber, email AS email,\
                                          first_name AS firstName, second_name AS secondName, last_name AS lastName, status, CASE WHEN (status=1) THEN 'Active' ELSE 'Inactive' END AS statusName,  \
                                          CREATEDBY AS createdBy, CREATEDDATE AS createdDate FROM sa_employee_details \

EmployeeSetupDAO.delete = DELETE FROM sa_employee_details WHERE employee_id=:employeeId