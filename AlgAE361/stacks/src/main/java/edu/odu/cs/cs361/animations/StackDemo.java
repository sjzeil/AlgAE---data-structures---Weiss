package edu.odu.cs.cs361.animations;


import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import edu.odu.cs.AlgAE.Animations.LocalJavaAnimation;
import edu.odu.cs.AlgAE.Server.MenuFunction;
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;
import edu.odu.cs.AlgAE.Server.Utilities.SimpleReference;

public class StackDemo extends LocalJavaAnimation {


	public class StackRenderer implements Renderer<Stack> {

		@Override
		public Color getColor(Stack obj) {
			return null;
		}

		@Override
		public List<Component> getComponents(Stack stk) {
			LinkedList<Component> comps = new LinkedList<Component>();
			stk.firstR.set(stk.first);
			comps.add (new Component(stk.firstR, "first"));
			return comps;
		}

		@Override
		public List<Connection> getConnections(Stack obj) {
			return new LinkedList<Connection>();
		}

		@Override
		public int getMaxComponentsPerRow(Stack obj) {
			return 1;
		}

		@Override
		public String getValue(Stack obj) {
			return "";
		}

	}



	private LocalJavaAnimation self;
	
	public StackDemo() {
		super("Stack Implementations");
		self = this;
	}

	@Override
	public String about() {
		return "Demonstration of stack implementation,\n" +
				"prepared for CS 361, Advanced Data Structures and Algorithms\n" +
				"Summer 2014";
	}

	
	
	@Override
	public void buildMenu() {
		
		
		
		registerStartingAction(new MenuFunction() {

			@Override
			public void selected() {
				//getAnimator().setSpeed(30);
				getMemoryModel().render(Stack.class, new StackRenderer());
				
				new Stack().demo(self);
			}
			
		});
		
		
	}
	
	
	
	public static void main (String[] args) {
		StackDemo demo = new StackDemo();
		demo.runAsMain();
	}

}
