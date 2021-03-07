package edu.odu.cs.cs361.animations;


import edu.odu.cs.AlgAE.Animations.LocalJavaAnimation;
import edu.odu.cs.AlgAE.Server.MenuFunction;
import edu.odu.cs.AlgAE.Server.Utilities.ArrayList;
import edu.odu.cs.AlgAE.Server.Utilities.DiscreteInteger;


public class CS361SortingAnimation extends LocalJavaAnimation {

	public CS361SortingAnimation() {
		super("Sorting Algorithms");
	}

	@Override
	public String about() {
		return "Demonstration of Sorting Algorithms,\n" +
				"prepared for CS 361, Data Structures\n" +
				"and Algorithms, Old Dominion University"
				+ "ty\n" +
				"Summer 2010";
	}

	
	private ArrayList<DiscreteInteger> array = new ArrayList<>();

	
	
	@Override
	public void buildMenu() {
		
		
		registerStartingAction(new MenuFunction() {
			
			@Override
			public void selected() {
				generateRandomArray(12);
				globalVar("v", array);
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
				new Sorting().insertionSort (array, array.size());
			}
		});
/*
		register ("Shell Sort", new MenuFunction() {
			@Override
			public void selected() {
				new Sorting().shellSort (array, array.size());
			}
		});
*/

		register ("Merge Sort", new MenuFunction() {
			@Override
			public void selected() {
				new Sorting().mergeSort (array, array.size());
			}
		});

		register ("Quick Sort", new MenuFunction() {
			@Override
			public void selected() {
				new Sorting().quicksort (array, array.size());
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
		array.clear();
		for (int i = 0; i < n; ++i) {
			array.add(new DiscreteInteger((int)((double)(2*n) * Math.random())));
		}
		
	}

	
	public void reverseArrayGenerated()
	{
		String value = promptForInput("How many elements?", "\\d+");
		int n = Integer.parseInt(value);
		generateReverseArray(n);
	}

	public void generateReverseArray(int n)
	{
		array.clear();
		for (int i = 0; i < n; ++i) {
			array.add(null);
		}
		array.set(n-1, new DiscreteInteger ((int)(3.0 * Math.random())));  
		for (int i = n-2; i >= 0; --i) {
			array.set(i, new DiscreteInteger(array.get(i+1).get() + (int)(3.0 * Math.random())));
		}
		
	}

	
	
	
	public static void main (String[] args) {
		CS361SortingAnimation demo = new CS361SortingAnimation();
		demo.runAsMain();
	}

}
