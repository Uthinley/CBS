globalConf = (function () {
    "use strict";

    function dateFormat() {
        var dateFormat = "dd/mm/yy";
        return dateFormat;
    }

    return {
        dateFormat: dateFormat
    };
})();

$(document).ready(function () {
    globalConf.dateFormat();
});