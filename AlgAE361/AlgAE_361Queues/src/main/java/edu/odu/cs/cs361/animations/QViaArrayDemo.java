package edu.odu.cs.cs361.animations;


import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import edu.odu.cs.cs361.animations.Queues.Queue_via_Array;
import edu.odu.cs.zeil.AlgAE.Animation;
import edu.odu.cs.zeil.AlgAE.Server.MenuFunction;
import edu.odu.cs.zeil.AlgAE.Snapshot.Component;
import edu.odu.cs.zeil.AlgAE.Snapshot.Connection;
import edu.odu.cs.zeil.AlgAE.Snapshot.Rendering.Renderer;
import edu.odu.cs.zeil.AlgAE.Utilities.Index;

public class QViaArrayDemo extends Animation {

	public class AQueueRenderer implements Renderer<Queue_via_Array> {

		@Override
		public Color getColor(Queue_via_Array obj) {
			return null;
		}

		@Override
		public List<Component> getComponents(Queue_via_Array q) {
			LinkedList<Component> comps = new LinkedList<Component>();
			comps.add (new Component(new Index(q.start, q.array), "start"));
			comps.add (new Component(new Index(q.stop, q.array), "stop"));
			comps.add (new Component(q.theSize, "theSize"));
			comps.add (new Component(q.array, "array"));
			return comps;
		}

		@Override
		public List<Connection> getConnections(Queue_via_Array obj) {
			return new LinkedList<Connection>();
		}

		@Override
		public int getMaxComponentsPerRow(Queue_via_Array obj) {
			return 3;
		}

		@Override
		public String getValue(Queue_via_Array obj) {
			return "";
		}

	}






	
	public QViaArrayDemo() {
		super("Queue Implementations", true);
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
				getActivationStack().render(Queue_via_Array.class, new AQueueRenderer());
				
				new Queues().arrayQDemo();
			}
			
		});
		
		
	}
	
	
	
	public static void main (String[] args) {
		QViaArrayDemo demo = new QViaArrayDemo();
		demo.runAsMain();
	}

}
