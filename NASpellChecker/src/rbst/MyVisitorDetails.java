package rbst;

/**
 * My visitor class allows for traversal of a Red Black Tree
 * Used in RBTTester
 * @author Shayan Asgari
 *
 */
public class MyVisitorDetails<Key extends Comparable<Key>> implements Visitor<Key>
{

	private RedBlackTree<Key> rbt;
	private String result;
	
	public MyVisitorDetails(RedBlackTree<Key> rbt)
	{
		this.rbt = rbt;
		result = "";
	}
	
	/**
	 * Special visit method for the VisitorDetails method
	 */
	@Override
	public void visit(Node<Key> n) 
	{
		if(!(n.key).equals(""))
  		  result = result +"Color: "+n.color+", Key:"+n.key+" Parent: "+ (n.parent == null ? "" :n.parent.key)+"\n";
	}
	
	public String makeStringDetails()
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
