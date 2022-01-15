package proxyObject;

/**
 * @ClassName AdminServiceImpl
 * @Description
 * @Author
 * @Date 2021/9/27 17:26
 * @Version 1.0
 **/
public class AdminServiceImpl implements AdminService{
    @Override
    public void update() {
        System.out.println("修改管理系统数据");
    }

    @Override
    public Object find() {
        System.out.println("查看管理系统数据");
        return new Object();
    }
}
