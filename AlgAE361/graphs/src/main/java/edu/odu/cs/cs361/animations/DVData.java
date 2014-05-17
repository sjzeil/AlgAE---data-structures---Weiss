package edu.odu.cs.cs361.animations;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import edu.odu.cs.cs361.animations.graphs.Edge;
import edu.odu.cs.cs361.animations.graphs.Vertex;
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!


public class DVData implements CanBeRendered<DVData>, Renderer<DVData> {
	Color colorList[] = {Color.yellow, Color.blue, Color.red,
		       Color.green, Color.cyan, Color.magenta,
		       Color.PINK, Color.black};
	
	
	int color;
	Vertex<DVData,DEData> cameFrom;
	Edge<DVData,DEData> ecameFrom;
	int distance;
	
	DVData() {
		cameFrom = null;
		ecameFrom = null;
		distance = 99999;
		color = -1;
	}

	DVData(boolean abbrv) {
		cameFrom = null;
		ecameFrom = null;
		distance = -1;
		color = -1;
	}

	@Override
	public String getValue(DVData obj) {
		return "";
	}

	@Override
	public Color getColor(DVData obj) {
		if (color < 0)
			return Color.lightGray;
		else 
			return colorList[color % colorList.length];
	}

	@Override
	public List<Component> getComponents(DVData obj) {
		LinkedList<Component> comp = new LinkedList<Component>();
		if (distance >= 0)
			comp.add (new Component(distance, "d"));
		if (cameFrom != null)
			comp.add (new Component(cameFrom.getLabel(), "from"));
		if (ecameFrom != null)
			comp.add (new Component(ecameFrom.getLabel(), "from"));
		return comp;
	}

	@Override
	public List<Connection> getConnections(DVData obj) {
		return new LinkedList<Connection>();
	}

	@Override
	public int getMaxComponentsPerRow(DVData obj) {
		return 1;
	}

	@Override
	public Renderer<DVData> getRenderer() {
		return this;
	}

}
