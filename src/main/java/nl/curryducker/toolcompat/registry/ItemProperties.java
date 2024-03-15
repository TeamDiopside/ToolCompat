package nl.curryducker.toolcompat.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import nl.curryducker.toolcompat.ToolCompat;
import nl.curryducker.toolcompat.tools.TCTiers;

public class ItemProperties {
    public static Item.Properties getProperties(TCTiers tier) {
        Item.Properties prop = new Item.Properties().tab(ToolCompat.TAB);
        if (tier.toString().equalsIgnoreCase("warden"))
            prop.rarity(Rarity.RARE).fireResistant();
        return prop;
    }
}
