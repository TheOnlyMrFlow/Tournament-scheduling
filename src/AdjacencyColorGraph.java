import java.util.Map; 

import java.util.HashMap; 
import java.util.Set; 
import java.util.HashSet;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator; 
public class AdjacencyColorGraph<Vertex, Edge> extends AdjacencyGraph<Vertex, Edge> implements ColorGraph<Vertex, Edge>{
	
	
	protected Map<Vertex, Integer> vertexToColor = new HashMap<Vertex, Integer>();
	
	public int colorVertices(){
		
		List<Vertex> allVertices = new ArrayList(getVertices());
		Collections.sort(allVertices, new Comparator<Vertex>() {

			@Override
			public int compare(Vertex v0, Vertex v1) {
				return getDegree(v1) - getDegree(v0);
			}
		});
		
		int nbrOfColors = 0;
		
		for (Vertex v : allVertices) {
			Set<Integer> forbidenColors = new HashSet<Integer>();
			
			for (Vertex n : getAdjacentVertices(v)) {
				if (hasColor(n))
					forbidenColors.add(getColor(n));
			}
			
			int color = 0;
			
			while (forbidenColors.contains(color)) {
				color++;
			}
			vertexToColor.put(v, color);	
			nbrOfColors= Math.max(color, nbrOfColors);
		}
		
		return nbrOfColors+1;
	} 
	
	public int getColor(Vertex v){
		return vertexToColor.get(v);
	}
	
	public Boolean hasColor(Vertex v) {
		return vertexToColor.containsKey(v);
	}
	
	
	// split("\\R")
	
	
	public Boolean checkLegalty(String reader) {
		
		Map<String, Set<Integer>> playerToColors = new HashMap<String, Set<Integer>>();
		
		for (String line : reader.split(System.lineSeparator())) {
			String[] split = line.split(" : ");
			String match = split[0];
			int color = Integer.parseInt(split[1]);
			for (String team : match.split(" vs ")) {
				for (String player : team.split(" ")) {
					Set colors = playerToColors.get(player);
					if (colors == null) {
						colors = new HashSet<Integer>();
					}
					if (colors.contains(color)) {
						return false;
					}
					else {
						colors.add(player);
					}
				}
			}
		}
		
		return true;
	}

}