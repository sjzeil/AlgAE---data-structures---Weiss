package edu.odu.cs.cs361.animations;



import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.odu.cs.zeil.AlgAE.Animation;
import edu.odu.cs.zeil.AlgAE.Server.MenuFunction;
import edu.odu.cs.zeil.AlgAE.Snapshot.Component;
import edu.odu.cs.zeil.AlgAE.Snapshot.Connection;
import edu.odu.cs.zeil.AlgAE.Snapshot.Rendering.CanBeRendered;
import edu.odu.cs.zeil.AlgAE.Snapshot.Rendering.Renderer;
import edu.odu.cs.zeil.AlgAE.Utilities.DiscreteInteger;

public class CS361ArraysAnimation extends Animation {

	public CS361ArraysAnimation() {
		super("Array Operations", true);
	}

	@Override
	public String about() {
		return "Demonstration of Array Manipulation Algorithms,\n" +
		"prepared for CS 361, Advanced Data Structures\n" +
		"and Algorithms, Old Dominion University\n" +
		"Summer 2011";
	}

	private DiscreteInteger[] array = new DiscreteInteger[0];
	
	private class ArrayContainer implements CanBeRendered<ArrayContainer>, Renderer<ArrayContainer> {

		@Override
		public Renderer<ArrayContainer> getRenderer() {
			return this;
		}

		@Override
		public Color getColor(ArrayContainer obj) {
			return Color.white;
		}

		@Override
		public List<Component> getComponents(ArrayContainer obj) {
			ArrayList<Component> c = new ArrayList<Component>();
			c.add (new Component(array));
			return c;
		}

		@Override
		public List<Connection> getConnections(ArrayContainer obj) {
			return new ArrayList<Connection>();
		}

		@Override
		public int getMaxComponentsPerRow(ArrayContainer obj) {
			return 1;
		}

		@Override
		public String getValue(ArrayContainer obj) {
			return "";
		}
		
	}

	private int size = 0;

	
	
	@Override
	public void buildMenu() {
		
		registerStartingAction(new MenuFunction() {
			
			@Override
			public void selected() {
				generateRandomArray(8);
				globalVar("array", new ArrayContainer());
			}
		});
		
		register ("Generate an array", new MenuFunction() {
			@Override
			public void selected() {
				randomArrayGenerated();
			}
		});

				
		register ("insert in order (version 1)", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to add:", "[0-9]+");
				try {
					Integer v = Integer.parseInt(value);
					new ArrayOperations().orderedInsert(array, 0, size, v);
				} catch (Exception e) {
					// do nothing
				}
			}
		});


		register ("sequential search", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to search for:", "[0-9]+");
				try {
					Integer v = Integer.parseInt(value);
					int k = new ArrayOperations().seqSearch(array, 0, size, v);
					out.println ("seqSearch returned " + k);
				} catch (Exception e) {
					// do nothing
				}
			}
		});

		register ("binary search", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to search for:", "[0-9]+");
				try {
					Integer v = Integer.parseInt(value);
					int k = new ArrayOperations().binSearch(array, 0, size, v);
					out.println ("binSearch returned " + k);
				} catch (Exception e) {
					// do nothing
				}
			}
		});

	}

	
	public void randomArrayGenerated()
	{
		String value = promptForInput("How many elements?", "\\d+");
		int n = Integer.parseInt(value);
		generateRandomArray(n);
	}

	public void generateRandomArray(int n)
	{
		int extraSlots = 4;
		if (n != array.length) {
			array = new DiscreteInteger[n+extraSlots];
		}
		if (n > 0) {
			array[0] = new DiscreteInteger((int)(5.0 * Math.random()));
		}
		for (int i = 1; i < n; ++i) {
			array[i] = new DiscreteInteger(array[i-1].get() + ((int)(5.0 * Math.random())));
		}
		for (int i = n; i < n+extraSlots; ++i) {
			array[i] = new DiscreteInteger(-1);
		}
		size= n;
	}

	
	
	public static void main (String[] args) {
		CS361ArraysAnimation demo = new CS361ArraysAnimation();
		demo.runAsMain();
	}

}
