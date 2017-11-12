package my.rigid.redblacktree;

import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alexander Petrov
 */
public class ImmutableTreeTest {

    
    @Test
    public void testAdd() {
        ImmutableTree<Integer> tree = new ImmutableTree<>(Integer.valueOf(10));
        
        ImmutableTree<Integer> newTree = tree.add(Integer.valueOf(5));
        
        assertEquals("Check old collectio size", 1, tree.size());
        assertEquals("Check new collectio size", 2, newTree.size());
        assertFalse("Check old collectio not contais added element", tree.contains(Integer.valueOf(5)));
        assertTrue("Check new collectio contais added element", newTree.contains(Integer.valueOf(5)));
    }
    
    @Test
    public void testDelete() {
        ImmutableTree<Integer> tree = new ImmutableTree<>(10);
        
        tree = tree.addAll(1, 5, 2, 4);
        
        assertTrue("Collection not contains test element", tree.contains(1));
        
        tree = tree.remove(2);
        assertFalse("Element not removed", tree.contains(2));
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testDeleteNotExistElement() {
        ImmutableTree<Integer> tree = new ImmutableTree<>(10);
        
        tree.remove(42);
    }
    
    @Test
    public void testDeleteLastElement() {
        ImmutableTree<Integer> tree = new ImmutableTree<>(10);
        
        
        tree = tree.remove(10);
        assertFalse("Element not removed", tree.contains(10));
        assertEquals("Tree has worng size", 0, tree.size());
    }
    
    @Test
    public void testDeleteAndAddLastElement() {
        ImmutableTree<Integer> tree = new ImmutableTree<>(10);
        
        
        tree = tree.remove(10);
        assertFalse("Element not removed", tree.contains(10));
        assertEquals("Tree has worng size", 0, tree.size());
        tree = tree.add(10);
        assertTrue("Element not added", tree.contains(10));
        assertEquals("Tree has worng size", 1, tree.size());
    }
    
    

}
