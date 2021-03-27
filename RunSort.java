//we can run all the different tests from this file - I put an example with randomSort below
public class RunSort{

    public static void main(String[] args) {
        Generate generator = new Generate();
        int[] A = generator.generateRandomInput(1000000);
        int lo = 0;
        int hi = A.length-1;
        randomSort sort = new randomSort(A, lo, hi);
        System.out.println("Count is " + sort.getCount());
    }
}