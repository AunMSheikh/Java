package graphSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import stack.*;

public class Graph<T> implements GraphInterface<T> {
	public static final int DEF_CAPACITY = 10;
	public static final int NULL_EDGE = 0;
	public static final int DEFAULT_WEIGHT = 1;
	private int numVertices;
	private int maxVertices;
	private T[] vertices;
	private int[][] edges;
	private boolean[] marks; // marks[i] is mark for vertices[i]

	public Graph() {
		numVertices = 0;
		maxVertices = DEF_CAPACITY;
		vertices = (T[]) new Object[DEF_CAPACITY];
		marks = new boolean[DEF_CAPACITY];
		edges = new int[DEF_CAPACITY][DEF_CAPACITY];
	}
	
	public Graph(int maxV)
	// Instantiates a graph with capacity maxV.
	{
		numVertices = 0;
		maxVertices = maxV;
		vertices = (T[]) new Object[maxV];
		marks = new boolean[maxV];
		edges = new int[maxV][maxV];
	}

	public boolean isEmpty()
	// Returns true if this graph is empty; otherwise, returns false.
	{
		return (numVertices == 0);
	}

	public boolean isFull()
	// Returns true if this graph is full; otherwise, returns false.
	{
		return (numVertices == maxVertices);
	}


	public void addVertex(T vertex) throws GraphIsFullException,
			VertexExistsException {

        //check to see if vertex is full and exists
		if(isFull())
			throw new GraphIsFullException("Graph is full");
		else if(containsVertex(vertex))
			throw new VertexExistsException("Vertex exists");
        //adding vertex to the graph
		else{
			this.vertices[this.numVertices]=vertex;
			this.numVertices += 1;
			}
		}


	public void addEdge(T fromVertex, T toVertex)
	// Adds an edge with the specified weight from fromVertex to toVertex.
	
	{
        
		int indexA = findIndex(fromVertex);
		int indexB = findIndex(toVertex);
		this.edges[indexA][indexB] = 1;
		this.edges[indexB][indexA] = 1;
		
	}

	

	public Queue<T> getToVertices(T vertex)
	// Returns a queue of the vertices that are adjacent from vertex.
	
	{
		
		Queue<T> queue = new LinkedList<T>();
		int index = findIndex(vertex);
		if(index!=-1){
		for(int i=0;i<this.numVertices;i++){
			if(this.edges[index][i] == 1)
				queue.add(this.vertices[i]);
				
		}
		}
		return queue;
	}

	public void clearMarks()
	// Sets marks for all vertices to false.
	{
		for (int i = 0; i < numVertices; i++)
			marks[i] = false;
	}

	

	public void markVertex(T vertex)
	// Sets mark for vertex to true.
	{
	
		int index = findIndex(vertex);
		if(index!=-1)
			marks[index] = true;
		
	}



	public boolean isMarked(T vertex)
	// Returns true if vertex is marked; otherwise, returns false.
	{
		int index = findIndex(vertex);
		if(index!=-1)
			return marks[index];
		else
			return false;
	}

	
	private Set<T> DFSVisit(T startVertex)
	// Uses depth-first search algorithm to visit as much vertices as
	// possible
	
	{
		
		Set<T> set = new HashSet<T>();//create a new set
		Queue<T> neibours = getToVertices(startVertex);
		neibours.add(startVertex);
		markVertex(startVertex);
		// find all startVertex's adjacent vertices, then add startVertex
		Queue<T> neibours_copy = getToVertices(startVertex);//create another neibours for DFS
		LinkedStack<T> neibours2 = new LinkedStack<T>();
		
        // create a LinkedStack contains all the startVertex's neibours;
		while(neibours_copy.size()>0){
			neibours2.push(neibours_copy.peek());
			markVertex(neibours_copy.poll());
		}
		//Depth first search
		while(!neibours2.isEmpty()){
			Queue<T> DFSNeibours = getToVertices(neibours2.top());
			neibours2.pop();
			while(DFSNeibours.size()>0){
				if(!isMarked(DFSNeibours.peek())){
					neibours.add(DFSNeibours.peek());	
					neibours2.push(DFSNeibours.peek());
					markVertex(DFSNeibours.poll());
					}
				else
					DFSNeibours.poll();
			}
		}
        //adding vertices to the set
		while(!neibours.isEmpty()){
				set.add(neibours.poll());
				}
		return set;
		
	}

	
	public ArrayList<Set<T>> connectedComponents()
	// Returns a list of connected components of the graph
	
	{
		
		ArrayList<Set<T>> list = new ArrayList<Set<T>>();
		Set<T> connectedComp = DFSVisit(this.vertices[0]);
		//set initial connectedComponents
		if(!connectedComp.contains(null)){
			list.add(connectedComp);
		}
		for(int i=1;i<this.numVertices;i++){
			if(!isMarked(this.vertices[i])){
			Set<T> vertex = DFSVisit(this.vertices[i]);
			list.add(vertex);
			}
		}
		clearMarks();
		return list;
	}
	
	public boolean containsVertex(T vertex){
        //checking to see if vertex exists s
		if(this.numVertices==0){
			return false;
		}
		else{
		for(int i = 0;i<this.numVertices;i++){
			if(this.vertices[i].equals(vertex)){
				return true;
			}
		}
		return false;
		}
	}
	
	public int findIndex(T vertex){
        //Finding the index of the vertex in a linked stack
		for(int i = 0;i<this.numVertices;i++){
			if(this.vertices[i].equals(vertex)){
				return i;
			}
		}
		return -1;

	}
	
		

}
