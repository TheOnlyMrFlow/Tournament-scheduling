public interface ColorGraph<Vertex, Edge> extends Graph<Vertex, Edge>{ 
	
	// graph coloring, returns the number of colors used 
	public int colorVertices();
	
	// returns the color of a given vertex 
	public int getColor(Vertex v); 
	
	public Boolean checkLegalty(String reader);

}