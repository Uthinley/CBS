/**
 * Created by RMA on 3/25/2020.
 */

transaction = (function () {
    "use strict";

    var formId = $('#bankDeposit');

    function _baseURL() {
        return nesGlobal.baseURL;
    }

    function getResearchList(){
        $.ajax({
            url: _baseURL() + 'research/getResearchList',
            type: 'GET',
            success: function (res) {
                var row = "";
                for (var i in res){
                    row = row + '<tr>'+
                        '<td></td>' +
                        '<td>' + (res[i].researchTopic) + '</td>' +
                        // '<td> <a href="' + (res[i].filePath) + '">file</a></td>' +
                        '<td> ' + (res[i].filePath) + '</td>' +
                        '<td>' + (res[i].wordCount) + '</td>' +
                        '<td>' + (res[i].status) + '</td>' +
                        '<td>' + (formatDate(res[i].createdDate)) + '</td>' +
                        // '<td class="d-none" id="status">' + (res[i].wordCount) + '</td>' +
                        // '<td hidden>' + (res[i].researchId) + '</td>' +
                        '</tr>'
                }
                var tableID = $('#researchTbl');
                tableID.dataTable().fnDestroy();
                tableID.find('tbody').empty().prepend(row);
                createDataTableWithButtons(tableID);
                // tableID.dataTable();
            }
        });
    }
    function formatDate(d){
        var date = new Date(d);
        var year = date.getFullYear();
        var month = date.getMonth()+1;
        var day = date.getDate();

        if (day < 10) {
            var dt = '0' + dt;
        }
        if (month < 10) {
            month = '0' + month;
        }
        var finalDate;
        return finalDate = `${day}/${month}/${year}`;
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
        getResearchList: getResearchList,
        formatDate: formatDate
    };
})();

$(document).ready(function () {
    transaction.save();
    transaction.getResearchList();
    transaction.formatDate();
});