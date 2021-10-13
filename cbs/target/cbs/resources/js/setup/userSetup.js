/**
 * Created by nimayoezer on 20/08/2018.
 */

userSetup = (function () {
    "use strict";
    var formID = "#userSetupForm";
    var isSubmitted = false;

    function _baseURL() {
        return nesGlobal.baseURL + "usersetup/"
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

    function getUserList() {
        $.ajax({
            url: _baseURL() + 'getUserList',
            type: 'GET',
            success: function (res) {

                var row = '';
                for (var i in res) {
                    row = row + '<tr align="left">' +
                        '<td></td>' +
                        '<td>' + res[i].userName + '</td>' +
                        '<td>' + res[i].fullName + '</td>' +
                        '<td>' + res[i].groupName + '</td>' +
                        '<td>' + ((res[i].userStatus == '1') ? 'Active' : 'Inactive') + '</td>' +
                        '<td>' +
                        '<input type="hidden" id="userId" value="' + res[i].userId + '"/> ' +
                        '<input type="hidden" id="agencyCode" value="' + res[i].agencyCode + '"/> ' +
                        '<input type="hidden" id="employeeCode" value="' + res[i].employeeCode + '"/> ' +
                        '<input type="hidden" id="userStatus" value="' + res[i].userStatus + '"/> ' +
                        // '<input type="hidden" id="groupId" value="' + res[i].groupId + '"/> ' +
                        '</td>' +
                        '</tr>';
                }
                var userListTbl = $('#userListTbl');
                userListTbl.dataTable().fnDestroy();
                userListTbl.find('tbody').empty().prepend(row);
                userListTbl.dataTable();
            }
        });
    }

    function selectRow() {
        $('#userListTbl').find(' tbody').on('click', 'tr', function () {
            var userName = $(this).find('td:nth-child(2)').text();
            var fullName = $(this).find('td:nth-child(3)').text();
            var userId = $(this).find('#userId').val();
            var emailId = $(this).find('#emailId').val();
            var employeeId = $(this).find('#employeeId').val();
            var userStatus = $(this).find('#userStatus').val();
            var groupId = $(this).find('#groupId').val();
            var data = {
                "userName": userName,
                "fullName": fullName,
                "userId": userId,
                "employeeId": employeeId,
                "emailId": emailId,
                "userStatus": userStatus,
                "groupId": groupId
            };
            populate(data);
            $(formID).find('.editable').prop('disabled', true);
            $(formID).find('.resetP').prop('disabled', true);
            $('#userName').prop('readonly', true);
            $('#btnSave').prop('disabled', true);
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
            $(this).prop('disabled', true);
            $('.editable').prop('disabled', true);
            $('.resetP').prop('disabled', false);
            $('#btnSave').prop('disabled', false);
            $('#btnDelete').prop('disabled', true);
            $('#actionType').val('R');
        });
    }


    return {
        save: save,
        edit: edit,
        deleteU: deleteU,
        selectRow: selectRow,
        getUserList: getUserList,
        resetPassword: resetPassword
    };
})();

$(document).ready(function () {
    userSetup.save();
    userSetup.edit();
    userSetup.deleteU();
    userSetup.selectRow();
    userSetup.getUserList();
    userSetup.resetPassword();

    $('#btnReset').on('click', function () {
        location.reload(true);
    });
});

