package rbst;

import java.util.HashSet;
import java.util.Set;

/**
 * My visitor class allows for traversal of a Red Black Tree
 * Used in RBTTester
 * @author Shayan Asgari
 *
 */
public class MyVisitor<Key extends Comparable<Key>> implements Visitor<Key>
{
	private RedBlackTree<Key> rbt;
	private String result;
	
	public MyVisitor(RedBlackTree<Key> rbt)
	{
		this.rbt = rbt;
		result = "";
	}
	@Override
	public void visit(Node<Key> n) 
	{
		result += n.key;
	}
	
	public String makeString()
	{
		this.preOrderVisit(rbt.getRoot());
		return result;
	}
	
	/**
	 * Simple Pre-Order traversal of red black tree
	 * @param node the node that is being traversed
	 */
	private void preOrderVisit(Node<Key> node) 
	{
		if(node == null)
			return;
		this.visit(node);
		this.preOrderVisit(node.leftChild);
		this.preOrderVisit(node.rightChild);
	}
}
