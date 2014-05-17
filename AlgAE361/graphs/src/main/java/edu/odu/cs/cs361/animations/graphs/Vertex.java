package edu.odu.cs.cs361.animations.graphs;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!


public class Vertex<VertexData,EdgeData> implements Labeled, Cloneable, CanBeRendered<Vertex<VertexData,EdgeData>>, Renderer<Vertex<VertexData,EdgeData>>
{
	DiGraph<VertexData,EdgeData> graph;
	int vID;
	private String label;
	private Color color;
	private VertexData data;

	private static String labels = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	Vertex (DiGraph<VertexData,EdgeData> g, int id)
	{
		graph = (g); 
		vID = id;
		int offset = (id-1) % labels.length();
		label = labels.substring(offset, offset+1);
		color = Color.orange;
		data = null;
	}


	// To get a non-trivial vertex, you must ask a DiGraph to generate one.
	public Vertex()
	{
		graph = null; 
		vID = 0;
		label = "";
		color = Color.orange;
		data = null;
	}

	public int id() {return vID;}


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
		return vID;
	}

	@Override
	public boolean equals (Object vo)
	{
		Vertex<?,?> v = (Vertex<?,?>)vo;
		return vID == v.vID && graph == v.graph;
	}

	@Override
	public String toString() {
		return "(" + vID + ((label != null) ? ":" + label.toString() : "") + ")";
	}


	@Override
	public Renderer<Vertex<VertexData,EdgeData>> getRenderer() {
		return this;
	}


	@Override
	public Color getColor(Vertex<VertexData,EdgeData> obj) {
		return color;
	}

	public Color getColor() {
		return color;
	}
	public void setColor (Color c)
	{
		color = c;
	}


	@Override
	public List<Component> getComponents(Vertex<VertexData,EdgeData> obj) {
		LinkedList<Component> comp = new LinkedList<Component>();
		if (data != null)
			comp.add (new Component(data));
		return comp;
	}


	@Override
	public List<Connection> getConnections(Vertex<VertexData,EdgeData> obj) {
		LinkedList<Connection> conn = new LinkedList<Connection>(); 
		for (CppIterator<Edge<VertexData,EdgeData>> ei = graph.outbegin(this); ei.notEnd(); ei.increment()) {
			Edge<VertexData,EdgeData> e = ei.at();
			Vertex<VertexData,EdgeData> dest = e.dest();
			Object value = e.get();
			Connection c = new Connection(dest);
			if (vID < dest.vID)
				c.setColor(Color.blue.darker());
			else
				c.setColor(Color.green.darker());
			if (value != null && value.toString().length() > 0) {
				c.setValue(value.toString());
			}
			conn.add(c);
		}
		return conn;
	}


	@Override
	public int getMaxComponentsPerRow(Vertex<VertexData,EdgeData> obj) {
		return 1;
	}


	@Override
	public String getValue(Vertex<VertexData,EdgeData> obj) {
		return label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String s) {
		label = s;
	}


	/**
	 * @param data the data to set
	 */
	public void set(VertexData data) {
		this.data = data;
	}


	/**
	 * @return the data
	 */
	public VertexData get() {
		return data;
	}

}
