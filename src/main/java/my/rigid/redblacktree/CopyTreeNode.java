package my.rigid.redblacktree;

/**
 * Copy of tree node. 
 * @author Alexander Petrov
 */
public class CopyTreeNode<E extends Comparable> extends DefaultTreeNode<E> {
    
    /** 
     * Origin of tree node.
     */
    private TreeNode origin;
    
    public CopyTreeNode(TreeNode<E> origin) {
        super(origin.getLeft(), origin.getRight(), origin.getValue(), origin.getMetainfo());
        this.origin = origin;
    }

    /**
     * Make node immutable. Origin and parent links will be null after this operation.
     * @return Immutable node. If current node will equivalent to origin - return origin.
     */
    @Override
    public TreeNode close() {
        if(this.isClosed()) return this;
        
        if(this.origin.isClosed() &&
                this.getValue() == origin.getValue() &&
                this.getLeft() == origin.getLeft() &&
                this.getRight() == origin.getRight() && 
                this.getMetainfo()== origin.getMetainfo()) {
            
            return origin;
        } 
        
        origin = null;
        super.close();
        
        return this;
    }
    
    
    
}
