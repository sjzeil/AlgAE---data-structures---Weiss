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

public class TreeTraversals extends LocalJavaAnimation {

	public TreeTraversals() {
		super("Tree Traversals");
	}

	@Override
	public String about() {
		return "Demonstration of tree traversals,\n" +
				"prepared for CS 361, \n" +
				"Advanced Data Structures and Algorithms,\n" +
				"Old Dominion University\n" +
				"Summer 2011";
	}


	class tnodeRendering implements Renderer<tnode<String>> {
		
		@Override
		public Color getColor(tnode<String> obj) {
			return Color.cyan;
		}

		@Override
		public List<Component> getComponents(tnode<String> obj) {
			List<Component> results = new LinkedList<Component>();
			return results;
		}
		
		@Override
		public List<Connection> getConnections(tnode<String> t) {
			LinkedList<Connection> results = new LinkedList<Connection>();
			Connection leftC = new Connection(t.left, 215, 215);
			Connection rightC = new Connection(t.right, 145, 145);
			results.add (leftC);
			results.add (rightC);
			return results;
		}
		@Override
		public int getMaxComponentsPerRow(tnode<String> obj) {
			return 0;
		}
		
		@Override
		public String getValue(tnode<String> t) {
			return "" + t.nodeValue;
		}
			
	}
	
	
	class BinaryTreeRendering implements Renderer<BinaryTrees> {

		@Override
		public Color getColor(BinaryTrees obj) {
			return null;
		}

		@Override
		public List<Component> getComponents(BinaryTrees obj) {
			return new LinkedList<Component>();
		}

		@Override
		public List<Connection> getConnections(BinaryTrees bt) {
			LinkedList<Connection> conn = new LinkedList<Connection>();
			conn.add(new Connection(bt.root, 165, 195));
			return conn;
		}

		@Override
		public int getMaxComponentsPerRow(BinaryTrees obj) {
			return 1;
		}

		@Override
		public String getValue(BinaryTrees obj) {
			return "";
		}
		
	}

	
	
	
	public void quickInsert (tnode<String> t, String element)
	{ 
		int comp = element.compareTo(t.nodeValue);
		if (comp < 0) 
		{
			if (t.left != null)
			{
				quickInsert (t.left, element);
			}
			else
			{
				t.left = new tnode<String>(element);
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
				t.right = new tnode<String>(element);
			}
		} 
	}

	public void quickInsert (BinaryTrees tree, String element)
	{ 
		if (tree.root != null)
			quickInsert(tree.root, element);
		else
			tree.root = new tnode<String>(element);
	}

		

	BinaryTrees bt = new BinaryTrees();
	Random rand = new Random();
	String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	@Override
	public void buildMenu() {
		
		registerStartingAction(new MenuFunction() {

			@Override
			public void selected() {
				globalVar("root", bt);
				bt.root = createSampleTree1();
				getMemoryModel().render(tnode.class, new tnodeRendering());
				getMemoryModel().render(BinaryTrees.class, new BinaryTreeRendering());
			}
			
		});
		

		register("pre-order traversal", new MenuFunction() {

			@Override
			public void selected() {
				bt.preorderOutput(bt.root, " ");
				out.println("");
			}
			
		});
		
		register("post-order traversal", new MenuFunction() {

			@Override
			public void selected() {
				bt.postorderOutput(bt.root, " ");
				out.println("");
			}
			
		});
		
		register("in-order traversal", new MenuFunction() {

			@Override
			public void selected() {
				bt.inorderOutput(bt.root, " ");
				out.println("");
			}
			
		});

		register("level-order traversal", new MenuFunction() {

			@Override
			public void selected() {
				bt.levelorderOutput(bt.root, " ");
				out.println("");
			}
			
		});

		register("create a random tree", new MenuFunction() {

			@Override
			public void selected() {
				bt.root = null;
				String nNodesS = promptForInput("How many nodes?", "[0-9]+");
				int nNodes = Integer.parseInt(nNodesS);
				for (int i = 0; i < nNodes; ++i) {
					int k = rand.nextInt(26);
					String c = chars.substring(k, k+1);
					quickInsert (bt, c);
				}
			}
		});

	}
	

	public tnode<String> createSampleTree1()//!
	{
		tnode<String> c13 = new tnode<String>("13", null, null);//!
		tnode<String> a = new tnode<String>("a", null, null);//!
		tnode<String> x = new tnode<String>("X", null, null);//!
		tnode<String> c1 = new tnode<String>("1", null, null);//!
		tnode<String> oplus = new tnode<String>("+", c13, a);//!
		tnode<String> ominus = new tnode<String>("-", x, c1);//!
		tnode<String> otimes = new tnode<String>("*", oplus, ominus);//!
		return otimes;
	}
	
	
	public static void main (String[] args) {
		TreeTraversals demo = new TreeTraversals();
		demo.runAsMain();
	}

}
