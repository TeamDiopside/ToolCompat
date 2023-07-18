package nl.curryducker.toolcompat.registry;

import net.mcreator.frostedfriends.init.FrostedFriendsModTabs;
import net.minecraft.world.item.Item;
import nl.curryducker.toolcompat.ToolCompat;
import nl.curryducker.toolcompat.tools.TCTiers;
import vectorwing.farmersdelight.common.item.KnifeItem;

public class FD {
    public static String MOD_ID = "farmersdelight";

    public static void register() {
        ToolCompat.LOGGER.debug("Registering Farmer's Delight Items for Tool Compat");
        for (TCTiers tier : TCTiers.values()) {
            if (ToolCompat.FORBIDDEN_REGISTRY.contains(tier.toString().toLowerCase() + "_knife"))
                continue;
            ToolCompat.REGISTRY.add(ToolCompat.registerItem(MOD_ID, tier.getMaterialMods(), tier.toString().toLowerCase() + "_knife", () -> new KnifeItem(tier, 0.5F, -2.0F, new Item.Properties().tab(ToolCompat.TAB))));
        }
    }
}
