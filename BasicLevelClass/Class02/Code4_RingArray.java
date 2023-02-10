package Class1;
/*
 * 循环队列的实现
 * 如何来设计循环？
 * 需要哪些变量
*/

public class Code4_RingArray {
    public static class MyQueue{
        // 数组
        private int[] arr;

        // 入队的下标pushi
        private int pushi;

        // 出队的下标polli
        private int polli;

        // 整个队列中所含有元素的个数
        private int size;

        // 队列的最大长度限制
        private final int limit;

        // 构造函数来初始化队列
        public MyQueue(int limit){
            // 初始化一个长度为limit的队列
            arr = new int[limit];
            // 默认的pushi的位置在开头
            pushi = 0;
            // 默认的polli的位置在开头
            polli = 0;
            // 默认的大小为0
            size = 0;
            // 自己来定义整个队列的最大长度
            this.limit = limit;
        }

        /*
        * 要求：手动实现入队
        * 步骤：1.检查一下当前队列是否是满队列，如果满队列则要即使报错
        *      2.入队以后，size则要及时更新++
        *      3.将数值value放入数组arr的pushi的位置
        *      4.将pushi更新，如果小于limit-1这个位置，则+1，否则就会从0开始
        * */
        public void push(int value){
            if(size == limit){
                throw new RuntimeException("栈满了，不能加了！");
            }
            size++;
            arr[pushi] = value;
            pushi = nextIndex(pushi);
        }

        /*
        * 要求：手动实现出队
        * 步骤：
        *
        * */
        public int pop(){
            if(size == 0){
                throw new RuntimeException("栈空了，不能再拿了");
            }
            size--;
            int ans = arr[polli];
            polli = nextIndex(polli);
            return ans;
        }

        // 如果i小于limit-1位置，则让i往前走，否则满队i就回到0，重头开始
        private int nextIndex(int i){
            return i < limit-1 ? i+1 : 0;
        }
    }
}
