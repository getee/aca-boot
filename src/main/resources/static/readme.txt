TsApp-v1 ˵����
1�����angularjs��ܣ�
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

2��·������(ʹ��ngRoute �������·������)��
    tsApp(module).config

2.1��ʹ��ngRoute�������¼������ݣ�
         ����                              ����                                ʹ��
    ngView          directive    �ṩ��ͬ·��ģ��������ͼ��
    $routeProvider  provider     �ṩ·������
    $route          service      ���ڹ�������·�ɵ�url��view��controller�Ĺ�ϵ
    $routeParams    service      ��������·���д��еĲ���


         ����·��֮��index.htmlҳ������ ng-view��������չʾ·��֮���componentģ�塣
      <html ng-app="tsApp">
        <body>
            <div ng-view></div>
        </body>
      </html>



2.2��$routeProvider�ṩ�˶���·�ɱ�ķ������������ķ�����
2.2.1�� when(path,route)
    path�� string���ͣ���ʾ����·�ɹ�����ƥ���·����
         ע�⣺ ���·������Ҫƥ���������path·���ǣ�/show/:name�������ַ��Ϊ��/show/lydia����ô�����name�Լ�����Ӧ��ֵlydia���ᱣ����$routeParams�����У���Ҫͨ��$routeParams.name����ȡ��
    route: object���ͣ�����ָ��һϵ�������

                  ����                            	˵��
        controller     	����������
        controllerAs    ���������������
        template    	��Ӧ·����ҳ��ģ�壬�������ng-view�����˴�����Ƕ��component
        templateUrl    ��Ӧģ���·��
        resolve    �����Ի��Լ�ֵ�Զ������ʽ����·����صĿ������󶨷������ֵ��Ȼ���ִ�еĽ��ֵ���߶�Ӧ�ķ������ã�ע�뵽�������С����resolve����һ��promise������ô�����ִ�гɹ��󣬲�ע�뵽�������У���ʱ��������ȴ�resolve�е�ִ�н����
        redirectTo    �ض����ַ
        reloadOnSearch    �����Ƿ���ֻ�е�ַ�ı�ʱ���ż��ض�Ӧ��ģ�塣search��params�ı䶼�������ģ�塣Ĭ��Ϊtrue����$location.search()�����仯ʱ�����¼���·�ɡ�
        caseInsensitiveMatch    ·�����ִ�Сд
2.2.2��otherwise(params)
    ��Ӧ��·��ƥ�䲻��ʱ�������



�ܽ᣺
	1��AngularJS��·��ʵ������һ�ִ�ǰ�˵Ľ�����������ı����ǣ�������һ��urlʱ������·���������url��Ȼ��������ģ��Ƭ�Σ������뵽ng-view�С�AngularJS��·�ɸ�������ͨ���ı�url����ҳ��ľֲ�ˢ�¡�
    2������ҳ��·�ɷ�ʽ��ֱ�ۣ���Ҫ�������е�module��ϵ�����������ngRoute�������׻��ҡ�


TsApp-v2˵����
	1��ʹ��ngRouter·�����õ���һ�ֱ�﷽ʽ�����tsApp.config.js







