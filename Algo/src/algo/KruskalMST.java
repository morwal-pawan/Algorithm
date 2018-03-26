package algo;

import java.util.Scanner;

public class KruskalMST {

    private double weight;                        
    private Queue<Edge> mst = new Queue<Edge>();  



   

    public KruskalMST(EdgeWeightedGraph g) {
	
    }


    public Iterable<Edge> edges() {
        return mst;
    }

    
    public double weight() {
        return weight;
    }
    


    
    public static void main(String[] args) {
	Scanner in = new Scanner(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        KruskalMST mst = new KruskalMST(G);
        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.println(mst.weight());
    }

}
