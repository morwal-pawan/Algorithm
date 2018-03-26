package algo;

public class HeapSort {

    

    public static void exchange(Comparable[] a,int i, int j) {
   	Comparable swap = a[i]; a[i] = a[j]; a[j] = swap;
       }

    public static boolean less(Comparable[] a,int i, int j) {
   	   return (a[i].compareTo(a[j]) ) < 0;
       }
    public static void sink(Comparable[] a, int k,int n) {
	while (2 * k <= n) {
	    int j = 2 * k;
	    if (less(a,j, j + 1)) j = j + 1;
	    if (!less(a,k, j)) break;
	    exchange(a,j, k);
	    k = j;
	}
    }
    public static void sort(Comparable[] a)
    {
        int n = a.length;
        for (int k = n/2; k >= 1; k--)
       	  sink(a, k, n);
        while (n > 1)
        {
            exchange(a, 1, n);
            sink(a, 1, --n);
        }
    }

}
