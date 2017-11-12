package my.rigid.redblacktree;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 *
 * @author Alexander Petrov
 */
public class TreeUtils {
    /**
     * Print tree to std err.
     * @param <E>
     * @param root root of tree
     * @param treeDeep max deep of tree
     */
    public static <E extends Comparable> void printTree(TreeNode<E> root, int treeDeep) {
        Queue<TreeNode<E>> queue = new LinkedList<>();
        
        int nodeSize = 2;
        
        queue.add(root);
        
        for(int deep = 0; deep < treeDeep; deep++) {
            String lineColor;
            if(deep % 2  == 0) lineColor = "\u001B[46m";
            else lineColor = "\u001B[43m";
            
            for(int i = 1; i <= Math.pow(2, deep); i++) {
                TreeNode<E> currentNode = queue.poll();
                printNode(currentNode, (int)Math.pow(2, treeDeep - deep + 1) * nodeSize, lineColor);
                if(currentNode == null) {
                    queue.add(null);
                    queue.add(null);
                } else {
                    queue.add(currentNode.getLeft());
                    queue.add(currentNode.getRight());
                }
            }
            
            System.err.println();
        }
       
    }
    
    
    /**
     * Print node to std err
     * @param <E>
     * @param node node for printing
     * @param size max number of symbols for node
     * @param lineColor color of current line
     */
    private static <E extends Comparable> void printNode(TreeNode<E> node, int size, String lineColor) {
        String valueString = node != null ?  node.getValue().toString() : "";
        
        int spacesBefore = (size - valueString.length()) / 2;
        
        StringBuilder result = new StringBuilder();
        
        IntStream.range(0, spacesBefore).forEach(it -> result.append(" "));
        if(node != null) {
            if(node.getMetainfo() == NodeColor.RED) result.append("\u001B[41m");
            else if(node.getMetainfo() == NodeColor.BLACK) result.append("\u001B[40m");
        }
        result.append(valueString);
        result.append(lineColor);
        IntStream.range(spacesBefore +  valueString.length(), size).forEach(it -> result.append(" "));
        
        System.err.print(lineColor);
        System.err.print(result.toString());
    }
    
    /**
     * Get min deep of tree
     * @param <E>
     * @param root
     * @return 
     */
    public static <E extends Comparable> int minDeep(TreeNode<E> root) {
        Queue<TreeNode<E>> queue = new LinkedList<>();
        
        int minDeep = 1;
        
        queue.add(root);
        
        while(true){
            for(int deep = 0; deep < Math.pow(2, minDeep); deep++) {
                TreeNode<E> node = queue.poll();
                if(node.getLeft() == null || node.getRight() == null) return minDeep;
                queue.add(node.getLeft());
                queue.add(node.getRight());
            }
            
            minDeep++;
        }
    }
    
    /**
     * get max deep of tree
     * @param <E>
     * @param root
     * @return 
     */
    public static <E extends Comparable> int deep(TreeNode<E> root) {
        
        Queue<TreeNode<E>> queue = new LinkedList<>();
         
        int deep = 0;
        
        queue.add(root);
        TreeNode<E> startNewLine = root;
        
        while(!queue.isEmpty()) {
            TreeNode<E> currentNode = queue.poll();
            if(currentNode == startNewLine) {
                startNewLine = null;
                deep ++;
            }
            if(currentNode.getLeft() != null) queue.add(currentNode.getLeft());
            if(startNewLine == null) {
                startNewLine = currentNode.getLeft();
            }
            if(currentNode.getRight()!= null) queue.add(currentNode.getRight());
            if(startNewLine == null) {
                startNewLine = currentNode.getRight();
            }
        }
        
        
        return deep;
    }
    
    /**
     * Insert node to tree
     * @param <E>
     * @param root
     * @param node
     * @return new root
     */
    public static <E extends Comparable> TreeNode<E> insertNode(TreeNode<E> root, TreeNode<E> node) {
        TreeNode<E> newRoot = TreeNode.copy(root);
        
        TreeNode<E> parent = newRoot;
        
        while(true) {
            if(node.getValue().compareTo(parent.getValue()) < 0) {
                if(parent.getLeft() == null) {
                    parent.setLeft(node);
                    break;
                } else {
                    parent.setLeft(TreeNode.copy(parent.getLeft()));
                    parent = parent.getLeft();
                }
            } else {
                if(parent.getRight() == null) {
                    parent.setRight(node);
                    break;
                } else {
                    parent.setRight(TreeNode.copy(parent.getRight()));
                    parent = parent.getRight();
                }
            }
        }
        
       return newRoot;        
    }
    
