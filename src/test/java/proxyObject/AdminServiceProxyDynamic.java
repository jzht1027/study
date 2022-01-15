package proxyObject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @ClassName AdminServiceDynamicProxy
 * @Description  DynamicProxy 动态代理
 * @Author
 * @Date 2021/9/27 17:48
 * @Version 1.0
 **/
public class AdminServiceProxyDynamic {
    private Object target;
    private InvocationHandler invocationHandler;
    public AdminServiceProxyDynamic(Object object, InvocationHandler invocationHandler){
        this.target = object;
        this.invocationHandler = invocationHandler;
    }

    public Object getPersonProxy(){
        Object object = Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),invocationHandler);
        return object;
    }
}
