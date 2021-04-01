import java.util.Arrays;
import java.util.Scanner;

public class RunSort{

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Please select (type number) an input type: "); //Ask user to select input array type
        System.out.println("(1) Random");
        System.out.println("(2) Partially Sorted");
        System.out.println("(3) Mostly Sorted");
        String selection = kb.nextLine();

        //check to make sure input is valid
        while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3")) {
            System.out.println("Invalid input. Please try again:");
            selection = kb.nextLine();
        }

        System.out.println("Please select (type number) an input type: "); //Ask user to select Quicksort type
        System.out.println("(1) Random Quicksort");
        System.out.println("(2) Deterministic Quicksort");
        String sortType = kb.nextLine();

        //check to make sure input is valid
        while (!sortType.equals("1") && !sortType.equals("2")) {
            System.out.println("Invalid input. Please try again:");
            sortType = kb.nextLine();
        }

        //Ask user to input a max array length for the experiments
        System.out.println("Please input a max array length (suggested max input 10^6).");
        System.out.println("Note: max input will be cut down to nearest order of 10:");
        int maxLength = kb.nextInt();
        int curLength = 10;
        //create an array to store results
        //each experiment will run on an array of order 10, i.e. 10,100,1000,10000, ...
        double[] results = new double[(int) Math.log10(maxLength)];
        //run the experiments on the different array lengths
        for (int i = 0; i < results.length; i++) {
            double result = runExperiment(selection, sortType, curLength);
            //add the result to the results array
            results[i] = result;
            curLength = curLength*10;
        }
        //the results array is what we can graph!
        System.out.println(Arrays.toString(results));
    }

    public static double runExperiment(String selection, String sortType, int length){
        //create a new array generator
        Generate generator = new Generate();
        int[] expRuns = new int[100]; //run the experiment 100 times on the array of length "length"
        for(int i = 0; i<100; i++){
            //create new array with user's desired input type
            int[] A;
            if(selection.equals("1")){
                A = generator.generateRandomInput(length);
            }
            else if(selection.equals("2")){
                A = generator.generatePartiallySortedInput(length);
            }
            else{
                A = generator.generateMostlySortedInput(length);
            }
            int lo = 0;
            int hi = A.length-1;

            if (sortType.equals("1")){
                randomSort sort = new randomSort(A, lo, hi);
                expRuns[i] = sort.getCount();
            }
            else{
                deterministicSort sort = new deterministicSort(A, lo, hi);
                expRuns[i] = sort.getCount();
            }
            //System.out.println(Arrays.toString(A));
            //System.out.println("Count is " + sort.getCount());
        }
        //calculate the average of these values in the experiment
        int sum = 0;
        for(int i = 0; i<100; i++){
            sum = sum + expRuns[i];
        }

        //calculate variance
        double avg = sum/100;

        double numerator=0;
        for(int i = 0; i<100; i++){
            numerator = numerator + (expRuns[i]-avg)*(expRuns[i]-avg);
        }

        double variance = numerator/99;
        System.out.println("Variance of array length " + length+ " is " + variance);

        //return the avg value over these 100 sorting runs
        return(avg);
    }
}