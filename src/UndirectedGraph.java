import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.nio.*;
import org.jgrapht.nio.dot.*;
import org.jgrapht.traverse.*;

import java.util.*;
import java.io.*;

public class UndirectedGraph {

    public static void main(String args[]) {
        //create graphs
        Graph<String, DefaultEdge> graph1 = new DefaultUndirectedGraph<>(DefaultEdge.class);
        Graph<String, DefaultEdge> graph2 = new DefaultUndirectedGraph<>(DefaultEdge.class);
        Graph<String, DefaultEdge> graph3 = new DefaultUndirectedGraph<>(DefaultEdge.class);

        //create n Choose 2 sizes for 10, 100, and 10000

        int ver1 = 10;
        int ver2 = 100;
        int ver3 = 1000;


        int edges1 = 36;
        int edges2 = 3960;
        //divided by 10 to avoid filling up the heap I realize this is only 11% 10,000 choose 2
        int edges3 = 399600;

        // add vertexes to graphs
        for (int i=0; i < ver1; i++){
            graph1.addVertex(Integer.toString(i));
        }
        for (int i=0; i < ver2; i++){
            graph2.addVertex(Integer.toString(i));
        }
        for (int i=0; i < ver3; i++){
            graph3.addVertex(Integer.toString(i));
        }


        //add edges to graphs
        for (int i=0; i < ver1-1; i++){
            graph1.addEdge(Integer.toString(i), Integer.toString((i+1)%ver1));
        }

        for (int i=0; i < ver2-1; i++){
            graph2.addEdge(Integer.toString(i), Integer.toString((i+1)%ver2));
        }

        for (int i=0; i < ver3-1; i++){
            graph3.addEdge(Integer.toString(i), Integer.toString((i+1)%ver3));
        }

        //add the rest of the edges randomly

        // graph one
        int i = 0;
        int min1 = 0;
        int max1 = ver1;
        while (i < edges1-ver1+1){
            // random numbers
            int rand1 = (int)(Math.random() * (max1 - min1) + min1);
            int rand2 = (int)(Math.random() * (max1 - min1) + min1);
            String strRand1 = Integer.toString(rand1);
            String strRand2 = Integer.toString(rand2);

            // if the numbers are not the same and the graph does not contain the edge already
            if (!( (rand1 == rand2) || graph1.containsEdge(strRand1, strRand2)))
            {
                graph1.addEdge(strRand1, strRand2);
                i++;
            }
        }

        // graph 2
        i = 0;
        min1 = 0;
        max1 = ver2;
        while (i < edges2-ver2+1){
            // random numbers
            int rand1 = (int)(Math.random() * (max1 - min1) + min1);
            int rand2 = (int)(Math.random() * (max1 - min1) + min1);
            String strRand1 = Integer.toString(rand1);
            String strRand2 = Integer.toString(rand2);

            // if the numbers are not the same and the graph does not contain the edge already
            if (!( (rand1 == rand2) || graph1.containsEdge(strRand1, strRand2)))
            {
                graph2.addEdge(strRand1, strRand2);
                i++;
            }
        }

        // graph 3
        i = 0;
        min1 = 0;
        max1 = ver3;
        while (i < edges3-ver3+1){
            // random numbers
            int rand1 = (int)(Math.random() * (max1 - min1) + min1);
            int rand2 = (int)(Math.random() * (max1 - min1) + min1);
            String strRand1 = Integer.toString(rand1);
            String strRand2 = Integer.toString(rand2);

            // if the numbers are not the same and the graph does not contain the edge already
            if (!( (rand1 == rand2) || graph1.containsEdge(strRand1, strRand2)))
            {
                graph3.addEdge(strRand1, strRand2);
                i++;
            }
        }

        // check to see if every vertex has an edge

        //for (i=0; i < ver1; i++){
        //    graph1.addVertex(Integer.toString(i));
        //}


        //print out graphs
        System.out.println(graph1);
        System.out.println(graph2);
        System.out.println(graph3);
    }
}
