package edu.odu.cs.cs361.animations;//!

import java.awt.Color;//!
import java.util.LinkedList;//!
import java.util.List;//!

import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!
import edu.odu.cs.AlgAE.Server.Utilities.SimpleReference;//!
import static edu.odu.cs.AlgAE.Server.LocalServer.activate;//!


public class LListHeader implements CanBeRendered<LListHeader>, Renderer<LListHeader> {//!


	
	//!#include <cstdlib>
	//!#include <iostream>
	//!
	//!using namespace std;
	//!
	//
	//  Based on Ford & Topp, Data Structures in C++ using STL, 2nd ed.
	//
	//!        
	//!
	//!template <typename Data>
	//!struct LListHeader {
	//!  
	node first;//!  node<Data>* first;
	SimpleReference vfirst;//!
	int size;
	static node NULL = null;//!
	//!  
	LListHeader()
	{//!    : first(NULL), size(0)
		first = null;//!
		vfirst = new SimpleReference(null, 90, 125);//!
		size = 0;//!
	}//!  {}

	//!  void add (const Data& x);
	//!  void remove (const Data& x);
	//!  void clear();

	//!private:
	//!  void addInOrder(const Data& x);
	//!  void addToEnd(const Data& x);
	//!  void addToFront(const Data& x);
	//!  void addAfter (node<Data>* afterThis, const Data value);
	//!  void addBefore (node<Data>* beforeThis, const Data value);
	//!  void removeAfter (node<Data>* afterThis);
	//!};

