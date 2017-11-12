package my.rigid.redblacktree;

/**
 * Default implementation of TreeNode interface.
 * @author Alexander Petrov
 */
public class DefaultTreeNode<E extends Comparable> implements TreeNode<E> {
    
    private TreeNode<E> left;
    private TreeNode<E> right;
    private TreeNode<E> parent;
    private E value;
    private boolean closed = false;
    private MetaInfo metaInfo;

    public DefaultTreeNode(TreeNode<E> left, TreeNode<E> right, E value, MetaInfo info) {
        this.left = left;
        this.right = right;
        this.value = value;
        this.metaInfo = info;
    }
    
    public DefaultTreeNode(TreeNode<E> left, TreeNode<E> right, E value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }
    

    public DefaultTreeNode(E value) {
        this.value = value;
    } 

    @Override
    public E getValue() {
        return this.value;
    }

    @Override
    public TreeNode<E> getLeft() {
        return this.left;
    }

    @Override
    public TreeNode<E> getRight() {
        return this.right;
    }
    
    @Override
    public MetaInfo getMetainfo() {
        return this.metaInfo;
    }

    @Override
    public void setLeft(TreeNode<E> node) {
        checkIsCosed();
        if(node != null && !node.isClosed()) {
            node.setParent(this);
        }
        this.left = node;
    }
    
    @Override
    public void setRight(TreeNode<E> node) {
        checkIsCosed();
        if(node != null && !node.isClosed()) {
            node.setParent(this);
        }
        
        this.right = node;
    }
    
    @Override
    public void setValue(E value) {
        checkIsCosed();
        this.value = value;
    }
    
    @Override
    public void setMetainfo(MetaInfo info) {
        checkIsCosed();
        this.metaInfo = info;
    }

    @Override
    public boolean isClosed() {
        return this.closed;
    }

    @Override
    public TreeNode close() {
        this.closed = true;
        this.parent = null;
        return this;
    }
    
    
    private void checkIsCosed() throws RuntimeException {
        if(closed) {
            throw new RuntimeException("Tree node hass been closed");
        }
    }

    @Override
    public TreeNode<E> getParent() {
        checkIsCosed();
        return this.parent;
    }

    @Override
    public void setParent(TreeNode<E> node) {
        checkIsCosed();
        this.parent = node;
    }
}
