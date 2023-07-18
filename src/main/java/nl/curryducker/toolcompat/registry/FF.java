package nl.curryducker.toolcompat.registry;

import net.minecraft.world.item.Item;
import nl.curryducker.toolcompat.ToolCompat;
import nl.curryducker.toolcompat.tools.SnowShovelItem;
import nl.curryducker.toolcompat.tools.TCTiers;

public class FF {
    public static String MOD_ID = "frosted_friends";

    public static void register() {
        ToolCompat.LOGGER.debug("Registering Frosted Friends Items for Tool Compat");
        for (TCTiers tier : TCTiers.values()) {
            if (ToolCompat.FORBIDDEN_REGISTRY.contains(tier.toString().toLowerCase() + "_snow_shovel"))
                continue;
            ToolCompat.REGISTRY.add(ToolCompat.registerItem(MOD_ID, tier.getMaterialMods(), tier.toString().toLowerCase() + "_snow_shovel", () -> new SnowShovelItem(tier, 1.0F, -2.8F, new Item.Properties().tab(ToolCompat.TAB))));
        }
    }
}
