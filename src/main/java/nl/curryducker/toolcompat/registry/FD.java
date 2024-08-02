package nl.curryducker.toolcompat.registry;

import nl.curryducker.toolcompat.ToolCompat;
import nl.curryducker.toolcompat.tools.TCTiers;
import vectorwing.farmersdelight.common.item.KnifeItem;

import static nl.curryducker.toolcompat.ToolCompat.getProperties;

public class FD {
    public static String MOD_ID = "farmersdelight";

    public static void register() {
        ToolCompat.LOGGER.debug("Registering Farmer's Delight Items for Tool Compat");
        for (TCTiers tier : TCTiers.values()) {
            if (ToolCompat.FORBIDDEN_REGISTRY.contains(tier.toString().toLowerCase() + "_knife"))
                continue;
            ToolCompat.REGISTRY.add(ToolCompat.registerItem(MOD_ID, tier.getMaterialMods(), tier.toString().toLowerCase() + "_knife", () -> new KnifeItem(tier, 0.5F, -2.0F, getProperties(tier)), tier));
        }
    }
}
