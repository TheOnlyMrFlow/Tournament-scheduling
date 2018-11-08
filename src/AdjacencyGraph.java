import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class AdjacencyGraph<Vertex, Edge> implements Graph<Vertex, Edge>{ 
	protected Map<Edge, Vertex[]> edgeToVertices = new HashMap<Edge, Vertex[]>();
	protected Map<Vertex, Set<Edge> > vertexToEdges= new HashMap<Vertex, Set<Edge>>();
	
	public void addVertex(Vertex v){
		if(!vertexToEdges.containsKey(v)){ 
			vertexToEdges.put(v, new HashSet<Edge>());
		}
	}
	public void addEdge(Edge e, Vertex[] v0v1){
		edgeToVertices.put(e, v0v1);
		for(Vertex v : v0v1){
			addVertex(v); 
			vertexToEdges.get(v).add(e); 
		}
	} 
	
	public int getDegree(Vertex v){
		return vertexToEdges.get(v).size();

	}
	
	public Collection<Vertex> getVertices(){
		return Collections.unmodifiableCollection(vertexToEdges.keySet());
	}
	
	public Collection<Edge> getEdges(){ 
		return Collections.unmodifiableCollection(edgeToVertices.keySet());
	} 
	
	public Collection<Edge> getEdges(Vertex v){
		return Collections.unmodifiableCollection(vertexToEdges.get(v));
	} 
	
	public Vertex[] getVertices(Edge e){ 
		return edgeToVertices.get(e); 
	}
	
	public List<Vertex> getAdjacentVertices(Vertex src){
		List<Vertex> res= new ArrayList<Vertex>();
		for(Edge e : vertexToEdges.get(src)){
			for (Vertex n : edgeToVertices.get(e)) {
				if (!n.equals(src)) {
					res.add(n);
				}
			}
		}
		return res;
	}
}