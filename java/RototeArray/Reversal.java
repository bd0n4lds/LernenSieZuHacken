//For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4]. 
//How many different ways do you know to solve this problem?

public class Reversal {

public static void rotate(int[] arr, int order) {	
	if (arr == null || arr.length==0 || order < 0) {
		throw new IllegalArgumentException("Illegal argument!");
	}
 
	if(order > arr.length){
		order = order %arr.length;
	}
 
	//length of first part
	int a = arr.length - order; 
 
	reverse(arr, 0, a-1);
	reverse(arr, a, arr.length-1);
	reverse(arr, 0, arr.length-1);
 
}
 
public static void reverse(int[] arr, int left, int right){
	if(arr == null || arr.length == 1) 
		return;
 
	while(left < right){
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
		left++;
		right--;
	}	
}
	public static void main(String[] args) {
		
	}
}