package inventory;

import item.Item;
import item.ItemRarity;
import util.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Inventory {
    final List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public Item getItemAt(int index) {
        if(index < 0 || index >= items.size()) {
            return null;
        }
        return items.get(index);
    }

    private void upgradeItem(Item itemToUpgrade) {
        if(itemToUpgrade == null) {
            Logger.logError("NULL is not a proper item.");
            return;
        }

        if (itemToUpgrade.getRarity() == ItemRarity.LEGENDARY) {
            Logger.logError(itemToUpgrade + " is already at Legendary rarity and cannot be upgraded further.");
            return;
        }

        final int CURRENT_UPGRADE_REQUIREMENT = itemToUpgrade.getUpgradeRequirement();
        if(CURRENT_UPGRADE_REQUIREMENT == -1){
            return;
        }
        List<Item> matchingItems = findItems(itemToUpgrade.getName(), itemToUpgrade.getRarity(), itemToUpgrade.getUpgradeCount());
        matchingItems.remove(itemToUpgrade);

        if(matchingItems.size() >= CURRENT_UPGRADE_REQUIREMENT){
            itemToUpgrade.upgrade();
            items.removeAll(matchingItems.subList(0, CURRENT_UPGRADE_REQUIREMENT));
            return;
        }

        Logger.logError("Upgrading is not possible, not enough items to upgrade " + itemToUpgrade + ".");
    }
    public void upgradeItem(String name, ItemRarity rarity, int upgradeCount) {
        if(rarity != ItemRarity.EPIC && upgradeCount != 0) {
            Logger.logError("Non epic item has upgrade count.");
        }

        Item itemToUpgrade = findItem(name, rarity, upgradeCount);
        if(itemToUpgrade != null) {
            upgradeItem(itemToUpgrade);
        }
    }
    public void upgradeItemAt(int index) {
        if(index < 0 || index >= items.size()) {
            Logger.logError("Invalid index, no such item in inventory.");
            return;
        }
        upgradeItem(getItemAt(index));
    }

    public Item findItem(String name, ItemRarity rarity, int upgradeCount) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)
                    && item.getRarity() == rarity
                    && item.getUpgradeCount() == upgradeCount) {
                return item;
            }
        }
        Logger.logError("No such item in inventory.");
        return null;
    }
    public List<Item> findItems(String name, ItemRarity rarity, int upgradeCount) {
        List<Item> matchingItems = new ArrayList<>();

        for (Item item : items) {
            if(item.getName().equalsIgnoreCase(name)
                    && item.getRarity() == rarity
                    && item.getUpgradeCount() == upgradeCount) {
                matchingItems.add(item);
            }
        }
        return matchingItems;
    }
    public List<Item> findItems(ItemRarity rarity) {
        List<Item> matchingItems = new ArrayList<>();

        for (Item item : items) {
            if(item.getRarity() == rarity) {
                matchingItems.add(item);
            }
        }
        return matchingItems;
    }
    public List<Item> findItems(String name) {
        List<Item> matchingItems = new ArrayList<>();

        for (Item item : items) {
            if(item.getName().equalsIgnoreCase(name)) {
                matchingItems.add(item);
            }
        }
        return matchingItems;
    }

    @Override
    public String toString() {
        StringBuilder inventoryString = new StringBuilder();
        inventoryString.append("Inventory:\n");

        for (ItemRarity rarity : ItemRarity.values()) {
            List<Item> itemsByRarity = findItems(rarity);

            itemsByRarity.sort(Comparator.comparingInt(Item::getUpgradeCount));

            for (Item item : itemsByRarity) {
                inventoryString.append(" - ").append(item).append("\n");
            }
        }
        return inventoryString.toString();
    }
}
