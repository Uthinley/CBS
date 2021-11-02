/**
 * Created by RMA on 3/25/2020.
 */

titleApproval = (function () {
    "use strict";
    let formId = $('#titleApprovalForm');

    function _baseURL() {
        return globalJs.baseURL+"titleApprove";
    }

    function init(){
        approve();
        reject();
        gTopicList();
    }

    function approve(){
        $(formId).on('click', '#btnApprove', function (e) {
            save('A');
        });
    }


    function reject(){
        $(formId).on('click', '#btnReturn', function (e) {
            save('T');
        })
    }

    function save(action) {
        if($('#research_title_list >tbody > tr').length < 1){
            warningMsg("Nothing to approve or reject");
            return;
        }
        let data = [];
        $("input:checkbox[class=check-for-action]:checked").each(function(){
            data.push({name:"research_title_ids",value:$(this).val()});
        });
        data.push({name:"action",value:action});
        data.push({name:"remarks",value:$('#remarks').val()});
        alert(JSON.stringify(data));
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
    }

    function gTopicList(){
        $('#btnSearch').on('click', function (e) {
                $('#searchForm').validate({
                    submitHandler: function (form) {
                        let data = $(form).serializeArray();
                        $.ajax({
                            url: _baseURL() + '/gTopicList',
                            type: 'GET',
                            data: data,
                            success: function (res) {
                                let row = "";
                                for (let i in res) {
                                    let color = "";
                                    let action="";
                                    if (res[i].status == 'T') {
                                        action = '<input type="checkbox" disabled title="Returned/rejected!!!"/>';
                                        color = "color:red";
                                    } else if (res[i].status == 'S') {
                                        action = '<input type="checkbox" class="check-for-action" value="' + res[i].research_topic_id + '"/>';
                                        color = "color:#5A1B0E";
                                    } else if (res[i].status == 'A') {
                                        action = '<input type="checkbox" disabled title="Already approved!!!"/>';
                                        color = "color:green";
                                    }else if (res[i].status == 'O') {
                                        action = '<input type="checkbox" class="check-for-action" value="' + res[i].research_topic_id + '"/>';
                                        color = "color:#5AFB0E";
                                    }
                                    color = color + ";font-weight:bold;";
                                    row = row + '<tr>' +
                                        '<td><input type="hidden" class="id" value="' + res[i].research_topic_id + '"/> </td>' +
                                        '<td class="month">' + (res[i].research_month) + '</td>' +
                                        '<td class="title">' + (res[i].research_topic) + '</td>' +
                                        '<td><span style="' + color + '">' + (res[i].status_name) + '</span></td>' +
                                        '<td>' + isNull(res[i].remarks) + '</td>' +
                                        '<td>' + isNull(res[i].createdBy) + '</td>' +
                                        '<td>' + (formatDate(res[i].createdDate)) + '</td>' +
                                        '<td>'+action+'</td>'+
                                        '</tr>'
                                }
                                let tbl = $('#research_title_list');
                                tbl.find('tbody').empty().prepend(row);
                            }
                        });
                    }
                });
        });

    }

    function reset(){
        $('#actionType').val('C');
        $('#research_month').val('');
        $('#research_topic').val('');
        $('#status').html($('<option value="P">Propose</option>'));
    }


    return {
        init: init
    };
})();

$(document).ready(function () {
    titleApproval.init();
});