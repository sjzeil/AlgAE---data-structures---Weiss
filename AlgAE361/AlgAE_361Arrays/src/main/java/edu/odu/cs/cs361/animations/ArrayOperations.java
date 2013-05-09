package edu.odu.cs.cs361.animations;//!

import edu.odu.cs.zeil.AlgAE.ActivationRecord;//!
import edu.odu.cs.zeil.AlgAE.Animation;//!
import edu.odu.cs.zeil.AlgAE.Utilities.DiscreteInteger;//!
import edu.odu.cs.zeil.AlgAE.Utilities.Index;//!

public class ArrayOperations {//!


//!# include <iostream>
//!# include <string>
	
//!using namespace std;

	
	


//int orderedInsert (int arr[], int first, int last, int target)
public int orderedInsert (DiscreteInteger[] arr, int first, int last, int target)//!
{
   ActivationRecord arec = Animation.activate(getClass());//!
   arec.refParam("arr", arr).param("first", first).param("last", last).param("target", target);//!
   arec.breakHere("starting addInOrder");//!
   if (last >= arr.length) {//!
	   arec.breakHere("array is already full - program may crash");//!
	   return first-1;//!
   }//!
  Index i = new Index(last, arr);//!  int i = last;
  arec.var("i", i);//!
  arec.breakHere("start at high end of the data");//!
  while (i.get() > first && target < arr[i.get()-1].get()) //!  while ((i > first) && (target < arr[i-1]))
  {   
	arec.breakHere("in loop: ready to move an element up");//!
    arr[i.get()] = arr[i.get()-1];//!    arr[i] = arr[i-1];
	arec.breakHere("in loop: Moved the element");//!
    i.set(i.get() - 1);//!    i = i - 1;
	arec.breakHere("in loop: decremented");//!
  }
  arec.breakHere("exited loop: insert the new value");//!
  arr[i.get()] = new DiscreteInteger(target);//!  arr[i] = target;  
  arec.breakHere("Done");//!
  return i.get();//!  return i;
}
	
/*
 * seqSearch from Ford & Topp, Data Structures in C++ using STL, 2nd ed.
 * section 3-2
 */
//!int seqSearch(const int arr[], int first, int last, int target)
public int seqSearch(DiscreteInteger arr[], int first, int last, int target)//!
{
	ActivationRecord arec = Animation.activate(getClass());//!
	arec.refParam("arr", arr).param("first", first).param("last", last).param("target", target);//!
	arec.breakHere("starting seqSearch");//!
    int i = first;
    
    // scan indices in the range first <= i < last; test for a match
    // or index out of range
    
    arec.var("i", new Index(i, arr));//!
    while (i != last && arr[i].get() != target) {//!    while (i != last && arr[i] != target)
    	arec.breakHere("in loop: have not found it");//!
    	++i;
        arec.var("i", new Index(i, arr));//!
    }//!
    arec.breakHere("Found it or ran out of data.");//!
    return i; // i is index of match or i = last if no match
}//!


//!int binSearch (const int arr[], int first, int last, int target)
public int binSearch(DiscreteInteger arr[], int first, int last, int target)//!
//search for target in ordered array of data
//return index of target, or index of
//next smaller target if not in collection
{
	ActivationRecord arec = Animation.activate(getClass());//!
	arec.refParam("arr", arr).param("first", new Index(first, arr)).param("last", new Index(last, arr)).param("target", target);//!
	arec.breakHere("starting binSearch");//!
    int mid = -997;//!    int mid;      // index of the midpoint
    int midValue = -1;//!	int midValue; // object that is assigned arr[mid]
	int origLast = last; // save original value of last 

	arec.var("mid", new Index(mid, arr)).var("midValue", midValue).var("origLast", origLast);//!
	arec.breakHere("start the loop");//!
    // repeatedly reduce the area of search
    // until it is just one target
	while (first < last) {  // test for nonempty sublist
		for (int i = first; i < last; ++i) arec.highlight(arr[i]); //!
		arec.breakHere("in the loop");//!
		mid = (first + last) / 2; 
		midValue = arr[mid].get();//!		midValue = arr[mid];  
		arec.var("mid", new Index(mid, arr)).var("midValue", midValue);//!
		arec.breakHere("look at the midpoint");//!
		if (target == midValue) 
		{//!
			arec.breakHere("Found it!");//!
			return mid;
		} //!
		else if (target < midValue)
		{ //!
			arec.breakHere("middle value is too high");//!
			last = mid;        // search lower sublist, reset last
			arec.param("last", new Index(last, arr));//!
		}//!
		else 
		{//!
			arec.breakHere("middle value is too low");//!
			first = mid+1;     // search upper sublist, reset first
			arec.param("first", new Index(first, arr));//!
		}//!
		arec.clearRenderings();//!
	}
	arec.breakHere("target is not in the array");//!
	return origLast;
}

        
}//!
