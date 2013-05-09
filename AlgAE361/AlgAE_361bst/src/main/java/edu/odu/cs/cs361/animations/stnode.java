package edu.odu.cs.cs361.animations;//!




public class stnode<T> {//!



// declares a binary search tree node object
//!template <typename T>
//!class stnode
//!{
//!   public:
		// stnode is used to implement the binary search tree class
		// making the data public simplifies building the class functions

		T nodeValue;
			// node data
		stnode<T> left, right, parent;//!	stnode<T> *left, *right, *parent;
			// child pointers and pointer to the node's parent

	  
	     // constructor
//!		stnode (const T& item, stnode<T> *lptr = NULL, 
//!              stnode<T> *rptr = NULL, stnode<T> *pptr = NULL):
//!				nodeValue(item), left(lptr), right(rptr), parent(pptr)
//!		{}
		stnode (T item, stnode<T> lptr, //!
              stnode<T> rptr, stnode<T> pptr) {//!
				nodeValue = item; left = lptr;  right = rptr;  parent = pptr;//!
		}//!
//!
		stnode (T item, stnode<T> lptr, //!
	              stnode<T> rptr) {//!
					nodeValue = item; left = lptr;  right = rptr;  parent = null;//!
			}//!
	//!
		stnode (T item, stnode<T> lptr) {//!
					nodeValue = item; left = lptr;  right = null;  parent = null;//!
			}//!
	//!
		stnode (T item) {//!
			nodeValue = item; left = null;  right = null;  parent = null;//!
	}//!
//!
}//!};


