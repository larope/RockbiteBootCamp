package util;

import item.Item;
import item.ItemRarity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileManager {
    public static void saveInventoryToFile(String filename, List<Item> inventory) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (ItemRarity rarity : ItemRarity.values()) {
                for (Item item : inventory) {
                    if (item.getRarity() == rarity) {
                        writer.write(item.getName() + " " + item.getRarity() + " " + item.getUpgradeCount() + "\n");
                    }
                }
            }
            Logger.logInfo("Inventory saved to " + filename);
        } catch (IOException e) {
            Logger.logError("Error saving inventory to file: " + e.getMessage());
        }
    }
    public static void loadInventoryFromFile(String filename, List<Item> inventory) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0].trim();
                String rarityString = parts[1].trim();
                String upgradeCountString = parts[2].trim();

                ItemRarity rarity = ItemRarity.valueOf(rarityString);
                int upgradeCount = Integer.parseInt(upgradeCountString);

                Item item = new Item(name, rarity);
                if (rarity == ItemRarity.EPIC) {
                    item = new Item(name, upgradeCount);
                }
                inventory.add(item);
            }
            Logger.logInfo("Inventory loaded from " + filename);
        } catch (IOException e) {
            Logger.logError("Error loading inventory from file: " + e.getMessage());
        }
    }
}
