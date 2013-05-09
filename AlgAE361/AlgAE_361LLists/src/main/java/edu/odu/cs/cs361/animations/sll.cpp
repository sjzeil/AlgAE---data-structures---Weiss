#include <cstdlib>
#include <iostream>

using namespace std;

//
//  Based on Ford & Topp, Data Structures in C++ using STL, 2nd ed.
//
        
template <typename T>
class node {
public:
  T nodeValue;
  node<T>* next;
  
  node() : next(NULL)
  {}
  
  node (const T& item, node<T>* nextNode = NULL)
    : nodeValue(item), next(nextNode)
  {}
};


template <typename Data>
struct LListHeader {
  
  node<Data>* first;
  int size;
  
  LListHeader()
    : first(NULL), size(0)
  {}
  
  void add (const Data& x);
  void remove (const Data& x);
  void clear();

private:
  void addInOrder(const Data& x);
  void addToEnd(const Data& x);
  void addToFront(const Data& x);
  void addAfter (node<Data>* afterThis, const Data x);
  void addBefore (node<Data>* beforeThis, const Data x);
  void removeAfter (node<Data>* afterThis, const Data x);
};

template <typename Data>
std::ostream& operator<< (const std::ostream& out, const LListHeader<Data>& list)
{
  for (const node<Data>* current = list.first; current != NULL; current = current->next)
    {
      if (current != list.first)
        out << ", ";
      out << current->nodeValue;
    }
  out << "\n";
  return out;
}

template <typename Data>
void LListHeader<Data>::add (const Data& value)
{
  addInOrder (value);
  ++size;
}

template <typename Data>
void LListHeader<Data>::remove (const Data& value)
{
  node<Data>* prev = NULL;
  node<Data>* current = first;
  while (current != NULL && !(current->nodeValue == value))
  {
   prev = current;
   current = current->next;
  }
  if (current != NULL)
  {
    // We found the item we were looking for.
    if (prev == NULL)
      {
       // We are removing the first node in the list
       first = current->next;       
       delete current;
      }
    else
      removeAfter (prev);
    --size;
  }   
}


template <typename Data>
void LListHeader<Data>::clear()
{
  node<Data>* current = first;
  while (current != NULL)
    {
      node<Data>* next = current->next;
      delete current;
      current = next;
    }
  first = NULL;
  size = 0;
}

template <typename Data>
void LListHeader<Data>::addInOrder(const Data& value)
{
  if (first == NULL)
    first = new node<Data>(value, NULL);
  else 
    {
      node<Data>* current = first;
      node<Data>* prev = NULL;
      while (current != NULL && current->data < value)
        {
          prev = current;
          current = current->next;
        }
      // Add between prev and current
      if (prev == NULL)
        first = new node<Data>(value, first);
      else
        {
          addAfter (prev, value);
        }
    }
}

template <typename Data>
void LListHeader<Data>::addToEnd(const Data& value)
{
  node<Data>* newNode = new node<Data>(value, NULL);
  if (first == NULL)
    {
      first = newNode;
    }
  else
    {
      // Move to last node 
      node<Data>* current = first;
      while (current->next != NULL)
        current = current->next;
      
      // Link after that node
      current->next = newNode;
    }
}

template <typename Data>
void LListHeader<Data>::addToFront(const Data& value){
  node<Data>* newNode = new node<Data>(value, first);
  first = newNode;
}

template <typename Data>
void LListHeader<Data>::addAfter (node<Data>* afterThis, const Data value)
{
  node<Data>* newNode = new node<Data> (value, afterThis->next);
  afterThis->next = newNode;
}

template <typename Data>
void LListHeader<Data>::addBefore (node<Data>* beforeThis, const Data value)
{
  if (beforeThis == first)
    addToFront (value);
  else
    {
      // Move to front of beforeThis
      node<Data>* current = first;
      while (current->next != beforeThis)
        current = current->next;
      
      // Link after that node
      addAfter (current, value);
    }
}


template <typename Data>
void LListHeader<Data>::removeAfter (node<Data>* afterThis, const Data value)
{
  node<Data>* toRemove = afterThis->next;
  afterThis->next = toRemove->next;
  delete toRemove;
}

