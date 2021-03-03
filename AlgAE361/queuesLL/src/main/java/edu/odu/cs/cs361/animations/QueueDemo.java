package edu.odu.cs.cs361.animations;


import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import edu.odu.cs.cs361.animations.Queue;
import edu.odu.cs.AlgAE.Server.MenuFunction;
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;
import edu.odu.cs.AlgAE.Server.Rendering.LinkedListRenderer;
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;
import edu.odu.cs.AlgAE.Server.Utilities.SimpleReference;
import edu.odu.cs.AlgAE.Animations.LocalJavaAnimation;


public class QueueDemo extends LocalJavaAnimation {


	public class LQueueRenderer implements Renderer<Queue> {

		@Override
		public Color getColor(Queue obj) {
			return null;
		}

		@Override
		public List<Component> getComponents(Queue q) {
			LinkedList<Component> comps = new LinkedList<Component>();
			comps.add (new Component(new SimpleReference(q.first,140.0, 180.0), "first"));
			comps.add (new Component(new SimpleReference(q.last,140.0, 180.0), "last"));
			return comps;
		}

		@Override
		public List<Connection> getConnections(Queue obj) {
			return new LinkedList<Connection>();
		}

		@Override
		public int getMaxComponentsPerRow(Queue obj) {
			return 2;
		}

		@Override
		public String getValue(Queue obj) {
			return "";
		}

	}



	private LocalJavaAnimation self;
	
	public QueueDemo() {
		super("Queue Implementations");
		self = this;
	}

	@Override
	public String about() {
		return "Demonstration of queue implementation,\n" +
				"prepared for CS 361, Advanced Data Structures and Algorithms\n" +
				"Summer 2014";
	}

	
	
	@Override
	public void buildMenu() {
		
		
		
		registerStartingAction(new MenuFunction() {

			@Override
			public void selected() {
				//getAnimator().setSpeed(30);
				getMemoryModel().render(LinkedList.class, new LinkedListRenderer<String>(true, false, getContext()));
				getMemoryModel().render(Queue.class, new LQueueRenderer());
				
				new Queue().listQDemo(self);
			}
			
		});
		
		
	}
	
	
	
	public static void main (String[] args) {
		QueueDemo demo = new QueueDemo();
		demo.runAsMain();
	}

}
