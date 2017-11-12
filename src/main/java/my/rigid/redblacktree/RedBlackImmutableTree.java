package my.rigid.redblacktree;

/**
 * Immutable red-black binary search tree.
 * @author Aleksandr Petrov
 * @param <E> Type of container
 */
public class RedBlackImmutableTree<E extends Comparable> extends ImmutableTree<E> {
    
    public RedBlackImmutableTree() {
        super();
    }

    public RedBlackImmutableTree(E value) {
        super(TreeNode.create(value, NodeColor.BLACK), 1);
    }

    protected RedBlackImmutableTree(TreeNode root, int size) {
        super(root, size);
    }

    @Override
    public RedBlackImmutableTree<E> add(E e) {
        
        if(root == null) {
            return new RedBlackImmutableTree<>(e);
        }
        
        TreeNode<E> newNode = TreeNode.create(e, NodeColor.RED);
        TreeNode<E> newRoot = TreeUtils.insertNode(this.root, newNode);
        
        newRoot = RedBlackTreeUtils.balanceInsert(newRoot, newNode);
        
        return new RedBlackImmutableTree<>(
                TreeUtils.closeNodes(newRoot),
                this.size() + 1);
    }
    
    @Override
    public RedBlackImmutableTree<E> addAll(E... eArray) {
        TreeNode<E> newRoot = root;
        for (E e : eArray) {
            if(newRoot == null) {
                newRoot = TreeNode.create(e, NodeColor.BLACK);
            } else {
                TreeNode<E> newNode = TreeNode.create(e, NodeColor.RED);
                newRoot = TreeUtils.insertNode(newRoot, newNode);
                
                newRoot = RedBlackTreeUtils.balanceInsert(newRoot, newNode);
            }
        }
        
        return new RedBlackImmutableTree<>(
                TreeUtils.closeNodes(newRoot),
                this.size() + eArray.length);
    }

    @Override
    public RedBlackImmutableTree<E> remove(E value) {
       
        TreeNode<E> newRoot = TreeNode.copy(this.root);
        
        TreeNode<E> nodeToRemove = TreeUtils.getNodeToRemove(newRoot, value);
        
        newRoot = RedBlackTreeUtils.balanceRemove(newRoot, nodeToRemove);

        return new RedBlackImmutableTree<>(TreeUtils.closeNodes(newRoot), this.size() - 1);
       
    }
    
}
