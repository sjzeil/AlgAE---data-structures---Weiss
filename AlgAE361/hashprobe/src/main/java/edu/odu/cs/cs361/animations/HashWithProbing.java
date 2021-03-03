package edu.odu.cs.cs361.animations;


import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.odu.cs.AlgAE.Animations.LocalJavaAnimation;
import edu.odu.cs.AlgAE.Server.MenuFunction;
import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationStack;
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;
//!
//!
//!
//!

public class HashWithProbing extends LocalJavaAnimation {

	public class LPHashTableRendering implements Renderer<hash_set_LP<?>> {

		@Override
		public String getValue(hash_set_LP<?> obj) {
			return "";
		}

		@Override
		public Color getColor(hash_set_LP<?> obj) {
			return null;
		}

		@Override
		public List<Component> getComponents(hash_set_LP<?> ht) {
			LinkedList<Component> comps = new LinkedList<Component>();
			comps.add (new Component(ht.hSize, "hSize"));
			comps.add (new Component(ht.theSize, "theSize"));
			comps.add (new Component(ht.table));
			return comps;
		}

		@Override
		public List<Connection> getConnections(hash_set_LP<?> obj) {
			return new LinkedList<Connection>();
		}

		@Override
		public int getMaxComponentsPerRow(hash_set_LP<?> obj) {
			return 2;
		}

	}

	public class QPHashTableRendering implements Renderer<hash_set_QP<?>> {

		@Override
		public String getValue(hash_set_QP<?> obj) {
			return "";
		}

		@Override
		public Color getColor(hash_set_QP<?> obj) {
			return null;
		}

		@Override
		public List<Component> getComponents(hash_set_QP<?> ht) {
			LinkedList<Component> comps = new LinkedList<Component>();
			comps.add (new Component(ht.hSize, "hSize"));
			comps.add (new Component(ht.theSize, "theSize"));
			comps.add (new Component(ht.table));
			return comps;
		}

		@Override
		public List<Connection> getConnections(hash_set_QP<?> obj) {
			return new LinkedList<Connection>();
		}

		@Override
		public int getMaxComponentsPerRow(hash_set_QP<?> obj) {
			return 2;
		}

	}
	
	
	
	class TableRendering implements Renderer<ArrayList<?>> {

		@Override
		public Color getColor(ArrayList<?> obj) {
			return null;
		}

		@Override
		public List<Component> getComponents(ArrayList<?> a) {
			LinkedList<Component> comps = new LinkedList<Component>();
			for (int i = 0; i < a.size(); ++i)
				comps.add (new Component(a.get(i), "" + i));
			return comps;
		}

		@Override
		public List<Connection> getConnections(ArrayList<?> obj) {
			return new LinkedList<Connection>();
		}

		@Override
		public int getMaxComponentsPerRow(ArrayList<?> obj) {
			return 100;
		}

		@Override
		public String getValue(ArrayList<?> obj) {
			return "";
		}
		
	}

	public HashWithProbing() {
		super("hashing with probing");
		self = this;
	}

	@Override
	public String about() {
		return "Demonstration of Hashing with Linear and Quadratic\n" +
				"Probing, prepared for CS 361, Advanced Data Structures\n" +
				"and Algorithms, Old Dominion University\n" +
				"Summer 2011";
	}

	
	
	class SillyString implements Renderer<SillyString>, CanBeRendered<SillyString>, Comparable<SillyString> {
		String s;
		
		SillyString (String ss) {s = ss;}
		
		public int hashCode()
		{
			return s.length();
		}

		public String toString()
		{
			return s;
		}
		
		public boolean equals (Object obj)
		{
			SillyString ss = (SillyString)obj;
			return s.equals(ss.s);
		}

		@Override
		public Renderer<SillyString> getRenderer() {
			return this;
		}

		@Override
		public String getValue(SillyString obj) {
			return s;
		}

		@Override
		public Color getColor(SillyString obj) {
			return null;
		}

		@Override
		public List<Component> getComponents(SillyString obj) {
			return new LinkedList<Component>();
		}

		@Override
		public List<Connection> getConnections(SillyString obj) {
			return new LinkedList<Connection>();
		}

		@Override
		public int getMaxComponentsPerRow(SillyString obj) {
			return 1;
		}

		@Override
		public int compareTo(SillyString o) {
			return s.compareTo(o.s);
		}
	}

	LocalJavaAnimation self;
	
	hash_set_LP<SillyString> linear = new hash_set_LP<HashWithProbing.SillyString>();
	hash_set_QP<SillyString> quadratic = new hash_set_QP<HashWithProbing.SillyString>();

	class Twotables implements Renderer<Twotables>, CanBeRendered<Twotables> {

		@Override
		public Color getColor(Twotables obj) {
			return new Color(1.0f, 1.0f, 1.0f, 0.0f);
		}
		@Override
		public List<Component> getComponents(Twotables obj) {
			LinkedList<Component> comps = new LinkedList<Component>();
			comps.add (new Component(linear, "linear"));
			comps.add (new Component(quadratic, "quadratic"));
			return comps;
		}
		@Override
		public List<Connection> getConnections(Twotables obj) {
			return new LinkedList<Connection>();
		}
		@Override
		public int getMaxComponentsPerRow(Twotables obj) {
			return 1;
		}
		@Override
		public String getValue(Twotables obj) {
			return "";
		}
		@Override
		public Renderer<Twotables> getRenderer() {
			return this;
		}
		
	}
	
	
	
	@Override
	public void buildMenu() {
		
		
		registerStartingAction(new MenuFunction() {
			
			@Override
			public void selected() {
				generateInitialTable();
				globalVar("Tables", new Twotables());
				ActivationStack stk = getMemoryModel().getActivationStack();
				stk.render(hash_set_LP.class, new LPHashTableRendering());
				stk.render(hash_set_QP.class, new QPHashTableRendering());
				stk.render(ArrayList.class, new TableRendering());
			}
		});
		
		
		register ("insert", new MenuFunction() {
			@Override
			public void selected() {
				String valuesList = promptForInput("Comma-separated list of names to insert: ", ".+");
				String[] values = valuesList.split("[ ,]+");
				for (String v: values) {
					linear.insert(new SillyString(v));
					quadratic.insert(new SillyString(v));
				}
			}
		});


		register ("find", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Names to search for: ", ".+");
				out.println(value + " occurs in the table " +	linear.count(new SillyString(value)) + " times");
				out.println(value + " occurs in the table " +	quadratic.count(new SillyString(value)) + " times");
			}
		});

		register ("erase", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Name to remove: ", ".+");
				linear.erase(new SillyString(value));
				quadratic.erase(new SillyString(value));
			}
		});

		register ("clear", new MenuFunction() {
			@Override
			public void selected() {
				linear.clear ();
				quadratic.clear ();
			}
		});

	}
	
	private void generateInitialTable() {
		String valuesList = "Adams,Baker,Clark,Davies";
		String[] values = valuesList.split("[ ,]+");
		for (String v: values) {
			linear.quickInsert(new SillyString(v));
			quadratic.quickInsert(new SillyString(v));
		}
	}
	
	public static void main (String[] args) {
		HashWithProbing demo = new HashWithProbing();
		demo.runAsMain();
	}

}
