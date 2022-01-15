package proxyObject;

/**
 * @ClassName AdminServiceProxy
 * @Description 静态代理
 * @Author
 * @Date 2021/9/27 17:27
 * @Version 1.0
 **/
public class AdminServiceProxy implements AdminService{

    private AdminService adminService;

    public AdminServiceProxy (AdminService adminService){
        this.adminService = adminService;
    }

    @Override
    public void update() {
        System.out.println("判断用户使用否权限进行update");
        adminService.update();
        System.out.println("记录用户执行update操作");
    }

    @Override
    public Object find() {
        System.out.println("判断用户使用否权限进行find");
        System.out.println("记录用户执行find操作");
        return adminService.find();
    }
}
