package edu.odu.cs.cs361.animations;//!

import static edu.odu.cs.AlgAE.Server.Animations.LocalJavaAnimation.activate;//!

import java.awt.Color;//!
import java.util.LinkedList;//!
import java.util.List;//!

import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Component;//!
import edu.odu.cs.AlgAE.Server.MemoryModel.Connection;//!
import edu.odu.cs.AlgAE.Server.Rendering.CanBeRendered;//!
import edu.odu.cs.AlgAE.Server.Rendering.Renderer;//!
import edu.odu.cs.AlgAE.Server.Utilities.SimpleReference;//!
//!
//!




public class stree<T extends Comparable<T>> {//!
    stnode<T> NULL = null; //!
//!template <typename T>
//!class stree
//!{
//!    public:

//!    friend class iterator;
//!    friend class const_iterator;
        // allow the iterator classes to access the private section
        // of stree

    class iterator
    implements CanBeRendered<iterator>, Renderer<iterator> //!
    {
//!        friend class stree<T>;
//!        friend class const_iterator;

//!        public:

            // constructor
            iterator ()
            {}

            // comparison operators. just compare node pointers
            public boolean equals (Object rhso)//!            bool operator== (const iterator& rhs) const
            {
                @SuppressWarnings("unchecked")
				iterator rhs = (iterator)rhso;//!
                return nodePtr == rhs.nodePtr;
            }

//!            bool operator!= (const iterator& rhs) const
//!            {
//!                return nodePtr != rhs.nodePtr;
//!            }

            // dereference operator. return a reference to
            // the value pointed to by nodePtr
            T get() //!            T& operator* () const
            {
                if (nodePtr == NULL)
                     throw
//!                        referenceError("stree iterator operator* (): NULL reference");
                     new NullPointerException ("stree iterator operator* (): NULL reference");

                return nodePtr.nodeValue;//!                return nodePtr->nodeValue;
            }

            // preincrement. move forward to next larger value
            iterator increment ()//!            iterator& operator++ ()
            {
            	ActivationRecord arec = activate(getClass());//!
                stnode<T> p = null;//!                stnode<T> *p;

            	arec.refVar("p", p).breakHere("entered operator++()");//!
                if (nodePtr == NULL)
                {
                    // ++ from end(). get the root of the tree
                	arec.breakHere("++end() is undefined - we can do whatever we like..");//!
                    nodePtr = tree.root;//!                    nodePtr = tree->root;

                    // error! ++ requested for an empty tree
                    if (nodePtr == NULL)
                        throw
//!                            underflowError("stree iterator operator++ (): tree empty");
                    new IndexOutOfBoundsException("stree iterator operator++ (): tree empty");//!

                    // move to the smallest value in the tree,
                    // which is the first node inorder
                	arec.breakHere("..so for lack of anything better to do, we will return begin()");//!
                    while (nodePtr.left != NULL)//!                    while (nodePtr->left != NULL)
                    {arec.breakHere("step downto the left");//!
                    	nodePtr = nodePtr.left;//!                        nodePtr = nodePtr->left;
                    }//!
                }
                else
                {arec.breakHere("'normal' ++ processing");//!
                if (nodePtr.right != NULL)//!                if (nodePtr->right != NULL)
                {
                    // successor is the furthest left node of
                    // right subtree
                	arec.breakHere("step to the right");//!
                    nodePtr = nodePtr.right;//!                    nodePtr = nodePtr->right;

                	arec.breakHere("then run to the left");//!
                    while (nodePtr.left != NULL)//!                    while (nodePtr->left != NULL)
                    {arec.breakHere("running to the left");//!
                        nodePtr = nodePtr.left;//!                        nodePtr = nodePtr->left;
                    }//!
                }
                else
                {
                    // have already processed the left subtree, and
                    // there is no right subtree. move up the tree,
                    // looking for a parent for which nodePtr is a left child,
                    // stopping if the parent becomes NULL. a non-NULL parent
                    // is the successor. if parent is NULL, the original node
                    // was the last node inorder, and its successor
                    // is the end of the list
                	arec.breakHere("we need to move up...");//!
                    p = nodePtr.parent;//!                    p = nodePtr->parent;

                	arec.refVar("p", p).breakHere("...until we back up over a left pointer");//!
                    while (p != NULL && nodePtr == p.right)//!                    while (p != NULL && nodePtr == p->right)
                    {
                    	arec.refVar("p", p).breakHere("keep moving up");//!
                        nodePtr = p;
                        p = p.parent;//!                        p = p->parent;
                    	arec.refVar("p", p).breakHere("did we pass over a left pointer?");//!
                    }

                    // if we were previously at the right-most node in
                    // the tree, nodePtr = NULL, and the iterator specifies
                    // the end of the list
                	arec.breakHere("move up that final step");//!
                    nodePtr = p;
                }
                }
            	arec.refVar("p", p).breakHere("finsihed operator++");//!
                return this;//!!                return *this;
            }

            // postincrement
            iterator post_increment ()//!            iterator operator++ (int)
            {
                // save current iterator
                iterator tmp = this;//!                iterator tmp = *this;

                // move myself forward to the next tree node
                increment();//!                ++*this;

                // return the previous value
                return tmp;
            }

            // predecrement. move backward to largest value < current value
//            iterator& operator-- ()
//!            {
//!                stnode<T> *p;

//!                if (nodePtr == NULL)
//!                {
                    // -- from end(). get the root of the tree
//!                    nodePtr = tree->root;

                    // error! -- requested for an empty tree
//!                    if (nodePtr == NULL)
//!                        throw
//!                            underflowError("stree iterator operator--: tree empty");

                    // move to the largest value in the tree,
                    // which is the last node inorder
//!                    while (nodePtr->right != NULL)
//!                        nodePtr = nodePtr->right;
//!                } else if (nodePtr->left != NULL)
//!                {
                    // must have gotten here by processing all the nodes
                    // on the left branch. predecessor is the farthest
                    // right node of the left subtree
//!                    nodePtr = nodePtr->left;

//!                    while (nodePtr->right != NULL)
//!                        nodePtr = nodePtr->right;
//!                }
//!                else
//!                {
//                    // must have gotten here by going right and then
                    // far left. move up the tree, looking for a parent
                    // for which nodePtr is a right child, stopping if the
                    // parent becomes NULL. a non-NULL parent is the
                    // predecessor. if parent is NULL, the original node
                    // was the first node inorder, and its predecessor
                    // is the end of the list
//!                    p = nodePtr->parent;
//!                    while (p != NULL && nodePtr == p->left)
//!                    {
//!                        nodePtr = p;
//!                        p = p->parent;
//!                    }

                    // if we were previously at the left-most node in
                    // the tree, nodePtr = NULL, and the iterator specifies
                    // the end of the list
//!!                    nodePtr = p;
//!                }

//!                return *this;
//!            }

            // postdecrement
//!            iterator operator-- (int)
//!            {
                // save current iterator
//!                iterator tmp = *this;

                // move myself backward to the previous tree node
//!                --*this;

                // return the previous value
//!                return tmp;
//!            }

//!        private:

            // nodePtr is the current location in the tree. we can move
            // freely about the tree using left, right, and parent.
            // tree is the address of the stree object associated
            // with this iterator. it is used only to access the
            // root pointer, which is needed for ++ and --
            // when the iterator value is end()
            stnode<T> nodePtr;//!            stnode<T> *nodePtr;
            stree<T> tree;//!            stree<T> *tree;

            // used to construct an iterator return value from
            // an stnode pointer
//!            iterator (stnode<T> *p, stree<T> *t) : nodePtr(p), tree(t)
            iterator (stnode<T> p, stree<T> t) { nodePtr = (p); tree = (t); }//!
//!            {}
            //!
    		@Override//!
    		public Color getColor(iterator obj) {//!
    			return null;//!
    		}//!
//!
    		@Override//!
    		public List<Component> getComponents(iterator obj) {//!
    			LinkedList<Component> comps = new LinkedList<Component>();//!
    			comps.add (new Component(new SimpleReference(tree, 270, 270), "tree"));//!
    			comps.add (new Component(new SimpleReference(nodePtr, 270, 315), "nodePtr"));//!
    			return comps;//!
    		}//!
//!
    		@Override//!
    		public List<Connection> getConnections(iterator obj) {//!
    			return  new LinkedList<Connection>();//!
    		}//!
//!
    		@Override//!
    		public int getMaxComponentsPerRow(iterator obj) {//!
    			return 1;//!
    		}//!
//!
    		@Override//!
    		public String getValue(iterator obj) {//!
    			return "";//!
    		}//!
//!
//!
			@Override//!
			public Renderer<iterator> getRenderer() {//!
				return this;//!
			}//!
    }//!    };

