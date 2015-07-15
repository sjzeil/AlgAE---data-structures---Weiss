package edu.odu.cs.cs361.animations;//!

import java.awt.Color;//!
import java.util.ArrayList;//!

import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!
import static edu.odu.cs.AlgAE.Server.LocalServer.activate;//!




public class heaps {//!

	public class heapnode {//!
		int index;//!
		heaps heap;//!
		
		heapnode (int index, heaps heap) {//!
			this.index = index;//!
			this.heap = heap;//!
		}//!
	}//!
	
	public class Compare  {//!
		Compare() { }//!
	}//!
	
	public ArrayList<Integer> data;//!
	public ArrayList<heapnode> tree;//!
	
	public heaps () {//!
		data = new ArrayList<Integer>();//!
		tree = new ArrayList<heapnode>();//!
		resize(8);//!
	}//!
	
	public void resize (int sz)//!
	{//!
		data.clear();//!
		tree.clear();//!
		for (int i = 0; i < sz; ++i) {//!
			data.add((int)(Math.random() * (double)(4*sz) ));//!
			tree.add(new heapnode(i, this)); //!
		}//!
	}//!
	
	public void quickAdd (int k) {//!
		data.add(k);//!
		tree.add(new heapnode(tree.size(), this)); //!		
	}//!
	
	public void swap (int i, int j) //!
	{//!
		int t = data.get(i);//!
		data.set(i, data.get(j));//!
		data.set(j, t);//!
	}//!
	
	public void push_back (int x) {//!
		data.add (x);
		tree.add (new heapnode(tree.size(), this));
	}
    
	public void pop_back () {//!
		data.remove(data.size()-1);
		tree.remove(tree.size()-1);
	}

