import inventory.Inventory;
import inventory.InventoryLoader;
import item.Item;
import item.ItemGenerator;
import item.ItemRarity;
import util.Logger;


public class Main {
    public static void main(String[] args) {
        // TEST CODE
        Inventory playerInventory = new Inventory();
        playerInventory.addItem(ItemGenerator.generateRandomItem("Sword"));
        playerInventory.addItem(ItemGenerator.generateRandomItem("Sword"));

        playerInventory.addItem(new Item("Axe", ItemRarity.COMMON));
        playerInventory.addItem(new Item("Axe", ItemRarity.COMMON));
        playerInventory.addItem(new Item("Axe", ItemRarity.COMMON));
        playerInventory.addItem(new Item("Axe", ItemRarity.COMMON));
        playerInventory.addItem(new Item("Axe", ItemRarity.COMMON));

        Logger.logInfo(playerInventory.toString());

        playerInventory.upgradeItem("Axe", ItemRarity.COMMON, 0);

        Logger.logInfo(playerInventory.toString());

        InventoryLoader.saveInventory("playerInventory", playerInventory);
    }
}