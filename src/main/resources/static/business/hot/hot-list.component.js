"use strict";

angular.module("hotList", []).component("hotList", {
    templateUrl : getBaseUrl("partials/hot/hot-list.template.html"),
    controller : function() {
        console.log("hot")
        this.hots = [ {
            "id" : 1,
            "content" : "content1"
        }, {
            "id" : 2,
            "content" : "content2"
        } ]
    }
});