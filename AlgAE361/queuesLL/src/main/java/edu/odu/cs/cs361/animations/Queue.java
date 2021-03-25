package edu.odu.cs.cs361.animations;//!

import java.awt.Color;//!
import java.util.ArrayList;//!
import java.util.Arrays;
import java.util.List;//!

import edu.odu.cs.AlgAE.Animations.LocalJavaAnimation;
import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!
import edu.odu.cs.AlgAE.Server.Utilities.LinkedList;
import edu.odu.cs.AlgAE.Server.Utilities.RenderedReference;

import static edu.odu.cs.AlgAE.Server.LocalServer.activate;//!


public class Queue implements CanBeRendered<Queue>, Renderer<Queue> {//!


//!#include "dsexceptions.h"
//!#include "List.h"
//!#include "Vector.h"

//!#include <stdlib.h>
//!#include <vector>
//!#include <iostream>
//!using namespace std;
	
//!template <typename Object>
//!class Queue
//!{
//!  public:

	Queue()//!
	{//!
		list = new LinkedList<>();//!
		list.showBackLinks(false);//!
	}//!
	
//!	bool isEmpty( ) const
//!	  { return theList.empty( ); }
	
	String front()//!
	//!    const Object & getFront( ) const
		{
			ActivationRecord arec = activate(getClass());//!
			arec.breakHere("getting front");//!
			return list.get(0);//!
	//!        return theList.front( );
		}
	
	void push (String x)//!	
//!    void enqueue( const Object & x )
	{
		ActivationRecord arec = activate(getClass());//!
		arec.param("x", x);//!
		arec.breakHere("pushing");//!
		list.add(x);//!
//!        theList.push_back( x );
		arec.breakHere("done pushing");//!
	}
	
	void pop ()//!
//!    void dequeue( Object & x )
	{
		ActivationRecord arec = activate(getClass());//!
		arec.breakHere("popping");//!
		list.remove(0);//!
//!        x = theList.front( ); theList.pop_front( );
		arec.breakHere("done popping");//!
	}
	//!private:
	LinkedList<String> list;//!
//!    list<Object> theList;

	


void listQDemo (LocalJavaAnimation self)
{
	ActivationRecord arec = activate(Queue.class);//!
	Queue queue = new Queue();//!
	self.getContext().getMemoryModel().globalVar("queue", queue);//!
	arec.breakHere("queues");//!
	for (int i = 0; i < 1000; ++i)
	{
		arec.breakHere("push onto end of queue");//!
		queue.push("Adams");
		arec.breakHere("push onto end of queue");//!
		queue.push("Baker");
		arec.breakHere("push onto end of queue");//!
		queue.push("Carter");
		arec.breakHere("pop from front of queue");//!
		queue.pop();
		arec.breakHere("push onto end of queue");//!
		queue.push("Davis");
		arec.breakHere("pop from front of queue");//!
		queue.pop();
		arec.breakHere("pop from front of queue");//!
		queue.pop();
		arec.breakHere("pop from front of queue");//!
		queue.pop();
	}
}

/*! */
@Override
public Color getColor(Queue arg0) {
	return null;
}

@Override
public List<Component> getComponents(Queue q) {
	Component[] components = {new Component(list, "list")};
	return Arrays.asList(components);
}

@Override
public List<Connection> getConnections(Queue arg0) {
	return new ArrayList<Connection>();
}

@Override
public int getMaxComponentsPerRow(Queue arg0) {
	return 1;
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
