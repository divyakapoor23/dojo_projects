import java.util.ArrayList;
import java.util.Random;

public class PuzzleJava {
    public static void main(String[] args) {
        PuzzleJava.arrTest1(new int[] {3, 5, 1, 2, 7, 9, 8, 13, 25, 32});
        PuzzleJava.arrTest2(new String[] {"Nancy", "Jinichi", "Fujibayashi", "Momochi", "Ishikawa"});
        PuzzleJava.alphaTest3(new char[] {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'});
        PuzzleJava.arrTest4();
        PuzzleJava.arrTest5();
        PuzzleJava.strTest6();        
        PuzzleJava.arrTest7();        

    }
    public static void arrTest1(int[] arr) {
        ArrayList<Integer> newarray = new ArrayList<Integer>();

        int sum = 0;
        for(int x=0; x<arr.length; x++) {
            sum += arr[x];
            if(arr[x] > 10) {
                newarray.add(arr[x]);
            }
        }
        System.out.println(sum);
        System.out.println(newarray);
    }
    public static void arrTest2(String[] arr) {
        ArrayList<String> newarray = new ArrayList<String>();
        Random rand = new Random();
        for(int x=0; x<arr.length; x++) {
            int n = rand.nextInt(arr.length);
            String temp = arr[x];
            arr[x] = arr[n];
            arr[n] = temp;
        }
        for(String i: arr){
            System.out.println(i);
            if (i.length() > 5) {
                newarray.add(i);
            }
        }
        System.out.println(newarray);
    }
    public static void alphaTest3(char[] alpha) {
        Random rand = new Random();
        for(int x=0; x<alpha.length; x++) {
            int n = rand.nextInt(alpha.length);
            char temp = alpha[x];
            alpha[x] = alpha[n];
            alpha[n] = temp;
        }
        System.out.println(alpha[0]);
        System.out.println(alpha[alpha.length-1]);
        if(alpha[0] == 'A' || alpha[0] == 'E' || alpha[0] == 'I' || alpha[0] == 'O' || alpha[0] == 'U') {
            System.out.println("The first letter is a vowel");
        }
    }
    public static void arrTest4() {
        Random rand = new Random();
        int[] myArray;
        myArray = new int[10];
        for(int x=0; x<myArray.length; x++) {
            int n = rand.nextInt(45) +55;
            myArray[x] = n;
        }
        for(int i: myArray) {
            System.out.println(i);
        }
    }
    public static void arrTest5() {
        Random rand = new Random();
        int[] myArray;
        myArray = new int[10];
        for(int x=0; x<myArray.length; x++) {
            int n = rand.nextInt(45) +55;
            myArray[x] = n;
        }
        int min = myArray[0];
        int max = myArray[0];
        for(int x=0; x<myArray.length; x++) {
            for(int y=x; y<myArray.length; y++) {
                if(myArray[y] < myArray[x]) {
                    int temp = myArray[x];
                    myArray[x] = myArray[y];
                    myArray[y] = temp;
                }
            }            
            if(myArray[x] > max) {
                max = myArray[x];
            }
            if(myArray[x] < min) {
                min = myArray[x];
            }

        }
        System.out.println(min);
        System.out.println(max);

        ArrayList<Integer> newarray = new ArrayList<Integer>();
        for(int i: myArray) {
            newarray.add(i);
        }
        System.out.println(newarray);
    }
    public static void strTest6() {
        Random charmander = new Random();
        String charizard = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        while(charizard.length() < 5) {
            int x = charmander.nextInt(alphabet.length());
            char charmeleon = alphabet.charAt(x);
            charizard += charmeleon;
        }
        System.out.println(charizard);
    }
    public static void arrTest7() {
        Random rand = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String[] charizard = new String[10];

        for(int y=0; y<10; y++) {
            String charmeleon = "";
            while(charmeleon.length() < 5) {
                int x = rand.nextInt(alphabet.length());
                char charmander = alphabet.charAt(x);
                charmeleon += charmander;
            }
            charizard[y] = charmeleon;
        }

        ArrayList<String> arrlist = new ArrayList<String>();
        for(String z: charizard) {
            arrlist.add(z);
        }
        System.out.println(arrlist);

    }

}