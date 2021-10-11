/**
 * Created by RMA on 3/25/2020.
 */

cardSetup = (function () {
    "use strict";
    var formId = $('#cardSetup');

    function _baseURL() {
        return nesGlobal.baseURL;
    }

    function save() {
        $(formId).on('click', '#btnSave', function () {
            $(formId).validate({
                submitHandler: function (form) {
                    $.ajax({
                        url: _baseURL() + 'cardSetup/save',
                        type: 'POST',
                        data: $(formId).serializeArray(),
                        success: function (res) {
                            if (res.status == 1) {
                                successMsg(res.text, _baseURL()+ 'cardSetup');
                            } else {
                                warningMsg(res.text);
                            }
                        }
                    });
                }
            });
        })
    }

    function getCardList(){
        $.ajax({
            url: _baseURL() + 'cardSetup/getCardList',
            type: 'GET',
            success: function (res) {
                var row = "";
                var stat = "";
                for (var i in res){
                    row = row + '<tr>'+
                        '<td></td>' +
                        '<td>' + (res[i].card_name) + '</td>' +
                        '<td>' + (res[i].card_type) + '</td>' +
                        '<td>' + (res[i].bank) + '</td>' +
                        '<td>' + (res[i].statusName) + '</td>' +
                        '<td class="d-none" id="satus">' + (res[i].status) + '</td>' +
                        '<td hidden>' + (res[i].id) + '</td>' +
                        '</tr>'
                }
                var cardTable = $('#groupTableId');
                cardTable.find('tbody').empty().prepend(row);
            }
        });
    }

    function selectRow() {
        $('#groupTableId').find(' tbody').on('click', 'tr', function () {
            var card_name = $(this).find('td:nth-child(2)').text();
            var card_type = $(this).find('td:nth-child(3)').text();
            var bank = $(this).find('td:nth-child(4)').text();
            var status = $(this).find('td:nth-child(6)').text();
            var id = $(this).find('td:nth-child(7)').text();
            var data = {
                "card_name": card_name,
                "card_type": card_type,
                "bank": bank,
                "status": status,
                "id": id,
            };
            populate(data);
            $(formId).find('.editable').prop('disabled', true);
            $(formId).find('.resetP').prop('disabled', true);
            $('#userName').prop('readonly', true);
            $('#btnSave').prop('disabled', true);
            $('#btnEdit').prop('disabled', false);
            $('#btnDelete').prop('disabled', false);
        });
    }

    function deleteC() {
        $(formId).on('click', '#btnDelete', function (e) {
            var id = $('#id').val();
            alert(id);
            $.ajax({
                url: _baseURL() + 'cardSetup/delete',
                type: 'POST',
                data: $(formId).serializeArray(),
                success: function (res) {
                    if (res.status == 1) {
                        successMsg(res.text, _baseURL());
                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        });
    }

    return {
        save: save,
        getCardList: getCardList,
        selectRow : selectRow,
        deleteC : deleteC
    };
})();

$(document).ready(function () {
    cardSetup.save();
    cardSetup.getCardList();
    cardSetup.selectRow();
    cardSetup.deleteC();

});