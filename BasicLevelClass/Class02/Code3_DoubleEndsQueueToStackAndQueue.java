package Class1;

public class Code3_DoubleEndsQueueToStackAndQueue {
    public static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;

        public Node(T data){
            value = data;
        }
    }

    public static class DoubleEndsQueue<T> {
        /*
        * head作为头节点
        * tail作为尾节点
        * */
        public Node<T> head;
        public Node<T> tail;

        // 头部加节点
        public void addFromHead(T value){
            // 创建一个新的节点
            Node<T> cur = new Node<T>(value);
            // 如果此时队列中没有节点，那么该节点就是作为头
            if(head == null){
                // 头尾都指向该节点
                head = cur;
                tail = cur;
            } else {
                // 如果队列中有数据，则进行以下操作
                // cur的next指向队列中的head
                cur.next = head;
                // head的上一个指针指向新节点cur
                head.last = cur;
                // 更新head节点
                head = cur;
            }
        }

        // 尾部加节点
        public void addFromBottom(T value){
            Node<T> cur = new Node<T>(value);
            if(head == null) {
                head = cur;
                tail = cur;
            }else{
                cur.last = tail;
                tail.next = cur;
                tail = cur;
            }
        }


        // 头部弹出节点
        public T popFromHead(){
            if(head == null){
                return null;
            }
            Node<T> cur = head;
            if(head == tail){
                head = null;
                tail = null;
            }else{
                head = head.next;
                cur.next = null;
                head.last = null;
            }
            return cur.value;
        }

        // 从尾部出队
        public T popFromBottom(){
            if(head == null){
                return null;
            }
            Node<T> cur = tail;
            if(head == tail){
                head = null;
                tail = null;
            }else {
                tail = tail.last;
                tail.next = null;
                cur.last = null;
            }
            return cur.value;
        }

        // 检查栈栈或者队列是否为空
         public boolean isEmpty(){
            return head == null;
         }

         // 栈的实现
         public static class MyStack<T> {
            // 定义一个双向队列
            private final DoubleEndsQueue<T> queue;

            // 构造函数初始化一个双向队列
            public MyStack(){
                queue = new DoubleEndsQueue<T>();
            }

            // 规定栈只从头部压入数据
            public void push(T value){
                queue.addFromHead(value);
            }

            // 规定数据只从头部弹出
            public T pop(){
                return queue.popFromHead();
            }

            // 检查栈是否为空
            public boolean isEmpty(){
                return queue.isEmpty();
            }
         }



    }

}
