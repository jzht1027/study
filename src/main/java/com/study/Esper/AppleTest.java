package com.study.Esper;


import com.espertech.esper.client.*;

public class AppleTest {

    public static void main(String[] args) throws InterruptedException {
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();

        EPAdministrator admin = epService.getEPAdministrator();

        String product = Apple.class.getName();
        //win:length_batch(size) 攒够size条数据后触发AppleListener监听，并清空队列。再攒满了再触发
//        String epl = "select avg(price) from " + product + ".win:length_batch(3)";

        String epl  = "@EventRepresentation(array=true) select sum(price) from " + product + ".win:length_batch(2)";
        String epl2 = "@Name(\"23424\")select sum(price) from " + product + ".win:length_batch(2)";
        String epl3 = "@Drop select sum(price) from " + product + ".win:length_batch(1)";

        UpdateListener listen = new AppleListener();
        EPStatement state1 = admin.createEPL(epl);
        state1.addListener(listen);

        EPStatement state2 = admin.createEPL(epl2);
        state2.addListener(listen);
        System.out.println("epl2's name is " + state2.getName());

        EPStatement state3 = admin.createEPL(epl3);
        state3.addListener(listen);

        EPRuntime runtime = epService.getEPRuntime();

        Apple apple1 = new Apple();
        apple1.setId(1);
        apple1.setPrice(5);
        runtime.sendEvent(apple1);

        Apple apple2 = new Apple();
        apple2.setId(2);
        apple2.setPrice(2);
        runtime.sendEvent(apple2);

        Apple apple3 = new Apple();
        apple3.setId(3);
        apple3.setPrice(3);
        runtime.sendEvent(apple3);
        }
}
