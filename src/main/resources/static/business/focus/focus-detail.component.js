"use strict";

angular.module("focusDetail", []).component("focusDetail", {
	templateUrl : getBaseUrl("partials/focus/focus-detail.template.html"),
	controller : function($routeParams) {
		console.log("focusDetail: " + $routeParams.id);
		this.id = $routeParams.id;
	}
});