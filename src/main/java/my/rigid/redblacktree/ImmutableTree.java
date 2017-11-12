package my.rigid.redblacktree;

import java.util.Iterator;

/**
 * Immutable binary search tree.
 * @author Aleksandr Petrov
 * @param <E> Type of container
 */
public class ImmutableTree<E extends Comparable> implements Iterable<E> {
    
    /**
     * Root node of tree
     */
    protected final TreeNode root;
    /**
     * Number of elements in collection.
     */
    private final int size;
    
    public ImmutableTree() {
        root = null;
        size = 0;
    }

    public ImmutableTree(E value) {
        root = TreeNode.createClosed(value);
        size = 1;
    }

    protected ImmutableTree(TreeNode root, int size) {
        this.root = root;
        this.size = size;
    }

    /**
     * Get number of elements in collection.
     * @return 
     */
    public int size() {
        return size;
    }

    /**
     * Check if element contains in collection.
     * @param value
     * @return true if collection contains element value
     */
    public boolean contains(E value) {
        return TreeUtils.findNode(this.root, value) != null;
    }
    
    
    @Override
    public Iterator<E> iterator() {
        return new ImmutableTreeIterator(this.root);
    }

    /**
     * Create copy of collection and add new element
     * @param e new element value
     * @return new collection
     */
    public ImmutableTree<E> add(E e) {
        
        if(root == null) {
            return new ImmutableTree<>(e);
        }
        
        return new ImmutableTree<>(
                TreeUtils.closeNodes(TreeUtils.insertNode(this.root, TreeNode.create(e))),
                this.size + 1);
    }
    
    /**
     * Create copy of collection and add new elements
     * @param eArray new element values
     * @return  new collection
     */
    public ImmutableTree<E> addAll(E... eArray) {
        TreeNode<E> newRoot = this.root;
        
        for(E e : eArray) {
            if(newRoot == null) {
                newRoot = TreeNode.create(e);
                continue;
            }
            newRoot = TreeUtils.insertNode(newRoot, TreeNode.create(e));
        }
        
        return new ImmutableTree<>(TreeUtils.closeNodes(newRoot), this.size + eArray.length);
    }

    /**
     * Create copy of collection without element with value
     * @param value
     * @return new collection
     */
    public ImmutableTree<E> remove(E value) {
       
        TreeNode newRoot = TreeNode.copy(this.root);
        
        TreeNode nodeToRemove = TreeUtils.getNodeToRemove(newRoot, value);
        TreeNode<E> child = (nodeToRemove.getLeft() != null) ? 
                nodeToRemove.getLeft() : nodeToRemove.getRight();
        
        if(nodeToRemove == newRoot) {
            newRoot = child;
        } else {
            TreeNode<E> parent = nodeToRemove.getParent();
            
            if(parent.getLeft() == nodeToRemove) parent.setLeft(child);
            else parent.setRight(child);
        }

        return new ImmutableTree<>(TreeUtils.closeNodes(newRoot), this.size - 1);
       
    }
    
}
