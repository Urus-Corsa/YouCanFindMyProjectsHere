public class DivideAndConquer {

    public static Utility.Helper find(int[] Arr){

        return divideAndConquer(Arr, 0, Arr.length-1);

    }

    // Returns sum of maxium sum subArray in aa[l..h]
    static Utility.Helper divideAndConquer(int[] Arr, int l, int r){
        //if Arr length is 0, return 0,0,0
        if(Arr.length == 0){
            return new Utility.Helper(l, r, 0);
        }
      
        //if l = r it means its base Case: Only one element
        if(l == r){
            return new Utility.Helper(l, l, Arr[l]);
        }

        // Find middle point
        int midpoint = (l + r ) / 2;

        /* Return maximum of following three possible cases
            a) Maximum subarray sum in left half
            b) Maximum subarray sum in right half
            c) Maximum subarray sum such that the subarray crosses the midpoint */

        //find left sum 
        Utility.Helper lSum = divideAndConquer(Arr,l,midpoint);
        //find r sum
        Utility.Helper rSum = divideAndConquer(Arr,midpoint+1,r);
        //find crossing sum
        Utility.Helper crossingSum = findCrossingSum(Arr, l, r, midpoint);

        //return the biggest sum
        return max(lSum, rSum, crossingSum);
    }


    //start from middle go to the l, and the r, and find the biggest sum
    static Utility.Helper findCrossingSum(int[] Arr, int l, int r, int mid){
        Utility.Helper ret = new Utility.Helper(-1,-1,0);
        int sum ;
        int lsum = 0;
        int rsum = 0;

        // Include elements on left of mid.
        sum = 0;
        for(int i = mid; i >= l; i--){
            sum += Arr[i];
            if(sum  > lsum){
                lsum = sum;
                ret.beg = i;
            }
        }
        // Include elements on right of mid
        sum = 0;
        for(int i = mid + 1; i < r+1; i++){
            sum += Arr[i];
            if(sum  > rsum){
                rsum = sum;
                ret.end = i;
            }
        }
        ret.setSum(lsum+rsum);
        if(ret.getBeg() == -1 || ret.getEnd() == -1){
            ret.setSum(Arr[mid]);
            ret.setBeg(mid);
            ret.setEnd(mid);
        }


        return ret;
    }

    //returns the bigger of the 3 sums provided
    static Utility.Helper max(Utility.Helper h1, Utility.Helper h2, Utility.Helper h3){
        return max(max( h1, h2), h3);
    }
    //returns the bigger of the 2 sums provided
    static Utility.Helper max(Utility.Helper h1, Utility.Helper h2){
   
        if(h1.sum > h2.sum){
            return h1;
        }
        return h2;
    }

}
