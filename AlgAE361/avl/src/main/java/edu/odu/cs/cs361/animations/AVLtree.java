package edu.odu.cs.cs361.animations;//!

import static edu.odu.cs.AlgAE.Server.LocalServer.activate;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!
import edu.odu.cs.AlgAE.Server.Utilities.RenderedReference;
import edu.odu.cs.AlgAE.Server.Utilities.SimpleReference;


public class AVLtree<T extends Comparable<T>> {//!
	
	avlNode<T> root;
	RenderedReference<avlNode<T>> rootRef;//!
	
	AVLtree() {
		root = null;
		rootRef = new RenderedReference<avlNode<T>>(root, 140, 220);//!
	}
	
	

   /**
    * Destructor for the tree
   */
   void clear()//!
//!   ~BinarySearchTree( )
   {
       makeEmpty( root);
   }
        

   /**
    * Returns true if x is found in the tree.
    */
   boolean contains( T x)//!
//!   bool contains( const Comparable & x ) const
   {
	   ActivationRecord arec = activate(getClass());//!
	   arec.param("x", x).breakHere("entered contains");//!
       return contains( x, root );
   }

   /**
    * Test if the tree is logically empty.
    * Return true if empty, false otherwise.
    */
   boolean isEmpty( ) //!
//!   bool isEmpty( ) const
   {
	   return root == null;//!
//!       return root == nullptr;
   }


   /**
    * Insert x into the tree; duplicates are ignored.
    */
   void insert( T  x )//!
//!   void insert( const Comparable & x )
   {
	   if (root == null) {//!
//!	   if (root == nullptr)
		   root = new avlNode<T>(x, null, null);//!           root = new avlNode<T>(x, nullptr, nullptr);
	   } else {//!
		   root = root.insert (x);//!       root->insert( x );
	   }//!
   }


   /**
    * Remove x from the tree. Nothing is done if x is not found.
    */
   void remove( T x )//!
//!   void remove( const Comparable & x )
   {
       root = remove( x, root );//!
//!       remove( x, root );
   }



   /**
    * Internal method to insert into a subtree.
    * x is the item to insert.
    * t is the node that roots the subtree.
    * Set the new root of the subtree.
    */
   avlNode<T> insert( T x, avlNode<T> t )//!
//!   void insert( const Comparable & x, avlNode * & t )
   {
	   ActivationRecord arec = activate(getClass());//!
	   arec.param("x", x).refParam("t", t).breakHere("entered insert");//!
	   if( t == null)//!
//!	   if( t == nullptr )
	   {//!
		   arec.breakHere("subtree is null, create the node");//!
		   return new avlNode<T>( x, null, null );//!
//!		   t = new avlNode{ x, nullptr, nullptr };
	   }//! 
	   else if( x.compareTo(t.value)< 0 )//!
//!       else if( x < t->value )
	   {//!
		   arec.breakHere("Go left.");//!
		   arec.highlight(t.leftChild);//!
		   t.leftChild = insert( x, t.leftChild);//!
//!           insert( x, t->leftChild );
		   arec.refParam("t", t);//!
		   return t;//!
	   }//!
	   else if( x.compareTo(t.value)> 0 )//!
//!       else if( t->value < x )
	   {//!
		   arec.breakHere("Go right.");//!
		   arec.highlight(t.rightChild);//!
		   t.rightChild = insert( x, t.rightChild);//!
//!           insert( x, t->rightChildChild );
		   arec.refParam("t", t);//!
		   return t;//!
	   }//!
       else
       {//!
    	   arec.breakHere("We found a duplicate item. Do nothing.");//!
    	   return t; // Duplicate; do nothing
       }//!
      
   }


   /**
    * Internal method to remove from a subtree.
    * x is the item to remove.
    * t is the node that roots the subtree.
    * Set the new root of the subtree.
    */
   avlNode<T> remove( T x, avlNode<T> t)//!
//!   void remove( const Comparable & x, avlNode * & t )
   {
	   if (t != null)
		   return null; //**t.remove(x);
	   return null;
   }



   /**
    * Internal method to test if an item is in a subtree.
    * x is item to search for.
    * t is the node that roots the subtree.
    */
   boolean contains( T x, avlNode<T> t )//!
//!   bool contains( const Comparable & x, avlNode *t ) const
   {
	  ActivationRecord arec = activate(getClass());//!
	  if (t != null) arec.highlight(t);//!
	  boolean result = false;//!
	  arec.param("x", x).refParam("t", t).breakHere("entered recursive contains");//!
      if ( t == null ) {//!
//!	  if( t == nullptr )
    	  arec.breakHere("Can't find x - it's not in the tree.");//!
         return false;
      } else if( x .compareTo(t.value) < 0 ) {//!
//!      else if( x < t->value )
    	  arec.breakHere("Look to the left.");//!
    	 result = contains( x, t.leftChild );//!
//!         return contains( x, t->leftChild );
    	 arec.breakHere("returning");//!
    	 return result;//!
      } else if( x.compareTo(t.value) > 0 ) {//!
//!      else if( t->value < x )
    	  arec.breakHere("Look to the right.");//!
    	 result = contains( x, t.rightChild );//!
//!         return contains( x, t->rightChild );
    	 arec.breakHere("returning");//!
    	 return result;//!
      }//!
      else
      {//!
    	  arec.breakHere("Found it!");//!
         return true;    // Match
      }//!
   }

   /****** NONRECURSIVE VERSION*************************
   bool contains( const Comparable & x, avlNode *t ) const
   {
       while( t != nullptr )
           if( x < t->value )
               t = t->leftChild;
           else if( t->value < x )
               t = t->rightChild;
           else
               return true;    // Match

       return false;   // No match
   }
   *****************************************************/

   /**
    * Internal method to make subtree empty.
    */
   void makeEmpty( avlNode<T>  t )//!
//!   void makeEmpty( avlNode * & t )
   {
	   if( t != null )//!
//!       if( t != nullptr )
       {
		   makeEmpty( t.leftChild );//!
//!           makeEmpty( t->leftChild );
		   makeEmpty( t.rightChild );//!
//!           makeEmpty( t->rightChild );
//!           delete t;
       }
	   t = null;//!
//!       t = nullptr;
   }



}//!