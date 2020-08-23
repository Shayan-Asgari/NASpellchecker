package rbst;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

/**
  Red Black Tree implementation that ensures for O(lgn) operations
 */
public class RedBlackTree<Key extends Comparable<Key>> 
{	
		private Node<Key> root;
		
		public void printTree()
		{  //preorder: visit, go left, go right
			Node<Key> currentNode = root;	
			printTree(currentNode);
		}
		
		public void printTree(Node<Key> node)
		{
			if (node.isLeaf(root)){
				return;
			}
			System.out.println(node.key);
			printTree(node.leftChild);
			printTree(node.rightChild);
		}
		
		public void addNode(Key data)
		{  	
			Node<Key> newNode = new Node<Key>(data);
			if(root == null)
			{
				root = newNode;
			}
			else
			{
				newNode.color = Node.RED;
				newNode.isRed = true;
				Node<Key> temp = root;
			    
			    // Pointer y maintains the trailing  
			    // pointer of x  
			    Node<Key> y = null;  
			    
			    while (temp != null) {  
			        y = temp;  
			        if (isLeftChild(temp , newNode))  
			            temp = temp.leftChild;  
			        else
			            temp = temp.rightChild;  
			    }  
			    
			    // If the root is null
			    if (y == null)  
			        y = newNode;  
			    
			    // If the new key is less then the leaf node key  
			    // Assign the new node to be its left child  
			    else if (this.isLeftChild(y, newNode))  
			        y.leftChild = newNode;  
			    // else assign the new node its right child  
			    else
			        y.rightChild = newNode;  
			    newNode.parent = y;
				fixTree(newNode);
			}
		
			
		}	
		public Node<Key> getRoot()
		{
			return this.root;
		}
		
		public void insert(Key data)
		{
			addNode(data);	
		}
		
		public Node<Key> lookup(Key key)
		{
			Node<Key> newNode = new Node<Key>(key);
			Node<Key> temp = root;
			while(temp != null && temp.key.compareTo(key) !=0)
			{
				if(isLeftChild(temp, newNode))
				{
					temp = temp.leftChild;
				}
				else
					temp = temp.rightChild;
			}
			return temp;
		}
	 	
		
		public Node<Key> getSibling(Node<Key> n)
		{
			if(n.parent == n.parent.parent.leftChild)
			{
				return n.parent.rightChild;
			}
			else if(n.parent == n.parent.parent.rightChild)
				return n.parent.leftChild;
			return null;
		}
		
	
		public Node<Key> getAunt(Node<Key> n)
		{
			if(n.parent == n.parent.parent.leftChild)
			{
				return n.parent.parent.rightChild;
			}
			else if(n.parent == n.parent.parent.rightChild)
				return n.parent.parent.leftChild;
			return null;
		}
		
		public Node<Key> getGrandparent(Node<Key> n){
			if(n.parent.parent !=null )
				return n.parent.parent;
			return null;
		}
		
		
		public void rotateLeft(Node<Key> n)
		{
			if(n.parent != null) // N does not equal root
			{
				if(n.parent.leftChild == n)
				{
					n.parent.leftChild = n.rightChild;
				}
				else
				{
					n.parent.rightChild = n.rightChild;
				}
			
				n.rightChild.parent = n.parent;
				n.parent = n.rightChild;
				if(n.rightChild.leftChild != null)
					n.rightChild.leftChild.parent = n;
				n.rightChild = n.rightChild.leftChild;
				n.parent.leftChild = n;
			}
			else //n== root
			{
				Node<Key> tempRight = root.rightChild; //1
				if(tempRight.leftChild !=null)
					tempRight.leftChild.parent = root; //3
				root.rightChild = tempRight.leftChild; //2
				n.parent = tempRight; //4
				tempRight.leftChild = root; //5
				root = tempRight;
				root.parent = null;
			}
		}
		
		public void rotateRight(Node<Key> n)
		{
			if(n.parent != null) //N does not equal root
			{
				if(n == n.parent.leftChild)
				{
					n.parent.leftChild  = n.leftChild;
				}
				else
				{
					n.parent.rightChild = n.leftChild;
				}
				n.leftChild.parent = n.parent;
				n.parent = n.leftChild;
				if(n.leftChild.rightChild != null)
				{
					n.leftChild.rightChild.parent = n;
				}
				n.leftChild = n.leftChild.rightChild;
				n.parent.rightChild = n;
				
			}
			else if(n!=null) //N is the root
			{
				Node<Key> left = root.leftChild;
				root.leftChild = left.rightChild;
				if(left.rightChild!=null)
					left.rightChild.parent = root;
				root.parent = left;
				left.rightChild = root;
				root = left;
				root.parent = null;
			}
		}
		
		public void fixTree(Node<Key> current) 
		{
			while(current != null && current!= root  && current.parent != null && current.parent.color == Node.RED)
			{
				Node<Key> aunt = null;
				//left case
				if(current.parent.parent.leftChild != null && current.parent == current.parent.parent.leftChild)
				{
					aunt = this.getAunt(current);
					if(aunt!=null && aunt.color == Node.RED)
					{
						current.parent.color = Node.BLACK;
						aunt.color = Node.BLACK;
						this.getGrandparent(current).color = Node.RED;
						current = this.getGrandparent(current);
						continue;
					}
					if(current == current.parent.rightChild)
					{
						current = current.parent;
						rotateLeft(current);
					}
					swapColor(current.parent, this.getGrandparent(current));
					rotateRight(this.getGrandparent(current));
				}
				else if(current.parent.parent.rightChild != null)
				{
					aunt = this.getAunt(current);
					if(aunt!=null && aunt.color == Node.RED)
					{
						current.parent.color = Node.BLACK;
						aunt.color = Node.BLACK;
						this.getGrandparent(current).color = Node.RED;
						current = this.getGrandparent(current);
						continue;
					}
					//Right left case
					if(current == current.parent.leftChild)
					{
						current = current.parent;
						rotateRight(current);
					}
					swapColor(current.parent, this.getGrandparent(current));
					rotateLeft(this.getGrandparent(current));
				}
			}
			//If inserted node becomes root
			root.color = Node.BLACK;
		}
		
		public void swapColor(Node<Key> parent, Node<Key> grandparent)
		{
			int temp = parent.color;
			parent.color = grandparent.color;
			grandparent.color = temp;
		}
		public boolean isEmpty(Node<Key> n){
			if (n.key == null){
				return true;
			}
			return false;
		}
		 
		public boolean isLeftChild(Node<Key> parent, Node<Key> child)
		{
			if (child.compareTo(parent) < 0 ) {//child is less than parent
				return true;
			}
			return false;
		}

		public void preOrderVisit() {//Visitor<Key> v) {
		   	preOrderVisit(root); //v);
		}
		 
		//changed from static to void 
		private void preOrderVisit(Node<Key> n)//Visitor<Key> v)
		{
		  	if (n == null) {
		  		return;
		  	}
		  	visit(n);
		  	preOrderVisit(n.leftChild);
		  	preOrderVisit(n.rightChild); 
		}	
		public void inOrderVisit() 
		{
		   	inOrderVisit(root);
		}
		private void inOrderVisit(Node<Key> n)
		{
		  	if (n == null)
		  	{
		  		System.out.println("NULL");
		  		return;
		  	}
		  	
		  	inOrderVisit(n.leftChild); 
		  	visit(n);
		  	inOrderVisit(n.rightChild); 
		}
		public void visit(Node<Key> n)
		{
			System.out.println(n.key);
		}
}

