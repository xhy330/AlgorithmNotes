package Class1;

public class Code1_ReverseList{

    // 单链表定义
    public static class Node{
        public int value;
        public Node next;

        public Node(int data){
            value = data;
        }
    }

    // 双链表定义
    public static class DoubleNode{
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data){
            value = data;
        }
    }

    /*
    * 要求：单链表反转
    * 思想：就是简单的头插法使用。
    * 步骤：1. 定义两个空指针pre和next只是记录环境而已
    *      2. 首先判断进入的链表是否是空的，如果为空则就直接返回pre
    *      3. next记录当前节点的下一个节点
    *      4. 当前节点的下一个节点记录pre存储的值（pre来存上一个节点）
    *      5. 当前节点的值赋值给pre
    *      6. 指针往后移next给到head
    * */
    public static Node reverseLinkedList(Node head){
        Node pre = null;
        Node next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


    public static DoubleNode reverseDoubleList(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

}
