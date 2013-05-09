package edu.odu.cs.cs361.animations;//!

import java.awt.Color;//!
import java.util.ArrayList;//!

import edu.odu.cs.zeil.AlgAE.ActivationRecord;//!
import edu.odu.cs.zeil.AlgAE.Animation;//!
import edu.odu.cs.zeil.AlgAE.Utilities.DiscreteInteger;//!
import edu.odu.cs.zeil.AlgAE.Utilities.Index;//!

public class Sorting {//!

//!#ifndef SORT_H
//!#define SORT_H

//!# include <algorithm>

//
//  From Ford & Topp, "Data Structures with C++ using STL"
//      Chapter 4
//
//!template <typename T>
public void insertionSort (DiscreteInteger[] v, int n)//!
//!void insertionSort (vector<T>&	 v)
{
	ActivationRecord arec = Animation.activate(getClass());//!
	arec.refParam("v", v);//!
	arec.breakHere("starting insertion sort");//!
	int i = 999, j=468; //!    int i, j, n = v.size();
	DiscreteInteger target = new DiscreteInteger(-97);//!	   T target;

	// place v[i] into the sublist
	//   v[0] ... v[i-1], 1 <= i < n,
	// so it is in the correct position
	arec.var("n", n).var("i", new Index(i, v)).var("j", new Index(j, v)).var("target", target);//!
	arec.breakHere("starting loop");//!
	for (i = 1; i < n; i++) 
	{
		  arec.var("i", new Index(i, v));//!
	      // index j scans down list from v[i] looking for
	      // correct position to locate target. assigns it to
	      // v[j]
		  arec.breakHere("Where should we put v[i]?");//!
	      j = i;
		  arec.var("j", new Index(j, v));//!
	      target = v[i];
	      arec.var("target", target);
	      // locate insertion point by scanning downward as long
	      // as target < v[j-1] and we have not encountered the
	      // beginning of the list
		  arec.breakHere("scan down from j");//!
	      while (j > 0 && target.get() < v[j-1].get())//!	      while (j > 0 && target < v[j-1])
	      {
	         // shift elements up list to make room for insertion
			 arec.breakHere("Not there yet: shift v[j-1] up");//!
	         v[j] = v[j-1];
	         arec.breakHere("and continue scanning");//!
	         j--;
	         arec.var("j", new Index(j, v));//!
	      }
	      // the location is found; insert target
	      arec.breakHere("Put the target at v[j]");//!
	      v[j] = target;
	}
	arec.breakHere("done sorting");//!
}

///////////////////////////////

// Shellsort: sort first N items in array A
// Etype: must have copy constructor, operator=, and operator<

//!template <class Etype>
void shellSort(DiscreteInteger[] a, int n )//!
//!void shellSort(Etype a[], int n )
{
	ActivationRecord arec = Animation.activate(getClass());//!
	for (int k = 0; k < n; ++k)//!
		arec.highlight(a[k], Color.lightGray);//!
	arec.refParam("a", a).param("n", n);//!
	arec.breakHere("starting Shell's sort");//!
	for(int Gap = n / 2; Gap > 0;
        Gap = Gap == 2 ? 1 : (int) (Gap / 2.2))
	{
		arec.pushScope();//!
		//inv: for all i in Gap..n-1, a[i] >= a[i-Gap]
		arec.var("Gap", Gap);//!
		arec.breakHere("Gap has been chosen");//!
		for( int i = Gap; i < n; i++ )
		{
			arec.var("i", new Index(i,a));//!
			for (int j = i; j >= 0; j-=Gap)//!
				arec.highlight(a[j], Color.green);//!
			arec.breakHere("'insertion sort' these elements");//!
			int Tmp = a[i].get();//!
			arec.var("Tmp",Tmp);//!
//!			Etype Tmp = a[i];
			int j = i;
			arec.var("j", new Index(j,a));//!
			arec.breakHere("where to put Tmp?");//!
			while (j >= Gap && Tmp < a[j - Gap].get())//!
//!			while (j >= Gap && Tmp < a[j - Gap])
			{
				arec.breakHere("shift a[j-Gap] up");//!
				a[j].set(a[j - Gap].get());//!
//!				a[j] = a[j - Gap];   
				j -= Gap;            
				arec.var("j", new Index(j,a));//!
				arec.breakHere("drop down Gap positions");//!
			}
			arec.breakHere("put Tmp in a[j]");//!
			a[j] = new DiscreteInteger(Tmp);//!
//!			a[j] = Tmp;
			for (int k = i; k >= 0; k-=Gap)//!
				arec.highlight(a[k], Color.lightGray);//!
		}
		arec.breakHere("The array is now " + Gap + "-sorted.");//!
		arec.popScope();//!
	}
	arec.breakHere("Finished Shell's sort");//!
}



/////////// Merge sort /////////////////


//!template<typename T>
void merge(DiscreteInteger[] v, int first, int mid, int last) {//!void merge(vector<T>& v, int first, int mid, int last) {
	ActivationRecord arec = Animation.activate(getClass());//!
	// temporary vector to merge the sorted sublists
	arec.param("v", "").param("first", first).param("mid", mid).param("last", last);//!
	arec.breakHere("starting merge");//!
	ArrayList<Integer> tempVector = new ArrayList<Integer>();//!	vector<T> tempVector;
	int indexA, indexB, indexV;

	// set indexA to scan sublistA (index range [first,mid)
	// and indexB to scan sublistB (index range [mid, last)
	indexA = first;
	indexB = mid;
	arec.var("tempVector", tempVector).var("indexA", new Index(indexA, v)).var("indexB", new Index(indexB, v));//!

	// while both sublists are not exhausted, compare v[indexA] and
	// v[indexB]copy the smaller to vector temp using push_back()
	for (int i = first; i < mid; ++i)//!
		arec.highlight(v[i], Color.yellow);//!
	for (int i = mid; i < Math.min(last, v.length); ++i)//!
		arec.highlight(v[i], Color.blue);//!
	arec.breakHere("indexA and indexB point to start of sublists");//!
	while (indexA < mid && indexB < last)
	{arec.breakHere("choose smaller of v[indexA] and v[indexB]");//!
	if (v[indexA].get() < v[indexB].get()) {//!		if (v[indexA] < v[indexB]) {
		arec.breakHere("add v[indexA] to tempVector");//!
		tempVector.add(v[indexA].get()); //!			tempVector.push_back(v[indexA]); // copy element to temp
		arec.highlight(v[indexA], Color.gray);//!
			indexA++; // increment indexA
		} else {
			arec.breakHere("add v[indexB] to tempVector");//!
			tempVector.add(v[indexB].get()); //!			tempVector.push_back(v[indexB]); // copy element to temp
			arec.highlight(v[indexB], Color.gray);//!
			indexB++; // increment indexB
		}
	arec.var("indexA", new Index(indexA, v)).var("indexB", new Index(indexB, v));//!
	arec.breakHere("repeat if both sublists are non-empty");//!
	}//!
	arec.breakHere("one or both sublists is empty");//!

	// copy the tail of the sublist that is not exhausted
	while (indexA < mid) {
		arec.breakHere("copy remaining left element");//!
		tempVector.add(v[indexA].get());//!		tempVector.push_back(v[indexA]);
		arec.highlight(v[indexA], Color.gray);//!
		indexA++;
		arec.var("indexA", new Index(indexA, v));//!
	}

	arec.breakHere("all elements have been copied from the left sublist");//!
	while (indexB < last) {
		arec.breakHere("copy remaining right element");//!
		tempVector.add(v[indexB].get());//!		tempVector.push_back(v[indexA]);
		arec.highlight(v[indexB], Color.gray);//!
		indexB++;
		arec.var("indexB", new Index(indexB, v));//!
	}

	// copy vector tempVector using indexV to vector v using indexA
	// which is initially set to first
	arec.breakHere("all elements have been copied from the right sublist");//!
	indexA = first;
	arec.var("indexA", new Index(indexA, v));//!

	// copy elements from temporary vector to original list
	arec.pushScope();//!
	for (indexV = 0; indexV < tempVector.size(); indexV++) {
		arec.var("indexV", new Index(indexV, tempVector));//!
		arec.breakHere("copy temp element back to original vector");//!
		v[indexA].set(tempVector.get(indexV));//!		v[indexA] = tempVector[indexV];
		indexA++;
	}
	arec.popScope();//!
	arec.breakHere("finished merge");//!
}




//sorts v in the index range [first,last) by merging
//ordered sublists
//!template<typename T>
void mergeSort (DiscreteInteger[] v, int first, int last) {//!void mergeSort(vector<T>& v, int first, int last) {
	ActivationRecord arec = Animation.activate(getClass());//!
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
		mergeSort(v, first, midpt);
		arec.breakHere("sort to right of center");//!
		mergeSort(v, midpt, last);
		arec.breakHere("merge sorted subarrays");//!
		merge(v, first, midpt, last);
	}
}





/////////// Quick sort /////////////////




//!template<typename T>
int pivotIndex(DiscreteInteger[] v, int first, int last) {//!int pivotIndex(vector<T>& v, int first, int last) {
	ActivationRecord arec = Animation.activate(getClass());//!
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
	ActivationRecord arec = Animation.activate(getClass());//!
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
