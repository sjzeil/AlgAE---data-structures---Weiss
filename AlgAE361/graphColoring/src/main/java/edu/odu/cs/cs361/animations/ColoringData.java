package edu.odu.cs.cs361.animations;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!


public class ColoringData implements CanBeRendered<ColoringData>, Renderer<ColoringData> {
	Color colorList[] = {Color.yellow, Color.blue, Color.red,
		       Color.green, Color.cyan, Color.magenta,
		       Color.PINK, Color.black};
	
	
	int color;
	int degree;
	int constraints;
	boolean abbreviated;
	
	ColoringData() {
		degree = constraints = 0;
		color = -1;
		abbreviated = false;
	}

	ColoringData(boolean abbrv) {
		degree = constraints = 0;
		color = 0;
		abbreviated = abbrv;
	}

	@Override
	public String getValue(ColoringData obj) {
		if (abbreviated)
			return "   ";
		else
			return "";
	}

	@Override
	public Color getColor(ColoringData obj) {
		if (color < 0)
			return Color.lightGray;
		else 
			return colorList[color % colorList.length];
	}

	@Override
	public List<Component> getComponents(ColoringData obj) {
		LinkedList<Component> comp = new LinkedList<Component>();
		if (!abbreviated) {
			comp.add (new Component(degree, "deg"));
			comp.add (new Component(constraints, "cns"));
			//comp.add (new Component(color, "color"));
		}
		return comp;
	}

	@Override
	public List<Connection> getConnections(ColoringData obj) {
		return new LinkedList<Connection>();
	}

	@Override
	public int getMaxComponentsPerRow(ColoringData obj) {
		return 0;
	}

	@Override
	public Renderer<ColoringData> getRenderer() {
		return this;
	}

}
