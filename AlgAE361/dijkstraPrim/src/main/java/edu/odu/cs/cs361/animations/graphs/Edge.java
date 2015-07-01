package edu.odu.cs.cs361.animations.graphs;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!

public class Edge<VertexData,EdgeData> implements Labeled, Cloneable, CanBeRendered<Edge<VertexData,EdgeData>>, Renderer<Edge<VertexData,EdgeData>>
{
	int sourceID, destID;
	DiGraph<VertexData,EdgeData> graph;
	int eID;
	EdgeData data;

	public Edge (DiGraph<VertexData,EdgeData> g, int theID, int source, int dest)
	{
		graph = g; 
		eID = (theID);
		this.sourceID = (source);
		this.destID = (dest);
		data = null;
	}

	public Edge() 
	{
		sourceID = destID = eID = 0;
		graph = null;
		data = null;
	}


	public Vertex<VertexData,EdgeData> source() 
	{
		if (graph != null)
			return graph.getVertex(sourceID);
		else
			return null;
	}

	public Vertex<VertexData,EdgeData> dest()
	{
		if (graph != null)
			return graph.getVertex(destID);
		else
			return null;
	}

	public int id()  {return eID;}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int hashCode()
	{
		return eID;
	}

	@Override
	public boolean equals (Object eo)
	{
		Edge<?,?> e = (Edge<?,?>)eo;
		return eID == e.eID && graph == e.graph;
	}

	@Override
	public String toString() {
		return "(" + eID + ":" + sourceID + "=>" + destID + ((data != null) ? ":" + data.toString() : "") + ")";
	}
	
	public EdgeData get() {
		return data;
	}
	
	public void set(EdgeData ob)
	{
		data = ob;
	}

	@Override
	public String getLabel() {
		return "(" + graph.getVertex(sourceID).getLabel() + "=>" + graph.getVertex(destID).getLabel() + ")";
	}

	@Override
	public String getValue(Edge<VertexData, EdgeData> obj) {
		return getLabel();
	}

	@Override
	public Color getColor(Edge<VertexData, EdgeData> obj) {
		return null;
	}

	@Override
	public List<Component> getComponents(Edge<VertexData, EdgeData> obj) {
		return new LinkedList<Component>();
	}

	@Override
	public List<Connection> getConnections(Edge<VertexData, EdgeData> obj) {
		return new LinkedList<Connection>();
	}

	@Override
	public int getMaxComponentsPerRow(Edge<VertexData, EdgeData> obj) {
		return 1;
	}

	@Override
	public Renderer<Edge<VertexData, EdgeData>> getRenderer() {
		return this;
	}

}