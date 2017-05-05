package graphSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UseGraph {

	/**
	 * @param args
	 * @throws VertexExistsException
	 * @throws GraphIsFullException
	 */
	static Graph<String> loadGraph(String pathName)
			throws FileNotFoundException, GraphIsFullException,
			VertexExistsException
	
	{
		
		Scanner file = new Scanner(new File(pathName));
		Graph<String> graph;
		String line = file.nextLine();
		String[] lineData = line.split(": ");
		graph = new Graph<String>(Integer.parseInt(lineData[1]));
		//create Graph with its max number of vertex
		while(file.hasNextLine()){
			String line_ = file.nextLine();
			String[] lineData_ = line_.split(": ");
            //adding vertex
			if(lineData_[0].equals("V")){
				graph.addVertex(lineData_[1]);
				}
            //adding edge
			else if(lineData_[0].equals("E")){
				String[] vertexData = lineData_[1].split(",");
				graph.addEdge(vertexData[0], vertexData[1]);
				
			}
	
		}
		file.close();
		return graph;
	}

	public static void main(String[] args) throws FileNotFoundException,
			GraphIsFullException, VertexExistsException {
		// TODO Auto-generated method stub
		String pathname = args[0];
		Graph<String> g1 = loadGraph(pathname);
		System.out.println(g1.connectedComponents());
	}

}
