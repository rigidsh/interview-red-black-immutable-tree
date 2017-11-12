package my.rigid.redblacktree;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alexander Petrov
 */
public class RedBlackImmutableTreeTest {
    
    private static RedBlackTreeChecker checker = new RedBlackTreeChecker();
    
    
    @Test
    public void addAll() {
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>();
        
        tree = tree.addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.root));
        
        int i = 0;
        for (Integer item : tree) {
            assertEquals("Tree not contains element " + i, i, item.intValue());
            i++;
        }
    }

}
