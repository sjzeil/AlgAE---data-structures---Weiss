/*
 * miniVector from Ford & Topp, Data Structures in C++ using STL, 2nd ed.
 * section 5-5
 */
package edu.odu.cs.cs361.animations;//!


import edu.odu.cs.zeil.AlgAE.ActivationRecord;//!
import edu.odu.cs.zeil.AlgAE.Animation;//!

public class miniVector {//!


//!#ifndef MINI_VECTOR
//!#define MINI_VECTOR

//!using namespace std;

//!template <typename T>
//!class miniVector
//!{
//!public:
//!  miniVector(int size = 0);
  // constructor.
  // Postconditions: allocates array with size number of elements
  // and capacity. elements are initialized to T(), the default
  // value for type T

//!  miniVector(const miniVector<T>& obj);
  // copy constructor
  // Postcondition: creates current vector as a copy of obj

//!  ~miniVector();
  // destructor
  // Postcondition: the dynamic array is destroyed

//!  miniVector& operator= (const miniVector<T>& rhs);
  // assignment operator.
  // Postcondition: current vector holds the same data
  // as rhs

//!  T& back();
  // return the element at the rear of the vector.
  // Precondition: the vector is not empty. if vector
  // is empty, throws the underflowError exception

//!  const T& back() const;
  // const version used when miniVector object is a constant

//!  T& operator[] (int i);
  // provides general access to elements using an index.
  // Precondition: 0 <= i < vSize. if the index is out
  // of range, throws the indexRangeError exception

//!  const T& operator[] (int i) const;
  // const version used when miniVector object is a constant

//!  void push_back(const T& item);
  // insert item at the rear of the vector.
  // Postcondition: the vector size is increased by 1

//!  void pop_back();
  // remove element at the rear of the vector.
  // Precondition: vector is not empty. if the vector is
  // empty, throws the underflowError exception

//!  int size() const;
  // return current list size

//!  bool empty() const;
  // return true if vector is empty and false otherwise

//!  int capacity() const;
  // return the current capacity of the vector

//!  void reserve (int newCapcity);
	// Ensure that this vector can contain up to newCpacity items without
	// requiring any new memory allocation.
	
//!  void resize (int newSize, const T& default = T());
	// Remove or add items at the end until the vector has newSize
	// items. If items must be added, use the value default for all
	// new items.
	
//!  void clear();
	// Empty the vector
	
//!private:
  int vCapacity;          // amount of available space
  int vSize;                      // number of elements in the list
  int[] vArr;//!  T *vArr;                                // the dynamic array

//!  void reserve(int n, bool copy);
  // called by public functions only if n > vCapacity. expands
  // the vector capacity to n elements, copies the existing
  // elements to the new space if copy == true, and deletes
  // the old dynamic array. throws the memoryAllocationError
  // exception if memory allocation fails
//!};

// set the capacity to n elements
//!template <typename T>
void reserve(int n, boolean copy)//!void miniVector<T>::reserve(int n, bool copy)
{
	   ActivationRecord arec = Animation.activate(this);//!
	int[] newArr;//!//!  T *newArr;
  int i;

  // allocate a new dynamic array with n elements
  arec.param("n", n).param("copy", copy).breakHere("starting reserve");//!
  newArr = new int[n];//!  newArr = new T[n];
  arec.refVar("newArr", newArr).breakHere("allocated new array");//!
//!  if (newArr == NULL)
//!    throw memoryAllocationError(
//!				"miniVector reserve(): memory allocation failure");

  // if copy is true, copy elements from the old list to the new list
  if (copy)
  {//!
	  arec.breakHere("copy data from old array");//!	  
    for(i = 0; i < vSize; i++)
      newArr[i] = vArr[i];
	  arec.breakHere("copied data from old array");//!	  
  }//!
  // delete original dynamic array. if vArr is NULL, the vector was
  // originally empty and there is no memory to delete
  if (vArr != null) {//!  if (vArr != NULL)
	  arec.breakHere("delete old array");//!	  
	  vArr = null;//!    delete [] vArr;
  }//!
  
  // set vArr to the value newArr. update vCapacity
  arec.breakHere("use the new array");//!	  
  vArr = newArr;
  arec.breakHere("update the capacity");//!	  
  vCapacity = n;
  arec.breakHere("done");//!	  
}

// constructor. initialize vSize and vCapacity.
// allocate a dynamic array of vSize integers
// and initialize the array with T()
//!template <typename T>
//!miniVector<T>::miniVector(int size):
miniVector (int size)//!
//!  vSize(0), vCapacity(0), vArr(NULL)
{
	vSize = vCapacity = 0;//!
	vArr = null;//!
  int i;

  // if size is 0, vSize/vCapacity are 0 and vArr is NULL.
  // just return
  if (size == 0)
    return;

  // set capacity to size. since we are building the vector,
  // copy is false
  reserve(size, false);
  // assign size to vSize
  vSize = size;

  // copy T() into each vector element
  for (i=0;i < vSize;i++)
	  vArr[i] = 0;//!    vArr[i] = T();
}

// copy constructor. make the current object a copy of obj.
// for starters, use initialization list to create an empty
// vector
//!template <typename T>
miniVector (miniVector obj)//!miniVector<T>::miniVector (const miniVector<T>& obj):
//!  vSize(0), vCapacity(0), vArr(NULL)
{
	ActivationRecord arec = Animation.activate(this);//!
	vSize = vCapacity = 0;//!
	vArr = null;
  int i;

  // if size is 0, vSize/vCapacity are 0 and vArr is NULL.
  // just return
  arec.refParam("obj", obj).breakHere("check if empty");//!	  
  if (obj.vSize == 0)
    return;

  // set capacity to obj.vSize. since we are building the vector,
  // copy is false
  arec.breakHere("reserve size in new vector");//!	  
  reserve(obj.vSize, false);
  // assign size to obj.vSize
  arec.breakHere("update size");//!	  
  vSize = obj.vSize;

  // copy items from the obj.vArr to the newly allocated array
  arec.breakHere("copy items");//!	  
  for (i = 0; i < vSize; i++)
    vArr[i] = obj.vArr[i];
  arec.breakHere("done");//!	  
}

// destructor. deallocate the dynamic array
//!template <typename T>
void destroy()//!miniVector<T>::~miniVector()
{
	ActivationRecord arec = Animation.activate(this);//!
	if (vArr != null)//!  if (vArr != NULL)
    // de-allocate memory for the array
  {	  arec.breakHere("delete array");//!	  
//!    delete [] vArr;
  vArr = null;}//!
  arec.breakHere("done");//!
}

// Empty the vector 
//!template <typename T>
void clear()//!miniVector<T>::clear()
{
	ActivationRecord arec = Animation.activate(this);//!
	vSize = 0;
	arec.breakHere("done");//!
}


// replace existing object (left-hand operand) by
// rhs (right-hand operand)
//!template <typename T>
void assign (miniVector rhs)//!miniVector<T>& miniVector<T>::operator= (const miniVector<T>& rhs)
{
	ActivationRecord arec = Animation.activate(this);//!
  int i;

  // check vCapacity to see if a new array must be allocated
  arec.refParam("rhs", rhs).breakHere("check the capacity");//!
  if (vCapacity < rhs.vSize)
    // make capacity of current object the size of rhs. don't
    // do a copy, since we will replace the old values
	{ arec.breakHere("reserve more space");//!
    reserve(rhs.vSize, false);
	}//!

  // assign current object to have same size as rhs
  arec.breakHere("copy the size");//!
  vSize = rhs.vSize;

  arec.breakHere("copy the items");//!
  // copy items from rhs.vArr to vArr
  for (i = 0; i < vSize; i++)
    vArr[i] = rhs.vArr[i];

  arec.breakHere("done");//!
//!  return *this;
}

// check vSize and throw an underflowError exception if the
// value is 0; otherwise return the element vArr[vSize-1]
//!template <typename T>
int back()//!T& miniVector<T>::back()
{
  if (vSize == 0)
	  throw new IndexOutOfBoundsException(//!    throw underflowError(
			 "miniVector back(): vector empty");

  return vArr[vSize-1];
}

//!template <typename T>
//!const T& miniVector<T>::back() const
//!{
//!  if (vSize == 0)
//!    throw underflowError(
//!			 "miniVector back(): vector empty");
//!
//!  return vArr[vSize-1];
//!}

// provides general access to array elements
//!template <typename T>
int get(int i)//!T& miniVector<T>::operator[] (int i)
{
  if (i < 0 || i >= vSize)
	throw new IndexOutOfBoundsException(//!     throw indexRangeError(
//!			  "miniVector: index range error", i, vSize);
			"miniVector: index range error"+ i + " " + vSize);//!

  return vArr[i];
}

// provides general access to array elements. constant version
//!template <typename T>
//!const T& miniVector<T>::operator[] (int i) const
//!{
//!  if (i < 0 || i >= vSize)
//!    throw indexRangeError(
//!			  "miniVector: index range error", i, vSize);

//!  return vArr[i];
//!}

// insure that list has sufficient capacity,
// add the new item to the list, and increment vSize
//!template <typename T>
void push_back (int item)//!void miniVector<T>::push_back(const T& item)
{
	   ActivationRecord arec = Animation.activate(this);//!
	   arec.param("item", item).breakHere("starting push_back");//!
  // if space is full, allocate more capacity
  if (vSize == vCapacity)
    {
	   arec.breakHere("we need more space");//!
      if (vCapacity == 0)
	// if capacity is 0, set capacity to 1.
	// set copy to false because there are
	// no existing elements
   	   {arec.breakHere("set capacity to 1");//!
	reserve(1,false);
   	   }//!
      else
	// double the capacity
  	   {arec.breakHere("double the capacity");//!
	reserve(2 * vCapacity, true);
  	   }//!
    }

  // add item to the list, update vSize
  arec.breakHere("add item");//!
  vArr[vSize] = item;
  arec.breakHere("update the size");//!
  vSize++;
  arec.breakHere("done with push_back");//!
}

// if not empty, just decrement the size
//!template <typename T>
void pop_back()//!void miniVector<T>::pop_back()
{
//!  if (vSize == 0)
//!    throw underflowError(
//!			 "miniVector pop_back(): vector is empty");

  vSize--;
}

//!template <typename T>
int size()//!int miniVector<T>::size() const
{
  return vSize;
}

//!template <typename T>
boolean empty()//!bool miniVector<T>::empty() const
{
  return vSize == 0;
}

//!template <typename T>
int capacity()//!int miniVector<T>:: capacity() const
{
  return vCapacity;
}


// Ensure that this vector can contain up to newCpacity items without
// requiring any new memory allocation.
//!template <typename T>
void reserve (int newCapacity)//!  void miniVector<T>::reserve (int newCapcity)
{
	ActivationRecord arec = Animation.activate(this);//!
	arec.param("newCapacity", newCapacity).breakHere("starting reserve");//!
	if (newCapacity > vCapacity)
      reserve (newCapacity, true);
	arec.breakHere("done");//!
}


// Remove or add items at the end until the vector has newSize
// items. If items must be added, use the value default for all
// new items.
//!template <typename T>
void resize (int newSize, int defaultV)//!  void miniVector<T>::resize (int newSize, const T& default)
{
	ActivationRecord arec = Animation.activate(this);//!
	arec.param("newSize", newSize).param("default", defaultV).breakHere("starting resize");//!
	if (newSize < vSize)
	{ arec.breakHere("reduce the size");//!
	  vSize = newSize;
	arec.breakHere("done");//!
	}
	if (newSize > vSize)
	{ arec.breakHere("increase the size");//!
	  if (newSize > vCapacity)
	  {
		  arec.breakHere("increase the capacity");//!
		  reserve (newSize, true);
	  }
	  arec.breakHere("append elements till we reach the desired size");//!
	  while (vSize < newSize)
	  {
		  arec.breakHere("add an element");//!
		  push_back (defaultV);//!		  push_back (default);
	  }
	}
	arec.breakHere("done");//!
}

//!#endif   // MINI_VECTOR

        
void quick_clear()//!
{//!
	vSize = vCapacity = 0;//!
	vArr = null;//!
}//!
        
void quick_push_back (int item)//!
{//!
  if (vSize == vCapacity)//!
    {//!
      if (vCapacity == 0)//!
    	  quick_reserve(1,false);//!
      else//!
    	  quick_reserve(2 * vCapacity, true);//!
    }//!
  vArr[vSize] = item;//!
  vSize++;//!
}//!


void quick_reserve(int n, boolean copy)//!
{//!
	int[] newArr;//!
  int i;//!

  newArr = new int[n];//!
  if (copy)//!
    for(i = 0; i < vSize; i++)//!
      newArr[i] = vArr[i];//!
  vArr = newArr;//!
  vCapacity = n;//!
}



}//!
