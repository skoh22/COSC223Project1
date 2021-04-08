
//sort with randomly picking three values and taking the midpoint as the partition element
public class pickThreeSort{

    static long counter;

    public pickThreeSort(int[] A, int lo, int hi){
        counter=0; //keep track of how many comparisons we do
        pickThreeSort(A,lo,hi);
    }

    public void pickThreeSort(int[] A, int lo, int hi){
        if(hi>lo){
            int r=hi;
            int r1 = (int) (Math.random()*(hi - lo + 1) + lo); //pick a number randomly between lo and hi
            int r2 = (int) (Math.random()*(hi - lo + 1) + lo); //pick a number randomly between lo and hi
            int r3 = (int) (Math.random()*(hi - lo + 1) + lo); //pick a number randomly between lo and hi
            if (A[r1]<=Math.max(A[r2],A[r3])&&A[r1]>=Math.min(A[r2],A[r3])){
                r = r1;
            }
            if (A[r2]<=Math.max(A[r1],A[r3])&&A[r2]>=Math.min(A[r1],A[r3])){
                r = r2;
            }
            if (A[r3]<=Math.max(A[r1],A[r2])&&A[r3]>=Math.min(A[r1],A[r2])){
                r = r3;
            }
            int p=partition(A,lo,hi,r); //find the partition element
            pickThreeSort(A,lo,p-1); //recursively call randSort on two halves of array
            pickThreeSort(A,p+1,hi);
        }
    }

    public static int partition(int[] A, int lo, int hi, int r){
        swap(A,r,hi);
        int j=hi-1;
        int i=lo;
        int x = A[hi];
        while(i<=j){
            if(A[i]<=x){
                i++;
                counter++;
            }
            else{
                swap(A,i,j);
                j--;
                counter++;
            }
        }
        swap(A,hi,j+1);
        //if(counter<0) {
        //System.out.println(counter);
        //}
        return(j+1);
    }

    public static void swap(int[] A, int x, int y){
        int holder = A[x];
        A[x]=A[y];
        A[y]=holder;
    }

    public static long getCount(){
        return counter;}
}