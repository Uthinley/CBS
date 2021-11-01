/**
 * Created by RMA on 3/25/2020.
 */

transaction = (function () {
    "use strict";

    var formId = $('#bankDeposit');

    function _baseURL() {
        return globalJs.baseURL;
    }

    function getResearchList(){
        $.ajax({
            url: _baseURL() + 'getAllResearchList',
            type: 'GET',
            success: function (res) {
                var row = "";
                for (var i in res){
                    row = row + '<tr>'+
                        '<td></td>' +
                        '<td hidden>' + (res[i].researchId) + '</td>' +
                        '<td>' + (res[i].researchTopic) + '</td>' +
                        '<td>' + (formatDate(res[i].createdDate)) + '</td>' +
                        '<td>' + (res[i].createdBy) + '</td>' +
                        // '<td> <a href="' + (res[i].filePath) + '">file</a></td>' +
                        '<td> ' + (res[i].filePath) + '</td>' +
                        '<td>' + (res[i].wordCount) + '</td>' +
                        '<td>' + (res[i].statusName) + '</td>' +
                        '<td><i class="fa fa-eye text-success" id="iconEdit" data-toggle="modal" data-target="#reviewerModal" aria-hidden="true"></i></td>' +
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

    function populateModal(){
        $('#researchTbl').find(' tbody').on('click', 'tr', function () {
            var researchId = $(this).find('td:nth-child(2)').text();
            var researchTopic = $(this).find('td:nth-child(3)').text();
            var submittedBy = $(this).find('td:nth-child(7)').text();
            $('#researchId').val(researchId);
            $('#researchTopic').val(researchTopic);
            $('#name').val(submittedBy);
        });
    }

    function sResearch(){
        $('#submittedR').on('click', '#sResearch', function () {
            $('#submittedResearchSection').removeAttr('hidden');
            $('#totalResearcherSection').attr('hidden',true);
            $('#reviewedResearchSection').attr('hidden',true);
        });
    }
    function reviewedRBtn(){
        $('#reviewedR').on('click', '#reviewedRBtn', function () {
            $('#reviewedResearchSection').removeAttr('hidden');
            $('#totalResearcherSection').attr('hidden',true);
            $('#submittedResearchSection').attr('hidden',true);
            $.ajax({
                url: _baseURL() + 'getReviewedResearchList',
                type: 'GET',
                success: function (res) {
                    var row = "";
                    for (var i in res){
                        row = row + '<tr>'+
                            '<td></td>' +
                            '<td hidden>' + (res[i].researchId) + '</td>' +
                            '<td>' + (res[i].researchTopic) + '</td>' +
                            // '<td> <a href="' + (res[i].filePath) + '">file</a></td>' +
                            '<td> ' + (res[i].filePath) + '</td>' +
                            '<td>' + (res[i].wordCount) + '</td>' +
                            // '<td>' + (res[i].status) + '</td>' +
                            '<td>' + (res[i].createdBy) + '</td>' +
                            '<td>' + (formatDate(res[i].createdDate)) + '</td>' +
                            // '<td><i class="fa fa-eye text-success" id="iconEdit" data-toggle="modal" data-target="#reviewerModal" aria-hidden="true"></i></td>' +
                            // '<td class="d-none" id="status">' + (res[i].wordCount) + '</td>' +
                            // '<td hidden>' + (res[i].researchId) + '</td>' +
                            '</tr>'
                    }
                    var tableID = $('#getReviewedResearchListTbl');
                    tableID.dataTable().fnDestroy();
                    tableID.find('tbody').empty().prepend(row);
                    createDataTableWithButtons(tableID);
                }
            });

        });
    }
    function totalResearcherDtls(){
        $('#totalR').on('click', '#totalResearcherDtls', function () {
            $('#totalResearcherSection').removeAttr('hidden');
            $('#submittedResearchSection').attr('hidden',true);
            $('#reviewedResearchSection').attr('hidden',true);
            $.ajax({
                url: _baseURL() + 'getResearcherList',
                type: 'GET',
                success: function (res) {
                    var row = '';
                    for (var i in res) {
                        row = row + '<tr align="left">' +
                            '<td></td>' +
                            '<td>' + res[i].employeeId + '</td>' +
                            '<td>' + res[i].userName + '</td>' +
                            '<td>' + res[i].fullName + '</td>' +
                            '<td>' + res[i].emailId + '</td>' +
                            '<td>' + ((res[i].userStatus == '1') ? 'Active' : 'Inactive') + '</td>' +
                            '</tr>';
                    }
                    var researcherListTbl = $('#researcherListTbl');
                    researcherListTbl.dataTable().fnDestroy();
                    researcherListTbl.find('tbody').empty().prepend(row);
                    // researcherListTbl.dataTable();
                    createDataTableWithButtons(researcherListTbl);
                }
            });
        });
    }
    function submitReviewerDtls(){
        $('#reviewerModal').on('click', '#reviwerSubmitBtn', function () {
            $('#reviewerModal').modal('toggle');
            var researchId = $('#researchId').val();
            var statusId = $('#statusId').val();
            var rComment = $('#rComment').val();
            $.ajax({
                url: _baseURL() + 'saveReviewerComments',
                type: 'POST',
                data: {
                    researchId: researchId,
                    // statusId:statusId,
                    rComment :rComment
                },
                success: function (res) {
                    if (res.status == 1) {
                        successMsg(res.text, _baseURL());
                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        })
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
    function getDate(){
        const monthNames = ["January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        ];
        var today = new Date();
        var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
        var date = today.getDate()+'-'+(today.getMonth()+1)+'-'+today.getFullYear();
        $('#currentDate').text(date);
        // $('#currentMonth').text(monthNames[today.getMonth()]);
    }

    return {
        save: save,
        getResearchList: getResearchList,
        formatDate: formatDate,
        populateModal: populateModal,
        submitReviewerDtls: submitReviewerDtls,
        totalResearcherDtls : totalResearcherDtls,
        sResearch : sResearch,
        reviewedRBtn: reviewedRBtn,
        getDate: getDate,
    };
})();

$(document).ready(function () {
    transaction.save();
    transaction.getDate();
    transaction.getResearchList();
    transaction.formatDate();
    transaction.populateModal();
    transaction.submitReviewerDtls();
    transaction.totalResearcherDtls();
    transaction.sResearch();
    transaction.reviewedRBtn();
});