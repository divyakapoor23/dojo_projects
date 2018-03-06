import java.util.ArrayList;

public class BasicJava {
    public static void main(String[] args) {
        BasicJava.print1To255();
        BasicJava.printOdds1To255();
        BasicJava.printSum();
        BasicJava.iterateThruArray(new int[] {1,3,5,7,9,13});
        BasicJava.findMax(new int[] {-4,-2,-3,-1});
        BasicJava.getAvg(new int[] {1, 3, 5, 7, 255});
        BasicJava.arrayWithOdds();
        BasicJava.greaterThanY(new int[] {1,3,5,7,9,13}, 3);
        BasicJava.squareValues(new int[] {1, 5, 10, -2});
        BasicJava.eliminateNegs(new int[] {1, 5, 10, -2});
        BasicJava.maxMinAvg(new int[] {234, 456, 11, 34, 45, 87, 255});
        BasicJava.ShiftingValues(new int[] {1, 5, 10, 7, -2});
    
    }
    public static void print1To255() {
        for(int x=1; x<=255; x++) {
            System.out.println(x);
        }
    }
    public static void printOdds1To255() {
        for(int x=1; x<=255; x+=2) {
            System.out.println(x);            
        }
    }
    public static void printSum() {
        int sum = 0;
        for(int x=1; x<=255; x++) {
            sum += x;
            System.out.println("New number: "+x+" Sum: "+sum);
        }
    }
    public static void iterateThruArray(int[] arr) {
        for(int x=0; x<arr.length; x++) {
            System.out.println(arr[x]);
        }
    }
    public static void findMax(int[] arr) {
        int max = arr[0];
        for(int x=0; x<arr.length; x++) {
            if(arr[x]>max) {
                max = arr[x];
            }
        }
        System.out.println(max);
    }
    public static void getAvg(int[] arr) {
        int sum = 0;
        for(int x=0; x<arr.length; x++) {
            sum += arr[x];
        }
        double avg = sum/arr.length;
        System.out.println(avg);
    }
    public static void arrayWithOdds() {
        ArrayList<Integer> y = new ArrayList<Integer>();
            for(int x=1; x<=255; x+=2) {
                y.add(x);            
            }
        System.out.println(y);
    }
    public static void greaterThanY(int[] arr, int y) {
        ArrayList<Integer> meow = new ArrayList<Integer>();

        for(int x=0; x<arr.length; x++) {
            if(arr[x]>y) {
                meow.add(arr[x]);
            }
        }
        System.out.println(meow);
    }
    public static void squareValues(int[] arr) {
        for(int x=0; x<arr.length; x++) {
            arr[x] *= arr[x];
        }
        for(int i: arr) {
            System.out.println(i);
        }
    }
    public static void eliminateNegs(int[] arr) {
        for(int x=0; x<arr.length; x++) {
            if(arr[x]<0) {
                arr[x] = 0;
            }
        }
        for(int i: arr) {
            System.out.println(i);
        }
    }
    public static void maxMinAvg(int[] arr) {
        int maxnum = arr[0];
        int minnum = arr[0];
        int sum = 0;
        for(int x=0; x<arr.length; x++) {
            if(arr[x]>maxnum) {
                maxnum = arr[x];
            }
            if(arr[x]<minnum) {
                minnum = arr[x];
            }
            sum =+ arr[x];
        }
        int avgnum = sum/arr.length;

        ArrayList<Integer> mma = new ArrayList<Integer>();
        mma.add(maxnum);
        mma.add(minnum);
        mma.add(avgnum);
        System.out.println(mma);
    }
    public static void ShiftingValues(int[] arr) {
        for(int x=0; x<arr.length-1; x++) {
            arr[x] = arr[x+1];
        }
        arr[arr.length-1] = 0;
        for(int i: arr) {
            System.out.println(i);
        }
    }
}