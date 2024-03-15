package nl.curryducker.toolcompat.registry;

import nl.curryducker.toolcompat.ToolCompat;
import nl.curryducker.toolcompat.tools.TCTiers;
import umpaz.nethersdelight.common.item.MacheteItem;

import static nl.curryducker.toolcompat.registry.ItemProperties.getProperties;

public class ND {
    public static String MOD_ID = "nethersdelight";

    public static void register() {
        ToolCompat.LOGGER.debug("Registering Nether's Delight Items for Tool Compat");
        for (TCTiers tier : TCTiers.values()) {
            if (ToolCompat.FORBIDDEN_REGISTRY.contains(tier.toString().toLowerCase() + "_machete"))
                continue;
            ToolCompat.REGISTRY.add(ToolCompat.registerItem(MOD_ID, tier.getMaterialMods(), tier.toString().toLowerCase() + "_machete", () -> new MacheteItem(tier, 2, -2.6F, getProperties(tier)), tier));
        }
    }
}
