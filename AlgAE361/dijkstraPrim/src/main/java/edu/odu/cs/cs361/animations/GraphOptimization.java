package edu.odu.cs.cs361.animations;//!

import java.awt.Color;//!
import java.util.ArrayList;//!
import java.util.Comparator;//!
import java.util.LinkedList;//!
import java.util.List;//!

import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!
import edu.odu.cs.cs361.animations.graphs.CppIterator;//!
import edu.odu.cs.cs361.animations.graphs.DiGraph;//!
import edu.odu.cs.cs361.animations.graphs.Edge;//!
import edu.odu.cs.cs361.animations.graphs.Labeled;
import edu.odu.cs.cs361.animations.graphs.Vertex;//!
import static edu.odu.cs.AlgAE.Server.LocalServer.activate;//!

public class GraphOptimization {//!


static int max (int x, int  y)//!
{//!
	return (x > y) ? x : y; //!
}//!
static int min (int x, int  y)//!
{//!
	return (x < y) ? x : y; //!
}//!

public static class VList<T extends Labeled> extends ArrayList<T> implements CanBeRendered<VList<T>>, Renderer<VList<T>> //!
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
	public Renderer<VList<T>> getRenderer() {//!
		return this;//!
	}//!

	@Override//!
	public Color getColor(VList<T> obj) {//!
		return color;//!
	}//!

	@Override//!
	public List<Component> getComponents(VList<T> obj) {//!
		LinkedList<Component> comp = new LinkedList<Component>();//!
		for (T v: this) {//!
			comp.add((new Component(v.getLabel())));//!
		}//!
		return comp;//!
	}//!

	@Override//!
	public List<Connection> getConnections(VList<T> obj) {//!
		return new LinkedList<Connection>();//!
	}//!

	@Override//!
	public int getMaxComponentsPerRow(VList<T> obj) {//!
		return 100;//!
	}//!

	@Override//!
	public String getValue(VList<T> obj) {//!
		return "";//!
	}//!

	public int count(T v) {//!
		for (T w: this) {
			if (v == w)
				return 1;
		}
		return 0;
	}
	
	public void addUnique (T x) {
		if (count(x) == 0)
			add(x);
	}
	
	public void make_heap (Comparator<T> comp)//!
	{//!
		make_heap (0, size(), comp);//!
	}//!

	public void make_heap (int first, //!
	                int last,//!
	                Comparator<T> comp)//!
	{
	  if (first != last) {//!
		  int i = first+(last-first-1)/2 + 1; //!
	      while (i != first) {//!
	    	  --i;
	    	  _percolateDown (first, last, comp, i-first, last-first);
	      }
	  }
	}
	
	private void _percolateDown 
    (int first, //!
     int last,//!
     Comparator<T> comp,//!
     int nodeToPerc,//!
     int heapSize)//!
	{//!
		while (2*nodeToPerc+1 < heapSize)//!
		{ 
			int child1 = 2*nodeToPerc +1;//!
			int child2 = child1+1;//!
			int largerChild = child1;//!
			if (child2 < heapSize 
					&& comp.compare (get(first + child2), get(first+child1)) > 0) {//!
				largerChild = child2;//!
			}//!
			if (comp.compare(get(first + largerChild), get(first + nodeToPerc)) > 0) {//!
				T temp = get(first + nodeToPerc);//!
				set(first + nodeToPerc, get(first + largerChild));//!
				set(first + largerChild, temp);//!
				nodeToPerc = largerChild;//!
			}//!
			else//!
			{//!
				nodeToPerc = heapSize;//!
			}//!
		}//!
	}//!
	
	public void pop()//!
	{//!
		remove(0);
	}//!
}//!

static class CompareByDistance implements Comparator<Vertex<DVData,DEData>> {//!

	@Override//!
	public int compare(Vertex<DVData, DEData> left, Vertex<DVData, DEData> right) {//!
		return right.get().distance - left.get().distance;//!
	}//!
	
}//!

static CompareByDistance compareByDist = new CompareByDistance(); 


//!struct VertexAndDistance {
//!	  Vertex v;
//!	  Vertex from;
//!	  int dist;

//!	  VertexAndDistance (Vertex vert, Vertex frm, double d)
//!	    : v(vert), from(frm), dist(d)  {}
//!	  VertexAndDistance ()  {}

//!	  bool operator< (const VertexAndDistance& right) const
//!	  {
//!	    return dist > right.dist;
//!	  }
//!	};



//!	typedef hash_map<Vertex, unsigned, VertexHash> VDistMap;

