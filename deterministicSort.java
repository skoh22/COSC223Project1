//sort with hi as selection for partition
public class deterministicSort{

    static int counter;

    public deterministicSort(int[] A, int lo, int hi){
        counter=0; //keep track of how many comparisons we do
        deterministicSort(A,lo,hi);
    }

    public void deterministicSort(int[] A, int lo, int hi){
        if(hi>lo){
            int r = hi; //pick hi
            int p=partition(A,lo,hi,r); //find the partition element
            deterministicSort(A,lo,p-1); //recursively call deterministicSort on two halves of array
            deterministicSort(A,p+1,hi);
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
        return(j+1);
    }

    public static void swap(int[] A, int x, int y){
        int holder = A[x];
        A[x]=A[y];
        A[y]=holder;
    }

    public static int getCount(){return counter;}
}