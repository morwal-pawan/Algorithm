package algo;

public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s) {
	marked = new boolean[G.V()];
	validateVertex(s);
	dfs(G, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
	count++;
	marked[v] = true;
	for (int w : G.adj(v)) {
	    if (!marked[w]) {
		dfs(G, w);
	    }
	}
    }

    public boolean marked(int v) {
	validateVertex(v);
	return marked[v];
    }

    public int count() {
	return count;
    }

    private void validateVertex(int v) {
	int V = marked.length;
	if (v < 0 || v >= V)
	    throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

}