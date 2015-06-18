package edu.odu.cs.cs361.animations;//!

import static edu.odu.cs.AlgAE.Server.LocalServer.activate;//!

import java.util.NoSuchElementException;//!

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
    
    
    class BstIterator
//!    : public std::iterator<std::bidirectional_iterator_tag, Comparable> {
//!  public:
    {//!
    BstIterator()
//!    : nodePtr(nullptr), tree(nullptr)
    {
        nodePtr = null;//!
        tree = null;//!
    }
    
    // comparison operators. just compare node pointers
//!    bool operator== (const BstIterator& rhs) const;
    
//!    bool operator!= (const BstIterator& rhs) const;
    
    // dereference operator. return a reference to
    // the value pointed to by nodePtr
//!    const Comparable& operator* () const;
    
    // preincrement. move forward to next larger value
//!    BstIterator& operator++ ()
    void advance()//!
    {
        ActivationRecord arec = activate(getClass());//!
        arec.breakHere("begin operator++");//!
        BinaryNode<T> p;//!      BinaryNode<Comparable> *p;

        if (nodePtr == null)//!      if (nodePtr == nullptr)
        {
          // ++ from end(). get the root of the tree
            arec.breakHere("at end");//!
            nodePtr = tree.root;//!          nodePtr = tree->root;

          // error! ++ requested for an empty tree
            arec.breakHere("Is the tree empty?");//!
          if (nodePtr == null)//!          if (nodePtr == nullptr)
              {arec.breakHere("The tree is empty.");//!
            throw new NoSuchElementException();}//!            throw UnderflowException { };

          // move to the smallest value in the tree,
          // which is the first node inorder
            arec.breakHere("Start running to the left.");//!
          while (nodePtr.left != null) {//!          while (nodePtr->left != nullptr) {
              arec.breakHere("Step to the left.");//!
              nodePtr = nodePtr.left;//!            nodePtr = nodePtr->left;
          }
          arec.breakHere("We've reached the begin() position.");//!
        }
      else
          arec.breakHere("Any unvisited children?");//!
          if (nodePtr.right != null)//!        if (nodePtr->right != nullptr)
          {
            // successor is the farthest left node of
            // right subtree
              arec.breakHere("Step to the right");//!
              nodePtr = nodePtr.right;//!            nodePtr = nodePtr->right;

              arec.breakHere("Then run to the left.");//!
              while (nodePtr.left != null) {//!            while (nodePtr->left != nullptr) {
                  arec.breakHere("Step to the left.");//!
                  nodePtr = nodePtr.left;//!              nodePtr = nodePtr->left;
            }
              arec.breakHere("Done moving left.");//!
          }
        else
          {
            // have already processed the left subtree, and
            // there is no right subtree. move up the tree,
            // looking for a parent for which nodePtr is a left child,
            // stopping if the parent becomes NULL. a non-NULL parent
            // is the successor. if parent is NULL, the original node
            // was the last node inorder, and its successor
            // is the end of the list
            arec.breakHere("Start moving up.");//!
            p = nodePtr.parent;//!            p = nodePtr->parent;
            arec.refVar("p", p).breakHere("Until we backtrack along a left child.");//!
            while (p != null && nodePtr == p.right)//!            while (p != nullptr && nodePtr == p->right)
              {
                arec.breakHere("We just backtracked over a right child. Keep going up..");//!
                nodePtr = p;
                arec.breakHere("Parent (p) goes up too.");//!
                p = p.parent;//!                p = p->parent;
              }
            arec.breakHere("Done moving up");//!

            // if we were previously at the right-most node in
            // the tree, nodePtr = nullptr, and the iterator specifies
            // the end of the list
            nodePtr = p;
            arec.breakHere("We're at the next node.");//!
          }

//!      return *this;
    }
    
    // postincrement
//!    BstIterator operator++ (int);
    
    // predecrement. move backward to largest value < current value
//!    BstIterator  operator-- ();
    
    // postdecrement
//!    BstIterator  operator-- (int);
    
//!  private:
//!    friend class BinarySearchTree<Comparable>;
    
    // nodePtr is the current location in the tree. we can move
    // freely about the tree using left, right, and parent.
    // tree is the address of the stree object associated
    // with this iterator. it is used only to access the
    // root pointer, which is needed for ++ and --
    // when the iterator value is end()
    BinaryNode<T> nodePtr;//!    const BinaryNode<Comparable> *nodePtr;
    BinarySearchTree<T> tree;//!    const BinarySearchTree<Comparable> *tree;
    
    // used to construct an iterator return value from
    // a node pointer
//!    BstIterator (const BinaryNode<Comparable> *p,
//!                 const BinarySearchTree<Comparable> *t);
    BstIterator (BinaryNode<T> p,//!
            BinarySearchTree<T> t) //!
    {//!
        nodePtr = p;//!
        tree = t;//!
    }//!
    }//!  };
    
    
    
    
    