    class const_iterator extends iterator//!    class const_iterator
    {
//!        friend class stree<T>;

//!        public:

            // constructor
            const_iterator ()
            {}

            // used to convert a const iterator to a const_iterator
//!            const_iterator (const iterator& pos): nodePtr(pos.nodePtr)
//!            {}

            // comparison operators. just compare node pointers
//!            bool operator== (const const_iterator& rhs) const
//!            {
//!                return nodePtr == rhs.nodePtr;
//!            }

//!            bool operator!= (const const_iterator& rhs) const
//!            {
//!                return nodePtr != rhs.nodePtr;
//!            }

            // dereference operator. return a reference to
            // the value pointed to by nodePtr
//!            const T& operator* () const
//!            {
//!!                if (nodePtr == NULL)
//!                     throw
//!                        referenceError("stree const_iterator operator* (): NULL reference");
//!
//!                return nodePtr->nodeValue;
//!            }

            // preincrement. move forward to next larger value
//!            const_iterator& operator++ ()
//!            {
//!                stnode<T> *p;

//!                if (nodePtr == NULL)
//!                {
                    // ++ from end(). get the root of the tree
//!                    nodePtr = tree->root;

                    // error! ++ requested for an empty tree
//!                    if (nodePtr == NULL)
//!                        throw underflowError("stree const_iterator operator++ (): tree empty");

