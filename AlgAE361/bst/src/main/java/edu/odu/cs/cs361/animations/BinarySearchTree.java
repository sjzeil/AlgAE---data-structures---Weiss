package edu.odu.cs.cs361.animations;//!

import static edu.odu.cs.AlgAE.Server.LocalServer.activate;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!
//!
//!

//!#ifndef BINARY_SEARCH_TREE_H
//!#define BINARY_SEARCH_TREE_H

//!#include "dsexceptions.h"
//!#include <algorithm>
//!using namespace std;       

// BinarySearchTree class
//
// CONSTRUCTION: zero parameter
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// bool contains( x )     --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as warranted


public class BinarySearchTree<T extends Comparable<T>> {//!
	
	
//!template <typename Comparable>
//!class BinarySearchTree
//!{
//! public:
		
	BinaryNode<T> root;//!
	BinarySearchTree() { root = null; }//!
	
//!    BinarySearchTree( ) : root{ nullptr }
//!    {
    	
//!    }
	
	
   /**
   *  Copy constructor
   */
   BinarySearchTree( BinarySearchTree<T> rhs )//!
//!    BinarySearchTree( const BinarySearchTree & rhs ) : root{ nullptr }
    {
       root = clone( rhs.root );
    }
	
   /**
    * Move constructor
    */
//!   BinarySearchTree( BinarySearchTree && rhs ) : root{ rhs.root }
//!   {
//!      rhs.root = nullptr;
//!   }
	

   /**
    * Destructor for the tree
   */
   void destroy()//!
//!   ~BinarySearchTree( )
   {
	   ActivationRecord arec = activate(this);//!
	   arec.breakHere("delete binary tree");//!
	
       makeEmpty( );
       arec.breakHere("done");//!
   }
        
   /**
    * Copy assignment
   */
//!   BinarySearchTree & operator=( const BinarySearchTree & rhs )
//!   {
//!       BinarySearchTree copy = rhs;
//!       std::swap( *this, copy );
//!       return *this;
//!   }

   /**
    * Move assignment
    */
//!   BinarySearchTree & operator=( BinarySearchTree && rhs )
//!   {
//!       std::swap( root, rhs.root );       
//!       return *this;
//!   }


   /**
    * Find the smallest item in the tree.
    * Throw UnderflowException if empty.
    */
   T findMin( )//!
//!   const Comparable & findMin( ) const
   {
       if( isEmpty( ) )
    	   throw  new RuntimeException ("underflow");//!
//!           throw UnderflowException{ };
       return findMin( root ).element;//!
//!       return findMin( root )->element;
   }


