package Items;

import Utils.Logger;

public class Item {
    static public final int MAX_EPIC_UPGRADE = 2;
    static public final int EPIC_UPGRADE_REQUIREMENT = 1;
    static public final int UPGRADE_REQUIREMENT = 2;

    private final String name;
    private ItemRarity rarity;
    private int upgradeCount;

    public Item(String name, ItemRarity rarity) {
        this.name = name;
        this.rarity = rarity;
        this.upgradeCount = 0;
    }

    public Item(String name, int upgradeCount) {
        this.name = name;
        this.rarity = ItemRarity.EPIC;
        if (upgradeCount > MAX_EPIC_UPGRADE) {
            Logger.logError("Passed parameter is out of epic upgrade limit.");
            this.upgradeCount = 2;
        } else {
            this.upgradeCount = upgradeCount;
        }
    }

    public String getName() {
        return name;
    }

    public ItemRarity getRarity() {
        return rarity;
    }

    public int getUpgradeCount() {
        return upgradeCount;
    }

    public int getUpgradeRequirement() {
        if (rarity == ItemRarity.EPIC && upgradeCount < Item.MAX_EPIC_UPGRADE) {
            return EPIC_UPGRADE_REQUIREMENT;
        } else if (rarity == ItemRarity.COMMON
                || rarity == ItemRarity.GREAT
                || rarity == ItemRarity.RARE
                || upgradeCount == Item.MAX_EPIC_UPGRADE) {
            return UPGRADE_REQUIREMENT;
        }

        Logger.logError("Legendary items do not have proper upgrade requirement.");
        return -1;
    }

    public void upgrade() {
        if (rarity == ItemRarity.EPIC) {
            upgradeCount++;
            if (upgradeCount > MAX_EPIC_UPGRADE) {
                rarity = ItemRarity.LEGENDARY;
                upgradeCount = 0;
            }
        } else {
            rarity = ItemRarity.values()[rarity.ordinal() + 1];
        }
    }

    @Override
    public String toString() {
        return name + " [" + rarity + (rarity == ItemRarity.EPIC ? " " + upgradeCount : "") + "]";
    }
}
