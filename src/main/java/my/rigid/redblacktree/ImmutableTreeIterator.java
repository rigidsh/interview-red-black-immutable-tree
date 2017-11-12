package my.rigid.redblacktree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Iterator for binary tree.
 * @author Alexander Petrov
 * @param <E> Type of iterator
 */
public class ImmutableTreeIterator<E extends Comparable<E>>implements Iterator<E> {
    
    private TreeNode<E> nextElement;
    private final LinkedList<TreeNode<E>> stack = new LinkedList<>();

    public ImmutableTreeIterator(TreeNode<E> root) {
        TreeNode<E> node;
        
        if(root == null) {
            nextElement = null;
            return;
        }
        
        for(node = root; node.getLeft() != null; node = node.getLeft()) {
            stack.push(node);
        }
        
        nextElement = node;
    }

    @Override
    public boolean hasNext() {
        return this.nextElement != null;
    }

    @Override
    public E next() {
        if(nextElement == null) throw new NoSuchElementException();
        
        TreeNode<E> retVal = this.nextElement;
        
        this.nextElement = successor(this.nextElement);
        
        return retVal.getValue();
    }
    
    /**
     * Get next element of tree. Based on JDK realization
     * @param current  current node of iterator
     * @return next node of collection
     */
    private TreeNode<E> successor(TreeNode<E> current) {
        if (current.getRight() != null) {
            TreeNode<E> p = current.getRight();
            stack.push(current);
            while (p.getLeft() != null){
                stack.push(p);
                p = p.getLeft();
            }
            return p;
        } else {
            TreeNode<E> p = stack.isEmpty() ? null : stack.pop();
            TreeNode<E> ch = current;
            while (p != null && ch == p.getRight()) {
                ch = p;
                p = stack.isEmpty() ? null : stack.pop();
            }
            return p;
        }
    }
    
}
