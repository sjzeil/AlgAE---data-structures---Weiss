package edu.odu.cs.cs361.animations;



import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.odu.cs.AlgAE.Server.MenuFunction;
import edu.odu.cs.AlgAE.Server.Animations.LocalJavaAnimation;
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;
import edu.odu.cs.AlgAE.Server.Utilities.SimpleReference;


public class VectorAnimationNew extends LocalJavaAnimation {

	public VectorAnimationNew() {
		super("Array Operations");
	}

	@Override
	public String about() {
		return "Demonstration of Vector Manipulation Algorithms,\n" +
		"prepared for CS 361, Advanced Data Structures\n" +
		"and Algorithms, Old Dominion University\n" +
		"Summer 2011";
	}

	private VectorNew a = new VectorNew(0);
	private VectorNew b = new VectorNew(0);

	
	
	
	@Override
	public void buildMenu() {
		
		
		
		registerStartingAction(new MenuFunction() {
			
			@Override
			public void selected() {
				getMemoryModel().getActivationStack().render(VectorNew.class, new VectorRenderer());
				generateRandomVector(3);
				globalVar("a", a);
				globalVar("b", b);
			}
		});
		
		register ("Generate a vector", new MenuFunction() {
			@Override
			public void selected() {
				randomVectorGenerated();
			}
		});

				
		register ("a.push_back(...)", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Value to add:", "[0-9]+");
				try {
					Integer v = Integer.parseInt(value);
					a.push_back (v.intValue());
				} catch (Exception e) {
					// do nothing
				}
			}
		});

		register ("a.reserve(...)", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Reserve size for this many elements:", "[0-9]+");
				try {
					Integer v = Integer.parseInt(value);
					a.reserve (v.intValue());
				} catch (Exception e) {
					// do nothing
				}
			}
		});

		register ("a.reset(...)", new MenuFunction() {
			@Override
			public void selected() {
				String sz = promptForInput("Reset size to this many elements:", "[0-9]+");
				//String value = promptForInput("Value to fill in to new elements:", "[0-9]+");
				try {
					Integer sizeV = Integer.parseInt(sz);
					//Integer v = Integer.parseInt(value);
					a.resize (sizeV.intValue());
				} catch (Exception e) {
					// do nothing
				}
			}
		});
/*
		register ("a.clear()", new MenuFunction() {
			@Override
			public void selected() {
				a.clear();
			}
		});

*/
		register ("b = a; // operator=", new MenuFunction() {
			@Override
			public void selected() {
					b.assign (a);
			}
		});

		register ("vector<int> c = a; // copy constructor", new MenuFunction() {
			@Override
			public void selected() {
				VectorUtilityNew.copy(a);
			}
		});

	}

	
	public void randomVectorGenerated()
	{
		String value = promptForInput("How many elements?", "\\d+");
		int n = Integer.parseInt(value);
		generateRandomVector(n);
	}

	public void generateRandomVector(int n)
	{
		a.quick_clear();
		for (int i = 0; i < n; ++i)
			a.quick_push_back ((int)(25.0*Math.random()));
	}

	
	
	public static void main (String[] args) {
		VectorAnimationNew demo = new VectorAnimationNew();
		demo.runAsMain();
	}
	
	
	private class VectorRenderer implements Renderer<VectorNew> {

		@Override
		public Color getColor(VectorNew obj) {
			return null;
		}

		@Override
		public List<Component> getComponents(VectorNew v) {
			List<Component> components = new ArrayList<Component>();
			Component c1 = new Component(v.theCapacity, "theCapacity");
			Component c2 = new Component(v.theSize, "theSize");
			SimpleReference ar = new SimpleReference(v.objects, 90, 90);
			Component c3 = new Component(ar, "objects");
			components.add(c1);
			components.add(c2);
			components.add(c3);
			return components;
		}

		@Override
		public List<Connection> getConnections(VectorNew obj) {
			return new ArrayList<Connection>();
		}

		@Override
		public int getMaxComponentsPerRow(VectorNew obj) {
			return 1;
		}

		@Override
		public String getValue(VectorNew obj) {
			return "";
		}
	}


}
