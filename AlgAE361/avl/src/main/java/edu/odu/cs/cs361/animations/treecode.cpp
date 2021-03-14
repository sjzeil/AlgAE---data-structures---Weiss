using namespace std;


void bstDoFind(multiset<int>& set, multiset<int>::iterator& pos, int& value)
{
  pos = set.lower_bound (value);
}


void bstDoErase(multiset<int>& set, multiset<int>::iterator& pos)
{ 
  set.erase (pos);
  pos = set.begin();
}



void preOrder ( const avlNode<int> * T )
{
  if (T != 0)
    {
      cout << T->value << " " << flush;  
      preOrder(T->leftChild);          
      preOrder(T->rightChild);         
    }
}


void inOrder ( const avlNode<int> * T )
{
  if (T != 0)
    {                             
      inOrder(T->leftChild);           
      cout << T->value << " " << flush;  
      inOrder(T->rightChild);          
    }
}

void postOrder ( const avlNode<int> * T )
{
  if (T != 0)
    {                       
      postOrder(T->leftChild);   
      postOrder(T->rightChild);  
      cout << T->value << " " << flush; 
    }
}



void levelOrder (avlNode<int>* Root) 
{
  queue<avlNode<int>*> q;
  if (Root != 0)
    q.push(Root); 
  while (!q.empty())
    {
     const avlNode<int>* T = q.front();
     q.pop();
     cout << T->value << " " << flush; 
     if (T->leftChild != 0)
        q.push (T->leftChild);
     if (T->rightChild != 0)
        q.push (T->rightChild); 
    }                      
}