                    // move to the smallest value in the tree,
                    // which is the first node inorder
//!                    while (nodePtr->left != NULL)
//!                        nodePtr = nodePtr->left;
//!                }
//!                else
//!                if (nodePtr->right != NULL)
//!                {
                    // successor is the furthest left node of
                    // right subtree
//!                    nodePtr = nodePtr->right;

//!                    while (nodePtr->left != NULL)
//!                        nodePtr = nodePtr->left;
//!                }
//!                else
//!                {
                    // have already processed the left subtree, and
                    // there is no right subtree. move up the tree,
                    // looking for a parent for which nodePtr is a left child,
                    // stopping if the parent becomes NULL. a non-NULL parent
                    // is the successor. if parent is NULL, the original node
                    // was the last node inorder, and its successor
                    // is the end of the list
//!                    p = nodePtr->parent;

//!                    while (p != NULL && nodePtr == p->right)
//!                    {
//!                        nodePtr = p;
//!                        p = p->parent;
//!                    }

                    // if we were previously at the right-most node in
                    // the tree, nodePtr = NULL, and the iterator specifies
                    // the end of the list
//!                    nodePtr = p;
//!                }

//!                return *this;
//!            }

            // postincrement
//!            const_iterator operator++ (int)
//!            {
//!                // save current const_iterator
//!                const_iterator tmp = *this;

//!                // move myself forward to the next tree node
//!                ++*this;

                // return the previous value
//!                return tmp;
//!            }

            // predecrement. move backward to largest value < current value
//!            const_iterator& operator-- ()
//!            {
//!            stnode<T> *p;

//!                if (nodePtr == NULL)
//!                {
                    // -- from end(). get the root of the tree
//!                    nodePtr = tree->root;

                    // error! -- requested for an empty tree
//!                    if (nodePtr == NULL)
//!                        throw
//!                            underflowError("stree iterator operator--: tree empty");

                    // move to the largest value in the tree,
                    // which is the last node inorder
//!                    while (nodePtr->right != NULL)
//!                        nodePtr = nodePtr->right;
//!                } else if (nodePtr->left != NULL)
//                {
                    // must have gotten here by processing all the nodes
                    // on the left branch. predecessor is the farthest
                    // right node of the left subtree
//!                    nodePtr = nodePtr->left;

//!                    while (nodePtr->right != NULL)
//!                        nodePtr = nodePtr->right;
//!                }
//!                else
//!                {
                    // must have gotten here by going right and then
                    // far left. move up the tree, looking for a parent
                    // for which nodePtr is a right child, stopping if the
                    // parent becomes NULL. a non-NULL parent is the
                    // predecessor. if parent is NULL, the original node
                    // was the first node inorder, and its predecessor
                    // is the end of the list
//!                    p = nodePtr->parent;
//!                    while (p != NULL && nodePtr == p->left)
//!                    {
//!                        nodePtr = p;
//!                        p = p->parent;
//!                    }

                    // if we were previously at the left-most node in
                    // the tree, nodePtr = NULL, and the iterator specifies
                    // the end of the list
//!                    nodePtr = p;
//!                }

//!                return *this;
//!            }

            // postdecrement
//!            const_iterator operator-- (int)
//!            {
                // save current const_iterator
//!                const_iterator tmp = *this;

