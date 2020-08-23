package rbst;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * Red Black Tree tester
 *
 * @author Shayan Asgari
 *
 */
public class RBTTester 
{
	@Test
    //Test the Red Black Tree
	public void test() 
	{
		RedBlackTree<String> rbt = new RedBlackTree<String>();
        rbt.insert("D");
        rbt.insert("B");
        rbt.insert("A");
        rbt.insert("C");
        rbt.insert("F");
        rbt.insert("E");
        rbt.insert("H");
        rbt.insert("G");
        rbt.insert("I");
        rbt.insert("J");
        MyVisitor<String> mv = new MyVisitor<String>(rbt);
        assertEquals("DBACFEHGIJ", mv.makeString());
        String str=     "Color: 1, Key:D Parent: \n"+
                        "Color: 1, Key:B Parent: D\n"+
                        "Color: 1, Key:A Parent: B\n"+
                        "Color: 1, Key:C Parent: B\n"+
                        "Color: 1, Key:F Parent: D\n"+
                        "Color: 1, Key:E Parent: F\n"+
                        "Color: 0, Key:H Parent: F\n"+
                        "Color: 1, Key:G Parent: H\n"+
                        "Color: 1, Key:I Parent: H\n"+
                        "Color: 0, Key:J Parent: I\n";
        MyVisitorDetails<String> mvd = new MyVisitorDetails<String>(rbt); 
        assertEquals(str, mvd.makeStringDetails());
    }  
	
	@Test
	public void myTest()
	{
		  /* Added test to test out the generic class. So I used Integer */
        RedBlackTree<Integer> rbt2 = new RedBlackTree<Integer>();
        rbt2.insert(1);
        rbt2.insert(2);
        rbt2.insert(3);
        rbt2.insert(6);
        rbt2.insert(4);
        rbt2.insert(0);
   
        MyVisitor<Integer> mv2 = new MyVisitor<Integer>(rbt2);
        assertEquals("210436", mv2.makeString());
        String str2=     "Color: 1, Key:2 Parent: \n"+
                        "Color: 1, Key:1 Parent: 2\n"+
                        "Color: 0, Key:0 Parent: 1\n"+
                        "Color: 1, Key:4 Parent: 2\n"+
                        "Color: 0, Key:3 Parent: 4\n"+
                        "Color: 0, Key:6 Parent: 4\n";
        MyVisitorDetails<Integer> mvd2 = new MyVisitorDetails<Integer>(rbt2); 
        assertEquals(str2, mvd2.makeStringDetails());
	}
}
    
  
