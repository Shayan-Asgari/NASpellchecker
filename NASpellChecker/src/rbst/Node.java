package rbst;

/**
 * Generic Node class
 * @author Shayan Asgari
 *
 * @param <Key>
 */
public class Node<Key extends Comparable<Key>>
{ 
	public static  final int RED = 0;
    public static final int BLACK = 1;
    Key key;  		  
    Node<Key> parent;//Changed from Keyto key
    Node<Key> leftChild;
    Node<Key> rightChild;
    boolean isRed;
    int color;
	  
    public Node(Key data)
    {
    	this.key = data;
    	this.parent = null;
    	leftChild = null;
    	rightChild = null;
    	color = BLACK;
    	isRed = false;
    }		
	  
    public int compareTo(Node<Key> n)
    { 
    	return key.compareTo(n.key);  	
    }
	  
    public boolean isLeaf(Node<Key> root)
    {
    	if (this.equals(root) && this.leftChild == null && this.rightChild == null) return true;
    	if (this.equals(root)) return false;
    	if (this.leftChild == null && this.rightChild == null)
    	{
    		return true;
    	}
    	return false;
    }
	  
    public String toString()
    {
    	System.out.println(leftChild==null);
    	return "Data: " + key.toString() + "\n" + 
    	"Left Child : " + (leftChild == null ? "" : leftChild.key) + 
    	" " + "Right Child : " + (rightChild == null ? "" : rightChild.key);
    }	
}