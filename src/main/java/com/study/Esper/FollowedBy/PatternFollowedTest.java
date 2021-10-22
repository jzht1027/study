package com.study.Esper.FollowedBy;


import com.espertech.esper.client.*;

/**
 * @ClassName PatternFollowedTest
 * @Description
 * @Author
 * @Date 2021/3/24 15:50
 * @Version 1.0
 **/
public class PatternFollowedTest {
    public static void main(String[] args) {
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();
        EPAdministrator admin = epService.getEPAdministrator();
        EPRuntime runtime = epService.getEPRuntime();

        String followed = FollowedEvent.class.getName();

        String epl = "select * from pattern[every a=" + followed + " -> b=" + followed + "(size < a.size)]";
        System.out.println("EPL: " + epl+"\n");
        EPStatement stat = admin.createEPL(epl);
        stat.addListener(new PatternFollowedListener());

        FollowedEvent f1 = new FollowedEvent();
        f1.setSize(4);
        System.out.println("Send Event1: " + f1);
        runtime.sendEvent(f1);
        System.out.println();

        FollowedEvent f2 = new FollowedEvent();
        f2.setSize(3);
        System.out.println("Send Event2: " + f2);
        runtime.sendEvent(f2);
        System.out.println();

        FollowedEvent f3 = new FollowedEvent();
        f3.setSize(2);
        System.out.println("Send Event3: " + f3);
        runtime.sendEvent(f3);
    }
}