                // move myself backward to the previous tree node
//!                --*this;

                // return the previous value
//!                return tmp;
//!            }

//!        private:

            // nodePtr is the current location in the tree. we can move
            // freely about the tree using left, right, and parent.
            // tree is the address of the stree object associated
            // with this iterator. it is used only to access the
            // root pointer, which is needed for ++ and --
            // when the iterator value is end()
//!            const stnode<T> *nodePtr;
//!            const stree<T> *tree;

            // used to construct a const_iterator return value from
            // an stnode pointer
//!            const_iterator (const stnode<T> *p, const stree<T> *t) : nodePtr(p), tree(t)
//!            {}

    }//!    };

//!        stree();
            // constructor. initialize root to NULL and size to 0
//!        stree(T *first, T *last);
            // constructor. insert the elements from the pointer
            // range [first, last) into the tree
//!        stree(const stree<T>& tree);
            // copy constructor
//!        ~stree();
            // destructor
//!        stree<T>& operator= (const stree<T>& rhs);
            // assignment operator

//!        iterator find(const T& item);
            // search for item. if found, return an iterator pointing
            // at it in the tree; otherwise, return end()
//!        const_iterator find(const T& item) const;
            // constant version

//!        int empty() const;
            // indicate whether the tree is empty
//!        int size() const;
            // return the number of data items in the tree

//!        pair<iterator, bool> insert(const T& item);
            // if item is not in the tree, insert it and
            // return a pair whose iterator component points
            // at item and whose bool component is true. if item
            // is in the tree, return a pair whose iterator
            // component points at the existing item and whose
            // bool component is false
            // Postcondition: the tree size increases by 1 if item
            // is not in the tree

//!        int erase(const T& item);
            // if item is in the tree, erase it and return 1;
            // otherwise, return 0
            // Postcondition: the tree size decreases by 1 if
            // item is in the tree

//!        void erase(iterator pos);
            // erase the item pointed to by pos.
            // Preconditions: the tree is not empty and pos points
            // to an item in the tree. if the tree is empty, the
            // function throws the underflowError exception. if the
            // iterator is invalid, the function throws the referenceError
            // exception.
            // Postcondition: the tree size decreases by 1

//!        void erase(iterator first, iterator last);
            // erase all items in the range [first, last).
            // Precondition: the tree is not empty. if the tree
            // is empty, the function throws the underflowError
            // exception.
            // Postcondition: the size of the tree decreases by
            // the number of elements in the range [first, last)

//!        iterator begin();
            // return an iterator pointing to the first item
            // inorder
//!        const_iterator begin() const;
            // constant version
//!        iterator end();
            // return an iterator pointing just past the end of
            // the tree data
//!        const_iterator end() const;
            // constant version

//!        void displayTree(int maxCharacters);
            // tree display function. maxCharacters is the
            // largest number of characters required to draw
            // the value of a node

//!    private:
        stnode<T> root;//!        stnode<T> *root;
            // pointer to tree root
        int treeSize;
            // number of elements in the tree

//!        stnode<T> *getSTNode(const T& item,
//!                                    stnode<T> *lptr,stnode<T> *rptr, stnode<T> *pptr);
            // allocate a new tree node and return a pointer to it.
            // if memory allocation fails, the function throws the
            // memoryAllocationError exception

//!!        stnode<T> *copyTree(stnode<T> *t);
            // recursive function used by copy constructor and assignment
            // operator to assign the current tree as a copy of another tree

//!        void deleteTree(stnode<T> *t);
            // recursive function used by destructor and assignment
            // operator to delete all the nodes in the tree

//!        stnode<T> *findNode(const T& item) const;
            // search for item in the tree. if it is in the tree,
            // return a pointer to its node; otherwise, return NULL.
            // used by find() and erase()

//!};

        
//!template <typename T>
//!stnode<T> *stree<T>::getSTNode(const T& item,
//!			stnode<T> *lptr,stnode<T> *rptr, stnode<T> *pptr)
stnode<T> getSTNode(T item,//!
        	stnode<T> lptr, stnode<T> rptr, stnode<T> pptr)//!
{
   	stnode<T> newNode;//!   	stnode<T> *newNode;

   	// initialize the data and all pointers
   	newNode = new stnode<T> (item, lptr, rptr, pptr);
   	if (newNode == NULL)
//!   		throw memoryAllocationError("stree: memory allocation failure");
   		throw new RuntimeException("stree: memory allocation failure");//!
   	return newNode;
}
        

//!template <typename T>
stree() { root = (NULL);treeSize=(0);}//!stree<T>::stree(): root(NULL),treeSize(0)
//!{}

//!template <typename T>
//!stree<T>::stree(T *first, T *last): root(NULL),treeSize(0)
//!{
//!    T *p = first;

