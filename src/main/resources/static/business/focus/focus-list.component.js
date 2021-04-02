'use strict';

angular.module("focusList", []).component('focusList', {
	templateUrl : getBaseUrl('partials/focus/focus-list.template.html'),
	controller : function() {
		console.log("focus");
		this.focuses = [ {
			"id" : 1,
			"title" : "title1",
			"content" : "content1"
		}, {
			"id" : 2,
			"title" : "title2",
			"content" : "content2"
		} ];
	}
});
