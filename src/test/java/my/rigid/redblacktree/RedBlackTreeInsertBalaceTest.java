package my.rigid.redblacktree;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Alexander Petrov
 */
public class RedBlackTreeInsertBalaceTest {
    private static RedBlackTreeChecker checker = new RedBlackTreeChecker();
    
    @Test
    public void toEmpty() {
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>();
        
        tree = tree.add(42);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.root));
    }
    
    @Test
    public void toBlackParent() {
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(42);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.add(1).root));
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.add(100).root));
    }
    
    @Test
    public void toRedParentAndRigthRedUncle() {
        TreeNode<Integer> root = TreeNode.create(10, NodeColor.BLACK);
        TreeNode<Integer> parent = TreeNode.create(5, NodeColor.RED);
        root.setLeft(parent);
        TreeNode<Integer> uncle = TreeNode.create(15, NodeColor.RED);
        root.setRight(uncle);
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 3);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.add(1).root));
    }
    
    @Test
    public void toRedParentAndLeftRedUncle() {
        TreeNode<Integer> root = TreeNode.create(10, NodeColor.BLACK);
        TreeNode<Integer> parent = TreeNode.create(15, NodeColor.RED);
        root.setRight(parent);
        TreeNode<Integer> uncle = TreeNode.create(5, NodeColor.RED);
        root.setLeft(uncle);
        
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 3);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.add(20).root));
    }
    
    @Test
    public void toRightToRedParentAndRightNullUncle() {
        TreeNode<Integer> root = TreeNode.create(10, NodeColor.BLACK);
        TreeNode<Integer> parent = TreeNode.create(5, NodeColor.RED);
        root.setLeft(parent);
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 2);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.add(6).root));
    }
    
    @Test
    public void toLeftToRedParentAndRightNullUncle() {
        TreeNode<Integer> root = TreeNode.create(10, NodeColor.BLACK);
        TreeNode<Integer> parent = TreeNode.create(5, NodeColor.RED);
        root.setLeft(parent);
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 2);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.add(1).root));
    }
    
    
    @Test
    public void toRightToRedParentAndLeftNullUncle() {
        TreeNode<Integer> root = TreeNode.create(10, NodeColor.BLACK);
        TreeNode<Integer> parent = TreeNode.create(15, NodeColor.RED);
        root.setRight(parent);
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 2);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.add(16).root));
    }
    
    
    @Test
    public void toLeftToRedParentAndLeftNullUncle() {
        TreeNode<Integer> root = TreeNode.create(10, NodeColor.BLACK);
        TreeNode<Integer> parent = TreeNode.create(15, NodeColor.RED);
        root.setRight(parent);
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 2);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.add(14).root));
    }
    
    @Test
    public void toLeftToRedParentAndRightNullUncleAndNotRootLeftGrandpa() {
        TreeNode<Integer> root = TreeNode.create(20, NodeColor.BLACK);
        root.setRight(TreeNode.create(25, NodeColor.BLACK));
        
        TreeNode<Integer> grandpa = TreeNode.create(10, NodeColor.BLACK);
        root.setLeft(grandpa);
        TreeNode<Integer> parent = TreeNode.create(5, NodeColor.RED);
        grandpa.setLeft(parent);
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 2);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.add(1).root));
    }
    
    @Test
    public void toLeftToRedParentAndRightNullUncleAndNotRootRightGrandpa() {
        TreeNode<Integer> root = TreeNode.create(20, NodeColor.BLACK);
        root.setLeft(TreeNode.create(10, NodeColor.BLACK));
        
        TreeNode<Integer> grandpa = TreeNode.create(30, NodeColor.BLACK);
        root.setRight(grandpa);
        TreeNode<Integer> parent = TreeNode.create(25, NodeColor.RED);
        grandpa.setLeft(parent);
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 2);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.add(22).root));
    }
    
    @Test
    public void toRightToRedParentAndLeftNullUncleAndNotRootLeftGrandpa() {
        TreeNode<Integer> root = TreeNode.create(20, NodeColor.BLACK);
        root.setRight(TreeNode.create(25, NodeColor.BLACK));
        
        TreeNode<Integer> grandpa = TreeNode.create(10, NodeColor.BLACK);
        root.setLeft(grandpa);
        TreeNode<Integer> parent = TreeNode.create(15, NodeColor.RED);
        grandpa.setRight(parent);
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 4);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.add(16).root));
    }
    
    @Test
    public void toRightToRedParentAndLeftNullUncleAndNotRootRightGrandpa() {
        TreeNode<Integer> root = TreeNode.create(20, NodeColor.BLACK);
        root.setLeft(TreeNode.create(10, NodeColor.BLACK));
        
        TreeNode<Integer> grandpa = TreeNode.create(20, NodeColor.BLACK);
        root.setRight(grandpa);
        TreeNode<Integer> parent = TreeNode.create(25, NodeColor.RED);
        grandpa.setRight(parent);
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 4);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.add(26).root));
    }
    
    
}
