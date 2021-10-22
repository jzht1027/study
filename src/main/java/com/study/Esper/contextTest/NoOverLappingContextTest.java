package com.study.Esper.contextTest;


import com.espertech.esper.client.*;

/**
 * @ClassName NoOverLappingContextTest
 * @Description
 * @Author
 * @Date 2021/3/30 13:23
 * @Version 1.0
 **/
public class NoOverLappingContextTest {
    public static void main(String[] args) {
        EPServiceProvider epservice = EPServiceProviderManager.getDefaultProvider();
        EPAdministrator admin = epservice.getEPAdministrator();
        EPRuntime runtime = epservice.getEPRuntime();

        String start = StartEvent.class.getName();
        System.out.println("start:"+start);
        String end = EndEvent.class.getName();
        System.out.println("end:"+end);
        String other = OtherEvent.class.getName();
        System.out.println("other:"+other);
        // 以StartEvent事件作为开始条件，EndEvent事件作为结束条件
        String epl1 = "create context NoOverLapping start " + start + " end " + end;
        String epl2 = "context NoOverLapping select * from " + other;

        admin.createEPL(epl1);
        EPStatement state = admin.createEPL(epl2);

        state.addListener(new ContextPropertiesListener2());

        StartEvent s = new StartEvent();
        System.out.println("sendEvent: StartEvent");
        runtime.sendEvent(s);

        OtherEvent o = new OtherEvent();
        o.setId(2);
        System.out.println("sendEvent: OtherEvent");
        runtime.sendEvent(o);

        OtherEvent o2 = new OtherEvent();
        o2.setId(4);
        System.out.println("sendEvent: OtherEvent");
        runtime.sendEvent(o2);

        EndEvent e = new EndEvent();
        System.out.println("sendEvent: EndEvent");
        runtime.sendEvent(e);


        String epl3 ="create context NineToFive start (*, 0, *, *, *) end (*, 24, *, *, *)";

        EPStatement state3 = admin.createEPL(epl3);


    }
}
