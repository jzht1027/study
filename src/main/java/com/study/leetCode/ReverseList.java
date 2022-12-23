package com.study.leetCode;

/**
 * 链表反转-迭代、递归
 */
public class ReverseList {
    public static void main(String[] args){
        ListNode list5 = new ListNode(5,null);
        ListNode list4 = new ListNode(4,list5);
        ListNode list3 = new ListNode(3,list4);
        ListNode list2 = new ListNode(2,list3);
        ListNode list1 = new ListNode(1,list2);

        //迭代
//        ListNode iterate = iterate(list1);
        //递归
        ListNode recursion = recursion(list1);
    }

    //链表反转-递归
    public static ListNode recursion(ListNode head){
        if(head==null || head.nest == null){
            return head;
        }

        ListNode newHead = recursion(head.nest);

        head.nest.nest = head;
        head.nest = null;
        return newHead;
    }

    //链表反转-迭代
    // 1->2->3->4->5  反转为： 5->4->3->2->1
    public static ListNode iterate(ListNode head){
        ListNode prev = null; //保存前后一个节点
        ListNode next; // 保存下一个节点
        ListNode curr = head; //保存当前节点
        while (curr!=null){
            next= curr.nest;
            curr.nest = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    static class ListNode{
        int val;
        ListNode nest;

        public ListNode(int val,ListNode next){
            this.val = val;
            this.nest = next;
        }
    }
}
