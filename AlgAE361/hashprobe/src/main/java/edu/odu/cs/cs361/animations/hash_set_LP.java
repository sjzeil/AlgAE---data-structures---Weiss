package edu.odu.cs.cs361.animations;//!

import static edu.odu.cs.AlgAE.Server.LocalServer.activate;//!


import java.awt.Color;//!
import java.util.LinkedList;
import java.util.List;//!

import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!
import edu.odu.cs.AlgAE.Server.Utilities.ArrayList;//!
import edu.odu.cs.AlgAE.Server.Utilities.DiscreteInteger;
//import edu.odu.cs.AlgAE.Server.Utilities.Index;//!
//!

public class hash_set_LP<T> {//!

//!#ifndef HASHSET_H
//!#define HASHSET_H

//
//      Hash table implementation of set (Linear Probing)
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
//!#include <utils/vislist.h>
//!#include <utils/visint.h>
//!#include <utils/visvectr.h>
//!#include <utils/whitebox.h>

//!#include <algorithm>
//!#include <functional>
//!
//!
enum HashStatus { Occupied, Empty, Deleted };
//!
//!template <class T>
class HashEntry implements Renderer<HashEntry>, CanBeRendered<HashEntry> //!struct HashEntry 
{
  T data;
  HashStatus info;
  
  HashEntry(){ info = (HashStatus.Empty); }//!  HashEntry(): info(Empty)  {}
  HashEntry(T v, HashStatus status)//!  HashEntry(const T& v, HashStatus status)
  { data=(v); info=(status); }//!    : data(v), info(status) {}
  //!
@Override//!
public Color getColor(HashEntry obj) {//!
	return null;//!
}//!
@Override//!
public List<Component> getComponents(HashEntry obj) {//!
	LinkedList<Component> comps = new LinkedList<Component>();//!
	comps.add (new Component(info));//!
	comps.add (new Component(data));//!
	return comps;//!
}//!
@Override//!
public List<Connection> getConnections(HashEntry obj) {//!
	return new LinkedList<Connection>();//!
}//!
@Override//!
public int getMaxComponentsPerRow(HashEntry obj) {//!
	return 1;//!
}//!
@Override//!
public String getValue(HashEntry obj) {//!
	return "";//!
}//!
@Override//!
public Renderer<HashEntry> getRenderer() {//!
	return this;//!
}//!
}//!};
//!
//!
//!template <class T, int hSize, class HashFun, class CompareEQ=std::equal_to<T> >
//!class hash_set  
//!{
  
//!public:
  hash_set_LP () { table = new ArrayList<HashEntry>(); theSize=(0); //!  hash_set (): table(hSize), theSize(0) 
  for (int i = 0; i < hSize; ++i) table.add(new HashEntry());}//!  {}
  //!
  boolean empty() {return theSize == 0;}//!  bool empty() const {return theSize == 0;}
  //!
  int size()  {return theSize;}//!  int size() const  {return theSize;}
  //!
  //!
  //!
  boolean insert (T element)//!  bool insert (const T& element)
  {
	  ActivationRecord arec = activate(getClass());//!
	  arec.param("element",element);//!
	  table.pushIndices();//!
	  arec.breakHere("Starting insert");//!
    int h0 = element.hashCode();//!      unsigned h0 = hash(element);
	  arec.var("h0", h0);//!
	  DiscreteInteger h0d = new DiscreteInteger(h0);//!
	  table.indexedBy(h0d, "h0");//!
	  arec.breakHere("Computed hashcode - search for element");//!
	  DiscreteInteger h = new DiscreteInteger(find(element, h0d));//!      unsigned h = find(element, h0);
	  arec.var("h", h);//!
	  table.indexedBy(h, "h");//!
	  arec.breakHere("Returned from find");//!
    if (h.get() == hSize) {//!     if (h == hSize) {
  	  arec.breakHere("element is not in the table - look for an empty slot in which to put it.");//!
      int count = 0;//!      unsigned count = 0;
      h.set(h0);//!        h = h0;
  	  arec.var("count", count);//!
  	  arec.breakHere("While looking count how many probes we have done.");//!
      while (table.get(h).info == HashStatus.Occupied //!        while (table[h].info == Occupied 
	     && count < hSize)//!               && count < hSize)
      {
      	arec.breakHere("table[h] is occupied - keep probing.");//!
	    ++count;
	  	  arec.var("count", count);//!
	  	h.set((h0 + /*f(count)*/ count) % hSize);//!	    h = (h0 + /*f(count)*/ count) % hSize;
	    arec.breakHere("Next possiblity for h.");//!
      }
      arec.breakHere("Finished searching.");//!
      if (count >= hSize)//!        if (count >= hSize)
      {arec.breakHere("Could not find an open slot.");//!
      table.popIndices();//!
           return false;  // could not add
      }//!
      else
      { 
    	  arec.breakHere("Put the element into slot h.");//!
          table.get(h).info = HashStatus.Occupied;//!          table[h].info = Occupied;
          table.get(h).data = element;//!          table[h].data = element;
          ++theSize;
          arec.breakHere("Done.");//!
          table.popIndices();//!
          return true;//!          return true;
      }
    }
    else { // replace
    	table.get(h).data = element;//!         table[h].data = element;
    	table.popIndices();//!
    	return true;
    }
  }
  

