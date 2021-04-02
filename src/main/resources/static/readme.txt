TsApp-v1 说明：
1、搭建简单angularjs框架：
  tsApp(module)
      userConfig(module)
      focusList(module)
          focusList(component)
      recentList(module)
          recentList(component)
      hotList(module)
          hotList(component)
      favsList(module)
          favsList(component)
      searchList(module)
          searchList(component)

2、路由配置(使用ngRoute 服务进行路由配置)：
    tsApp(module).config

2.1、使用ngRoute包含以下几个内容：
         名称                              所属                                使用
    ngView          directive    提供不同路由模板插入的视图层
    $routeProvider  provider     提供路由配置
    $route          service      用于构建各个路由的url，view，controller的关系
    $routeParams    service      解析返回路由中带有的参数


         配置路由之后，index.html页面增加 ng-view区域，用于展示路由之后的component模板。
      <html ng-app="tsApp">
        <body>
            <div ng-view></div>
        </body>
      </html>



2.2、$routeProvider提供了定义路由表的服务，有两个核心方法：
2.2.1、 when(path,route)
    path： string类型，表示该条路由规则所匹配的路径。
         注意： 如果路径中需要匹配参数，如path路径是：/show/:name，如果地址栏为：/show/lydia，那么这里的name以及所对应的值lydia都会保存在$routeParams服务中，需要通过$routeParams.name来获取。
    route: object类型，用来指定一系列配置项。

                  配置                            	说明
        controller     	控制器名称
        controllerAs    给控制器起个别名
        template    	对应路径的页面模板，会出现在ng-view处，此处可以嵌套component
        templateUrl    对应模板的路径
        resolve    该属性会以键值对对象的形式，给路由相关的控制器绑定服务或者值。然后把执行的结果值或者对应的服务引用，注入到控制器中。如果resolve中是一个promise对象，那么会等它执行成功后，才注入到控制器中，此时控制器会等待resolve中的执行结果。
        redirectTo    重定向地址
        reloadOnSearch    设置是否在只有地址改变时，才加载对应的模板。search和params改变都不会加载模板。默认为true，当$location.search()发生变化时会重新加载路由。
        caseInsensitiveMatch    路径区分大小写
2.2.2、otherwise(params)
    对应了路径匹配不到时的情况。



总结：
	1、AngularJS的路由实际上是一种纯前端的解决方案，它的本质是：当请求一个url时，根据路由配置这个url，然后再请求模板片段，并插入到ng-view中。AngularJS的路由更倾向于通过改变url进行页面的局部刷新。
    2、父子页面路由方式不直观，需要理清所有的module关系，否则后续的ngRoute配置容易混乱。


TsApp-v2说明：
	1、使用ngRouter路由配置的另一种表达方式，详见tsApp.config.js







