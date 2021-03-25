package edu.odu.cs.cs361.animations;


import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import edu.odu.cs.cs361.animations.Queue;
import edu.odu.cs.AlgAE.Animations.LocalJavaAnimation;
import edu.odu.cs.AlgAE.Server.MenuFunction;
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;
import edu.odu.cs.AlgAE.Server.Utilities.Index;


public class QueueDemo extends LocalJavaAnimation {



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
				
				new Queue().arrayQDemo(self);
			}
			
		});
		
		
	}
	
	
	
	public static void main (String[] args) {
		QueueDemo demo = new QueueDemo();
		demo.runAsMain();
	}

}
