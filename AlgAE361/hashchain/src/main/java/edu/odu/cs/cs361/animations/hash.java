package edu.odu.cs.cs361.animations;//!

import static edu.odu.cs.AlgAE.Server.LocalServer.activate;//!


import edu.odu.cs.AlgAE.Server.Utilities.LinkedList;//!
import java.util.ListIterator;//!

import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!
import edu.odu.cs.AlgAE.Server.Utilities.ArrayList;//!
import edu.odu.cs.AlgAE.Server.Utilities.DiscreteInteger;//!
import edu.odu.cs.AlgAE.Server.Utilities.Index;//!

public class hash<T> {//!

        
//!#ifndef HASH_CLASS
//!#define HASH_CLASS
//!
//!#include <iostream>
//!#include <vector>
//!#include <list>
//!#include <utility>
//!#include <cstdlib>
//!
//!#include "d_except.h"
//!
//!
//!template <typename T, typename HashFunc>
//!class hash
//!{
//!public:
//!  
//!#include "d_hiter.h"
//!  // hash table iterator nested classes
//!  class iterator;
//!  friend class iterator;
//!
//!  class const_iterator;
//!  friend class const_iterator;
//!  // give the iterator classes access to private
//!  // section of hash
//!
//!  class iterator
//!	{
//!  public:
//!
//!    friend class hash<T,HashFunc>;
//!    friend class const_iterator;
//!
//!    iterator()
//!    {}
//!
//!    bool operator== (const iterator& rhs) const
//!    {
//!      if (currentBucket == rhs.currentBucket)
//!        if (currentBucket == -1)
//!          // when bucket locations are = -1, don't
//!          // care about currentLoc
//!          return true;
//!        else
//!          // compare locations in the bucket
//!          return currentLoc == rhs.currentLoc;
//!      else
//!        return false;
//!    }
//!
//!    bool operator!= (const iterator& rhs) const
//!    {
//!      if (currentBucket != rhs.currentBucket)
//!        return true;
//!      else
//!        if (currentBucket == -1)
//!          // when bucket locations are = -1, don't
//!          // care about currentLoc. consider iterators
//!          // as equal
//!          return false;
//!        else
//!          // compare locations in the bucket
//!          return currentLoc != rhs.currentLoc;
//!    }
//!
//!    T& operator* ()
//!    {
//!      if (currentBucket == -1)
//!        throw referenceError("hash iterator operator *: "
//!                             "invalid reference");
//!
//!      // return the data in the list element
//!      return *currentLoc;
//!    }
//!
//!    iterator& operator++ ()
//!    {
//!      // move to the next data value or the end of
//!      // the list
//!      currentLoc++;
//!
//!      // if at end of list, call findNext() to
//!      // identify the next non-empty bucket in the table
//!      // and set currentLoc to its first element
//!      if (currentLoc == hashTable->bucket[currentBucket].end())
//!        findNext();
//!
//!      return *this;
//!    }
//!
//!    iterator operator++ (int)
//!    {
//!      // record the current state of the iterator
//!      iterator tmp = *this;
//!
//!      // move to the next data value or the end of
//!      // the list
//!      currentLoc++;
//!
//!      // if at end of list, call findNext() to
//!      // identify the next non-empty bucket in the table
//!      // and set currentLoc to its first element
//!      if (currentLoc == hashTable->bucket[currentBucket].end())
//!        findNext();
//!
//!      // return the original iterator state
//!      return tmp;
//!    }
//!
//!  private:
//!
//!    // points to the hash table container
//!    hash<T,HashFunc> *hashTable;
//!
//!    // index of current bucket being traversed
//!    int currentBucket;
//!    // points to the current element in the current bucket
//!    typename std::list<T>::iterator currentLoc;
//!
//!    iterator(hash<T,HashFunc> *ht, int b,
//!             typename std::list<T>::iterator loc):
//!      hashTable(ht), currentBucket(b), currentLoc(loc)
//!    {}
//!
//!    // find next non-empty bucket and set currentLoc
//!    // to point at its first element
//!    void findNext()
//!    {
//!      int i;
//!
//!      // search from the next bucket to end of
//!      // table for a non-empty bucket
//!      for(i=currentBucket+1; i < hashTable->numBuckets;i++)
//!        if (!hashTable->bucket[i].empty())
//!          {
//!            // found a non-empty bucket. set
//!            // currentBucket index to i and
//!            // currentLoc to point at the first
//!            // element of the list
//!            currentBucket = i;
//!            currentLoc = hashTable->bucket[i].begin();
//!            return;
//!          }
//!
//!      // we are at end()
//!      currentBucket = -1;
//!    }
//!  };
//!
//!  // the constant iterator class
//!  class const_iterator
//!  {
//!  public:
//!    friend class hash<T,HashFunc>;
//!
//!    const_iterator()
//!    {}
//!
//!    // converts a const iterator to a const_iterator
//!    const_iterator (const iterator& x):
//!      hashTable(x.hashTable),
//!      currentBucket(x.currentBucket),
//!      currentLoc(x.currentLoc)
//!    {}
//!
//!    bool operator== (const const_iterator& rhs) const
//!    {
//!      if (currentBucket == rhs.currentBucket)
//!        if (currentBucket == -1)
//!          // when bucket locations are = -1, don't
//!          // care about currentLoc
//!          return true;
//!        else
//!          // compare locations in the bucket
//!          return currentLoc == rhs.currentLoc;
//!      else
//!        return false;
//!    }
//!
//!    bool operator!= (const const_iterator& rhs) const
//!    {
//!      if (currentBucket != rhs.currentBucket)
//!        return true;
//!      else
//!        if (currentBucket == -1)
//!          // when bucket locations are = -1, don't
//!          // care about currentLoc. consider Const_iterators
//!          // as equal
//!          return false;
//!        else
//!          // compare locations in the bucket
//!          return currentLoc != rhs.currentLoc;
//!    }
//!
//!    const T& operator* () const
//!    {
//!      using namespace std;
//!      if (currentBucket == -1)
//!        {
//!          // const_iterator does not refer to valid data
//!          cerr << "hash const_iterator operator *: "
//!               << "invalid reference" << endl;
//!          exit(1);
//!        }
//!
//!      // return the data in the list element
//!      return *currentLoc;
//!    }
//!
//!    const_iterator& operator++ ()
//!    {
//!      // move to the next data value or the end of
//!      // the list
//!      currentLoc++;
//!
//!      // if at end of list, call findNext() to
//!      // identify the next non-empty bucket in the table
//!      // and set currentLoc to its first element
//!      if (currentLoc == hashTable->bucket[currentBucket].end())
//!        findNext();
//!
//!      return *this;
//!    }
//!
//!    const_iterator operator++ (int)
//!    {
//!      // record the current state of the const_iterator
//!      const_iterator tmp = *this;
//!
//!      // move to the next data value or the end of
//!      // the list
//!      currentLoc++;
//!
//!      // if at end of list, call findNext() to
//!      // identify the next non-empty bucket in the table
//!      // and set currentLoc to its first element
//!      if (currentLoc == hashTable->bucket[currentBucket].end())
//!        findNext();
//!
//!      // return the original const_iterator state
//!      return tmp;
//!    }
//!
//!  private:
//!
//!    // points to the constant hash table container
//!    const hash<T,HashFunc> *hashTable;
//!
//!    // index of current bucket being traversed
//!    int currentBucket;
//!    // points to the current element in the current bucket
//!    typename std::list<T>::const_iterator currentLoc;
//!
//!    // used to construct an iterator return value
//!    const_iterator(const hash<T,HashFunc> *ht, int b,
//!                   typename std::list<T>::const_iterator loc):
//!      hashTable(ht), currentBucket(b), currentLoc(loc)
//!    {}
//!
//!    // find next non-empty bucket and set currentLoc
//!    // to point at its first element
//!    void findNext()
//!    {
//!      int i;
//!
//!      // search from the next bucket to end of
//!      // table for a non-empty bucket
//!      for(i=currentBucket+1; i < hashTable->numBuckets;i++)
//!        if (!hashTable->bucket[i].empty())
//!          {
//!            // found a non-empty bucket. set
//!            // currentBucket index to i and
//!            // currentLoc to point at the first
//!            // element of the list
//!            currentBucket = i;
//!            currentLoc = hashTable->bucket[i].begin();
//!            return;
//!          }
//!
//!      // we are at end()
//!      currentBucket = -1;
//!    }
//!  };
//!  
//!  hash(int nbuckets, const HashFunc& hfunc = HashFunc());
//!  // constructor specifying the number of buckets in the hash table
//!  // and the hash function
//!  
//!  hash(T *first, T *last, int nbuckets, const HashFunc& hfunc = HashFunc());
//!  // constructor with arguments including a pointer range
//!  // [first, last) of values to insert, the number of
//!  // buckets in the hash table, and the hash function
//!  
//!  bool empty() const;
//!  // is the hash table empty?
//!  int size() const;
//!  // return number of elements in the hash table
//!  
//!  iterator find(const T& item);
//!  const_iterator find(const T& item) const;
//!  // return an iterator pointing at item if it is in the
//!  // table; otherwise, return end()
//!  
//!  std::pair<iterator,bool> insert(const T& item);
//!  // if item is not in the table, insert it and
//!  // return a pair whose iterator component points
//!  // at item and whose bool component is true. if item
//!  // is in the table, return a pair whose iterator
//!  // component points at the existing item and whose
//!  // bool component is false
//!  // Postcondition: the table size increases by 1 if item
//!  // is not in the table
//!  
//!  int erase(const T& item);
//!  // if item is in the table, erase it and return 1;
//!  // otherwise, return 0
//!  // Postcondition: the table size decreases by 1 if
//!  // item is in the table
//!  void erase(iterator pos);
//!  // erase the item pointed to by pos.
//!  // Precondition: the table is not empty and pos points
//!  // to an item in the table. if the table is empty, the
//!  // function throws the underflowError exception. if the
//!  // iterator is invalid, the function throws the
//!  // referenceError exception.
//!  // Postcondition: the tree size decreases by 1
//!  void erase(iterator first, iterator last);
//!  // erase all items in the range [first, last).
//!  // Precondition: the table is not empty. if the table
//!  // is empty, the function throws the underflowError
//!  // exception.
//!  // Postcondition: the size of the table decreases by
//!  // the number of elements in the range [first, last)
//!  
//!  iterator begin();
//!  // return an iterator positioned at the start of the
//!  // hash table
//!  const_iterator begin() const;
//!  // constant version
//!  iterator end();
//!  // return an iterator positioned past the last element of the
//!  // hash table
//!  const_iterator end() const;
//!  // constant version
//!  
//!private:
  int numBuckets;
  // number of buckets in the table
  ArrayList<LinkedList<T>> bucket;//!  std::vector<std::list<T> > bucket;
//!  // the hash table is a vector of lists
//!  HashFunc hf;
//!  // hash function
  int hashtableSize;//!  int hashtableSize;
//!  // number of elements in the hash table
//!};
//!
//!// constructor. create an empty hash table
//!template <typename T, typename HashFunc>
  public hash(int nbuckets) {//!hash<T, HashFunc>::hash(int nbuckets, const HashFunc& hfunc):
//!  numBuckets(nbuckets), bucket(nbuckets), hf(hfunc),
//!  hashtableSize(0)
	  numBuckets = nbuckets;//!
	  bucket = new ArrayList<LinkedList<T>>();//!
	  bucket.renderHorizontally(false);//!
	  for (int i = 0; i < nbuckets; ++i) //!
	  {//!
		  LinkedList<T> b = new LinkedList<>();//!
		  b.showBackLinks(false);//!
		  b.showFirstLast(false);
		  bucket.add (b);//!
	  }//!
	  hashtableSize = 0;//!
  }//!
//!{}
//!
//!// constructor. initialize table from pointer range [first, last)
//!template <typename T, typename HashFunc>
//!hash<T, HashFunc>::hash(T *first, T *last, int nbuckets, const HashFunc& hfunc):
//!  numBuckets(nbuckets), bucket(nbuckets), hf(hfunc),
//!  hashtableSize(0)
//!{
//!  T *p = first;
//!  
//!  while (p != last)
//!    {
//!      insert(*p);
//!      p++;
//!    }
//!}
//!
//!template <typename T, typename HashFunc>
  public boolean empty()//!bool hash<T, HashFunc>::empty() const
{
  return hashtableSize == 0;
}
//!
//!template <typename T, typename HashFunc>
  public int size()//!int hash<T, HashFunc>::size() const
{
  return hashtableSize;
}
//!
//!template <typename T, typename HashFunc>
//!typename hash<T, HashFunc>::iterator hash<T, HashFunc>::find(const T& item)
//!{
//!  // hashIndex is the bucket number (index of the linked list)
//!  int hashIndex = int(hf(item) % numBuckets);
//!  // use alias for bucket[hashIndex] to avoid indexing
//!  std::list<T>& myBucket = bucket[hashIndex];
//!  // use to traverse the list bucket[hashIndex]
//!  typename std::list<T>::iterator bucketIter;
//!  // returned if we find item
//!  
//!  // traverse list and look for a match with item
//!  bucketIter = myBucket.begin();
//!  while(bucketIter != myBucket.end())
//!    {
//!      // if locate item, return an iterator positioned in
//!      // bucket hashIndex at location bucketIter
//!      if (*bucketIter == item)
//!        return iterator(this, hashIndex, bucketIter);
//!      
//!      bucketIter++;
//!    }
//!  
//!  // return iterator positioned at the end of the hash table
//!  return end();
//!}
//!
//!template <typename T, typename HashFunc>
//!typename hash<T, HashFunc>::const_iterator
//!hash<T, HashFunc>::find(const T& item) const
public boolean find(T item)//!
{
	ActivationRecord arec = activate(getClass());//!
//!  using namespace std;

  // hashIndex is the bucket number (index of the linked list)
	arec.param("item", item);//!
	arec.breakHere("Starting find - compute the hash function");//!
	DiscreteInteger hashIndex = new DiscreteInteger(item.hashCode() % numBuckets);//!  int hashIndex = int(hf(item) % numBuckets);
  // use alias for bucket[hashIndex] to avoid indexing
	bucket.indexedBy(hashIndex, "hashIndex");//!
	arec.breakHere("Get the indicated bucket");//!
	LinkedList<T> myBucket = bucket.get(hashIndex);//!  const list<T>& myBucket = bucket[hashIndex];
	arec.refVar("myBucket",myBucket);//!
	arec.breakHere("Get ready to search myBucket");//!
  // use to traverse the list bucket[hashIndex]
	ListIterator<T> bucketIter = null;//!  typename list<T>::const_iterator bucketIter;
	arec.var("bucketIter",bucketIter);//!
  // returned if we find item
  
  // traverse list and look for a match with item

	   bucketIter = myBucket.listIterator();//!  bucketIter = myBucket.begin();
	   arec.breakHere("Start the search");//!
	   while (bucketIter.hasNext())//!  while(bucketIter != myBucket.end())
    {
      // if locate item, return an iterator positioned in
      // bucket hashIndex at location bucketIter
		   T current = bucketIter.next();//!
		   arec.var("bucketIter",bucketIter);//!
		   arec.breakHere("Is item in the bucket?");//!
		   if (current.equals(item)) {//!      if (*bucketIter == item)
			   arec.breakHere("Found it!");//!
			   return true;//!        return const_iterator(this, hashIndex, bucketIter);
		   }//!      
//!      bucketIter++;
    }
//!  
  // return iterator positioned at the end of the hash table
	   arec.breakHere("Could not find item");//!
	   return false;//!  return end();
}
//!
//!template <typename T, typename HashFunc>
//!typename std::pair<typename hash<T, HashFunc>::iterator,bool>
  public void insert(T item)//!hash<T, HashFunc>::insert(const T& item)
{
	  ActivationRecord arec = activate(getClass());//!
//!  using namespace std;
//!
  // hashIndex is the bucket number
  arec.param("item", item);//!
  arec.breakHere("Starting insert - compute the hash function");//!
  int hashIndex = item.hashCode() % numBuckets;//!  int hashIndex = int(hf(item) % numBuckets);
  arec.var("hashIndex", new Index(hashIndex, bucket));//!
  arec.breakHere("Get the indicated bucket");//!
  // for convenience, make myBucket an alias for bucket[hashIndex]
   LinkedList<T> myBucket = bucket.get(hashIndex);//!  list<T>& myBucket = bucket[hashIndex];
   arec.refVar("myBucket",myBucket);//!
   arec.breakHere("Get ready to search myBucket");//!
   //!  // use iterator to traverse the list myBucket
   ListIterator<T> bucketIter = null;//!  typename list<T>::iterator bucketIter;
   arec.var("bucketIter", bucketIter);//!
//!  // specifies whether or not we do an insert
   boolean success = false;//!  bool success;
//!  
//!  // traverse list until we arrive at the end of
//!  // the bucket or find a match with item
   bucketIter = myBucket.listIterator();//!  bucketIter = myBucket.begin();
   arec.var("bucketIter",bucketIter);//!
   T current = null;//!
   arec.var("success",success);//!
   arec.breakHere("Start the search");//!
   while (bucketIter.hasNext())//!  while (bucketIter != myBucket.end())
	   { current = bucketIter.next();//!
	   arec.breakHere("Is item already in the bucket?");//!
	   if (current.equals(item)) {//!    if (*bucketIter == item)
		   arec.breakHere("Item is already in the bucket.");//!
      break;
	   }//!
    else
    {//!
    	arec.breakHere("Keep searching for item.");//!
    	current = null;//!      bucketIter++;
    }//!  
	   }//!
   arec.breakHere("Did we find it?");//!
   if (current == null) //!  if (bucketIter == myBucket.end())
    {
      // at the end of the list, so item is not
      // in the hash table. call list class insert()
      // and assign its return value to bucketIter
	   arec.breakHere("No. Add the item to the bucket.");//!
	   bucketIter.add(item);//!      bucketIter = myBucket.insert(bucketIter, item);
      success = true;
      arec.var("success",success);//!
      // increment the hash table size 
	   arec.breakHere("And increment the table size.");//!
      hashtableSize++;
    }
  else
  {//!
    // item is in the hash table. duplicates not allowed.
    // no insertion
	   arec.breakHere("Yes. Ignore this item.");//!
    success = false;
    arec.var("success",success);//!
  }//!  
  // return a pair with iterator pointing at the new or
  // pre-existing item and success reflecting whether
  // an insert took place
   arec.breakHere("Done.");//!
//!  return pair<iterator,bool>
//!    (iterator(this, hashIndex, bucketIter), success);
}
//!
//!template <typename T, typename HashFunc>
//!void hash<T, HashFunc>::erase(iterator pos)
//!{
//!  if (hashtableSize == 0)
//!    throw underflowError("hash erase(pos): hash table empty");
//!  
//!  if (pos.currentBucket == -1)
//!    throw referenceError("hash erase(pos): invalid iterator");
//!  
//!  
//!  // go to the bucket (list object) and erase the list item
//!  // at pos.currentLoc 
//!  bucket[pos.currentBucket].erase(pos.currentLoc);
//!}
//!
//!template <typename T, typename HashFunc>
//!void hash<T, HashFunc>::erase(hash<T, HashFunc>::iterator first,
//!                              hash<T, HashFunc>::iterator last)
//!{
//!  if (hashtableSize == 0)
//!    throw underflowError("hash erase(first,last): hash table empty");
//!  
//!  // call erase(pos) for each item in the range
//!  while (first != last)
//!    erase(first++);
//!}
//!
//!template <typename T, typename HashFunc>
//!int hash<T, HashFunc>::erase(const T& item)
//!{
//!  iterator iter;
//!  int numberErased = 1;
//!  
//!  iter = find(item);
//!  if (iter != end())
//!    erase(iter);
//!  else
//!    numberErased = 0;
//!  
//!  return numberErased;
//!}
//!
//!template <typename T, typename HashFunc>
//!typename hash<T, HashFunc>::iterator hash<T, HashFunc>::begin()
//!{
//!  hash<T, HashFunc>::iterator tmp;
//!  
//!  tmp.hashTable = this;
//!  tmp.currentBucket = -1;
//!  // start at index -1 + 1 = 0 and search for a non-empty
//!  // list
//!  tmp.findNext();
//!  
//!  return tmp;
//!}
//!
//!template <typename T, typename HashFunc>
//!typename hash<T, HashFunc>::const_iterator hash<T, HashFunc>::begin() const
//!{
//!  hash<T, HashFunc>::const_iterator tmp;
//!  
//!  tmp.hashTable = this;
//!  tmp.currentBucket = -1;
//!  // start at index -1 + 1 = 0 and search for a non-empty
//!  // list
//!  tmp.findNext();
//!  
//!  return tmp;
//!}
//!
//!template <typename T, typename HashFunc>
//!typename hash<T, HashFunc>::iterator hash<T, HashFunc>::end()
//!{
//!  hash<T, HashFunc>::iterator tmp;
//!  
//!  tmp.hashTable = this;
//!  // currentBucket of -1 means we are at end of the table
//!  tmp.currentBucket = -1;
//!  
//!  return tmp;
//!}
//!
//!template <typename T, typename HashFunc>
//!typename hash<T, HashFunc>::const_iterator hash<T, HashFunc>::end() const
//!{
//!  hash<T, HashFunc>::const_iterator tmp;
//!  
//!  tmp.hashTable = this;
//!  // currentBucket of -1 means we are at end of the table
//!  tmp.currentBucket = -1;
//!  
//!  return tmp;
//!}
//!
//!#endif   // HASH_CLASS

  public void clear() {//!
	  bucket = new ArrayList<LinkedList<T>>();//!
	  for (int i = 0; i < numBuckets; ++i) //!
		  bucket.add (new LinkedList<T>());//!
	  hashtableSize = 0;//!
  }//!

  
  public void quickInsert(T item)//!
  {
    int hashIndex = item.hashCode() % numBuckets;//!
     LinkedList<T> myBucket = bucket.get(hashIndex);//!
     ListIterator<T> bucketIter = null;//!
     bucketIter = myBucket.listIterator();//!
     while (bucketIter.hasNext())//!
  	   {//!
    	 T current = bucketIter.next();//!
    	 if (current.equals(item)) {//!
    		 return;//!
    	 }//!
  	   }//!
     bucketIter.add(item);//!
     hashtableSize++;//!
  }//!
  
  
}//!
