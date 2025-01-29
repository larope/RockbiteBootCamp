import Inventory.Inventory;
import Inventory.InventoryLoader;
import Items.Item;
import Items.ItemGenerator;
import Items.ItemRarity;
import Utils.Logger;


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