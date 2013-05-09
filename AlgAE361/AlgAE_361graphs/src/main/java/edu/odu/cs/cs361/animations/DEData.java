package edu.odu.cs.cs361.animations;

import java.awt.Color;

public class DEData {
	public static Color colorList[] = {Color.yellow, Color.blue, Color.red,
		       Color.green, Color.cyan, Color.magenta,
		       Color.PINK, Color.black};
	
	
	int color;
	int weight;
	
	DEData() {
		weight = -1;
		color = -1;
	}

	public String getValue() {
		return "" + weight;
	}

	public Color getColor() {
		if (color < 0)
			return Color.lightGray;
		else 
			return colorList[color % colorList.length];
	}
	
	public String toString()
	{
		return "" + weight;
	}


}
