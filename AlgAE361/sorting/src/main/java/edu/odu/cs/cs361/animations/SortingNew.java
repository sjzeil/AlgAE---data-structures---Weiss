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
void merge(DiscreteInteger[] a,  DiscreteInteger[] tempa, int leftPos, int rightPos, int rightEnd) {
//!void merge( vector<Comparable> & a, vector<Comparable> & tmpArray,int leftPos, int rightPos, int rightEnd ){
	ActivationRecord arec = activate(getClass());//!

	arec.param("a", "").param("leftPos", leftPos).param("rightPos", rightPos).param("rightEnd", rightEnd);//!
	arec.breakHere("starting merge");//!
	
	int leftEnd = rightPos - 1;//!
	//!int leftEnd = rightPos - 1;
    int tmpPos = leftPos;//!
    //!int tmpPos = leftPos;
    int numElements = rightEnd - leftPos + 1;//!
    //!int numElements = rightEnd - leftPos + 1;
    
	arec.var("tempa", tempa).var("leftPos", new Index(leftPos, a)).var("rightPos", new Index(rightPos, a)).var("tmpPos", new Index(tmpPos, a));//!

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
        	arec.breakHere("add a[leftPos++] to tempa");//!
        	tempa[tmpPos++].set(a[leftPos++].get()); //!tmpArray[ tmpPos++ ] = std::move( a[ leftPos++ ] );
        	arec.highlight(a[leftPos-1], Color.gray);//!
        }
        else
        {
        	arec.breakHere("add a[rightPos++] to tempa");//!
        	tempa[tmpPos++].set(a[rightPos++].get());//!tmpArray[ tmpPos++ ] = std::move( a[ rightPos++ ] );
        	arec.highlight(a[rightPos-1], Color.gray);//!
        }
        arec.var("leftPos", new Index(leftPos, a)).var("rightPos", new Index(rightPos, a));//!
    	arec.breakHere("repeat if both sublists are non-empty");//!
	}//!
	
	arec.breakHere("one or both sublists is empty");//!

    
	while( leftPos <= leftEnd )    // Copy rest of first half
	//!while( leftPos <= leftEnd )
    {
		arec.breakHere("copy remaining left element");//!
		tempa[tmpPos++].set(a[leftPos++].get());//!tmpArray[ tmpPos++ ] = std::move( a[ leftPos++ ] );
		arec.highlight(a[leftPos], Color.gray);//!
		arec.var("leftPos", new Index(leftPos, a));//!
    }
    
	arec.breakHere("all elements have been copied from the left sublist");//!
    while( rightPos <= rightEnd )  // Copy rest of right half
    {
    	arec.breakHere("copy remaining right element");//!
    	tempa[tmpPos++].set(a[rightPos++].get());//!tmpArray[ tmpPos++ ] = std::move( a[ rightPos++ ] );
    	arec.highlight(a[rightPos], Color.gray);//!
		arec.var("rightPos", new Index(rightPos, a));//!
    }
        
    arec.breakHere("all elements have been copied from the right sublist");//!
    
    // Copy tmpArray back
    arec.pushScope();//!
    for( int i = 0; i < numElements; ++i, --rightEnd )//!for( int i = 0; i < numElements; ++i, --rightEnd )
    {
    	arec.var("i", new Index(i, tempa)).var("rightEnd",new Index(i, tempa));//!
		arec.breakHere("copy temp element back to original vector");//
    	a[ rightEnd ].set(tempa[ rightEnd ].get() );//!a[ rightEnd ] = std::move( tmpArray[ rightEnd ] );
        
    }
    arec.popScope();//!
	arec.breakHere("finished merge");//!
        
}



//sorts v in the index range [first,last) by merging
//ordered sublists
//!template<typename T>
void mergeSort (DiscreteInteger[] v, int first, int last) {//!void mergeSort(vector<T>& v, int first, int last) {
	ActivationRecord arec = activate(getClass());//!
	// if the sublist has more than 1 element continue
	arec.refParam("v", v).param("first", first).param("last", last);//!
	arec.breakHere("starting mergeSort");//!
	if (first + 1 < last) {
		// for sublists of size 2 or more, call mergeSort()
		// for the left and right sublists and then
		// merge the sorted sublists using merge()
		int midpt = (last + first) / 2;

		arec.var("midPt", midpt);//!
		arec.breakHere("sort to left of center");//!
		//mergeSort(v, first, midpt);
		arec.breakHere("sort to right of center");//!
		//mergeSort(v, midpt, last);
		arec.breakHere("merge sorted subarrays");//!
		//merge(v, first, midpt, last);
	}
}





/////////// Quick sort /////////////////




