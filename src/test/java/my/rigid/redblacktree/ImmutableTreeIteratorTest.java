package my.rigid.redblacktree;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alexander Petrov
 */
public class ImmutableTreeIteratorTest {


    @Test
    public void testHasNextOnEmptyTree() {
        ImmutableTree<Integer> emptyTree = new ImmutableTree<>();
        
        Iterator<Integer> iterator = emptyTree.iterator();
        assertFalse("Empty iterator has elements", iterator.hasNext());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testNextOnEmptyTree() {
        ImmutableTree<Integer> emptyTree = new ImmutableTree<>();
        
        Iterator<Integer> iterator = emptyTree.iterator();
        iterator.next();
    }
    
    @Test
    public void testOneElementTree() {
        ImmutableTree<Integer> tree = new ImmutableTree<>(42);
        
        Iterator<Integer> iterator = tree.iterator();
        
        assertTrue("Check hasNext", iterator.hasNext());
        assertEquals("Check next value", 42, iterator.next().intValue());
        assertFalse("Check hasNext", iterator.hasNext());
    }

    @Test
    public void testIteratorOrder() {
        ImmutableTree<Integer> test = new ImmutableTree<>();
        test = test.addAll(3, 2, 1, 10, 9, 8, 4, 7, 5, 6);
        Iterator<Integer> iterator = test.iterator();
        
        for (int i = 1; i <= 10; i++) {
            assertEquals("Iterator have wrong order", i, iterator.next().intValue());
        }
        
        
    }
    
}