    /**
     * remove node from tree
     * @param <E>
     * @param root
     * @param value
     * @return new root of tree
     */
    public static <E extends Comparable> TreeNode<E> removeNode(TreeNode<E> root, E value) {
        TreeNode<E>[] newRoot = new TreeNode[] {TreeNode.copy(root)};
        
        TreeNode<E> node = newRoot[0];
        Consumer<TreeNode<E>> changeParent = (TreeNode<E> it) -> newRoot[0] = it;
        
        while(node != null) {
            int compare = value.compareTo(node.getValue());
            
            if(compare == 0) {
                break;
            } else if(compare < 0) {
                changeParent = node::setLeft;
                node.setLeft(TreeNode.copy(node.getLeft()));
                
                node = node.getLeft();
            } else {
                changeParent = node::setRight;
                
                node.setRight(TreeNode.copy(node.getRight()));
                node = node.getRight();
            }
        }
        
        if(node == null) throw new NoSuchElementException();
        
        if(node.getLeft() == null || node.getRight() == null) {
            changeParent.accept(node.getLeft() != null ? node.getLeft() : node.getRight());
        } else {
            node.setLeft(TreeNode.copy(node.getLeft()));
            TreeNode<E> maxValueNode = node.getLeft();
            TreeNode<E> parent = null;
            
            while(maxValueNode.getRight() != null) {
                parent = maxValueNode;
                
                maxValueNode.setRight(TreeNode.copy(maxValueNode.getRight()));
                maxValueNode = maxValueNode.getRight();
            }
            
            node.setValue(maxValueNode.getValue());
            parent.setRight(null);
            
        }
        
        return newRoot[0]; 
    }
    

    public static <E extends Comparable> TreeNode<E> getNodeToRemove(TreeNode<E> root, E value) {
       
        if(root.isClosed()) throw new RuntimeException("Root node should be open");
        
        TreeNode<E> node = root;
        
        while(node != null) {
            int compare = value.compareTo(node.getValue());
            
            if(compare == 0) {
                break;
            } else if(compare < 0) {
                node.setLeft(TreeNode.copy(node.getLeft()));
                
                node = node.getLeft();
            } else {
                node.setRight(TreeNode.copy(node.getRight()));
                node = node.getRight();
            }
        }
        
        if(node == null) throw new NoSuchElementException();
        
        if(node.getLeft() == null || node.getRight() == null) {
            return node;
        } else {
            node.setLeft(TreeNode.copy(node.getLeft()));
            TreeNode<E> maxValueNode = node.getLeft();
            
            while(maxValueNode.getRight() != null) {
                
                maxValueNode.setRight(TreeNode.copy(maxValueNode.getRight()));
                maxValueNode = maxValueNode.getRight();
            }
            
            node.setValue(maxValueNode.getValue());
            
            return maxValueNode;
        }
        
    }
    
    /**
     * Find node with value
     * @param <E>
     * @param root
     * @param value
     * @return 
     */
    public static <E extends Comparable> TreeNode<E> findNode(TreeNode<E> root, E value) {
        TreeNode node = root;
        
        while(node != null) {
            int compare = value.compareTo(node.getValue());
            
            if(compare == 0) {
                return node;
            } else if(compare < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        
        return null;
    }
    
    /**
     * Close all nodes in tree.
     * @param <E>
     * @param root
     * @return 
     */
    public static <E extends Comparable> TreeNode<E> closeNodes(TreeNode<E> root) {
        if(root == null) return null;
        if(root.isClosed()) return root;
        TreeNode<E> node = root;
        
        while(true) {
            if(node.getLeft() != null && !node.getLeft().isClosed()) {
                node = node.getLeft();
            } else if(node.getRight() != null && !node.getRight().isClosed()) {
                node = node.getRight();
            } else {
                TreeNode<E> parent = node.getParent();
                
                if(parent == null) {
                    return node.close();
                }
                
                if(parent.getLeft() == node) {
                    parent.setLeft(node.close());
                } else {
                    parent.setRight(node.close());
                }
                
                node = parent;   
            }
        }
    }
    
    /**
     * Get sibling of node
     * @param <E>
     * @param parent parent of node
     * @param node
     * @return 
     */
    public static <E extends Comparable> TreeNode<E> getSibling(TreeNode<E> parent, TreeNode<E> node) {
        
        if(parent != null) {
            if(parent.getLeft() == node) return parent.getRight();
            else return parent.getLeft();
        }
        
         return null;
    }
    
    /**
     * Find sibling and make open copy of it. If sibling will be open just return it.
     * @param <E>
     * @param parent
     * @param node
     * @return 
     */
    public static <E extends Comparable> TreeNode<E> getCopySibling(TreeNode<E> parent, TreeNode<E> node) {
        
        if(parent != null) {
            if(parent.getLeft() == node) {
                if(!parent.getRight().isClosed()) return parent.getRight();
                parent.setRight(TreeNode.copy(parent.getRight()));
                return parent.getRight();
            } else {
                if(!parent.getLeft().isClosed()) return parent.getLeft();
                parent.setLeft(TreeNode.copy(parent.getLeft()));
                return parent.getLeft();
            }
        }
        
         return null;
    }
}
