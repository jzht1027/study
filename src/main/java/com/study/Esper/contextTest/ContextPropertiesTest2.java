package com.study.Esper.contextTest;


import com.espertech.esper.client.*;

/**
 * @ClassName ContextPropertiesTest2
 * @Description
 * @Author
 * @Date 2021/3/29 11:24
 * @Version 1.0
 **/
public class ContextPropertiesTest2 {
    public static void main(String[] args) {
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();
        EPAdministrator admin = epService.getEPAdministrator();
        EPRuntime runtime = epService.getEPRuntime();

        String esb = ESB.class.getName();
        // 创建context
        String epl1 = "create context esbtest partition by id,price from " + esb;
        // context.id针对不同的esb的id,price建立一个context，如果事件的id和price相同，则context.id也相同，即表明事件进入了同一个context
        String epl2 = "context esbtest select context.id,context.name,context.key1,context.key2 from " + esb;

        admin.createEPL(epl1);
        EPStatement state = admin.createEPL(epl2);
        state.addListener(new ContextPropertiesListener2());

        ESB e1 = new ESB();
        e1.setId(1);
        e1.setPrice(20);
        System.out.println("sendEvent: id=1, price=20");
        runtime.sendEvent(e1);


        ESB e2 = new ESB();
        e2.setId(2);
        e2.setPrice(30);
        System.out.println("sendEvent: id=2, price=30");
        runtime.sendEvent(e2);

        ESB e3 = new ESB();
        e3.setId(1);
        e3.setPrice(20);
        System.out.println("sendEvent: id=1, price=20");
        runtime.sendEvent(e3);

        ESB e4 = new ESB();
        e4.setId(4);
        e4.setPrice(20);
        System.out.println("sendEvent: id=4, price=20");
        runtime.sendEvent(e4);
    }
}
