/*
 *Vector From Mark Allen Weiss, "Data Structures and Algorithm Analysis in C++ (Fourth Edition)" 
 * 
*/
package edu.odu.cs.cs361.animations;//!


import static edu.odu.cs.AlgAE.Server.LocalServer.activate;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!

public class Vector {//!


//!   #ifndef VECTOR_H
//!   #define VECTOR_H

//!   #include <algorithm>
//!   #include <iostream>
//!   #include <stdexcept>
//!   #include "dsexceptions.h"

//!   template <typename Object>
//!   class Vector
//!   {
//!	   public:
//!         explicit Vector( int initSize = 0 )
//!         : theSize{ initSize }, theCapacity{ initSize + SPARE_CAPACITY }
//!         { objects = new Object[ theCapacity ]; }
	
final int SPARE_CAPACITY = 2;//!
int theSize;//!
int theCapacity;//!
int [] objects;//!
    
Vector (int initSize)//!
{//!
	theSize = initSize;//!
	theCapacity = initSize + SPARE_CAPACITY;//!
	objects = new int[theCapacity];//!
}//!
	
Vector (Vector rhs)//!
//!   Vector( const Vector & rhs )
//!   : theSize{ rhs.theSize }, theCapacity{ rhs.theCapacity }, objects{ nullptr }
  {
	ActivationRecord arec = activate(this);//!
	
	arec.refParam("rhs", rhs).breakHere("start copying");//!	  
    theSize = rhs.theSize;//!
    theCapacity = rhs.theCapacity;//!

    objects = new int[ theCapacity ];//!
    //!    objects = new Object[ theCapacity ];
    
    arec.breakHere("copy items");//!	
    for( int k = 0; k < theSize; ++k )
        objects[ k ] = rhs.objects[ k ];
    
    arec.breakHere("done");//!	  
  }


void assign (Vector rhs)//!
//!Vector & operator= ( const Vector & rhs )
{
	ActivationRecord arec = activate(this);//!
    int i;//!
    arec.refParam("rhs", rhs);//!
    
    Vector copy = rhs;
    if (theCapacity < copy.theSize)//!
	{//!
      reserve(copy.theSize, false);//!
	}//!
    theSize = copy.theSize;//!
    for (i = 0; i < theSize; i++)//!
      objects[i] = copy.objects[i];//!
//!    std::swap( *this, copy );
    arec.breakHere("done");//!
//!    return *this;
}

void destroy()//!
//!~Vector( )
{
	ActivationRecord arec = activate(this);//!
	arec.breakHere("delete array");//!	  
    objects = null;//! 
//!    delete [ ] objects;
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
//!  std::swap( theSize, rhs.theSize );
//!  std::swap( theCapacity, rhs.theCapacity );
//!  std::swap( objects, rhs.objects );
    
//!  return *this;
//!}

boolean empty()//!
//!bool empty( ) const
{
    return theSize == 0;//!
//!    return size( ) == 0;
}

int size()//!
//!int size( ) const
{
    return theSize;
}

int capacity( )//!
//!int capacity( ) const
{ 
	return theCapacity;
}

int get(int index)//!
//!Object & operator[]( int index )
{
//!                                                 #ifndef NO_CHECK

	 if( index < 0 || index >= size( ) )
	   throw new IndexOutOfBoundsException(//!
			"ArrayIndexOutOfBoundsException");//!        throw ArrayIndexOutOfBoundsException{ };
//!                                                 #endif

	 return objects[ index ];
}


//!const Object & operator[]( int index ) const
//!{
//!                                                 #ifndef NO_CHECK
//!    if( index < 0 || index >= size( ) )
//!        throw ArrayIndexOutOfBoundsException{ };
//!                                                 #endif
//!    return objects[ index ];
//!}


void resize (int newSize)
{
	ActivationRecord arec = activate(this);//!
	arec.param("newSize", newSize).breakHere("starting resize");//!

	if (newSize > theCapacity)
	{
	    arec.breakHere("increase the capacity");//!
	    reserve( newSize * 2 );//!        reserve( newSize * 2 );
	}
	arec.breakHere("update the size");//!
	theSize = newSize;
	arec.breakHere("done");//!
}

void reserve(int newCapacity)
{
	 ActivationRecord arec = activate(this);//!
	 arec.param("newCapacity", newCapacity).breakHere("starting reserve");//!
	 
	 if( newCapacity < theSize )
	      return;
	 int[] newArray = new int[ newCapacity ];//!
//!     Object *newArray = new Object[ newCapacity ];
	   
	 arec.refVar("newArray", newArray).breakHere("allocated new array");//!
  
	 arec.breakHere("copy data from old array");//!
	 for( int k = 0; k < theSize; ++k )
	 {
		newArray[ k ] = objects[ k ];//!
//!        newArray[ k ] = std::move( objects[ k ] );
	 }
	 if (objects != null) {//!
		   objects = null;//!
	 }//!
	 arec.breakHere("copied data from old array");//!
	   
	 arec.breakHere("update the capacity");//!
	 theCapacity = newCapacity;
	   
	 arec.breakHere("use the new array");//!	
	 int[] tempArray;//!     std::swap( objects, newArray );
	 tempArray = objects;//!
	 objects = newArray;//!
	 newArray = objects;//!
	 arec.breakHere("delete new array");//!	
	 newArray = null;//!     delete [ ] newArray;
	   
	 arec.breakHere("done");//!
}

//Stacky stuff
void push_back (int x)//!
//!void push_back( const Object & x )
{
	 ActivationRecord arec = activate(this);//!
	 arec.param("Object", x).breakHere("starting push_back");//!
	 if( theSize == theCapacity )
	 {
		 arec.breakHere("double the capacity");//!
		 reserve( 2 * theCapacity + 1 );
	 }
	 arec.breakHere("add x and update the size");//!
	 objects[ theSize++ ] = x;
	   
	 arec.breakHere("done with push_back");//!
}
// Stacky stuff
//!void push_back( Object && x )
//!{
//!  if( theSize == theCapacity )
//!      reserve( 2 * theCapacity + 1 );
//!  objects[ theSize++ ] = std::move( x );
  
//!}

void pop_back()//!void pop_back( )
{
//!     if( empty( ) )
//!         throw UnderflowException{ };
	theSize--;//!     --theSize;
}


int back()//!
//!const Object & back ( ) const
{
	if( empty( ) )
	  throw new IndexOutOfBoundsException(//!       throw UnderflowException{ };
			 "Vector back(): vector empty");//!

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
//!  int theSize;
//!  int theCapacity;
//!  Object * objects;
//!};

//!#endif


void reserve(int n, boolean copy)//!
{//!
	 
	int[] newArr;//!
    int i;//!

newArr = new int[n];//!
if (copy)//!
{//!
 
 for(i = 0; i < theSize; i++)//!
   newArr[i] = objects[i];//!
 
}//!

if (objects != null) {//!
	objects = null;//!
}//!

objects = newArr;//!
theCapacity = n;//!	  
}//!

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
