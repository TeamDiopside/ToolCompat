package nl.curryducker.toolcompat.registry;

import net.minecraft.world.item.Item;
import nl.curryducker.toolcompat.ToolCompat;
import nl.curryducker.toolcompat.tools.TCTiers;
import umpaz.nethersdelight.common.item.MacheteItem;

public class ND {
    public static String MOD_ID = "nethersdelight";

    public static void register() {
        ToolCompat.LOGGER.debug("Registering Nether's Delight Items for Tool Compat");
        for (TCTiers tier : TCTiers.values()) {
            ToolCompat.REGISTRY.add(ToolCompat.registerItem(MOD_ID, tier.getMaterialMods(), tier.toString().toLowerCase() + "_machete", () -> new MacheteItem(tier, 2, -2.6F, new Item.Properties().tab(ToolCompat.TAB))));
        }
    }
}
