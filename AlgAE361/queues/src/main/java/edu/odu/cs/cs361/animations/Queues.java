package edu.odu.cs.cs361.animations;//!

import java.awt.Color;//!
import java.util.ArrayList;//!
import java.util.List;//!

import edu.odu.cs.AlgAE.Server.Animations.LocalJavaAnimation;
import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!
import static edu.odu.cs.AlgAE.Server.Animations.LocalJavaAnimation.activate;//!


public class Queues {//!


//!#include <list>
//!#include <vector>
	
//using namespace std;

//!template <typename T>
class Queue_via_Array
{
//!public:
	Queue_via_Array()	
	{
		start = 0;
		stop = ArraySize-1;
		theSize = 0;
		for (int i = 0; i < ArraySize; ++i) array[i] = "??"; //!
	}
	
	void push (String x)//!	void push (const T& x)
	{
		ActivationRecord arec = activate(getClass());//!
		arec.param("x", x);//!
		arec.breakHere("pushing");//!
//!		assert (theSize &lt; ArraySize);
		arec.breakHere("advance stop, wrapping if necessary");//!
		stop = (stop + 1) % ArraySize;
		arec.breakHere("fill in the data and increment the size");//!
		array[stop] = x;
		theSize++;
		arec.breakHere("done pushing");//!
	}
	
	void pop ()
	{
		ActivationRecord arec = activate(getClass());//!
		arec.breakHere("popping");//!
//!		assert (theSize &gt; 0);
		arec.breakHere("advance start, wrapping if necessary");//!
		start = (start + 1) % ArraySize;
		arec.breakHere("decrement the size");//!
		theSize--;
		arec.breakHere("done popping");//!
	}
	
	String front()//!    T front() const
	{
		ActivationRecord arec = activate(getClass());//!
		arec.breakHere("getting top");//!
		return array[start];
	}

	void clear ()
	{
		start = stop = theSize = 0;
	}

	//!private:
	static final int ArraySize = 6;//!	static const int ArraySize = 6;
	String[] array = new String[6];//!	T array[8];
	int start;
	int stop;
	int theSize = 0;
}
	
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




void listQDemo (LocalJavaAnimation self)
{
	ActivationRecord arec = activate(Queues.class);//!
	Queue_via_List lqueue = new Queue_via_List();//!Queue_via_List<string> lqueue;
	self.getMemoryModel().globalVar("lqueue", lqueue);//!
	arec.breakHere("queues");//!
	for (int i = 0; i < 1000; ++i)
	{
		arec.breakHere("push onto end of queue");//!
		lqueue.push("Adams");
		arec.breakHere("push onto end of queue");//!
		lqueue.push("Baker");
		arec.breakHere("push onto end of queue");//!
		lqueue.push("Carter");
		arec.breakHere("pop from front of queue");//!
		lqueue.pop();
		arec.breakHere("push onto end of queue");//!
		lqueue.push("Davis");
		arec.breakHere("pop from front of queue");//!
		lqueue.pop();
		arec.breakHere("pop from front of queue");//!
		lqueue.pop();
		arec.breakHere("pop from front of queue");//!
		lqueue.pop();
	}
}

void arrayQDemo (LocalJavaAnimation self)
{
	ActivationRecord arec = activate(Queues.class);//!
	Queue_via_Array aqueue = new Queue_via_Array();//!Queue_via_Array<string> aqueue;
	self.getMemoryModel().globalVar("aqueue", aqueue);//!
	arec.breakHere("queues");//!
	for (int i = 0; i < 1000; ++i)
	{
		arec.breakHere("push onto end of queue");//!
		aqueue.push("Adams");
		arec.out().println(aqueue.front() + " is at the front.");//!		cout << aqueue.front() << " is at the front." << endl;
		arec.breakHere("push onto end of queue");//!
		aqueue.push("Baker");
		arec.out().println(aqueue.front() + " is at the front.");//!		cout << aqueue.front() << " is at the front." << endl;
		arec.breakHere("push onto end of queue");//!
		aqueue.push("Carter");
		arec.breakHere("pop from front of queue");//!
		arec.out().println(aqueue.front() + " is at the front.");//!		cout << aqueue.front() << " is at the front." << endl;
		arec.out().println(aqueue.front() + " is at the front.");//!		cout << aqueue.front() << " is at the front." << endl;
		aqueue.pop();
		arec.breakHere("push onto end of queue");//!
		aqueue.push("Davis");
		arec.out().println(aqueue.front() + " is at the front.");//!		cout << aqueue.front() << " is at the front." << endl;
		arec.breakHere("pop from front of queue");//!
		aqueue.pop();
		arec.out().println(aqueue.front() + " is at the front.");//!		cout << aqueue.front() << " is at the front." << endl;
		arec.breakHere("pop from front of queue");//!
		aqueue.pop();
		arec.out().println(aqueue.front() + " is at the front.");//!		cout << aqueue.front() << " is at the front." << endl;
		arec.breakHere("pop from front of queue");//!
		aqueue.pop();
	}
}

}//!
