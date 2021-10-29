/**
 * Created by nima yoezer on 25-Jan-17.
 */

//region *** Function for Global Object ***

var dateFormat = 'dd-mm-yyyy';

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


function getParameterByNameFromUrl(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#$"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

function allowKeys(e) {
    //Allow: backspace, delete, tab, escape, enter
    if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110]) !== -1 ||
            //Allow: Ctr+A
        (e.keyCode == 65 && e.ctrlKey === true) ||
            //Allow: home, end, left, right, down, up
        (e.keyCode >= 35 && e.keyCode <= 40)) {
        return true;
    }
    return false;
}

function getDate(dateParam) {
    var d = new Date(dateParam);

    if (d.getTimezoneOffset() > 0) {
        d.setTime(d.getTime() + d.getTimezoneOffset() * 60 * 1000);
    } else {
        d.setTime(d.getTime() - d.getTimezoneOffset() * 60 * 1000);
    }

    return d;
}

/**
 * Responsible to populate the entire form
 * @param {json} data
 * @return {void}
 */
function populate(data) {

    for (var i in data) {
        if (typeof (data[i]) === 'object') {
            //populate(data[i]);
        } else {
            $(
                "input[type='text'][name='" + i + "'], " +
                "input[type='hidden'][name='" + i + "'], " +
                "input[type='checkbox'][name='" + i + "'], " +
                "input[type='date'][name='" + i + "'], " +
                "select[name='" + i + "'], textarea[name='" + i + "']"
            ).val((data[i] === null || data[i] === 'null' ? '' : data[i]));


            //$("input[type='date'][name='" + i + "']").val($.datepicker.formatDate(dateFormat, new Date(parseInt(data[i]))));


            $("input[type='radio'][name='" + i + "'][value='" + data[i] + "']").prop('checked', true);

        }
    }

    $('form').find('input[type="checkbox"]').each(
        function () {
            if ($(this).siblings('input[type="hidden"]').val() == "true" ||
                $(this).siblings('input[type="hidden"]').val() == 1) {
                $(this).prop('checked', true);
            } else {
                $(this).prop('checked', false);
            }
        }
    );
}

function formatAsDate(date) {
    if (date)
        return $.datepicker.formatDate(dateFormat, new Date(date));

    return '';
}

function parseAsDate(val) {
    if (val) {
        var dateRegex = /((3[01])|([012]\d)|[1-9])((1[012])|(0?[1-9]))?((19|20)?\d\d?)?/;
        var dateMatch = val.match(dateRegex);
        if (!dateMatch || !dateMatch[1]) {
            return;
        }
        var day = dateMatch[1];
        var date = new Date();
        var month = date.getMonth();
        var year = date.getFullYear();
        if (dateMatch[4]) {
            month = dateMatch[4];
        }
        if (dateMatch[7]) {
            year = dateMatch[7];
            year = parseInt(year);
            if (dateMatch[7].length < 3) {
                if (year > 70)
                    year = 1900 + year;
                else
                    year = 2000 + year;
            }
        }
        date = new Date(year, month - 1, day);
        return date;

    }
}

function parseEntryAsDate(id) {
    id = '#' + id;
    var val = $(id).val();
    val = val.trim();
    if (val) {
        var date = null;
        if (val.length > 8) {
            date = $.datepicker.parseDate(globalConf.dateFormat(), val);
            if (date.getTime()) {
                val = $.datepicker.formatDate(globalConf.dateFormat(), new Date(date));
                $(id).val(val);
                return true;
            }
        }
        date = parseAsDate(val);
        if (!date) {
            $(id).val('');
            return;
        }
        $(id).datepicker("setDate", date);
        val = $.datepicker.formatDate(globalConf.dateFormat(), date);
        $(id).val(val);
        return true;
    }
}

/**
 * To get current date.
 * @returns {string}
 */
function getCurrentDate() {
    var today = new Date();

    var date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
    var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
    return date + ' ' + time;
}
/**
 * To add buttons to copy, csv, excel, pdf, print option on dataTable.
 * @param tableId
 */
