package my.rigid.redblacktree;

/**
 * Immutable tree node.
 * @author Alexander Petrov
 */
public class ClosedTreeNode <E extends Comparable> implements TreeNode<E> {
    private final TreeNode left;
    private final TreeNode right;
    private final MetaInfo info;
    private final E value;
    
    public ClosedTreeNode(TreeNode left, TreeNode right, E value, MetaInfo info) {
        this.left = left;
        this.right = right;
        this.value = value;
        this.info = info;
    }

    public ClosedTreeNode(TreeNode left, TreeNode right, E value ) {
        this(left, right, value, null);
    }
    
    public ClosedTreeNode(E value) {
        this(null, null, value, null);
    }

    @Override
    public E getValue() {
        return this.value;
    }

    @Override
    public TreeNode getLeft() {
        return this.left;
    }

    @Override
    public TreeNode getRight() {
        return this.right;
    }
    
    @Override
    public MetaInfo getMetainfo() {
        return this.info;
    }

    @Override
    public void setLeft(TreeNode node) {
        throw new RuntimeException("Tree node hass been closed");
    }

    @Override
    public void setRight(TreeNode node) {
        throw new RuntimeException("Tree node hass been closed");
    }
    
    @Override
    public void setValue(E value) {
        throw new RuntimeException("Tree node hass been closed");
    }
    
    @Override
    public void setMetainfo(MetaInfo info) {
        throw new RuntimeException("Tree node hass been closed");
    }

    @Override
    public boolean isClosed() {
        return true;
    }

    @Override
    public TreeNode close() {
        return this;
    }

    @Override
    public TreeNode<E> getParent() {
        throw new RuntimeException("Tree node hass been closed");
    }

    @Override
    public void setParent(TreeNode<E> node) {
        throw new RuntimeException("Tree node hass been closed");
    }
}
