package edu.odu.cs.cs361.animations;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!


public class TopoData implements CanBeRendered<TopoData>, Renderer<TopoData> {
	Color colorList[] = {Color.yellow, Color.blue, Color.red,
		       Color.green, Color.cyan, Color.magenta,
		       Color.PINK, Color.black};
	
	
	int color;
	int degree;
	
	TopoData() {
		degree = -1;
		color = -1;
	}

	TopoData(boolean abbrv) {
		degree = 0;
		color = 0;
	}

	@Override
	public String getValue(TopoData obj) {
		return "";
	}

	@Override
	public Color getColor(TopoData obj) {
		if (color < 0)
			return Color.lightGray;
		else 
			return colorList[color % colorList.length];
	}

	@Override
	public List<Component> getComponents(TopoData obj) {
		LinkedList<Component> comp = new LinkedList<Component>();
		if (degree >= 0)
			comp.add (new Component(degree, "deg"));
		return comp;
	}

	@Override
	public List<Connection> getConnections(TopoData obj) {
		return new LinkedList<Connection>();
	}

	@Override
	public int getMaxComponentsPerRow(TopoData obj) {
		return 0;
	}

	@Override
	public Renderer<TopoData> getRenderer() {
		return this;
	}

}
