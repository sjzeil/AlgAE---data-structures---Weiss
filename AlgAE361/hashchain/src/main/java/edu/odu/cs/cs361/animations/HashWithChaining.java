package edu.odu.cs.cs361.animations;


import java.awt.Color;
import java.util.List;

import edu.odu.cs.AlgAE.Animations.LocalJavaAnimation;
import edu.odu.cs.AlgAE.Server.MenuFunction;
import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationStack;
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;
import edu.odu.cs.AlgAE.Server.Utilities.LinkedList;

public class HashWithChaining extends LocalJavaAnimation {

	public class HashTableRendering implements Renderer<hash<?>> {

		@Override
		public String getValue(hash<?> obj) {
			return "";
		}

		@Override
		public Color getColor(hash<?> obj) {
			return null;
		}

		@Override
		public List<Component> getComponents(hash<?> ht) {
			LinkedList<Component> comps = new LinkedList<Component>();
			comps.add (new Component(ht.numBuckets, "numBuckets"));
			comps.add (new Component(ht.hashtableSize, "hashtableSize"));
			comps.add (new Component(ht.bucket, "bucket"));
			return comps;
		}

		@Override
		public List<Connection> getConnections(hash<?> obj) {
			return new LinkedList<Connection>();
		}

		@Override
		public int getMaxComponentsPerRow(hash<?> obj) {
			return 1;
		}

	}

	public HashWithChaining() {
		super("hashing with Separate Chaining");
		self = this;
	}

	@Override
	public String about() {
		return "Demonstration of Hashing with Separate Chaining,\n" +
				"prepared for CS 361, Advanced Data Structures\n" +
				"and Algorithms, Old Dominion University\n" +
				"Summer 2011, based on code from Chapter 12 of\n" +
				"Ford & Topp, Data Structures in C++ wit STL";
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

	hash<SillyString> table = new hash<HashWithChaining.SillyString>(6);
	LocalJavaAnimation self;
	
	
	@Override
	public void buildMenu() {
		
		
		registerStartingAction(new MenuFunction() {
			
			@Override
			public void selected() {
				generateInitialTable();
				globalVar("table", table);
				ActivationStack stk = getMemoryModel().getActivationStack();
				stk.render(hash.class, new HashTableRendering());
			}
		});
		
		
		register ("add", new MenuFunction() {
			@Override
			public void selected() {
				String valuesList = promptForInput("Comma-separated list of names to insert: ", ".+");
				String[] values = valuesList.split("[ ,]+");
				for (String v: values) {
					table.insert(new SillyString(v));
				}
			}
		});


		register ("find", new MenuFunction() {
			@Override
			public void selected() {
				String value = promptForInput("Names to search for: ", ".+");
					table.find(new SillyString(value));
			}
		});

		register ("clear", new MenuFunction() {
			@Override
			public void selected() {
				table.clear ();
			}
		});

	}
	
	private void generateInitialTable() {
		String valuesList = "Adams,Baker,Chen,Doe,Evans,Franklin,Gerhardt,Hamilton,Iverson,Jacoby,Kendricks";
		String[] values = valuesList.split("[ ,]+");
		for (String v: values) {
			table.quickInsert(new SillyString(v));
		}
	}
	
	public static void main (String[] args) {
		HashWithChaining demo = new HashWithChaining();
		demo.runAsMain();
	}

}
