package edu.odu.cs.cs361.animations;



import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.odu.cs.AlgAE.Server.MenuFunction;
import edu.odu.cs.AlgAE.Server.Animations.LocalJavaAnimation;
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;
import edu.odu.cs.AlgAE.Server.Utilities.DiscreteInteger;
import edu.odu.cs.AlgAE.Server.Utilities.Index;




public class CS361ArraysAnimation extends LocalJavaAnimation {

	public CS361ArraysAnimation() {
		super("Array Operations");
	}

	@Override
	public String about() {
		return "Demonstration of Array Manipulation Algorithms,\n" +
		"prepared for CS 361, Advanced Data Structures\n" +
		"and Algorithms, Old Dominion University\n" +
		"Summer 2014";
	}
	
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

	private String[] array = new String[8];
	private DiscreteInteger size = new DiscreteInteger(0);

	
	
	@Override
	public void buildMenu() {
		
		registerStartingAction(new MenuFunction() {

			@Override
			public void selected() {
				array[0] = "Adams";
				array[1] = "Baker";
				array[2] = "Clarke";
				for (int i = 3; i < array.length; ++i)
					array[i] = "";
				size.set(3);
				globalVar("array", array);
				globalVar("size", size);
			}
			
		});
		
		
		register ("reset array", new MenuFunction() {
			@Override
			public void selected() {
				array[0] = "Adams";
				array[1] = "Baker";
				array[2] = "Clarke";
				for (int i = 3; i < array.length; ++i)
					array[i] = "";
				size.set(3);
			}
		});

		register ("add in order (version 1)", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to add:", ".+");
				new ArrayOperations().addInOrder(array, size, value);
			}
		});


		register ("sequential search", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to search for:", ".+");
				new ArrayOperations().seqSearch(array, size.get(), value);
			}
		});

		register ("sequential ordered search", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to search for:", ".+");
				new ArrayOperations().seqOrderedSearch(array, size.get(), value);
			}
		});
		
		
		register ("sequential ordered search", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to search for:", ".+");
				new ArrayOperations().binarySearch(array, size.get(), value);
			}
		});
		
		register ("remove Element", new MenuFunction() {
			@Override
			public void selected() {
				String indexStr = promptForInput("Position from which to remove (0.." + (size.get()-1) + "):", "\\d+");
				int indexVal = Integer.parseInt(indexStr);
				Index index = new Index(indexVal, array);
				new ArrayOperations().removeElement(array, size, index);
			}
		});
	}

	
	
	
	public static void main (String[] args) {
		CS361ArraysAnimation demo = new CS361ArraysAnimation();
		demo.runAsMain();
	}
}
