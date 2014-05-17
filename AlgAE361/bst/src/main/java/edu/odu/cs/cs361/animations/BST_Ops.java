package edu.odu.cs.cs361.animations;


import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import edu.odu.cs.zeil.AlgAE.Animation;
import edu.odu.cs.zeil.AlgAE.Server.MenuFunction;
import edu.odu.cs.zeil.AlgAE.Snapshot.Component;
import edu.odu.cs.zeil.AlgAE.Snapshot.Connection;
import edu.odu.cs.zeil.AlgAE.Snapshot.Rendering.Renderer;
import edu.odu.cs.zeil.AlgAE.Utilities.SimpleReference;

public class BST_Ops extends Animation {

	public BST_Ops() {
		super("Binary Search Trees", true);
	}

	@Override
	public String about() {
		return "Demonstration of binary search trees,\n" +
		"prepared for CS 361, \n" +
		"Advanced Data Structures and Algorithms,\n" +
		"Old Dominion University\n" +
				"Summer 2011";
	}

	boolean displayParentPointers = false;

	class stnodeRendering implements Renderer<stnode<Integer>> {
		
		@Override
		public Color getColor(stnode<Integer> obj) {
			return Color.cyan;
		}

		@Override
		public List<Component> getComponents(stnode<Integer> obj) {
			List<Component> results = new LinkedList<Component>();
			return results;
		}
		
		@Override
		public List<Connection> getConnections(stnode<Integer> t) {
			LinkedList<Connection> results = new LinkedList<Connection>();
			Connection parC = new Connection(t.parent, 340, 20);
			Connection leftC = new Connection(t.left, 215, 215);
			Connection rightC = new Connection(t.right, 145, 145);
			if (displayParentPointers)
				results.add (parC);
			results.add (leftC);
			results.add (rightC);
			return results;
		}
		@Override
		public int getMaxComponentsPerRow(stnode<Integer> obj) {
			return 0;
		}
		
		@Override
		public String getValue(stnode<Integer> t) {
			return "" + t.nodeValue;
		}
			
	}
	
	
	class BinaryTreeRendering implements Renderer<stree<Integer>> {

		@Override
		public Color getColor(stree<Integer> obj) {
			return null;
		}

		@Override
		public List<Component> getComponents(stree<Integer> bst) {
			LinkedList<Component> comps = new LinkedList<Component>();
			comps.add (new Component(new SimpleReference(bst.root, 140, 220), "root"));
			comps.add (new Component(bst.treeSize, "treeSize"));
			return comps;
		}

		@Override
		public List<Connection> getConnections(stree<Integer> obj) {
			return new LinkedList<Connection>();
		}

		@Override
		public int getMaxComponentsPerRow(stree<Integer> obj) {
			return 2;
		}

		@Override
		public String getValue(stree<Integer> obj) {
			return "";
		}
		
	}



	public void quickInsert (stnode<Integer> t, int element)
	{ 
		if (element < t.nodeValue) 
		{
			if (t.left != null)
			{
				quickInsert (t.left, element);
			}
			else
			{
				t.left = new stnode<Integer>(element, null, null, t);
			}
		} 
		else
		{
			if (t.right != null)
			{
				quickInsert (t.right, element);
			}
			else
			{
				t.right = new stnode<Integer>(element, null, null, t);
			}
		} 
	}

	public void quickInsert (stree<Integer> tree, int element)
	{ 
		if (tree.root != null)
			quickInsert(tree.root, element);
		else
			tree.root = new stnode<Integer>(element);
	}
	
	public void createSampleTree1(stree<Integer> bst) {
		bst.root = null;
		int[] data = {30, 20, 70, 10, 50, 40, 60};
		for (int k: data) {
			quickInsert (bst, k);
		}
		bst.treeSize = data.length;
	}

	
	stree<Integer> bst = new stree<Integer>();
	stree<Integer>.iterator current = bst.quickEnd();
	Random rand = new Random();
	
	@Override
	public void buildMenu() {
		
		registerStartingAction(new MenuFunction() {

			@Override
			public void selected() {
				globalVar("tree", bst);
				globalVar("current", current);
				createSampleTree1(bst);
				getActivationStack().render(stnode.class, new stnodeRendering());
				getActivationStack().render(stree.class, new BinaryTreeRendering());
			}
			
		});

		register("Toggle display of parent pointers", new MenuFunction() {

			@Override
			public void selected() {
				displayParentPointers = !displayParentPointers;
			}
		});

		
		register("current = begin()", new MenuFunction() {

			@Override
			public void selected() {
				current.nodePtr = bst.begin().nodePtr;
			}
			
		});

		register("current = end()", new MenuFunction() {

			@Override
			public void selected() {
				current.nodePtr = bst.end().nodePtr;
			}
			
		});

		register("++current", new MenuFunction() {

			@Override
			public void selected() {
				current.increment();
			}
			
		});

		register("current = find(...)", new MenuFunction() {

			@Override
			public void selected() {
				String xs = promptForInput("Integer value to search for?", "[0-9]+");
				int x = Integer.parseInt(xs);
				current.nodePtr = bst.find(x).nodePtr;
			}
			
		});

		register("create a random tree", new MenuFunction() {

			@Override
			public void selected() {
				bst.root = null;
				String nNodesS = promptForInput("How many nodes?", "[0-9]+");
				int nNodes = Integer.parseInt(nNodesS);
				int[] v = new int[nNodes];
				int k = 0;
				for (int i = 0; i < nNodes; ++i) {
					k += 1 + rand.nextInt(3);
					v[i] = k;
				}
				for (int i = 1; i < nNodes; ++i) {
					int j = rand.nextInt(i+1);
					int temp = v[i];
					v[i] = v[j];
					v[j] = temp;
				}
				for (int i = 0; i < nNodes; ++i) {
					quickInsert (bst, v[i]);
				}
				bst.treeSize = nNodes;
			}
		});
		
		
		register("insert value", new MenuFunction() {

			@Override
			public void selected() {
				String xs = promptForInput("Comma-separated list of integers to insert:", "[0-9 ,]+");
				String[] vals = xs.split("[ ,]+");
				for (String x0: vals) {
					try { 
						int x = Integer.parseInt(x0);
						bst.insert(x);
					} catch (Exception e) {}
				}
			}
			
		});
		
		register("remove value", new MenuFunction() {

			@Override
			public void selected() {
				String xs = promptForInput("Integer to remove:", "[0-9]+");
				try { 
					int x = Integer.parseInt(xs);
					bst.erase (x);
				} catch (Exception e) {}
			}
			
		});
		
		register("clear tree", new MenuFunction() {

			@Override
			public void selected() {
				bst.root = null;
				bst.treeSize = 0;
			}
			
		});

	}
	

	
	
	
	public static void main (String[] args) {
		BST_Ops demo = new BST_Ops();
		demo.runAsMain();
	}

}
