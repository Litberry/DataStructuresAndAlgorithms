import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Implementation of a BST.
 *
 * @author Andrew Eng
 * @version 1.0
 * @userid aeng48
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot create BST from null list");
        }
        for (T item : data) {
            add(item);
        }
    }

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to BST");
        }
        root = addHelper(data, root);
    }

    private BSTNode<T> addHelper(T data, BSTNode<T> node) {
        if (node == null) {
            node = new BSTNode<T>(data);
            size++;
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(addHelper(data, node.getLeft()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(addHelper(data, node.getRight()));
        }
        return node;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data. You MUST use recursion to find and remove the
     * predecessor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data");
        }
        BSTNode<T> removed = new BSTNode<T>(null);
        root = removeHelper(root, data, removed);
        if (removed.getData() == null) {
            throw new NoSuchElementException("Unable to find data to remove");
        }
        size--;
        return removed.getData();
    }

    private BSTNode<T> removeHelper(BSTNode<T> node, T data, BSTNode<T> removed) {
        if (node == null) {
            return null; 
        } 
        if (node.getData().compareTo(data) < 0) {
            node.setRight(removeHelper(node.getRight(), data, removed));
        } else if (node.getData().compareTo(data) > 0) {
            node.setLeft(removeHelper(node.getLeft(), data, removed));
        } else {

            removed.setData(node.getData());
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                BSTNode<T> removed2 = new BSTNode<T>(null);                
                node.setLeft(removePredecessor(node.getLeft(), removed2));
                node.setData(removed2.getData());
            }
        }
        return node;
    }

    private BSTNode<T> removePredecessor(BSTNode<T> node, BSTNode<T> removed) {
        if (node.getRight() == null) {
            removed.setData(node.getData());
            return node.getLeft();
        } else {
            node.setRight(removePredecessor(node.getRight(), removed));
            return node;
        }
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot retrieve null data");
        }
        T retrieved = getHelper(data, root);
        if (retrieved == null) {
            throw new NoSuchElementException("Cannot find data in tree");
        }
        return retrieved;
    }

    private T getHelper(T data, BSTNode<T> node) {
        if (node == null) {
            return null;
        }
        if (node.getData().equals(data)) {
            return node.getData();
        } else if (data.compareTo(node.getData()) < 0) {
            return getHelper(data, node.getLeft());
        } else {
            return getHelper(data, node.getRight());
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot check if BST contains null data");
        }
        return containsHelper(root, data);
    }

    private boolean containsHelper(BSTNode<T> root, T data) {
        if (root == null) {
            return false;
        }
        if (data == root.getData()) {
            return true;
        } else if (data.compareTo(root.getData()) < 0) {
            return containsHelper(root.getLeft(), data);
        } else {
            return containsHelper(root.getRight(), data);
        }
    }
    /**
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        ArrayList<T> list = new ArrayList<T>();
        BSTNode<T> node = root;
        preorderHelper(node, list);
        return list;
    }

    private void preorderHelper(BSTNode<T> node, ArrayList<T> list) {
        if (node != null) {
            list.add(node.getData());
            preorderHelper(node.getLeft(), list);
            preorderHelper(node.getRight(), list);
        }
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        ArrayList<T> list = new ArrayList<T>();
        BSTNode<T> node = root;
        inorderHelper(node, list);
        return list;
    }

    private void inorderHelper(BSTNode<T> node, ArrayList<T> list) {
        if (node != null) {
            inorderHelper(node.getLeft(), list);
            list.add(node.getData());
            inorderHelper(node.getRight(), list);
        }
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        ArrayList<T> list = new ArrayList<T>();
        BSTNode<T> node = root;
        postorderHelper(node, list);
        return list;
    }

    private void postorderHelper(BSTNode<T> node, ArrayList<T> list) {
        if (node != null) {
            postorderHelper(node.getLeft(), list);
            postorderHelper(node.getRight(), list);
            list.add(node.getData());
        }
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        Queue<BSTNode<T>> queue = new LinkedList<>();
        ArrayList<T> list = new ArrayList<>();
        if (size == 0) {
            return list;
        }
        queue.add(root);
        levelorderHelper(queue, list);
        return list;
    }

    private void levelorderHelper(Queue<BSTNode<T>> queue, ArrayList<T> list) {
        if (!queue.isEmpty()) {
            BSTNode<T> popped = queue.remove();
            list.add(popped.getData());
            if (popped.getLeft() != null) {
                queue.add(popped.getLeft());
            }
            if (popped.getRight() != null) {
                queue.add(popped.getRight());
            }
            levelorderHelper(queue, list);
        }
    }

    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return heightHelper(root);
    }

    private int heightHelper(BSTNode<T> node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = heightHelper(node.getLeft());
        int rightHeight = heightHelper(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Generates a list of the max data per level from the top to the bottom
     * of the tree. (Another way to think about this is to get the right most
     * data per level from top to bottom.)
     * 
     * This must be done recursively.
     *
     * This list should contain the last node of each level.
     *
     * If the tree is empty, an empty list should be returned.
     *
     * Ex:
     * Given the following BST composed of Integers
     *      2
     *    /   \
     *   1     4
     *  /     / \
     * 0     3   5
     * getMaxDataPerLevel() should return the list [2, 4, 5] - 2 is the max
     * data of level 0, 4 is the max data of level 1, and 5 is the max data of
     * level 2
     *
     * Ex:
     * Given the following BST composed of Integers
     *               50
     *           /        \
     *         25         75
     *       /    \
     *      12    37
     *     /  \    \
     *   11   15   40
     *  /
     * 10
     * getMaxDataPerLevel() should return the list [50, 75, 37, 40, 10] - 50 is
     * the max data of level 0, 75 is the max data of level 1, 37 is the
     * max data of level 2, etc.
     *
     * Must be O(n).
     *
     * @return the list containing the max data of each level
     */
    public List<T> getMaxDataPerLevel() {
        ArrayList<T> maxDataPerLevel = new ArrayList<>();
        getMaxDataPerLevelHelper(root, 0, maxDataPerLevel);
        return maxDataPerLevel;
    }

    private void getMaxDataPerLevelHelper(BSTNode<T> node, int level, ArrayList<T> maxDataPerLevel) {
        if (node == null) {
            return;
        }
        if (level == maxDataPerLevel.size()) {
            maxDataPerLevel.add(node.getData());
        } else {
            maxDataPerLevel.set(level, maximumData(maxDataPerLevel.get(level), node.getData()));
        }
        getMaxDataPerLevelHelper(node.getLeft(), level + 1, maxDataPerLevel);
        getMaxDataPerLevelHelper(node.getRight(), level + 1, maxDataPerLevel);
    }

    private T maximumData(T one, T two) {
        if (one.compareTo(two) > 0) {
            return one;
        }
        return two;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
