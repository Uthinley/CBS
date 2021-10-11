/**
 * Created By Dechen Wangdi on 05/12/2019
 */

var loadScript = function () {
    if (document.URL.search("usersetup") > 1) {
        scriptLoader("setup/userSetup");
    }

    if (document.URL.search("permissionSetup") > 1) {
        scriptLoader("setup/permissionSetup");
    }
    if (document.URL.search("groupSetup") > 1) {
        scriptLoader("setup/groupSetup");
    }
    if (document.URL.search("research") > 1) {
        scriptLoader("cbs/addResearch");
    }
};

var scriptLoader = function (url) {
    //TODO for production environment
    url = globalConf.context + '/resources/js/' + url + '.js';
    //url = window.location.host+'/resources/js/' + url + '.js';

    $.ajax(
        {
            type: "GET",
            url: url,
            dataType: "script",
            cache: false
            //cache: prodEnv
        }
    );
};
