package edu.odu.cs.cs361.animations;


import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import edu.odu.cs.cs361.animations.Queues.Queue_via_List;
import edu.odu.cs.zeil.AlgAE.Animation;
import edu.odu.cs.zeil.AlgAE.AnimationContext;
import edu.odu.cs.zeil.AlgAE.Server.MenuFunction;
import edu.odu.cs.zeil.AlgAE.Snapshot.Component;
import edu.odu.cs.zeil.AlgAE.Snapshot.Connection;
import edu.odu.cs.zeil.AlgAE.Snapshot.Rendering.LinkedListRenderer;
import edu.odu.cs.zeil.AlgAE.Snapshot.Rendering.Renderer;
import edu.odu.cs.zeil.AlgAE.Utilities.SimpleReference;

public class QViaListDemo extends Animation {


	public class LQueueRenderer implements Renderer<Queue_via_List> {

		@Override
		public Color getColor(Queue_via_List obj) {
			return null;
		}

		@Override
		public List<Component> getComponents(Queue_via_List q) {
			LinkedList<Component> comps = new LinkedList<Component>();
			comps.add (new Component(new SimpleReference(q.first,140.0, 180.0), "first"));
			comps.add (new Component(new SimpleReference(q.last,140.0, 180.0), "last"));
			return comps;
		}

		@Override
		public List<Connection> getConnections(Queue_via_List obj) {
			return new LinkedList<Connection>();
		}

		@Override
		public int getMaxComponentsPerRow(Queue_via_List obj) {
			return 2;
		}

		@Override
		public String getValue(Queue_via_List obj) {
			return "";
		}

	}



	private AnimationContext self;
	
	public QViaListDemo() {
		super("Queue Implementations", true);
		self = this;
	}

	@Override
	public String about() {
		return "Demonstration of queue implementation,\n" +
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
				getActivationStack().render(Queue_via_List.class, new LQueueRenderer());
				
				new Queues().listQDemo();
			}
			
		});
		
		
	}
	
	
	
	public static void main (String[] args) {
		QViaListDemo demo = new QViaListDemo();
		demo.runAsMain();
	}

}
