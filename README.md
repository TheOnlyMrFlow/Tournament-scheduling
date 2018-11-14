# Tournament-scheduling

## How could you build upon the available Graph<Vertex, Edge> implementations to handle colored graph ?

> I made a ColorGraph<Vertex, Edge> interface that extends the Graph<Vertex, Edge> interface. My ColorGraph interface declares the methods int colorVertices(), int getColor(Vertex v) and Boolean checkLegalty(Strign reader). The checkLegalty method verifies that the solution found respects the fact that no player is playing more than one match in one week.

> Then, I made an AdjacencyColorGraph that extends AdjacencyGraph and implements the ColorGraph interface.

##  How could a color be implemented ? One could for instance use a special kind of vertices that could contain a color information. Or one could use a special kind of graph that could store a color for each vertex. How would you do that ? 

> I chose to store the colors in a Map<Vertex, Integer> that is an attribute of AdjacencyColorGraph. 
> I could also have created a Match class, which would store its color as an Integer attribute.

## What would be the trade-offs between those two implementations ?

> Storing the colors in a Map is 'cleaner' and better for code scalability.
> Storing the color as an attribute for each vertex costs less memory.

## Could you implement optimal coloring ? How or why not ? Implement a coloring algorithm that would enable you to solve the problem at hand.

> Implementing an optimal coloring would require to 'brutforce' over all the possible solutions, which would give a complexity of O(n!) where n is the number of matches in the tournament. This would take way to long to compute.

> An efficient algorithm would be to iterate over all the vertices and giving them the lowest color value as possible, considering the colors of their neighbors.  An even better way to do this would be to sort the vertices in descending order of their degrees before processing.
