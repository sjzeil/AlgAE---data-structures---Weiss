package edu.odu.cs.cs361.animations;//!

import java.awt.Color;//!
import java.util.ArrayList;//!
import java.util.List;//!

import edu.odu.cs.zeil.AlgAE.ActivationRecord;//!
import edu.odu.cs.zeil.AlgAE.Animation;//!
import edu.odu.cs.zeil.AlgAE.Snapshot.Component;//!
import edu.odu.cs.zeil.AlgAE.Snapshot.Connection;//!
import edu.odu.cs.zeil.AlgAE.Snapshot.Rendering.CanBeRendered;//!
import edu.odu.cs.zeil.AlgAE.Snapshot.Rendering.Renderer;//!

public class Stacks {//!


//!#include <list>
//!#include <vector>
	
//using namespace std;

//!template <typename T>
class Stack_via_Vector
{
//!public:
	Stack_via_Vector()	{}
	
	void push (String x)//!	void push (const T& x)
	{
		ActivationRecord arec = Animation.activate(this);//!
		arec.param("x", x).breakHere("pushing");//!
		v.add(x);//!		v.push_back(x);
		arec.breakHere("done pushing");//!
	}
	
	void pop ()
	{
		ActivationRecord arec = Animation.activate(this);//!
		arec.breakHere("popping");//!
		v.remove(v.size()-1);//!		v.pop_back(x);
		arec.breakHere("done popping");//!
	}
	
	String top()
	{
		ActivationRecord arec = Animation.activate(this);//!
		arec.breakHere("getting top");//!
		return (v.size() > 0) ? v.get(0) : "";//!        return v.front();
	}

	void clear ()
	{
		v.clear();
	}

	//!private:
	ArrayList<String> v = new ArrayList<String>();//!    vector<T> v;
}
	
//!template <typename T>
class Stack_via_List
{
//!public:
	Stack_via_List()	{}
	
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
		ActivationRecord arec = Animation.activate(this);//!
		arec.param("x", x).breakHere("pushing");//!
		first = new Node(x, first);//!		list.push_back(x);
		arec.breakHere("done pushing");//!
	}
	
	void pop ()
	{
		ActivationRecord arec = Animation.activate(this);//!
		arec.breakHere("popping");//!
		first = first.next;//!		list.pop_back(x);
		arec.breakHere("done popping");//!
	}
	
	String top()//!    T top() const
	{
		ActivationRecord arec = Animation.activate(this);//!
		arec.breakHere("getting top");//!
		return (first != null) ? first.data : "";//!        return list.front();
	}

	void clear ()
	{
		first = null;//!		list.clear();
	}

	//!private:
	Node first = null; //!    list<T> list;
}




void demo ()
{
	ActivationRecord arec = Animation.activate(Stacks.class);//!
	Stack_via_Vector vstack = new Stack_via_Vector();//!Stack_via_Vector<string> vstack;
	Stack_via_List lstack = new Stack_via_List();//!Stack_via_List<string> lstack;
	arec.context().getActivationStack().globalVar("vstack", vstack);//!
	arec.context().getActivationStack().globalVar("lstack", lstack);//!
	arec.breakHere("stacks");//!
	vstack.push("Adams");
	arec.breakHere("pushed onto vector stack");//!
	lstack.push("Adams");
	arec.breakHere("pushed onto list stack");//!
	for (int i = 0; i < 1000; ++i)
	{
		arec.breakHere("push onto vector stack");//!
		vstack.push("Baker");
		arec.breakHere("push onto list stack");//!
		lstack.push("Baker");
		arec.breakHere("push onto vector stack");//!
		vstack.push("Carter");
		arec.breakHere("push onto list stack");//!
		lstack.push("Carter");
		arec.breakHere("pop from vector stack");//!
		vstack.pop();
		arec.breakHere("pop from list stack");//!
		lstack.pop();
		arec.breakHere("push onto vector stack");//!
		vstack.push("Davis");
		arec.breakHere("push onto list stack");//!
		lstack.push("Davis");
		arec.breakHere("pop from vector stack");//!
		vstack.pop();
		arec.breakHere("pop from list stack");//!
		lstack.pop();
		arec.breakHere("pop from vector stack");//!
		vstack.pop();
		arec.breakHere("pop from list stack");//!
		lstack.pop();
	}
}

}//!
