package proxyObject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName ProxyTest
 * @Description
 * @Author
 * @Date 2021/9/27 17:31
 * @Version 1.0
 **/
public class ProxyTest {
    public static void main(String[] args){
        AdminService adminService = new AdminServiceImpl();

        System.out.println("================静态代理测试=================");
        //静态代理测试
        AdminServiceProxy proxy = new AdminServiceProxy(adminService);
        proxy.update();
        System.out.println("=================================");
        proxy.find();

        System.out.println("================动态代理测试=================");
        //动态代理测试
        // 方法一
        System.out.println("============ 方法一 ==============");
        System.out.println("代理的目标对象：" + adminService.getClass());

        AdminServiceInvocation adminServiceInvocation = new AdminServiceInvocation(adminService);

        AdminService Dynamicproxy = (AdminService) new AdminServiceProxyDynamic(adminService, adminServiceInvocation).getPersonProxy();

        System.out.println("代理对象：" + Dynamicproxy.getClass());

        Object obj = Dynamicproxy.find();
        System.out.println("find 返回对象：" + obj.getClass());
        System.out.println("----------------------------------");
        Dynamicproxy.update();

        //方法二
        System.out.println("============ 方法二 ==============");
        AdminService target = new AdminServiceImpl();
        AdminServiceInvocation invocation = new AdminServiceInvocation(adminService);
        AdminService Dynamicproxy2 = (AdminService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), invocation);

        Object obj2 = Dynamicproxy2.find();
        System.out.println("find 返回对象：" + obj2.getClass());
        System.out.println("----------------------------------");
        Dynamicproxy2.update();

        //方法三
        System.out.println("============ 方法三 ==============");
        final AdminService target3 = new AdminServiceImpl();
        AdminService Dynamicproxy3 = (AdminService) Proxy.newProxyInstance(target3.getClass().getClassLoader(), target3.getClass().getInterfaces(), new InvocationHandler() {

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("判断用户是否有权限进行操作");
                Object obj = method.invoke(target3, args);
                System.out.println("记录用户执行操作的用户信息、更改内容和时间等");
                return obj;
            }
        });

        Object obj3 = Dynamicproxy3.find();
        System.out.println("find 返回对象：" + obj3.getClass());
        System.out.println("----------------------------------");
        Dynamicproxy3.update();

    }
}
