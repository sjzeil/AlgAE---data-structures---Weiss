package edu.odu.cs.cs361.animations;//!

import java.awt.Color;//!
import java.util.ArrayList;//!

import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!
import edu.odu.cs.AlgAE.Server.Utilities.DiscreteInteger;//!
import edu.odu.cs.AlgAE.Server.Utilities.Index;//!
import static edu.odu.cs.AlgAE.Server.Animations.LocalJavaAnimation.activate;//!

public class SortingNew {//!

//!#ifndef SORT_H
//!#define SORT_H

//!#include <vector>
//!#include <functional>
//!using namespace std;

//
//  From Mark Allen Weiss, "Data Structures and Algorithm Analysis in C++ (Fourth Edition)" 
//      Chapter ?
//
	
//Simple insertion sort.

//!template <typename Comparable>
public void insertionSort (DiscreteInteger[] v, int n)//!
//!void insertionSort( vector<Comparable> & a )
{
	ActivationRecord arec = activate(getClass());//!
	arec.refParam("v", v);//!
	arec.breakHere("starting insertion sort");//!
	
	DiscreteInteger tmp = new DiscreteInteger(-97);//!
	
	// place v[p] into the sublist
	//   v[0] ... v[p-1], 1 <= p < n,
	// so it is in the correct position
	arec.var("n", n);//!
	arec.breakHere("starting loop");//!
		
	for(int p = 1; p< n; p++)//!for( int p = 1; p < a.size( ); ++p )
	{
		    //arec.pushScope();//!    
		    arec.var("p", new Index(p, v));//!
		    
		    
			arec.breakHere("Where should we put v[p]?");//!
		    
		    tmp = v[p];//! Comparable tmp = std::move( a[ p ] );
		    arec.var("tmp", tmp);//!
		    
	        int j = 468; //! int j;
	        arec.var("j", new Index(j, v));//!
	         		
    		// locate insertion point by scanning downward as long
		    // as target < v[j-1] and we have not encountered the
		    // beginning of the list
			arec.breakHere("scan down from j");//!   
	        for(j = p; j > 0 && tmp.get() < v[j-1].get(); j--) //! for( j = p; j > 0 && tmp < a[ j - 1 ]; --j )
	        {
	        	  arec.var("j", new Index(j, v));//!
	        	  // shift elements up list to make room for insertion
	        	  arec.breakHere("Not there yet: shift v[j-1] up");//!
	        	  v[j] = v[j-1]; //! a[ j ] = std::move( a[ j - 1 ] );
	        	  arec.breakHere("and continue scanning");//!
	        	  arec.var("j", new Index(j, v));//!
	        }
	        // the location is found; insert tmp
		    arec.breakHere("Put the tmp at v[j]");//!
	        v[j] = tmp; //! a[ j ] = std::move( tmp );
	    }
	    arec.breakHere("done sorting");//!
	}


//
// Shellsort, using Shell's (poor) increments.
//
//!template <typename Comparable>
void shellSort(DiscreteInteger[] a, int n )//!
//!void shellsort( vector<Comparable> & a )
{
	ActivationRecord arec = activate(getClass());//!
	for (int k = 0; k < n; ++k)//!
		arec.highlight(a[k], Color.lightGray);//!
	arec.refParam("a", a).param("n", n);//!
	arec.breakHere("starting Shell's sort");//!
	
	for(int Gap = n / 2; Gap > 0;
	        Gap = Gap == 2 ? 1 : (int) (Gap / 2.2))//!
	//!for( int gap = a.size( ) / 2; gap > 0; gap /= 2 )
	{
		arec.pushScope();//!
		//inv: for all i in Gap..n-1, a[i] >= a[i-Gap]
		arec.var("Gap", Gap);//!
		arec.breakHere("Gap has been chosen");//!
		
		for( int i = Gap; i < n; i++ )//!
		//!for( int i = gap; i < a.size( ); ++i )      
        {
			arec.var("i", new Index(i,a));//!
			for (int j = i; j >= 0; j-=Gap)//!
				arec.highlight(a[j], Color.green);//!
			arec.breakHere("'insertion sort' these elements");//!
			
			int Tmp = a[i].get();//!
			arec.var("Tmp",Tmp);//!
			//!Comparable tmp = std::move( a[ i ] );
			
            int j = i;//!
            //!int j = i;
            arec.var("j", new Index(j,a));//!
			arec.breakHere("where to put Tmp?");//!
			
			
			for(; j >= Gap && Tmp < a[ j - Gap ].get(); j -= Gap)//!
			//! for( ; j >= gap && tmp < a[ j - gap ]; j -= gap )
			{
				arec.var("j", new Index(j,a));//!
				arec.breakHere("shift a[j-Gap] up");//!
				a[j].set(a[j - Gap].get());//!
				//!a[ j ] = std::move( a[ j - gap ] );
				arec.breakHere("drop down Gap positions");//!
			}
			arec.breakHere("put Tmp in a[j]");//!
			a[j] = new DiscreteInteger(Tmp);//!
            //!	a[ j ] = std::move( tmp );
			for (int k = i; k >= 0; k-=Gap)//!
				arec.highlight(a[k], Color.lightGray);//!
                           
         }
		 arec.breakHere("The array is now " + Gap + "-sorted.");//!
		 arec.popScope();//!
	}
	arec.breakHere("Finished Shell's sort");//!
}



/////////// Merge sort /////////////////



/**
 * Internal method that merges two sorted halves of a subarray.
 * a is an array of Comparable items.
 * tmpArray is an array to place the merged result.
 * leftPos is the left-most index of the subarray.
 * rightPos is the index of the start of the second half.
 * rightEnd is the right-most index of the subarray.
 */
//!template <typename Comparable>
void merge(DiscreteInteger[] a,  DiscreteInteger[] tmpArray2, int leftPos, int rightPos, int rightEnd) {
//!void merge( vector<Comparable> & a, vector<Comparable> & tmpArray,int leftPos, int rightPos, int rightEnd ){
	ActivationRecord arec = activate(getClass());//!
	DiscreteInteger[] tmpArray = new DiscreteInteger[8];
	arec.param("a", "").param("leftPos", leftPos).param("rightPos", rightPos).param("rightEnd", rightEnd);//!
	arec.breakHere("starting merge");//!
	
	int leftEnd = rightPos - 1;//!
	//!int leftEnd = rightPos - 1;
    int tmpPos = leftPos;//!
    //!int tmpPos = leftPos;
    int numElements = rightEnd - leftPos + 1;//!
    //!int numElements = rightEnd - leftPos + 1;
    
	arec.var("tmpArray", tmpArray).var("leftPos", new Index(leftPos, a)).var("rightPos", new Index(rightPos, a)).var("tmpPos", new Index(tmpPos, tmpArray));//!

	for (int i = leftPos; i < rightPos; ++i)//!
		arec.highlight(a[i], Color.yellow);//!
	for (int i = rightPos; i < Math.min(rightEnd, a.length); ++i)//!
		arec.highlight(a[i], Color.blue);//!
	arec.breakHere("leftPos and rightPos point to start of sublists");//!
	
    // Main loop
	while( leftPos <= leftEnd && rightPos <= rightEnd )//!
	//!while( leftPos <= leftEnd && rightPos <= rightEnd )
	{
		arec.breakHere("choose smaller of a[leftPos] and a[leftPos]");//!
        if(a[ leftPos ].get() <= a[ rightPos].get())//!if( a[ leftPos ] <= a[ rightPos ] )
        {
        	arec.breakHere("add a[leftPos++] to tmpArray");//!
        	//DiscreteInteger leftvalue = a[leftPos];
        	
        	arec.breakHere("add a[leftPos++] to tmpArrayget");//!
        	tmpArray[tmpPos++] = a[leftPos++]; //!tmpArray[ tmpPos++ ] = std::move( a[ leftPos++ ] );
        	arec.highlight(a[leftPos-1], Color.gray);//!
        }
        else
        {
        	arec.breakHere("add a[rightPos++] to tmpArrayfdfd");//!
        	//int rightvalue = a[rightPos].get();
        	arec.breakHere("add a[rightPos++] to tmpArrayget");//!
        	tmpArray[tmpPos++] = a[rightPos++];//!tmpArray[ tmpPos++ ] = std::move( a[ rightPos++ ] );
        	arec.highlight(a[rightPos-1], Color.gray);//!
        }
        arec.var("leftPos", new Index(leftPos, a)).var("rightPos", new Index(rightPos, a)).var("tmpPos", new Index(tmpPos, tmpArray));//!
    	arec.breakHere("repeat if both sublists are non-empty");//!
	}//!
	
	arec.breakHere("one or both sublists is empty");//!

    
	while( leftPos <= leftEnd )    // Copy rest of first half
	//!while( leftPos <= leftEnd )
    {
		arec.breakHere("copy remaining left element");//!
		tmpArray[tmpPos++] = a[leftPos++];//!tmpArray[ tmpPos++ ] = std::move( a[ leftPos++ ] );
		arec.highlight(a[leftPos-1], Color.gray);//!
		arec.var("leftPos", new Index(leftPos, a)).var("tmpPos", new Index(tmpPos,tmpArray));//!
    }
    
	arec.breakHere("all elements have been copied from the left sublist");//!
    while( rightPos <= rightEnd )  // Copy rest of right half
    //!while( rightPos <= rightEnd ) 
    {
    	arec.breakHere("copy remaining right element");//!
    	tmpArray[tmpPos++] = a[rightPos++];//!tmpArray[ tmpPos++ ] = std::move( a[ rightPos++ ] );
    	arec.highlight(a[rightPos-1], Color.gray);//!
    	arec.var("rightPos", new Index(rightPos, a)).var("tmpPos", new Index(tmpPos,tmpArray));//!
    }
        
    arec.breakHere("all elements have been copied from the right sublist");//!
    
    // Copy tmpArray back
    arec.pushScope();//!
    for( int i = 0; i < numElements; ++i, --rightEnd )//!for( int i = 0; i < numElements; ++i, --rightEnd )
    {
    	arec.var("i", new Index(i, tmpArray)).var("rightEnd",new Index(i, tmpArray));//!
		arec.breakHere("copy temp element back to original vector");//
    	a[ rightEnd ].set(tmpArray[ rightEnd ].get() );//!a[ rightEnd ] = std::move( tmpArray[ rightEnd ] );
        
    }
    arec.popScope();//!
	arec.breakHere("finished merge");//!
        
}


//
//Internal method that makes recursive calls.
//a is an array of DiscreteInteger
//tmpA is an array to place the merged result.
//left is the left-most index of the subarray.
//right is the right-most index of the subarray.
//
//!template <typename Comparable>
void mergeSort (DiscreteInteger[] a, DiscreteInteger[] tmpArray, int left, int right)//!
//!void mergeSort( vector<Comparable> & a,vector<Comparable> & tmpArray, int left, int right )
{    
	ActivationRecord arec = activate(getClass());//!
	// if the sublist has more than 1 element continue
	arec.refParam("a", a).param("tmpArray",tmpArray).param("left", left).param("right", right);//!
	arec.breakHere("starting mergeSort");//!
	if( left < right )
    {
		// for sublists of size 2 or more, call mergeSort()
		// for the left and right sublists and then
		// merge the sorted sublists using merge()
		int center = ( left + right ) / 2;
        
		arec.var("center", center);//!
		arec.breakHere("sort to left of center");//!
        mergeSort( a, tmpArray, left, center );
        arec.breakHere("sort to right of center");//!
        mergeSort( a, tmpArray, center + 1, right );
        arec.breakHere("merge sorted subarrays");//!
        merge( a, tmpArray, left, center + 1, right );
    }
}


//
// Mergesort algorithm (driver).
//
//!template <typename Comparable>
void mergeSort( DiscreteInteger[] a, int length )
//!void mergeSort( vector<Comparable> & a )
{
	ActivationRecord arec = activate(getClass());//!
	arec.refParam("a", a);//!
	arec.breakHere("starting mergeSort");//!
	
	DiscreteInteger[] tmpArray = new DiscreteInteger[length];
	arec.refParam("tmpArray",tmpArray);//!
	//!vector<Comparable> tmpArray( a.size( ) );
    mergeSort( a, tmpArray, 0, length-1 );
}





/////////// Quick sort /////////////////

//
//Internal quicksort method that makes recursive calls.
//Uses median-of-three partitioning and a cutoff of 10.
//a is an array of DiscreteInteger
//left is the left-most index of the subarray.
//right is the right-most index of the subarray.
//
//!template <typename Comparable>
void quicksort(DiscreteInteger[] a, int left, int right) {//!void quicksort( vector<Comparable> & a, int left, int right )

	ActivationRecord arec = activate(getClass());//!
	arec.refParam("a", a).param("left", left).param("right",right);
	
	if( left + 10 <= right )//!if( left + 10 <= right )
    {
		DiscreteInteger pivot = median3( a, left, right );//!const Comparable & pivot = median3( a, left, right );
		arec.var("pivot", pivot);
        // Begin partitioning
		arec.breakHere("begin partitioning");//!
        int i = left, j = right - 1;//!int i = left, j = right - 1;
        arec.var("i", new Index(i, a)).var("j",new Index(j, a));//!
        arec.breakHere("look for elements to swap");//!
        for( ; ; )//!for( ; ; )
        {
        	while( a[ ++i ].get() < pivot.get() ) { //!while( a[ ++i ] < pivot ) { }
        		arec.breakHere("scan up from the left");//!
        		arec.var("i", new Index(i, a));//!
        	}
            while( pivot.get() < a[ --j ].get() ) { //! while( pivot < a[ --j ] ) { }
            	arec.breakHere("scan down from the right");//!
            	arec.var("j", new Index(j, a));//!
            }
            arec.breakHere("Either we are ready to swap or we are done pivoting");//!
            if( i < j )//!if( i < j )
            {
            	arec.breakHere("Swap the out-of-position elements");//!
            	DiscreteInteger temp = a[ i ];//!std::swap( a[ i ], a[ j ] );
            	a[ i ] = a[ j ];
            	a[ j ] = temp;
            	            }
            else
                break;
        }

        arec.breakHere("Restore pivot");//!
        DiscreteInteger temp = a[ i ];//!std::swap( a[ i ], a[ right - 1 ] );
    	a[ i ] = a[ right - 1 ];
    	a[ right - 1 ] = temp;
        
    	arec.breakHere("Sort small elements");//!
    	quicksort( a, left, i - 1 ); //!quicksort( a, left, i - 1 );   
    	
    	arec.breakHere("Sort large elements");//!
        quicksort( a, i + 1, right );//!quicksort( a, i + 1, right );
    }
    else  
    {// Do an insertion sort on the subarray
    	arec.breakHere("Do an insertion sort on the subarray");//!
        insertionSort( a, left, right );//!insertionSort( a, left, right );
    }
}

//
//Internal insertion sort routine for subarrays
//that is used by quicksort.
//a is an array of DiscreteInteger.
//left is the left-most index of the subarray.
//right is the right-most index of the subarray.
//
//!template <typename Comparable>
void insertionSort( DiscreteInteger[] a, int left, int right )//!void insertionSort( vector<Comparable> & a, int left, int right )
{
	ActivationRecord arec = activate(getClass());//!
	arec.param("a","").param("left", left).param("right",right);
	arec.breakHere("begin insertionSort");//!
	
	for( int p = left + 1; p <= right; ++p )//!for( int p = left + 1; p <= right; ++p )
    {
		arec.var("p", new Index(p, a));//!
		
		DiscreteInteger tmp = a[ p ];//!Comparable tmp = std::move( a[ p ] );
        int j = 789;//!int j;
        arec.var("j", new Index(j, a));//!
        arec.breakHere("look for the position to put tmp");//!
        
        for( j = p; j > left && tmp.get() < a[ j - 1 ].get(); --j )//!for( j = p; j > left && tmp < a[ j - 1 ]; --j )
        {
        	arec.breakHere("scan down from j");//!
        	arec.var("j", new Index(j, a));//!
        	a[ j ] = a[ j - 1 ] ;//!a[ j ] = std::move( a[ j - 1 ] );
        }
        arec.breakHere("Put tmp at a[j]");//!
        a[ j ] = tmp;//!a[ j ] = std::move( tmp );
    }
	arec.breakHere("done sorting");//!
}


//
// Return median of left, center, and right.
// Order these and hide the pivot.
//
//!template <typename Comparable>
final DiscreteInteger median3( DiscreteInteger[] a, int left, int right )//!const Comparable & median3( vector<Comparable> & a, int left, int right )
{
	ActivationRecord arec = activate(getClass());//!
	arec.param("a","").param("left", left).param("right",right);//!

	
	int center = ( left + right ) / 2;//!int center = ( left + right ) / 2;
	
	arec.var("center", new Index(center, a));
    
	if( a[ center ].get() < a[ left ].get() ) //!if( a[ center ] < a[ left ] )
	{
		arec.breakHere("swap  a[ left ] and  a[ center ]");//!
		DiscreteInteger temp = a[ left ];//!std::swap( a[ left ], a[ center ] );
		a[ left ] = a[ center ];
		a[ center ] = temp;
		arec.breakHere("done with swapping left and center");//!
		
	}
	if( a[ right ].get() < a[ left ].get() )//!if( a[ right ] < a[ left ] )
	{
		arec.breakHere("swap  a[ right ] and  a[ left ]");//!
		DiscreteInteger temp = a[ left ];//!std::swap( a[ left ], a[ right ] );
		a[ left ] = a[ right ];
		a[ right ] = temp;
		arec.breakHere("done with swapping right and left");//!
	}
	if( a[ right ].get() < a[ center ].get() )//!if( a[ right ] < a[ center ] )
	{
		arec.breakHere("swap  a[ right ] and  a[ center ]");//!
		DiscreteInteger temp = a[ right ];//!std::swap( a[ right ], a[ center ] );
		a[ right ] = a[ center ];
		a[ center ] = temp;
		arec.breakHere("done with swapping right and center");//!
	}

    // Place pivot at position right - 1
	arec.breakHere("Place pivot at position right - 1");//!
	arec.breakHere("swap  a[ center ] and  a[ right - 1 ]");//!
	DiscreteInteger temp = a[ center ];//!std::swap( a[ center ], a[ right - 1 ] );
	a[ center ] = a[ right - 1 ];
	a[ right - 1 ] = temp;
	arec.breakHere("done with swapping center and right - 1");//!
    
    return a[ right - 1 ];
}


//
// Quicksort algorithm (driver).
//
//!template <typename Comparable>
void quicksort( DiscreteInteger[] a, int size)//!void quicksort( vector<Comparable> & a )
{
	ActivationRecord arec = activate(getClass());//!
	arec.refParam("a",a);
	arec.breakHere("start quicksorting");
	
	quicksort( a, 0, size - 1 );//!quicksort( a, 0, a.size( ) - 1 );
}



//!#endif  
        
        
}//!
