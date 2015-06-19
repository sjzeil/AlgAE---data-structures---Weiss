package edu.odu.cs.cs361.animations;//!


import edu.odu.cs.AlgAE.Animations.LocalJavaAnimationApplet;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!
import static edu.odu.cs.AlgAE.Server.LocalServer.activate;//!


public class Queue {//!


	//!#include <list>
	//!#include <vector>
		
	//using namespace std;

	//!template <typename T>
	//!class Queue
	//!{
	//!public:
		Queue ()	
		{
			start = 0;
			stop = ArraySize-1;
			theSize = 0;
			for (int i = 0; i < ArraySize; ++i) array[i] = new DiscreteString("??"); //!
		}
		
		void push (String x)//!	void push (const T& x)
		{
			ActivationRecord arec = activate(getClass());//!
			arec.param("x", x);//!
			decorate(arec).breakHere("pushing");//!
	//!		assert (theSize &lt; ArraySize);
			arec.breakHere("advance stop, wrapping if necessary");//!
			stop = (stop + 1) % ArraySize;
			arec.breakHere("fill in the data and increment the size");//!
			array[stop] = new DiscreteString(x);//!			array[stop] = x;
			theSize++;
			decorate(arec).breakHere("done pushing");//!
		}
		
		void pop ()
		{
			ActivationRecord arec = activate(getClass());//!
			decorate(arec).breakHere("popping");//!
	//!		assert (theSize &gt; 0);
			arec.breakHere("advance start, wrapping if necessary");//!
			start = (start + 1) % ArraySize;
			arec.breakHere("decrement the size");//!
			theSize--;
			decorate(arec).breakHere("done popping");//!
		}
		
		DiscreteString front()//!    T front() const
		{
			ActivationRecord arec = activate(getClass());//!
			arec.breakHere("getting top");//!
			return array[start];
		}

		void clear ()
		{
			start = stop = theSize = 0;
		}

		//!private:
		static final int ArraySize = 6;//!	static const int ArraySize = 6;
		DiscreteString[] array = new DiscreteString[6];//!	T array[8];
		int start;
		int stop;
		int theSize = 0;
	//!}

void arrayQDemo (LocalJavaAnimationApplet self)
{
	ActivationRecord arec = activate(Queue.class);//!
	Queue queue = new Queue();//!
	self.getContext().getMemoryModel().globalVar("queue", queue);//!
	arec.breakHere("queues");//!
	for (int i = 0; i < 1000; ++i)
	{
		decorate(arec).breakHere("push onto end of queue");//!
		queue.push("Adams");
		decorate(arec).breakHere("push onto end of queue");//!
		queue.push("Baker");
		decorate(arec).breakHere("push onto end of queue");//!
		queue.push("Carter");
		decorate(arec).breakHere("pop from front of queue");//!
		queue.pop();
		decorate(arec).breakHere("push onto end of queue");//!
		queue.push("Davis");
		decorate(arec).breakHere("pop from front of queue");//!
		queue.pop();
		decorate(arec).breakHere("pop from front of queue");//!
		queue.pop();
		decorate(arec).breakHere("pop from front of queue");//!
		queue.pop();
	}
}

ActivationRecord decorate (ActivationRecord arec) {
	arec.clearRenderings();
	int i = start;
	while ((stop + 1) % ArraySize != i) {
		arec.highlight(array[i]);
		i = (i + 1) % Queue.ArraySize;
	}
	return arec;
}


}//!
