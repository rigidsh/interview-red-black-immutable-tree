package my.rigid.redblacktree;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Checker for red black binary tree.
 * @author Alexander Petrov
 */
public class RedBlackTreeChecker {
    
    /**
     * Check if tree valid red black binary tree and print it is invalid.
     * @param <E> type of container
     * @param root root of tree
     * @return true if tree is a valid red-black binary tree
     */
    public <E extends Comparable> boolean checkAndPrintRedBlackTree(TreeNode<E> root) {
        if(!checkRedBlackTree(root)) {
            TreeUtils.printTree(root, TreeUtils.deep(root));
            return false;
        }
        return true;
    }
    
    /**
     * Check if tree valid red black binary tree
     * @param <E> type of container
     * @param root root of tree
     * @return true if tree is a valid red-black binary tree
     */
    public <E extends Comparable> boolean checkRedBlackTree(TreeNode<E> root) {
        int blackNodeCount = -1;
        E lastValue = null;
        
        LinkedList<TreeNode<E>> stack = new LinkedList<>();
        
        TreeNode<E> node;
        
        if(root == null) {
            return true;
        }
        
        for(node = root; node.getLeft() != null; node = node.getLeft()) {
            stack.push(node);
        }
        
        while(node != null) {
            if(lastValue == null) lastValue = node.getValue();
            else if(lastValue.compareTo(node.getValue()) > 0) return false;
            
            if(node.getLeft() == null && node.getRight() == null) {
                int blackNodesInPath = calcBlackNodes(stack) + (node.getMetainfo() == NodeColor.BLACK ? 1 : 0);
                if(blackNodeCount == -1)  blackNodeCount = blackNodesInPath;
                else if(blackNodeCount != blackNodesInPath) return false;
            }
            
            if(node.getMetainfo() == NodeColor.RED) {
                if(node.getLeft() != null && node.getLeft().getMetainfo() != NodeColor.BLACK) return false;
                if(node.getRight() != null && node.getRight().getMetainfo() != NodeColor.BLACK) return false;
            }
            
            if (node.getRight() != null) {
                TreeNode<E> p = node.getRight();
                stack.push(node);
                while (p.getLeft() != null) {
                    stack.push(p);
                    p = p.getLeft();
                }
                node = p;
            } else {
                TreeNode<E> p = stack.isEmpty() ? null : stack.pop();
                TreeNode<E> ch = node;
                while (p != null && ch == p.getRight()) {
                    ch = p;
                    p = stack.isEmpty() ? null : stack.pop();
                }
                node = p;
            }
            
            
        }
        
        return true;
    }

    private <E extends Comparable> int calcBlackNodes(Collection<TreeNode<E>> nodeCollection) {
        int result = 0;
        for (TreeNode<E> node : nodeCollection) {
            if(node.getMetainfo() == NodeColor.BLACK) result ++;
        }
        
        return result;
    }
}
