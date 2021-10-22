package com.study.Esper.FollowedBy;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
 * @ClassName PatternFollowedListener
 * @Description
 * @Author
 * @Date 2021/3/24 15:49
 * @Version 1.0
 **/
public class PatternFollowedListener implements UpdateListener {

    @Override
    public void update(EventBean[] eventBeans, EventBean[] eventBeans1) {
        if (eventBeans != null) {
            for (int i = 0; i < eventBeans.length; i++) {
                System.out.println();
                EventBean event = eventBeans[i];
                System.out.println("Result:");
                System.out.println(event.get("a") + " " + event.get("b"));
            }
        }
    }
}
