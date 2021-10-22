package com.study.Esper;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class AppleListener implements UpdateListener {

    @Override
    public void update(EventBean[] eventBeans, EventBean[] eventBeans1) {
        if (eventBeans != null) {
//            Double avg = (Double) eventBeans[0].get("avg(price)");
//            System.out.println("Apple's average price is " + avg);

            EventBean event = eventBeans[0];
            // 当加上注解@EventRepresentation(array=true)时，结果事件类型为数组而不是Map。
            // array=false时，也就是默认情况，结果事件类型为数组是Map。
            System.out.println("Sum Price: " + event.get("sum(price)") + ", Event Type is " + event.getEventType().getUnderlyingType());

        }
    }
}
