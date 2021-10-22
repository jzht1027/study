package com.study.Esper.contextTest;


import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
 * @ClassName ContextPropertiesListener2
 * @Description
 * @Author
 * @Date 2021/3/29 11:23
 * @Version 1.0
 **/
public class ContextPropertiesListener2 implements UpdateListener {
    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        if (newEvents != null) {
            EventBean event = newEvents[0];
            System.out.println("Class:" + event.getUnderlying().getClass().getName() + ", id:" + event.get("id"));
//            System.out.println("context.name " + event.get("name") + ", context.id " + event.get("id")
//                    + ", context.key1 " + event.get("key1")
//                    + ", context.key2 " + event.get("key2"));
        }
    }
}