	//!template <class RandomIterator, class Compare>
void push_heap (int first, //!void push_heap (RandomIterator first,
                int last,//!                RandomIterator last,
                Compare comp)
//Pre: The range [first,last-1) is a valid heap.
//Post: Places the value in the location last-1 into the resulting
//          heap [first, last).

{
	ActivationRecord arec = activate(getClass());//!
	HeapIterator rfirst = new HeapIterator(first, this);//!
	HeapIterator rlast = new HeapIterator(last, this);//!
	arec.param("first", rfirst).param("last", rlast);//!
	arec.breakHere("starting push_heap");//!
    
  int nodeToBubble = last - 1;//!  RandomIterator nodeToBubble = last - 1;
  arec.highlight(data.get(nodeToBubble));   arec.highlight(tree.get(nodeToBubble));//!
  arec.var("nodeToBubble", new HeapIterator(nodeToBubble, this));//!
  arec.breakHere("Start bubbling from the last item");//!
  int parent = first + (nodeToBubble - first - 1)/2;//!RandomIterator parent = first + (nodeToBubble - first - 1)/2;
  arec.var("parent", new HeapIterator(parent, this));//!
  arec.breakHere("Identified first parent");//!
  while (nodeToBubble != first && data.get(parent) < data.get(nodeToBubble))//!  while (nodeToBubble != first && comp(*parent, *nodeToBubble))
     {
		arec.breakHere("Swap with parent");//!
		arec.highlight(data.get(nodeToBubble));   //!
		swap(nodeToBubble, parent); //!      swap(*nodeToBubble, *parent);
		arec.highlight(data.get(nodeToBubble));   //!
		arec.breakHere("Move up");//!
      nodeToBubble = parent;
      arec.var("nodeToBubble", new HeapIterator(nodeToBubble, this));//!
      arec.highlight(data.get(nodeToBubble));   arec.highlight(tree.get(nodeToBubble));//!
		arec.breakHere("Get new parent");//!
      parent = first + (nodeToBubble - first - 1)/2;
      arec.var("parent", new HeapIterator(parent, this));//!
      arec.breakHere("Get Continue if necessary");//!
     }
	arec.breakHere("Done");//!
}



//!template <class RandomIterator, class Compare, class Distance>
void _percolateDown 
    (int first, //!    (RandomIterator first,
     int last,//!     RandomIterator last,
     Compare comp,
     int nodeToPerc,//!     Distance nodeToPerc,
     int heapSize)//!     Distance heapSize)
{
	ActivationRecord arec = activate(getClass());//!
	HeapIterator rfirst = new HeapIterator(first, this);//!
	HeapIterator rlast = new HeapIterator(last, this);//!
	arec.param("first", rfirst).param("last", rlast).param("nodeToPerc", nodeToPerc).param("heapSize", heapSize);//!
	arec.breakHere("starting _percolateDown");//!
  while (2*nodeToPerc+1 < heapSize)
    { 
	  arec.pushScope();//!
	  arec.highlight(data.get(first+nodeToPerc)); arec.highlight(tree.get(first+nodeToPerc), Color.red);//!
	  arec.breakHere("move node down");//!
      int child1 = 2*nodeToPerc +1;//!      Distance child1 = 2*nodeToPerc +1;
      int child2 = child1+1;//!      Distance child2 = child1+1;
      arec.highlight(tree.get(first+child1), Color.yellow); if (child2 < heapSize) arec.highlight(tree.get(first+child2), Color.yellow);//!
	  arec.var("child1", child1).var("child2", child2);//!
	  arec.breakHere("Look at the two children");//!
      int largerChild = child1;//!      Distance largerChild = child1;
      arec.var("largerChild", largerChild);//!
      arec.breakHere("Look at the two children");//!
      if (child2 < heapSize 
          && data.get(first + child2) > data.get(first+child1)) {//!          && *(first + child2) > *(first+child1))
        largerChild = child2;
        arec.var("largerChild", largerChild);//!
        arec.breakHere("child2 is larger");//!
      }//!
      arec.highlight(tree.get(first+child1), Color.gray); if (child2 < heapSize) arec.highlight(tree.get(first+child2), Color.gray);//!
      if (data.get(first + largerChild) > data.get(first + nodeToPerc))//!      if (*(first + largerChild) > *(first + nodeToPerc))
        {
          arec.highlight(tree.get(first+largerChild), Color.red); //!
      	  arec.breakHere("swap with larger child");//!
          swap ((first + nodeToPerc), (first + largerChild));//!          swap (*(first + nodeToPerc), *(first + largerChild));
      	  arec.breakHere("then move down");//!
          nodeToPerc = largerChild;
          arec.param("nodeToPerc", nodeToPerc);
      	  arec.breakHere("continue");//!
        }
      else
      {//!
      	  arec.breakHere("We've gone down as far as we want.");//!
        nodeToPerc = heapSize;
        arec.param("nodeToPerc", nodeToPerc);
    	  arec.breakHere("Force an exit");//!
      }//!
      arec.popScope();//!
    }
  arec.breakHere("Done");//!
}



//!template <class RandomIterator, class Compare>
void pop_heap (int first, //!void pop_heap (RandomIterator first,
               int last,//!               RandomIterator last,
               Compare comp)
//Pre: The range [first,last) is a valid heap.
//Post: Swaps the value in location first with the value in the location
//          last-1 and makes [first, last-1) into a heap.
{
	ActivationRecord arec = activate(getClass());//!
	HeapIterator rfirst = new HeapIterator(first, this);//!
	HeapIterator rlast = new HeapIterator(last, this);//!
	arec.param("first", rfirst).param("last", rlast);//!
	arec.breakHere("starting pop_heap");//!
  swap (first, last);//!  swap (*first, *last);
	arec.breakHere("Replaced the root - now percolate it down");//!
  _percolateDown (first, last, comp, 0, last-first);
	arec.breakHere("Done");//!
}



//!template <class RandomIterator, class Compare>
void make_heap (int first, //!void make_heap (RandomIterator first,
                int last,//!                RandomIterator last,
                Compare comp)
//Pre: 
//Post: Arranges the values in [first, last) into a heap.
{
	ActivationRecord arec = activate(getClass());//!
	HeapIterator rfirst = new HeapIterator(first, this);//!
	HeapIterator rlast = new HeapIterator(last, this);//!
	arec.param("first", rfirst).param("last", rlast);//!
	arec.breakHere("starting make_heap");//!
  if (first != last)
    {
	  arec.pushScope();//!
      int i = first+(last-first-1)/2 + 1; //!      RandomIterator i = first+(last-first-1)/2 + 1;
      arec.var("i", new HeapIterator(i, this));//!
      arec.breakHere("start at first non-leaf node");//!
      while (i != first) 
        {
          --i;
          arec.var("i", new HeapIterator(i, this));//!
          arec.breakHere("percolated");//!
          _percolateDown (first, last, comp, i-first, last-first);
          arec.breakHere("percolated");//!
        }
      arec.popScope();//!
    }
	arec.breakHere("done");//!
}




//!template <class Iterator, class Compare> 
void heapsort (int first, int last, Compare comp)//!void heapsort (Iterator first, Iterator last, Compare comp)
{
	ActivationRecord arec = activate(getClass());//!
	HeapIterator rfirst = new HeapIterator(first, this);//!
	HeapIterator rlast = new HeapIterator(last, this);//!
	arec.param("first", rfirst).param("last", rlast);//!
	arec.breakHere("starting heapsort - make this data into a heap");//!
  make_heap (first, last, comp);
	arec.breakHere("We have a heap, now repeatedly pop the heap");//!
  while (last != first)
    {
	  arec.breakHere("Ready to pop the heap");//!
     pop_heap (first, last-1, comp);
     arec.breakHere("Popped. Now 'shorten' the heap and continue");//!
     --last;
     rlast = new HeapIterator(last, this);//!
     arec.param("last", rlast);//!
     arec.highlight(data.get(last), Color.lightGray);//!
    }
	arec.breakHere("Done");//!
}



//template <class Container, class Compare>
static void build_heap (heaps heap, Compare comp)//!void build_heap (Container& heap, Compare comp)
{
  heap.make_heap (0, heap.data.size(), comp);//!  make_heap (heap.begin(), heap.end(), comp);
}


//!template <class Container, class T, class Compare>
static void add_to_heap(heaps heap, int k, Compare comp)//!void add_to_heap(Container& heap, const T& k, Compare comp)
{
  ActivationRecord arec = activate(heaps.class);//!
  arec.refParam("heap", heap).param("k",k);//!
  arec.breakHere("starting add_to_heap");//!
  heap.push_back(k);
  arec.breakHere("Added " + k + " to data - now repair the heap");//!
  heap.push_heap (0, heap.data.size(), comp);//!  push_heap (heap.begin(), heap.end(), comp);
  arec.breakHere("done");//!
}


//!template <class Container, class Compare>
static void remove_from_heap(heaps heap, Compare comp)//!void remove_from_heap(Container& heap, Compare comp)
{
  ActivationRecord arec = activate(heaps.class);//!
  arec.refParam("heap", heap);//!
  arec.breakHere("starting remove_from_heap");//!
  heap.pop_heap (0, heap.data.size()-1, comp);//!  pop_heap (heap.begin(), heap.end(), comp);
  arec.breakHere("pop removed data from end");//!
  heap.pop_back();
}

}
