package edu.odu.cs.cs361.animations;

import java.awt.Color;//!
import java.util.Arrays;//!
import java.util.Comparator;//!
import java.util.HashMap;//!
import java.util.LinkedList;//!
import java.util.List;//!
import java.util.ListIterator;//!

import edu.odu.cs.cs361.animations.graphs.CppIterator;//!
import edu.odu.cs.cs361.animations.graphs.Edge;//!
import edu.odu.cs.cs361.animations.graphs.Graph;
import edu.odu.cs.cs361.animations.graphs.Vertex;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!
import static edu.odu.cs.AlgAE.Server.LocalServer.activate;//!

public class GraphColoring {//!


public static class CompareByDegree implements Comparator<Vertex<ColoringData,X>> {//!
	
	public CompareByDegree ()//!
	{//!
	}//!

	@Override//!
	public int compare(Vertex<ColoringData,X> v, Vertex<ColoringData,X> w) {//!
		return v.get().degree - w.get().degree;//!
	}//!

}//!

public static class CompareByConstraints implements Comparator<Vertex<ColoringData,X>> {//!
	
	public CompareByConstraints ()//!
	{//!
	}//!

	@Override//!
	public int compare(Vertex<ColoringData,X> v, Vertex<ColoringData,X> w) {//!
		return w.get().constraints - v.get().constraints;//!
	}//!

}//!

public static class VList extends LinkedList<Vertex<ColoringData,X>> implements CanBeRendered<VList>, Renderer<VList> //!
{//!

	private Color color;
	
	public VList () {
		super();
		color = null;
	}

	public VList (Color c) {
		super();
		color = c;
	}
	
	@Override//!
	public Renderer<VList> getRenderer() {//!
		return this;//!
	}//!

	@Override//!
	public Color getColor(VList obj) {//!
		return color;//!
	}//!

	@Override//!
	public List<Component> getComponents(VList obj) {//!
		LinkedList<Component> comp = new LinkedList<Component>();//!
		for (Vertex<ColoringData,X> v: this) {//!
			comp.add((new Component(v.getLabel())));//!
		}//!
		return comp;//!
	}//!

	@Override//!
	public List<Connection> getConnections(VList obj) {//!
		return new LinkedList<Connection>();//!
	}//!

	@Override//!
	public int getMaxComponentsPerRow(VList obj) {//!
		return 100;//!
	}//!

