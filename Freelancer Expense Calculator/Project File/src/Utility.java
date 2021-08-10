
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Utility {
    public static class Timer {
        public static void main(String[] args) throws Exception {
            //saves lines from input
            ArrayList<String> lines = Utility.readFile("maxSumtest.txt");

            //saves array of arrays and then convers read lines into them
            int[][] arrs = new int[lines.size()][];
            for(int i = 0;i < lines.size();i++){
                arrs[i] = Utility.generateArrayFromString(lines.get(i));
            }
            //prints brute divide and kadane for numbers read from files
            for(int[] arr : arrs){
                System.out.println("Brute: " + BruteForce.find(arr));
                System.out.println("Divide: " + BruteForce.find(arr));
                System.out.println("Kadane: " + BruteForce.find(arr));
                System.out.println("~~~~~~~~~~~~~~");
            }

            System.out.println("\n\n\n");



            //prints how long it took for random arrays of given sizes
            int[] sizes = new int[]{50,200,500,1000,2000,5000,10000,50000,100000,1000000,10000000};
            for (int size:sizes){
                System.out.println("Size: " + size);
                if(size <= 2000){
                    System.out.println("Brute: " + testBrute(size));
                }
                System.out.println("Divide: " + testDivide(size));
                System.out.println("Kadane: " + testKadane(size));
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            }

        }
        //generate random array 10 times, find the max sum, and return time spent on searching / 10 because there were
        //10 searches of length arrsize (same for all 3 algorithms)
        public static double testDivide(int arrize){
            double totaltime = 0;
            for(int i = 0; i < 10; i++){
                int[] arr = Utility.generateArray(arrize);
                long before = System.currentTimeMillis();
                DivideAndConquer.find(arr);
                long after = System.currentTimeMillis();
                long diff = after - before;
                totaltime += diff;
            }

            return totaltime  / 10;
        }
        public static double testBrute(int arrize){
            double totaltime = 0;
            for(int i = 0; i < 10; i++){
                int[] arr = Utility.generateArray(arrize);
                long before = System.currentTimeMillis();
                BruteForce.find(arr);
                long after = System.currentTimeMillis();
                long diff = after - before;
                totaltime += diff;
            }

            return totaltime  / 10;
        }
        public static double testKadane(int arrize){
            double totaltime = 0;
            for(int i = 0; i < 10; i++){
                //generates array, and saves timestamp before and after, adds to total time, and returns total time / 10 because it ran 10 times
                int[] arr = Utility.generateArray(arrize);
                long before = System.currentTimeMillis();
                Kadane.find(arr);
                long after = System.currentTimeMillis();
                long diff = after - before;
                totaltime += diff;
            }

            return totaltime  / 10;
        }
    }




    //we save beginning index, save index and sum in the Helper class
    public static class Helper{
        //beginning index, ending index, and sum
        int beg;
        int end;
        int sum;

        public Helper(int beg, int end, int sum) {
            this.beg = beg;
            this.end = end;
            this.sum = sum;
        }

        public int getBeg() {
            return beg;
        }

        public int getEnd() {
            return end;
        }

        public int getSum() {
            return sum;
        }

        public void setBeg(int beg) {
            this.beg = beg;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "Started: " + beg + ", finished: " + end + ", total sum: " + sum;
        }

        //its equal if sum, beg, and end are the same
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Helper){
                return this.sum == ((Helper) obj).sum && this.end == ((Helper) obj).end && this.beg == ((Helper) obj).beg;
            }
            return false;
        }
    }

    //generates random array of length length
    public static int[] generateArray(int length){
        int[] arr = new int[length];
        Random random = new Random();
        for(int i = 0; i <length; i++){
            arr[i] = random.nextInt(600) - 300;
        }
        return arr;
    }
    //reads file and returns all the lines in it
    public static ArrayList<String> readFile(String fileName) throws Exception {
        ArrayList<String> ret = new ArrayList<>();
        Scanner sc = new Scanner(Path.of(fileName));
        while (sc.hasNextLine()){
            ret.add(sc.nextLine());
        }
        return ret;
    }
    //splits line, and parses integers and returns an array that holds those integers
    //This is for reading from input
    public static int[] generateArrayFromString(String line){
        String[] nums = line.strip().split("\\s+");
        int[] numbers = new int[nums.length];
        int pos = 0;
        for(String n : nums){
            numbers[pos++] = Integer.parseInt(n);
        }
        return numbers;
    }
}
