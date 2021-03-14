#ifndef MSET_H
#define MSET_H

//
//      simplified implementation of (multi)set data type
//
//      Described in Chapter 12 of
//      Data Structures in C++ using the STL
//      Published by Addison-Wesley, 1997
//      Written by Tim Budd, budd@cs.orst.edu
//      Oregon State University
//
//      SJZ: Changed from set to multi-set to better display tree's
//         capabilities
#include "avl.h"


template <class T>
class multiset {
public:
  typedef inorderTreeTraversal<T> iterator;
  
  // constructor
  multiset () 
    { root = 0; }

  // tree traversing operations
  void insert (T &);
  void erase (iterator p);
  
  int count (T & testElement);
  iterator lower_bound (T & testElement);
  iterator upper_bound (T & testElement);

  iterator begin();
  iterator end();

  void clear();
  
  // other operations
  bool empty () { return root == 0; }
  int size ();
  void swap (multiset<T> & right) { std::swap (root, right.root); }

protected:
  // root of binary search tree
  avlNode<T> * root;

  // internal method used in removal
  avlNode<T> * remove (avlNode<T> *, T &);
};


template <class T> int multiset<T>::size()
  // count the number of elements in multiset
{
  if (root == 0)
    return 0;
  else
    return root->size();
}

template <class T> 
inorderTreeTraversal<T> multiset<T>::lower_bound (T & element)
  // find first occurrence of element in collection
{
  if (root == 0)
    return end();
  else
    return root->lower_bound(element);
}

template <class T> 
inorderTreeTraversal<T> multiset<T>::upper_bound (T & element)
  // find position after last occurrence of element in collection
{
  if (root == 0)
    return end();
  else
    return root->upper_bound(element, end());
}

template <class T> int multiset<T>::count (T & element)
        // count number of occurrences of element
{
  iterator first = lower_bound(element);
  iterator last = upper_bound(element);
  int counter = 0;
  // count number of elements between lower and upper
  while (first != last) {
    counter++;
    first++;
  }
  return counter;
}

template <class T> void multiset<T>::insert (T & newElement)
  // insert a new element into a multiset
{
  if (root == 0)
    {
      // create a new avlNode
      root = new avlNode<T> (newElement, 0);
    }
  else
    {
      root = root->insert(newElement);
    }
}


template <class T>
avlNode<T> * multiset<T>::remove (avlNode<T> * current, T & testElement)
  // remove all instances of testElement from collection
{
  if (current != 0) {
    avlNode<T> * pa = current->parent;
    if (testElement < current->value)
      current->leftChild = 
        remove (current->leftChild, testElement);
    else if (current->value < testElement)
      current->rightChild = 
        remove (current->rightChild, testElement);
    else {              // found an item to remove
      avlNode<T> * result
        = current->merge(current->leftChild, 
                         remove (current->rightChild, testElement));
      delete current;
      result->parent = pa;
      return result;
    }
  }
  return current;
}



template <class T>
void multiset<T>::erase (iterator p)
{ 
  avlNode<T>::remove (p, root);
}


template <class T>
typename multiset<T>::iterator multiset<T>::begin()
{
  return inorderTreeTraversal<T>(root, true);
}


template <class T>
typename multiset<T>::iterator multiset<T>::end()
{
  return inorderTreeTraversal<T>();
}


template <class T>
void multiset<T>::clear()
{
  if (root != 0)
      root->release();
  delete root;
}


#endif
