package edu.odu.cs.cs361.animations;//!

import java.awt.Color;//!
import java.util.ArrayList;//!
import java.util.List;//!

import edu.odu.cs.AlgAE.Animations.LocalJavaAnimationApplet;
import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!
import static edu.odu.cs.AlgAE.Server.LocalServer.activate;//!


public class Queue {//!


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
	
	class Node implements Renderer<Node>, CanBeRendered<Node> {//!
		String data;//!
		Node next;//!
		//!
		Node (String d, Node nxt)//!
		{//!
			data = d;//!
			next = nxt;//!
		}//!
//!
		@Override//!
		public Renderer<Node> getRenderer() {//!
			return this;//!
		}//!
//!
		@Override//!
		public String getValue(Node obj) {//!
			return data;//!
		}//!
//!
		@Override//!
		public Color getColor(Node obj) {//!
			return null;//!
		}//!
//!
		@Override//!
		public List<Component> getComponents(Node obj) {//!
			return new ArrayList<Component>();//!
		}//!
//!
		@Override//!
		public List<Connection> getConnections(Node obj) {//!
			ArrayList<Connection> conn = new ArrayList<Connection>();//!
			conn.add (new Connection(next, 90.0, 90.0));//!
			return conn;//!
		}//!
//!
		@Override//!
		public int getMaxComponentsPerRow(Node obj) {//!
			return 1;//!
		}//!
	}//!
	
//!	bool isEmpty( ) const
//!	  { return theList.empty( ); }
	
	String front()//!
	//!    const Object & getFront( ) const
		{
			ActivationRecord arec = activate(getClass());//!
			arec.breakHere("getting front");//!
			return (first != null) ? first.data : "";//!
	//!        return theList.front( );
		}
	
	void push (String x)//!	
//!    void enqueue( const Object & x )
	{
		ActivationRecord arec = activate(getClass());//!
		arec.param("x", x);//!
		arec.breakHere("pushing");//!
		if (first == null) first = last = new Node(x, null); else last = last.next = new Node(x, null);;//!
//!        theList.push_back( x );
		arec.breakHere("done pushing");//!
	}
	
	void pop ()//!
//!    void dequeue( Object & x )
	{
		ActivationRecord arec = activate(getClass());//!
		arec.breakHere("popping");//!
		first = first.next; if(first == null) last = null;//!
//!        x = theList.front( ); theList.pop_front( );
		arec.breakHere("done popping");//!
	}
	//!private:
	Node first = null; Node last = null;//!
//!    List<Object> theList;

	

//!template <typename T>
class Queue_via_List
{
//!public:
	Queue_via_List()	{}
	
	class Node implements Renderer<Node>, CanBeRendered<Node> {//!
		String data;//!
		Node next;//!
		//!
		Node (String d, Node nxt)//!
		{//!
			data = d;//!
			next = nxt;//!
		}//!
//!
		@Override//!
		public Renderer<Node> getRenderer() {//!
			return this;//!
		}//!
//!
		@Override//!
		public String getValue(Node obj) {//!
			return data;//!
		}//!
//!
		@Override//!
		public Color getColor(Node obj) {//!
			return null;//!
		}//!
//!
		@Override//!
		public List<Component> getComponents(Node obj) {//!
			return new ArrayList<Component>();//!
		}//!
//!
		@Override//!
		public List<Connection> getConnections(Node obj) {//!
			ArrayList<Connection> conn = new ArrayList<Connection>();//!
			conn.add (new Connection(next, 90.0, 90.0));//!
			return conn;//!
		}//!
//!
		@Override//!
		public int getMaxComponentsPerRow(Node obj) {//!
			return 1;//!
		}//!
	}//!
	
	void push (String x)//!	void push (const T& x)
	{
		ActivationRecord arec = activate(getClass());//!
		arec.param("x", x);//!
		arec.breakHere("pushing");//!
		if (first == null) first = last = new Node(x, null); else last = last.next = new Node(x, null);;//!		list.push_back(x);
		arec.breakHere("done pushing");//!
	}
	
	void pop ()
	{
		ActivationRecord arec = activate(getClass());//!
		arec.breakHere("popping");//!
		first = first.next; if(first == null) last = null;//!		list.pop_front(x);
		arec.breakHere("done popping");//!
	}
	
	String front()//!    T front() const
	{
		ActivationRecord arec = activate(getClass());//!
		arec.breakHere("getting front");//!
		return (first != null) ? first.data : "";//!        return list.front();
	}

	void clear ()
	{
		first = last = null;//!		list.clear();
	}

	//!private:
	Node first = null; Node last = null;//!    list<T> list;
}


void listQDemo (LocalJavaAnimationApplet self)
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


}//!
