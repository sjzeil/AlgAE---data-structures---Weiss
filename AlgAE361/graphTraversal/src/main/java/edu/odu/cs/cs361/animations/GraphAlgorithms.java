package edu.odu.cs.cs361.animations;//!

import java.awt.Color;//!
import java.util.LinkedList;//!
import java.util.List;//!

import edu.odu.cs.cs361.animations.graphs.CppIterator;//!
import edu.odu.cs.cs361.animations.graphs.DiGraph;//!
import edu.odu.cs.cs361.animations.graphs.Edge;//!
import edu.odu.cs.cs361.animations.graphs.Vertex;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!
import static edu.odu.cs.AlgAE.Server.LocalServer.activate;//!


public class GraphAlgorithms {//!


static int max (int x, int  y)//!
{//!
	return (x > y) ? x : y; //!
}//!
static int min (int x, int  y)//!
{//!
	return (x < y) ? x : y; //!
}//!

public static class VList extends LinkedList<Vertex<TopoData,X>> implements CanBeRendered<VList>, Renderer<VList> //!
{//!

	private Color color;//!
	
	public VList () {//!
		super();//!
		color = null;//!
	}//!

	public VList (Color c) {//!
		super();//!
		color = c;//!
	}//!
	
	public boolean empty() {return isEmpty();}//!
	
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
		for (Vertex<TopoData,X> v: this) {//!
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

	public int count(Vertex<TopoData, X> v) {//!
		for (Vertex<TopoData, X> w: this) {
			if (v == w)
				return 1;
		}
		return 0;
	}
	
}//!


//private static void swapSmallestWithFront (VList array, Comparator<Vertex<TopoData,X>> comp) //!
//{//!
//	if (array.size() > 1) {//!
//		ListIterator<Vertex<TopoData,X>> iter0 = array.listIterator();
//		Vertex<TopoData,X> v0 = iter0.next();
//		for (ListIterator<Vertex<TopoData,X>> iter = array.listIterator(); iter.hasNext();) {//!
//			Vertex<TopoData,X> vi = iter.next();
//			
//			if (comp.compare(v0, vi) > 0) {//!
//				iter.set(v0);//!
//				iter0.set(vi);//!
//				v0 = vi;//!
//			}//!
//		}//!
//	}//!
//}//!



// A topological sort of a directed graph is any listing of the vertices
// in g such that v1 precedes v2 in the listing only if there exists no
// path from v2 to v1.
//
// The following routine attempts a topological sort of g. If the sort is
// successful, the return value is true and the ordered listing of
// vertices is placed in sorted.  If no toplological sort is possible
// (because the graph contains a cycle), false is returned and sorted will
// be empty.
//
static boolean topologicalSort (DiGraph<TopoData,X> g, VList sorted)//!bool topologicalSort (const DiGraph& g, list<Vertex>& sorted)
{
	ActivationRecord arec = activate(GraphAlgorithms.class);//!
	arec.param("g", "").refParam("sorted", sorted).breakHere("Starting topological sort");//!
  // Step 1: get the indegrees of all vertices. Place vertices with
  // indegree 0 into a queue.
  VList q = new VList(Color.blue);//!  queue<list<Vertex> > q;
  arec.refParam("sorted", sorted).var("q", q).breakHere("Init q with indegree 0 nodes");
  for (CppIterator<Vertex<TopoData,X>> v = g.vbegin(); v.notEnd(); v.increment())//!  for (AllVertices v = g.vbegin(); v != g.vend(); ++v)
    {
	  arec.pushScope();//!
      int indeg = g.indegree(v.at());//!      unsigned indeg = g.indegree(*v);
	  v.at().set(new TopoData());//!
      v.at().get().degree = indeg;//!      inDegree[*v] = indeg;
      arec.refVar("v",v.at()).var("indeg",indeg).breakHere("If degree is zero, add to q");//!
      if (indeg == 0)
      {
    	  v.at().get().color = 0;//!
    	  arec.breakHere("This node has indegree 0");//!
    	  q.add(v.at());//!    	  q.push(*v);
    	  v.at().get().color = -1;//!
      }
      arec.popScope();//!
    }

  arec.breakHere("Queue has been initialized");//!
  // Step 2. Take vertices from the q, one at a time, and add to sorted.
  // As we do, pretend that we have deleted these vertices from the graph,
  // decreasing the indegree of all adjacent nodes. If any nodes attain an
  // indegree of 0 because of this, add them to the queue.
  while (!q.empty())
    {
	  arec.pushScope();//!
      Vertex<TopoData,X> v = q.getFirst();//!      Vertex v = q.front();
      arec.refVar("v", v).breakHere("Get vertex from q");//!      
      q.pop();

      arec.breakHere("Add v to sorted list");//!      
      sorted.add(v);//!      sorted.push_back(v);

      arec.breakHere("Pretend to remove v from the graph");//!      
      for (CppIterator<Edge<TopoData,X>> e = g.outbegin(v); e.notEnd(); e.increment())//!      for (AllOutgoingEdges e = g.outbegin(v); e != g.outend(v); ++e)
        {
    	  arec.pushScope();//!
          Vertex<TopoData,X> adjacent = (e.at()).dest();//!          Vertex adjacent = (*e).dest();
          arec.refVar("adjacent", adjacent).breakHere("Decrement indegree of adjacent vertex");//!      
          adjacent.get().degree = adjacent.get().degree - 1;//!          inDegree[adjacent] = inDegree[adjacent] - 1;
          arec.param("g", "").refParam("sorted", sorted).var("q", q).refVar("v", v).refVar("adjacent", adjacent).breakHere("If no incoming edges are left, add adjacent vertex to the q");//!      
          if (adjacent.get().degree == 0)//!          if (inDegree[adjacent] == 0)
          {
            q.add (adjacent);//!            q.push (adjacent);
          }
          arec.breakHere("Check remaining adjacent vertices");//!
          arec.popScope();//!
        }
      arec.popScope();//!
    }
  
  // Step 3:  Did we finish the entire graph?
  arec.breakHere("Did we process the entire graph?");//!
  if (sorted.size() == g.numVertices())
  {arec.breakHere("Yes! We have sorted the graph.");//! 
  	return true;
  	}//!
  else
    {
	  arec.breakHere("Failed.");//!
      sorted.clear();
	  arec.breakHere("Graph must have contained a cycle..");//!
      return false;
    }
}


static boolean depthFirst (DiGraph<TopoData,X> g,//!bool depthFirst (const DiGraph& g,
		 Vertex<TopoData,X> v,//!		 const Vertex& v,
		 String lookFor,//!		 const string& lookFor,
		 VList visited)//!		 set<Vertex, less<Vertex> >& visited)
{
	ActivationRecord arec = activate(GraphAlgorithms.class);//!
	v.setColor(Color.red);//!
	arec.param("g", "").refParam("v", v).param("lookFor", lookFor).refParam("visited", visited).breakHere("Entered depthFirst.");//!
	v.setColor(Color.pink);//!
  if (v.getLabel().equals(lookFor))//!  if (v.label == lookFor)
  {
    arec.breakHere("Found our target.");//!
    return true;
  }
  else if (visited.count(v) == 0)
    {
      boolean found = false;//!      bool found = false;
      visited.add (v); //!      visited.insert (v);
      arec.var("found",found).breakHere("Search the descendents of this vertex");//!
      for (CppIterator<Edge<TopoData,X>> e = g.outbegin(v); e.notEnd() && !found; e.increment())//!      for (AllOutgoingEdges e = g.outbegin(v); e != g.outend(v) && !found; ++e)
      {
    	  arec.pushScope();//!
    	  arec.var("*e", e.at());//!
    	  e.at().dest().setColor(Color.yellow);//!
    	  arec.breakHere("Recursively search this adjacent vertex");//!
    	  found = depthFirst (g, (e.at()).dest(), lookFor, visited);//!    	  found = depthFirst (g, (*e).dest(), lookFor, visited);
    	  arec.var("found",found).breakHere("Did we find it?");//!
    	  arec.popScope();//!
      }
	  arec.var("found",found).breakHere("Done searching vertex v");//!
      return found;
    }  
  else
  {
	  arec.breakHere("this vertex has no successors");//!
	  return false;
  }
}

	



static void depthFirstSearch (DiGraph<TopoData,X> g,//!void depthFirstSearch (const DiGraph& g,
		       Vertex<TopoData,X> start,//!		       const Vertex &start,
		       String lookFor)//!		       const string &lookFor)
{
	ActivationRecord arec = activate(GraphAlgorithms.class);//!
	for (CppIterator<Vertex<TopoData,X>> v = g.vbegin(); v.notEnd(); v.increment()) {v.at().set(null); v.at().setColor(Color.gray);}//!
	VList visited = new VList(Color.cyan); //!	set<Vertex, less<Vertex> > visited;
	arec.param("g", "").refParam("start", start).param("lookFor", lookFor).refVar("visited", visited).breakHere("Start recursion");//!
	depthFirst (g, start, lookFor, visited);
	arec.param("g", "").refParam("start", start).param("lookFor", lookFor).refVar("visited", visited).breakHere("Finished recursion");//!
}


static void breadthFirstSearch (DiGraph<TopoData,X> g,//!void breadthFirstSearch (const DiGraph& g,
			 Vertex<TopoData,X> start,//!			 const Vertex& start,
			 String lookFor)//!const string& lookFor)
{
	ActivationRecord arec = activate(GraphAlgorithms.class);//!
	for (CppIterator<Vertex<TopoData,X>> v = g.vbegin(); v.notEnd(); v.increment()) {v.at().set(null); v.at().setColor(Color.gray);}//!
	arec.param("g", "").refParam("start", start).param("lookFor", lookFor).breakHere("Set up the data structures");//!
	VList q = new VList(Color.blue);//!	queue<list<Vertex> > q;
	VList visited = new VList(Color.cyan);//!	set<Vertex, less<Vertex> > visited;

	arec.param("g", "").refParam("start", start).param("lookFor", lookFor).refVar("q", q).refVar("visited", visited).breakHere("'visit' the starting vertex");//!
	q.add (start);//!	q.push (start);
	visited.add (start);//!	visited.insert (start);
	boolean found = false;//!	bool found = false;
	arec.param("g", "").refParam("start", start).param("lookFor", lookFor).refVar("q", q).refVar("visited", visited).var("found", found).breakHere("conduct breadth-first search");//!
	while ((!q.empty()) && !found)
	{
      Vertex<TopoData,X> v = q.getFirst();//!      Vertex v = q.front();
      v.setColor(Color.red);//!
      arec.param("g", "").refParam("start", start).param("lookFor", lookFor).refVar("q", q).refVar("visited", visited).var("found", found).refVar("v",v).breakHere("Get node from front of q");//!
      q.removeFirst();//!      q.pop();
      arec.param("g", "").refParam("start", start).param("lookFor", lookFor).refVar("q", q).refVar("visited", visited).var("found", found).refVar("v",v).breakHere("Is this the one we wanted?");//!
      found = (v.getLabel().equals(lookFor));//!      found = (v.label() == lookFor);
      if (found) arec.param("g", "").refParam("start", start).param("lookFor", lookFor).refVar("q", q).refVar("visited", visited).var("found", found).refVar("v",v).breakHere("Found it!");//!
      if (!found)
    	  for (CppIterator<Edge<TopoData,X>> e = g.outbegin(v); e.notEnd(); e.increment())//!    	  for (AllOutgoingEdges e = g.outbegin(v); e != g.outend(v); ++e)
    	  {
    		  Vertex<TopoData,X> w = (e.at()).dest();//!    		  Vertex w = (*e).dest();  
    	      arec.param("g", "").refParam("start", start).param("lookFor", lookFor).refVar("q", q).refVar("visited", visited).var("found", found).refVar("v",v).refVar("w",w).breakHere("Check adjacent vertex w");//!
    		  if (visited.count(w) == 0)
    		  {
        	      arec.param("g", "").refParam("start", start).param("lookFor", lookFor).refVar("q", q).refVar("visited", visited).var("found", found).refVar("v",v).refVar("w",w).breakHere("We have not visited w. Put it in the q for future processing.");//!
        	      q.add (w);//!        	      q.push (w);
        	      visited.add (w);//!        	      visited.insert (w);
        	      arec.param("g", "").refParam("start", start).param("lookFor", lookFor).refVar("q", q).refVar("visited", visited).var("found", found).refVar("v",v).refVar("w",w).breakHere("Marked w as visited.");//!
    		  }
	      }
      v.setColor(Color.yellow);//!
      arec.param("g", "").refParam("start", start).param("lookFor", lookFor).refVar("q", q).refVar("visited", visited).var("found", found).refVar("v",v).breakHere("Done with v.");//!
	}
}





}