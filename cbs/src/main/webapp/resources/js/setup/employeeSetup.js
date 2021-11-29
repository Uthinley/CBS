employeeSetup = (function () {
    "use strict";
    var formID = "#employeeSetupForm";
    var isSubmitted = false;

    function _baseURL() {
        return globalJs.baseURL + "employeeSetup/"
    }

    function save() {
        $(formID).on('click', '#btnSave', function (e) {
            $(formID).validate({
                submitHandler: function (form) {
                    $.ajax({
                        url: _baseURL() + 'save',
                        type: 'POST',
                        data: $(form).serializeArray(),
                        success: function (res) {
                            if (res.status == 1) {
                                successMsg(res.text, _baseURL());
                            } else {
                                errorMsg(res.text);
                            }
                        }
                    });
                }
            });
        })
    }

    function getEmployeeList(){
        $('#employeeList').on('click', function (e) {
            $('.elist').removeAttr('hidden');
            $.ajax({
                url: _baseURL() + 'getEmployeeList',
                type: 'GET',
                success: function (res) {
                    var row = '';
                    for (var i in res) {
                        let color = "";
                        let status = "";
                        if (res[i].status == 0) {
                            color = "color:red";
                            status = "<span class='badge badge-danger' style='font-weight: bold;'>"+res[i].statusName+"</span>"
                        }else{
                            status = "<span class='badge badge-success' style='font-weight: bold;'>"+res[i].statusName+"</span>"
                        }
                        row = row + '<tr align="left">' +
                            '<td>'+ parseInt(i) +'</td>' +
                            '<td>' + res[i].employeeId + '</td>' +
                            '<td>' + res[i].cid + '</td>' +
                            '<td>' + res[i].firstName + '</td>' +
                            '<td>' + res[i].secondName + '</td>' +
                            '<td>' + res[i].lastName + '</td>' +
                            '<td>' + res[i].positionTitle + '</td>' +
                            '<td>' + res[i].positionLevel + '</td>' +
                            '<td>' + res[i].agency + '</td>' +
                            '<td>' + res[i].division + '</td>' +
                            '<td>' + res[i].email + '</td>' +
                            '<td>' + res[i].phoneNumber + '</td>' +
                            '<td>' + status +'</td>' +
                            '<input type="hidden" id="status" value="' + res[i].status + '"/> ' +
                            '</tr>';
                    }
                    var userListTbl = $('#employeeListTbl');
                    userListTbl.dataTable().fnDestroy();
                    userListTbl.find('tbody').empty().prepend(row);
                    userListTbl.dataTable();
                }
            });
        });
    }

    function deleteU() {
        $(formID).on('click', '#btnDelete', function (e) {
            //$(formID).validate({
            //    submitHandler: function (form) {
            $.ajax({
                url: _baseURL() + 'delete',
                type: 'POST',
                data: $(formID).serializeArray(),
                success: function (res) {
                    if (res.status == 1) {
                        successMsg(res.text, _baseURL());
                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        });
        //});
        //})
    }


    function selectRow() {
        $('#employeeListTbl').find(' tbody').on('click', 'tr', function () {
            var employeeId = $(this).find('td:nth-child(2)').text();
            var cid = $(this).find('td:nth-child(3)').text();
            var firstName = $(this).find('td:nth-child(4)').text();
            var secondName = $(this).find('td:nth-child(5)').text();
            var lastName = $(this).find('td:nth-child(6)').text();
            var positionTitle = $(this).find('td:nth-child(7)').text();
            var positionLevel = $(this).find('td:nth-child(8)').text();
            var agency = $(this).find('td:nth-child(9)').text();
            var division = $(this).find('td:nth-child(10)').text();
            var email = $(this).find('td:nth-child(11)').text();
            var phoneNumber = $(this).find('td:nth-child(12)').text();
            var statusName = $(this).find('td:nth-child(13)').text();
            var status = $(this).find('#status').val();
            var data = {
                "employeeId": employeeId,
                "firstName": firstName,
                "secondName": secondName,
                "lastName": lastName,
                "positionTitle": positionTitle,
                "positionLevel": positionLevel,
                "agency": agency,
                "division": division,
                "email": email,
                "phoneNumber": phoneNumber,
                "status": status,
            };
            populate(data);
            $(formID).find('.editable').prop('disabled', true);
            $(formID).find('.resetP').prop('disabled', true);
            $('#userName').prop('readonly', true).focus();
            $('#btnSave').prop('disabled', true);
            $('#resetPassword').prop('disabled', false);
            $('#btnEdit').prop('disabled', false);
            $('#btnDelete').prop('disabled', false);

        });
    }

    function edit() {
        $('#btnEdit').on('click', function () {
            $('.editable').prop('disabled', false);
            $('.resetP').prop('disabled', true);
            $('#btnSave').prop('disabled', false);
            $('#btnDelete').prop('disabled', true);
            $('#resetDiv').removeClass('hide');
            $('#resetPassword').prop('disabled', false);
            $('#actionType').val('M');
        });
    }

    function resetPassword() {
        $('#resetPassword').on('click', function () {
            var username = $('#userName').val();
            if(!username){
                warningMsg("Select user to reset the password.");
                return;
            }
            $.ajax({
                url: _baseURL() + 'resetPassword',
                type: 'POST',
                data: {username:username},
                success: function (res) {
                    successMsg(res.text);
                }
            });
        });
    }


    return {
        save: save,
        edit: edit,
        deleteU: deleteU,
        selectRow: selectRow,
        // getUserList: getUserList,
        resetPassword: resetPassword,
        getEmployeeList : getEmployeeList
    };
})();

$(document).ready(function () {
    employeeSetup.save();
    employeeSetup.edit();
    employeeSetup.deleteU();
    employeeSetup.selectRow();
    employeeSetup.resetPassword();
    employeeSetup.getEmployeeList();

    $('#btnReset').on('click', function () {
        location.reload(true);
    });


});

