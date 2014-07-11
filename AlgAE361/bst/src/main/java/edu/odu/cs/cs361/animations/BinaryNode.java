package edu.odu.cs.cs361.animations;//!


public class BinaryNode<T> {//!

		  
		   T element;//!
		   BinaryNode<T> left;//!
		   BinaryNode<T> right;//!
		   
		   BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt )//!
		   {//!
			   element = theElement;//!
			   left = lt;//!
			   right = rt;//!
		   }//!
		   
		   BinaryNode(T theElement )//!
		   {//!
			   element = theElement;//!
			   left = null;//!
			   right = null;//!
		   }//!
		   
}//!};


