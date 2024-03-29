import org.jgrapht.*;
import org.jgrapht.alg.connectivity.KosarajuStrongConnectivityInspector;
import org.jgrapht.graph.*;
import java.util.*;

public class UndirectedGraph {

    // method to create and return a graph with
    public static Graph<String, DefaultEdge> makeUndirectedGraph(int numVertex, int numEdges)
    {
        //add vertexes to graph
        Graph<String, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
        for (int i=0; i < numVertex; i++){
            graph.addVertex(Integer.toString(i));
        }

        //add first set of edges to graph
        for (int i=0; i < numVertex-1; i++){
            graph.addEdge(Integer.toString(i), Integer.toString((i+1)%numVertex));
        }

        //add random edges to graph
        // graph one
        int i = 0;
        int min1 = 0;
        int max1 = numVertex;
        while (i < numEdges-numVertex+1){
            // random numbers
            int rand1 = (int)(Math.random() * (max1 - min1) + min1);
            int rand2 = (int)(Math.random() * (max1 - min1) + min1);
            String strRand1 = Integer.toString(rand1);
            String strRand2 = Integer.toString(rand2);

            // if the numbers are not the same and the graph does not contain the edge already
            if (!( (rand1 == rand2) || graph.containsEdge(strRand1, strRand2)))
            {
                graph.addEdge(strRand1, strRand2);
                i++;
            }
        }

        //return the graph
        return (graph);
    }

    // method to create and return a graph with
    public static Graph<String, DefaultEdge> makeDirectedGraph(int numVertex, int numEdges)
    {
        //add vertexes to graph
        Graph<String, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        for (int i=0; i < numVertex; i++){
            graph.addVertex(Integer.toString(i));
        }

        //add first set of edges to graph
        for (int i=0; i < numVertex-1; i++){
            graph.addEdge(Integer.toString(i), Integer.toString((i+1)%numVertex));
        }

        //add random edges to graph
        // graph one
        int i = 0;
        int min1 = 0;
        int max1 = numVertex;
        while (i < numEdges-numVertex+1){
            // random numbers
            int rand1 = (int)(Math.random() * (max1 - min1) + min1);
            int rand2 = (int)(Math.random() * (max1 - min1) + min1);
            String strRand1 = Integer.toString(rand1);
            String strRand2 = Integer.toString(rand2);

            // if the numbers are not the same and the graph does not contain the edge already
            if (!( (rand1 == rand2) || graph.containsEdge(strRand1, strRand2)))
            {
                graph.addEdge(strRand1, strRand2);
                i++;
            }
        }

        //return the graph
        return (graph);
    }

    //make graph bipartite
    public static Graph<String, DefaultEdge> makeBipartite(Graph<String, DefaultEdge> graphX)
    {
        int numVertex = graphX.vertexSet().size();
        int numEdges = graphX.edgeSet().size();

        //add vertexes to graph
        Graph<String, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        for (int i=0; i < graphX.vertexSet().size(); i++){
            graph.addVertex(Integer.toString(i));
        }

        //add random edges to graph
        // graph one
        int i = 0;
        int min1 = 0;
        int max1 = numVertex;
        // n^2 * .25 == the maximum amount of edges you can have to be bipartite
        while (i < numVertex*numVertex *.25 ){
            // random numbers
            int rand1 = (int)(Math.random() * (max1 - min1) + min1);
            int rand2 = (int)(Math.random() * (max1 - min1) + min1);
            String strRand1 = Integer.toString(rand1);
            String strRand2 = Integer.toString(rand2);


            // if the numbers are not the same and the graph does not contain the edge already and one edge is odd and the other even || (rand1%2 == 0)
            if (!( (rand1 == rand2) || graph.containsEdge(strRand1, strRand2) || ((rand1+rand2)%2 == 0) || graph.containsEdge(strRand2, strRand1) ))
            {
                if(rand1%2 == 0){
                    graph.addEdge(strRand1, strRand2);
                    i++;
                }
            }
        }

        return graph;
    }

