package Class1;

public class Code2_DeleteGivenValue {
    public static class Node{
        public int value;
        public Node next;

        public Node(int data){
            value = data;
        }
    }

    /*
    * 要求： 删除单链表中值为num的节点
    * 思想：首先会注意到，单链表中可能会出现第一个节点就是待删除节点的情况，所以我们
    *      要先检索有无这种情况，直接把首先出现的给跳过。后续代码的逻辑就是，cur往后走，
    *      只要没有碰见num，pre就跟cur保持同步。如果碰见了num，就把当前cur所指向的
    *      节点给跳过，不管有都少个都跳过。
    * 变量：head记录则是当前节点，pre基本保持是cur的前一个节点方便删除
    * 步骤：1. 检查开始的头节点是否是待删除的节点，如果是的话，则直接往后变量，跳过就行，找到第一个不是待删除节点作为头节点即可。
    *      2. pre和cur都是从该链表的首部开始往后遍历
    *      3. cur往后变量，如果找到与num值相等的节点，执行4，否则执行5
    *      4. pre是cur的前一个节点，直接pre.next = cur.next
    *      5. 更新pre，pre也要往前走，始终跟cur保持间隔一个节点 pre = cur
    *      6. cur往前走，cur = cur.next
    * */
    public static Node removeValue(Node head, int num){
            while (head != null){
                if(head.value != num){
                    break;
                }
                head = head.next;
            }
            Node pre = head;
            Node cur = head;
            while (cur != null){
                if(cur.value == num){
                    pre.next = cur.next;
                } else {
                    pre = cur;
                }
                cur = cur.next;
            }

            return head;
    }
}
