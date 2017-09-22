/**
 * Created by wenjunyang on 22/9/17.
 */
public class MaxSubList {

    public static int[] find_cross(int[] arr, int low, int mid, int high){
        int maxleft = 0;
        int sum = 0;
        int leftsum = Integer.MIN_VALUE;
        for (int i = mid; i >= low; i--){
            sum+=arr[i];
            if (sum > leftsum){
                leftsum = sum;
                maxleft = i;
            }
        }

        int maxright = 0;
        sum = 0;
        int rightsum = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= high; i++){
            sum+=arr[i];
            if (sum > rightsum){
                rightsum = sum;
                maxright = i;
            }
        }

        int[] outcome = new int[3];
        outcome[0] = maxleft;
        outcome[1] = maxright;
        outcome[2] = rightsum + leftsum;
        return outcome;
    }

    public static int[] find_nocross(int[] arr, int low, int high) {

        int[] outcome = new int[3];

        if (low == high) {
            outcome[0] = low;
            outcome[1] = high;
            outcome[2] = arr[low];
            return outcome;
        } else {
            int mid = (low + high) / 2;
            int[] left = find_nocross(arr, low, mid);
            int leftsum = left[2];

            int[] right = find_nocross(arr, mid+1, high);
            int rightsum = left[2];

            int[] cross = find_cross(arr, low, mid, high);
            int crosssum = cross[2];

            if ((leftsum >= rightsum) && (leftsum >= crosssum)) {
                return left;
            }else if ((rightsum >= leftsum) && (rightsum >= crosssum)) {
                return right;
            }else {
                return cross;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1,20,-9,3,-8,7};
        int[] result = find_nocross(arr1,0,arr1.length-1);
        System.out.println("The maximum subarray starts from index "+result[0]+" and ends in index " + result[1] + ", the maximum sum is "+result[2]);

        int[] arr2 = {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
        int[] result2 = find_nocross(arr2,0,arr2.length-1);
        System.out.println("The maximum subarray starts from index "+result2[0]+" and ends in index " + result2[1] + ", the maximum sum is "+result2[2]);

    }
}