   /**
    * Find the largest item in the tree.
    * Throw UnderflowException if empty.
    */
   T findMax( )//!
//!   const Comparable & findMax( ) const
   {
       if( isEmpty( ) )
    	   throw  new RuntimeException ("underflow");//!
//!           throw UnderflowException{ };
       return findMax( root ).element;//!
//!       return findMax( root )->element;
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
    * Print the tree contents in sorted order.
    */

//!   void printTree( ostream & out = cout ) const
//!   {
//!       if( isEmpty( ) )
//!           out << "Empty tree" << endl;
//!       else
//!           printTree( root, out );
//!   }

   /**
    * Make the tree logically empty.
    */
   void makeEmpty( )
   {
       makeEmpty( root );
   }

   /**
    * Insert x into the tree; duplicates are ignored.
    */
   void insert( T  x )//!
//!   void insert( const Comparable & x )
   {
	   root = insert (x, root);//!       insert( x, root );
   }

   /**
    * Insert x into the tree; duplicates are ignored.
    */
//!   void insert( Comparable && x )
//!   {
//!       insert( std::move( x ), root );
//!   }

   /**
    * Remove x from the tree. Nothing is done if x is not found.
    */
   void remove( T x )//!
//!   void remove( const Comparable & x )
   {
       root = remove( x, root );//!
//!       remove( x, root );
   }

//!private:
//!    struct BinaryNode
//!    {
//!        Comparable element;
//!        BinaryNode *left;
//!        BinaryNode *right;

//!        BinaryNode( const Comparable & theElement, BinaryNode *lt, BinaryNode *rt )
//!          : element{ theElement }, left{ lt }, right{ rt } { }
        
//!        BinaryNode( Comparable && theElement, BinaryNode *lt, BinaryNode *rt )
//!          : element{ std::move( theElement ) }, left{ lt }, right{ rt } { }
//!    };

//!    BinaryNode *root;


   /**
    * Internal method to insert into a subtree.
    * x is the item to insert.
    * t is the node that roots the subtree.
    * Set the new root of the subtree.
    */
   BinaryNode<T> insert( T x, BinaryNode<T> t )//!
//!   void insert( const Comparable & x, BinaryNode * & t )
   {
	   ActivationRecord arec = activate(getClass());//!
	   arec.param("x", x).refParam("t", t).breakHere("entered insert");//!
	   if( t == null)//!
//!	   if( t == nullptr )
	   {//!
		   arec.breakHere("subtree is null, create the node");//!
		   return new BinaryNode<T>( x, null, null );//!
//!		   t = new BinaryNode{ x, nullptr, nullptr };
	   }//! 
	   else if( x.compareTo(t.element)< 0 )//!
//!       else if( x < t->element )
	   {//!
		   arec.breakHere("Go left.");//!
		   arec.highlight(t.left);//!
		   t.left = insert( x, t.left);//!
//!           insert( x, t->left );
		   arec.refParam("t", t);//!
		   return t;//!
	   }//!
	   else if( x.compareTo(t.element)> 0 )//!
//!       else if( t->element < x )
	   {//!
		   arec.breakHere("Go right.");//!
		   arec.highlight(t.right);//!
		   t.right = insert( x, t.right);//!
//!           insert( x, t->right );
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
    * Internal method to insert into a subtree.
    * x is the item to insert.
    * t is the node that roots the subtree.
    * Set the new root of the subtree.
    */
//!   void insert( Comparable && x, BinaryNode * & t )
//!   {
//!       if( t == nullptr )
//!           t = new BinaryNode{ std::move( x ), nullptr, nullptr };
//!       else if( x < t->element )
//!           insert( std::move( x ), t->left );
//!       else if( t->element < x )
//!           insert( std::move( x ), t->right );
//!       else
//!           ;  // Duplicate; do nothing
//!   }


   /**
    * Internal method to remove from a subtree.
    * x is the item to remove.
    * t is the node that roots the subtree.
    * Set the new root of the subtree.
    */
   BinaryNode<T> remove( T x, BinaryNode<T> t)//!
//!   void remove( const Comparable & x, BinaryNode * & t )
   {
	   ActivationRecord arec = activate(getClass());//!
	   arec.param("x", x).refParam("t", t).breakHere("begin remove");//!
	   if( t == null)//!
//!	   if( t == nullptr )
	   {//!
		   arec.breakHere("Item not found; do nothing");//!
	       return t;//!  
//!	          return;// Item not found; do nothing
	   }//!
	   if( x.compareTo(t.element)< 0 )//!
//!       if( x < t->element )
	   {//!
		
		   arec.breakHere("Go left");//!
		   arec.highlight(t.left);//!
		   t.left = remove( x, t.left );//!
//!		      remove( x, t->left );
		   arec.refParam("t", t);//!
		
	   }//!
	   else if( x.compareTo(t.element)> 0 )//!
//!       else if( t->element < x )
	   {//!
		   arec.breakHere("Go right");//!
		   arec.highlight(t.right);//!
		   t.right = remove( x, t.right );//!
//!		      remove( x, t->right );
		   arec.refParam("t", t);//!
	   }//!
       else if( t.left != null && t.right != null )//!
//!       else if( t->left != nullptr && t->right != nullptr ) // Two children
       {
    	   arec.breakHere("Find minimum item in t's right");//!
    	   t.element = findMin( t.right ).element;//!
//!           t->element = findMin( t->right )->element;
    	   arec.breakHere("Remove the minimum item");//!
    	   t.right  = remove( t.element, t.right);//!
//!           remove( t->element, t->right );
           arec.refParam("t", t);//!
       }
       else
       {
    	   arec.breakHere("Delete t");//!
//!    	   BinaryNode *oldNode = t;
    	   t = ( t.left != null ) ? t.left : t.right;//!
//!           t = ( t->left != nullptr ) ? t->left : t->right;
//!           delete oldNode;
       }
	   arec.breakHere("Done.");//!
	   return t;//!
	
   }


   /**
    * Internal method to find the smallest item in a subtree t.
    * Return node containing the smallest item.
    */
   BinaryNode<T> findMin( BinaryNode<T> t )//!
//!   BinaryNode * findMin( BinaryNode *t ) const
   {
	   if(t==null)//!
//!       if( t == nullptr )
	   {//!
		   return null;//!
//!		      return nullptr;
	   }//!
	   if( t.left == null )//!
//!       if( t->left == nullptr )
           return t;
	   return findMin( t.left );//!
//!       return findMin( t->left );
   }



   /**
    * Internal method to find the largest item in a subtree t.
    * Return node containing the largest item.
    */
   BinaryNode<T> findMax( BinaryNode<T> t )//!
//!   BinaryNode * findMax( BinaryNode *t ) const
   {
	   if( t != null )//!
//!       if( t != nullptr )
		   while( t.right != null )//!
//!           while( t->right != nullptr )
			   t = t.right;//!
//!               t = t->right;
       return t;
   }

   /**
    * Internal method to test if an item is in a subtree.
    * x is item to search for.
    * t is the node that roots the subtree.
    */
   boolean contains( T x, BinaryNode<T> t )//!
//!   bool contains( const Comparable & x, BinaryNode *t ) const
   {
	  ActivationRecord arec = activate(getClass());//!
	  if (t != null) arec.highlight(t);//!
	  boolean result = false;//!
	  arec.param("x", x).refParam("t", t).breakHere("entered recursive contains");//!
      if ( t == null ) {//!
//!	  if( t == nullptr )
    	  arec.breakHere("Can't find x - it's not in the tree.");//!
         return false;
      } else if( x .compareTo(t.element) < 0 ) {//!
//!      else if( x < t->element )
    	  arec.breakHere("Look to the left.");//!
    	 result = contains( x, t.left );//!
//!         return contains( x, t->left );
    	 arec.breakHere("returning");//!
    	 return result;//!
      } else if( x.compareTo(t.element) > 0 ) {//!
//!      else if( t->element < x )
    	  arec.breakHere("Look to the right.");//!
    	 result = contains( x, t.right );//!
//!         return contains( x, t->right );
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
   bool contains( const Comparable & x, BinaryNode *t ) const
   {
       while( t != nullptr )
           if( x < t->element )
               t = t->left;
           else if( t->element < x )
               t = t->right;
           else
               return true;    // Match

       return false;   // No match
   }
   *****************************************************/

   /**
    * Internal method to make subtree empty.
    */
   void makeEmpty( BinaryNode<T>  t )//!
//!   void makeEmpty( BinaryNode * & t )
   {
	   if( t != null )//!
//!       if( t != nullptr )
       {
		   makeEmpty( t.left );//!
//!           makeEmpty( t->left );
		   makeEmpty( t.right );//!
//!           makeEmpty( t->right );
//!           delete t;
       }
	   t = null;//!
//!       t = nullptr;
   }

   /**
    * Internal method to print a subtree rooted at t in sorted order.
    */
//!   void printTree( BinaryNode *t, ostream & out ) const
//!   {
//!       if( t != nullptr )
//!       {
//!           printTree( t->left, out );
//!           out << t->element << endl;
//!           printTree( t->right, out );
//!       }
//!   }



   /**
    * Internal method to clone subtree.
    */
   BinaryNode<T> clone( BinaryNode<T> t )//!
//!   BinaryNode * clone( BinaryNode *t ) const
   {
       if( t == null )//!
//!	   if( t == nullptr )
	      return null;//!
//!          return nullptr;
       else
    	  return new BinaryNode<T>(t.element, clone( t.left ), clone( t.right )) ;//!
//!          return new BinaryNode{ t->element, clone( t->left ), clone( t->right ) };
   }

//!};

//!#endif



}//!