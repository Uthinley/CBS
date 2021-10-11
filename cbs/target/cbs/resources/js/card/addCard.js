/**
 * Created by RMA on 3/25/2020.
 */

card = (function () {
    "use strict";
    var formId1 = $('#searchCard');
    var formId = $('#addCardInfo');

    function _baseURL() {
        return nesGlobal.baseURL;
    }

    function save() {
        $(formId).on('click', '#btnSave', function () {
            $(formId).validate({
                submitHandler: function (form) {
                    $.ajax({
                        url: _baseURL() + 'card/save',
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
                }
            });
        })
    }

    function search(){
        $(formId1).on('click', '#searchBtn', function () {
            $(formId1).validate({
                submitHandler: function (form) {
                    var cid = $('#cid').val();
                    $.ajax({
                        url: _baseURL() + 'card/search',
                        type: 'GET',
                        data: {
                            cid : cid
                        },
                        success: function (res) {
                            var row = "";
                            var rowINR="";
                            var name = "";
                            for (var i in res){
                                if(res[i].currency == "USD" ){
                                    row = row + '<tr>'+
                                        '<td>' + (parseInt(i)+1) + '</td>' +
                                        '<td>' + (res[i].cardType) + '</td>' +
                                        '<td>' + (res[i].sanctionAmt) + '</td>' +
                                        '</tr>'
                                }else{
                                    rowINR = rowINR + '<tr>'+
                                        '<td>' + (parseInt(i)+1) + '</td>' +
                                        '<td>' + (res[i].cardType) + '</td>' +
                                        '<td>' + (res[i].sanctionAmt) + '</td>' +
                                        '</tr>'
                                }
                                name = res[i].name;
                            }
                            $('#cid1').val(cid);
                            $('#fullname').val(name);
                            var cardTable = $('#cardInfo');
                            cardTable.find('tbody').empty().prepend(row);
                            var cardTable2 = $('#cardInfoINR');
                            cardTable2.find('tbody').empty().prepend(rowINR);
                            // $('result').show();
                            $('.result').removeAttr('hidden');
                        }
                    });
                }
            });
        })
    }

    return {
        save: save,
        search: search
    };
})();

$(document).ready(function () {
    card.search();
    card.save();

});