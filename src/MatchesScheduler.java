import java.util.Map; 
import java.util.HashMap; 
import java.util.Set; 
import java.util.HashSet; 
import java.util.Scanner; 
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream; 
import java.io.OutputStream;

public class MatchesScheduler{ 
	
	private static Set<String> readPlayers(String match){ 
		Set<String> res= new HashSet<String>(); 
		for(Scanner scanMatch= new Scanner(new ByteArrayInputStream(match.getBytes())); scanMatch.hasNext();){
			String player= scanMatch.next(); 
			if(!player.equals("vs")){ 
				res.add(player); 
			} 
		} 
		return res; 
	} 
	
	private static ColorGraph<String, Integer> readMatches(InputStream in) {
		ColorGraph<String, Integer> res = new AdjacencyColorGraph<String, Integer>();
		Map<String, Set<String>> matchToPlayers = new HashMap<String, Set<String>>();
		int edgeId = 0;
		for (Scanner scanFile = new Scanner(in); scanFile.hasNextLine();) {
			String match = scanFile.nextLine();
			if (match.contains(" vs ")) {
				Set<String> currentPlayers = readPlayers(match);
				res.addVertex(match);
				for (Map.Entry<String, Set<String>> matchPlayers : matchToPlayers.entrySet()) {
					for (String player : currentPlayers) {
						if (matchPlayers.getValue().contains(player)) {
							res.addEdge(new Integer(edgeId++), new String[] { match, matchPlayers.getKey() });
						}
					}
				}
				matchToPlayers.put(match, currentPlayers);
			}
		}
		return res;
	}

	public static void main(String[] args){
		ColorGraph<String, Integer> scheduler;
		try {
			scheduler = readMatches(new FileInputStream(new File(args[0])));
			int nbrColors = scheduler.colorVertices(); 
			for(String match : scheduler.getVertices()){ 
				System.out.println(match + " : "+ scheduler.getColor(match)); 
			} 
			System.out.println(nbrColors + " colors were used in total");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}