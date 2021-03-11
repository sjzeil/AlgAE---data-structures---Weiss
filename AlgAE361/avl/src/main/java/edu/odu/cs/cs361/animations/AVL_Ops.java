package edu.odu.cs.cs361.animations;


import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import edu.odu.cs.AlgAE.Server.MenuFunction;
import edu.odu.cs.AlgAE.Animations.LocalJavaAnimation;
import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!
import edu.odu.cs.AlgAE.Server.Utilities.SimpleReference;//!

public class AVL_Ops extends LocalJavaAnimation {

	public AVL_Ops() {
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

	class avlNodeRendering implements Renderer<avlNode<Integer>> {
		
		@Override
		public Color getColor(avlNode<Integer> obj) {
			return Color.cyan;
		}

		@Override
		public List<Component> getComponents(avlNode<Integer> t) {
			List<Component> results = new LinkedList<Component>();
			return results;
		}
		
		@Override
		public List<Connection> getConnections(avlNode<Integer> t) {
			LinkedList<Connection> results = new LinkedList<Connection>();
			//Connection parC = new Connection(t.parent, 340, 20);
			Connection leftC = new Connection(t.leftChild, 215, 215);
			Connection rightC = new Connection(t.rightChild, 145, 145);
			//if (displayParentPointers)
				//results.add (parC);
			results.add (leftC);
			results.add (rightC);
			return results;
		}
		@Override
		public int getMaxComponentsPerRow(avlNode<Integer> obj) {
			return 1;
		}
		
		@Override
		public String getValue(avlNode<Integer> t) {
			return "" + t.value + " (bf:" + t.balanceFactor + ")";
		}
			
	}
	
	
	class BinaryTreeRendering implements Renderer<AVLtree<Integer>> {

		@Override
		public Color getColor(AVLtree<Integer> obj) {
			return null;
		}

		@Override
		public List<Component> getComponents(AVLtree<Integer> bst) {
			LinkedList<Component> comps = new LinkedList<Component>();
			//comps.add (new Component(new SimpleReference(bst.root, 140, 220), "root"));
			bst.rootRef.set(bst.root);
			comps.add(new Component(bst.rootRef, "root"));
			//comps.add (new Component(bst.treeSize, "treeSize"));
			return comps;
		}

		@Override
		public List<Connection> getConnections(AVLtree<Integer> obj) {
			return new LinkedList<Connection>();
		}

		@Override
		public int getMaxComponentsPerRow(AVLtree<Integer> obj) {
			return 2;
		}

		@Override
		public String getValue(AVLtree<Integer> obj) {
			return "";
		}
		
	}




	public void quickInsert (AVLtree<Integer> tree, int element)
	{ 
		if (tree.root != null)
			tree.root = tree.root.quickinsert(element);
		else
			tree.root = new avlNode<Integer>(element);
	}
	
	public void createSampleTree1(AVLtree<Integer> bst) {
		bst.root = null;
		int[] data = {30, 20, 70, 10, 50, 40, 60};
		for (int k: data) {
			quickInsert (bst, k);
			
		}
		//bst.treeSize = data.length;
	}

	
	AVLtree<Integer> bst = new AVLtree<Integer>();
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
				getMemoryModel().getActivationStack().render(avlNode.class, new avlNodeRendering());
				getMemoryModel().getActivationStack().render(AVLtree.class, new BinaryTreeRendering());
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

	}
	

	
	
	
	public static void main (String[] args) {
		AVL_Ops demo = new AVL_Ops();
		demo.runAsMain();
	}

}
