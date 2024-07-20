import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.NoSuchElementException;

/**
 * Implementation of an AVL.
 *
 * @author Andrew Eng
 * @version 1.0
 * @userid aeng48
 */
public class AVL<T extends Comparable<? super T>> {

    // Do not add new instance variables or modify existing ones.
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot create AVL from null list");
        }
        for (T item : data) {
            add(item);
        }
    }

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to AVL");
        }
        root = addHelper(data, root);
    }

    /**
     * Returns the new AVL tree after node has been added.
     *
     * @param data the data to search for in the tree
     * @param node the node currently being examined
     * @return node that is balanced
     */
    private AVLNode<T> addHelper(T data, AVLNode<T> node) {
        if (node == null) {
            size++;
            return new AVLNode<>(data);
        }
        int tmp = data.compareTo(node.getData());
        if (tmp < 0) {
            node.setLeft(addHelper(data, node.getLeft()));
        } else if (tmp > 0) {
            node.setRight(addHelper(data, node.getRight()));
        } else {
            return node;
        }
        calculate(node);
        return balance(node);
    }

    /**
     * Balances the node by rotation.
     *
     * @param node the node of a tree that will get balanced
     * @return node that is balanced
     */
    private AVLNode<T> balance(AVLNode<T> node) {
        if (node.getBalanceFactor() < -1) {
            if (node.getRight().getBalanceFactor() > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            node = rotateLeft(node);
        } else if (node.getBalanceFactor() > 1) {
            if (node.getLeft().getBalanceFactor() < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            node = rotateRight(node);
        }
        return node;
    }

    /**
     * Do right rotation.
     *
     * @param node the node that will get rotated
     * @return node that is right-rotated
     */
    private AVLNode<T> rotateRight(AVLNode<T> node) {
        AVLNode<T> tmp = node.getLeft();
        node.setLeft(tmp.getRight());
        tmp.setRight(node);
        calculate(node);
        calculate(tmp);
        return tmp;
    }

    /**
     * Do left rotation.
     *
     * @param node the node that will get rotated
     * @return node that is left-rotated
     */
    private AVLNode<T> rotateLeft(AVLNode<T> node) {
        AVLNode<T> tmp = node.getRight();
        node.setRight(tmp.getLeft());
        tmp.setLeft(node);
        calculate(node);
        calculate(tmp);
        return tmp;
    }

    /**
     * Calculate height and balance factor.
     *
     * @param node the node that will get calculated
     */
    private void calculate(AVLNode<T> node) {
        int lHeight;
        int rHeight;
        if (node.getLeft() == null) {
            lHeight = -1;
        } else {
            lHeight = node.getLeft().getHeight();
        }
        if (node.getRight() == null) {
            rHeight = -1;
        } else {
            rHeight = node.getRight().getHeight();
        }
        node.setHeight(Math.max(lHeight, rHeight) + 1);
        node.setBalanceFactor(lHeight - rHeight);
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data, NOT predecessor. As a reminder, rotations can occur
     * after removing the successor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can't be null");
        }
        AVLNode<T> removed = new AVLNode<>(null);
        root = removeHelper(data, root, removed);
        return removed.getData();
    }

    /**
     * Returns the node matching the given parameter from the tree.
     *
     * @param data the data to search for in the tree
     * @param node the node currently being examined
     * @param removed the placeholder for the removed node
     * @return the node corresponding to the removed information
     * 
     * Calls itself if the node is not leaf node/data is not found.
     * Also rebalances the tree.
     */
    private AVLNode<T> removeHelper(T data,
                                    AVLNode<T> node, AVLNode<T> removed) {
        if (node == null) {
            throw new NoSuchElementException("Data can't be found");
        }
        int tmp = data.compareTo(node.getData());
        if (tmp < 0) {
            node.setLeft(removeHelper(data, node.getLeft(), removed));
        } else if (tmp > 0) {
            node.setRight(removeHelper(data, node.getRight(), removed));
        } else {
            size--;
            removed.setData(node.getData());
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                AVLNode<T> child = new AVLNode<>(null);
                node.setRight(successorHelper(node.getRight(), child));
                node.setData(child.getData());
            }
        }
        calculate(node);
        return balance(node);
    }

    /**
    * Finds successor of node
    *
    * @param node the node to inspect
    * @param child the child of a node that will be removed
    * @return successor node of an element that will be removed
    */
    private AVLNode<T> successorHelper(AVLNode<T> node, AVLNode<T> child) {
        if (node.getLeft() == null) {
            child.setData(node.getData());
            return node.getRight();
        }
        node.setLeft(successorHelper(node.getLeft(), child));
        calculate(node);
        return balance(node);
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Target data cannot be null");
        }
        return getHelper(root, data);
    }

    /**
     * Returns the element from the tree matching the given parameter recursively.
     *
     * @param data the data to search for in the tree
     * @param curr the node currently being examined
     * @return the data found
     * 
     * Calls itself if the node is not leaf node/data is not found.
     */
    private T getHelper(AVLNode<T> curr, T data) {
        if (curr == null) {
            throw new NoSuchElementException("The data is not found.");
        }

        if (curr.getData().equals(data)) {
            return curr.getData();
        } else if (curr.getData().compareTo(data) < 0) {
            return getHelper(curr.getRight(), data);
        } else {
            return getHelper(curr.getLeft(), data);
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree.
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot check contains for null data in AVL");
        }
        return containsHelper(root, data);
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree recursively.
     *
     * @param root the node to check if it contains the data.
     * @param data the data to search for in the tree.
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * 
     * Calls itself if the node is not a leaf node/the data isn't found.
     */
    private boolean containsHelper(AVLNode<T> root, T data) {
        if (root == null) {
            return false;
        }
        if (data.equals(root.getData())) {
            return true;
        } else if (data.compareTo(root.getData()) < 0) {
            return containsHelper(root.getLeft(), data);
        } else {
            return containsHelper(root.getRight(), data);
        }
    }

    /**
     * Returns the height of the root of the tree.
     *
     * Should be O(1).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (root == null) {
            return -1;
        }
        return root.getHeight();
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Find all elements within a certain distance from the given data.
     * "Distance" means the number of edges between two nodes in the tree.
     *
     * To do this, first find the data in the tree. Keep track of the distance
     * of the current node on the path to the data (you can use the return
     * value of a helper method to denote the current distance to the target
     * data - but note that you must find the data first before you can
     * calculate this information). After you have found the data, you should
     * know the distance of each node on the path to the data. With that
     * information, you can determine how much farther away you can traverse
     * from the main path while remaining within distance of the target data.
     *
     * Use a HashSet as the Set you return. Keep in mind that since it is a
     * Set, you do not have to worry about any specific order in the Set.
     * 
     * This must be implemented recursively.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *              50
     *            /    \
     *         25      75
     *        /  \     / \
     *      13   37  70  80
     *    /  \    \      \
     *   12  15    40    85
     *  /
     * 10
     * elementsWithinDistance(37, 3) should return the set {12, 13, 15, 25,
     * 37, 40, 50, 75}
     * elementsWithinDistance(85, 2) should return the set {75, 80, 85}
     * elementsWithinDistance(13, 1) should return the set {12, 13, 15, 25}
     *
     * @param data     the data to begin calculating distance from
     * @param distance the maximum distance allowed
     * @return the set of all data within a certain distance from the given data
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   is the data is not in the tree
     * @throws java.lang.IllegalArgumentException if distance is negative
     */
    public Set<T> elementsWithinDistance(T data, int distance) {
        if (data == null) {
            throw new IllegalArgumentException("Target data cannot be null.");
        }
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative.");
        }

        Set<T> dataSet = new HashSet<>();
        searchData(root, data, dataSet, distance);
        return dataSet;
    }

    /**
     * Helper method that finds data and the distance of paths
     * 
     * @param curr the current node along the path
     * @param data the target data
     * @param set a set to be filled with data within distance
     * @param distance distance to the target data
     * @return the distance between the current node and the target node
     */
    private int searchData(AVLNode<T> curr, T data, Set<T> set, int distance) {
        if (curr == null) {
            throw new NoSuchElementException("The element is not found.");
        }
        if (curr.getData().equals(data)) {
            addToSet(curr, set, distance, 0);
            return 0;
        } else if (curr.getData().compareTo(data) < 0) {
            int currentDistance = searchData(curr.getRight(), data, set, distance) + 1;
            if (distance >= currentDistance) {
                set.add(curr.getData());
                addToSet(curr.getLeft(), set, distance, currentDistance + 1);
            }
            return currentDistance;
        } else {
            int currentDistance = searchData(curr.getLeft(), data, set, distance) + 1;
            if (distance >= currentDistance) {
                set.add(curr.getData());
                addToSet(curr.getRight(), set, distance, currentDistance + 1);
            }
            return currentDistance;
        }
    }

    
    /**
     * Helper method that adds data that are within distance to a set.
     * 
     * @param curr the current node
     * @param set a set to be filled with data within distance
     * @param distance the target distance
     * @param currentDistance the distance between the current node and the target node
     */
    private void addToSet(AVLNode<T> curr, Set<T> set, int distance, int currentDistance) {
        if (curr == null || distance < currentDistance) {
            return;
        }
        set.add(curr.getData());
        addToSet(curr.getLeft(), set, distance, currentDistance + 1);
        addToSet(curr.getRight(), set, distance, currentDistance + 1);
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
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
