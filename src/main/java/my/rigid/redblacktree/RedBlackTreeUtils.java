package my.rigid.redblacktree;

/**
 * Utils for red black tree.
 * @author Alexander Petrov
 */
public class RedBlackTreeUtils {
    
    /**
     * Balance tree after insertion
     * @param <E> Type of container
     * @param root root of tree. Should be open node.
     * @param insertedNode inserted node. Should be open node.
     * @return new root
     */
    public static <E extends Comparable> TreeNode<E> balanceInsert(TreeNode<E> root, TreeNode<E> insertedNode) {
        TreeNode<E> parent = insertedNode.getParent();
        TreeNode<E> grandpa = (parent != null) ? parent.getParent() : null;
        TreeNode<E> uncle = (grandpa != null)
                ? (grandpa.getLeft() == parent) ? grandpa.getRight() : grandpa.getLeft()
                : null;

        if (parent == null) {
            insertedNode.setMetainfo(NodeColor.BLACK);
        } else if (parent.getMetainfo() == NodeColor.BLACK) {

        } else if (nullSafeGetNodeColor(uncle) == NodeColor.RED) {
            parent.setMetainfo(NodeColor.BLACK);
            if (grandpa.getLeft() == uncle) {
                uncle = TreeNode.copy(uncle);
                grandpa.setLeft(uncle);
            } else {
                uncle = TreeNode.copy(uncle);
                grandpa.setRight(uncle);
            }
            uncle.setMetainfo(NodeColor.BLACK);
            grandpa.setMetainfo(NodeColor.RED);
            return balanceInsert(root, grandpa);

        } else {
            if (parent.getRight() == insertedNode && grandpa.getLeft() == parent) {

                grandpa.setLeft(rotateLeft(parent));
                insertedNode = insertedNode.getLeft();
                parent = insertedNode.getParent();
                grandpa = (parent != null) ? parent.getParent() : null;
            } else if (parent.getLeft() == insertedNode && grandpa.getRight() == parent) {
                grandpa.setRight(rotateRight(parent));

                insertedNode = insertedNode.getRight();
                parent = insertedNode.getParent();
                grandpa = (parent != null) ? parent.getParent() : null;
            }

            parent.setMetainfo(NodeColor.BLACK);
            grandpa.setMetainfo(NodeColor.RED);

            if ((insertedNode == parent.getLeft()) && (parent == grandpa.getLeft())) {
                TreeNode<E> grandgrandpa = grandpa.getParent();
                TreeNode<E> newGrandpa = rotateRight(grandpa);
                if (grandgrandpa == null) {
                    root = newGrandpa;
                    newGrandpa.setParent(null);
                } else {
                    if (grandgrandpa.getLeft() == grandpa) {
                        grandgrandpa.setLeft(newGrandpa);
                    } else {
                        grandgrandpa.setRight(newGrandpa);
                    }
                }
            } else {
                TreeNode<E> grandgrandpa = grandpa.getParent();
                TreeNode<E> newGrandpa = rotateLeft(grandpa);
                if (grandgrandpa == null) {
                    root = newGrandpa;
                    newGrandpa.setParent(null);
                } else {
                    if (grandgrandpa.getLeft() == grandpa) {
                        grandgrandpa.setLeft(newGrandpa);
                    } else {
                        grandgrandpa.setRight(newGrandpa);
                    }
                }
            }
        }

        return root;
    }

    /**
     * Remove and balance node from tree
     * @param <E> Type of container
     * @param root root of tree
     * @param removeNode node for remove
     * @return new root
     */
    public static <E extends Comparable> TreeNode<E> balanceRemove(TreeNode<E> root, TreeNode<E> removeNode) {
        if (removeNode.isClosed()) {
            throw new RuntimeException("Node is closed");
        }

        TreeNode<E> child = TreeNode.copy(getSingleChild(removeNode));
        
        if (removeNode == root) {
            root = child;
            if(child != null) child.setParent(null);
        } else {
            if (removeNode.getParent().getLeft() == removeNode) {
                removeNode.getParent().setLeft(child);
            } else {
                removeNode.getParent().setRight(child);
            }
        }

        if (nullSafeGetNodeColor(removeNode) == NodeColor.BLACK) {
		if (nullSafeGetNodeColor(child) == NodeColor.RED) {
                    child.setMetainfo(NodeColor.BLACK);
                } else {
                    root = deleteCase1(root, removeNode.getParent(), child);
                }
	}
        
        return root;
    }

