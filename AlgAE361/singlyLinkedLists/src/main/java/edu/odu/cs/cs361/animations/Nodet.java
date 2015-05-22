package edu.odu.cs.cs361.animations;//!

import java.awt.Color;//!
import java.util.LinkedList;//!
import java.util.List;//!

import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!


public class Nodet implements CanBeRendered<Nodet>, Renderer<Nodet> {//!

     //!struct Node
    //!{
	     String data;//! Object  data;
	     Nodet prev;//! Node  *prev;
	     Nodet next;//! Node  *next;
	     
	//! Node( const Object & d = Object{ }, Node * p = nullptr, Node * n = nullptr )
    //!   : data{ d }, prev{ p }, next{ n } { }
	     
	
    public Nodet()//!
    {//!
    	data = "";//!
    	prev = null;//!
    	next = null;//!
    }//!

    //!Node( Object && d, Node * p = nullptr, Node * n = nullptr )
    //!   : data{ std::move( d ) }, prev{ p }, next{ n } { }
    
    public Nodet(String d, Nodet p, Nodet n)//!
    {//!
    	data = d;//!
    	prev = p;
    	next = n;//!
    	
    }//!
//!
//!
	public Renderer<Nodet> getRenderer() {//!
		return this;//!
	}//!
	public Color getColor(Nodet obj) {//!
		return Color.green.darker();//!
	}//!
	public List<Component> getComponents(Nodet obj) {
		LinkedList<Component> data = new LinkedList<Component>();//!
		data.add (new Component(data));//!
		return data;//!
	}//!
	public List<Connection> getConnections(Nodet obj) {//!
		LinkedList<Connection> links = new LinkedList<Connection>();//!
		Connection c =  new Connection(next, 85.0, 85.0);//!
		links.add(c);//!
		return links;//!
	}//!
	public int getMaxComponentsPerRow(Nodet obj) {//!
		return 100;//!
	}//!
	public String getValue(Nodet obj) {//!
		return "";//!
	}//!
}//!};