function createDataTableWithButtons(tableId) {


    tableId.DataTable({
        fixedHeader: {
            header: true,
            footer: true
        },
        responsive: true,
        dom: 'Bfrtip',
        buttons: [
            {
                extend: 'pdfHtml5',
                orientation: 'landscape',
                text: 'PDF',
                title: 'All cards information',
                pageSize: 'LEGAL',
                customize: function (doc) {
                    doc.content.splice(0, 0,
                        {
                            margin: [0, 0, 0, 0],
                            alignment: 'center',
                            image: 'data:image/png;base64,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',
                            width: 60,
                            height: 60
                        }
                    );

                    doc['footer'] = (function (page, pages) {
                        return {
                            columns: [
                                'Report on: ' + getCurrentDate(),
                                {
                                    alignment: 'right',
                                    text: [
                                        {text: page.toString(), italics: false},
                                        ' of ',
                                        {text: pages.toString(), italics: false}
                                    ]
                                }
                            ],
                            margin: [10, 0]
                        }
                    });
                }
            },
            /* 'pdf'*/, 'excel']
    });


    /*tableId.dataTable({
     ordering: false,
     dom: 'Bfrtip',
     buttons: [
     */
    /* {
     extend: 'pdfHtml5',
     customize: function (doc) {

     //ensure doc.images exists
     doc.images = doc.images || {};

     //build dictionary
     doc.images['header'] = getBase64Image(header);
     doc.content.splice(1, 0, {
     margin: [0, 0, 0, 12],
     alignment: 'left',
     //width: '20%',
     //height: '20%',
     image: doc.images['header']
     });
     }
     },*/
    /*
     'pdf','excel', 'csv', 'print'
     ]
     });*/
}

function commaSeparator(number) {
    return number_format(number, 2, '.', ',')
}
/**
 * Comma separator
 * @param number - actual number.
 * @param decimals - digits required after decimal point
 * @param dec_point - separator,i.e ','
 * @param thousands_sep - separator,i.e ','
 * @returns {string|*}
 * example: number_format(1234.56, 2, ',', ' ')-  return: '1 234,56';
 */
function number_format(number, decimals, dec_point, thousands_sep) {
    number = (number + '').replace(',', '').replace(' ', '');
    var n = !isFinite(+number) ? 0 : +number,
        prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
        sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
        dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
        s = '',
        toFixedFix = function (n, prec) {
            var k = Math.pow(10, prec);
            return '' + Math.round(n * k) / k;
        };
    // Fix for IE parseFloat(0.55).toFixed(0) = 0;
    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
    if (s[0].length > 3) {
        s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
    }
    if ((s[1] || '').length < prec) {
        s[1] = s[1] || '';
        s[1] += new Array(prec - s[1].length + 1).join('0');
    }
    return s.join(dec);
}

function isNull(value) {
    if (value === null) {
        return "-";
    }
    return value;
}

//region sweet alert

function successMsg(msg, url) {
    if (!url) {
        swal({
            title: "Success!",
            text: msg,
            type: "success"
        })
    } else {
        swal({
            title: "Success",
            text: msg,
            type: "success"
        }, function () {
            window.location = url;
        });
    }
}
function successMsg2(msg, functionName) {
    if (!functionName) {
        swal({
            title: "Success!",
            text: msg,
            type: "success"
        })
    } else {
        swal({
            title: "Success",
            text: msg,
            type: "success"
        }, function () {
            functionName;
        });
    }
}

function errorMsg(msg) {

    swal({
        title: "Error!",
        text: msg,
        type: "error",
        button: "ok"
    });

}
function warningMsg(msg) {
    swal({
        title: "Warning!",
        text: msg,
        type: "warning",
        button: "ok"
    });
}

function confirmMsg(msg) {
    swal({
        title: "Confirmation",
        text: msg,
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: '#DD6B55',
        confirmButtonText: 'Yes, I am sure!',
        cancelButtonText: "No, cancel it!",
        closeOnConfirm: false,
        closeOnCancel: false
    }, function (isConfirm) {
        return isConfirm;
    });
}


//endregion

//endregion *** Function for Global Object ***

//*********************************************************************************************************

