package nl.curryducker.toolcompat.registry;

import nl.curryducker.toolcompat.ToolCompat;
import nl.curryducker.toolcompat.tools.SnowShovelItem;
import nl.curryducker.toolcompat.tools.TCTiers;

import static nl.curryducker.toolcompat.registry.ItemProperties.getProperties;

public class FF {
    public static String MOD_ID = "frosted_friends";

    public static void register() {
        ToolCompat.LOGGER.debug("Registering Frosted Friends Items for Tool Compat");
        for (TCTiers tier : TCTiers.values()) {
            if (ToolCompat.FORBIDDEN_REGISTRY.contains(tier.toString().toLowerCase() + "_snow_shovel"))
                continue;
            ToolCompat.REGISTRY.add(ToolCompat.registerItem(MOD_ID, tier.getMaterialMods(), tier.toString().toLowerCase() + "_snow_shovel", () -> new SnowShovelItem(tier, 1.0F, -2.8F, getProperties(tier)), tier));
        }
    }
}