//!    typedef BstIterator const_iterator;
//!    typedef const_iterator iterator;
        
    BinaryNode<T> root;//!
    BinarySearchTree() { root = null; }//!
    
//!    BinarySearchTree( ) : root{ nullptr }
//!    {
        
//!    }
    
    
   /**
   *  Copy constructor
   */
//!   BinarySearchTree( BinarySearchTree<T> rhs )//!
//!    BinarySearchTree( const BinarySearchTree & rhs ) : root{ nullptr }
//!    {
//!       root = clone( rhs.root );
//!    }
    
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
       Search for item. if found, return an iterator pointing
       at it in the tree; otherwise, return end()
   */
//!    const_iterator find(const Comparable& item) const
    void find(T item, BstIterator iter)//!
    {
        BinaryNode<T> t = root;
        while (t != null && !t.element.equals(item))
        {
            if (item .compareTo(t.element) < 0)
            {
                t = t.left;
            }
            else
            {
                t = t.right;
            }
        }
        if (t != null)
        {
//!            return new BstIterator(t, this);
            iter.tree = this;//!
            iter.nodePtr = t;//!
        }
        else
        {
            end(iter);//!            return end();
        }
    }

    /**
     * return an iterator pointing to the first item (inorder)
     */
//!    const_iterator begin() const
    void begin(BstIterator iter)
    {
        ActivationRecord arec = activate(getClass());//!
        arec.breakHere("begin()");//!
        BstIterator result = new BstIterator(findMin(root), this);//!        return const_iterator(findMin(root), this);
        iter.tree = result.tree;//!
        iter.nodePtr = result.nodePtr;//!
    }

    /**
     * return an iterator pointing just past the end of
     * the tree data
     */
//!    const_iterator end() const
    void end(BstIterator iter)
    {
        ActivationRecord arec = activate(getClass());//!
        arec.breakHere("end()");//!
//!        return const_iterator(nullptr, this);
        iter.tree = this;//!
        iter.nodePtr = null;//!
    }
    
    
    
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
       root = insert (x, root, null);//!       insert( x, root, nullptr );
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
    * par is the parent of t
    * Set the new root of the subtree.
    */
   BinaryNode<T> insert( T x, BinaryNode<T> t,  BinaryNode<T> par)//!
//!   void insert( const Comparable & x, BinaryNode * & t, BinaryNode * par )
   {
       ActivationRecord arec = activate(getClass());//!
       arec.param("x", x).refParam("t", t).breakHere("entered insert");//!
       if( t == null)//!
//!    if( t == nullptr )
       {//!
           arec.breakHere("subtree is null, create the node");//!
           return new BinaryNode<T>( x, null, null, par);//!
//!        t = new BinaryNode( x, nullptr, nullptr, par );
       }//! 
       else if( x.compareTo(t.element)< 0 )//!
//!       else if( x < t->element )
       {//!
           arec.breakHere("Go left.");//!
           arec.highlight(t.left);//!
           t.left = insert( x, t.left, t);//!
//!           insert( x, t->left, t );
           arec.refParam("t", t);//!
           return t;//!
       }//!
       else if( x.compareTo(t.element)> 0 )//!
//!       else if( t->element < x )
       {//!
           arec.breakHere("Go right.");//!
           arec.highlight(t.right);//!
           t.right = insert( x, t.right, t);//!
//!           insert( x, t->right, t );
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
//!    if( t == nullptr )
       {//!
           arec.breakHere("Item not found; do nothing");//!
           return t;//!  
//!           return;// Item not found; do nothing
       }//!
       if( x.compareTo(t.element)< 0 )//!
//!       if( x < t->element )
       {//!
        
           arec.breakHere("Go left");//!
           arec.highlight(t.left);//!
           t.left = remove( x, t.left );//!
//!           remove( x, t->left );
           arec.refParam("t", t);//!
        
       }//!
       else if( x.compareTo(t.element)> 0 )//!
//!       else if( t->element < x )
       {//!
           arec.breakHere("Go right");//!
           arec.highlight(t.right);//!
           t.right = remove( x, t.right );//!
//!           remove( x, t->right );
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
//!        BinaryNode *oldNode = t;
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
       ActivationRecord arec = activate(getClass());//!
       arec.refParam("t", t).breakHere("begin findMin");//!
       if(t==null)//!
//!       if( t == nullptr )
       {//!
           arec.breakHere("tree is empty");//!
           return null;//!
//!           return nullptr;
       }//!
       arec.breakHere("Can we move down to the left?");//!
       if( t.left == null )//!
//!       if( t->left == nullptr )
           {arec.breakHere("No, we are at the min.");//!
           return t;
           }//!
       arec.breakHere("Move down to the left.");//!
       BinaryNode<T> result = findMin( t.left );//!
//!       return findMin( t->left );
       arec.breakHere("Returning...");//!
       return result;//!
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
//!   if( t == nullptr )
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

public BinarySearchTree<T>.BstIterator quickEnd() {//!
    return new BstIterator(null, this);//!
}//!
   
   
   

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




//!};

//!#endif



}//!
