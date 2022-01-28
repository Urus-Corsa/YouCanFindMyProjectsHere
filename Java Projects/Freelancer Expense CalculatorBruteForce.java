public class BruteForce {
    public static Utility.Helper find(int[] Arr){

        //check if Array is empty.
        Utility.Helper maxx = new Utility.Helper(0,0,0);
        int size = Arr.length;
        if(size == 0){
            return maxx;
        }
        maxx.setSum(Arr[0]);
        
        //check in all subarrays the maxximum sum.
        // store the current maxx and save it

        for(int i = 0; i < size; i++){
            Utility.Helper curr=  new Utility.Helper(i,0,0);
            
            // subarray of size j -i + 1.
            for(int j = i; j < size; j++){
                // sum from index i to j
                curr.sum += Arr[j];
                if(maxx.sum < curr.sum ){
                    maxx.sum = curr.sum;
                    maxx.beg = i;
                    maxx.end = j;
                }
            }
        }
       
        return maxx;
    }

}