  boolean quickInsert (T element)//!
  {//!
    int h0 = element.hashCode();//!
    int h = quickFind(element, h0);//!
    if (h == hSize) {//!
      int count = 0;//!
      h = h0;//!
      while (table.get(h).info == HashStatus.Occupied //! 
	     && count < hSize)//!
      {//!
	    ++count;//!
	    h = (h0 + /*f(count)*/ count) % hSize;//!
      }//!
      if (count >= hSize)//!
           return false;  // could not add//!
      else//!
      { //!
          table.get(h).info = HashStatus.Occupied;//!
          table.get(h).data = element;//!
          ++theSize;//+
          return true;//!
      }//!
    }//!
    else { // replace//!
    	table.get(h).data = element;//!
    	return true;//!
    }//!
  }//!

  
  int count (T element)//!  int count (const T& element)
  {//!    {
	  ActivationRecord arec = activate(getClass());//!
	  arec.param("element",element);//!
	  table.pushIndices();//!
	  arec.breakHere("Starting count");//!
    int h0 = element.hashCode();//!      unsigned h0 = hash(element);
	  arec.var("h0", h0);//!
	  DiscreteInteger h0d = new DiscreteInteger(h0);//!
	  table.indexedBy(h0d, "h0");//!
	  arec.breakHere("Computed hashcode - search for element");//!
      DiscreteInteger h = new DiscreteInteger(find(element, h0d));//!      unsigned h = find(element, h0);
      table.indexedBy(h, "h");;
	  arec.breakHere("Returned from find - return 0 or 1");//!
	  table.popIndices();//!
    return  (h.get() != hSize) ? 1 : 0;//!    return  (h != hSize) ? 1 : 0;
  }
  
  
  
  
  void erase (T element)//!  void erase (const T& element)
  {
	  ActivationRecord arec = activate(getClass());//!
	  arec.param("element",element);//!
	  table.pushIndices();//!
	  arec.breakHere("Starting erase");//!
    DiscreteInteger h0 = new DiscreteInteger(element.hashCode());//!      unsigned h0 = hash(element);
	  arec.var("h0", h0);//!
	  table.indexedBy(h0, "h0");
	  arec.breakHere("Computed hashcode - search for element");//!
    DiscreteInteger h = new DiscreteInteger(find(element, h0));//!      unsigned h = find(element, h0);
	  table.indexedBy(h, "h");;//!
	  arec.breakHere("Returned from find");//!
    if (h.get() != hSize)//!     if (h != hSize)
    {
    	arec.breakHere("Found the element - mark its slot as Deleted");//!
    	table.get(h).info = HashStatus.Deleted;//!          table[h].info = Deleted;
    	--theSize;
    }//!
    arec.breakHere("Done");//!
    table.popIndices();//!
  }//!    }
  //!
  //!
  void clear()
  {//!    {
    //!      table.clear();
    for (int i = 0; i < hSize; ++i) table.set(i, new HashEntry());//!      table.resize(hSize);
  }//!}
  //!  
  //!
  //!private:
  //!
  int find (T element, DiscreteInteger h0)//!  int find (const T& element, int h0) const
  {//!    {
	  ActivationRecord arec = activate(getClass());//!
	  arec.param("element",element);//!
	  arec.param("h0",h0);//!
	  table.pushIndices();//!
	  table.indexedBy(h0,  "h0");//!
	  arec.breakHere("Starting insert");//!
	  DiscreteInteger h = new DiscreteInteger(h0.get() % hSize);//!      unsigned h = h0 % hSize;
	  table.indexedBy(h,  "h");//!
	  arec.var("h", h);
	  arec.breakHere("Computed hashcode - search for element");//!
    int count = 0;
	  arec.var("count", count);//!
	  arec.breakHere("While looking, count how many probes we have done.");//!
    while ((table.get(h).info == HashStatus.Deleted ||//!      while ((table[h].info == Deleted ||
	    (table.get(h).info == HashStatus.Occupied //!              (table[h].info == Occupied 
	     && (!table.get(h).data.equals(element))))//!               && (!compare(table[h].data, element))))
	     && count < hSize)
      {
      	arec.breakHere("table[h] is occupied or deleted - keep probing.");//!
    	++count;
  	    arec.var("count", count);//!
    	h.set((h0.get() + /*f(count)*/ count) % hSize);//!    	h = (h0 + /*f(count)*/ count) % hSize;
      	arec.breakHere("Next possiblity for h.");//!
     }
    arec.breakHere("Finished searching.");//!
    if (count >= hSize 
	|| table.get(h).info == HashStatus.Empty)//!          || table[h].info == Empty)
    {arec.breakHere("Could not find the element.");//!
     table.popIndices();//!
     return hSize;
    }//!
    else 
    {arec.breakHere("Found it!");//!
      table.popIndices();//!
      return h.get();//!      return h;
    }//!
  }
//!
  int quickFind (T element, int h0)//!
  {//!
    int h = h0 % hSize;//!
    int count = 0;//!
    while ((table.get(h).info == HashStatus.Deleted ||//!
	    (table.get(h).info == HashStatus.Occupied //! 
	     && (!table.get(h).data.equals(element))))//!
	     && count < hSize)//!
      {//!
    	++count;//!
    	h = (h0 + /*f(count)*/ count) % hSize;//!
      }//!
    if (count >= hSize//! 
	|| table.get(h).info == HashStatus.Empty)//!
      return hSize;//!
    else //!
      return h;//!
  }//!
  //!
  //!  
  int hSize = 11;//!
  ArrayList<HashEntry> table;//!  vector<HashEntry<T> > table;
  //!  HashFun hash;
  //!  CompareEQ compare;
  int theSize;
}//!};
//!
//!
//!#endif
//!
