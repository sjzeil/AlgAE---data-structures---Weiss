package edu.odu.cs.cs361.animations;


import java.awt.Color;
import java.util.Random;

import edu.odu.cs.AlgAE.Animations.LocalJavaAnimation;
import edu.odu.cs.AlgAE.Server.MenuFunction;
import edu.odu.cs.cs361.animations.graphs.CppIterator;
import edu.odu.cs.cs361.animations.graphs.DiGraph;
import edu.odu.cs.cs361.animations.graphs.Edge;
import edu.odu.cs.cs361.animations.graphs.Vertex;

public class DijkstraPrimDemo extends LocalJavaAnimation {

	public DijkstraPrimDemo() {
		super("Graph Optimizations");
	}

	@Override
	public String about() {
		return "Demonstration of Dijkstra and\n" +
		"and Prim algorithms prepared for CS 361, \n" +
		"Advanced Data Structures and Algorithms,\n" +
		"Old Dominion University\n" +
				"Summer 2011";
	}


	Color colorList[] = {Color.yellow, Color.blue, Color.red,
				       Color.green, Color.cyan, Color.magenta,
				       Color.PINK, Color.black};

	DiGraph<DVData,DEData> g = new DiGraph<DVData,DEData>();


	void addVertex()
	{
	  Vertex<DVData,DEData> v = g.addVertex();
	  out.println("Added vertex " + v);
	}


	void removeVertex()
	{
	  String name = promptForInput("Vertex to remove", ".+");
	  Vertex<DVData,DEData> v = find_if (g.vbegin(), g.vend(), name).at();
	  if (v != null) {
	      g.removeVertex (v);
	  } else
	    out.println ("Could not find requested vertex.");
	}





	@SuppressWarnings("unchecked")
	private CppIterator<Vertex<DVData,DEData>> find_if(CppIterator<Vertex<DVData,DEData>> start,
			CppIterator<Vertex<DVData,DEData>> stop, String name) 
	{
		CppIterator<Vertex<DVData,DEData>> current = (CppIterator<Vertex<DVData,DEData>>)start.clone();
		while (!current.equals(stop)) {
			Vertex<DVData,DEData> v = current.at();
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
		Vertex<DVData,DEData> source = find_if (g.vbegin(), g.vend(), name).at();
		if (source != null)
		{
			name = promptForInput("Destination Vertex", ".+");
			Vertex<DVData,DEData> dest = find_if (g.vbegin(), g.vend(), name).at();
			if (dest != null)
			{
				Edge<DVData,DEData> e = g.addEdge (source, dest);
				String vs = promptForInput("Weight/distance for edge (integer):", "[0-9]+");
				int v = Integer.parseInt(vs);
				e.set(new DEData());
				e.get().weight = v;
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
		Vertex<DVData,DEData> source = find_if (g.vbegin(), g.vend(), name).at();
		if (source != null)
		{
			name = promptForInput("Destination Vertex", ".+");
			Vertex<DVData,DEData> dest = find_if (g.vbegin(), g.vend(), name).at();
			if (dest != null)
			{
				Edge<DVData,DEData> e = g.getEdge(source, dest);
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
		Vertex<DVData,DEData> source = (Vertex<DVData,DEData>)v[i];
		  for (int j = i; j < nv; ++j) {
			  @SuppressWarnings("unchecked")
			Vertex<DVData,DEData> dest = (Vertex<DVData,DEData>)v[j];
			  if (source != dest && rand.nextInt(nv) < avgOutDegree)
			  {
				  Edge<DVData,DEData> e = g.addEdge (source, dest);
				  e.set(new DEData());
				  e.get().weight = rand.nextInt(8) + 1;
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


	void doDijkstra()
	{
		String name = promptForInput("Starting Vertex?", ".+");
		Vertex<DVData,DEData> start = find_if (g.vbegin(), g.vend(), name).at();
		if (start != null)
		{
			name = promptForInput("Ending Vertex?", ".+");
			Vertex<DVData,DEData> finish = find_if (g.vbegin(), g.vend(), name).at();
			if (finish != null)
			{
				GraphOptimization.VList<Vertex<DVData,DEData>> path = new GraphOptimization.VList<Vertex<DVData,DEData>>();
				GraphOptimization.findWeightedShortestPath(g, path, start, finish);
			}
			else
				out.println("Could not find requested ending vertex.");
		}
		else
			out.println("Could not find requested starting vertex.");
	}

	void doPrim()
	{
		String name = promptForInput("Starting Vertex?", ".+");
		Vertex<DVData,DEData> start = find_if (g.vbegin(), g.vend(), name).at();
		if (start != null)
		{
				GraphOptimization.VList<Edge<DVData,DEData>> path = new GraphOptimization.VList<Edge<DVData,DEData>>();
				GraphOptimization.findMinSpanTree(g, path, start);
		}
		else
			out.println("Could not find requested starting vertex.");
	}



	  
	@Override
	public void buildMenu() {
		
		registerStartingAction(new MenuFunction() {

			@Override
			public void selected() {
				generateAirline();
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
	
		
	
		register("Find shortest path", new MenuFunction() {
			@Override
			public void selected() {
				doDijkstra();
			}

		});

		register("Find minimum spanning tree", new MenuFunction() {
			@Override
			public void selected() {
				doPrim();
			}

		});

	}
	

	private void showVertices(DiGraph<DVData,DEData> g) {
		getMemoryModel().getGlobalComponents().clear();
		for (CppIterator<Vertex<DVData,DEData>> v = g.vbegin(); !v.equals(g.vend()); v.increment()) {
			globalVar("", v.at());
		}
	}		

	private void generateAirline() {
		g.clear();
		Vertex<DVData,DEData> Norfolk = city("Norfolk");
		Vertex<DVData,DEData> Raleigh = city("Raleigh");
		Vertex<DVData,DEData> WashDC = city("WashDC");
		Vertex<DVData,DEData> Boston = city("Boston");
		Vertex<DVData,DEData> NewYork = city("NewYork");
		flight(Norfolk, Raleigh, 64);
		flight(Raleigh, NewYork, 101);
		flight(NewYork, Boston, 175);
		flight(NewYork, WashDC, 199);
		flight(Boston, NewYork, 159);
		flight(Boston, WashDC, 75);
		flight(WashDC, Norfolk, 58);
		flight(WashDC, NewYork, 239);
		flight(WashDC, Boston, 79);
	}

	
	
	private void flight(Vertex<DVData, DEData> from,
			Vertex<DVData, DEData> to, int w) {
		Edge<DVData, DEData> e = g.addEdge(from, to);
		e.set(new DEData());
		e.get().weight = w;
	}

	private Vertex<DVData, DEData> city(String label) {
		Vertex<DVData, DEData> v = g.addVertex();
		v.setLabel(label);
		return v;
	}

	public static void main (String[] args) {
		DijkstraPrimDemo demo = new DijkstraPrimDemo();
		demo.runAsMain();
	}

}
