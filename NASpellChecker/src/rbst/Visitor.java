package rbst;

/**
 * Generic visitor interface which has one method visit
 * @author Shayan Asgari
 */
public interface Visitor<Key extends Comparable<Key>>
{
	/**
	This method is called at each node.
	@param n the visited node
	*/
	void visit(Node<Key> n); 
}