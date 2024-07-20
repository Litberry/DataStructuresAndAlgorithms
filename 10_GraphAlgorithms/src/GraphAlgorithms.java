import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of various different graph algorithms.
 *
 * @author Andrew Eng
 * @userid aeng48
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null) {
            throw new java.lang.IllegalArgumentException("Input cannot be null.");
        }
        Map<Vertex<T>, List<VertexDistance<T>>> adjacentList = graph.getAdjList();
        if (!(adjacentList.containsKey(start))) {
            throw new IllegalArgumentException("Start isn't in graph");
        }
        List<Vertex<T>> visited = new LinkedList<>();
        Queue<Vertex<T>> vertexQ = new LinkedList<>();
        visited.add(start);
        vertexQ.add(start);
        while (vertexQ.size() > 0) {
            Vertex<T> v = vertexQ.remove();
            for (VertexDistance<T> vertexDist : adjacentList.get(v)) {
                if (!(visited.contains(vertexDist.getVertex()))) {
                    visited.add(vertexDist.getVertex());
                    vertexQ.add(vertexDist.getVertex());
                }
            }
        }
        return visited;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * all points for this method.
     *
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null) {
            throw new java.lang.IllegalArgumentException("Input cannot be null.");
        }
        Map<Vertex<T>, List<VertexDistance<T>>> adjacentList = graph.getAdjList();
        if (!(adjacentList.containsKey(start))) {
            throw new IllegalArgumentException("Start isn't in graph");
        }
        List<Vertex<T>> visited = new LinkedList<>();
        visited = dfsHelper(graph, start, visited);
        return visited;
    }

    /**
     * Recursive helper method for dfs.
     *
     * @param graph the graph to search through
     * @param start the vertex to begin the dfs on
     * @param visited list of vertices in visited order
     * @param <T> the generic typing of the data
     * @return list of vertices in visited order
     */
    private static <T> List<Vertex<T>> dfsHelper(Graph<T> graph, Vertex<T> start, List<Vertex<T>> visited) {
        visited.add(start);
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        for (VertexDistance<T> vertexDistance : adjList.get(start)) {
            if (!(visited.contains(vertexDistance.getVertex()))) {
                dfsHelper(graph, vertexDistance.getVertex(), visited);
            }
        }
        return visited;
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null || graph == null) {
            throw new java.lang.IllegalArgumentException("Input cannot be null.");
        }
        Map<Vertex<T>, List<VertexDistance<T>>> adjacentList = graph.getAdjList();
        if (!(adjacentList.containsKey(start))) {
            throw new IllegalArgumentException("Start isn't in graph");
        }
        Set<Vertex<T>> visited = new HashSet<>();
        Map<Vertex<T>, Integer> distMap = new HashMap<>();
        Queue<VertexDistance<T>> priorityQueue = new PriorityQueue<>();
        for (Vertex<T> vertex : graph.getVertices()) {
            distMap.put(vertex, Integer.MAX_VALUE);
        }
        priorityQueue.add(new VertexDistance<>(start, 0));
        while (priorityQueue.size() != 0 && visited.size() < graph.getVertices().size()) {
            VertexDistance<T> temp = priorityQueue.remove();
            if (!(visited.contains(temp.getVertex()))) {
                visited.add(temp.getVertex());
                distMap.put(temp.getVertex(), temp.getDistance());
                for (VertexDistance<T> vertexDist : adjacentList.get(temp.getVertex())) {
                    if (!(visited.contains(vertexDist.getVertex()))) {
                        priorityQueue.add(new VertexDistance<>(vertexDist.getVertex(), 
                            vertexDist.getDistance() + temp.getDistance()));
                    }
                }
            }
        }
        return distMap;
    }

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use PriorityQueue, java.util.Set, and any class that 
     * implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     *
     * @param <T> the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null || !graph.getAdjList().containsKey(start)) {
            throw new java.lang.IllegalArgumentException("Input cannot be null.");
        }
        int numVertices = graph.getVertices().size();
        Set<Vertex<T>> visited = new HashSet<>();
        Set<Edge<T>> mstEdges = new HashSet<>();
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>();

        for (VertexDistance<T> vd : graph.getAdjList().get(start)) {
            pq.add(new Edge<T>(start, vd.getVertex(), vd.getDistance()));
        }
        visited.add(start);
        while (pq.peek() != null && visited.size() < numVertices) {
            Edge<T> adj = pq.poll();
            Vertex<T> u = adj.getU();
            Vertex<T> v = adj.getV();
            if (!visited.contains(v)) {
                visited.add(v);
                mstEdges.add(adj);
                mstEdges.add(new Edge<T>(v, u, adj.getWeight()));  
                for (VertexDistance<T> vd : graph.getAdjList().get(v)) {
                    Vertex<T> w = vd.getVertex();
                    if (!visited.contains(w)) {
                        pq.add(new Edge<T>(v, vd.getVertex(), vd.getDistance()));
                    }
                }
            }
        }
        if (visited.size() == numVertices) {
            return mstEdges;
        }
        return null;
    }
}