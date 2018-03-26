package algo;

import java.util.Scanner;

public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstPaths(Graph G, int s) {
	this.s = s;
	edgeTo = new int[G.V()];
	marked = new boolean[G.V()];
	validateVertex(s);
	dfs(G, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
	marked[v] = true;
	for (int w : G.adj(v)) {
	    if (!marked[w]) {
		edgeTo[w] = v;
		dfs(G, w);
	    }
	}
    }

    public boolean hasPathTo(int v) {
	validateVertex(v);
	return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
	validateVertex(v);
	if (!hasPathTo(v))
	    return null;
	Stack<Integer> path = new Stack<Integer>();
	for (int x = v; x != s; x = edgeTo[x])
	    path.push(x);
	path.push(s);
	return path;
    }

    private void validateVertex(int v) {
	int V = marked.length;
	if (v < 0 || v >= V)
	    throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public static void main(String[] args) {
	Scanner in = new Scanner(args[0]);
	Graph G = new Graph(in);
	int s = Integer.parseInt(args[1]);
	DepthFirstPaths dfs = new DepthFirstPaths(G, s);

	for (int v = 0; v < G.V(); v++) {
	    if (dfs.hasPathTo(v)) {
		System.out.println(s + " to " + v);
		for (int x : dfs.pathTo(v)) {
		    if (x == s)
			System.out.println(x);
		    else
			System.out.println("-" + x);
		}
		System.out.println();
	    }

	    else {
		System.out.println(s + " to " + v + " :  not connected\n ");
	    }

	}
    }

}