    // insert each item in [first, last) into the tree
//!    while (p != last)
//!    {
//!        insert(*p);
//!!        p++;
//!    }
//!}

//!template <typename T>
//!stree<T>::stree(const stree<T>& tree): treeSize(tree.treeSize)
//!{
    // copy tree to the current object
//!    root = copyTree(tree.root);
//!}

//!!template <typename T>
//!stree<T>::~stree()
//!{
    // erase the tree nodes from memory
//!    deleteTree(root);

    // tree is emtpy
//!    root = NULL;
//!    treeSize = 0;
//!}

//!template <typename T>
//!stree<T>& stree<T>::operator= (const stree<T>& rhs)
//!{
    // can't copy a tree to itself
//!    if (this == &rhs)
//!        return *this;

    // erase the existing tree nodes from memory
//!    deleteTree(root);

    // copy tree rhs into current object
//!    root = copyTree(rhs.root);

    // set the tree size
//!    treeSize = rhs.treeSize;

    // return reference to current object
//!    return *this;
//!}


// search for data item in the tree. if found, return its node
// address; otherwise, return NULL
//!template <typename T>
stnode<T> findNode(T item) //!stnode<T> *stree<T>::findNode(const T& item) const
{   
	ActivationRecord arec = activate(getClass());//!
	arec.param("item", item).breakHere("entered findNode()");//!
	// cycle t through the tree starting with root
	stnode<T> t = root;//!	stnode<T> *t = root;
	arec.refVar("t", t).breakHere("start at the root");//!

	// terminate on on empty subtree
	while(t != NULL && !(item.compareTo(t.nodeValue) == 0))//!	while(t != NULL && !(item == t->nodeValue))
	{	arec.highlight(t);arec.refVar("t", t).breakHere("which way should we go from t?");//!
		if (item.compareTo(t.nodeValue) < 0)//!		if (item < t->nodeValue)
		{arec.breakHere("go to the left");//!
			t = t.left;//!			t = t->left;
		}//!
		else 
		{arec.breakHere("go to the right");//!
			t = t.right;//!			t = t->right;
		}//!
	}//!
	
	// return pointer to node; NULL if not found
	if (t != null) arec.highlight(t);//!
	arec.refVar("t", t).breakHere("done");//!
	return t;
}

//!template <typename T>
iterator find(T item)//!stree<T>::iterator stree<T>::find(const T& item)
{
	ActivationRecord arec = activate(getClass());//!
    stnode<T> curr = null;//!    stnode<T> *curr;
	arec.param("item", item).refVar("curr", curr).breakHere("entered find()");//!

    // search tree for item
    curr = findNode (item);

    // if item found, return const_iterator with value current;
    // otherwise, return end()
	arec.refVar("curr", curr).breakHere("returned from findNode()");//!
    if (curr != NULL)
    { arec.breakHere("return an iterator built from curr");//!
        return new iterator(curr, this);//!        return iterator(curr, this);
    }//!
    else
    { arec.breakHere("did not find " + item + ", return end()");//!
        return end();
    }//!
}

//!template <typename T>
//!stree<T>::const_iterator stree<T>::find(const T& item) const
//!{
//!    stnode<T> *curr;
//!
//!    // search tree for item
//!    curr = findNode (item);
//!
//!    // if item found, return const_iterator with value current;
//!    // otherwise, return end()
//!    if (curr != NULL)
//!        return const_iterator(curr, this);
//!    else
//!        return end();
//!}

//!template <typename T>
boolean empty() //!int stree<T>::empty() const
{
    return root == NULL;
}

//!template <typename T>
int size() //!int stree<T>::size() const
{
    return treeSize;
}

//!template <typename T>
pair<iterator, Boolean> insert(T item)//!pair<stree<T>::iterator, bool> stree<T>::insert(const T& item)
{
	ActivationRecord arec = activate(getClass());//!
    // t is current node in traversal, parent the previous node
	arec.param("item", item).breakHere("entered insert()");//!
    stnode<T> t = root; stnode<T> parent = NULL; stnode<T> newNode = null;//!    stnode<T> *t = root, *parent = NULL, *newNode;

    // terminate on on empty subtree
	arec.refVar("t", t).refVar("parent", parent).refVar("newNode", newNode).breakHere("search for insertion point()");//!
    while(t != NULL)
    {
        // update the parent pointer. then go left or right
    	arec.breakHere("update parent pointer");//!
        parent = t;
        // if a match occurs, return a pair whose iterator
        // component points at item in the tree and whose
        // bool component is false
    	arec.refVar("parent", parent).breakHere("Which way do we go?");//!
        if (item.compareTo(t.nodeValue) == 0)//!        if (item == t->nodeValue)
        	{arec.breakHere("We found a duplicate item. Stop here.");//!
            return new pair<iterator, Boolean> (new iterator(t, this), false);//!            return pair<iterator, bool> (iterator(t, this), false);
        	}//!
        else if (item.compareTo (t.nodeValue) < 0)//!        else if (item < t->nodeValue)
    	{arec.breakHere("Go left.");//!
            t = t.left;//!            t = t->left;
            arec.refVar("t", t);//!
    	}//!
        else 
    	{arec.refVar("t", t).refVar("parent", parent).refVar("newNode", newNode).breakHere("Go right.");//!
            t = t.right;//!            t = t->right;
            arec.refVar("t", t);//!
    	}//!
    }
    
    // create the new leaf node
    arec.breakHere("We know where we want to put the data. Create the new node");//!
    newNode = new stnode<T>(item,NULL,NULL,parent);

    // if parent is NULL, insert as root node
    arec.refVar("newNode", newNode).breakHere("is this a new root?");//!
    if (parent == NULL)
        root = newNode;
    else if (item.compareTo(parent.nodeValue) < 0) //!    else if (item < parent->nodeValue)
    {arec.breakHere("insert as left child");//!
        // insert as left child        
        parent.left = newNode;  //!        parent->left = newNode;
    }//!
    else
    {arec.breakHere("insert as right child");//!
        // insert as right child     
        parent.right = newNode;//!        parent->right = newNode;
    }//!
    
    // increment size
    arec.breakHere("Done with the tree. Update the size and return.");//!
    treeSize++;

    // return an pair whose iterator component points at
    // the new node and whose bool component is true
    return new pair<iterator, Boolean> (new iterator(newNode, this), true);//!    return pair<iterator, bool> (iterator(newNode, this), true);
}

//!template <typename T>
void erase(iterator pos)//!void stree<T>::erase(iterator pos)
{
	ActivationRecord arec = activate(getClass());//!
	arec.param("pos", pos).breakHere("entered erase()");//!
    // dNodePtr = pointer to node D that is deleted
    // pNodePtr = pointer to parent P of node D
    // rNodePtr = pointer to node R that replaces D
    stnode<T> dNodePtr = pos.nodePtr, pNodePtr=null, rNodePtr=null;//!    stnode<T> *dNodePtr = pos.nodePtr, *pNodePtr, *rNodePtr;

	arec.refVar("dNodePtr",dNodePtr).refVar("pNodePtr", pNodePtr).refVar("rNodePtr", rNodePtr).breakHere("Watch for possible errors");//!
    if (treeSize == 0)
         throw
//!            underflowError("stree erase(): tree is empty");
         new RuntimeException("stree erase(): tree is empty");
    
    if (dNodePtr == NULL)
         throw
//!            referenceError("stree erase(): invalid iterator");
         new NullPointerException("stree erase(): invalid iterator");


    // assign pNodePtr the address of P
	arec.breakHere("Get the parent of D");//!
    pNodePtr = dNodePtr.parent;//!    pNodePtr = dNodePtr->parent;
    arec.param("pos", pos).refVar("pNodePtr", pNodePtr);//!
    
    // If D has a NULL pointer, the
    // replacement node is the other child
    arec.breakHere("How many null children does D have?");//!
    if (dNodePtr.left == NULL || dNodePtr.right == NULL)//!    if (dNodePtr->left == NULL || dNodePtr->right == NULL)
    {
        arec.breakHere("D has a null child. Replace D by the other child.");//!
        if (dNodePtr.right == NULL)//!        if (dNodePtr->right == NULL)
        {arec.breakHere("Replace D by the left child.");//!
            rNodePtr = dNodePtr.left;//!            rNodePtr = dNodePtr->left;
            arec.refVar("rNodePtr", rNodePtr);//!
        }//!
        else
        {arec.breakHere("Replace D by the right child.");//!
            rNodePtr = dNodePtr.right;//!            rNodePtr = dNodePtr->right;
            arec.refVar("rNodePtr", rNodePtr);//!
        }//!

        arec.breakHere("Change parent of the replacement");//!
        if (rNodePtr != NULL)
            // the parent of R is now the parent of D
            rNodePtr.parent = pNodePtr;//!            rNodePtr->parent = pNodePtr;
        arec.refVar("rNodePtr", rNodePtr).breakHere("Done updating the tree");//!
    }
    // both pointers of dNodePtr are non-NULL.
    else
    {
    	arec.pushScope();//!
        arec.breakHere("D has no null children.");//!
        // find and unlink replacement node for D.
        // starting at the right child of node D,
        // find the node whose value is the smallest of all
        // nodes whose values are greater than the value in D.
        // unlink the node from the tree.
  
        // pOfRNodePtr = pointer to parent of replacement node
        arec.breakHere("Store parent of replacement node.");//!
        stnode<T> pOfRNodePtr = dNodePtr;//!        stnode<T> *pOfRNodePtr = dNodePtr;

        // first possible replacement is right child of D
        arec.refVar("pOfRNodePtr", pOfRNodePtr).breakHere("Step to the right.");//!
        rNodePtr = dNodePtr.right;//!        rNodePtr = dNodePtr->right;
        arec.refVar("rNodePtr", rNodePtr);//!
        
        // descend down left subtree of the right child of D,
        // keeping a record of current node and its parent.
        // when we stop, we have found the replacement
        arec.breakHere("Then run to the left.");//!
        while(rNodePtr.left != NULL)//!        while(rNodePtr->left != NULL)
        {
            arec.breakHere("Running to the left.");//!
            pOfRNodePtr = rNodePtr;
            rNodePtr = rNodePtr.left;//!            rNodePtr = rNodePtr->left;
            arec.refVar("rNodePtr", rNodePtr).refVar("pOfRNodePtr", pOfRNodePtr);//!
        }
  
        arec.breakHere("rNodePtr points to data that we could use where D is now.");//!
        if (pOfRNodePtr == dNodePtr)
        {
            // right child of deleted node is the replacement.
            // assign left subtree of D to left subtree of R
            arec.breakHere("Move the replacement node.");//!
            rNodePtr.left = dNodePtr.left;//!            rNodePtr->left = dNodePtr->left;
            // assign the parent of D as the parent of R
            arec.breakHere("Move the replacement node.");//!
            rNodePtr.parent = pNodePtr;//!            rNodePtr->parent = pNodePtr;
            // assign the left child of D to have parent R
            arec.breakHere("Move the replacement node.");//!
            dNodePtr.left.parent = rNodePtr;//!            dNodePtr->left->parent = rNodePtr;
        }
        else
        {
            // we moved at least one node down a left branch
            // of the right child of D. unlink R from tree by
            // assigning its right subtree as the left child of
            // the parent of R
            arec.breakHere("Move the replacement node.");//!
            pOfRNodePtr.left = rNodePtr.right;//!            pOfRNodePtr->left = rNodePtr->right;

            // the parent of the right child of R is the
            // parent of R
            arec.breakHere("Move the replacement node.");//!
            if (rNodePtr.right != NULL)//!            if (rNodePtr->right != NULL)
                rNodePtr.right.parent = pOfRNodePtr;//!                rNodePtr->right->parent = pOfRNodePtr;

            // put replacement node in place of dNodePtr
            // assign children of R to be those of D
            arec.breakHere("Move the replacement node.");//!
            rNodePtr.left = dNodePtr.left;//!            rNodePtr->left = dNodePtr->left;
            arec.breakHere("Move the replacement node.");//!
            rNodePtr.right = dNodePtr.right;//!            rNodePtr->right = dNodePtr->right;
            // assign the parent of R to be the parent of D
            arec.breakHere("Move the replacement node.");//!
            rNodePtr.parent = pNodePtr;//!            rNodePtr->parent = pNodePtr;
            // assign the parent pointer in the children
            // of R to point at R
            arec.breakHere("Move the replacement node.");//!
            rNodePtr.left.parent = rNodePtr;//!            rNodePtr->left->parent = rNodePtr;
            arec.breakHere("Move the replacement node.");//!
            rNodePtr.right.parent = rNodePtr;//!            rNodePtr->right->parent = rNodePtr;
        }
        arec.popScope();//!
    }

    // complete the link to the parent node.

    // deleting the root node. assign new root
    arec.refVar("dNodePtr",dNodePtr).refVar("pNodePtr", pNodePtr).refVar("rNodePtr", rNodePtr).breakHere("Adjust the parent.");//!
    if (pNodePtr == NULL)
    {arec.breakHere("We've removed the former root.");//!
        root = rNodePtr;
    }//!
    // attach R to the correct branch of P
    else if (dNodePtr.nodeValue.compareTo(pNodePtr.nodeValue) < 0)//!    else if (dNodePtr->nodeValue < pNodePtr->nodeValue)
    {arec.breakHere("Replacement goes on left of parent.");//!
        pNodePtr.left = rNodePtr;//!        pNodePtr->left = rNodePtr;
    }//!
    else
    {arec.breakHere("Replacement goes on right of parent.");//!
        pNodePtr.right = rNodePtr;//!        pNodePtr->right = rNodePtr;
    }//!
    
    arec.breakHere("Delete node and decrement size.");//!
    // delete the node from memory and decrement tree size
    dNodePtr = null;//!    delete dNodePtr;
    treeSize--;
    arec.refVar("dNodePtr",dNodePtr).breakHere("Done.");//!
}

//!template <typename T>
int erase(T item)//!int stree<T>::erase(const T& item)
{
	ActivationRecord arec = activate(getClass());//!
	arec.param("item", item).breakHere("entered erase()");//!
    int numberErased = 1;
    // search tree for item
	arec.var("numberErased", numberErased).breakHere("search for node to remove");//!
    stnode<T> p  = findNode(item);//!    stnode<T> *p  = findNode(item);

    // if item found, delete the node
    if (p != NULL)
    	{arec.refVar("p", p).breakHere("remove node p");//!
        erase(new iterator(p,this));//!        erase(iterator(p,this));
    	}//!
    else
	{arec.refVar("p", p).breakHere("Could not find it. Do nothing.");//!
        numberErased = 0;
	}//!
    arec.breakHere("Done");//!
    return numberErased;
}

//!template <typename T>
//!void stree<T>::erase(iterator first, iterator last)
//!{
//!    if (treeSize == 0)
//!         throw
//!            underflowError("stree erase(): tree is empty");
//!
//!    iterator p = first;
//!
//!    if (first == begin() && last == end())
//!    {
//!        // we are asked to erase the entire tree.
//!        // erase the tree nodes from memory
//!        deleteTree(root);
//!
//!        // tree is emtpy
//!        root = NULL;
//!        treeSize = 0;
//!    }
//!    else
//!        // erase each item in a subrange of the tree
//!        while (p != last)
//!            erase(p++);
//!}

//!template <typename T>
iterator begin()//!stree<T>::iterator stree<T>::begin()
{
	ActivationRecord arec = activate(getClass());//!
	arec.breakHere("start at the root");//!
    stnode<T> curr = root;//!    stnode<T> *curr = root;

    // if the tree is not empty, the first node
    // inorder is the farthest node left from root
	arec.refVar("curr",curr).breakHere("entered begin()");//!
    if (curr != NULL)
    {    	arec.breakHere("start running down to the left");//!
    	while (curr.left != NULL)//!        while (curr->left != NULL)
        {    	arec.breakHere("step down to the left");//!
            curr = curr.left;//!            curr = curr->left;
            arec.refVar("curr",curr);//!
        }//!
    }//!
    arec.breakHere("curr points to were we want");//!

    // build return value using private constructor
    return new iterator(curr, this);//!    return iterator(curr, this);
}

//!template <typename T>
//!stree<T>::const_iterator stree<T>::begin() const
//!{
//!    const stnode<T> *curr = root;
//!
//!    // if the tree is not empty, the first node
//!    // inorder is the farthest node left from root
//!    if (curr != NULL)
//!        while (curr->left != NULL)
//!            curr = curr->left;
//!
//!    // build return value using private constructor
//!    return const_iterator(curr, this);
//!}

//!template <typename T>
iterator end()//!stree<T>::iterator stree<T>::end()
{
    // end indicated by an iterator with NULL stnode pointer
	ActivationRecord arec = activate(getClass());//!
	arec.breakHere("entered end()");//!
    return new iterator(NULL, this);//!    return iterator(NULL, this);
}

//!template <typename T>
//!stree<T>::const_iterator stree<T>::end() const
//!{
//!    // end indicated by an iterator with NULL stnode pointer
//!    return const_iterator(NULL, this);
//!}


//!#endif  // BINARY_SEARCH_TREE_CLASS


iterator quickEnd()//!
{//!
    return new iterator(NULL, this);//!
}//!

}//!