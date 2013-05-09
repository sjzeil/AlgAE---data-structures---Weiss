#ifndef HASHSET_H
#define HASHSET_H

//
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
#include <utils/vislist.h>
#include <utils/visint.h>
#include <utils/visvectr.h>
#include <utils/whitebox.h>

#include <algorithm>
#include <functional>


enum HashStatus { Occupied, Empty, Deleted };

template <class T>
struct HashEntry 
{
  T data;
  HashStatus info;

  HashEntry(): info(Empty)  {}
  HashEntry(const T& v, HashStatus status)
    : data(v), info(status) {}

};


template <class T, int hSize, class HashFun, class CompareEQ=std::equal_to<T> >
class hash_set  
{
  
  typedef list<T> Container;
public:
  hash_set (): table(hSize), theSize(0) 
  {}

  bool empty() const {return theSize == 0;}

  int size() const  {return theSize;}



  bool insert (const T& element)
    {
      unsigned h0 = hash(element);
      unsigned h = find(element, h0);
     if (h == hSize) {
      unsigned count = 0;
        h = h0;
        while (table[h].info == Occupied 
               && count < hSize)
          {
           ++count;
           h = (h0 + /*f(count)*/ count) % hSize;
          }
        if (count >= hSize)
           return false;  // could not add
        else
         { 
          table[h].info = Occupied;
          table[h].data = element;
          return true;
         }
     }
       else { // replace
         table[h].data = element;
         return true;
       }
    }



  int count (const T& element)
    {
      unsigned h0 = hash(element);
      unsigned h = find(element, h0);
       return  (h != hSize) ? 1 : 0;
    }




  void erase (const T& element)
    {
      unsigned h0 = hash(element);
      unsigned h = find(element, h0);
     if (h != hSize)
          table[h].info = Deleted;
    }


  void clear()
    {
      table.clear();
      table.resize(hSize);
}
  

private:

  int find (const T& element, int h0) const
    {
      unsigned h = h0 % hSize;
      unsigned count = 0;
      while ((table[h].info == Deleted ||
              (table[h].info == Occupied 
               && (!compare(table[h].data, element))))
             && count < hSize)
        {
          ++count;
          h = (h0 + /*f(count)*/ count) % hSize;
        }
      if (count >= hSize 
          || table[h].info == Empty)
        return hSize;
      else 
        return h;
    }

  
  vector<HashEntry<T> > table;
  HashFun hash;
  CompareEQ compare;
  int theSize;
};


#endif
