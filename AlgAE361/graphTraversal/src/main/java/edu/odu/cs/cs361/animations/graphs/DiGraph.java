package edu.odu.cs.cs361.animations.graphs;

import java.util.HashMap;

public class DiGraph<VertexData,EdgeData> implements Cloneable
{
	private HashMap<Integer, Vertex<VertexData,EdgeData>> vertices;
	private HashMap<Integer, Edge<VertexData,EdgeData>> edges;
	private HashMap<Integer, HashMap<Integer, Edge<VertexData,EdgeData>>> inEdges;
	private HashMap<Integer, HashMap<Integer, Edge<VertexData,EdgeData>>> outEdges;
	private int maxVertexID;
	private int maxEdgeID;
	
	public DiGraph()
	{
		vertices = new HashMap<Integer, Vertex<VertexData,EdgeData>>();
		edges = new HashMap<Integer, Edge<VertexData,EdgeData>>();
		inEdges = new HashMap<Integer, HashMap<Integer, Edge<VertexData,EdgeData>>>();
		outEdges = new HashMap<Integer, HashMap<Integer, Edge<VertexData,EdgeData>>>();
		maxVertexID = maxEdgeID = 0;
	}
	
	public Vertex<VertexData,EdgeData> addVertex()
	{
		int newID = maxVertexID + 1;
		++maxVertexID;
		Vertex<VertexData,EdgeData> v = new Vertex<VertexData,EdgeData>(this, newID);
		vertices.put (newID, v);
		inEdges.put(newID, new HashMap<Integer, Edge<VertexData,EdgeData>>());
		outEdges.put(newID, new HashMap<Integer, Edge<VertexData,EdgeData>>());
		return v;
	}
	
	Vertex<VertexData,EdgeData> getVertex(int id) {return vertices.get(id);}
	Edge<VertexData,EdgeData> getEdge(int id) {return edges.get(id);}
	
	public Edge<VertexData,EdgeData> getEdge (Vertex<VertexData,EdgeData> source, Vertex<VertexData,EdgeData> dest)
	{
		HashMap<Integer, Edge<VertexData,EdgeData>> out = outEdges.get(source.id());
		if (out == null) {
			out = new HashMap<Integer, Edge<VertexData,EdgeData>>();
			outEdges.put(source.id(), out);
		}
		Edge<VertexData,EdgeData> e = out.get(dest.id());
		return e;		
	}

	public void removeVertex(Vertex<VertexData,EdgeData> v)
	{
		if (vertices.get(v.id()) != null) {
			vertices.remove(v.id());
			HashMap<Integer, Edge<VertexData,EdgeData>> vIn = inEdges.get(v.id());
			if (vIn != null) {
				for (int eID: vIn.keySet()) {
					edges.remove(eID);
				}
				inEdges.remove(v.id());
			}
			HashMap<Integer, Edge<VertexData,EdgeData>> vOut = outEdges.get(v.id());
			if (vOut != null) {
				for (int eID: vOut.keySet()) {
					edges.remove(eID);
				}
				outEdges.remove(v.id());
			}
		}
	}

	public Edge<VertexData,EdgeData> addEdge(Vertex<VertexData,EdgeData> source, Vertex<VertexData,EdgeData> dest)
	{	
		HashMap<Integer, Edge<VertexData,EdgeData>> out = outEdges.get(source.id());
		if (out == null) {
			out = new HashMap<Integer, Edge<VertexData,EdgeData>>();
			outEdges.put(source.id(), out);
		}
		Edge<VertexData,EdgeData> e = out.get(dest.id());
		if (e == null) {
			++maxEdgeID;
			e = new Edge<VertexData,EdgeData>(this, maxEdgeID, source.id(), dest.id());
			edges.put(e.id(), e);
			HashMap<Integer, Edge<VertexData,EdgeData>> in = inEdges.get(dest.id());
			if (in == null) {
				in = new HashMap<Integer, Edge<VertexData,EdgeData>>();
				inEdges.put(dest.id(), in);
			}
			in.put(source.id(), e);
			out.put(dest.id(), e);
		}
		return e;
	}
  
  public void removeEdge (Edge<VertexData,EdgeData> e)
  {
	  if (edges.get(e.id()) != null) {
		  HashMap<Integer, Edge<VertexData,EdgeData>> out = outEdges.get(e.sourceID);
		  out.remove(e.destID);
		  HashMap<Integer, Edge<VertexData,EdgeData>> in = inEdges.get(e.destID);
		  in.remove(e.sourceID);
		  edges.remove(e.id());
	  }
  }


  public int numVertices()   {return vertices.size();}
  public int numEdges() {return edges.size();}


  public int indegree (Vertex<VertexData,EdgeData> v) 
  {
	  HashMap<Integer, Edge<VertexData,EdgeData>> in = inEdges.get(v.id());
	  return (in != null) ? in.size() : 0;
  }
  
  public int outdegree (Vertex<VertexData,EdgeData> v) {
	  HashMap<Integer, Edge<VertexData,EdgeData>> out = outEdges.get(v.id());
	  return (out != null) ? out.size() : 0;  
  }



  public boolean isAdjacent(Vertex<VertexData,EdgeData> v, Vertex<VertexData,EdgeData> w)
  {
	  HashMap<Integer, Edge<VertexData,EdgeData>> out = outEdges.get(v.id());
	  if (out != null)
		  return out.get(w.id()) != null;
	  else
		  return false;
  }

