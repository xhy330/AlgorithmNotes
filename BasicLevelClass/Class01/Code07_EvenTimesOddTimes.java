package class01;

public class Code07_EvenTimesOddTimes {

	/*
	* 问题描述：一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并且打印这个数
	* 算法思想：某个数跟自己异或的结果是0，跟0异或的结果则是自己本身，所以，该数的个数是奇数次就意味着
	* 		  该数异或起来的结果是自己本身。其他的偶数个的数异或起来则为0，不影响结果。
	* 解决步骤：将数组的所有数异或起来，剩下的那个数字一定就是出现奇数次个数的数字。
	* */
	//
	public static void printOddTimesNum1(int[] arr) {
		// 启动值，0不影响异或的结果。
		int eO = 0;
		// 将e0与数组所有的数字异或起来
		for (int cur : arr) {	//int i=0; i<arr.length; i++
			eO ^= cur;	//eo ^= arr[i];
		}
		System.out.println(eO);
	}

	/*
	* 问题描述：如何提取一个数的最右侧的二进制位的1
	* 算法思想：N = 01101010000
	* 		 ～N = 10010101111
	* 	   ～N+1 = 10010110000
	* 		然后再与N与一起，则发现除了最右边的1之外，其他的位都是0
	* 解决步骤：N&((~N)+1)
	* */


	/*
	* 问题描述：一个数组种有两种数出现了奇数次，其他数字出现了偶数次，求这两种数（假设这两种数分别为a、b。ab一定不相等）
	*
	* 算法思想：1. 将该数组的所有值异或起来，起结果肯定为：eor = a ^ b != 0（需要分别知道a、b是什么）
	* 		  2. 由eor不等于0可知，eor一定在某个位上有1，假设eor的第八位上是1，也就是说明a的第8位!=b的第8位。
	* 		  3. 故整个数组可以分为两类，第8位为1的数和第8位为0的数，则a、b是分别位于这两类数中
	* 		  4. 从新定义一个eor' 再一次异或arr数组且只异或那些第8位为1的数（这样的话，第8位为1的数中出现偶数次数的数都会为0，剩下的只留下
	* 			 奇数次的个数，a或者b。不能再次异或第8位为0的数，无效，0无论奇偶异或的结果还是0，分辨不出来a或b）
	* 		  5. 则另一个数就是 eor ^ eor'（假设第4步出来的数字为a，则有eor'=a，又已知eor = a ^ b，则b = a ^ eor）
	* 		  6. 第8位可以是最右边的1
	*
	* 算法步骤：1. 先将整个数组异或起来得到eor，其结果就是eor=a^b
	* 		  2. 定义一个rightOne，提取eor最右边的1，也就是说明在该位上a与b肯定不相等
	* 		  3. 对整个arr进行异或，只对与rightOne与起来的结果不为0的元素异或，也就是思想中第8位为1的进行异或，结果肯定是
	* 			 其中一个奇数元素
	* 		  4. 将得到的结果与eor异或，得到第二个奇数个数元素
	* */

	// arr中，有两种数出现奇数次
	public static void printOddTimesNum2(int[] arr) {
		int eor = 0;
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
		}
		// eor = a ^ b
		// eor != 0
		// eor必然有一个位置上是1
		// 提取出最右边的1
		// 0110010000
		// 0000010000 就是rightone
		int rightOne = eor & (~eor + 1);

		int onlyOne = 0;	//eor'
		for (int i = 0; i < arr.length; i++) {
			// 数组与rightOne与出来的结果不为0，则说明该数组中的某个元素与rightOne的1相同
			if((arr[i] & rightOne) != 0){
				onlyOne ^= arr[i];
			}
		}
		System.out.println(onlyOne + " " + (eor ^ onlyOne));
	}

	public static void main(String[] args) {
		int a = 5;
		int b = 7;

		a = a ^ b;
		b = a ^ b;
		a = a ^ b;

		System.out.println(a);
		System.out.println(b);

		int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
		printOddTimesNum1(arr1);

		int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
		printOddTimesNum2(arr2);

	}

}
