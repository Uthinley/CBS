/**
 * Created by  UThinley on 24/9/2021.
 */
groupSetup = (function () {
    "use strict";
    var formID = "#groupSetup";

    function _baseURL() {
        return globalJs.baseURL + "groupSetup/"
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
                                warningMsg(res.text);
                            }
                        }
                    });
                }
            });
        })
    }

    function deleteGroup() {
        $('#btnDelete').on('click',function () {
            var groupId = $('#groupId').val();
            alert(groupId);
            $.ajax({
                url: _baseURL() + 'delete',
                type: 'POST',
                data: {groupId: groupId},
                success: function (res) {
                    if (res.status == 1) {
                        successMsg(res.text, _baseURL());
                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        })
    }

    function selectRow() {
        $('#groupTableId').find(' tbody').on('click', 'tr', function () {

            var groupId = $(this).find('#groupIds').text();
            $('#groupId').val(groupId);
            var groupName = $(this).find('#groupNames').text();
            var groupStatus = $(this).find('#groupStatusTbl').text();
            $('#groupStatus').val(groupStatus);
            var data = {
                //"groupId ": groupId ,
                "groupName": groupName,
                "groupStatus": groupStatus
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

    function isGroupExist() {
        $('#groupName').on('blur', function () {
            var groupName = $(this).val();
            if (groupName !== "") {
                $.ajax({
                    url: _baseURL() + '/isGroupExist',
                    type: 'GET',
                    data: {groupName: groupName},
                    success: function (res) {
                        if (res) {
                            warningMsg("Group name already exists.");
                            $('#groupName').val('');
                        }
                    }
                });
            }
        });
    }

    function getGroupList() {
        $.ajax({
            url: _baseURL() + 'getGroupList',
            type: 'GET',
            success: function (res) {
                var row = '';
                for (var i in res) {
                    row = row + '<tr align="left">' +
                        '<td></td>' +
                        '<td id="groupIds">' + res[i].groupID + '</td>' +
                        '<td id="groupNames">' + res[i].groupName + '</td>' +
                        '<td class="d-none" id="groupStatusTbl">' + res[i].groupStatus + '</td>'+
                        '<td>' + res[i].groupStatusName + '</td>'
                }
                var groupListTable = $('#groupTableId');
                groupListTable.dataTable().fnDestroy();
                groupListTable.find('tbody').empty().prepend(row);
                groupListTable.dataTable();
            }
        });
    }

    return {
        save: save,
        edit: edit,
        deleteGroup: deleteGroup,
        isGroupExist: isGroupExist,
        getGroupList: getGroupList,
        selectRow: selectRow
    };
})();

$(document).ready(function () {
    groupSetup.edit();
    groupSetup.save();
    groupSetup.deleteGroup();
    groupSetup.getGroupList();
    groupSetup.isGroupExist();
    groupSetup.selectRow();

    $('#btnReset').on('click', function () {
        location.reload(true);
    });
});
