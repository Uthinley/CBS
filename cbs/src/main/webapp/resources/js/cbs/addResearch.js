/**
 * Created by RMA on 3/25/2020.
 */

research = (function () {
    "use strict";
    let formId = $('#addResearch');

    function _baseURL() {
        return globalJs.baseURL+'research';
    }

    function init(){
        save();
        getResearchList();
        getTitleForMonth();
        wordCountOnFileSelect();
        findResearch();
        action_btn();
        editR();
        deleteR();

        let url      = window.location.href;
        if(url.endsWith("#researchListTbl")){
            $('#research-list-tab').click();
        }
    }

    function save() {
        $(formId).on('click', '#btnSave', function (e) {
            $(formId).validate({
                submitHandler: function (form) {
                    let data = new FormData($(formId)[0]);
                    data.append('research_paper', $('input[type=file]')[0].files[0]);
                    // data.append('supporting_documents', $('input[type=file]')[0].files[1]);
                    $.ajax({
                        url: _baseURL() + '/save',
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
        });
    }

    function getResearchList(){
        $('#research-list-tab').on('click', function (e) {
            $.ajax({
                url: _baseURL() + '/getResearchList',
                type: 'GET',
                success: function (res) {
                    let row = "";
                    for (let i in res) {
                        //nesGlobal.viewOrDownloadFile(res[i].filePath);
                        let color = "";
                        let action = "<button class='action-btn'><i class='fas fa-user-edit'></i></button>";
                        if (res[i].status == 'T') {
                            color = "color:red";
                        } else if (res[i].status == 'S') {
                            color = "color:#5A1B0E";
                        } else if (res[i].status == 'R') {
                            action ="<button class='action-btn' disabled title='Already reviewed. Cannot update or delete'><i class='fas fa-user-edit'></i></button>";
                            color = "color:green";
                        }else if (res[i].status == 'U') {
                            color = "color:gray";
                        }
                        color = color + ";font-weight:bold;";
                        row = row + '<tr>' +
                            '<td><input type="hidden" class="id" value="'+res[i].researchId+'"/></td>' +
                            '<td>' + (res[i].employee_id) + '</td>' +
                            '<td>' + (res[i].createdBy) + '</td>' +
                            '<td>' + (res[i].position_title) + '</td>' +
                            '<td class="research_number">' + (res[i].research_number) + '</td>' +
                            '<td>' + (res[i].research_month) + '</td>' +
                            '<td>' + (res[i].researchTopic) + '</td>' +
                            '<td>' + (res[i].wordCount) + '</td>' +
                            '<td>' + globalJs.viewOrDownloadFile(res[i].filePath+'/'+res[i].research_paper_name) + '</td>' +
                            '<td> <span style="' + color + '">' + (res[i].statusName) + '</span></td>' +
                            '<td>' + isNull(res[i].reviewer_comment) + '</td>' +
                            '<td>' + isNull(formatDate(res[i].createdDate)) + '</td>' +
                            '<td>'+action+'</td>' +
                            '</tr>'
                    }

                    let tbl = $('#researchListTbl');
                    tbl.dataTable().fnDestroy();
                    tbl.find('tbody').empty().prepend(row);

                    tbl.removeAttr('width').DataTable( {
                        scrollY: "500px",
                        scrollX:        true,
                        scrollCollapse: true,
                        paging: false,
                        columnDefs: [
                            { width: 200, targets: 6 }
                        ],

                        dom: 'Bfrtip',
                        buttons: [
                            {
                                extend: 'csvHtml5',
                                title: 'Research Papers'
                            },
                            {
                                extend: 'excelHtml5',
                                title: 'Research Papers'
                            },
                            {
                                extend: 'pdfHtml5',
                                title: 'Research Papers'
                            }
                        ],
                        fixedColumns: true
                    } );
                }
            });
        });

    }

    function getTitleForMonth(){
        $('#research_month').on('change',function(){
            $.ajax({
                url: _baseURL() + '/getTitleForMonth',
                type: 'GET',
                data: {research_month:$(this).val()},
                success: function(res){
                    if(jQuery.isEmptyObject(res)){
                        warningMsg("Propose the research title and get approved from the approver for the selected month.")
                        $('#researchTopic').val('');
                        return;
                    }
                    if(res.status != 'A'){
                        warningMsg("Your research title is not approved yet. Please wait until it is approved or contact the approver.")
                        $('#btnSave').prop('disabled',true);
                        $('#researchTopic').val('');
                        return;
                    }
                    $('#researchTopic').text(res.research_topic);
                    $('#btnSave').prop('disabled',false);
                }
            })
        });
    }

    function wordCountOnFileSelect(){

        $('#research_paper').bind('change', function () {
            let filename = $(this).val().split('\\').pop();

            if(!isValidFile(filename.split('.').pop(),['docx'])){
                $(this).val('');
                return;
            }
            if(!check_file_size(this.files[0].size)){
                $(this).val('');
                return;
            }
            let data = new FormData();
            data.append('file', $(this)[0].files[0]);
            $.ajax({
                url: _baseURL() + '/getWordCount',
                type: 'POST',
                data: data,
                enctype: 'multipart/form-data',
                contentType: false,
                processData: false,
                success: function(res){
                    $('.word_count').text(res);
                }
            })
        });
    }

    //to get a research by research number
    function findResearch(){
        $('#research_number').on('change',function(e){
            e.preventDefault();
            let research_number = $(this).val();
            if(!research_number){
               return;
            }
            $.ajax({
                url: _baseURL() + '/findResearch',
                type: 'GET',
                data: {research_number:research_number},
                success: function(res){
                    populate(res);
                    $('#btnSave').prop('disabled',true);
                    $('#btnEdit').prop('disabled',false);
                    $('#btnDelete').prop('disabled',false);
                    $('#researchTopic').prop('disabled',true);
                    $('#key_words').prop('disabled',true);
                    $('#research_abstract').prop('disabled',true);
                    $('#research_month').prop('disabled',true);
                    $('#research_paper').prop('disabled',true);
                    $('#research_number').prop('readonly',false);
                }
            })
        });
    }


    function action_btn(){
        $('body').on('click','.action-btn',function (e) {
            $('#research_number').val($(this).closest('tr').find('.research_number').text()).change();
            $('#form-tab').tab('show');
        });
    }

    function editR(){
        $('body').on('click','#btnEdit',function (e) {
            $('#researchTopic').prop('disabled',false);
            $('#key_words').prop('disabled',false);
            $('#research_abstract').prop('disabled',false);
            $('#research_month').prop('disabled',false);
            $('#research_paper').prop('disabled',false);
            $('#btnSave').prop('disabled',false);
            $('#btnEdit').prop('disabled',true);
            $('#actionType').val('M');
        });
    }

    function deleteR(){
        $('body').on('click','#btnDelete',function (e) {
            e.preventDefault();
            $.ajax({
                url: _baseURL() + '/delete',
                type: 'POST',
                data: {research_number: $('#research_number').val(),research_id: $('#researchId').val()},
                success: function (res) {
                    if (res.status == 1) {
                        successMsg(res.text, _baseURL());
                    } else {
                        warningMsg(res.text);
                    }
                }
            })
        });
    }


    return {
        init: init
    };
})();

$(document).ready(function () {
    research.init();
});