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
                    var row = "";
                    for (var i in res) {
                        //nesGlobal.viewOrDownloadFile(res[i].filePath);
                        let color = "";
                        if (res[i].status == 'T') {
                            color = "color:red";
                        } else if (res[i].status == 'S') {
                            color = "color:#5A1B0E";
                        } else if (res[i].status == 'R') {
                            color = "color:green";
                        }
                        color = color + ";font-weight:bold;";
                        row = row + '<tr>' +
                            '<td></td>' +
                            '<td>' + (res[i].research_number) + '</td>' +
                            '<td>' + (res[i].research_month) + '</td>' +
                            '<td>' + (res[i].researchTopic) + '</td>' +
                            '<td>' + (res[i].wordCount) + '</td>' +
                            '<td>' + globalJs.viewOrDownloadFile(res[i].filePath) + '</td>' +
                            '<td> <span style="' + color + '">' + (res[i].statusName) + '</span></td>' +
                            '<td>' + isNull(res[i].reviewer_comment) + '</td>' +
                            '<td>' + isNull(formatDate(res[i].createdDate)) + '</td>' +
                            '</tr>'
                    }
                    $('#researchListTbl').find('tbody').empty().prepend(row);
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


    return {
        init: init
    };
})();

$(document).ready(function () {
    research.init();
});