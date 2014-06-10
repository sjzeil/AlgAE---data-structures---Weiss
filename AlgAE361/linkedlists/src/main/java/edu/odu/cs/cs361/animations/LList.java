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
import static edu.odu.cs.AlgAE.Server.Animations.LocalJavaAnimation.activate;//!


public class LList implements CanBeRendered<LList>, Renderer<LList> {//!


	//!#ifndef LIST_H
	//!#define LIST_H

	//!#include <algorithm>
	//!using namespace std;

	//!template <typename Object>
	//!class List
	//!{
	//!  private:    
	    // The basic doubly linked list node.
	    // Nested inside of List, can be public
	    // because the Node is itself private
	   
    /*//!struct Node
    //!{
        //!Object  data;
        //!Node   *prev;
        //!Node   *next;

        //!Node( const Object & d = Object{ }, Node * p = nullptr, Node * n = nullptr )
        //!  : data{ d }, prev{ p }, next{ n } { }
        
        //!Node( Object && d, Node * p = nullptr, Node * n = nullptr )
        //!  : data{ std::move( d ) }, prev{ p }, next{ n } { }
    //!};
     * 
     */
    int  theSize;//!
    Node head; //!
    Node tail; //!
    SimpleReference vhead;//!
    SimpleReference vtail;//!
    
class Node implements CanBeRendered<Node>, Renderer<Node> {//!

	    //!struct Node
	    //!{
		     String data;//! Object  data;
		     Node prev;//! Node  *prev;
		     Node next;//! Node  *next;
		     
		//! Node( const Object & d = Object{ }, Node * p = nullptr, Node * n = nullptr )
	    //!   : data{ d }, prev{ p }, next{ n } { }
		     
		
	    public Node()//!
	    {//!
	    	data = "";//!
	    	prev = null;//!
	    	next = null;//!
	    }//!

	    //!Node( Object && d, Node * p = nullptr, Node * n = nullptr )
	    //!   : data{ std::move( d ) }, prev{ p }, next{ n } { }
	    
