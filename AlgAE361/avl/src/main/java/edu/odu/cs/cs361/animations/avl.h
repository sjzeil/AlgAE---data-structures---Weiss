#ifndef AVL_H
#define AVL_H

//
//      AVL tree
//
//      Described in Chapter 14 of
//      Data Structures in C++ using the STL
//      Published by Addison-Wesley, 1997
//      Written by Tim Budd, budd@cs.orst.edu
//      Oregon State University
//
//      Modifications by S Zeil, Old Dominion UNiversity




template <class T>
class inorderTreeTraversal;


template <class T>
class avlNode 
{
public:
  avlNode (const T & v, avlNode<T> * par)
    : value(v), parent(par), leftChild(0), rightChild(0), balanceFactor(0) { }
  
  // operations

  void release ();
  int count (const T & testElement) const;
  avlNode<T>* insert (const T&);
  int size () const;

  inorderTreeTraversal<T> lower_bound(const T &);
  inorderTreeTraversal<T> upper_bound(const T &);
  
  static void remove (inorderTreeTraversal<T> p, avlNode<T>* root);

  // Data fields
  T value;
  avlNode<T> * parent;
  avlNode<T> * leftChild;
  avlNode<T> * rightChild;

protected:
  short balanceFactor;

  avlNode<T>* singleRotateLeft ();
  avlNode<T>* singleRotateRight ();
  avlNode<T>* balance ();

  static void avlNode<T>::remove (avlNode<T>*& t);
};



template <class T>
class inorderTreeTraversal 
{
public:
  typedef inorderTreeTraversal<T> iterator;
  
  // constructor
  inorderTreeTraversal (): current(0) {}
  inorderTreeTraversal (avlNode<T> * s, bool slide);
  inorderTreeTraversal (const iterator & itr)  
    : current (itr.current) { }
  
  // iterator protocol
  inorderTreeTraversal<T> & operator ++();
  inorderTreeTraversal<T> operator ++(int);
  T & operator * ()
    { return current->value; }
  void operator = (const iterator & itr)
    { current = itr.current; }
  bool operator == (const iterator & rhs) const
    { return current == rhs.current; }
  
protected:
  friend class avlNode<T>;
  avlNode<T> * current;
};


template <class T>
inorderTreeTraversal<T> inorderTreeTraversal<T>::operator ++ (int)
{
  // clone ourselves using the copy constructor
  inorderTreeTraversal<T> clone (*this);
  operator ++ ();               // increment ourselves
  return clone;                 // return copy
}


template <class T>
inorderTreeTraversal<T>& inorderTreeTraversal<T>::operator ++ ()
{
  if (current != 0)
    {
      if (current->rightChild != 0)
        { 
          // step to the right, then run down to the left
          current = current->rightChild;
          while (current->leftChild != 0) 
            current = current->leftChild;  
        }
      else
        { 
          // move up until we retrace a left edge
          avlNode<T>* cameFrom;
          do { 
             cameFrom = current;
             current = current->parent; 
          } while (current != 0 
                   && cameFrom != current->leftChild);
        }
    }
  return *this;
}


template <class T>
inorderTreeTraversal<T>::inorderTreeTraversal (avlNode<T> * root, bool slide)
  // initialize inorder traversal
{
  current = root;
  // perform a left slide
  while (slide && current && current->leftChild)
    current = current->leftChild;
}


template <class T> 
int avlNode<T>::size() const
  // count number of elements in subtree rooted at avlNode
{
  int count = 1;
  if (leftChild != 0)
    count += leftChild.size();
  if (rightChild != 0)
    count += rightChild.size();
  return count;
}


template <class T> 
inorderTreeTraversal<T> avlNode<T>::lower_bound 
(const T & element)
{ 
 if (value < element)
   {
    if (rightChild != 0)
      return rightChild->lower_bound (element);
    else
      return inorderTreeTraversal<T>(); // not found at all
   }
 else if (element < value)
   {
    // check left child
    if (leftChild != 0)
        // see if it is found along left child
      return leftChild->lower_bound (element);
    else
      return inorderTreeTraversal<T>();
   }
  else
   {
       // We have found it
    return inorderTreeTraversal<T>(this, false); // make iterator that points to us
   }
}

template <class T> 
inorderTreeTraversal<T> avlNode<T>::upper_bound 
  (const T & element)
{
  if (value < element)
    if (rightChild != 0)
      return rightChild->upper_bound (element);
    else
      return inorderTreeTraversal<T>(); // not found at all
  if (leftChild != 0) {
    inorderTreeTraversal<T> result = leftChild->upper_bound (element);
    if (result != inorderTreeTraversal<T>())
      return result;
  }
  return inorderTreeTraversal<T>();
}


