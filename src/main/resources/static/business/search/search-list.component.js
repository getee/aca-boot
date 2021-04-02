"use strict";

angular.module("searchList", []).component("searchList", {
    templateUrl : getBaseUrl("partials/search/search-list.template.html"),
    controller : function() {
        console.log("search");
        this.searchList = [ {
            "id" : 1,
            "content" : "content1"
        }, {
            "id" : 2,
            "content" : "content2"
        } ]
    }

});