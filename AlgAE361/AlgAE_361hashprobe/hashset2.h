#ifndef HASHSET_H//!#ifndef HASHSET_H
#define HASHSET_H//!#define HASHSET_H
//!
////!//
//      Hash table implementation of set
//
//      Described in Chapter 17 of
//      Data Structures in C++ using the STL
//      Published by Addison-Wesley, 1997
//      Written by Tim Budd, budd@cs.orst.edu
//      Oregon State University
//
//      SJZ: Heavily modified, combined two classes,
//           used list for set container, added erase function,
//           added comparison template parameter, const's
#include <utils/vislist.h>//!#include <utils/vislist.h>
#include <utils/visint.h>//!#include <utils/visint.h>
#include <utils/visvectr.h>//!#include <utils/visvectr.h>
#include <utils/whitebox.h>//!#include <utils/whitebox.h>
//!
#include <algorithm>//!#include <algorithm>
#include <functional>//!#include <functional>
//!
//!
enum HashStatus { Occupied, Empty, Deleted };//!enum HashStatus { Occupied, Empty, Deleted };
//!
template <class T>//!template <class T>
struct HashEntry //!struct HashEntry 
{//!{
  T data;//!  T data;
  HashStatus info;//!  HashStatus info;
  //!
  HashEntry(): info(Empty)  {}//!  HashEntry(): info(Empty)  {}
  HashEntry(const T& v, HashStatus status)//!  HashEntry(const T& v, HashStatus status)
    : data(v), info(status) {}//!    : data(v), info(status) {}
  //!
};//!};
//!
//!
template <class T, int hSize, class HashFun, class CompareEQ=std::equal_to<T> >//!template <class T, int hSize, class HashFun, class CompareEQ=std::equal_to<T> >
class hash_set  //!class hash_set  
{//!{
  //!  
  typedef list<T> Container;//!  typedef list<T> Container;
public://!public:
  hash_set (): table(hSize), theSize(0) //!  hash_set (): table(hSize), theSize(0) 
  {}//!  {}
  //!
  bool empty() const {return theSize == 0;}//!  bool empty() const {return theSize == 0;}
  //!
  int size() const  {return theSize;}//!  int size() const  {return theSize;}
  //!
  //!
  //!
  bool insert (const T& element)//!  bool insert (const T& element)
  {//!    {
    unsigned h0 = hash(element);//!      unsigned h0 = hash(element);
    unsigned h = find(element, h0);//!      unsigned h = find(element, h0);
    if (h == hSize) {//!     if (h == hSize) {
      unsigned count = 0;//!      unsigned count = 0;
      h = h0;//!        h = h0;
      while (table[h].info == Occupied //!        while (table[h].info == Occupied 
	     && count < hSize)//!               && count < hSize)
	{//!          {
	  ++count;//!           ++count;
	  h = (h0 + /*f(count)*/ count) % hSize;//!           h = (h0 + /*f(count)*/ count) % hSize;
	}//!          }
      if (count >= hSize)//!        if (count >= hSize)
           return false;  // could not add//!           return false;  // could not add
      else//!        else
	{ //!         { 
          table[h].info = Occupied;//!          table[h].info = Occupied;
          table[h].data = element;//!          table[h].data = element;
          return true;//!          return true;
	}//!         }
    }//!     }
       else { // replace//!       else { // replace
         table[h].data = element;//!         table[h].data = element;
         return true;//!         return true;
       }//!       }
  }//!    }
  //!
  //!
  //!
  int count (const T& element)//!  int count (const T& element)
  {//!    {
    unsigned h0 = hash(element);//!      unsigned h0 = hash(element);
    unsigned h = find(element, h0);//!      unsigned h = find(element, h0);
    return  (h != hSize) ? 1 : 0;//!       return  (h != hSize) ? 1 : 0;
  }//!    }
  //!
  //!
  //!
  //!
  void erase (const T& element)//!  void erase (const T& element)
  {//!    {
    unsigned h0 = hash(element);//!      unsigned h0 = hash(element);
    unsigned h = find(element, h0);//!      unsigned h = find(element, h0);
    if (h != hSize)//!     if (h != hSize)
      table[h].info = Deleted;//!          table[h].info = Deleted;
  }//!    }
  //!
  //!
  void clear()//!  void clear()
  {//!    {
    table.clear();//!      table.clear();
    table.resize(hSize);//!      table.resize(hSize);
  }//!}
  //!  
  //!
private://!private:
  //!
  int find (const T& element, int h0) const//!  int find (const T& element, int h0) const
  {//!    {
    unsigned h = h0 % hSize;//!      unsigned h = h0 % hSize;
    unsigned count = 0;//!      unsigned count = 0;
    while ((table[h].info == Deleted ||//!      while ((table[h].info == Deleted ||
	    (table[h].info == Occupied //!              (table[h].info == Occupied 
	     && (!compare(table[h].data, element))))//!               && (!compare(table[h].data, element))))
	   && count < hSize)//!             && count < hSize)
      {//!        {
	++count;//!          ++count;
	h = (h0 + /*f(count)*/ count) % hSize;//!          h = (h0 + /*f(count)*/ count) % hSize;
      }//!        }
    if (count >= hSize //!      if (count >= hSize 
	|| table[h].info == Empty)//!          || table[h].info == Empty)
      return hSize;//!        return hSize;
    else //!      else 
      return h;//!        return h;
  }//!    }
  //!
  //!  
  vector<HashEntry<T> > table;//!  vector<HashEntry<T> > table;
  HashFun hash;//!  HashFun hash;
  CompareEQ compare;//!  CompareEQ compare;
  int theSize;//!  int theSize;
};//!};
//!
//!
#endif//!#endif
//!
