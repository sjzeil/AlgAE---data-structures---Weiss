package edu.odu.cs.cs361.animations;//!


import java.awt.Color;//!
import java.util.Random;//!

import edu.odu.cs.AlgAE.Animations.LocalJavaAnimation;//!
import edu.odu.cs.cs361.animations.graphs.CppIterator;//!
import edu.odu.cs.cs361.animations.graphs.DiGraph;//!
import edu.odu.cs.cs361.animations.graphs.Edge;//!
import edu.odu.cs.cs361.animations.graphs.Graph;//!
import edu.odu.cs.cs361.animations.graphs.Vertex;//!
import edu.odu.cs.AlgAE.Server.MenuFunction;//!

public class GraphColoringDemo extends LocalJavaAnimation {

	public GraphColoringDemo() {
		super("Graph Coloring");
	}

	@Override
	public String about() {
		return "Demonstration of graph coloring,\n" +
		"via backtracking and heuristic methods, prepared for CS 361, \n" +
		"Advanced Data Structures and Algorithms,\n" +
		"Old Dominion University\n" +
				"Summer 2011";
	}

	
	Graph<ColoringData,X> g = new Graph<ColoringData,X>();


	void addVertex()
	{
	  Vertex<ColoringData,X> v = g.addVertex();
	  out.println("Added vertex " + v);
	}


	void removeVertex()
	{
	  String name = promptForInput("Vertex to remove", ".+");
	  Vertex<ColoringData,X> v = find_if (g.vbegin(), g.vend(), name).at();
	  if (v != null) {
	      g.removeVertex (v);
	  } else
	    out.println ("Could not find requested vertex.");
	}





	@SuppressWarnings("unchecked")
	private CppIterator<Vertex<ColoringData,X>> find_if(CppIterator<Vertex<ColoringData,X>> start,
			CppIterator<Vertex<ColoringData,X>> stop, String name) 
	{
		CppIterator<Vertex<ColoringData,X>> current = (CppIterator<Vertex<ColoringData,X>>)start.clone();
		while (!current.equals(stop)) {
			Vertex<ColoringData,X> v = current.at();
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
		Vertex<ColoringData,X> source = find_if (g.vbegin(), g.vend(), name).at();
		if (source != null)
		{
			name = promptForInput("Destination Vertex", ".+");
			Vertex<ColoringData,X> dest = find_if (g.vbegin(), g.vend(), name).at();
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
		Vertex<ColoringData,X> source = find_if (g.vbegin(), g.vend(), name).at();
		if (source != null)
		{
			name = promptForInput("Destination Vertex", ".+");
			Vertex<ColoringData,X> dest = find_if (g.vbegin(), g.vend(), name).at();
			if (dest != null)
			{
				Edge<ColoringData,X> e = g.getEdge(source, dest);
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
		Vertex<ColoringData,X> source = (Vertex<ColoringData,X>)v[i];
		  for (int j = i; j < nv; ++j) {
			  @SuppressWarnings("unchecked")
			Vertex<ColoringData,X> dest = (Vertex<ColoringData,X>)v[j];
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


	
	void doColorback ()
	{
	  int numColors = 0;
	  while (numColors < 2 || numColors > 8)
	    numColors = promptForIntegerInput("How many colors? (2-8)");

	  for (CppIterator<Vertex<ColoringData,X>> v = g.vbegin(); !v.equals(g.vend()); v.increment()) {
		  v.at().setColor(Color.lightGray);
	  }
	  GraphColoring.colorGraph_backtracking (g, numColors, new Color[0]);
	}


	void doColorheur ()
	{
		  int numColors = 0;
		  while (numColors < 2 || numColors > 8)
		    numColors = promptForIntegerInput("How many colors? (2-8)");

		  for (CppIterator<Vertex<ColoringData,X>> v = g.vbegin(); !v.equals(g.vend()); v.increment()) {
			  v.at().setColor(Color.lightGray);
		  }
		  GraphColoring.colorGraph_heuristic (g, numColors);
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
	
		
		register("Color via backtracking", new MenuFunction() {
			@Override
			public void selected() {
				doColorback();
			}

		});

		register("Color via heuristics", new MenuFunction() {
			@Override
			public void selected() {
				doColorheur();
			}		
		});
	}
	

	private void showVertices(DiGraph<ColoringData,X> g) {
		getMemoryModel().getGlobalComponents().clear();
		for (CppIterator<Vertex<ColoringData,X>> v = g.vbegin(); !v.equals(g.vend()); v.increment()) {
			globalVar("", v.at());
		}
	}		
	
	
	
	public static void main (String[] args) {
		GraphColoringDemo demo = new GraphColoringDemo();
		demo.runAsMain();
	}

}
