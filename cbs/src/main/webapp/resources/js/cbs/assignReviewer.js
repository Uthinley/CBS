/**
 * Created by RMA on 3/25/2020.
 */

reviewer = (function () {
    "use strict";
    var formId = $('#addResearch');

    function _baseURL() {
        return nesGlobal.baseURL;
    }
    function getResearchListByMonth(){
        $('.search').on('click', '#searchBtn', function () {
            alert($('#monthId').val());
            $.ajax({
                url: _baseURL() + 'reviewer/getReviewerList',
                type: 'GET',
                success: function (res) {
                    var row = "";
                    for (var i in res){
                        //nesGlobal.viewOrDownloadFile(res[i].filePath);
                        row = row + '<tr>'+
                            '<td></td>' +
                            '<td>' + (res[i].researchTopic) + '</td>' +
                            // '<td> <a href="' + (res[i].filePath) + '">file</a></td>' +
                            '<td>'+nesGlobal.viewOrDownloadFile(res[i].filePath)+'</td>' +
                            '<td>' + (res[i].wordCount) + '</td>' +
                            '<td>' + (res[i].status) + '</td>' +
                            '<td>' + (formatDate(res[i].createdDate)) + '</td>' +
                            '<td class="d-none" id="status">' + (res[i].wordCount) + '</td>' +
                            '<td hidden>' + (res[i].researchId) + '</td>' +
                            '</tr>'
                    }
                    var cardTable = $('#researchListTbl');
                    cardTable.find('tbody').empty().prepend(row);
                }
            });
        });
    }

    function wordCountOnFileSelect(){
        $('#file').on('change', function () {
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

    return {
        getResearchListByMonth: getResearchListByMonth
    };
})();

$(document).ready(function () {
    reviewer.getResearchListByMonth();
});