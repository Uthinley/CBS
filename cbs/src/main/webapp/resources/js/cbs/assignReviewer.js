/**
 * Created by RMA on 3/25/2020.
 */

reviewer = (function () {
    "use strict";
    var formId = $('#addResearch');

    function _baseURL() {
        return globalJs.baseURL;
    }
    function getResearchListByMonth(){
        $('.search').on('click', '#searchBtn', function () {
            // alert($('#monthId').val());
            $.ajax({
                url: _baseURL() + 'reviewer/getResearchListForReview',
                type: 'GET',
                data: {
                    month : $('#monthId').val(),
                },
                success: function (res) {
                    var row = "";
                        for (var i in res) {
                            //nesGlobal.viewOrDownloadFile(res[i].filePath);
                            row = row + '<tr>' +
                                '<td></td>' +
                                '<td>' + (res[i].research_number) + '</td>' +
                                '<td>' + (res[i].researchTopic) + '</td>' +
                                '<td>' + (formatDate(res[i].createdDate)) + '</td>' +
                                '<td><a data-toggle="modal" data-target=".bd-example-modal-lg" class="btn btn-danger btn-circle text-white">\n' +
                                '                                       <i class="fas fa-edit"></i>\n' +
                                '                                  </a>' +
                                '</td>' +
                                // '<td> <a href="' + (res[i].filePath) + '">file</a></td>' +
                                // '<td>'+nesGlobal.viewOrDownloadFile(res[i].filePath)+'</td>' +
                                // '<td>' + (res[i].wordCount) + '</td>' +
                                // '<td>' + (res[i].status) + '</td>' +
                                // '<td>' + (formatDate(res[i].createdDate)) + '</td>' +
                                // '<td class="d-none" id="status">' + (res[i].wordCount) + '</td>' +
                                // '<td hidden>' + (res[i].researchId) + '</td>' +
                                '</tr>'
                        }
                        getAssignedResearchList($('#monthId').val());
                        var cardTable = $('#researchListTbl');
                        cardTable.dataTable().fnDestroy();
                        cardTable.find('tbody').empty().prepend(row);
                        $(cardTable).DataTable();
                        // createDataTableWithButtons(cardTable);
                    }
            });
        });
    }

    function assignReviewer(){
        $('.modal-footer').on('click', '#assignBtn', function () {
            $('#assignModal').modal('toggle');
            // var researchNo = $('#rNumber').val();
            // var reviewerId = $('#reviewerName').val();
            // var completionDate= $('#completionDate').val();
            $.ajax({
                url: _baseURL() + 'reviewer/assignReviewer',
                type: 'POST',
                data: {
                    researchNo: $('#rNumber').val(),
                    reviewerId: $('#reviewerName').val(),
                    completionDate :$('#completionDate').val()
                },
                success: function (res) {
                    if (res.status == 1) {
                        // successMsg(res.text, _baseURL()+'reviewer');
                        successMsg(res.text);
                        // getResearchListByMonth();
                        $('#searchBtn').trigger('click');
                        getAssignedResearchList($('#monthId').val());
                        // successMsg(res.text, _baseURL()+'reviewer/updatedList');
                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        });
    }

    function getAssignedResearchList(month){
        $.ajax({
            url: _baseURL() + 'reviewer/getAssignedResearchList',
            type: 'GET',
            data: {
                month : month,
            },
            success: function (res) {
                var row = "";
                for (var i in res){
                    //nesGlobal.viewOrDownloadFile(res[i].filePath);
                    row = row + '<tr>'+
                        '<td></td>' +
                        '<td>' + (res[i].research_number) + '</td>' +
                        '<td>' + (res[i].researchTopic) + '</td>' +
                        '<td>' + (formatDate(res[i].assignedDate)) + '</td>' +
                        '<td>' + (res[i].reviewerName) + '</td>' +
                        '<td>' + (res[i].statusName) + '</td>' +
                        '</tr>'
                }
                var cardTable = $('#assingedResearchTbl');
                cardTable.dataTable().fnDestroy();
                cardTable.find('tbody').empty().prepend(row);
                // $('#researchListTbl').DataTable();
                createDataTableWithButtons(cardTable);
            }
        });
    }

    function populateModal(){
        $('#researchListTbl').find(' tbody').on('click', 'tr', function () {
            // var researchId = $(this).find('td:nth-child(2)').text();
            var researchNumber = $(this).find('td:nth-child(2)').text();
            var researchTopic = $(this).find('td:nth-child(3)').text();
            // $('#researchId').val(researchId);
            $('#rName').val(researchTopic);
            $('#rNumber').val(researchNumber);
            // $('#name').val(submittedBy);
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
        getResearchListByMonth: getResearchListByMonth,
        populateModal : populateModal,
        assignReviewer : assignReviewer
    };
})();

$(document).ready(function () {
    reviewer.getResearchListByMonth();
    reviewer.populateModal();
    reviewer.assignReviewer();
});