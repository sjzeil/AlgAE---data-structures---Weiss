package edu.odu.cs.cs361.animations;//!

import static edu.odu.cs.AlgAE.Server.LocalServer.activate;//!

import java.util.LinkedList;//!

import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!
import edu.odu.cs.AlgAE.Server.Rendering.HorizontalRenderer;//!
import edu.odu.cs.AlgAE.Server.Utilities.SimpleReference;//!


public class BinaryTrees {//!

	tnode<String> root = null;//!
	static tnode<String> NULL = null;//!
	
// preorder recursive output of the nodes in a binary tree.
// output separator after each node value. default value
// of separator is "  "
//!template <typename T>
//!void preorderOutput(tnode<T> *t, const string& separator);
public void preorderOutput (tnode<String> t, String separator)//!
{
   ActivationRecord arec = activate(getClass());//!
   if (t != null) arec.highlight(t);//!
   arec.refParam("t", t).param("separator",separator);//!
   arec.breakHere("entered preOrder()");//!
   // the recursive scan terminates on a empty subtree
   if (t != NULL)
   {
	   arec.out().print(t.nodeValue + separator);//!cout << t->nodeValue << separator;			// output the node
	   arec.breakHere("printed - now go left");//!
	   preorderOutput(t.left, separator);	//!	   preorderOutput(t->left, separator);	// descend left
	   arec.breakHere("Returned from the left - now go right");//!
	   preorderOutput(t.right, separator);	//!	   preorderOutput(t->right, separator);	// descend right
	}
	arec.breakHere("All done here");//!
}

	
//postorder recursive output of the nodes in a binary tree.
//output separator after each node value. default value
//of separator is "  "
//!template <typename T>
//!void postorderOutput(tnode<T> *t, const string& separator);
public void postorderOutput (tnode<String> t, String separator)//!
{
ActivationRecord arec = activate(getClass());//!
if (t != null) arec.highlight(t);//!
arec.refParam("t", t).param("separator",separator).breakHere("entered postOrder()");//!
// the recursive scan terminates on a empty subtree
if (t != NULL)
{
	   arec.breakHere("First go left");//!
	   postorderOutput(t.left, separator);	//!	   postorderOutput(t->left, separator);	// descend left
	   arec.breakHere("Returned from the left - now go right");//!
	   postorderOutput(t.right, separator);	//!	   postorderOutput(t->right, separator);	// descend right
	   arec.breakHere("Back from the right - print");//!
	   arec.out().print(t.nodeValue + separator);//!cout << t->nodeValue << separator;			// output the node
	}
	arec.breakHere("All done here");//!
}


//inorder recursive output of the nodes in a binary tree.
//output separator after each node value. default value
//of separator is "  "
//!template <typename T>
//!void inorderOutput(tnode<T> *t, const string& separator);
public void inorderOutput (tnode<String> t, String separator)//!
{
ActivationRecord arec = activate(getClass());//!
if (t != null) arec.highlight(t);//!
arec.refParam("t", t).param("separator",separator).breakHere("entered postOrder()");//!
//the recursive scan terminates on a empty subtree
if (t != NULL)
{
	   arec.breakHere("First go left");//!
	   inorderOutput(t.left, separator);	//!	   inorderOutput(t->left, separator);	// descend left
	   arec.breakHere("Returned from the left - print");//!
	   arec.out().print(t.nodeValue + separator);//!cout << t->nodeValue << separator;			// output the node
	   arec.breakHere("Printed - now go right");//!
	   inorderOutput(t.right, separator);	//!	   inorderOutput(t->right, separator);	// descend right
	}
	arec.breakHere("All done here");//!
}



//!template <typename T>
@SuppressWarnings("unchecked")
void levelorderOutput(tnode<String> t, String separator)//!void levelorderOutput(tnode<T> *t, const string& separator = "  ")
{
	ActivationRecord arec = activate(getClass());//!
   // store siblings of each node in a queue so that they are
   // visited in order at the next level of the tree
   arec.refParam("t", t).param("separator",separator).breakHere("entered levelorderOutput()");//!
   LinkedList<SimpleReference> q = new LinkedList<SimpleReference>();//!   queue<tnode<T> *> q;
   arec.render(new HorizontalRenderer<LinkedList<SimpleReference>>(q));//!
   tnode<String> p = null;//!   tnode<T> *p;

   // initialize the queue by inserting the root in the queue
   arec.var("q", q).refVar("p", p).breakHere("initialize the queue");//!
   q.add(new SimpleReference(t, 160, 200));//!   q.push(t);

   arec.breakHere("ready to enter loop");//!
   // continue the iterative process until the queue is empty
   while(!q.isEmpty())//!   while(!q.empty())
   {
      // delete front node from queue and output the node value
	   arec.breakHere("get front element");//!
	   p = (tnode<String>)q.getFirst().get();//!      p = q.front();
	   arec.refVar("p", p).breakHere("remove front element from queue");//!
	   q.pop();//!	  q.pop();
	   arec.breakHere("print");//!
	   arec.out().print(p.nodeValue + separator);//!      cout << p->nodeValue << separator;
		// if a left child exists, insert it in the queue
	   arec.breakHere("add left to queue");//!
      if(p.left != NULL)//!      if(p->left != NULL)
			q.add(new SimpleReference(p.left,  160, 200));//!			q.push(p->left);
      // if a right child exists, insert next to its sibling
	   arec.breakHere("add right to queue");//!
      if(p.right != NULL)//!      if(p->right != NULL)
			q.add(new SimpleReference(p.right, 160, 200));//!			q.push(p->right);

   }
	arec.breakHere("All done here");//!
}



			
}
