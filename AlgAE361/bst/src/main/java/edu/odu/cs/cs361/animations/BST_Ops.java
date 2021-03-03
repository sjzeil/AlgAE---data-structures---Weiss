package edu.odu.cs.cs361.animations;


import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import edu.odu.cs.AlgAE.Animations.LocalJavaAnimation;
import edu.odu.cs.AlgAE.Server.MenuFunction;
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!
import edu.odu.cs.AlgAE.Server.Utilities.SimpleReference;//!

public class BST_Ops extends LocalJavaAnimation {

	public BST_Ops() {
		
		super("Binary Search Trees");
	}

	@Override
	public String about() {
		return "Demonstration of binary search trees,\n" +
		"prepared for CS 361, \n" +
		"Advanced Data Structures and Algorithms,\n" +
		"Old Dominion University\n" +
				"Summer 2014";
	}

	boolean displayParentPointers = false;

	class BinaryNodeRendering implements Renderer<BinaryNode<Integer>> {
		
		@Override
		public Color getColor(BinaryNode<Integer> obj) {
			return Color.cyan;
		}

		@Override
		public List<Component> getComponents(BinaryNode<Integer> obj) {
			List<Component> results = new LinkedList<Component>();
			return results;
		}
		
		@Override
		public List<Connection> getConnections(BinaryNode<Integer> t) {
			LinkedList<Connection> results = new LinkedList<Connection>();
			//Connection parC = new Connection(t.parent, 340, 20);
			Connection leftC = new Connection(t.left, 215, 215);
			Connection rightC = new Connection(t.right, 145, 145);
			//if (displayParentPointers)
				//results.add (parC);
			results.add (leftC);
			results.add (rightC);
			return results;
		}
		@Override
		public int getMaxComponentsPerRow(BinaryNode<Integer> obj) {
			return 0;
		}
		
		@Override
		public String getValue(BinaryNode<Integer> t) {
			return "" + t.element;
		}
			
	}
	
	
	class BinaryTreeRendering implements Renderer<BinarySearchTree<Integer>> {

		@Override
		public Color getColor(BinarySearchTree<Integer> obj) {
			return null;
		}

		@Override
		public List<Component> getComponents(BinarySearchTree<Integer> bst) {
			LinkedList<Component> comps = new LinkedList<Component>();
			comps.add (new Component(new SimpleReference(bst.root, 140, 220), "root"));
			//comps.add (new Component(bst.treeSize, "treeSize"));
			return comps;
		}

		@Override
		public List<Connection> getConnections(BinarySearchTree<Integer> obj) {
			return new LinkedList<Connection>();
		}

		@Override
		public int getMaxComponentsPerRow(BinarySearchTree<Integer> obj) {
			return 2;
		}

		@Override
		public String getValue(BinarySearchTree<Integer> obj) {
			return "";
		}
		
	}



	public void quickInsert (BinaryNode<Integer> t, int element)
	{ 
		if (element < t.element) 
		{
			if (t.left != null)
			{
				quickInsert (t.left, element);
			}
			else
			{
				t.left = new BinaryNode<Integer>(element, null, null);
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
				t.right = new BinaryNode<Integer>(element, null, null);
			}
		} 
		
	}

	public void quickInsert (BinarySearchTree<Integer> tree, int element)
	{ 
		if (tree.root != null)
			quickInsert(tree.root, element);
		else
			tree.root = new BinaryNode<Integer>(element);
	}
	
	public void createSampleTree1(BinarySearchTree<Integer> bst) {
		bst.root = null;
		int[] data = {30, 20, 70, 10, 50, 40, 60};
		for (int k: data) {
			quickInsert (bst, k);
			
		}
		//bst.treeSize = data.length;
	}

	
	BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
	//BinarySearchTree<Integer>.iterator current = bst.quickEnd();
	Random rand = new Random();
	
	@Override
	public void buildMenu() {
		
		registerStartingAction(new MenuFunction() {

			@Override
			public void selected() {
				globalVar("tree", bst);
				//globalVar("current", current);
				createSampleTree1(bst);
				getMemoryModel().getActivationStack().render(BinaryNode.class, new BinaryNodeRendering());
				getMemoryModel().getActivationStack().render(BinarySearchTree.class, new BinaryTreeRendering());
			}
			
		});


		register("search", new MenuFunction() {

			@Override
			public void selected() {
				String xs = promptForInput("Value to search for:", "[0-9]+");
				try { 
					int x = Integer.parseInt(xs);
					bst.contains(x);
				} catch (Exception e) {}
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
					bst.remove(x);
				} catch (Exception e) {}
			}
			
		});
		
		
		register("clear tree", new MenuFunction() {

			@Override
			public void selected() {
				bst.root = null;
				//bst.treeSize = 0;
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
				//bst.treeSize = nNodes;
			}
		});
		
		
	}
	

	
	
	
	public static void main (String[] args) {
		BST_Ops demo = new BST_Ops();
		demo.runAsMain();
	}

}
