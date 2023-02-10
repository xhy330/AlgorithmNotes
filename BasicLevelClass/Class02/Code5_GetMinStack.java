package Class1;
import java.util.Stack;

public class Code5_GetMinStack {

    /*
     * 要求：实现获得栈的最小值，实时的，并且要求时间复杂度为O(1)，对比MyStack2来说，用时间还空间，stackMin栈中少导入几次数据
     * 思想：建立两个栈，一个栈来正常存储数据，另一个栈用来同步记录目标栈的实时的最小值数据。
     * 步骤：1. 使用系统默认的两个栈，分别记为stackData，stackMin
     *      2. 构造函数来初始化这两个栈
     *      3. 实现入栈
     *      4. 实现出栈
     *      5. 获取栈中最小值
     * */
    public static class MyStack1{
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        private MyStack1(){
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        /*
        * 要求：单调栈入栈
        * 步骤：1. 判断stackMin是否为空，如果为空，则把newNum压入栈中
        *      2. 如果newNum小于等于栈中的最小值(stackMin的peek())，那么就将其newNum压入栈
        *      3. 对比MyStack2，如果newNum大于最小值就会复制一份最小的然后重新压入，本栈中则不这么操作
        *         而是放在后续出栈中加一次比较
        *      4. stackData栈正常压栈
        * */
        public void push(int newNum){
            if(this.stackMin.isEmpty()){
                this.stackMin.push(newNum);
            }else if(newNum <= this.getMin()){
                this.stackMin.push(newNum);
            }
            this.stackData.push(newNum);
        }

        /*
        * 要求：实现单调栈的出栈
        * 步骤：1. 先是判空
        *      2. 如果不是空，则value保存stackData的出栈值
        *      3. 判断出栈的值是否等于最小值(stackMin的peek值)，如果相等，则也将stackMin出栈
        *      4. 返回value
        * */
        public int pop(){
            if(this.stackMin.isEmpty()){
                throw new RuntimeException("Your stack is empty.");
            }
            int value = this.stackData.pop();
            if(value == this.getMin()){
                this.stackMin.pop();
            }
            return value;
        }


        // 获取stackMin的栈顶值
        public int getMin(){
            if(this.stackMin.isEmpty()){
                throw new RuntimeException("Your stack is empty.");
            }
            int value = this.stackMin.peek();
            return value;
        }

    }



    /*
     * 要求：实现获得栈的最小值，实时的，并且要求时间复杂度为O(1)，对比MyStack1来说，用空间换时间，少了一个对比。
     * 思想：建立两个栈，一个栈来正常存储数据，另一个栈用来同步记录目标栈的实时的最小值数据。
     * 步骤：1. 使用系统默认的两个栈，分别记为stackData，stackMin
     *      2. 构造函数来初始化这两个栈
     *      3. 实现入栈
     *      4. 实现出栈
     *      5. 获取栈中最小值
     * */
    public static class MyStack2{
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack2(){
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        /*
        * 要求：双栈实现入栈
        * 思路：分类讨论stackMin的入栈数据
        * 步骤：1. 如果stackMin为空，则就直接同步stackData的入栈数据，也就是newNum
        *      2. 如果newNum小于stackMin的最小值，那么就要更新stackMin的栈顶数据，也就是将nuwNum压入stackMin的栈顶
        *      3. 如果newNum大于stackMin的最小值(栈顶数据)，那么就将该栈顶数据重新压入stackMin这个栈
        *      4. stackData不管咋样都是压入newNum这个数据
        * */
        public void push(int newNum){
            if(this.stackMin.isEmpty()){
                this.stackMin.push(newNum);
            }else if(newNum < this.getMin()){
                this.stackMin.push(newNum);
            }else{
                int newMin = this.stackMin.peek();
                this.stackMin.push(newMin);
            }
            this.stackData.push(newNum);
        }

        /*
         * 要求：实现单调栈的出栈
         * 步骤：1. 先是判空
         *      2. 如果不是空，则stackMin出栈，要与stackData保持同步
         *      3. 返回stackData的栈顶值
         * */
        public int pop(){
            if(this.stackData.isEmpty()){
                throw new RuntimeException("Your stack is empty.");
            }
            this.stackMin.pop();
            return this.stackData.pop();
        }

        // 获取stackMin的栈顶值
        public int getMin(){
            if(this.stackMin.isEmpty()){
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }

    public static void main(String[] args) {

        MyStack1 stack1 = new MyStack1();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());

        System.out.println("=============");

        MyStack1 stack2 = new MyStack1();
        stack2.push(3);
        System.out.println(stack2.getMin());
        stack2.push(4);
        System.out.println(stack2.getMin());
        stack2.push(1);
        System.out.println(stack2.getMin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getMin());
    }
}