    public static boolean checkGraph(Graph<String, DefaultEdge> graph)
    {
        //check each node such that is has an edge
        int length = graph.vertexSet().size();
        for(int i = 0; i < length-1; i++)
        {
            // if the vertex does not contain an edge
            if (!(graph.containsEdge(Integer.toString(i), Integer.toString(i+1)))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {

        // Create Undirected graphs of size 10, 100, and 1000
        Graph<String, DefaultEdge> graph1 = makeUndirectedGraph(10, 36);
        Graph<String, DefaultEdge> graph2 = makeUndirectedGraph(100, 3960);
        Graph<String, DefaultEdge> graph3 = makeUndirectedGraph(1000, 399600);

        System.out.println("\nPrinting Undirected Graphs...");
        System.out.println("Size 10 Graph: " + graph1);
        System.out.println("Size 100 Graph: " + graph2);
        System.out.println("Size 1000 Graph: TOO BIG TO PRINT");// + graph3);

        // check to see if every node has and edge
        System.out.println("\nChecking to see if every vertex has an edge...");
        System.out.println("Size 10 Graph: " + checkGraph(graph1));
        System.out.println("Size 100 Graph: " + checkGraph(graph2));
        System.out.println("Size 1000 Graph: " + checkGraph(graph3));

        // check to see if every node has and edge
        System.out.println("\nChecking to see how many edges each graph has... (Expected is 36, 3960, and 399600)");
        System.out.println("Size 10 Graph: " + graph1.edgeSet().size());
        System.out.println("Size 100 Graph: " + graph2.edgeSet().size());
        System.out.println("Size 1000 Graph: " + graph3.edgeSet().size());

        // Create Directed graphs of size 10, 100, and 1000
        Graph<String, DefaultEdge> digraph1 = makeDirectedGraph(10, 36);
        Graph<String, DefaultEdge> digraph2 = makeDirectedGraph(100, 3960);
        Graph<String, DefaultEdge> digraph3 = makeDirectedGraph(1000, 399600);

        //Compute the strongly connected sets

        System.out.println("\nCalclulating the time taken to find strongly connected components of each graph...");
        //record time taken
        long timeNow;
        long timeAfter;
        long timeTaken;


        // Grab time before, perform search for strongly connected component, then grab time after
        timeNow = System.currentTimeMillis();
        KosarajuStrongConnectivityInspector<String, DefaultEdge> inspector1 = new KosarajuStrongConnectivityInspector<>(digraph1);
        List<Set<String>> strongSet1 = inspector1.stronglyConnectedSets();
        timeAfter = System.currentTimeMillis();

        // Calculate time taken to perform the search of strongly connects components
        timeTaken = timeAfter - timeNow;
        System.out.println("Size 10 Graph: " + timeTaken + " milliseconds");


        // Grab time before, perform search for strongly connected component, then grab time after
        timeNow = System.currentTimeMillis();
        KosarajuStrongConnectivityInspector<String, DefaultEdge> inspector2 = new KosarajuStrongConnectivityInspector<>(digraph2);
        List<Set<String>> strongSet2 = inspector2.stronglyConnectedSets();
        timeAfter = System.currentTimeMillis();


        // Calculate time taken to perform the search of strongly connects components
        timeTaken = timeAfter - timeNow;
        System.out.println("Size 100 Graph: " + timeTaken + " milliseconds");


        // Grab time before, perform search for strongly connected component, then grab time after
        timeNow = System.currentTimeMillis();
        KosarajuStrongConnectivityInspector<String, DefaultEdge> inspector3 = new KosarajuStrongConnectivityInspector<>(digraph3);
        List<Set<String>> strongSet3 = inspector3.stronglyConnectedSets();
        timeAfter = System.currentTimeMillis();

        // Calculate time taken to perform the search of strongly connects components
        timeTaken = timeAfter - timeNow;
        System.out.println("Size 1000 Graph: " + timeTaken + " milliseconds");

        //print out strongly connected components
        System.out.println("\nThe strongly connected components are as follows...");
        System.out.println("Size 10 Graph: " + strongSet1);
        System.out.println("Size 100 Graph: " + strongSet2);
        System.out.println("Size 1000 Graph: " + strongSet3);


        System.out.println("\nChecking to see if graphs are bipartite before making them bipartite...");
        System.out.println("Size 10 Graph: " + GraphTests.isBipartite(digraph1));
        System.out.println("Size 100 Graph: " + GraphTests.isBipartite(digraph2));
        System.out.println("Size 1000 Graph: " + GraphTests.isBipartite(digraph3));

        //make graphs bipartite using method described in homework document
        Graph<String, DefaultEdge> bigraph1 = makeBipartite(digraph1);
        Graph<String, DefaultEdge> bigraph2 = makeBipartite(digraph2);
        Graph<String, DefaultEdge> bigraph3 = makeBipartite(digraph3);

        System.out.println("\nChecking to see if graphs are bipartite after making them bipartite...");
        System.out.println("Size 10 Graph: " + GraphTests.isBipartite(bigraph1));
        System.out.println("Size 100 Graph: " + GraphTests.isBipartite(bigraph2));
        System.out.println("Size 1000 Graph: " + GraphTests.isBipartite(bigraph3));

        System.out.println("\nPrinting out the bipartite graphs (There should be no even connection to an odd connection)");
        System.out.println("Size 10 Graph: " + bigraph1);
        System.out.println("Size 100 Graph: " + bigraph2);
        System.out.println("Size 1000 Graph: TOO BIG TO PRINT");
    }
}
