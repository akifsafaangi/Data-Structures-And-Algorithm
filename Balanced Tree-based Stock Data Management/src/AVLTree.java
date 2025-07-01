/**
 * The AVLTree class which represents a AVLTree.
 *
 * @file AVLTree.java
 * @author Akif Safa Angi
 * @brief Represents a AVLTree that can handle avltree implementations.
 * @version 1.0
 * @date 2024-05-23
 */

public class AVLTree {

    /**
     * The Node class which represents a Node.
     *
     * @file AVLTree.java
     * @author Akif Safa Angi
     * @brief Represents a Node that holds stock information.
     * @version 1.0
     * @date 2024-05-23
     */
    private class Node {
        /**
         * The Stock object which represents a stock.
         */
        Stock stock;
        /**
         * The Node objects which represents a left and right nodes.
         */
        Node left, right;
        /**
         * The height of the node.
         */
        int height;

        /**
         * Constructs a new Node object.
         * 
         * @param stock The stock object
         */
        Node(Stock stock) {
            this.stock = stock;
            this.height = 1;
        }
    }

    /**
     * The Node object which represents a root node.
     */
    private Node root;

    
    /**
     * Inserts a new stock to the AVL tree.
     * @param stock The stock object
     */
    public void insert(Stock stock) {
        root = insert(root, stock);
    }

    /**
     * Inserts a new stock to the AVL tree.
     * 
     * @param node The node object
     * @param stock The stock object
     * @return Node The node object
     */
    private Node insert(Node node, Stock stock) {
        if (node == null) {
            return new Node(stock);
        }

        if(stock.getSymbol().compareTo(node.stock.getSymbol()) == 0) {
            node.stock = stock; // Update the stock information if the symbol already exists
            return node;
        } else if (stock.getSymbol().compareTo(node.stock.getSymbol()) < 0) {
            node.left = insert(node.left, stock);
        } else {
            node.right = insert(node.right, stock);
        }

        updateHeight(node);
        return balance(node);
    }

    /**
     * Deletes a stock from the AVL tree.
     * 
     * @param symbol The symbol of the stock
     */
    public void delete(String symbol) {
        root = delete(root, symbol);
    }

    /**
     * Deletes a stock from the AVL tree.
     * 
     * @param node The node object
     * @param symbol The symbol of the stock
     * @return Node The deleted node object
     */
    private Node delete(Node node, String symbol) {
        if (node == null) {
            return node;
        }

        if (symbol.compareTo(node.stock.getSymbol()) < 0) { // Go to the left
            node.left = delete(node.left, symbol);
        } else if (symbol.compareTo(node.stock.getSymbol()) > 0) { // Go to the right
            node.right = delete(node.right, symbol);
        } else { // Found the node
            if ((node.left == null) || (node.right == null)) {
                Node temp = null;
                if (temp == node.left) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }

                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                Node temp = minValueNode(node.right);
                node.stock = temp.stock;
                node.right = delete(node.right, temp.stock.getSymbol());
            }
        }

        if (node == null) {
            return node;
        }

        updateHeight(node); // Update the height of the node
        return balance(node); // Balance the tree
    }

    /**
     * Searches for a stock in the AVL tree.
     * 
     * @param symbol The symbol of the stock
     * @return Stock The stock object
     */
    public Stock search(String symbol) {
        Node result = search(root, symbol);
        return (result != null) ? result.stock : null;
    }

    /**
     * Searches for a stock in the AVL tree.
     * 
     * @param node The node object
     * @param symbol The symbol of the stock
     * @return Node The found node object
     */
    private Node search(Node node, String symbol) {
        if (node == null || node.stock.getSymbol().equals(symbol)) {
            return node;
        }

        if (symbol.compareTo(node.stock.getSymbol()) < 0) { // Go to the left
            return search(node.left, symbol);
        } else {
            return search(node.right, symbol);
        }
    }

    /**
     * Finds the minimum value node in the AVL tree.
     * 
     * @param node The node object
     * @return Node The minimum value node object
     */
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) { // Go to the leftmost node
            current = current.left;
        }
        return current;
    }

    /**
     * Balances the AVL tree.
     * 
     * @param node The node object
     * @return Node The balanced node object
     */
    private Node balance(Node node) {
        int balance = getBalance(node); // Get the balance of the node

        if (balance > 1 && getBalance(node.left) >= 0) { // Left Left Case
            return rotateRight(node);
        }

        if (balance > 1 && getBalance(node.left) < 0) { // Left Right Case
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0) { // Right Right Case
            return rotateLeft(node);
        }

        if (balance < -1 && getBalance(node.right) > 0) { // Right Left Case
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    /**
     * Rotates the AVL tree to the right.
     * 
     * @param y The node object
     * @return Node The rotated node object
     */
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    /**
     * Rotates the AVL tree to the left.
     * 
     * @param x The node object
     * @return Node The rotated node object
     */
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(y);
        updateHeight(x);

        return y;
    }

    /**
     * Returns the height of the node.
     * 
     * @param node The node object
     * @return int The height of the node
     */
    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    /**
     * Returns the maximum value of two integers.
     * 
     * @param a The first integer
     * @param b The second integer
     * @return int The maximum value
     */
    int max(int a, int b) 
    { 
        return (a > b) ? a : b; 
    }

    /**
     * Returns the balance of the node.
     * 
     * @param node The node object
     * @return int The balance of the node
     */
    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    /**
     * Updates the height of the node.
     * @param node The node object
     */
    private void updateHeight(Node node) {
        node.height = 1 + max(height(node.left), height(node.right));
    }

    /**
     * In-order traversal
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    /**
     * In-order traversal
     * 
     * @param node The node object
     */
    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.stock);
            inOrderTraversal(node.right);
        }
    }

    /**
     * Pre-order traversal
     */
    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    /**
     * Pre-order traversal
     * 
     * @param node The node object
     */
    private void preOrderTraversal(Node node) { 
        if (node != null) { 
            System.out.println(node.stock); 
            preOrderTraversal(node.left); 
            preOrderTraversal(node.right); 
        } 
    }

    /**
     * Post-order traversal
     */
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    /**
     * Post-order traversal
     * @param node The node object
     */
    private void postOrderTraversal(Node node) { 
        if (node != null) { 
            postOrderTraversal(node.left); 
            postOrderTraversal(node.right); 
            System.out.println(node.stock); 
        }
    }

    /**
     * Method to print the tree in the tree format
     */
    public void printTree() {
        if (root != null) {
            System.out.println(root.stock.getSymbol());
            printTree(root.left, "   ", true);
            printTree(root.right, "   ", false);
        }
    }
    /**
     * Method to print the tree in the tree format
     * @param node The node object
     * @param str The string
     */
    private void printTree(Node node, String indent, boolean isLeft) {
        if (node != null) {
            System.out.println(indent + (isLeft ? "left->" : "right->") + node.stock.getSymbol());
            printTree(node.left, indent + "   ", true);
            printTree(node.right, indent + "   ", false);
        } else {
            System.out.println(indent + (isLeft ? "left->null" : "right->null"));
        }
    }
}