	    public Node(String d, Node p, Node n)//!
	    {//!Node
	    	data = d;//!
	    	prev = p;
	    	next = n;//!
	    	
	    }//!
	//!
	//!
		public Renderer<Node> getRenderer() {//!
			return this;//!
		}//!
		public Color getColor(Node obj) {//!
			return Color.green.darker();//!
		}//!
		public List<Component> getComponents(Node obj) {
			LinkedList<Component> datavalue = new LinkedList<Component>();//!
			datavalue.add (new Component(data));//!
			return datavalue;//!
		}//!
		public List<Connection> getConnections(Node obj) {//!
			LinkedList<Connection> links = new LinkedList<Connection>();//!
			Connection c =  new Connection(next, 85.0, 85.0);//!
			links.add(c);//!
			Connection c1 =  new Connection(prev, 200.0, 200.0);//!
			links.add(c1);//!
			return links;//!
		}//!
		public int getMaxComponentsPerRow(Node obj) {//!
			return 100;//!
		}//!
		public String getValue(Node obj) {//!
			return "";//!
		}//!
	}//!};
    


	
//!public:
    class const_iterator
    implements CanBeRendered<const_iterator>, Renderer<const_iterator> //!
    {
//!      public:
    	
    	Node current;//!
    	  
        // Public constructor for const_iterator.
    	const_iterator( ) { current = null; }//!
    	 
//!        const_iterator( ) : current{ nullptr }
//!          { }

        // Return the object stored at the current position.
        // For const_iterator, this is an accessor with a
        // const reference return type.
    	
    	String get() { return retrieve( ); }//!
//!        const Object & operator* ( ) const
//!          { return retrieve( ); }
    	
    	const_iterator increment ()//!
//!        const_iterator & operator++ ( )
    	{
    		ActivationRecord arec = activate(getClass());//!
        	arec.refVar("current", current).breakHere("entered operator++()");//!
        	current = current.next;//!
//!            current = current->next;
        	arec.refVar("current", current).breakHere("finsihed operator++");//!
        	return this;//!
//!            return *this;    		
    	}
        
    	const_iterator post_increment ()//!
//!        const_iterator operator++ ( int )
        {
    		const_iterator old = this;//!
//!            const_iterator old = *this;
    		increment();//!  
//!            ++( *this );
            return old;
        }
        
    	 const_iterator decrement ()//!
//!        const_iterator & operator-- ( )
        {
    		ActivationRecord arec = activate(getClass());//!
         	arec.refVar("current", current).breakHere("entered operator--()");//!
         	current = current.prev;//!
//!            current = current->prev;
         	arec.refVar("current", current).breakHere("finsihed operator--");//!
         	return this;//!
//!            return *this;
        }

    	const_iterator postdecrement ()//!
//!        const_iterator operator-- ( int )
        {
    		const_iterator old = this;//!
//!            const_iterator old = *this;
    		decrement();//!
//!            --( *this );
            return old;
        }
    	
    	boolean IsEqualEqual ( const_iterator rhs ) //!          
//!        bool operator== ( const const_iterator & rhs ) const
        { return current == rhs.current; }

        boolean IsNotEqual ( const_iterator rhs ) //!    
//!        bool operator!= ( const const_iterator & rhs ) const
        { return !( this == rhs ); }
//!          { return !( *this == rhs ); }

//!      protected:
//!        Node *current;

        // Protected helper in const_iterator that returns the object
        // stored at the current position. Can be called by all
        // three versions of operator* without any type conversions.
        String  retrieve( )//!
//!        Object & retrieve( ) const
        { return current.data; }
//!          { return current->data; }

        // Protected constructor for const_iterator.
        // Expects a pointer that represents the current position.
        const_iterator( Node p ) { current = p ;}//!
//!        const_iterator( Node *p ) :  current{ p }
//!          { }
        
//!        friend class List<Object>;
        //!
		@Override//!
		public Color getColor(const_iterator obj) {//!
			return null;//!
		}//!
//!
		@Override//!
		public List<Component> getComponents(const_iterator obj) {//!
			LinkedList<Component> comps = new LinkedList<Component>();//!
			comps.add (new Component(new SimpleReference(current, 270, 270), "current"));//!
			//comps.add (new Component(new SimpleReference(nodePtr, 270, 315), "nodePtr"));//!
			return comps;//!
		}//!
//!
		@Override//!
		public List<Connection> getConnections(const_iterator obj) {//!
			return  new LinkedList<Connection>();//!
		}//!
//!
		@Override//!
		public int getMaxComponentsPerRow(const_iterator obj) {//!
			return 1;//!
		}//!
//!
		@Override//!
		public String getValue(const_iterator obj) {//!
			return "";//!
		}//!
//!
//!
		@Override//!
		public Renderer<const_iterator> getRenderer() {//!
			return this;//!
		}//!
    };
    
    class iterator extends  const_iterator//!
//!    class iterator : public const_iterator
    {
//!      public:

        // Public constructor for iterator.
        // Calls the base-class constructor.
        // Must be provided because the private constructor
        // is written; otherwise zero-parameter constructor
        // would be disabled.
        iterator( )
          { }
        
        String get() { return retrieve( ); }//!
//!        Object & operator* ( )
//!          { return const_iterator::retrieve( ); }

        // Return the object stored at the current position.
        // For iterator, there is an accessor with a
        // const reference return type and a mutator with
        // a reference return type. The accessor is shown first.
//!        const Object & operator* ( ) const
//!          { return const_iterator::operator*( ); }
        
        iterator increment ()//!
//!           iterator & operator++ ( )
        {
        	this.current = this.current.next;//!
//!            this->current = this->current->next;
        	return this;//!
//!            return *this;
        }

        iterator postincrement ()//!
//!        iterator operator++ ( int )
        {
        	iterator old = this;//!
//!            iterator old = *this;
        	increment();//!
//!            ++( *this );
            return old;
        }
        
        iterator decrement ()//!
//!        iterator & operator-- ( )
        {
        	this.current = this.current.prev;//!
//!            this->current = this->current->prev;
        	return this;//!
//!            return *this;
        }

        iterator postdecrement ( )//!
//!        iterator operator-- ( int )
        {
        	iterator old = this;//!
//!            iterator old = *this;
        	decrement();//!
//!            --( *this );
            return old;
        }

//!      protected:
        // Protected constructor for iterator.
        // Expects the current position.
        iterator (Node p) { current = p; }
//!        iterator( Node *p ) : const_iterator{ p }
//!          { }

//!        friend class List<Object>;
    };
    
//!    public:
    