template <class T>
avlNode<T>* avlNode<T>::singleRotateLeft ()
  // perform single rotation rooted at current node
{
  // [I have renamed the nodes from what appears in the text to follow
  //  the convention that U is the Unbalanced node, H is the Higher child, 
  //  and I is the interior grandchild - S Zeil]
  avlNode<T>* U = this;
  avlNode<T>* H = U->rightChild;
  avlNode<T>* I = H->leftChild;
  
  U->rightChild = I;
  H->leftChild = U;
  if (I != 0)
    I->parent = U;
  H->parent = U->parent;
  U->parent = H;

  // now update the balance factors
  int Ubf = U->balanceFactor;
  int Hbf = H->balanceFactor;
  if (Hbf <= 0) {
    if (Ubf >= 1)  
      H->balanceFactor = Hbf - 1;
    else  
      H->balanceFactor = Ubf + Hbf - 2;

    U->balanceFactor = Ubf - 1;
  }
  else {
    if (Ubf <= Hbf)  
      H->balanceFactor = Ubf - 2;
    else  
      H->balanceFactor = Hbf - 1;
    U->balanceFactor = (Ubf - Hbf) - 1;
  }
  return H;
}

template <class T>
avlNode<T>* avlNode<T>::singleRotateRight ()
  // perform single rotation rooted at current node
{
  // [I have renamed the nodes from what appears in the text to follow
  //  the convention that U is the Unbalanced node, H is the Higher child, 
  //  and I is the interior grandchild - S Zeil]
  avlNode<T>* U = this;
  avlNode<T>* H = U->leftChild;
  avlNode<T>* I = H->rightChild;
  
  U->leftChild = I;
  H->rightChild = U;
  if (I != 0)
    I->parent = U;
  H->parent = U->parent;
  U->parent = H;

  // now update the balance factors
  int Ubf = U->balanceFactor;
  int Hbf = H->balanceFactor;
  if (Hbf >= 0) {
    if (Ubf <= 1)  
      H->balanceFactor = Hbf + 1;
    else  
      H->balanceFactor = Ubf + Hbf + 2;

    U->balanceFactor = Ubf + 1;
  }
  else {
    if (Ubf <= Hbf)  
      H->balanceFactor = Ubf + 2;
    else  
      H->balanceFactor = Hbf + 1;
    U->balanceFactor = (Ubf - Hbf) + 1;
  }
  return H;
}


template <class T>
avlNode<T>* avlNode<T>::balance ()
{  // balance tree rooted at node
   //  using single or double rotations as appropriate
  if (balanceFactor < 0) {
    if (leftChild->balanceFactor <= 0)
      // perform single rotation
      return singleRotateRight();
    else {
      // perform double rotation
      leftChild = leftChild->singleRotateLeft();
      return singleRotateRight();
    }
  }
  else {
    if (rightChild->balanceFactor >= 0)
      return singleRotateLeft();
    else {
      // perform double rotation
      rightChild = rightChild->singleRotateRight();
      return singleRotateLeft();
    }
  }
}

  


template <class T> 
avlNode<T>* avlNode<T>::insert (const T& val)
  // insert a new element into balanced AVL tree
{
  if (val < value) { // insert into left subtree
     if (leftChild != 0) {
        int oldbf = leftChild->balanceFactor;
      leftChild = leftChild->insert (val);
      // check to see if tree grew 
      if ((leftChild->balanceFactor != oldbf) &&
          leftChild->balanceFactor)
         balanceFactor--;
      }
    else {
       leftChild = new avlNode(val, this);
       balanceFactor--;
    }
  }
  else { // insert into right subtree
     if (rightChild != 0) {
        int oldbf = rightChild->balanceFactor;
      rightChild = rightChild->insert (val);
      // check to see if tree grew 
      if ((rightChild->balanceFactor != oldbf) &&
          rightChild->balanceFactor)
         balanceFactor++;
      }
    else {
       rightChild = new avlNode(val, this);
       balanceFactor++;
    }
  }

  // check if we are now out of balance, if so balance
  if ((balanceFactor < -1) || (balanceFactor > 1))
    return balance();
  else
    return this;
}




template <class T>
void avlNode<T>::remove (avlNode<T>*& t)
{
  if (t != 0)
    {
      if (t->leftChild == 0)
        {
          if (t->rightChild == 0)
            {
              // case 1: removing a leaf - just do it.
              delete t;
          else
            {
              // case 2: avlNode has only a right child - replace
              //    this avlNode by its child
              avlNode<T>* child = t->rightChild;
              child->parent = t->parent;
              delete t;
              t = child;
            }
        }
      else if (t->rightChild == 0)
        {
          // case 3: avlNode has only a left child - replace
          //    this avlNode by its child
          avlNode<T>* child = t->leftChild;
          child->parent = t->parent;
          delete t;
          t = child;
        }
      else
        {
          // case 4: avlNode has two children - swap with the smallest
          //    value from the right subtree
          avlNode<T>* minFromRight = t->rightChild;
          while (minFromRight->leftChild != 0)
            minFromRight = minFromRight->leftChild;
          t->value = minFromRight->value;
          if (minFromRight == t->rightChild)
            remove (t->rightChild);
          else
            remove (minFromRight->parent->leftChild);
        }
    }
}



template <class T>
void avlNode<T>::release ()
{
  if (leftChild != 0)
    {
      leftChild->release();
      delete leftChild;
    }
  if (rightChild != 0)
    {
      rightChild->release();
      delete rightChild;
    }
}




template <class T>
void avlNode<T>::remove (inorderTreeTraversal<T> p, avlNode<T>* root)
{ 
  if (p.current == root)
    remove(root);
  else if (p.current->parent->leftChild == p.current)
    remove(p.current->parent->leftChild);
  else
    remove(p.current->parent->rightChild);
}

        

#endif