	@Override//!
	public String getValue(VList obj) {//!
		return "";//!
	}//!
	
}//!


private static void swapSmallestWithFront (VList array, Comparator<Vertex<ColoringData,X>> comp) //!
{//!
	if (array.size() > 1) {//!
		ListIterator<Vertex<ColoringData,X>> iter0 = array.listIterator();
		Vertex<ColoringData,X> v0 = iter0.next();
		for (ListIterator<Vertex<ColoringData,X>> iter = array.listIterator(); iter.hasNext();) {//!
			Vertex<ColoringData,X> vi = iter.next();
			
			if (comp.compare(v0, vi) > 0) {//!
				iter.set(v0);//!
				iter0.set(vi);//!
				v0 = vi;//!
			}//!
		}//!
	}//!
}//!



static int max (int x, int  y)//!
{//!
	return (x > y) ? x : y; //!
}//!
static int min (int x, int  y)//!
{//!
	return (x < y) ? x : y; //!
}//!

static int clashingColors (Graph<ColoringData,X> g,//!int clashingColors (Graph& g,
		HashMap<Vertex<ColoringData,X>, Integer> vertexNumbers,//!                    VertexToIntegers& vertexNumbers,
		BackTrack colors)//!                    const BackTrack& colors)
{
  // Test to see if the current coloring is OK. If not, return the
  // lowest number of a vertex that we want to change for the next
  // potential solution.

  int vertexToChange = vertexNumbers.size();
  for (CppIterator<Vertex<ColoringData,X>> v = g.vbegin(); !v.equals(g.vend()); v.increment())//!  for (AllVertices v = g.vbegin(); v != g.vend(); ++v)
    {
	  int vNum = vertexNumbers.get(v.at()).intValue();//!      int vNum = vertexNumbers[*v];
      int vColor = colors.get(vNum);//!      int vColor = colors[vNum];
      // Check to see if *v is adjacent to a 
      //  vertex of the same color.
      // If so, one of them has to change.
      int clashingVertex = -1;
      for (CppIterator<Edge<ColoringData,X>> e = g.outbegin(v.at()); //!      for (AllOutgoingEdges e = g.outbegin(*v);
           (clashingVertex < 0) && (e.notEnd()); e.increment())//!          (clashingVertex < 0) && (e != g.outend(*v)); ++e)
        {
         Vertex<ColoringData,X> w = e.at().dest();//!         Vertex w = (*e).dest();
         int wNum = vertexNumbers.get(w).intValue();//!         int wNum = vertexNumbers[w];
         int wColor = colors.get(wNum);//!         int wColor = colors[wNum];
         if (vColor == wColor) 
           clashingVertex = max(vNum, wNum);
        }
      if (clashingVertex >= 0)
      	vertexToChange = min(vertexToChange, clashingVertex);
    }
  return vertexToChange;
}	
	
static Color colorList[] = {Color.yellow, Color.blue, Color.red,//!
	       Color.green, Color.cyan, Color.magenta,//!
	       Color.PINK, Color.black};//!


static boolean colorGraph_backtracking (Graph<ColoringData,X> g, int numColors,//!bool colorGraph_backtracking (Graph& g, unsigned numColors,
			      Color[] colors)//!			      ColorMap& colors)
{
	ActivationRecord arec = activate(GraphColoring.class);//!
	HashMap<Vertex<ColoringData,X>, Integer> vertexNumbers = new HashMap<Vertex<ColoringData,X>, Integer>();//!  VertexToIntegers vertexNumbers;

  for (CppIterator<Vertex<ColoringData,X>> v = g.vbegin(); v.notEnd(); v.increment())//!  for (AllVertices v = g.vbegin(); v != g.vend(); ++v)
    {
	  v.at().set(new ColoringData(true));//!
      vertexNumbers.put(v.at(), vertexNumbers.size());//!      vertexNumbers[*v] = vertexNumbers.size();
    }

  arec.param("g", "").param("numColors",numColors).param("colors", "").breakHere("ready to initialize backtracking");//!
  // Search for acceptable solution via backtracking
  BackTrack problem = new BackTrack(vertexNumbers.size(), numColors);//!  BackTrack problem (vertexNumbers.size(), numColors);

  for (CppIterator<Vertex<ColoringData,X>> v = g.vbegin(); v.notEnd(); v.increment()) v.at().get().color = problem.get(vertexNumbers.get(v.at()));//! 
  arec.var("problem",problem).breakHere("Initialized backtracking");//!
  boolean solved = false;//!  bool solved = false;
  arec.pushScope();//!
  while (!solved && problem.more())
    {
	  for (CppIterator<Vertex<ColoringData,X>> v = g.vbegin(); v.notEnd(); v.increment()) v.at().get().color = problem.get(vertexNumbers.get(v.at()));//! 
	  arec.var("solved",solved).breakHere("Do we have a solution?");//!
      int pruneAt = clashingColors(g, vertexNumbers, problem);
      arec.var("pruneAt",pruneAt);//!
      if (pruneAt >= vertexNumbers.size())
      {arec.breakHere("Do we have a solution? Yes!");//!
        solved = true; 
        arec.var("solved",solved);//!
        }//!
      else
      {arec.breakHere("Not a solution. Prune indicated vertex.");//!
        problem.prune(pruneAt+1);
      }//!
    }
  arec.popScope();//!

  for (CppIterator<Vertex<ColoringData,X>> v = g.vbegin(); v.notEnd(); v.increment()) v.at().get().color = problem.get(vertexNumbers.get(v.at()));//! 
  arec.var("solved",solved).breakHere("Completed backtracking");//!
//!  colors.clear();  
//!  if (solved)
//!    {
     // Construct the solution (map of vertices to colors)
//!     for (AllVertices v = g.vbegin(); v != g.vend(); ++v)
//!       colors[*v] = problem[vertexNumbers[*v]];
//!    }
  return solved;
}




//!typedef hash_map<Vertex, int, VertexHash> VintMap;

//!struct CompareVertices
//!{
//!  VintMap& vimap;
//!  bool lessComp;
  

//!  CompareVertices (VintMap& mp, bool compareWithLess)
//!    : vimap(mp), lessComp(compareWithLess) {}

//!  bool operator() (Vertex left, Vertex right)
//!    {if (lessComp)
//!      return vimap[left] < vimap[right];
//!    else
//!      return vimap[left] > vimap[right];
//!    }
//!};


//!typedef adjustable_priority_queue<Vertex, VintMap, 
//!                                  CompareVertices>
//!   PriorityQueue;



boolean colorIsOK (Graph<ColoringData,X> g, Vertex<ColoringData,X> v, int color)//!bool colorIsOK (Graph& g, Vertex v, int color, ColorMap& colors)
// return true if no node adjacent to v has color "color"
{
  for (CppIterator<Edge<ColoringData,X>> e = g.outbegin(v); e.notEnd(); e.increment())//!  for (AllOutgoingEdges e = g.outbegin(v); e != g.outend(v); ++e)
    {
      Vertex<ColoringData,X> w = e.at().dest();//!      Vertex w = (*e).dest();
      if ((w.get().color >= 0)//!if ((colors.find(w) != colors.end())
	    && (w.get().color == color))//!	    && (colors[w] == color))
    	  return false;
    }
  return true;
}


private static int chooseColor (Graph<ColoringData,X> g, Vertex<ColoringData,X> v,//!int chooseColor (Graph& g, Vertex v,
		 int numColors,//!		 unsigned numColors,
		 //!		 ColorMap& colors,
		 int startingColor)
{
  boolean[] colorHasBeenSeen = new boolean[numColors];//!  bool* colorHasBeenSeen = new bool[numColors];
  Arrays.fill (colorHasBeenSeen, false);//!  fill (colorHasBeenSeen, colorHasBeenSeen+numColors, false);
  for (CppIterator<Edge<ColoringData,X>> e = g.outbegin(v); e.notEnd(); e.increment())//!  for (AllOutgoingEdges e = g.outbegin(v); e != g.outend(v); ++e)
    {
      Vertex<ColoringData,X> w = (e.at()).dest();//!      Vertex w = (*e).dest();
      int c = w.get().color;//!      int c = colors[w];
      if ((c >= 0) && !colorHasBeenSeen[c])
      {
    	  colorHasBeenSeen[c] = true;
      }
    }
  int chosen = 0;//!  int chosen = find(colorHasBeenSeen+startingColor+1,
  while (chosen < numColors && colorHasBeenSeen[chosen])//!		    colorHasBeenSeen+numColors, false)
    ++chosen;//!    - colorHasBeenSeen;
//!  delete [] colorHasBeenSeen;
  return (chosen < numColors) ? chosen : -1;
}



private static int computeConstraints (Graph<ColoringData,X> g, Vertex<ColoringData,X> v,//!int computeConstraints (Graph& g, Vertex v,
			int numColors)//!			unsigned numColors, ColorMap& colors)
{
  boolean[] colorHasBeenSeen = new boolean[numColors];//!  bool* colorHasBeenSeen = new bool[numColors];
  Arrays.fill (colorHasBeenSeen, false);//!  fill (colorHasBeenSeen, colorHasBeenSeen+numColors, false);
  int nSeen = 0;
  for (CppIterator<Edge<ColoringData,X>> e = g.outbegin(v); e.notEnd(); e.increment())//!  for (AllOutgoingEdges e = g.outbegin(v); e != g.outend(v); ++e)
    {
      Vertex<ColoringData,X> w = (e.at()).dest();//!      Vertex w = (*e).dest();
      int c = w.get().color;//!      int c = colors[w];
      if ((c >= 0) && !colorHasBeenSeen[c])
      {
    	  ++nSeen;
    	  colorHasBeenSeen[c] = true;
      }
    }
//!  delete [] colorHasBeenSeen;
  return nSeen;
}


static boolean colorGraph_heuristic (Graph<ColoringData,X> g, int numColors//!bool colorGraph_heuristic (Graph& g, unsigned numColors,
			   )//!			   ColorMap& colors)
{
	ActivationRecord arec = activate(GraphColoring.class);//!
	  for (CppIterator<Vertex<ColoringData,X>> vv = g.vbegin(); vv.notEnd(); vv.increment())//!
	    {//!
		  vv.at().set(new ColoringData());//
	    }//!

	  arec.param("g", "").param("numColors",numColors).breakHere("starting");//!
  // Step 1 - push any vertices with degree < numColors onto a stack
  VList stk = new VList(Color.green);//!  stack<vector<Vertex> > stk;

 //! VintMap degrees;
 //! VintMap constraints;
 arec.var("stk", stk).breakHere("starting");//!
 for (CppIterator<Vertex<ColoringData,X>> v = g.vbegin(); v.notEnd() ; v.increment())//! for (AllVertices v = g.vbegin(); v != g.vend() ; ++v)
   {
     v.at().get().color = -1;//!     colors[*v] = -1;
     v.at().get().constraints = 0;//!     constraints[*v] = 0;
     v.at().get().degree = g.outdegree(v.at());//!degrees[*v] = g.outdegree(*v);
   }
 arec.breakHere("set up a priority queue");//!
 VList pq = new VList(Color.blue);//! PriorityQueue pq (g.vbegin(), g.vend(), CompareVertices(degrees, false));
 for (CppIterator<Vertex<ColoringData,X>> vv = g.vbegin(); vv.notEnd(); vv.increment()) pq.add(vv.at());//!
 swapSmallestWithFront(pq, new CompareByDegree());//!
 arec.var("pq", pq).breakHere("Push vertices with low degree onto stack");//!
 arec.pushScope();//!
 while ((!pq.isEmpty()) && (pq.get(0).get().degree < numColors))//! while ((!pq.empty()) && (degrees[v = pq.top()] < numColors))
   {
	 Vertex<ColoringData,X> v = pq.getFirst();//! Vertex v = pq.top();
	 arec.refVar("v",v).breakHere("push this vertex");//!
     pq.removeFirst();//!     pq.pop();
     swapSmallestWithFront(pq, new CompareByDegree());//
     stk.addFirst(v);//!     stk.push (v);
     v.get().color = -2;//!    colors[v] = -2;
     arec.pushScope();//!
     for (CppIterator<Edge<ColoringData,X>> e = g.outbegin(v); e.notEnd(); e.increment())//!for (AllOutgoingEdges e = g.outbegin(v); e != g.outend(v); ++e)
       {
    	 Vertex<ColoringData,X> w = e.at().dest();//!    	 Vertex w = (*e).dest();
    	 if (w.get().color == -1)//!    	 if (colors[w] == -1)
    	 {
    		 arec.refVar("w",w).breakHere("w's degree must be reduced");//!
            w.get().degree = w.get().degree - 1;//!            degrees[w] = degrees[w] - 1;
            swapSmallestWithFront(pq, new CompareByDegree());//!            pq.adjust(w);
    	 }
       }
     arec.popScope();//!
	 arec.breakHere("done with v - check pq again");//!
   }
 arec.popScope();//!
    
  // Step 2 - assign colors starting with the "most constrained" node
 VList pq2 = new VList(Color.blue);//! PriorityQueue pq2 (CompareVertices(constraints, true));
 arec.var("pq2",pq2).breakHere("Create a new priority queue, ordered by #adj. vertices already colored");//!
 while (!pq.isEmpty())//! while (!pq.empty())
  {
	arec.breakHere("Creating a new priority queue, ordered by #adj. vertices already colored");//!
    pq2.add (pq.getFirst());//!    pq2.push (pq.top());
    swapSmallestWithFront(pq2, new CompareByDegree());//!
    pq.removeFirst();//!    pq.pop();
  }

 arec.breakHere("Assign colors to most heavily constrained nodes");
 arec.pushScope();//!
 while (!pq2.isEmpty())//! while (!pq2.empty())
   {
     Vertex<ColoringData,X> v = pq2.getFirst();//!     v = pq2.top();
     pq2.remove(0);//!     pq2.pop();
     arec.refVar("v", v).breakHere("Choose a color for v");//!
     int c = chooseColor (g, v, numColors, -1);//!     int c = chooseColor (g, v, numColors, colors, -1);
     arec.var("c", c);//!
     if (c < 0)
       { // Could not assign a color - give up
         arec.breakHere("Could not assign v a color - give up");//!
         return false;
       }
     v.get().color = c;//!     colors[v] = c;
     arec.breakHere("v has been colored");//!
     arec.pushScope();
     for (CppIterator<Edge<ColoringData,X>> e = g.outbegin(v); e.notEnd(); e.increment())//!     for (AllOutgoingEdges e = g.outbegin(v); e != g.outend(v); ++e)
     {
    	 Vertex<ColoringData,X> w = e.at().dest();//!    	 Vertex w = (*e).dest();
    	 arec.refVar("w", w);//!
    	 if (w.get().color == -1)//!    	 if (colors[w] == -1)
    	 {
    	     arec.breakHere("compute constraints on w");//!
    	     int nConstraints = computeConstraints (g, w, numColors);//!    	     int nConstraints = computeConstraints (g, w, numColors, colors);
    	     arec.var("nConstraints",nConstraints);
    	     if (w.get().constraints != nConstraints)//!    	     if (constraints[w] != nConstraints)
    	     {
        	     arec.var("nConstraints",nConstraints).breakHere("number of constraints on w have changed");//!
        	     w.get().constraints = nConstraints;//!        	     constraints[w] = nConstraints;
        	     swapSmallestWithFront(pq2, new CompareByConstraints());//!        	     pq2.adjust (w);
        	     arec.breakHere("w and pq2 have been updated");//!
    	     }
    	 }
       }
   }
 arec.popScope();//!
    
  // Step 3 - assign colors to the nodes saved on the stack
 arec.param("g", "").param("numColors",numColors).var("stk", stk).var("pq2", pq2).breakHere("assign colors to the nodes saved on the stack");//!
 while (!stk.isEmpty())//! while (!stk.empty())
   {
	 Vertex<ColoringData,X> v = stk.getFirst();//!     v = stk.top();
     stk.remove(0);//!     stk.pop();
     arec.refVar("v", v).breakHere("choose a color for v");//!
     v.get().color = chooseColor (g, v, numColors, -1);//!     colors[v] = chooseColor (g, v, numColors, colors, -1);
     arec.breakHere("v has been colored.");//!
   }
  return true;
}



}