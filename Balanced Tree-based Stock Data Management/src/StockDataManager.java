/**
 * The StockDataManager class which represents a StockDataManager.
 *
 * @file StockDataManager.java
 * @author Akif Safa Angi
 * @brief Represents a StockDataManager that handles stock management.
 * @version 1.0
 * @date 2024-05-23
 */
public class StockDataManager {

    /**
     * The AVLTree object which represents a AVLTree.
     */
    private AVLTree avlTree;

    /**
     * Constructs a new StockDataManager object.
     */
    public StockDataManager() {
        avlTree = new AVLTree();
    }

    /**
     * Adds or updates a stock.
     * 
     * @param symbol The symbol of the stock
     * @param price The price of the stock
     * @param volume The volume of the stock
     * @param marketCap The market cap of the stock
     */
    public void addOrUpdateStock(String symbol, double price, long volume, long marketCap) {
        Stock existingStock = avlTree.search(symbol);
        if (existingStock != null) { // Update existing stock
            existingStock.setPrice(price);
            existingStock.setVolume(volume);
            existingStock.setMarketCap(marketCap);
        } else { // Add new stock
            Stock newStock = new Stock(symbol, price, volume, marketCap);
            avlTree.insert(newStock);
        }
    }

    /**
     * Removes a stock.
     * 
     * @param symbol The symbol of the stock
     */
    public void removeStock(String symbol) {
        avlTree.delete(symbol);
    }

    /**
     * Searches for a stock.
     * 
     * @param symbol The symbol of the stock
     * @return Stock The stock object
     */
    public Stock searchStock(String symbol) {
        return avlTree.search(symbol);
    }

    /**
     * Updates a stock.
     * 
     * @param symbol The symbol of the stock
     * @param newSymbol The new symbol of the stock
     * @param newPrice The new price of the stock
     * @param newVolume The new volume of the stock
     * @param newMarketCap The new market cap of the stock
     */
    public void updateStock(String symbol, String newSymbol, double newPrice, long newVolume, long newMarketCap) {
        Stock stock = avlTree.search(symbol);
        if (stock != null) { // Update existing stock
            stock.setSymbol(newSymbol);
            stock.setPrice(newPrice);
            stock.setVolume(newVolume);
            stock.setMarketCap(newMarketCap);
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        StockDataManager manager = new StockDataManager();
        manager.addOrUpdateStock("AAPL", 150.0, 1000000, 2500000000L);
        manager.addOrUpdateStock("GOOGL", 2800.0, 500000, 1500000000L);
        System.out.println(manager.searchStock("AAPL"));
        manager.removeStock("AAPL");
        System.out.println(manager.searchStock("AAPL"));
    }
    /**
     * Prints the AVL tree in order.
     */
    public void printInOrder() {
        System.out.println("In Order Traversal:");
        avlTree.inOrderTraversal();
    }
    /**
     * Prints the AVL tree in pre order.
     */
    public void printPreOrder() {
        System.out.println("Pre Order Traversal:");
        avlTree.preOrderTraversal();
    }
    /**
     * Prints the AVL tree in post order.
     */
    public void printPostOrder() {
        System.out.println("Post Order Traversal:");
        avlTree.postOrderTraversal();
    }

    /**
     * Prints the AVL tree.
     */
    public void printTree() {
        System.out.println("AVL Tree:");
        avlTree.printTree();
    }
}
