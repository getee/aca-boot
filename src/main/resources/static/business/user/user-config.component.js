"use strict";

angular.module("userConfig", []).component("userConfig", {
    templateUrl : getBaseUrl("partials/user/user-config.template.html"),
    controller : function() {
        console.log("user");
        this.userConfig = {
            "loginName" : "user1",
            "userName" : "user1",
            "config" : {}
        };
    }
})
// 在component中重新配置其他的路由
.config(['$locationProvider' ,'$routeProvider',
    function config($locationProvider, $routeProvider) {
	console.log("donation.config")

	$locationProvider.hashPrefix('!');

	$routeProvider.
		when("/donation", {
			template: "<ts-app-donation></ts-app-donation>"
		});
}]
);