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
        edit();
        paperUpload();
        btnEditClick();
        deleteTitle();
    }

    function save() {
        $(formId).on('click', '#btnSave', function (e) {
             $(formId).validate({
                 submitHandler: function (form) {
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
                 }
             });
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
                        let status = "";
                        let statusId = res[i].status ;
                        let action = "";
                        if (statusId == 'T') {
                            status = "<span style='color:darkred;font-weight: bold;'>"+res[i].status_name+"<button class='re-propose-btn'>Re Propose</button></span>"
                        }else if(statusId == 'S' || statusId == 'O'){
                            status = res[i].status_name;
                            action = '<button type="button" class="title-edit" title="Click to edit title"> <i class="fas fa-edit"></i></button>';
                            //action = action + '<button type="button" class="paper-upload" title="Click to upload the research paper"> <i class="fas fa-upload"></i></button>';
                        }else{
                            status = res[i].status_name;

                        }
                        row = row + '<tr>' +
                            '<td><input type="hidden" class="id" value="'+res[i].research_topic_id+'"/>' +
                            '<input type="hidden" class="status" value="'+res[i].status+'"> </td>' +
                            '<td class="month">' + (res[i].research_month) + '</td>' +
                            '<td class="objectives">' + isNull(res[i].objectives) + '</td>' +
                            '<td class="title">' + (res[i].research_topic) + '</td>' +
                            '<td>' + status + '</td>' +
                            '<td>' + isNull(res[i].remarks) + '</td>' +
                            '<td>' + isNull(res[i].createdBy) + '</td>' +
                            '<td>' + (formatDate(res[i].createdDate)) + '</td>' +
                            '<td>' + action +'</td>' +
                            '</tr>'
                    }
                    let tbl = $('#approved_topic_list');
                    tbl.find('tbody').empty().prepend(row);
                }
            });
        });

    }

    function edit(){
        $('body').on('click','.title-edit',function(){
            let title_id = $(this).closest('tr').find('.id').val();
            let month = $(this).closest('tr').find('.month').text();
            let objectives = $(this).closest('tr').find('.objectives').text();
            let title = $(this).closest('tr').find('.title').text();

            $('#research_topic_id').val(title_id);
            $('#actionType').val('M');
            $('#research_month').val(month).prop('readonly',true);
            $('#objectives').val(objectives).prop('readonly',true);
            $('#research_topic').val(title).prop('readonly',true);
            $('#btnEdit').prop('disabled',false);
            $('#btnSave').prop('disabled',true);
            $('#btnDelete').prop('disabled',false);
            $('#form-tab').tab('show');
        });
    }

    function btnEditClick(){
        $('#btnEdit').on('click',function(){
            $('#research_month').prop('readonly',false);
            $('#objectives').prop('readonly',false);
            $('#research_topic').prop('readonly',false);
            $('#btnSave').prop('disabled',false)
        });
    }

    function deleteTitle(){
        $('body').on('click','#btnDelete',function (e) {
            e.preventDefault();

            swal({
                title: "Confirmation",
                text: "Are you sure to delete the research title",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: '#DD6B55',
                confirmButtonText: 'Yes',
                cancelButtonText: "Cancel",
                closeOnConfirm: false,
                closeOnCancel: true
            }, function (isConfirm) {
                if(isConfirm){
                    $.ajax({
                        url: _baseURL() + '/delete',
                        type: 'POST',
                        data: {title_id: $('#research_topic_id').val()},
                        success: function (res) {
                            if (res.status == 1) {
                                successMsg(res.text, _baseURL());
                            } else {
                                warningMsg(res.text);
                            }
                        }
                    })
                }
            });
        });
    }

    function paperUpload(){
        $('body').on('click','.paper-upload',function(){
            alert('lslsl');
        });
    }



    function rePropose(){
        $('body').on('click','.re-propose-btn',function (e) {

            $('#actionType').val('R');
            $('#research_topic_id').val($(this).closest('tr').find('.id').val());
            $('#research_month').val($(this).closest('tr').find('.month').text());
            $('#objectives').val($(this).closest('tr').find('.objectives').text());
            $('#research_topic').val($(this).closest('tr').find('.title').text());
            $('#status').html('<option value="O">Re-Submit</option>');

            $('#form-tab').tab('show');
        });
    }

    function reset(){
        $('#actionType').val('C');
        $('#research_month').val('');
        $('#research_topic').val('');
        $('#status').html($('<option value="S">Submit</option>'));
    }


    return {
        init: init
    };
})();

$(document).ready(function () {
    researchTopic.init();
});