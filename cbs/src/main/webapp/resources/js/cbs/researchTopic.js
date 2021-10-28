/**
 * Created by RMA on 3/25/2020.
 */

researchTopic = (function () {
    "use strict";
    var formId = $('#researchTopicForm');

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
            // $(formId).validate({
            //     submitHandler: function (form) {
            var data = new FormData($(formId)[0]);
                    $.ajax({
                        url: _baseURL() + 'researchTopic/save',
                        type: 'POST',
                        data: data,
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
        save: save,
        formatDate: formatDate,
        wordCountOnFileSelect:wordCountOnFileSelect
    };
})();

$(document).ready(function () {
    researchTopic.save();
    // research.wordCountOnFileSelect();
});