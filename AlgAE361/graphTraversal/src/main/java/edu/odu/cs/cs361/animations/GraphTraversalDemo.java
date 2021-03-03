package edu.odu.cs.cs361.animations;


import java.awt.Color;
import java.util.Random;

import edu.odu.cs.cs361.animations.graphs.CppIterator;
import edu.odu.cs.cs361.animations.graphs.DiGraph;
import edu.odu.cs.cs361.animations.graphs.Edge;
import edu.odu.cs.cs361.animations.graphs.Vertex;
import edu.odu.cs.AlgAE.Animations.LocalJavaAnimation;
import edu.odu.cs.AlgAE.Server.MenuFunction;


public class GraphTraversalDemo extends LocalJavaAnimation {

	public GraphTraversalDemo() {
		super("Graph Traversals");
	}

	@Override
	public String about() {
		return "Demonstration of graph traversals\n" +
		"and topological sorting prepared for CS 361, \n" +
		"Advanced Data Structures and Algorithms,\n" +
		"Old Dominion University\n" +
				"Summer 2011";
	}


	Color colorList[] = {Color.yellow, Color.blue, Color.red,
				       Color.green, Color.cyan, Color.magenta,
				       Color.PINK, Color.black};

	DiGraph<TopoData,X> g = new DiGraph<TopoData,X>();


	void addVertex()
	{
	  Vertex<TopoData,X> v = g.addVertex();
	  out.println("Added vertex " + v);
	}


	void removeVertex()
	{
	  String name = promptForInput("Vertex to remove", ".+");
	  Vertex<TopoData,X> v = find_if (g.vbegin(), g.vend(), name).at();
	  if (v != null) {
	      g.removeVertex (v);
	  } else
	    out.println ("Could not find requested vertex.");
	}





	@SuppressWarnings("unchecked")
	private CppIterator<Vertex<TopoData,X>> find_if(CppIterator<Vertex<TopoData,X>> start,
			CppIterator<Vertex<TopoData,X>> stop, String name) 
	{
		CppIterator<Vertex<TopoData,X>> current = (CppIterator<Vertex<TopoData,X>>)start.clone();
		while (!current.equals(stop)) {
			Vertex<TopoData,X> v = current.at();
			if (v.getLabel().equals(name)) {
				return current;
			}
			current.increment();
		}
		return stop;
	}
	

	void addEdge()
	{
		String name = promptForInput("Source Vertex", ".+");
		Vertex<TopoData,X> source = find_if (g.vbegin(), g.vend(), name).at();
		if (source != null)
		{
			name = promptForInput("Destination Vertex", ".+");
			Vertex<TopoData,X> dest = find_if (g.vbegin(), g.vend(), name).at();
			if (dest != null)
			{
				g.addEdge (source, dest);
			}
			else
				out.println("Could not find requested destination vertex.");
		}
		else
			out.println("Could not find requested source vertex.");
	}





	void removeEdge()
	{
		String name = promptForInput("Source Vertex", ".+");
		Vertex<TopoData,X> source = find_if (g.vbegin(), g.vend(), name).at();
		if (source != null)
		{
			name = promptForInput("Destination Vertex", ".+");
			Vertex<TopoData,X> dest = find_if (g.vbegin(), g.vend(), name).at();
			if (dest != null)
			{
				Edge<TopoData,X> e = g.getEdge(source, dest);
				if (e != null) {
					g.removeEdge(e);
				} else {
					out.println("Could not find requested edge.");
				}
			}
			else
				out.println("Could not find requested destination vertex.");
		}
		else
			out.println("Could not find requested source vertex.");
	}

	Random rand = new Random();

	void generate()
	{
	  g.clear();
	  
	  int nv  = promptForIntegerInput("Number of Vertices");
	  if (nv > 26)
	    nv = 26;

	  int avgOutDegree = promptForIntegerInput("Average degree");

	  generate (nv, avgOutDegree);
	}


