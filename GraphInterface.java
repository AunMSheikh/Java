package graphSet;

import java.util.Queue;

public interface GraphInterface<T>
{
  boolean isEmpty();
  // Returns true if this graph is empty; otherwise, returns false.

  boolean isFull();
  // Returns true if this graph is full; otherwise, returns false.
  
  void addVertex(T vertex) throws GraphIsFullException, VertexExistsException;
  // Preconditions:  Vertex is not already in this graph.
  //                 Vertex is not null.
  // Throws GraphIsFullException if the graph is full
  // else adds vertex to this graph.

  void addEdge(T fromVertex, T toVertex);
  // Adds an edge with the specified weight from fromVertex to toVertex.

  int weightIs(T fromVertex, T toVertex);
  // If edge from fromVertex to toVertex exists, returns the weight of edge;
 

  Queue<T> getToVertices(T vertex);
  // Returns a queue of the vertices that are adjacent from vertex.

  void clearMarks();
  // Sets marks for all vertices to false.

  void markVertex(T vertex);
  // Sets mark for vertex to true.

  boolean isMarked(T vertex);
  // Returns true if vertex is marked; otherwise, returns false.
    
}