       LList() { init();} //!
//!        List( )
//!          { init( ); }

       void destroy() { clear(); head = null; tail = null;}//! 
//!       ~List( )
//!        {
//!            clear( );
//!            delete head;
//!            delete tail;
//!        }

//!        List( const List & rhs )
//!        {
//!            init( );
//!            for( auto & x : rhs )
//!                push_back( x );
//!        }

//!        List & operator= ( const List & rhs )
//!        {
//!            List copy = rhs;
//!            std::swap( *this, copy );
//!            return *this;
//!        }

        LList( LList rhs)//!
//!        List( List && rhs )
//!          : theSize{ rhs.theSize }, head{ rhs.head }, tail{ rhs.tail }
        {
        	rhs.theSize = 0;
            rhs.head = null;
//!            rhs.head = nullptr;
            rhs.tail = null;
//!            rhs.tail = nullptr;
        }
       
//!        List & operator= ( List && rhs )
//!        {    
//!            std::swap( theSize, rhs.theSize );
//!            std::swap( head, rhs.head );
//!            std::swap( tail, rhs.tail );
            
//!            return *this;
//!        }
        
        
        // Return iterator representing beginning of list.
        // Mutator version is first, then accessor version.
        iterator begin( )
        { return new iterator( head.next);}//!
//!          { return iterator( head->next ); }

        const_iterator constbegin( )
//!        const_iterator begin( ) const
        { return new const_iterator( head.next ); }
//!          { return const_iterator( head->next ); }

        // Return iterator representing endmarker of list.
        // Mutator version is first, then accessor version.
        iterator end( )
          { return new iterator( tail ); }

        const_iterator constend( )//!
//!        const_iterator end( ) const
          { return new const_iterator( tail ); }

        // Return number of elements currently in the list.
        int size( )//!
//!        int size( ) const
          { return theSize; }

        // Return true if the list is empty, false otherwise.
        boolean empty( )//!
//!        bool empty( ) const
          { return size( ) == 0; }

        void clear( )
        {
            while( !empty( ) )
                pop_front( );
        }
     
        // front, back, push_front, push_back, pop_front, and pop_back
        // are the basic double-ended queue operations.
        String front( )//!
//!        Object & front( )
        { return begin().get(); }//!
//!          { return *begin( ); }

//!        const Object & front( ) const
//!          { return *begin( ); }

        String back( )//!
//!        Object & back( )
        { return (end().decrement().get()); }
//!          { return *--end( ); }

//!        const Object & back( ) const
//!          { return *--end( ); }

        void push_front( String x)//!
//!        void push_front( const Object & x )
          { insert( begin( ), x ); }

        void push_back( String x )
//!        void push_back( const Object & x )
          { insert( end( ), x ); }

//!        void push_front( Object && x )
//!          { insert( begin( ), std::move( x ) ); }

//!        void push_back( Object && x )
//!          { insert( end( ), std::move( x ) ); }

        void pop_front( )
          { erase( begin( ) ); }

        void pop_back( )
        { erase( end( ).decrement() ); }
//!          { erase( --end( ) ); }