	void generate(int nv, int avgOutDegree)
	{
	  g.clear();
	  
	  Vertex<?,?>[] v = new Vertex<?,?>[nv];
	  for (int i = 0; i < nv; ++i) {
		  v[i] = g.addVertex();
	  }


	  for (int i = 0; i < nv; ++i) {
		  @SuppressWarnings("unchecked")
		Vertex<TopoData,X> source = (Vertex<TopoData,X>)v[i];
		  for (int j = i; j < nv; ++j) {
			  @SuppressWarnings("unchecked")
			Vertex<TopoData,X> dest = (Vertex<TopoData,X>)v[j];
			  if (source != dest && rand.nextInt(nv) < avgOutDegree)
			  {
				  g.addEdge (source, dest);
			  }
	      }
	  }
	}


	private int promptForIntegerInput(String prompt) {
		String v = promptForInput(prompt, "[0-9]+");
		return Integer.parseInt(v);
	}

	void clear()
	{
	  g.clear();
	}


	void doDepthFirst()
	{
		String name = promptForInput("Starting Vertex?", ".+");
		Vertex<TopoData,X> source = find_if (g.vbegin(), g.vend(), name).at();
		if (source != null)
		{
			name = promptForInput("Label to search for?", ".+");
			GraphAlgorithms.depthFirstSearch (g, source, name);
		}
		else
			out.println("Could not find requested starting vertex.");
	}


	void doBreadthFirst()
	{
		String name = promptForInput("Starting Vertex?", ".+");
		Vertex<TopoData,X> source = find_if (g.vbegin(), g.vend(), name).at();
		if (source != null)
		{
			name = promptForInput("Label to search for?", ".+");
			GraphAlgorithms.breadthFirstSearch (g, source, name);
		}
		else
			out.println("Could not find requested starting vertex.");
	}

	
	void doSort ()
	{
		boolean done = GraphAlgorithms.topologicalSort(g, new GraphAlgorithms.VList());
		if (done)
			out.println("Sort was successful");
		else
			out.println("Sort failed - graph contains a cycle.");
	}



	  
	@Override
	public void buildMenu() {
		
		registerStartingAction(new MenuFunction() {

			@Override
			public void selected() {
				generate(6, 2);
				showVertices(g);
			}
		});
		
		register("Add Vertex", new MenuFunction() {
			@Override
			public void selected() {
				addVertex();
				showVertices(g);
			}		
		});
		register("Remove Vertex", new MenuFunction() {
			@Override
			public void selected() {
				removeVertex();
				showVertices(g);
			}		
		});

		register("Add Edge", new MenuFunction() {
			@Override
			public void selected() {
				addEdge();
				showVertices(g);
			}		
		});
		register("Remove Edge", new MenuFunction() {
			@Override
			public void selected() {
				removeEdge();
				showVertices(g);
			}		
		});

		register("Generate Random Graph", new MenuFunction() {
			@Override
			public void selected() {
				generate();
				showVertices(g);
			}		
		});
		register("Clear", new MenuFunction() {
			@Override
			public void selected() {
				g.clear();
				showVertices(g);
			}		
		});
	
		
	
		register("Depth-first search", new MenuFunction() {
			@Override
			public void selected() {
				doDepthFirst();
			}

		});

		register("Breadth-first search", new MenuFunction() {
			@Override
			public void selected() {
				doBreadthFirst();
			}

		});

		
		register("Topological Sort", new MenuFunction() {
			@Override
			public void selected() {
				doSort();
			}

		});

	}
	

	private void showVertices(DiGraph<TopoData,X> g) {
		getMemoryModel().getGlobalComponents().clear();
		for (CppIterator<Vertex<TopoData,X>> v = g.vbegin(); !v.equals(g.vend()); v.increment()) {
			globalVar("", v.at());
		}
	}		
	
	
	
	public static void main (String[] args) {
		GraphTraversalDemo demo = new GraphTraversalDemo();
		demo.runAsMain();
	}

}