    /**
     * Process case 1 for balance. https://en.wikipedia.org/wiki/Red%E2%80%93black_tree#Removal
     * @param <E> type of container
     * @param root root of tree
     * @param parent parent of processing node
     * @param node processing node
     * @return  new root
     */
    private static <E extends Comparable> TreeNode<E> deleteCase1(TreeNode<E> root, TreeNode<E> parent, TreeNode<E> node) {
        if (parent != null) {
            return deleteCase2(root, parent, node);
        }

        return root;
    }

    /**
     * Process case 2 for balance. https://en.wikipedia.org/wiki/Red%E2%80%93black_tree#Removal
     * @param <E> type of container
     * @param root root of tree
     * @param parent parent of processing node
     * @param node processing node
     * @return  new root
     */
    private static <E extends Comparable> TreeNode<E> deleteCase2(TreeNode<E> root, TreeNode<E> parent, TreeNode<E> node) {
        TreeNode<E> sibling = TreeUtils.getCopySibling(parent, node);
        TreeNode<E> grandpa = parent.getParent();

        if (nullSafeGetNodeColor(sibling) == NodeColor.RED) {
            parent.setMetainfo(NodeColor.RED);
            sibling.setMetainfo(NodeColor.BLACK);
            TreeNode<E> newParent;
            if (parent.getLeft() == node) {
                newParent = rotateLeft(parent);
            } else {
                newParent = rotateRight(parent);
            }
            if (grandpa == null) {
                root = newParent;
                newParent.setParent(null);
            } else if (grandpa.getLeft() == parent) {
                grandpa.setLeft(newParent);
            } else {
                grandpa.setRight(newParent);
            }
        }

        return deleteCase3(root, parent, node);

    }

    /**
     * Process case 3 for balance. https://en.wikipedia.org/wiki/Red%E2%80%93black_tree#Removal
     * @param <E> type of container
     * @param root root of tree
     * @param parent parent of processing node
     * @param node processing node
     * @return  new root
     */
    private static <E extends Comparable> TreeNode<E> deleteCase3(TreeNode<E> root, TreeNode<E> parent, TreeNode<E> node) {
        TreeNode<E> sibling = TreeUtils.getCopySibling(parent, node);

        if ((parent.getMetainfo() == NodeColor.BLACK)
                    && (sibling.getMetainfo() == NodeColor.BLACK)
                && (nullSafeGetNodeColor(sibling.getLeft()) == NodeColor.BLACK)
                && (nullSafeGetNodeColor(sibling.getRight()) == NodeColor.BLACK)) {
            sibling.setMetainfo(NodeColor.RED);

            return deleteCase1(root, parent.getParent(), parent);
        } else {
            return deleteCase4(root, parent, node);
        }

    }

    /**
     * Process case 4 for balance. https://en.wikipedia.org/wiki/Red%E2%80%93black_tree#Removal
     * @param <E> type of container
     * @param root root of tree
     * @param parent parent of processing node
     * @param node processing node
     * @return  new root
     */
    private static <E extends Comparable> TreeNode<E> deleteCase4(TreeNode<E> root, TreeNode<E> parent, TreeNode<E> node) {
        TreeNode<E> sibling = TreeUtils.getCopySibling(parent, node);

        if ((nullSafeGetNodeColor(parent) == NodeColor.RED)
                && (sibling.getMetainfo() == NodeColor.BLACK)
                && (nullSafeGetNodeColor(sibling.getLeft()) == NodeColor.BLACK)
                && (nullSafeGetNodeColor(sibling.getRight()) == NodeColor.BLACK)) {
            sibling.setMetainfo(NodeColor.RED);
            parent.setMetainfo(NodeColor.BLACK);
            
            return root;
        } else {
            return deleteCase5(root, parent, node);
        }

    }