//!	struct CompareVerticesByDistance 
//!	{
//!	  VDistMap& dist;
//!	  
//!	  CompareVerticesByDistance (VDistMap& dm): dist(dm) {}

//!	bool operator() (Vertex left, Vertex right) const
//!	    {
//!	      return dist[left] > dist[right];
//!	    }
//!	};


static//!
    void findWeightedShortestPath (
	   DiGraph<DVData,DEData> g,//!	   DiGraph& g,
	   VList<Vertex<DVData,DEData>> path,//!	   list<Vertex>& path,
	   Vertex<DVData,DEData> start,//!	   Vertex start,
	   Vertex<DVData,DEData> finish)//!	   Vertex finish,
//!	   hash_map<Edge, int, EdgeHash>& weight)
	{      // Dijkstra's Algorithm
//!	  hash_map<Vertex, Vertex, VertexHash> cameFrom;
//!	  VDistMap dist;
      ActivationRecord arec = activate(GraphOptimization.class);//!
  	  arec.param("g", "").refParam("path", path).param("start",start.getLabel()).param("finish",finish.getLabel());//!
  	  arec.breakHere("Starting Dijkstra's algorithm");//!
	  for (CppIterator<Vertex<DVData, DEData>> vi = g.vbegin(); vi.notEnd(); vi.increment())//!	  for (AllVertices vi = g.vbegin(); vi != g.vend(); ++vi)
	    {
		  vi.at().set(new DVData());//!
	      vi.at().get().distance = 99999;//!	      dist[*vi] = INT_MAX;
	    }
  	  arec.breakHere("Set start's distance to zero");//!
	  start.get().distance = 0;//!	  dist[start] = 0;
  	  arec.breakHere("Put all nodes into the priority queue");//!
//!	  adjustable_priority_queue<Vertex, VDistMap, CompareVerticesByDistance>
//!	     pq (g.vbegin(), g.vend(), CompareVerticesByDistance(dist));
  	  VList<Vertex<DVData,DEData>> pq = new VList<Vertex<DVData,DEData>>();//!
	  for (CppIterator<Vertex<DVData, DEData>> vi = g.vbegin(); vi.notEnd(); vi.increment()) pq.add(vi.at());//!
	  pq.make_heap(compareByDist);//!

	  arec.var("pq",pq).breakHere("Initial priority queue");//!
	  // Compute distance from start to finish
	  arec.pushScope();//!
	  while (!pq.empty())
	    {
	      Vertex<DVData,DEData> v = pq.get(0);//!	      Vertex v = pq.top();
	      int d = v.get().distance;//!	      int d = dist[v];
	      v.get().color = 1;//!
		  arec.refVar("v", v).var("d", d).breakHere("Vertex v is a distance d from start");//!
	     
	      pq.pop();
		  pq.make_heap(compareByDist);//!
		  arec.pushScope();//!
	      for (CppIterator<Edge<DVData,DEData>> e = g.outbegin(v); e.notEnd(); e.increment())//!	      for (AllOutgoingEdges e = g.outbegin(v); e != g.outend(v); ++e)
	      {
	    	  Vertex<DVData,DEData> w = (e.at()).dest();//!	    	  Vertex w = (*e).dest();
	    	  w.get().color = 2;//!
	    	  arec.refVar("w",w).breakHere("Is this a shorter way to get to w?");//!
	    	  if (w.get().distance > d+e.at().get().weight)//!	    	  if (dist[w] > d+weight[*e])
	    	  { 
		    	  arec.breakHere("compute the vertex's new, shorter, distance");//!
		    	  w.get().distance = d + e.at().get().weight;//!		    	  dist[w] = d + weight[*e];
		    	  arec.breakHere(" Then adjust the priority queue");//!
		    	  pq.make_heap(compareByDist);//!		    	  pq.adjust (w);
		    	  arec.breakHere("Remember how we got to w");//!
		    	  w.get().cameFrom = v;//!		    	  cameFrom[w] = v;
	            }
	    	  w.get().color = -1;//!
	    	 }
	      arec.popScope();//!
    	  arec.breakHere("Done examining neighbors of v");//!
	      v.get().color = -1;//!
	    }
	  arec.popScope();
	  arec.breakHere("start extracting the path");//!
	  // Extract path
	  Vertex<DVData,DEData> v = finish;//!	  Vertex v = finish;
	  arec.refVar("v",v).breakHere("Work backwards from the finish node");//!
	  if (v.get().distance != 99999)//!	  if (dist[v] != INT_MAX)
	    {
	     while (!(v == start))
	       {
	    	 arec.highlight(v, Color.yellow);//!
	         path.add(0, v);  //!	         path.push_front(v);
	         arec.refVar("v",v).breakHere("Extracting path");//!
	         v = v.get().cameFrom;//!	         v = cameFrom[v];
	       }
         arec.refVar("v",v).breakHere("Finally, add the start node");//!
         arec.highlight(start, Color.yellow);//!
	     path.add(0, start);//!	     path.push_front(start);
	    }
	  arec.refVar("v",v).breakHere("Done");//!
	}


