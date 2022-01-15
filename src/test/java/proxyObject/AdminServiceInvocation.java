package proxyObject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName AdminServiceInvocation
 * @Description
 * @Author
 * @Date 2021/9/27 17:45
 * @Version 1.0
 **/
public class AdminServiceInvocation implements InvocationHandler {

    private Object target;

    public AdminServiceInvocation(Object object){
        this.target = object;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("判断用户使用否权限进行操作");
        Object object = method.invoke(target);
        System.out.println("记录用户执行操作");
        return object;
    }
}
