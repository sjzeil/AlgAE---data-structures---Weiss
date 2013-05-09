package edu.odu.cs.cs361.animations;


import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import edu.odu.cs.cs361.animations.Stacks.Stack_via_List;
import edu.odu.cs.cs361.animations.Stacks.Stack_via_Vector;
import edu.odu.cs.zeil.AlgAE.Animation;
import edu.odu.cs.zeil.AlgAE.AnimationContext;
import edu.odu.cs.zeil.AlgAE.Server.MenuFunction;
import edu.odu.cs.zeil.AlgAE.Snapshot.Component;
import edu.odu.cs.zeil.AlgAE.Snapshot.Connection;
import edu.odu.cs.zeil.AlgAE.Snapshot.Rendering.LinkedListRenderer;
import edu.odu.cs.zeil.AlgAE.Snapshot.Rendering.Renderer;
import edu.odu.cs.zeil.AlgAE.Utilities.SimpleReference;

public class StackDemo extends Animation {

	public class VStackRenderer implements Renderer<Stack_via_Vector> {

		@Override
		public Color getColor(Stack_via_Vector obj) {
			return null;
		}

		@Override
		public List<Component> getComponents(Stack_via_Vector stk) {
			LinkedList<Component> comps = new LinkedList<Component>();
			comps.add (new Component(stk.v, "v"));
			return comps;
		}

		@Override
		public List<Connection> getConnections(Stack_via_Vector obj) {
			return new LinkedList<Connection>();
		}

		@Override
		public int getMaxComponentsPerRow(Stack_via_Vector obj) {
			return 1;
		}

		@Override
		public String getValue(Stack_via_Vector obj) {
			return "";
		}

	}



	public class LStackRenderer implements Renderer<Stack_via_List> {

		@Override
		public Color getColor(Stack_via_List obj) {
			return null;
		}

		@Override
		public List<Component> getComponents(Stack_via_List stk) {
			LinkedList<Component> comps = new LinkedList<Component>();
			comps.add (new Component(new SimpleReference(stk.first,140.0, 180.0), "first"));
			return comps;
		}

		@Override
		public List<Connection> getConnections(Stack_via_List obj) {
			return new LinkedList<Connection>();
		}

		@Override
		public int getMaxComponentsPerRow(Stack_via_List obj) {
			return 1;
		}

		@Override
		public String getValue(Stack_via_List obj) {
			return "";
		}

	}



	private AnimationContext self;
	
	public StackDemo() {
		super("Stack Implementations", true);
		self = this;
	}

	@Override
	public String about() {
		return "Demonstration of stack implementation,\n" +
				"prepared for CS 361, Advanced Data Structures and Algorithms\n" +
				"Summer 2011";
	}

	
	
	@Override
	public void buildMenu() {
		
		
		
		registerStartingAction(new MenuFunction() {

			@Override
			public void selected() {
				getAnimator().setSpeed(30);
				getActivationStack().render(LinkedList.class, new LinkedListRenderer<String>(true, false, self));
				getActivationStack().render(Stack_via_Vector.class, new VStackRenderer());
				getActivationStack().render(Stack_via_List.class, new LStackRenderer());
				
				new Stacks().demo();
			}
			
		});
		
		
	}
	
	
	
	public static void main (String[] args) {
		StackDemo demo = new StackDemo();
		demo.runAsMain();
	}

}