static//!
void findMinSpanTree (
		DiGraph<DVData,DEData> g,//!		DiGraph& g,
		VList<Edge<DVData,DEData>> spanTree,//!		set<Edge>& spanTree,
		Vertex<DVData,DEData> start)//!		Vertex start,
//!		hash_map<Edge, int, EdgeHash>& weight)
{      // Prim's Algorithm
//!	VDistMap dist;   /*{*/vDist = &dist;
//!	hash_map<Vertex, Edge, VertexHash> cameFrom;

	ActivationRecord arec = activate(GraphOptimization.class);//!
	arec.param("g", "").refParam("spanTree", spanTree).param("start",start.getLabel()).breakHere("Starting Prim's algorithm");//!
	for (CppIterator<Vertex<DVData, DEData>> vi = g.vbegin(); vi.notEnd(); vi.increment())//!	  for (AllVertices vi = g.vbegin(); vi != g.vend(); ++vi)
	{
		vi.at().set(new DVData());//!
		vi.at().get().distance = 99999;//!	      dist[*vi] = INT_MAX;
	}
	start.get().distance = 0;//	dist[start] = 0;
	arec.breakHere("Initial distances set - now create the priority queue");//!
//	CompareVerticesByDistance comp (dist);
//	adjustable_priority_queue<Vertex, VDistMap, CompareVerticesByDistance>
//	   pq (g.vbegin(), g.vend(), CompareVerticesByDistance(dist)); 
	  VList<Vertex<DVData,DEData>> pq = new VList<Vertex<DVData,DEData>>();//!
	  for (CppIterator<Vertex<DVData, DEData>> vi = g.vbegin(); vi.notEnd(); vi.increment()) pq.add(vi.at());//!
	  pq.make_heap(compareByDist);//!
	  arec.var("pq",pq).breakHere("Initial priority queue");//!
		  
	  arec.pushScope();//!
	  while (!pq.empty())
	  {
	      Vertex<DVData,DEData> v = pq.get(0);//!	      Vertex v = pq.top();
	      int d = v.get().distance;//!	      int d = dist[v];
	      v.get().color = 1;//!
		  arec.refVar("v", v).var("d", d).breakHere("Vertex v is closest remaining vertex");//!
		  pq.pop();
		  arec.pushScope();//!
		  for (CppIterator<Edge<DVData,DEData>> e = g.outbegin(v); e.notEnd(); e.increment())//!		  for (AllOutgoingEdges e = g.outbegin(v); e != g.outend(v); ++e)
		  {
			  Vertex<DVData,DEData> w = (e.at()).dest();//!			  Vertex w = (*e).dest();
			  w.get().color = 2;//!
			  arec.refVar("w", w).breakHere("Check the edge (v,w)");//!
			  if (w.get().distance > e.at().get().weight)//!			  if (dist[w] > weight[*e])
			  { 
				  arec.breakHere("compute the vertex's new, shorter, distance");//!
			      w.get().distance = e.at().get().weight;//!			      dist[w] = weight[*e];
				  arec.breakHere("Then adjust the priority queue");//!
				  pq.make_heap(compareByDist);//!				  pq.adjust (w);
				  arec.breakHere("updated priority queue");//!
			      w.get().ecameFrom = e.at();//!			      cameFrom[w] = *e;
			  }
			  w.get().color = 0;//!

		  }
		  arec.popScope();//!
		  v.get().color = 0;//!
	  }
	  arec.popScope();//!
	  arec.breakHere("Extract the selected edges");//!
//!	  for (hash_map<Vertex, Edge, VertexHash>::iterator p = cameFrom.begin();
//!	     p != cameFrom.end(); ++p)
	  arec.pushScope();//!
	  for (CppIterator<Vertex<DVData,DEData>> p = g.vbegin(); p.notEnd(); p.increment())//!
	  {
		  Vertex<DVData,DEData> v = p.at();//!
		  if (v.get().ecameFrom != null) {//!
			  arec.refVar("p", p.at()).breakHere("Add edge.");//!
		  spanTree.addUnique (v.get().ecameFrom);//!		  spanTree.insert ((*p).second);
		  }//!
	  }
	  arec.popScope();//!
	  arec.breakHere("Final span tree.");//!
}


}