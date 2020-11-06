import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.nio.*;
import org.jgrapht.nio.dot.*;
import org.jgrapht.traverse.*;

import java.util.*;
import java.io.*;

public class UndirectedGraph {


    public static void main(String args[]) {
        //create a graph
        Graph<String, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);

        

        System.out.print(graph);
    }

}