  public void clear()
  {
	  vertices.clear();
	  edges.clear();
	  inEdges.clear();
	  outEdges.clear();
  }

  
  // iterators
  
  public CppIterator<Vertex<VertexData, EdgeData>> vbegin()
  {
	  return new MapValueIterator<Integer, Vertex<VertexData, EdgeData>>(vertices);
  }
  
  public CppIterator<Vertex<VertexData, EdgeData>> vend()
  {
	  return new MapValueIterator<Integer, Vertex<VertexData, EdgeData>>(vertices, true);	  
  }

  public CppIterator<Edge<VertexData,EdgeData>> ebegin()
  {
	  return new MapValueIterator<Integer, Edge<VertexData,EdgeData>>(edges);	  
  }
  
  public CppIterator<Edge<VertexData,EdgeData>> eend()
  {
	  return new MapValueIterator<Integer, Edge<VertexData,EdgeData>>(edges).end();	  	  
  }

  public CppIterator<Edge<VertexData,EdgeData>> outbegin(Vertex<VertexData,EdgeData> source)
  {
	  HashMap<Integer, Edge<VertexData,EdgeData>> out = outEdges.get(source.id());
	  return new MapValueIterator<Integer, Edge<VertexData,EdgeData>>(out);
  }
  
  public CppIterator<Edge<VertexData,EdgeData>> outend(Vertex<VertexData,EdgeData> source) 
  {
	  HashMap<Integer, Edge<VertexData,EdgeData>> out = outEdges.get(source.id());
	  return new MapValueIterator<Integer, Edge<VertexData,EdgeData>>(out).end();	  
  }

  public CppIterator<Edge<VertexData,EdgeData>> inbegin(Vertex<VertexData,EdgeData> dest)
  {
	  HashMap<Integer, Edge<VertexData,EdgeData>> in = inEdges.get(dest.id());
	  return new MapValueIterator<Integer, Edge<VertexData,EdgeData>>(in);
  }
  
  public CppIterator<Edge<VertexData,EdgeData>> inend(Vertex<VertexData,EdgeData> dest) 
  {
	  HashMap<Integer, Edge<VertexData,EdgeData>> in = inEdges.get(dest.id());
	  return new MapValueIterator<Integer, Edge<VertexData,EdgeData>>(in);	  
  }

  
//  private class MapKeyIterator<T,U> implements CppIterator<T> {
//	  private HashMap<T, U> theMap;
//	  private T[] theKeys;
//	  private int current;
//
//	  @SuppressWarnings("unchecked")
//	  public MapKeyIterator(HashMap<T, U> map) 
//	  {
//		  theMap = map;
//		  theKeys = (T[]) map.keySet().toArray();
//		  current = 0; 
//	  }
//
//	  @SuppressWarnings("unchecked")
//	  public MapKeyIterator(HashMap<T, U> map, boolean atEnd) 
//	  {
//		  theMap = map;
//		  theKeys = (T[]) map.keySet().toArray();
//		  current = (atEnd) ? theKeys.length : 0; 
//	  }
//
//	  @Override
//	  public T at() {
//		  try {
//			  return theKeys[current];
//		  } catch (ArrayIndexOutOfBoundsException ex) {
//			  return null;
//		  }
//	  }
//
//	  @Override
//	  public void increment() {
//		  ++current;
//	  }
//
//	  @SuppressWarnings("unchecked")
//	  public boolean Equals (Object obj)
//	  {
//		  MapKeyIterator<T,U> other = (MapKeyIterator<T,U>)obj;
//		  return theMap == other.theMap && current == other.current;
//	  }
//
//	  @Override
//	  public Object clone()
//	  {
//		  try {
//			  return super.clone();
//		  } catch (CloneNotSupportedException e) {
//			  e.printStackTrace();
//			  return null;
//		  }
//	  }
//
//	  @Override
//	  public boolean notEnd() {
//		  return current < theKeys.length;
//	  }
//
//  }
  
  
  private class MapValueIterator<T,U> implements CppIterator<U> {
	  private HashMap<T, U> theMap;
	  private U[] theValues;
	  private int current;

	@SuppressWarnings("unchecked")
	public MapValueIterator(HashMap<T, U> map) 
	  {
		  theMap = map;
		  theValues = (U[]) map.values().toArray();
		  current = 0; 
	  }

	@SuppressWarnings("unchecked")
	public MapValueIterator(HashMap<T, U> map, boolean atEnd) 
	  {
		  theMap = map;
		  theValues = (U[]) map.values().toArray();
		  current = (atEnd) ? theValues.length : 0; 
	  }

	public MapValueIterator<T,U> end() 
	  {
		  current = theValues.length; 
		  return this;
	  }

	  @Override
	  public U at() {
		  try {
			  return theValues[current];
		  } catch (ArrayIndexOutOfBoundsException ex) {
			  return null;
		  }
	  }

	  @Override
	  public void increment() {
		  ++current;
	  }

		@Override
		public boolean notEnd() {
			return current < theValues.length;
		}

		@SuppressWarnings("unchecked")
	  public boolean equals (Object obj)
	  {
		  MapValueIterator<T,U> other = (MapValueIterator<T,U>)obj;
		  return theMap == other.theMap && current == other.current;
	  }
	  
	  @Override
	  public Object clone()
	  {
		  try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	  }

  }
  

}


