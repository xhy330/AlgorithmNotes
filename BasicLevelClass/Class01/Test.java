package class01;/*
* 关于异或运算的问题
* 如何不通过额外的变量来交换A B
* */

public class Test {
    public static void main(String[] args) {

        // 异或的内容是两个不同的东西
        int a = 1;
        int b = 2;

        System.out.println("交换前");
        System.out.println("a=" + a);
        System.out.println("b=" + b);

        a = a ^ b;  // a = a ^ b,           b = b
        b = a ^ b;  // a = a ^ b,           b = a ^ b ^ b = a
        a = a ^ b;  // a = a ^ b ^ a = b,   b = a

        System.out.println("交换后");
        System.out.println("a=" + a);
        System.out.println("b=" + b);


        // 如果两个异或的是同一种东西，那么就会出现问题
        // 自己跟自己倒的话，就会变成0
        int[] arr = {3, 1, 100};

        System.out.println("交换前：");
        System.out.println(arr[0]);
        System.out.println(arr[2]);

        swap(arr, 0, 2);

        System.out.println("交换后：");
        System.out.println(arr[0]);
        System.out.println(arr[2]);

    }

    public static void swap(int[] arr, int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