    /**
     * Process case 5 for balance. https://en.wikipedia.org/wiki/Red%E2%80%93black_tree#Removal
     * @param <E> type of container
     * @param root root of tree
     * @param parent parent of processing node
     * @param node processing node
     * @return  new root
     */
    private static <E extends Comparable> TreeNode<E> deleteCase5(TreeNode<E> root, TreeNode<E> parent, TreeNode<E> node) {
        TreeNode<E> sibling = TreeUtils.getCopySibling(parent, node);
        
        if  (sibling.getMetainfo() == NodeColor.BLACK) { 
		if ((parent.getLeft() == node) &&
		    (nullSafeGetNodeColor(sibling.getRight()) == NodeColor.BLACK) &&
		    (nullSafeGetNodeColor(sibling.getLeft()) == NodeColor.RED)) { 
			sibling.setMetainfo(NodeColor.RED);
                        sibling.getLeft().setMetainfo(NodeColor.BLACK);
			parent.setRight(rotateRight(sibling));
		} else if ((parent.getRight() == node) &&
		           (nullSafeGetNodeColor(sibling.getRight()) == NodeColor.RED) &&
		    (nullSafeGetNodeColor(sibling.getLeft()) == NodeColor.BLACK)) { 
			sibling.setMetainfo(NodeColor.RED);
			sibling.getRight().setMetainfo(NodeColor.BLACK);
                        parent.setLeft(rotateLeft(sibling));
		}
	}
	return deleteCase6(root, parent, node);
    }

    /**
     * Process case 6 for balance. https://en.wikipedia.org/wiki/Red%E2%80%93black_tree#Removal
     * @param <E> type of container
     * @param root root of tree
     * @param parent parent of processing node
     * @param node processing node
     * @return  new root
     */
    private static <E extends Comparable> TreeNode<E> deleteCase6(TreeNode<E> root, TreeNode<E> parent, TreeNode<E> node) {
        TreeNode<E> sibling = TreeUtils.getCopySibling(parent, node);
        TreeNode<E> grandpa = (parent != null) ? parent.getParent() : null;
        
        sibling.setMetainfo(parent.getMetainfo());
        parent.setMetainfo(NodeColor.BLACK);
        
        TreeNode<E> newParent;
        if (parent.getLeft() == node) {
            sibling.setRight(TreeNode.copy(sibling.getRight()));
            if(sibling.getRight() != null) sibling.getRight().setMetainfo(NodeColor.BLACK);
            newParent = rotateLeft(parent); 
	} else {
            sibling.setLeft(TreeNode.copy(sibling.getLeft()));
            if(sibling.getLeft() != null) sibling.getLeft().setMetainfo(NodeColor.BLACK);
            newParent = rotateRight(parent); 
	}
        
        
        if (grandpa == null) {
            root = newParent;
            newParent.setParent(null);
        } else if (grandpa.getLeft() == parent) {
            grandpa.setLeft(newParent);
        } else {
            grandpa.setRight(newParent);
        }
        
        return root;
    }
    
    /**
     * Get child of node if node have only one child.
     * @param <E> Type of container
     * @param parent parent
     * @return child
     */
    private static <E extends Comparable> TreeNode<E> getSingleChild(TreeNode<E> parent) {
        if (parent.getLeft() != null && parent.getRight() != null) {
            throw new RuntimeException("Node have 2 child");
        }
        if(parent.getLeft() != null) return parent.getLeft();
        else return parent.getRight();
    }

    /**
     * Rotate left around node.
     * @param <E> type of container
     * @param q node
     * @return new parent
     */
    private static <E extends Comparable> TreeNode<E> rotateLeft(TreeNode<E> q) {
        TreeNode<E> p = q.getRight();
        q.setRight(p.getLeft());
        p.setLeft(q);
        return p;
    }

    /**
     * Rotate right around node.
     * @param <E> type of container
     * @param q node
     * @return new parent
     */
    private static <E extends Comparable> TreeNode<E> rotateRight(TreeNode<E> q) {
        TreeNode<E> p = q.getLeft();
        q.setLeft(p.getRight());
        p.setRight(q);
        return p;
    }

    /**
     * Get color of node or BLACK if node null
     * @param <E> type of container
     * @param node node
     * @return color of node
     */
    private static <E extends Comparable> NodeColor nullSafeGetNodeColor(TreeNode<E> node) {
        return (node == null) ? NodeColor.BLACK : (NodeColor) node.getMetainfo();
    }
}
