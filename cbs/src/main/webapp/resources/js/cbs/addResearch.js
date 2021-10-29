/**
 * Created by RMA on 3/25/2020.
 */

research = (function () {
    "use strict";
    var formId = $('#addResearch');

    function _baseURL() {
        return globalJs.baseURL;
    }

    function wordCountOnFileSelect(){
        $('#research_paper').on('change', function () {
            let data = new FormData();
            data.append('file', $(this)[0].files[0]);
            $.ajax({
                url: _baseURL() + 'research/getWordCount',
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

    function save() {
        $(formId).on('click', '#btnSave', function (e) {
            e.preventDefault();
            // $(formId).validate({
            //     submitHandler: function (form) {
            var data = new FormData($(formId)[0]);
            data.append('research_paper', $('input[type=file]')[0].files[0]);
            data.append('supporting_documents', $('input[type=file]')[0].files[1]);
                    $.ajax({
                        url: _baseURL() + 'research/save',
                        type: 'POST',
                        data: data,
                        enctype: 'multipart/form-data',
                        contentType: false,
                        processData: false,
                        success: function (res) {
                            if (res.status == 1) {
                                successMsg(res.text, _baseURL()+'research');
                            } else {
                                warningMsg(res.text);
                            }
                        }
                    });
                // }
            // });
        })
    }

    function getResearchList(){
        $.ajax({
            url: _baseURL() + 'research/getResearchList',
            type: 'GET',
            success: function (res) {
                var row = "";
                for (var i in res){
                    //nesGlobal.viewOrDownloadFile(res[i].filePath);
                    let color = "";
                    if(res[i].status == '3'){
                        color = "color: red;";
                    }
                    row = row + '<tr>'+
                        '<td></td>' +
                        '<td>' + (res[i].research_number) + '</td>' +
                        '<td>' + (res[i].researchTopic) + '</td>' +
                        // '<td> <a href="' + (res[i].filePath) + '">file</a></td>' +
                        '<td>'+globalJs.viewOrDownloadFile(res[i].filePath)+'</td>' +
                        '<td>' + (res[i].paper_version) + '</td>' +
                        '<td style="'+color+'">' + (res[i].statusName) + '</td>' +
                        '<td>' + (formatDate(res[i].createdDate)) + '</td>' +
                        '<td class="d-none" id="status">' + (res[i].wordCount) + '</td>' +
                        '<td hidden>' + (res[i].researchId) + '</td>' +
                        '</tr>'
                }
                var cardTable = $('#researchListTbl');
                cardTable.find('tbody').empty().prepend(row);
            }
        });

    }


    return {
        save: save,
        getResearchList: getResearchList,
        wordCountOnFileSelect:wordCountOnFileSelect
    };
})();

$(document).ready(function () {
    research.save();
    research.getResearchList();
    research.wordCountOnFileSelect();
});