	//!template <typename Data>
void outputOp ()//!std::ostream& operator<< (const std::ostream& out, const LListHeader<Data>& list)
{
	ActivationRecord arec = activate(getClass());//!
	LListHeader list = this;//!
	arec.param("out", "").refParam("list", list);//!
	arec.breakHere("starting traversal");//!
	arec.pushScope();//!
	for (node current = list.first; current != null; current = current.next)//!
	//!  for (const node<Data>* current = list.first; current != NULL; current = current->next)
	{
		arec.refVar("current", current);//!
		arec.breakHere("if not first, print a comma");//!
		if (current != list.first)
			arec.out().print(", ");//!        out << ", ";
		arec.breakHere("print the value");//!
		arec.out().print(current.nodeValue);//!      out << current->nodeValue;
		arec.breakHere("repeat if necessary");//!
	}
	arec.popScope();//!
	arec.breakHere("done");//!
	arec.out().println();//!  out << "\n";
	//!  return out;
}

//!template <typename Data>
void add (String value)//!void LListHeader<Data>::add (const Data& value)
{
	ActivationRecord arec = activate(this);//!
	arec.param("value", value);//!
	arec.breakHere("starting add");//!
	addInOrder (value);
	arec.breakHere("added - now update the size");//!
	++size;
	arec.breakHere("done");//!
}

//!template <typename Data>
void remove (String value)	//!void LListHeader<Data>::remove (const Data& value)
{
	ActivationRecord arec = activate(this);//!
	arec.param("value", value);//!
	arec.breakHere("starting remove");//!
	node prev = null;//!  node<Data>* prev = NULL;
	node current = first;//!  node<Data>* current = first;
	arec.refVar("current", current).refVar("prev", prev);//!
	arec.breakHere("search for the location to remove");//!
	while (current != null && !current.nodeValue.equals(value))//!  while (current != NULL && !(current->nodeValue == value))
	{
		arec.breakHere("not yet - move prev forward");//!
		prev = current;
		arec.refVar("prev", prev);//!
		arec.breakHere("then move current forward");//!
		current = current.next;//!   current = current->next;
		arec.refVar("current", current);//!
	}
	arec.breakHere("We've either found it or run off the end of the loop");//!
	if (current != NULL)
	{
		// We found the item we were looking for.
		arec.breakHere("We've found it");//!
		if (prev == NULL)
		{
			// We are removing the first node in the list
			arec.breakHere("move first forward");//!
			first = current.next;//!       first = current->next;       
			arec.breakHere("then delete the former first node");//!
			delete(current,arec);//!       delete current;
			arec.refVar("current", null);//!
		}
		else
			{arec.breakHere("remove the node after prev");//!
			removeAfter (prev);
			arec.refVar("prev", prev);//!
			arec.breakHere("done removing after prev");}//!

		arec.breakHere("Finally, decrement the size counter");//!
		--size;
	}   
	arec.breakHere("done");//!
}

void delete(node n, ActivationRecord arec)//!
{//!
	arec.highlight(n, Color.DARK_GRAY);
	n.nodeValue="??";
	n.next=null;
}//!

//!template <typename Data>
void clear()	//!void LListHeader<Data>::clear()
{
	ActivationRecord arec = activate(this);//!
	node current = first;//!  node<Data>* current = first;
	arec.refVar("current", current);//!
	arec.breakHere("delete nodes, starting at the beginning");//!
	while (current != NULL)
	{
		arec.pushScope();
		arec.breakHere("save the next location (because we're about to delete the current one)");//!
		node next = current.next;//!      node<Data>* next = current->next;
		arec.refVar("next", next);//!
		arec.breakHere("delete the current node");//!
		delete (current, arec);//!      delete current;
		arec.refVar("current", null);//!
		arec.breakHere("move current to next");//!
		current = next;
		arec.refVar("current", current);//!
		arec.breakHere("repeat if necessary");//!
		arec.popScope();
	}
	arec.breakHere("clear the data members");//!
	first = NULL;
	size = 0;
	arec.breakHere("done");//!
}

//!template <typename Data>
void addInOrder (String value)//!void LListHeader<Data>::addInOrder(const Data& value)
{
	ActivationRecord arec = activate(this);//!
	arec.param("value", value);//!
	arec.breakHere("starting addInOrder");//!
	if (first == NULL)
	{arec.breakHere("adding to an empty list is easy");//!
		first = new node(value, NULL);//!    first = new node<Data>(value, NULL);
		arec.breakHere("done adding to an empty list");}//!
	else 
	{
		arec.pushScope();//!
		node current = first;//!      node<Data>* current = first;
		arec.refVar("current", current);//!
		arec.breakHere("start looking for where to insert");//!
		node prev = null;//!      node<Data>* prev = NULL;
		arec.refVar("prev", prev);//!
		arec.breakHere("prev will be one step behind current");//!
		while (current != null && current.nodeValue.compareTo(value) < 0)//!      while (current != NULL && current->nodeValue < value)
		{
			arec.breakHere("haven't found the insertion point yet - move prev forward");//!
			prev = current;
			arec.refVar("prev", prev);//!
			arec.breakHere("then move current forward");//!
			current = current.next;//!          current = current->next;
			arec.refVar("current", current);//!
		}
		// Add between prev and current
		if (prev == NULL)
		{arec.breakHere("we're actually adding at the front");//!
			first = new node(value, first);//!        first = new node<Data>(value, first);
			arec.breakHere("done");}//!
		else
		{
			arec.breakHere("add after prev");//!
			addAfter (prev, value);
			arec.breakHere("done adding after prev");//!
		}
		arec.popScope();
	}
}

//!template <typename Data>
void addToEnd (String value)//!void LListHeader<Data>::addToEnd(const Data& value)
{
	ActivationRecord arec = activate(this);//!
	arec.breakHere("starting addToEnd");//!
	node newNode = new node(value, null);//!  node<Data>* newNode = new node<Data>(value, NULL);
	arec.refVar("newNode", newNode);//!
	arec.breakHere("allocated the new node");//!
	if (first == NULL)
	  {
		arec.breakHere("if the list is empty, this is easy");//!
		first = newNode;
		arec.breakHere("done");//!
	  }
	else
	  {
		// Move to last node 
		node current = first;//!      node<Data>* current = first;
		arec.refVar("current", current);//!
		arec.breakHere("start at the beginning");//!
		while (current.next != null)//!      while (current->next != NULL)
			{arec.breakHere("keep moving forward");//!
			current = current.next;//!        current = current->next;
			arec.refVar("current", current);//!
			}//!
		// Link after that node
		arec.breakHere("current now points to the last node");//!
		current.next = newNode;//!      current->next = newNode;
		arec.breakHere("done");//!
	  }
}

//!template <typename Data>
void addToFront (String value)//!void LListHeader<Data>::addToFront(const Data& value)
{
	node newNode = new node(value, first);//!  node<Data>* newNode = new node<Data>(value, first);
	first = newNode;
}

//!template <typename Data>
void addAfter (node afterThis, String value)//!void LListHeader<Data>::addAfter (node<Data>* afterThis, const Data value)
{
	ActivationRecord arec = activate(this);//!
	arec.refParam("afterThis", afterThis).param("value", value);//!
	arec.breakHere("starting addAfter: allocate the new node");//!
	node newNode = new node(value, afterThis.next);//!  node<Data>* newNode = new node<Data> (value, afterThis->next);
	arec.refVar("newNode", newNode);//!
	arec.breakHere("make *afterThis point to the new node");//!
	afterThis.next = newNode;//!  afterThis->next = newNode;
	arec.breakHere("done");//!
}

//!template <typename Data>
void addBefore (node beforeThis, String value)//!void LListHeader<Data>::addBefore (node<Data>* beforeThis, const Data value)
{
	if (beforeThis == first)
		addToFront (value);
	else
	  {
		// Move to front of beforeThis
		node current = first;//!      node<Data>* current = first;
		while (current.next != beforeThis)//!      while (current->next != beforeThis)
			current = current.next;//!        current = current->next;

		// Link after that node
		addAfter (current, value);
	  }
}


//!template <typename Data>
void removeAfter (node afterThis)//!void LListHeader<Data>::removeAfter (node<Data>* afterThis)
{
	ActivationRecord arec = activate(this);//!
	arec.refParam("afterThis", afterThis);//!
	arec.breakHere("starting removeAfter");//!
	node toRemove = afterThis.next;//!  node<Data>* toRemove = afterThis->next;
	arec.refVar("toRemove", toRemove);//!
	arec.breakHere("make the list point 'around' the node we want to remove to the node to remove");//!
	afterThis.next = toRemove.next;//!  afterThis->next = toRemove->next;
	arec.breakHere("then delete the unwanted node");//!
	delete (toRemove, arec);//!  delete toRemove;
	arec.breakHere("done");//!
}


//!template <typename Data>
node find (String value)//!const node<Data>* LListHeader<Data>::find (const Data& value) const
{
	ActivationRecord arec = activate(this);//!
	arec.param("value", value);//!
	arec.breakHere("starting a search");//!
	node current = first;//!    const node<Data>* current = first;
	arec.refVar("current", current);//!
	arec.breakHere("start at the beginning");//!
	while (current != null && !current.nodeValue.equals(value))//!    while (current != NULL && !(current.nodeValue == value))
	  {
		arec.breakHere("Haven't found it yet - move forward");//!
		current = current.next;
		arec.refVar("current", current);//!
	  }
	arec.breakHere("We have either found it or run off the end of the list");//!
	return current;
}

	

//!
	public Renderer<LListHeader> getRenderer() {//!
		return this;//!
	}//!
	public Color getColor(LListHeader obj) {//!
		return Color.green.darker();//!
	}//!
	public List<Component> getComponents(LListHeader obj) {
		vfirst.set(first);//!
		LinkedList<Component> data = new LinkedList<Component>();//!
		data.add (new Component(vfirst, "first"));//!
		data.add (new Component(size, "size"));
		return data;//!
	}//!
	public List<Connection> getConnections(LListHeader obj) {//!
		LinkedList<Connection> links = new LinkedList<Connection>();//!
		//Connection c =  new Connection(link, 85.0, 95.0);//!
		//links.add(c);//!
		return links;//!
	}//!
	public int getMaxComponentsPerRow(LListHeader obj) {//!
		return 1;//!
	}//!
	public String getValue(LListHeader obj) {//!
		return "";//!
	}//!


public void setUp ()//!
{//!
	first = NULL;//!
	size = 3;//!
	addToFrontQ("Davis");//!
	addToFrontQ("Baker");//!
	addToFrontQ("Adams");//!
}//!

void addToFrontQ (String value)//!
{//!
	node newNode = new node(value, first);//!
	first = newNode;//!
}//!


node findQ (String value)//!
{
	node current = first;//!
	while (current != null && !current.nodeValue.equals(value))//!
	{//!
		current = current.next;//!
	}//!
	return current;//!
}//!


}//!
