package edu.odu.cs.cs361.animations;


import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import edu.odu.cs.AlgAE.Animations.LocalJavaAnimation;
import edu.odu.cs.AlgAE.Server.MenuFunction;
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;


public class HeapDemo extends LocalJavaAnimation {

	public HeapDemo() {
		super("Heaps");
	}

	@Override
	public String about() {
		return "Demonstration of heaps and heapsort,\n" +
		"prepared for CS 361, \n" +
		"Advanced Data Structures and Algorithms,\n" +
		"Old Dominion University\n" +
				"Summer 2011";
	}

	boolean displayParentPointers = false;

	class heapnodeRendering implements Renderer<heaps.heapnode> {
		
		@Override
		public Color getColor(heaps.heapnode obj) {
			return Color.cyan;
		}

		@Override
		public List<Component> getComponents(heaps.heapnode obj) {
			List<Component> results = new LinkedList<Component>();
			return results;
		}
		
		@Override
		public List<Connection> getConnections(heaps.heapnode t) {
			LinkedList<Connection> results = new LinkedList<Connection>();
			int ileft = 2 * t.index + 1;
			int iright = 2 * t.index + 2;
			heaps.heapnode left = (ileft < t.heap.data.size()) ? t.heap.tree.get(ileft) : null;
			heaps.heapnode right = (iright < t.heap.data.size()) ? t.heap.tree.get(iright) : null;
			
			Connection leftC = new Connection(left, 215, 215);
			Connection rightC = new Connection(right, 145, 145);

			results.add (leftC);
			results.add (rightC);
			return results;
		}
		@Override
		public int getMaxComponentsPerRow(heaps.heapnode obj) {
			return 0;
		}
		
		@Override
		public String getValue(heaps.heapnode obj) {
			heaps.heapnode t = (heaps.heapnode)obj;
			int v = t.heap.data.get(t.index);
			return "" + v;
		}
			
	}
	
	
	class HeapRendering implements Renderer<heaps> {

		@Override
		public Color getColor(heaps obj) {
			return null;
		}

		@Override
		public List<Component> getComponents(heaps heap) {
			LinkedList<Component> comps = new LinkedList<Component>();
			for (int i = 0; i < heap.data.size(); ++i)
				comps.add (new Component(heap.data.get(i)));
			return comps;
		}

		@Override
		public List<Connection> getConnections(heaps heap) {
			LinkedList<Connection> conns =  new LinkedList<Connection>();
			conns.add (new Connection(heap.tree.get(0), 180, 180));
			return conns;
		}

		@Override
		public int getMaxComponentsPerRow(heaps obj) {
			return 100;
		}

		@Override
		public String getValue(heaps obj) {
			return "";
		}
		
	}



	
	public void createSampleHeap(heaps heap) {
		heap.resize(0);
		int[] data = {66, 58, 63, 55, 48, 60, 11, 14, 53, 47};
		for (int i = 0; i < data.length; ++i) {
			int k = data[i];
			heap.quickAdd(k);
		}
	}

	
	heaps heap = new heaps();
	Random rand = new Random();
	boolean isAHeap = false;
	
	
	@Override
	public void buildMenu() {
		
		registerStartingAction(new MenuFunction() {

			@Override
			public void selected() {
				globalVar("", heap);
				createSampleHeap(heap);
				isAHeap = true;
				getMemoryModel().render(heaps.heapnode.class, new heapnodeRendering());
				getMemoryModel().render(heaps.class, new HeapRendering());
			}
			
		});


		register("create a random array", new MenuFunction() {

			@Override
			public void selected() {
				String szS = promptForInput("How many nodes?", "[0-9]+");
				int sz = Integer.parseInt(szS);
				heap.resize(sz);
				isAHeap = (sz <= 1);
			}
		});
		
		register("clear", new MenuFunction() {

			@Override
			public void selected() {
				heap.resize(0);
				isAHeap = true;
			}
		});

		register("heapsort", new MenuFunction() {

			@Override
			public void selected() {
				heap.heapsort (0, heap.data.size(), null);
				isAHeap = false;
			}
			
		});

		register("build heap", new MenuFunction() {

			@Override
			public void selected() {
				heaps.build_heap(heap, null);
				isAHeap = true;
			}
			
		});

		register("add a value", new MenuFunction() {

			@Override
			public void selected() {
				if (isAHeap) {
					String xs = promptForInput("Comma-separated list of integers to insert:", "[0-9 ,]+");
					String[] vals = xs.split("[ ,]+");
					for (String x0: vals) {
						try { 
							int x = Integer.parseInt(x0);
							heaps.add_to_heap(heap, x, null);
						} catch (Exception e) {}
					}
				} else {
					promptForInput("You must build the heap, first.", ".*");
				}
			}
			
		});
		
		register("remove largest value", new MenuFunction() {

			@Override
			public void selected() {
				if (isAHeap) {
					heaps.remove_from_heap(heap, null);
				} else {
					promptForInput("You must build the heap, first.", ".*");
				}
			}
			
		});

	}
	

	
	
	
	public static void main (String[] args) {
		HeapDemo demo = new HeapDemo();
		demo.runAsMain();
	}

}