//region *** globalJs Object ***
globalJs = (function () {
    "use strict";

    function baseURL() {
        //TODO for production environment
        //return window.location.protocol + '//' + window.location.host + '/';
        //return window.location.protocol + '//' + window.location.host + '/nes/';
        return window.location.protocol + '//' + window.location.host + globalConf.context + '/';
    }

    function baseReportLocation() {
        //TODO for production environment
        //return window.location.protocol + '//' + window.location.host + '/resources/reports/';
        //return window.location.protocol + '//' + window.location.host + '/nes/resources/reports/';
        return window.location.protocol + '//' + window.location.host + globalConf.context + '/resources/reports/';
    }

    function ajax(url, type, data, callback) {
        $.ajax({
            url: url,
            type: type,
            data: data,
            success: callback
        });
    }


    function loadDropDown(element, data, type) {
        element.empty();
        if (!data) {
            data = [];
        }
        if (data.length) {
            element.append(
                $(
                    '<option/>', {
                        value: "",
                        text: "--- Please select ---"
                    }
                )
            );
            if (type === 'char') {
                $.each(
                    data, function (index, itemData) {
                        element.append(
                            $(
                                '<option/>', {
                                    value: itemData.valueChar,
                                    text: itemData.text
                                }
                            )
                        );
                    }
                );
            }


            if (type === 'string') {
                $.each(
                    data, function (index, itemData) {
                        element.append(
                            $(
                                '<option/>', {
                                    value: itemData.value,
                                    text: itemData.text
                                }
                            )
                        );
                    }
                );
            }

            if (type === 'integer') {
                $.each(
                    data, function (index, itemData) {
                        element.append(
                            $(
                                '<option/>', {
                                    value: itemData.value,
                                    text: itemData.text
                                }
                            )
                        );
                    }
                );
            }

            if (type === 'short') {
                $.each(
                    data, function (index, itemData) {
                        element.append(
                            $(
                                '<option/>', {
                                    value: itemData.valueShort,
                                    text: itemData.text
                                }
                            )
                        );
                    }
                );
            }

            if (type === 'byte') {
                $.each(
                    data, function (index, itemData) {
                        element.append(
                            $(
                                '<option/>', {
                                    value: itemData.valueByte,
                                    text: itemData.text
                                }
                            )
                        );
                    }
                );
            }
        }
    }

    function resetForm(gridList) {
        $('.globalForm')[0].reset();
        $('.globalForm').find.error('.error').addClass('valid');
        $('.globalForm').find.error('.error').removeClass('error link-tooltip');
        $('.globalForm').find.error('.error').removeAttr('title');

        if (gridList.length) {
            $.each(
                gridList, function (index, value) {
                    value.dataTable().fnClearTable();
                }
            );
        }

    }


    function formIndexing(tableBody, serialNo, iterator) {
        if (!iterator)
            iterator = 0;
        var row = tableBody.find('tr');

        for (var i = 0; i < row.length; i++) {
            tableBody.children().eq(i).find(':input').each(
                function () {
                    if (this.name) {
                        this.name = this.name.replace(
                            /\[(\d+)\]/, function (str, p) {
                                return '[' + (i + iterator) + ']';
                            });
                    }

                    if ($(this).hasClass(serialNo)) {
                        $(this).val(i + 1);
                    }
                }
            );
        }
    }

    function formIndexingDiv(outerdiv, iterator) {
        if (!iterator)
            iterator = 0;

        var length = outerdiv.children().length;
        for (var i = 0; i < length; i++) {
            outerdiv.children().eq(i).find('.form-control').each(
                function () {
                    if (this.name) {
                        this.name = this.name.replace(
                            /\[(\d+)\]/, function (str, p) {
                                return '[' + (i + iterator) + ']';
                            });
                    }

                }
            );
        }
    }

    function formIndexingM(tbl, iterator) {
        if (!iterator)
            iterator = 0;

        var length = tbl.find('tbody').length;
        for (var i = 0; i < length; i++) {
            tbl.find('tbody').eq(i).find('.form-control').each(
                function () {
                    if (this.name) {
                        this.name = this.name.replace(
                            /\[(\d+)\]/, function (str, p) {
                                return '[' + (i + iterator) + ']';
                            });
                    }

                }
            );
        }
    }

    function formIndexingN(tbody, iterator) {
        if (!iterator)
            iterator = 0;

        var row = tbody.find('tr');
        for (var i = 0; i < row.length; i++) {
            tbody.children().eq(i).children().children().each(
                function () {
                    var nth = 0;
                    if (this.name) {
                        this.name = this.name.replace(
                            /\[(\d+)\]/g, function (str, p) {
                                nth++;
                                return (nth === 2) ? '[' + (i - 1 + iterator) + ']' : str;
                            });
                    }
                }
            );
        }
    }

    function getSelectedOptionText(element, event) {
        return element.options[event.target.selectedIndex].text;
    }

    function handleCheckboxBeforeSave(form) {
        $(form).find('input[type="checkbox"]').each(
            function () {
                if ($(this).is(':checked')) {
                    $(this).next('input[type="hidden"]').val('true');
                } else {
                    $(this).next('input[type="hidden"]').val('false');
                }
            }
        );
    }

    function switchToInvalidTab(targetControl) {
        if (!targetControl)
            targetControl = $('.error');
        var errorContainedPane = targetControl.first().parents('.tab-pane'), tabPaneID = errorContainedPane.attr('id');
        errorContainedPane.addClass('active in');
        errorContainedPane.siblings().removeClass('active in');
        $('a[href$="#' + tabPaneID + '"]').parent('li').siblings().removeClass('active');
        $('a[href$="#' + tabPaneID + '"]').parent('li').addClass('active');
    }

    function convertAmountInWord(amount) {
        var th = ['', 'Thousand', 'Million', 'Billion', 'Trillion'];

        var dg = ['Zero', 'One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine'];
        var tn = ['Ten', 'Eleven', 'Twelve', 'Thriteen', 'Fourteen', 'Fifteen', 'Sixteen', 'Seventeen', 'Eighteen', 'Nineteen'];
        var tw = ['Twenty', 'Thirty', 'Fourty', 'Fifty', 'Sixty', 'Seventy', 'Eighty', 'Ninety'];

        amount = amount.toString();
        var x = amount.indexOf('.');
        amount = amount.replace(/[\. ]/g, '');
        if (amount != parseFloat(amount))
            return 'not a number';

        if (x == -1) x = amount.length;
        if (x > 15) return 'Too Big';

        var n = amount.split('');
        var str = '';
        var sk = 0;
        for (var i = 0; i < x; i++) {
            if ((x - i) % 3 == 2) {
                if (n[i] == '1') {
                    str += tn[Number(n[i + 1])] + ' ';
                    i++;
                    sk = 1;
                } else if (n[i] != '0') {
                    str += tw[n[i] - 2] + ' ';
                    sk = 1;
                }
            }
            else if (n[i] != '0') {
                str += dg[n[i]] + ' ';
                if ((x - i) % 3 == 0) str += 'Hundred ';
                sk = 1;
            }
            if ((x - i) % 3 == 1) {
                if (sk) str += th[(x - i - 1) / 3] + ' ';
                sk = 0;
            }
        }

        if (x != amount.length) {
            var y = amount.length;
            str += 'Point ';
            for (var z = x; z < y; z++) str += dg[n[z]] + ' ';
        }
        return str.replace(/\s+/g, ' ');

    }

    function reset() {
        $('body').on('click', '#btnReset', function (e) {
            /*var form = $(this).closest('form');
             form[0].reset();
             form.find(':input').each(function(e){
             $(this).removeClass('error') ;
             });*/
            window.location.reload(true);
        });

    }

    function enterKeyAsTab() {
        $('form input').keydown(function (e) {
            if (e.keyCode == 13) {
                var inputs = $(this).parents('form').eq(0).find(':input');
                if (inputs[inputs.index(this) + 1] != null) {
                    inputs[inputs.index(this) + 1].focus();
                }
                e.preventDefault();
                return false;
            }
        });
    }

    //
    // notification
    //
    function notification() {
    }

    function viewOrDownloadFile(filePath){
        let url = globalConf.context+"/viewOrDownloadFile";
        let token = $("meta[name='_csrf']").attr("content");
        let parameter = $("meta[name='_csrf_parameterName']").attr("content");
        return '<form target="_blank" method="POST" action="'+url+'">' +
            '<input type="hidden" name="'+parameter+'" value="'+token+'"/>'+
            '<input type="hidden" name="filePath" value="' + filePath + '"/>' +
            '<input type="submit" value="View File"/></form>';
        /*$.ajax({
            url: baseURL()+"viewOrDownloadFile",
            type: 'POST',
            data: {filePath:filePath},
            success: function(res){
                window.open(baseURL()+"viewOrDownloadFile","_blank");
            }
        });*/
    }

    return {
        baseURL: baseURL(),
        ajax: ajax,
        loadDropDown: loadDropDown,
        resetForm: resetForm,
        formIndexing: formIndexing,
        formIndexingM: formIndexingM,
        formIndexingN: formIndexingN,
        formIndexingDiv: formIndexingDiv,
        getSelectedOptionText: getSelectedOptionText,
        handleCheckboxBeforeSave: handleCheckboxBeforeSave,
        switchToInvalidTab: switchToInvalidTab,
        convertAmountInWord: convertAmountInWord,
        baseReportLocation: baseReportLocation,
        reset: reset,
        enterKeyAsTab: enterKeyAsTab,
        viewOrDownloadFile: viewOrDownloadFile,
        number_format: number_format
    };
})();
//endregion

