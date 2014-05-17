package edu.odu.cs.cs361.animations;//!

import java.awt.Color;//!
import java.util.LinkedList;//!
import java.util.List;//!

import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!


public class node implements CanBeRendered<node>, Renderer<node> {//!


	
	//!#include <cstdlib>
	//!#include <iostream>
	//!
	//!using namespace std;
	//!
	//
	//  Based on Ford & Topp, Data Structures in C++ using STL, 2nd ed.
	//
	//!        
	//!template <typename T>
	//!class node {
	//!public:
	String nodeValue;//!  T nodeValue;
	node next;//!  node<T>* next;
	//!  
	//!  node() : next(NULL)
	//!  {}
    public node()//!
    {//!
    	nodeValue = "";//!
    	next = null;//!
    }//!
	//!  
	//!  node (const T& item, node<T>* nextNode = NULL)
	//!    : nodeValue(item), next(nextNode)
	//!  {}
    public node(String item, node nextNode)//!
    {//!
    	nodeValue = item;//!
    	next = nextNode;//!
    }//!
//!
//!
	public Renderer<node> getRenderer() {//!
		return this;//!
	}//!
	public Color getColor(node obj) {//!
		return Color.green.darker();//!
	}//!
	public List<Component> getComponents(node obj) {
		LinkedList<Component> data = new LinkedList<Component>();//!
		data.add (new Component(nodeValue));//!
		return data;//!
	}//!
	public List<Connection> getConnections(node obj) {//!
		LinkedList<Connection> links = new LinkedList<Connection>();//!
		Connection c =  new Connection(next, 85.0, 85.0);//!
		links.add(c);//!
		return links;//!
	}//!
	public int getMaxComponentsPerRow(node obj) {//!
		return 100;//!
	}//!
	public String getValue(node obj) {//!
		return "";//!
	}//!
}//!};

