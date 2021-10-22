package com.study.Esper.FollowedBy;

/**
 * @ClassName FollowedEvent
 * @Description
 * @Author
 * @Date 2021/3/24 15:48
 * @Version 1.0
 **/
public class FollowedEvent {
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "FollowedEvent{" + "size=" + size + '}';
    }

}