//*********************************************************************************************************

//region *** Document Ready Method ***
$(document).ready(



    function () {
        //Local variable for show errors on pop instead of tooltip
        var submitted = false;

        //region *** jQuery custom validator ***
        $.validator.setDefaults(
            {
                ignore: '.ignore',
                focusCleanup: true,
                showErrors: function (errorMap, errorList) {
                    $.each(
                        this.validElements(), function (index, element) {
                            var $element = $(element);
                            $element.removeClass("error");
                        }
                    );
                    $.each(
                        errorList, function (index, error) {
                            var $element = $(error.element);
                            $element.addClass("error");
                        }
                    );

                    if (errorList && errorList.length > 0) {
                        errorList[0].element.focus();
                    }

                },
                invalidHandler: function (event, validator) {
                    submitted = true;
                    globalJs.switchToInvalidTab();
                }
            }
        );
        //endregion

        //region comma separator.
        $(".add_comma").each(function () {
            $(this).format({format: "#,###", locale: "us"});
        });
        //endregion

        //region *** Restriction Event ***
        $('body').on(
            'keypress', '.number', function (e) {
                if (allowKeys(e)) {
                    return true;
                }
                var regex = new RegExp("^[0-9]+$");
                var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
                if (regex.test(str)) {
                    return true;
                }

                e.preventDefault();
                return false;
            }
        );


        $('body').on(
            'keypress', '.alphanumeric', function (e) {
                if (allowKeys(e)) {
                    return true;
                }
                var regex = new RegExp("^[a-zA-Z0-9]+$");
                var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
                if (regex.test(str)) {
                    return true;
                    n
                }

                e.preventDefault();
                return false;
            }
        );
        //region *** on trail by sonam ***
        $('body').on(
            'keypress', '.nameOnly', function (e) {
                if (allowKeys(e)) {
                    return true;
                }
                var regex = new RegExp("^[a-zA-Z\\s]*$");
                var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
                if (regex.test(str)) {
                    return true;
                }

                e.preventDefault();
                return false;
            }
        );


        function isAmount(event, element) {
            var charCode = (event.which) ? event.which : event.keyCode;
            if (
                (charCode != 46 || $(element).val().indexOf('.') != -1) &&
                (charCode != 9) &&
                (charCode < 48 || charCode > 57) &&
                (charCode != 8))
                return false;
            else
                return true;
        }

        $('body').on(
            'keypress', '.amount', function (e) {
                /*if (allowKeys(e)) {
                    return;
                }*/
                return isAmount(e, this);
            }
        );


        $('body').on(
            'keydown', '.percentage', function (e) {
                if (allowKeys(e)) {
                    return;
                }

                if ((e.which >= 96 && e.which <= 105) || (e.which >= 48 && e.which <= 57) || e.which === 190 ||
                    e.which === 110) {

                } else {
                    e.preventDefault();
                }
            }
        );

        $('body').on(
            'blur', '.percentage', function (e) {
                var $this = $(this);
                if ($this.val()) {
                    var value = $this.val();

                    var regex = new RegExp("^[0-9]{1,3}(\\.([0-9]{1,2})?)?$");
                    if (!regex.test(value)) {
                        errorMsg('Incorrect Format. Format is ###.##');
                        $this.val('');
                        return;
                    }
                }
                if (value > 100) {
                    errorMsg('Please insert interest rate between zero(0) and hundred(100)');
                    $this.val('');
                    return;
                }
            }
        );
        //endregion *** Restriction Event ***


        //region custom global methods
        globalJs.reset();
        globalJs.enterKeyAsTab();
        //endregion


        /*  //region *** Validation Message Alert Configuration ***
         alertify.dialog(
         'errorAlert', function factory() {
         return {
         build: function () {
         var errorHeader = '<span class="glyphicon glyphicon-exclamation-sign errorMsg gi-2x" ' +
         'style="vertical-align:moddle;">' +
         '</span> Application Error';
         this.setHeader(errorHeader);
         }
         };
         }, true, 'alert'
         );

         alertify.dialog(
         'successAlert', function factory() {
         return {
         build: function () {
         var successHeader = '<span class="glyphicon glyphicon-ok-sign successMsg gi-2x" ' +
         'style="vertical-align:middle;">' +
         '</span> Application Success';
         this.setHeader(successHeader);
         }
         };
         }, true, 'alert'
         );

         alertify.dialog(
         'warningAlert', function factory() {
         return {
         build: function () {
         var successHeader = '<span class="glyphicon glyphicon-warning-sign warningMsg gi-2x" ' +
         'style="vertical-align:middle;">' +
         '</span> Application Warning';
         this.setHeader(successHeader);
         }
         };
         }, true, 'alert'
         );

         alertify.dialog(
         'infoAlert', function factory() {
         return {
         build: function () {
         var successHeader = '<span class="glyphicon glyphicon-info-sign infoMsg gi-2x" ' +
         'style="vertical-align:middle;">' +
         '</span> Application Information';
         this.setHeader(successHeader);
         }
         };
         }, true, 'alert'
         );

         alertify.dialog(
         'confirmAlert', function factory() {
         return {
         build: function () {
         var confirmHeader = '<span class="glyphicon glyphicon-question-sign errorMsg gi-2x" ' +
         'style="vertical-align:middle;">' +
         '</span> Application Confirmation';
         this.setHeader(confirmHeader);
         }
         };
         }, true, 'confirm'
         );

         alertify.dialog(
         'successCustomerAlert', function factory() {
         return {
         build: function () {
         var successHeader = '<span class="glyphicon glyphicon-ok-sign successMsg gi-2x" ' +
         'style="vertical-align:middle;">' +
         '</span> Application Success';
         this.setHeader(successHeader);
         }
         };
         }, true, 'confirm'
         );
         //endregion *** Validation Message Alert Configuration ****/


        //region *** jQuery Custom Plugin ***
        $.fn.disableElements = function (status) {
            $(this).removeClass('error');
            $(this).each(
                function () {
                    $(this).attr('readonly', status);
                    $(this).find('option').prop('disabled', status);

                    if ($(this).is(':checkbox') || $(this).is(':radio')) {
                        $(this).attr('disabled', status);
                    }
                    $('input:checkbox[name=checkme]').attr('disabled', status);
                }
            );
        };

        $.fn.SimpleGridWithoutPaging = function (data, column_def) {
            //debugger;
            $(this).dataTable().fnDestroy();
            $(this).dataTable(
                {
                    "paging": false,
                    "retrieve": true,
                    "data": data,
                    "columns": column_def
                }
            );
        };

        $.fn.ditSimpleGrid = function (data, column_def) {
            //debugger;
            $(this).dataTable().fnDestroy();
            $(this).dataTable(
                {
                    "scrollY": "200px",
                    "data": data,
                    "columns": column_def,
                    "editable": true
                }
            );
        };

        $.fn.ditEditableGrid = function (data, column_def) {
            SimpleGridEditTable = $(this).dataTable(
                {

                    "data": data,
                    "columns": column_def,
                    "bStateSave": true
                }
            );

            $(this).on(
                'click', ' tbody td', function () {
                    $(this).editable(
                        function (sValue) {
                            var aPos = SimpleGridEditTable.fnGetPosition(this);
                            var aData = SimpleGridEditTable.fnGetData(aPos[0]);

                            aData[aPos[1]] = sValue;

                            return sValue;
                        }, {"onblur": 'submit'}
                    );
                }
            );

            $(this).on(
                'change', ' tbody td', function () {
                    $(this).css({"color": "#ffb7b7"});
                }
            );
        };

        $.fn.addToGrid = function (form) {
            var formElelmentValue = {};

            $(form).serializeArray().map(
                function (item) {
                    formElelmentValue[item.name] = item.value;
                }
            );

            var dtTable = $(this).dataTable();

            dtTable.fnAddData(formElelmentValue);
            $(form)[0].reset();

            dtTable = dtTable.DataTable();

            $(this + " tbody tr").unbind('click');
            $(this + " tbody tr").bind(
                'click', function () {
                    dtTable.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    populate(dtTable.row('.selected').data(), form);
                }
            );
        };

        $.fn.removeFromGrid = function (form, buttonSelector) {
            var dtTable = $(this).DataTable();

            dtTable.row('.selected').remove().draw(false);
            $(form)[0].reset();
        };

        $.fn.showDialog = function (url) {
            $.ajax(
                {
                    url: url,
                    success: function (data) {
                        $(this).html(data).dialog();
                    }
                }
            );
        };

        $.fn.showModalDialog = function (url) {
            $.ajax(
                {
                    url: url,
                    success: function (data) {
                        $(this).html(data).dialog(
                            {
                                modal: true,
                                buttons: {
                                    Close: function () {
                                        $(this).dialog("close");
                                    }
                                }
                            }
                        );
                    }
                }
            );
        };
        //endregion *** jQuery Custom Plugin ***
    }
);
//endregion *** Document Ready Method ***