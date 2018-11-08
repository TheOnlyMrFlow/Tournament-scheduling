import java.util.Collection;

public interface Graph<Vertex, Edge> {
	
	public void addVertex(Vertex v);
	public void addEdge(Edge e, Vertex[] v0v1);
	public int getDegree(Vertex v);
	public Collection<Vertex> getVertices();
	public Collection<Edge> getEdges();
	public Collection<Edge> getEdges(Vertex v);
	public Vertex[] getVertices(Edge e);
}





