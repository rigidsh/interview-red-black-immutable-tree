package my.rigid.redblacktree;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Alexander Petrov
 */
public class RedBlackTreeRemoveBalaceTest {
    private static RedBlackTreeChecker checker = new RedBlackTreeChecker();
    
    @Test
    public void rootNode() {
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(42);
        
        tree = tree.remove(42);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.root));
    }
    
    @Test
    public void redLeftNode() {
        TreeNode<Integer> root = TreeNode.create(10, NodeColor.BLACK);
        root.setLeft(TreeNode.create(5, NodeColor.RED));
        
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 2);
        
        tree = tree.remove(5);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.root));
    }
    
    @Test
    public void redRightNode() {
        TreeNode<Integer> root = TreeNode.create(10, NodeColor.BLACK);
        root.setRight(TreeNode.create(15, NodeColor.RED));
        
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 2);
        
        tree = tree.remove(15);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.root));
    }
    
    @Test
    public void blackNodeWithRedChild() {
        TreeNode<Integer> root = TreeNode.create(10, NodeColor.BLACK);
        root.setRight(TreeNode.create(15, NodeColor.BLACK));
        
        TreeNode<Integer> removeNode = TreeNode.create(5, NodeColor.BLACK);
        root.setLeft(removeNode);
        removeNode.setLeft(TreeNode.create(1, NodeColor.RED));
        
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 4);
        
        tree = tree.remove(5);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.root));
    }
    
    @Test
    public void blackNodeInLeftWithBlackChildAndRedSibling() {
        TreeNode<Integer> root = TreeNode.create(10, NodeColor.BLACK);
        
        TreeNode<Integer> sibling = TreeNode.create(15, NodeColor.RED);
        sibling.setLeft(TreeNode.create(14, NodeColor.BLACK));
        sibling.setRight(TreeNode.create(16, NodeColor.BLACK));
        
        root.setRight(sibling);
        
        TreeNode<Integer> removeNode = TreeNode.create(5, NodeColor.BLACK);
        root.setLeft(removeNode);
        
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 5);
        
        tree = tree.remove(5);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.root));
    }
    
    @Test
    public void blackNodeInRightWithBlackChildAndRedSibling() {
        TreeNode<Integer> root = TreeNode.create(10, NodeColor.BLACK);
        
        TreeNode<Integer> sibling = TreeNode.create(5, NodeColor.RED);
        sibling.setLeft(TreeNode.create(4, NodeColor.BLACK));
        sibling.setRight(TreeNode.create(6, NodeColor.BLACK));
        
        root.setLeft(sibling);
        
        TreeNode<Integer> removeNode = TreeNode.create(15, NodeColor.BLACK);
        root.setRight(removeNode);
        
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 5);
        
        tree = tree.remove(15);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.root));
    }
    
    @Test
    public void blackNodeInLeftWithBlackChildAndBlackSibling() {
        TreeNode<Integer> root = TreeNode.create(10, NodeColor.BLACK);
        
        TreeNode<Integer> sibling = TreeNode.create(15, NodeColor.BLACK);
        
        root.setRight(sibling);
        
        TreeNode<Integer> removeNode = TreeNode.create(5, NodeColor.BLACK);
        root.setLeft(removeNode);
        
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 3);
        
        tree = tree.remove(5);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.root));
    }
    
    @Test
    public void blackNodeInLeftWithBlackChildAndBlackSiblingWithRedLeftNephewAndBlackRightNephew() {
        TreeNode<Integer> root = TreeNode.create(10, NodeColor.BLACK);
        
        TreeNode<Integer> sibling = TreeNode.create(15, NodeColor.BLACK);
        sibling.setLeft(TreeNode.create(14, NodeColor.RED));
        
        root.setRight(sibling);
        
        TreeNode<Integer> removeNode = TreeNode.create(5, NodeColor.BLACK);
        root.setLeft(removeNode);
        
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 4);
        
        tree = tree.remove(5);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.root));
    }
    
    @Test
    public void blackNodeInLeftWithBlackChildAndBlackSiblingWithBlackLeftNephewAndRedRightNephew() {
        TreeNode<Integer> root = TreeNode.create(10, NodeColor.BLACK);
        
        TreeNode<Integer> sibling = TreeNode.create(15, NodeColor.BLACK);
        sibling.setRight(TreeNode.create(16, NodeColor.RED));
        
        root.setRight(sibling);
        
        TreeNode<Integer> removeNode = TreeNode.create(5, NodeColor.BLACK);
        root.setLeft(removeNode);
        
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 4);
        
        tree = tree.remove(5);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.root));
    }
    
    @Test
    public void blackNodeInRightWithBlackChildAndBlackSiblingWithRedLeftNephewAndBlackRightNephew() {
        TreeNode<Integer> root = TreeNode.create(10, NodeColor.BLACK);
        
        TreeNode<Integer> sibling = TreeNode.create(5, NodeColor.BLACK);
        sibling.setLeft(TreeNode.create(4, NodeColor.RED));
        
        root.setLeft(sibling);
        
        TreeNode<Integer> removeNode = TreeNode.create(15, NodeColor.BLACK);
        root.setRight(removeNode);
        
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 4);
        
        tree = tree.remove(15);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.root));
    }
    
    @Test
    public void blackNodeInRightWithBlackChildAndBlackSiblingWithBlackLeftNephewAndRedRightNephew() {
        TreeNode<Integer> root = TreeNode.create(10, NodeColor.BLACK);
        
        TreeNode<Integer> sibling = TreeNode.create(5, NodeColor.BLACK);
        sibling.setRight(TreeNode.create(6, NodeColor.RED));
        
        root.setLeft(sibling);
        
        TreeNode<Integer> removeNode = TreeNode.create(15, NodeColor.BLACK);
        root.setRight(removeNode);
        
        RedBlackImmutableTree<Integer> tree = new RedBlackImmutableTree<>(root, 4);
        
        tree = tree.remove(15);
        
        assertTrue("Tree is not valid" , checker.checkAndPrintRedBlackTree(tree.root));
    }
    
  
    
    
}
