/**
 * Created by RMA on 3/25/2020.
 */

transaction = (function () {
    "use strict";

    var formId = $('#bankDeposit');

    function _baseURL() {
        return nesGlobal.baseURL;
    }

    function save() {
        $(formId).on('click', '#btnSave', function () {
            $(formId).validate({
                submitHandler: function (form) {
                    var data = new FormData($(form)[0]);
                    data.append('uploadFile', $('input[type=file]')[0].files[0]);
                    $.ajax({
                        url: _baseURL() + 'save',
                        type: 'POST',
                        data: data,
                        enctype: 'multipart/form-data',
                        contentType: false,
                        processData: false,
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

    return {
        save: save,
    };
})();

$(document).ready(function () {
    transaction.save();
});