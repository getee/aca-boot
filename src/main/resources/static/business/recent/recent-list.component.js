"use strict";

angular.module("recentList", []).component("recentList", {
    templateUrl : getBaseUrl('partials/recent/recent-list.template.html'),
    controller : function($scope) {
        console.log("recent");
        var $ctrl = this;
        $ctrl.recents = [ {
            "id" : 1,
            "content" : "content1"
        }, {
            "id" : 2,
            "content" : "content2"
        } ];
    }
});