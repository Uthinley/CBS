/**
 * Created by Dechen Wangdi on 16/12/2019.
 */

permissionSetup = (function () {
    "use strict";
    var formID = "#permissionSetupForm";
    var isSubmitted = false;

    function _baseURL() {
        return nesGlobal.baseURL + "permissionSetup/"
    }

    function save() {
        $(formID).on('click', '#btnSave', function (e) {
            $(formID).validate({
                submitHandler: function (form) {
                    nesGlobal.formIndexing($("#permissionListTbl").find('tbody'));
                    $.ajax({
                        url: _baseURL() + 'save',
                        type: 'POST',
                        data: $(form).serializeArray(),
                        success: function (res) {
                            if (res.status == 1) {
                                successMsg(res.text);
                                //getUserList();
                            } else {
                                errorMsg(res.text);
                            }
                        }
                    });
                }
            });
        })
    }

    var screenList = $('#screenList');

    //TODO need to check
    function checkCheckbox() {
        $('#permissionListTbl').find('tbody').on('change', 'input[type="checkbox"]', function () {

            var tr = $(this).closest('tr');
            $('#yourid').attr('name');
            var isDelete = tr.find('#isDelete').attr('name');
            var isSave = tr.find('#isSave').is(":checked");
            var isEdit = tr.find('#isEdit').is(":checked");
            var isView = tr.find('#isView').is(":checked");

            //alert(isView + ", " + isEdit + ", " + isSave + ", " + isDelete);
            if(isDelete){
                $(this).closest('tr').find(':checkbox').prop('checked', this.checked);
            }else if(isSave){
                tr.find('#isSave').is(":checked");
                tr.find('#isEdit').is(":checked");
                tr.find('#isView').is(":checked");
            }else if(isEdit){
                tr.find('#isEdit').is(":checked");
                tr.find('#isView').is(":checked");
            }else{
                tr.find('#isView').is(":checked");
            }


        });


        /* $('.selectall').click(function () {
         $('#friendslist').find(':checkbox').attr('checked', this.checked);
         });
         */
    }

    function getPermissionList() {
        $(formID).on('change', '#groupId', function (e) {
            var permissionListTbl = $("#permissionListTbl");
            var groupId = $(this).val();
            if (!groupId) {
                screenList.find("option").prop('hidden', false).prop('disabled', false);
                permissionListTbl.find('tbody').empty();
                return;
            }
            $.ajax({
                url: _baseURL() + 'getPermissionList',
                data: {groupId: groupId},
                type: 'GET',
                success: function (res) {
                    screenList.find("option").prop('hidden', false).prop('disabled', false);

                    var row = '';
                    for (var i in res) {
                        var isView = (res[i].isView == 'Y') ? 'checked' : '';
                        var isEdit = (res[i].isEdit == 'Y') ? 'checked' : '';
                        var isSave = (res[i].isSave == 'Y') ? 'checked' : '';
                        var isDelete = (res[i].isDelete == 'Y') ? 'checked' : '';
                        row = row + '<tr align="middle">' +
                            '<td></td>' +
                            '<td>' +
                            '<input type="hidden" name="permissionList[0].permissionId" class="form-control input-sm" value="' + res[i].permissionId + '" readonly/>' +
                            '<input type="hidden" name="permissionList[0].groupId" class="form-control input-sm" value="' + $('#groupId').val() + '" readonly/>' +
                            '<input type="text" name="permissionList[0].screenId" class="form-control input-sm screenId" value="' + res[i].screenId + '" readonly/>' +
                            '</td>' +
                            '<td><input type="text" class="form-control input-sm" value="' + res[i].screenName + '" readonly></td>' +
                            '<td><input type="checkbox" id="isView" name="permissionList[0].isView" value="Y" ' + isView + '></td>' +
                            '<td><input type="checkbox" id="isEdit" name="permissionList[0].isEdit" value="Y" ' + isEdit + '></td>' +
                            '<td><input type="checkbox" id="isSave" name="permissionList[0].isSave" value="Y" ' + isSave + '></td>' +
                            '<td><input type="checkbox" id="isDelete" name="permissionList[0].isDelete" value="Y"' + isDelete + '></td>' +
                            '<td><input type="button" class="btn-primary removeP" value="-"></td>' +
                            '</tr>';
                        screenList.find("option[value=" + res[i].screenId + "]").prop('hidden', true).prop('disabled', true);
                    }

                    permissionListTbl.find('tbody').empty().prepend(row);
                    nesGlobal.formIndexing(permissionListTbl.find('tbody'));

                }
            });
        });
    }

    /**
     * To get screen list by screen type.
     */
    function getScreenList() {
        $('#screenType').on('change', function () {
            var screenType = $(this).val();

            $.ajax({
                url: _baseURL() + 'getScreenList',
                data: {screenType: screenType},
                type: 'GET',
                success: function (res) {
                    if (res != null) {
                        $("#screenList option").remove();
                        nesGlobal.loadDropDown($('#screenList'), res, 'string');
                    }
                }
            });
        });
    }

    function addPermission() {
        $(formID).on('click', '#addP', function (e) {
            e.preventDefault();
            var groupId = $('#groupId').val();

            var row = '';
            screenList.find('option:selected').each(function () {
                row = row + '<tr align="middle">' +
                    '<td></td>' +
                    '<td>' +
                    '<input type="hidden" name="permissionList[0].groupId" class="form-control input-sm" value="' + groupId + '" readonly/>' +
                    '<input type="text" name="permissionList[0].screenId" class="form-control input-sm screenId" value="' + $(this).val() + '" readonly/>' +
                    '</td>' +
                    '<td><input type="text" class="form-control input-sm" value="' + $(this).text() + '" readonly></td>' +
                    '<td><input type="checkbox" name="permissionList[0].isView" value="Y" ></td>' +
                    '<td><input type="checkbox" name="permissionList[0].isEdit" value="Y" ></td>' +
                    '<td><input type="checkbox" name="permissionList[0].isSave" value="Y" ></td>' +
                    '<td><input type="checkbox" name="permissionList[0].isDelete" value="Y"></td>' +
                    '<td><input type="button" class="btn-primary removeP" value="-"></td>' +
                    '</tr>';
                $(this).closest('option').prop('hidden', true).prop('disabled', true);
            });
            var permissionListTbl = $("#permissionListTbl");
            permissionListTbl.find('tbody').prepend(row);
            nesGlobal.formIndexing(permissionListTbl.find('tbody'));
        });
    }

    function removePermission() {
        $(formID).on('click', '.removeP', function (e) {
            e.preventDefault();
            var tr = $(this).parent().parent();
            screenList.find("option[value=" + tr.find('.screenId').val() + "]").prop('hidden', false).prop('disabled', false);
            tr.remove();
        });
    }

    return {
        save: save,
        addPermission: addPermission,
        removePermission: removePermission,
        getPermissionList: getPermissionList,
        getScreenList: getScreenList,
        checkCheckbox: checkCheckbox

    };
})();

$(document).ready(function () {
    permissionSetup.save();
    permissionSetup.addPermission();
    permissionSetup.removePermission();
    permissionSetup.getPermissionList();
    permissionSetup.getScreenList();
    permissionSetup.checkCheckbox();
});


