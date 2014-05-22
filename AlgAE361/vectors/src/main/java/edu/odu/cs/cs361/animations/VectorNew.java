/*
 * Vector From Mark Allen Weiss, "Data Structures and Algorithm Analysis in C++ (Fourth Edition)" 
 * 
 */
package edu.odu.cs.cs361.animations;//!


import static edu.odu.cs.AlgAE.Server.Animations.LocalJavaAnimation.activate;//!

import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!

public class VectorNew {//!


//!#ifndef VECTOR_H
//!#define VECTOR_H

//!#include <algorithm>
//!#include <iostream>
//!#include <stdexcept>
//!#include "dsexceptions.h"

//!template <typename Object>
//!class Vector
//! {
//!	  public:
	
    //!explicit Vector( int initSize = 0 )
    //!: theSize{ initSize }, theCapacity{ initSize + SPARE_CAPACITY }
    //!{ objects = new Object[ theCapacity ]; }
	
final int SPARE_CAPACITY = 2;
int theSize;
int theCapacity;
int [] objects;
    
//constructor. initialize theSize, theCapacity and objects.
VectorNew (int initSize)//!
{
	
	theSize = initSize;
	theCapacity = initSize + SPARE_CAPACITY;
	objects = new int[theCapacity];

}
	

//copy constructor. make the current object a copy of obj.
VectorNew (VectorNew obj)//!Vector( const Vector & rhs )
//!: theSize{ rhs.theSize }, theCapacity{ rhs.theCapacity }, objects{ nullptr }
{
	ActivationRecord arec = activate(this);//!
	
	arec.refParam("obj", obj).breakHere("start copying");//!	  

    
    theSize = obj.theSize;
    theCapacity = obj.theCapacity;


    objects = new int[ theCapacity ];//!objects = new Object[ theCapacity ];
    
    arec.breakHere("copy items");//!	
    
    for( int k = 0; k < theSize; ++k )//!for( int k = 0; k < theSize; ++k )
        objects[ k ] = obj.objects[ k ];//! objects[ k ] = rhs.objects[ k ];
    
    
    arec.breakHere("done");//!	  
}


//replace existing object (left-hand operand) by
//rhs (right-hand operand)
//!template <typename T>
void assign (VectorNew rhs)//!Vector & operator= ( const Vector & rhs )
{
	ActivationRecord arec = activate(this);//!
    int i;
    
    // check vCapacity to see if a new array must be allocated
    arec.refParam("rhs", rhs).breakHere("check the capacity");//!
    if (theCapacity < rhs.theSize)
    // make capacity of current object the size of rhs. don't
    // do a copy, since we will replace the old values
	{ arec.breakHere("reserve more space");//!
      reserve(rhs.theSize, false);
	}//!

    // assign current object to have same size as rhs
    arec.breakHere("copy the size");//!
    theSize = rhs.theSize;

    arec.breakHere("copy the items");//!
    // copy items from rhs.vArr to vArr
    for (i = 0; i < theSize; i++)
      objects[i] = rhs.objects[i];

    arec.breakHere("done");//!
     //!  return *this;
}

//set the capacity to n elements
void reserve(int n, boolean copy)
{
	 
	int[] newArr;
    int i;

// allocate a new dynamic array with n elements
newArr = new int[n];
// if copy is true, copy elements from the old list to the new list
if (copy)
{
 
 for(i = 0; i < theSize; i++)
   newArr[i] = objects[i];
 
}
// delete original dynamic array. if vArr is NULL, the vector was
// originally empty and there is no memory to delete
if (objects != null) {
	objects = null;
}

// set vArr to the value newArr. update vCapacity

objects = newArr;
theCapacity = n;	  
}

//destructor. deallocate the dynamic array
void destroy()//!~Vector( )
{
	ActivationRecord arec = activate(this);//!
    // de-allocate memory for the array  
	arec.breakHere("delete array");//!	  
    
    objects = null;//! delete [ ] objects;
    arec.breakHere("done");//!
}

//!Vector( Vector && rhs )
//!: theSize{ rhs.theSize }, theCapacity{ rhs.theCapacity }, objects{ rhs.objects }
//!{
//!  rhs.objects = nullptr;
//!  rhs.theSize = 0;
//!  rhs.theCapacity = 0;
//!}


//!Vector & operator= ( Vector && rhs )
//!{    
//! std::swap( theSize, rhs.theSize );
//! std::swap( theCapacity, rhs.theCapacity );
//! std::swap( objects, rhs.objects );
    
//! return *this;
//!}

boolean empty()//!bool empty( ) const
{
  return theSize == 0;//!return size( ) == 0;
}

int size()//!int size( ) const
{
   return theSize;//!return theSize;
}

int capacity( )//!int capacity( ) const
{ 
	return theCapacity;//!return theCapacity;
}

//provides general access to array elements
int get(int index)//!Object & operator[]( int index )
{
	//!               #ifndef NO_CHECK

	 if( index < 0 || index >= size( ) )//! if( index < 0 || index >= size( ) )
	throw new IndexOutOfBoundsException(//! throw ArrayIndexOutOfBoundsException{ };
			"ArrayIndexOutOfBoundsException");//!
	 //!              #endif

	 return objects[ index ];//!return objects[ index ];
}


//!const Object & operator[]( int index ) const
//!{
//!                                                 #ifndef NO_CHECK
//!    if( index < 0 || index >= size( ) )
//!        throw ArrayIndexOutOfBoundsException{ };
//!                                                 #endif
//!    return objects[ index ];
//!}


void resize (int newSize)//!void resize( int newSize )
{
	ActivationRecord arec = activate(this);//!
	arec.param("newSize", newSize).breakHere("starting resize");//!

	if (newSize > theCapacity)//!if( newSize > theCapacity )
	{
	    arec.breakHere("increase the capacity");//!
	    reserve( newSize * 2 );//!reserve( newSize * 2 );
	}
	  arec.breakHere("update the size");//!
	  theSize = newSize;//!theSize = newSize;
	  arec.breakHere("done");//!
}

void reserve(int newCapacity)//!void reserve( int newCapacity )
{
	   ActivationRecord arec = activate(this);//!
	   arec.param("newCapacity", newCapacity).breakHere("starting reserve");//!
	   
	   if( newCapacity < theSize )
	        return;
	   // allocate a new dynamic array with newCapacity elements
	   int[] newArray = new int[ newCapacity ];//!Object *newArray = new Object[ newCapacity ];
	   
	   arec.refVar("newArray", newArray).breakHere("allocated new array");//!
  
	   arec.breakHere("copy data from old array");//!
	   for( int k = 0; k < theSize; ++k )
	   {
		   newArray[ k ] = objects[ k ];//!newArray[ k ] = std::move( objects[ k ] );
	   }
	   
	   if (objects != null) { 
		      objects = null;
	   }//!
	   arec.breakHere("copied data from old array");//!
	   
	   arec.breakHere("update the capacity");//!
	   theCapacity = newCapacity;//! theCapacity = newCapacity;
	   
	   arec.breakHere("use the new array");//!	
	   int[] tempArray;//!std::swap( objects, newArray );
	   tempArray = objects;
	   objects = newArray;
	   newArray = objects;

	   
	   arec.breakHere("delete new array");//!	
	   newArray = null;//!delete [ ] newArray;
	   
	   arec.breakHere("done");//!
 
}

//Stacky stuff
void push_back (int x)//!void push_back( const Object & x )
{
	   ActivationRecord arec = activate(this);//!
	   arec.param("Object", x).breakHere("starting push_back");//!
	   // if space is full, allocate more capacity
	   if( theSize == theCapacity )
	   {
		   arec.breakHere("double the capacity");//!
		   reserve( 2 * theCapacity + 1 );
	   }
	   //add Object to the list, update vSize
	   arec.breakHere("add x and update the size");//!
	   objects[ theSize++ ] = x;//! objects[ theSize++ ] = x;
	   
	   arec.breakHere("done with push_back");//!
}
// Stacky stuff
//!void push_back( Object && x )
//!{
//!  if( theSize == theCapacity )
//!      reserve( 2 * theCapacity + 1 );
//!      objects[ theSize++ ] = std::move( x );
  
//!}

//if not empty, just decrement the size
void pop_back()//!void pop_back( )
{
	//!if( empty( ) )
		//!throw UnderflowException{ };
	theSize--;//!--theSize;
}


//check theSize and throw an underflowError exception if the
//value is 0; otherwise return the element objects[theSize-1]
int back()//!const Object & back ( ) const
{
	if( empty( ) )
	  throw new IndexOutOfBoundsException(//!   throw UnderflowException{ };
			 "Vector back(): vector empty");

	return objects[ theSize - 1 ];
}

// Iterator stuff: not bounds checked
//!typedef Object * iterator;
//!typedef const Object * const_iterator;

//!iterator begin( )
//!  { return &objects[ 0 ]; }
//!const_iterator begin( ) const
//!  { return &objects[ 0 ]; }
//!iterator end( )
//!  { return &objects[ size( ) ]; }
//!const_iterator end( ) const
//!  { return &objects[ size( ) ]; }


//!static const int SPARE_CAPACITY = 2;

//!private:
//!int theSize;
//!int theCapacity;
//!Object * objects;
//!};

//!#endif



void quick_clear()//!
{//!
	theSize = theCapacity = 0;//!
	objects = null;//!
}//!
        
void quick_push_back (int item)//!
{//!
  if (theSize == theCapacity)//!
    {//!
      if (theCapacity == 0)//!
    	  quick_reserve(1,false);//!
      else//!
    	  quick_reserve(2 * theCapacity, true);//!
    }//!
  objects[theSize] = item;//!
  theSize++;//!
}//!


void quick_reserve(int n, boolean copy)//!
{//!
	int[] newArr;//!
    int i;//!

    newArr = new int[n];//!
    if (copy)//!
      for(i = 0; i < theSize; i++)//!
        newArr[i] = objects[i];//!
        objects = newArr;//!
        theCapacity = n;//!
}

}//!
