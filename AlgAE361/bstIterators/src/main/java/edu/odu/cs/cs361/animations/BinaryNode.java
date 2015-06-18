package edu.odu.cs.cs361.animations;//!


public class BinaryNode<T> {//!

		  
		   T element;//!
		   BinaryNode<T> parent;//!
		   BinaryNode<T> left;//!
		   BinaryNode<T> right;//!
		   
		   BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt, BinaryNode<T> par )//!
		   {//!
			   element = theElement;//!
			   left = lt;//!
			   right = rt;//!
			   parent = par;//!
		   }//!
		   
		   BinaryNode(T theElement )//!
		   {//!
			   element = theElement;//!
			   left = null;//!
			   right = null;//!
			   parent = null;//!
		   }//!
		   
}//!};


