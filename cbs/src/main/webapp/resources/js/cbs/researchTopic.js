/**
 * Created by RMA on 3/25/2020.
 */

researchTopic = (function () {
    "use strict";
    let formId = $('#researchTopicForm');

    function _baseURL() {
        return globalJs.baseURL+"researchTopic";
    }

    function init(){
        save();
        gTopicList();
        rePropose();
    }

    function save() {
        $(formId).on('click', '#btnSave', function (e) {
            // $(formId).validate({
            //     submitHandler: function (form) {
            let data = $(formId).serializeArray();
                    $.ajax({
                        url: _baseURL() + '/save',
                        type: 'POST',
                        data: data,
                        success: function (res) {
                            if (res.status == 1) {
                                successMsg(res.text, _baseURL());
                            } else {
                                warningMsg(res.text);
                            }
                        }
                    });
                // }
            // });
        })
    }

    function gTopicList(){
        $('#approved-tab').on('click', function (e) {
            $.ajax({
                url: _baseURL() + '/gTopicList',
                type: 'GET',
                success: function (res) {
                    let row = "";
                    for (var i in res) {
                        let color = "";
                        let status = "";
                        if (res[i].status == '3') {
                            color = "color:red";
                            status = "<span style='color:darkred;font-weight: bold;'>"+res[i].status_name+"<button class='re-propose-btn'>Re Propose</button></span>"
                        }else{
                            status = res[i].status_name;
                        }
                        row = row + '<tr>' +
                            '<td></td>' +
                            '<td class="month">' + (res[i].research_month) + '</td>' +
                            '<td class="title">' + (res[i].research_topic) + '</td>' +
                            '<td>' + status + '</td>' +
                            '<td>' + isNull(res[i].remarks) + '</td>' +
                            '<td>' + isNull(res[i].createdBy) + '</td>' +
                            '<td>' + (formatDate(res[i].createdDate)) + '</td>' +
                            '</tr>'
                    }
                    let tbl = $('#approved_topic_list');
                    tbl.find('tbody').empty().prepend(row);
                }
            });
        });

    }

    function rePropose(){
        $('body').on('click','.re-propose-btn',function (e) {
            $('#research_month').val($(this).closest('tr').find('.month').text());
            $('#research_topic').val($(this).closest('tr').find('.title').text());
            $('#status').append($('<option>', {
                value: 'R',
                text: 'Re Propose'
            }));

            $('#form-tab').tab('show');
        });
    }


    return {
        init: init
    };
})();

$(document).ready(function () {
    researchTopic.init();
});