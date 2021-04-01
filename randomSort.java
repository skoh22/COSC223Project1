//sort with random selection of partition
public class randomSort{

    static int counter;

    public randomSort(int[] A, int lo, int hi){
        counter=0; //keep track of how many comparisons we do
        randSort(A,lo,hi);
    }

    public void randSort(int[] A, int lo, int hi){
        if(hi>lo){
            int r = (int) (Math.random()*(hi - lo + 1) + lo); //pick a number randomly between lo and hi
            int p=partition(A,lo,hi,r); //find the partition element
            randSort(A,lo,p-1); //recursively call randSort on two halves of array
            randSort(A,p+1,hi);
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

    public static int getCount(){
        return counter;}
}