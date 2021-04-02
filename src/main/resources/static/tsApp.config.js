'use strict';

// 获取跟路径
function getBaseUrl(url) {
    return "/" + url;
}

angular.module("tsApp").
  config(['$locationProvider' ,'$routeProvider',
    function config($locationProvider, $routeProvider) {
      $locationProvider.hashPrefix('!');

      $routeProvider.
          // 使用controller配置路由
          when("/aboutUs", {
              controller: "AboutUsController",
              controllerAs: "AboutUsControllerAs",
              templateUrl: "partials/aboutUs/aboutUs.template.html",
              resolve: {
                  message: function() {
                      console.log("aboutUs resolve");
                      return true;
                  }
              },
              caseInsensitiveMatch: true
          }).when("/aboutUs/:id", {
            controller : "AboutUsDetailController",
            controllerAs : "AboutUsDetailControllerAs",
            templateUrl : "partials/aboutUs/aboutUsDetail.template.html"
        }).
        // 使用component.controller进行路由配置
        when('/focus', {
            template: '<focus-list></focus-list>'
        }).when("/focus/:id", {
            template : "<focus-detail></focus-detail>"
        }).
        when("/user", {
            template: '<user-config></user-config>'
        }).
        when("/recent", {
            template: '<recent-list></recent-list>'
        }).
        when("/hot", {
            template: '<hot-list></hot-list>'
        }).
        when("/favs", {
            template: "<favs-list></favs-list>"
        }).
        when("/search", {
            template: "<search-list></search-list>"
        }).
        otherwise({
            redirectTo: '/focus'
        });
    }
  ]);