//!template<typename T>
int pivotIndex(DiscreteInteger[] v, int first, int last) {//!int pivotIndex(vector<T>& v, int first, int last) {
	ActivationRecord arec = activate(getClass());//!
	arec.param("v","").param("first", first).param("last",last);
	// index for the midpoint of [first,last) and the
	// indices that scan the index range in tandem
	int mid = 789; int scanUp = -47; int scanDown = 472;//!    int mid, scanUp, scanDown;
	// pivot value and object used for exchanges
	int pivot = -987; int temp = 0;//!	T pivot, temp;
	arec.var("mid", new Index(mid, v)).var("scanUp", new Index(scanUp, v)).var("scanDown", new Index(scanDown, v)).var("pivot", pivot);//!

	for (int k = first; k < last; ++k) arec.highlight(v[k]);//!
	arec.breakHere("starting pivot");//!
	if (first == last)
		{arec.breakHere("exit immediately: use last");//!
		return last;
		}//!
	else if (first == last - 1)
	{arec.breakHere("exit immediately: use first");//!
		return first;
	}//!
	else {
		mid = (last + first) / 2;
		pivot = v[mid].get();//!		pivot = v[mid];

		arec.var("mid", new Index(mid, v)).var("pivot", pivot);//!
		arec.breakHere("try middle value as pivot");//!
		// exchange the pivot and the low end of the range
		// and initialize the indices scanUp and scanDown.
		v[mid].set(v[first].get());//!		v[mid] = v[first];
		v[first].set(pivot);//!		v[first] = pivot;

		arec.breakHere("Moved pivot - now set up the scans");//!
		scanUp = first + 1;
		scanDown = last - 1;
		arec.var("scanUp", new Index(scanUp, v)).var("scanDown", new Index(scanDown, v));//!

		// manage the indices to locate elements that are in
		// the wrong sublist; stop when scanDown <= scanUp
		arec.breakHere("look for elements to swap");//!
		for (;;) {
			// move up lower sublist; stop when scanUp enters
			// upper sublist or identifies an element >= pivot
			while (scanUp <= scanDown && v[scanUp].get() < pivot) {//!			while (scanUp <= scanDown && v[scanUp] < pivot)
				arec.breakHere("scan up from the left");//!
				scanUp++;
				arec.var("scanUp", new Index(scanUp, v));//!
			}//!

			// scan down upper sublist; stop when scanDown locates
			// an element <= pivot; we guarantee we stop at arr[first]
			while (pivot < v[scanDown].get()) {//!			while (pivot < v[scanDown])
				arec.breakHere("scan down from the right");//!
				scanDown--;
				arec.var("scanDown", new Index(scanDown, v));//!
			}//!

			arec.breakHere("Either we are ready to swap or we are done pivoting");//!
			// if indices are not in their sublists, partition complete
			if (scanUp >= scanDown)
				{arec.breakHere("We are done pivoting");//!
				break;
				}//!

			// indices are still in their sublists and identify
			// two elements in wrong sublists. exchange
			arec.breakHere("Swap the out-of-position elements");//!
			temp = v[scanUp].get();//!			temp = v[scanUp];
			v[scanUp].set(v[scanDown].get());//!			v[scanUp] = v[scanDown];
			v[scanDown].set(temp);//!			v[scanDown] = temp;


			arec.breakHere("Then continue scanning");//!
			scanUp++;
			scanDown--;
			arec.var("scanUp", new Index(scanUp, v)).var("scanDown", new Index(scanDown, v));//!
		}

		// copy pivot to index (scanDown) that partitions sublists
		// and return scanDown
		arec.breakHere("Done with scan - move pivot element into proper position");//!
		v[first].set(v[scanDown].get());//!		v[first] = v[scanDown];
		v[scanDown].set(pivot);//!		v[scanDown] = pivot;
		arec.breakHere("pivot is position scanDown");//!
		return scanDown;
	}
}



//!template<typename T>
void quicksort(DiscreteInteger[] v, int first, int last) {//!void quicksort(vector<T>& v, int first, int last) {
	ActivationRecord arec = activate(getClass());//!
	for (int k = first; k < Math.min(last, v.length); ++k) arec.highlight(v[k]);//!
	// index of the pivot
	int pivotLoc = -27;//!	int pivotLoc;
	// temp used for an exchange when [first,last) has
	// two elements
	int temp = 0;//!	T temp;

	arec.refParam("v", v).param("first", first).param("last", last).var("pivotLoc", new Index(pivotLoc, v));//!
	arec.breakHere("starting mergeSort");//!
	// if the range is not at least two elements, return
	if (last - first <= 1)
	{	arec.breakHere("return immediately");//!
		return;
	}//!
	
	// if sublist has two elements, compare v[first] and
	// v[last-1] and exchange if necessary
	else if (last - first == 2) {
		arec.breakHere("special case: short subarray");//!
		if (v[last - 1].get() < v[first].get()) {//!		if (v[last - 1] < v[first]) {
			arec.breakHere("swap these two elements");//!
			temp = v[last - 1].get();//!			temp = v[last - 1];
			v[last - 1].set(v[first].get());//!			v[last - 1] = v[first];
			v[first].set(temp);//!			v[first] = temp;
		}
		arec.breakHere("done with short subarray");//!
		return;
	} else {
		arec.breakHere("find a pivot location");//!
		pivotLoc = pivotIndex(v, first, last);
		arec.var("pivotLoc", new Index(pivotLoc, v));//!
		// make the recursive call
		arec.breakHere("then sort everything to the left of the pivot");//!
		quicksort(v, first, pivotLoc);

		// make the recursive call
		arec.breakHere("then sort everything to the right of the pivot");//!
		quicksort(v, pivotLoc + 1, last);
		arec.breakHere("done with this subarray");//!
	}
}




//!#endif  
        
        
}//!