        // Insert x before itr.
        iterator insert( iterator itr, String x )
//!        iterator insert( iterator itr, const Object & x )
        {
        	
        	Node p = itr.current;
//!            Node *p = itr.current;
            ++theSize;
            Node addNode = new Node( x, p.prev, p ) ;
            p.prev.next = addNode;
            p.prev = addNode;      
            return new iterator( addNode );
//!            return iterator( p->prev = p->prev->next = new Node{ x, p->prev, p } );
        }

        // Insert x before itr.
//!        iterator insert( iterator itr, Object && x )
//!        {
//!            Node *p = itr.current;
//!            ++theSize;
//!            return iterator( p->prev = p->prev->next = new Node{ std::move( x ), p->prev, p } );
//!        }
        
        // Erase item at itr.
        iterator erase( iterator itr )
        {
        	System.out.println("clear");
        	Node p = itr.current;//!
        	System.out.println(p.data);
//!            Node *p = itr.current;
        	iterator retVal = new iterator(p.next);//!
//!            iterator retVal( p->next );
        	p.prev.next = p.next;//!
//!            p->prev->next = p->next;
        	p.next.prev = p.prev;//!
//!            p->next->prev = p->prev;
        	p = null;//!
//!            delete p;
            --theSize;

            return retVal;
        }

        iterator erase( iterator from, iterator to )
        {
            for( iterator itr = from; itr != to; )
                itr = erase( itr );

            return to;
        }

//!      private:
//!        int   theSize;
//!        Node *head;
//!        Node *tail;

        void init( )
        {
            theSize = 0;
            head = new Node();//!
 //!           head = new Node;
            tail = new Node();//!
 //!           tail = new Node;
            head.next = tail;//!
//!            head->next = tail;
            tail.prev = head;//!
//!            tail->prev = head;
            vhead = new SimpleReference(null, 90, 125);//!
            vhead.set(head);
            vtail = new SimpleReference(null, 100, 125);//!
            vtail.set(tail);
        }
  
    
  //!
  	public Renderer<LList> getRenderer() {//!
  		return this;//!
  	}//!
  	public Color getColor(LList obj) {//!
  		return Color.green.darker();//!
  	}//!
  	public List<Component> getComponents(LList obj) {
  		vhead.set(head);//!
  		vtail.set(tail);//!
  		LinkedList<Component> data = new LinkedList<Component>();//!
  		data.add (new Component(vhead, ""));//!
  		data.add (new Component(vtail, ""));//!
  		data.add (new Component(theSize, "size"));
  		return data;//!
  	}//!
  	public List<Connection> getConnections(LList obj) {//!
  		LinkedList<Connection> links = new LinkedList<Connection>();//!
  		//Connection c =  new Connection(link, 85.0, 95.0);//!
  		//links.add(c);//!
  		return links;//!
  	}//!
  	public int getMaxComponentsPerRow(LList obj) {//!
  		return 1;//!
  	}//!
  	public String getValue(LList obj) {//!
  		return "";//!
  	}//!




public void setUp ()//!
{//!
	//head = null;//!
	//tail = null;//!
	head = new Node ("head",null,null);//!
	tail = new Node("tail",null,null);//!
	vhead.set(head);//!
	vtail.set(tail);//!
	theSize = 3;//!
	
	Node newNode1 = new Node("Davis", head, null);//!
	head.next = newNode1;//!
	newNode1.prev = head;//!
	Node newNode2 = new Node("Baker", newNode1, null);//!
	newNode1.next = newNode2;//!
	newNode2.prev = newNode1;//!
	Node newNode3 = new Node("Adams", newNode2, null);//!
	newNode2.next = newNode3;//!
	newNode3.prev = newNode2;//!
	newNode3.next = tail;//!
	tail.prev = newNode3;//!
	tail.next = null;//!
	
}//!


void addToFrontQ (String value)//!
{//!
	Node newNode = new Node(value, null, head);//!
	head = newNode;//!
}//!


Node findQ (String value)//!
{
	Node current = head;//!
	while (current != null && !current.data.equals(value))//!
	{//!
		current = current.next;//!
	}//!
	return current;//!
}//!

};//!

//!    #endif


