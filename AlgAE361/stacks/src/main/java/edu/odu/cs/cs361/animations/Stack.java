package edu.odu.cs.cs361.animations;//!

import java.awt.Color;//!
import java.util.ArrayList;//!
import java.util.List;//!

import edu.odu.cs.AlgAE.Animations.LocalJavaAnimation;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!
import edu.odu.cs.AlgAE.Server.Utilities.RenderedReference;

import static edu.odu.cs.AlgAE.Server.LocalServer.activate;//!


public class Stack {//!

//!#include "dsexceptions.h"
//!#include "List.h"
//!#include "Vector.h"

//!#include <stdlib.h>
//!#include <vector>
//!#include <iostream>
//!using namespace std;
	
//!template <typename Object>
//!class Stack 
//!{
//!   public:
      
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
	
//!bool isEmpty( ) const
//!	 { return theList.empty( ); }
	
	String top()//!
//!     const Object & top( ) const
	{
		ActivationRecord arec = activate(getClass());//!
		arec.breakHere("getting top");//!
		return (first != null) ? first.data : "";//!
//!        return theList.front( );
	}
	
	void push (String x)//!
//!    void push( const Object & x )
	{
		ActivationRecord arec = activate(getClass());//!
		arec.param("x", x).breakHere("pushing");//!
		first = new Node(x, first);//!
//!        theList.push_front( x );
		arec.breakHere("done pushing");//!
	}
	
	void pop ()//!
//!	   void pop( Object & x )
	{
		ActivationRecord arec = activate(getClass());//!
		arec.breakHere("popping");//!
		first = first.next;//!
//!        x = theList.front( ); theList.pop_front( );
		arec.breakHere("done popping");//!
	}
	
	//!private:
	Node first = null; //!    List<Object> theList;
	RenderedReference<Node> firstR = new RenderedReference<Stack.Node>(null, 140, 180);//!
	

void demo (LocalJavaAnimation anim)
{
	ActivationRecord arec = activate(Stack.class);//!
	Stack lstack = new Stack();//!
	anim.getContext().getMemoryModel().globalVar("lstack", lstack);//!
	arec.breakHere("stacks");//!
	arec.breakHere("pushed onto vector stack");//!
	lstack.push("Adams");
	arec.breakHere("pushed onto stack");//!
	for (int i = 0; i < 1000; ++i)
	{

		arec.breakHere("push onto stack");//!
		lstack.push("Baker");
		arec.breakHere("push onto stack");//!
		lstack.push("Carter");
		arec.breakHere("pop from stack");//!
		lstack.pop();
		arec.breakHere("push onto stack");//!
		lstack.push("Davis");
		arec.breakHere("pop from stack");//!
		lstack.pop();
		arec.breakHere("pop from stack");//!
		lstack.pop();
	}
}

}//!
