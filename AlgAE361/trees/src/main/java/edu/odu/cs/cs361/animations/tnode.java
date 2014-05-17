package edu.odu.cs.cs361.animations;//!




public class tnode<T> {//!



//!template <typename T>
//!class tnode 
//!{
//!public:
	// tnode is a class implementation structure. making the
	// data public simplifies building class functions
	  T nodeValue;	//!  T nodeValue;
	  tnode<T> left;//!  tnode<T> *left, *right;
	  tnode<T> right;//!
	  
	// default constructor. data not initialized
	  tnode()
	  {}

    // initialize the data members
		tnode (T item, tnode<T> lptr,//!		tnode (const T& item, tnode<T> *lptr = NULL, 
				 tnode<T> rptr) {//!				 tnode<T> *rptr = NULL):
//!					nodeValue(item), left(lptr), right(rptr)
			nodeValue = item; left = lptr; right = rptr; //!        {
		}
//!		
		tnode (T item, tnode<T> lptr) {//!
			nodeValue = item; left = lptr; right = null;//!
		}//!
//!		
		tnode (T item) {//!
			nodeValue = item; left = null; right = null;//!
		}//!
//!		
}//!};


