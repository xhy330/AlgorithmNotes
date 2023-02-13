/*
* 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数
* 的小和累加起来，叫做数组小和，求数组小和
* */
package Class3;

public class Code02_SmallSum {

	/*
	* 要求：求数组小和
	* 思想：求一个数左边比它小的数的和，不就是说概数的右边有多少个比它大的数字，然后加几次就行。
	* 	   采用归并排序的思路，每次递归都可以求出一部分的小和，并且归并排序的比较意义是会保留下来的
	* 	   每次merge后，同组内的比较是不会再有的，大大降低了时间复杂度。
	* 	   每个数的心路历程就是，我跟右组比较，只有有组存在比我大的数，那么我才会产生小和，每次merge都是如此，最后再把结果加起来
	* 细节：跟归并排序的区别就是，merge所做的事不一样
	*
	* */

	// 同等于归并排序的启动
	public static int smallSum(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		return process(arr, 0, arr.length - 1);
	}

	/*
	* arr[L..R]既要排好序，也要求小和返回
	* 返回值是左边排序merge产生的所有小和+右边排序参数的小和，然后加上左右一起merge产生的小和。
	* */
	public static int process(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		// l < r
		// 找到中点，左侧排序然后merge求小和
		// 右侧排序然后merge求小和
		// 整个一起merge求小和
		int mid = l + ((r - l) >> 1);
		return 
				process(arr, l, mid) 
				+ 
				process(arr, mid + 1, r) 
				+ 
				merge(arr, l, mid, r);
	}

	/*
	* 关键的merge函数
	* 思想：跟归并排序一样，整一个help数组来存排序后的数组，然后再拷贝回arr数组
	* 参数：help：辅助数组
	* 	   i：用来控制help数组的下标索引
	* 	   p1：主要是来遍历arr数组的左半边，从L开始
	* 	   p2：主要是来遍历arr数组的右半边，从m+1开始
	*      res：记录小和，作为累加遍历
	* 步骤：1. 在p1和p2不越界的情况下来遍历数组
	*      2. 比较arr[p1]与arr[p2]，如果右边的数比左边大，那么就加上数组右边长度大小的当前左边数组指针所指的数字(arr[p1])
	* 	   3. 否则就是不加，也就是+0
	* 	   4. 右边比左边大，就加上小和，再就是，把左边的数给拷贝到help数组的当前位置，然后左边的索引和help数组索引往后移动一个，p1++，i++
	* 	   5. 否则，如果右边的数，小于或等于左边数字，则直接拷贝右边数组，类似同上操作
	* */

	public static int merge(int[] arr, int L, int m, int r) {
		int[] help = new int[r - L + 1];
		int i = 0;
		int p1 = L;
		int p2 = m + 1;
		int res = 0;
		while (p1 <= m && p2 <= r) {
			// 求小和
			// 利用右边有序的特性，将小和个数给榨出来
			res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
			// 左右组拷贝
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= m) {
			help[i++] = arr[p1++];
		}
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {
			arr[L + i] = help[i];
		}
		return res;
	}

	// for test
	public static int comparator(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int res = 0;
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				res += arr[j] < arr[i] ? arr[j] : 0;
			}
		}
		return res;
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			if (smallSum(arr1) != comparator(arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

}
