package inventory;

import util.FileManager;

public abstract class InventoryLoader {
    public static void saveInventory(String filename, Inventory fromInventory) {
        FileManager.saveInventoryToFile(filename, fromInventory.items);
    }
    public static void loadInventory(String filename, Inventory toInventory) {
        FileManager.loadInventoryFromFile(filename, toInventory.items);
    }
}
