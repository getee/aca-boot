"use strict";

angular.module("favsList", []).component("favsList", {
    templateUrl : getBaseUrl("partials/favs/favs-list.template.html"),
    controller : function() {
        console.log("favs");
        this.favs = [ {
            "id" : 1,
            "content" : "content1"
        }, {
            "id" : 2,
            "content" : "content2"
        } ];
    }
});