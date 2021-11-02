/**
 * Created by nimayoezer on 26/09/2018.
 */

changePassword = (function () {
    "use strict";
    var formID = "#changePasswordForm";
    var isSubmitted = false;

    function _baseURL() {
        return globalJs.baseURL + "changePassword/"
    }

    function Init() {
        matchPassword();
    }

    function save() {
        $(formID).on('click', '#btnSave', function (e) {
            $(formID).validate({
                submitHandler: function (form) {
                    //alert(JSON.stringify($(form).serializeArray()));
                    $.ajax({
                        url: _baseURL() + 'save',
                        type: 'POST',
                        data: $(form).serializeArray(),
                        success: function (res) {
                            if (res.status == 1) {
                                successMsg(res.text, globalJs.baseURL+'logout');
                            } else {
                                warningMsg(res.text);
                            }
                        }
                    });
                }
            });
        })
    }

    function matchPassword() {
        $(formID).on('blur', '#password', function (e) {
            if(!$(this).val()){
                return;
            }
            $.ajax({
                url: _baseURL() + 'matchPassword',
                type: 'GET',
                data: {password: $(this).val()},
                success: function (res) {
                    if (res.status == 1) {
                        $('.editable').prop('disabled', false);
                    } else {
                        warningMsg(res.text);
                        $('.editable').prop('disabled', true);
                    }
                }
            });
        });
    }


    return {
        Init: Init,
        save: save

    };
})();

$(document).ready(function () {
    changePassword.Init();
    changePassword.save();
});

