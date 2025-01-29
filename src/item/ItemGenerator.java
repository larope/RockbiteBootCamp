package item;

import util.Logger;

import java.util.Random;

public class ItemGenerator {
    private static final float[] rarityProbabilities = {
            0.50f,
            0.25f,
            0.15f,
            0.08f,
            0.02f
    };
    private static final Random random = new Random();

    private static final ItemRarity[] rarities = ItemRarity.values();

    public static Item generateRandomItem(String name) {
        float randomValue = random.nextFloat();

        float cumulativeProbability = 0.0f;
        for (int i = 0; i < rarityProbabilities.length; i++) {
            cumulativeProbability += rarityProbabilities[i];
            if (randomValue <= cumulativeProbability) {
                return createItem(name, rarities[i]);
            }
        }

        Logger.logError("Possibilities do not add up to 1.");
        return null;
    }

    private static Item createItem(String name, ItemRarity rarity) {
        return new Item(name, rarity);
    }
}
