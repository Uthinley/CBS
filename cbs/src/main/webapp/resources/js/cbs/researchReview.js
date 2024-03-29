/**
 * Created by RMA on 3/25/2020.
 */

titleApproval = (function () {
    "use strict";
    let formId = $('#researchReviewForm');

    function _baseURL() {
        return globalJs.baseURL+"researchReview";
    }

    function init(){
        approve();
        reject();
        gResearchList();
        populateModal();
        saveReviewerComment();
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
        let data = [];
        $("input:checkbox[class=check-for-action]:checked").each(function(){
            data.push({name:"research_title_ids",value:$(this).val()});
        });
        data.push({name:"action",value:action});
        data.push({name:"remarks",value:$('#remarks').val()});
        // alert(JSON.stringify(data));
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

    function saveReviewerComment(){
        $('#submitBtn').on('click', function (e) {
            var error="";
            if($('#rComment').val()=='' || $('#weightageOne').val()=='' || $('#weightageTwo').val()=='' || $('#weightageThree').val()==''||
                $('#weightageFour').val()=='' || $('#weightageFive').val()=='' ){
                error = "Please fill up all the input fields.";
                $('#errorAlert').text(error)
                $('#errorAlert').removeAttr('hidden');
            }else if($('#weightageOne').val() >20 || $('#weightageTwo').val() >20 || $('#weightageThree').val()>20||
                $('#weightageFour').val()>20 || $('#weightageFive').val()>20 ){
                error = "Weightage cannot be greater than 20";
                $('#errorAlert').text(error)
                $('#errorAlert').removeAttr('hidden');
            }else{
                saveReviewerMarks();
                $('#errorAlert').attr('hidden',true);
            }
        });
    }

    function saveReviewerMarks(){
        var assessmentCriteria = [
            "Policy relevance/Research significance",
            "Research methodology",
            "Data analysis and findings",
            "Recommendations",
            "Overall standard of the report"
        ];
        const weightage=[20, 20, 20, 20, 20];
        const marksAllocated = [$('#weightageOne').val(), $('#weightageTwo').val(), $('#weightageThree').val(), $('#weightageFour').val(), $('#weightageFive').val()];
        var rComment = $('#rComment').val();
        var researchId = $('#researchId').val();
        var researchNo = $('#researchNo').val();
        // alert(researchNo)
        $.ajax({
            url: _baseURL() + '/saveReviewerMarks',
            type: 'POST',
            data: {
                assessmentCriteria: JSON.stringify(assessmentCriteria),
                weightage: JSON.stringify(weightage),
                marksAllocated:JSON.stringify(marksAllocated),
                rComment: rComment,
                researchId: researchId,
                researchNo: researchNo
            },
            dataType: "json",
            success: function (res) {
                if (res.status == 1) {
                    successMsg(res.text, _baseURL());
                } else {
                    warningMsg(res.text);
                }
            }
        });
    }

    function gResearchList(){
        $('#btnSearch').on('click', function (e) {
            $('#searchForm').validate({
                submitHandler: function (form) {
                    let data = $(form).serializeArray();
                    $.ajax({
                        url: _baseURL() + '/gResearchList',
                        type: 'GET',
                        data: data,
                        success: function (res) {
                            let row = "";
                            for (let i in res) {
                                let color = "";
                                let action="";
                                if (res[i].status == 'T') {
                                    color = "color:red";
                                } else if (res[i].status == 'S') {
                                    // action = '<input type="checkbox" class="check-for-action" value="' + res[i].research_number + '"/>';
                                    action = '<button type="button" class="btn btn-circle btn-sm btn-outline-danger" id="iconEdit" data-toggle="modal" data-target="#reviewerModal" aria-hidden="true">' +
                                        '<i class="fas fa-info-circle"></i></button>';
                                    color = "color:#5A1B0E";
                                } else if (res[i].status == 'R') {
                                    action = '<i class="fa fa-check text-success" aria-hidden="true"></i>';
                                    color = "color:green";
                                }else{
                                    action = '<button type="button" class="btn btn-circle btn-sm btn-outline-danger" id="iconEdit" data-toggle="modal" data-target="#reviewerModal" aria-hidden="true">' +
                                        '<i class="fas fa-info-circle"></i></button>';
                                    color = "color:#5A1B0E";
                                }
                                color = color + ";font-weight:bold;";
                                row = row + '<tr>' +
                                    // '<td><input type="hidden" class="id" value="' + res[i].researchId + '"/> </td>' +
                                    '<td></td>' +
                                    '<td class="id" hidden>' + (res[i].researchId) + '</td>' +
                                    '<td class="month">' + (res[i].research_month) + '</td>' +
                                    '<td class="title">' + (res[i].researchTopic ) + '</td>' +
                                    '<td><span style="' + color + '">' + (res[i].statusName) + '</span></td>' +
                                    '<td>'+(globalJs.viewOrDownloadFile(res[i].filePath+'/'+res[i].research_paper_name))+'</td>' +
                                    '<td>' + isNull(res[i].wordCount) + '</td>' +
                                    '<td>' + isNull(res[i].createdBy) + '</td>' +
                                    '<td hidden>' + isNull(res[i].research_number) + '</td>' +
                                    '<td>' + (formatDate(res[i].assignedDate)) + '</td>' +
                                    '<td>'+action+'</td>'+
                                    '</tr>'
                            }
                            let tbl = $('#assigned_research_tbl');
                            tbl.find('tbody').empty().prepend(row);
                            if(res.length==0){
                                warningMsg("No details found");
                            }
                        }
                    });
                }
            });
        });
    }

    function populateModal(){
        $('#assigned_research_tbl').find(' tbody').on('click', 'tr', function () {
            var researchId = $(this).find('td:nth-child(2)').text();
            var researchTitle = $(this).find('td:nth-child(4)').text();
            var author = $(this).find('td:nth-child(8)').text();
            var researchNo = $(this).find('td:nth-child(9)').text();
            $('#researchId').val(researchId);
            $('#researchNo').val(researchNo);
            $('#researchTitle').val(researchTitle);
            $('#author').val(author);
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