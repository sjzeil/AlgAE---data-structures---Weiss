package edu.odu.cs.cs361.animations;//!


import edu.odu.cs.AlgAE.Animations.LocalJavaAnimation;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;
import edu.odu.cs.AlgAE.Server.Utilities.ArrayList;
import edu.odu.cs.AlgAE.Server.Utilities.DiscreteInteger;

import static edu.odu.cs.AlgAE.Server.LocalServer.activate;//!

import java.awt.Color;
import java.util.List;


public class Queue implements CanBeRendered<Queue>, Renderer<Queue>{//!


	//!#include <list>
	//!#include <vector>
		
	//using namespace std;

	//!template <typename T>
	//!class Queue
	//!{
	//!public:
		Queue ()	
		{
			start = 0;
			stop = ArraySize-1;
			theSize = 0;
			startD = new DiscreteInteger(start);//!
			stopD = new DiscreteInteger(stop);//!
			array = new ArrayList<DiscreteString>();//!
			for (int i = 0; i < ArraySize; ++i) array.add(new DiscreteString("??")); //!
			array.renderHorizontally(true);//!
		}
		
		void push (String x)//!	void push (const T& x)
		{
			ActivationRecord arec = activate(getClass());//!
			array.pushIndices();//!
			array.indexedBy(startD, "start");//!
			array.indexedBy(stopD, "stop");//!
			arec.param("x", x);//!
			decorate(arec).breakHere("pushing");//!
	//!		assert (theSize &lt; ArraySize);
			arec.breakHere("advance stop, wrapping if necessary");//!
			stop = (stop + 1) % ArraySize;
			stopD.set(stop);//!
			arec.breakHere("fill in the data and increment the size");//!
			array.get(stop).set(x);//!			array[stop] = x;
			theSize++;
			decorate(arec).breakHere("done pushing");//!
			array.popIndices();
		}
		
		void pop ()
		{
			ActivationRecord arec = activate(getClass());//!
			array.pushIndices();//!
			array.indexedBy(startD, "start");//!
			array.indexedBy(stopD, "stop");//!
			decorate(arec).breakHere("popping");//!
	//!		assert (theSize &gt; 0);
			arec.breakHere("advance start, wrapping if necessary");//!
			start = (start + 1) % ArraySize;
			startD.set(start);//!
			arec.breakHere("decrement the size");//!
			theSize--;
			decorate(arec).breakHere("done popping");//!
			array.popIndices();//!
		}
		
		DiscreteString front()//!    T front() const
		{
			ActivationRecord arec = activate(getClass());//!
			array.pushIndices();//!
			array.indexedBy(startD, "start");//!
			array.indexedBy(stopD, "start");//!
			arec.breakHere("getting top");//!
			array.popIndices();
			return array.get(start);//!			return array[start];
		}

		void clear ()
		{
			start = stop = theSize = 0;
		}

		//!private:
		static final int ArraySize = 6;//!	static const int ArraySize = 6;
		ArrayList<DiscreteString> array;//!	T array[8];
		int start;
		int stop;
		DiscreteInteger startD;//!
		DiscreteInteger stopD;//!
		int theSize = 0;
	//!}

void arrayQDemo (LocalJavaAnimation self)
{
	ActivationRecord arec = activate(Queue.class);//!
	Queue queue = new Queue();//!
	self.getContext().getMemoryModel().globalVar("queue", queue);//!
	arec.breakHere("queues");//!
	for (int i = 0; i < 1000; ++i)
	{
		decorate(arec).breakHere("push onto end of queue");//!
		queue.push("Adams");
		decorate(arec).breakHere("push onto end of queue");//!
		queue.push("Baker");
		decorate(arec).breakHere("push onto end of queue");//!
		queue.push("Carter");
		decorate(arec).breakHere("pop from front of queue");//!
		queue.pop();
		decorate(arec).breakHere("push onto end of queue");//!
		queue.push("Davis");
		decorate(arec).breakHere("pop from front of queue");//!
		queue.pop();
		decorate(arec).breakHere("pop from front of queue");//!
		queue.pop();
		decorate(arec).breakHere("pop from front of queue");//!
		queue.pop();
	}
}

/*! */
ActivationRecord decorate (ActivationRecord arec) {
	arec.clearRenderings();
	int i = start;
	while ((stop + 1) % ArraySize != i) {
		arec.highlight(array.get(i));
		i = (i + 1) % Queue.ArraySize;
	}
	return arec;
}

@Override
public Color getColor(Queue arg0) {
	return Color.cyan.brighter();
}

@Override
public List<Component> getComponents(Queue arg0) {
	java.util.ArrayList<Component> components = new java.util.ArrayList<>();
	components.add(new Component(start, "start"));
	components.add(new Component(stop, "stop"));
	components.add(new Component(theSize, "theSize"));
	components.add(new Component(array, "array"));
	return components;
}

@Override
public List<Connection> getConnections(Queue arg0) {
	return new java.util.ArrayList<Connection>();
}

@Override
public int getMaxComponentsPerRow(Queue arg0) {
	return 3;
}

@Override
public String getValue(Queue arg0) {
	return "";
}

@Override
public Renderer<Queue> getRenderer() {
	return this;
}

/* !*/
}//!
