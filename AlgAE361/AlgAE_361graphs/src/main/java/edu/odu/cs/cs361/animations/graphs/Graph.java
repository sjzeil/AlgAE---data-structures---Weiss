package edu.odu.cs.cs361.animations.graphs;

public class Graph<VertexData,EdgeData> extends DiGraph<VertexData,EdgeData>
{
	public Graph()
	{
		super();
	}
	

	public Edge<VertexData,EdgeData> addEdge(Vertex<VertexData,EdgeData> source, Vertex<VertexData,EdgeData> dest)
	{
		if (source.id() != dest.id())
			super.addEdge(dest, source);
		return super.addEdge(source, dest);
	}


	public void removeEdge(Edge<VertexData,EdgeData> e)
	{
		super.removeEdge(e);
		@SuppressWarnings("unchecked")
		Edge<VertexData,EdgeData> reversed = (Edge<VertexData,EdgeData>)e.clone();
		reversed.sourceID = e.destID;
		reversed.destID = e.sourceID;
		super.removeEdge (reversed);
	}
}






