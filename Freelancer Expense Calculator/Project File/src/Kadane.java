
public class Kadane {
    public static Utility.Helper find(int[] Arr){
        Utility.Helper ret = new Utility.Helper(0,0,0);
        //stores the maximum sum subarray found so far
        int maxSum = -1;
        // stores the maximum sum of subarray ending at the current position
        int maxEndingSum = 0;
        int start  = -1, end  = -1, s  = 0;
        // size of array
        int size = Arr.length;


        // the subarray with the maximum sum, we maintain indices whenever we get the maximum sum.   
        for(int i = 0; i <size; i++){

            // update the maximum sum of subarray "ending" at index `i` (by adding the
           // current element to maximum sum ending at previous index `i-1`)
            maxEndingSum = maxEndingSum + Arr[i];

            // update the result if the current subarray sum is found to be greater
            if(maxSum < maxEndingSum){
                maxSum = maxEndingSum;
                start  = s;
                end  = i;

            }
            // if the maximum sum is negative, set it to 0 (which represents
             // an empty subarray)
            if(maxEndingSum < 0){
                maxEndingSum = 0;
                s  = i + 1;
            }

        }
        
        //if sum does not change and is negative
        if(maxSum == -1 ){
            return ret;
        }
      
        return new Utility.Helper(start ,end ,maxSum);
    }
}
