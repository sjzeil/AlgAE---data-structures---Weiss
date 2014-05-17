package edu.odu.cs.cs361.animations;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.odu.cs.AlgAE.Server.MenuFunction;
import edu.odu.cs.AlgAE.Server.Animations.LocalJavaAnimation;
import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;
import edu.odu.cs.AlgAE.Server.Utilities.DiscreteInteger;


public class CS361SortingAnimation extends LocalJavaAnimation {

	public CS361SortingAnimation() {
		super("Sorting Algorithms");
	}

	@Override
	public String about() {
		return "Demonstration of Sorting Algorithms,\n" +
				"prepared for CS 361, Advanced Data Strcutures\n" +
				"and Algorithms, Old Dominion University\n" +
				"Summer 2010";
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


	
	
	@Override
	public void buildMenu() {
		
		
		registerStartingAction(new MenuFunction() {
			
			@Override
			public void selected() {
				generateRandomArray(8);
				globalVar("vector", new ArrayContainer());
				/*
				getActivationStack().render(ArrayList.class, 
						new Renderer<ArrayList<?>>() {
							@Override
							public Color getColor(ArrayList<?> obj) {
								return null;
							}

							@Override
							public List<Component> getComponents(ArrayList<?> obj) {
								return null;
							}

							@Override
							public List<Connection> getConnections(ArrayList<?> obj) {
								return null;
							}

							@Override
							public int getMaxComponentsPerRow(ArrayList<?> obj) {
								return 100;
							}

							@Override
							public String getValue(ArrayList<?> obj) {
								return null;
							}
					
				});*/
			}
		});
		
		register ("Generate a random vector", new MenuFunction() {
			@Override
			public void selected() {
				randomArrayGenerated();
			}
		});

		register ("Generate a reversed vector", new MenuFunction() {
			@Override
			public void selected() {
				reverseArrayGenerated();
			}
		});
		
		register ("Insertion Sort", new MenuFunction() {
			@Override
			public void selected() {
				new Sorting().insertionSort (array, array.length);
			}
		});

		register ("Shell Sort", new MenuFunction() {
			@Override
			public void selected() {
				new Sorting().shellSort (array, array.length);
			}
		});

		register ("Merge Sort", new MenuFunction() {
			@Override
			public void selected() {
				new Sorting().mergeSort (array, 0, array.length);
			}
		});

		register ("Quick Sort", new MenuFunction() {
			@Override
			public void selected() {
				new Sorting().quicksort (array, 0, array.length);
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
		if (n != array.length) {
			array = new DiscreteInteger[n];
		}
		for (int i = 0; i < n; ++i) {
			array[i] = new DiscreteInteger((int)((double)(2*n) * Math.random()));
		}
		
	}

	
	public void reverseArrayGenerated()
	{
		String value = promptForInput("How many elements?", "\\d+");
		int n = Integer.parseInt(value);
		generateReverseArray(n);
		
		ActivationRecord arec = activate(this);//!
		arec.var("array", array).breakHere("generated");//!
	}

	public void generateReverseArray(int n)
	{
		if (n != array.length) {
			array = new DiscreteInteger[n];
		}
		array[n-1] = new DiscreteInteger ((int)(3.0 * Math.random()));  
		for (int i = n-2; i >= 0; --i) {
			array[i] = new DiscreteInteger(array[i+1].get() + (int)(3.0 * Math.random()));
		}
		
	}

	
	
	
	public static void main (String[] args) {
		CS361SortingAnimation demo = new CS361SortingAnimation();
		demo.runAsMain();
	}

}
