package Class3;

public class Code01_MergeSort {

	// 递归方法实现
	// 算法思想：将数组分为左右两边，左侧排好序，右侧也排好序，然后再merge在一起。
	public static void mergeSort1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process(arr, 0, arr.length - 1);
	}

	// arr[L...R]范围上，变成有序的
	// L...R    N    T(N) = 2*T(N/2) + O(N)  ->
	public static void process(int[] arr, int L, int R) {
		// 左右相等就不用排序了
		if (L == R) { // base case
			return;
		}
		// min就是L加上(R-L)/2
		int mid = L + ((R - L) >> 1);
		// 分别对L到mid和(mid+1)到R位置的数组进行归并排序，然后merge
		process(arr, L, mid);
		process(arr, mid + 1, R);
		merge(arr, L, mid, R);
	}

	/*
	* 要求：实现merge函数
	* */
	public static void merge(int[] arr, int L, int M, int R) {
		// help作为辅助数组，负责将排好序的数据存起来
		int[] help = new int[R - L + 1];
		// i是给help使用的，控制help数组
		int i = 0;
		// 左边部分的指针
		int p1 = L;
		// 右边部分的指针
		int p2 = M + 1;
		// p1与p2均不越界，谁小拷贝谁
		while (p1 <= M && p2 <= R) {
			// 如果p1的值小于p2的值，那么就将p1对应的数据赋值到help的i位置
			// 然后p1往后移动，i往后移动
			// 否则就是p2的值赋值给help的i位置后，p2往后移动，i往后移动
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		// 要么p1越界了，要么p2越界了
		// 如果p2越界了，就把p1剩下的数据全部复制到help后面
		// 每次复制完一次数据，就把p1和i指针分别往后移动一次
		while (p1 <= M) {
			help[i++] = arr[p1++];
		}
		// 同理，这是p1越界，然后把p2剩余的数据copy到help数组中
		while (p2 <= R) {
			help[i++] = arr[p2++];
		}
		// 将help排好序的数组都copy到arr中
		for (i = 0; i < help.length; i++) {
			arr[L + i] = help[i];
		}
	}

	// 非递归方法实现
	public static void mergeSort2(int[] arr) {
		// 不满足条件的数组直接舍弃
		if (arr == null || arr.length < 2) {
			return;
		}
		// N来记录数组的长度
		int N = arr.length;

		// mergeSize是左组长度
		// 相邻的mergeSize*2的部分来进行merge
		// 第一个mergeSize是左边，下一个mergeSize是右边
		int mergeSize = 1;
		// <N是希望能够覆盖整个数组
		while (mergeSize < N) { // log N
			// L来枚举每个左组的位置
			int L = 0;
			// 0.... 
			while (L < N) {
				// L...M  左组（mergeSize）
				// M是左组的右边界
				int M = L + mergeSize - 1;
				// 如果只有左组的话，那么可以直接break，再上一次排序中一定是有序的
				if (M >= N) {
					break;
				}
				//  L...M   M+1...R(mergeSize)
				// M+1是右组左边界
				// R是右组右边界
				int R = Math.min(M + mergeSize, N - 1);
				merge(arr, L, M, R);
				L = R + 1;
			}
			// 这个判断条件主要是防止溢出现象，int最大存储一个21亿的数字，而mergeSize则是翻倍地增长
			if (mergeSize > N / 2) {
				break;
			}
			// mergeSize每次往右移动一位，则mergeSize翻倍
			mergeSize <<= 1;
		}
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
			mergeSort1(arr1);
			mergeSort2(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Oops!");
	}

}
