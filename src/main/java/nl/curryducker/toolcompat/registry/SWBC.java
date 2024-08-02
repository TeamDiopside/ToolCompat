package nl.curryducker.toolcompat.registry;

import net.minecraft.world.item.SwordItem;
import nl.curryducker.toolcompat.ToolCompat;
import nl.curryducker.toolcompat.tools.simple_weapons.*;
import nl.curryducker.toolcompat.tools.TCTiers;

import static nl.curryducker.toolcompat.ToolCompat.getProperties;

public class SWBC {
    public static String MOD_ID = "simple_weapons";

    public static void register() {
        ToolCompat.LOGGER.debug("Registering Simple Weapons Items for Tool Compat");
        for (TCTiers tier : TCTiers.values()) {
            if (forbidden(tier, "scythe"))
                continue;
            ToolCompat.REGISTRY.add(ToolCompat.registerItem(MOD_ID, tier.getMaterialMods(), tier.toString().toLowerCase() + "_scythe", () -> new ScytheItem(tier, 3, -2.9F, getProperties(tier)), tier));
        }
        for (TCTiers tier : TCTiers.values()) {
            if (forbidden(tier, "greatsword"))
                continue;
            ToolCompat.REGISTRY.add(ToolCompat.registerItem(MOD_ID, tier.getMaterialMods(), tier.toString().toLowerCase() + "_greatsword", () -> new SwordItem(tier, 3, -2.75F, getProperties(tier)), tier));
        }
        for (TCTiers tier : TCTiers.values()) {
            if (forbidden(tier, "spear"))
                continue;
            ToolCompat.REGISTRY.add(ToolCompat.registerItem(MOD_ID, tier.getMaterialMods(), tier.toString().toLowerCase() + "_spear", () -> new SpearItem(tier, 3, -3.3F, getProperties(tier)), tier));
        }
        for (TCTiers tier : TCTiers.values()) {
            if (forbidden(tier, "dagger"))
                continue;
            ToolCompat.REGISTRY.add(ToolCompat.registerItem(MOD_ID, tier.getMaterialMods(), tier.toString().toLowerCase() + "_dagger", () -> new DaggerItem(tier, 3, -1.5F, getProperties(tier)), tier));
        }
        for (TCTiers tier : TCTiers.values()) {
            if (forbidden(tier, "katar"))
                continue;
            ToolCompat.REGISTRY.add(ToolCompat.registerItem(MOD_ID, tier.getMaterialMods(), tier.toString().toLowerCase() + "_katar", () -> new KatarItem(tier, 3, -2.6F, getProperties(tier)), tier));
        }
        for (TCTiers tier : TCTiers.values()) {
            if (forbidden(tier, "scimitar"))
                continue;
            ToolCompat.REGISTRY.add(ToolCompat.registerItem(MOD_ID, tier.getMaterialMods(), tier.toString().toLowerCase() + "_scimitar", () -> new ScimitarItem(tier, 3, -2.15F, getProperties(tier)), tier));
        }
        for (TCTiers tier : TCTiers.values()) {
            if (forbidden(tier, "crowbill"))
                continue;
            ToolCompat.REGISTRY.add(ToolCompat.registerItem(MOD_ID, tier.getMaterialMods(), tier.toString().toLowerCase() + "_crowbill", () -> new CrowbillItem(tier, 3, -2.65F, getProperties(tier)), tier));
        }
        for (TCTiers tier : TCTiers.values()) {
            if (forbidden(tier, "katana"))
                continue;
            ToolCompat.REGISTRY.add(ToolCompat.registerItem(MOD_ID, tier.getMaterialMods(), tier.toString().toLowerCase() + "_katana", () -> new KatanaItem(tier, 3, -2.5F, getProperties(tier)), tier));
        }
    }

    private static boolean forbidden(TCTiers tier, String toolType) {
        return ToolCompat.FORBIDDEN_REGISTRY.contains(tier.toString().toLowerCase() + "_" + toolType);
    }
}
