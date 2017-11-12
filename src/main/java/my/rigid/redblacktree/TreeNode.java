package my.rigid.redblacktree;


/**
 * Node of immutable binary tree.
 * @author Aleksandr Petrov
 */
public interface TreeNode<E extends Comparable> {
    /**
     * Create new open node.
     * @param <E>
     * @param left
     * @param right
     * @param value
     * @return 
     */
    public static <E extends Comparable> TreeNode<E> create(TreeNode<E> left, TreeNode<E> right, E value) {
        return new DefaultTreeNode<>(left, right, value);
    }
    
    /**
     * Create new open node.
     * @param <E>
     * @param value
     * @return 
     */
    public static <E extends Comparable> TreeNode<E> create(E value) {
        return new DefaultTreeNode<>(value);
    }
    
    /**
     * Create new open node.
     * @param <E>
     * @param value
     * @param info
     * @return 
     */
    public static <E extends Comparable> TreeNode<E> create(E value, MetaInfo info) {
        return new DefaultTreeNode<>(null, null, value, info);
    }
    
    /**
     * Create new closed node.
     * @param <E>
     * @param left
     * @param right
     * @param value
     * @return 
     */
    public static <E extends Comparable> TreeNode<E> createClosed(TreeNode<E> left, TreeNode<E> right, E value) {
        return new ClosedTreeNode<>(left, right, value);
    }
    
    /**
     * Create new closed node.
     * @param <E>
     * @param value
     * @return 
     */
    public static <E extends Comparable> TreeNode<E> createClosed(E value) {
        return new ClosedTreeNode<>(value);
    }
    
    /**
     * Create open copy of closed node, or return same node
     * @param <E>
     * @param origin
     * @return 
     */
    public static <E extends Comparable> TreeNode<E> copy(TreeNode<E> origin) {
        if(origin == null) return null;
        if(!origin.isClosed()) return origin;
        return new CopyTreeNode<>(origin);
    }

    /**
     * Get value of node
     * @return 
     */
    E getValue();

    /**
     * Get left child
     * @return 
     */
    TreeNode<E> getLeft();

    /**
     * Get right child
     * @return 
     */
    TreeNode<E> getRight();
    
    /**
     * Get parent of node if it open.
     * @return 
     */
    TreeNode<E> getParent();
    
    /**
     * Get meta info of node
     * @return 
     */
    MetaInfo getMetainfo();
    
    /**
     * Change left child if node open
     * @param node 
     */
    void setLeft(TreeNode<E> node);
    
    /**
     * Change right child if node open 
     * @param node 
     */
    void setRight(TreeNode<E> node);
    
    /**
     * Set parent of node is it open
     * @param node 
     */
    void setParent(TreeNode<E> node);
    
    /**
     * Change value of node if it open
     * @param value 
     */
    void setValue(E value);
    
    /**
     * Change metainfo of node if it open
     * @param info 
     */
    void setMetainfo(MetaInfo info);
    
    /**
     * Check if node closed
     * @return 
     */
    boolean isClosed();
    
    /**
     * Close node
     * @return 
     */
    TreeNode<E> close();
}
