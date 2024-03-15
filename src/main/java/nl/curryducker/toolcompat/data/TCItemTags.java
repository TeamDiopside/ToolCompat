package nl.curryducker.toolcompat.data;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import nl.curryducker.toolcompat.ToolCompat;
import nl.curryducker.toolcompat.tools.TCTiers;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class TCItemTags extends ItemTagsProvider {

    public TCItemTags(DataGenerator pGenerator, BlockTagsProvider pBlockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, pBlockTagsProvider, ToolCompat.MODID, existingFileHelper);
    }

    public static final TagKey<Item> ICE_TOOL_INGREDIENT = TagKey.create(Registry.ITEM.key(), new ResourceLocation(ToolCompat.MODID, "ice_tool_ingredient"));
    public static final TagKey<Item> PENDORITE_REPAIR_INGREDIENT = TagKey.create(Registry.ITEM.key(), new ResourceLocation(ToolCompat.MODID, "pendorite_repair_ingredient"));
    public static final TagKey<Item> WARDEN_REPAIR_INGREDIENT = TagKey.create(Registry.ITEM.key(), new ResourceLocation(ToolCompat.MODID, "warden_repair_ingredient"));

    @Override
    protected void addTags() {
        // Add Items to Ingredient Tags
        getOrCreateRawBuilder(ICE_TOOL_INGREDIENT)
                .addOptionalElement(new ResourceLocation("frosted_friends", "packed_ice_cubes"));
        getOrCreateRawBuilder(PENDORITE_REPAIR_INGREDIENT)
                .addOptionalElement(new ResourceLocation("byg", "pendorite_scraps"));
        getOrCreateRawBuilder(WARDEN_REPAIR_INGREDIENT)
                .addOptionalElement(new ResourceLocation("deeperdarker", "reinforced_echo_shard"));
        // Add shovel ingredients
        for (TCTiers tier : TCTiers.values()) {
            if (tier == TCTiers.ICE || ToolCompat.SMITHING_TIERS.contains(tier))
                continue;
            for (String mod : tier.getMaterialMods())
                getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation(ToolCompat.MODID, tier.toString().toLowerCase() + "_snow_shovel_ingredient")))
                        .addOptionalElement(new ResourceLocation(mod, tier.toString().toLowerCase() + "_shovel"));
        }// Add smithing ingredients
        for (String type : ToolCompat.MODDED_TOOL_TYPES.keySet())
            for (TCTiers tier : ToolCompat.SMITHING_TIERS) {
                if (ToolCompat.FORBIDDEN_REGISTRY.contains(tier.toString().toLowerCase() + "_" + type))
                    continue;

                // Damn why did they have to be inconsistent
                String newType = type;
                if (type.equalsIgnoreCase("katar")) {
                    newType = "fist";
                } else if (type.equalsIgnoreCase("crowbill")) {
                    newType = "sickle";
                } else if (type.equalsIgnoreCase("spear")) {
                    newType = "halberd";
                }

                getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation(ToolCompat.MODID, tier.toString().toLowerCase() + "_" + type + "_ingredient")))
                        .addOptionalElement(new ResourceLocation(ToolCompat.TYPE_ID_MAP.get(type), tier.getSmithingIngredientTier().toString().toLowerCase() + "_" + newType));
            }

        // Add missing tags
        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "tools/axes")))
                .addOptionalElement(new ResourceLocation("create_sa", "blazing_axe"))
                .addOptionalElement(new ResourceLocation("create_sa", "rose_quartz_axe"))
                .addOptionalElement(new ResourceLocation("create_sa", "brass_axe"))
                .addOptionalElement(new ResourceLocation("create_sa", "zinc_axe"))
                .addOptionalElement(new ResourceLocation("create_sa", "copper_axe"))
                .addOptionalElement(new ResourceLocation("create_sa", "experience_axe"))
                .addOptionalElement(new ResourceLocation("alloyed", "steel_axe"))
                .addOptionalElement(new ResourceLocation("frosted_friends", "ice_axe"));

        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "tools/hoes")))
                .addOptionalElement(new ResourceLocation("create_sa", "brass_hoe"))
                .addOptionalElement(new ResourceLocation("create_sa", "zinc_hoe"))
                .addOptionalElement(new ResourceLocation("create_sa", "copper_hoe"))
                .addOptionalElement(new ResourceLocation("alloyed", "steel_hoe"))
                .addOptionalElement(new ResourceLocation("frosted_friends", "ice_hoe"));

        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "tools/knives")))
                .addOptionalTag(new ResourceLocation("farmersdelight", "tools/knives"));

        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("nethersdelight", "tools/machetes")))
                .addOptionalElement(new ResourceLocation("oreganized", "electrum_machete"));

        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "tools/machetes")))
                .addOptionalTag(new ResourceLocation("nethersdelight", "tools/machetes"));

        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "tools/pickaxes")))
                .addOptionalElement(new ResourceLocation("alexsmobs", "ghostly_pickaxe"))
                .addOptionalElement(new ResourceLocation("create_sa", "blazing_pickaxe"))
                .addOptionalElement(new ResourceLocation("create_sa", "rose_quartz_pickaxe"))
                .addOptionalElement(new ResourceLocation("create_sa", "brass_pickaxe"))
                .addOptionalElement(new ResourceLocation("create_sa", "zinc_pickaxe"))
                .addOptionalElement(new ResourceLocation("create_sa", "copper_pickaxe"))
                .addOptionalElement(new ResourceLocation("create_sa", "experience_pickaxe"))
                .addOptionalElement(new ResourceLocation("alloyed", "steel_pickaxe"))
                .addOptionalElement(new ResourceLocation("frosted_friends", "ice_pickaxe"));

        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "tools/shovels")))
                .addOptionalElement(new ResourceLocation("create_sa", "blazing_shovel"))
                .addOptionalElement(new ResourceLocation("create_sa", "rose_quartz_shovel"))
                .addOptionalElement(new ResourceLocation("create_sa", "brass_shovel"))
                .addOptionalElement(new ResourceLocation("create_sa", "zinc_shovel"))
                .addOptionalElement(new ResourceLocation("create_sa", "copper_shovel"))
                .addOptionalElement(new ResourceLocation("create_sa", "experience_shovel"))
                .addOptionalElement(new ResourceLocation("alloyed", "steel_shovel"))
                .addOptionalElement(new ResourceLocation("frosted_friends", "ice_shovel"));

        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "tools/snow_shovels")))
                .addOptionalElement(new ResourceLocation("frosted_friends", "wooden_snow_shovel"))
                .addOptionalElement(new ResourceLocation("frosted_friends", "stone_snow_shovel"))
                .addOptionalElement(new ResourceLocation("frosted_friends", "iron_snow_shovel"))
                .addOptionalElement(new ResourceLocation("frosted_friends", "gold_snow_shovel"))
                .addOptionalElement(new ResourceLocation("frosted_friends", "diamond_snow_shovel"))
                .addOptionalElement(new ResourceLocation("frosted_friends", "netherite_snow_shovel"))
                .addOptionalElement(new ResourceLocation("frosted_friends", "ice_snow_shovel"));

        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "tools/swords")))
                .addOptionalElement(new ResourceLocation("create_sa", "blazing_cleaver"))
                .addOptionalElement(new ResourceLocation("create_sa", "rose_quartz_sword"))
                .addOptionalElement(new ResourceLocation("create_sa", "brass_sword"))
                .addOptionalElement(new ResourceLocation("create_sa", "zinc_sword"))
                .addOptionalElement(new ResourceLocation("create_sa", "copper_sword"))
                .addOptionalElement(new ResourceLocation("create_sa", "experience_sword"))
                .addOptionalElement(new ResourceLocation("alloyed", "steel_sword"))
                .addOptionalElement(new ResourceLocation("frosted_friends", "ice_sword"));

        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("cluster_max_harvestables")))
                .addOptionalTag(new ResourceLocation("forge", "tools/pickaxes"));

        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "ingots")))
                .addOptionalTag(new ResourceLocation("forge", "ingots/horizonite"))
                .addOptionalTag(new ResourceLocation("forge", "ingots/pendorite"))
                .addOptionalTag(new ResourceLocation("forge", "ingots/tungsten"))
                .addOptionalTag(new ResourceLocation("forge", "ingots/adamantite"));

        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "gems")))
                .addOptionalTag(new ResourceLocation("forge", "gems/diopside"))
                .addOptionalTag(new ResourceLocation("forge", "gems/aquite"))
                .addOptionalTag(new ResourceLocation("forge", "gems/pyrope"))
                .addOptionalTag(new ResourceLocation("forge", "gems/charoite"))
                .addOptionalTag(new ResourceLocation("forge", "gems/chorundum"));

        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "gems/diopside")))
                .addOptionalTag(new ResourceLocation("blue_skies", "gems/diopside"));
        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "gems/aquite")))
                .addOptionalTag(new ResourceLocation("blue_skies", "gems/aquite"));
        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "gems/pyrope")))
                .addOptionalTag(new ResourceLocation("blue_skies", "gems/pyrope"));
        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "gems/charoite")))
                .addOptionalTag(new ResourceLocation("blue_skies", "gems/charoite"));
        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "gems/chorundum")))
                .addOptionalElement(new ResourceLocation("stalwart_dungeons", "chorundum"));

        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "ingots/horizonite")))
                .addOptionalTag(new ResourceLocation("blue_skies", "ingots/horizonite"));
        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "ingots/pendorite")))
                .addOptionalElement(new ResourceLocation("byg", "pendorite_ingot"));
        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "ingots/tungsten")))
                .addOptionalElement(new ResourceLocation("stalwart_dungeons", "tungsten_ingot"));
        getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "ingots/adamantite")))
                .addOptionalElement(new ResourceLocation("enlightened_end", "adamantite_ingot"));

        // Add Forge tool Tags to other forge tool tags because mods use different types
        for (String forgeToolType : ToolCompat.FORGE_TOOL_TYPES) {
            getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", forgeToolType)))
                    .addOptionalTag(new ResourceLocation("forge", "tools/" + forgeToolType));
            getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "tools")))
                    .addOptionalTag(new ResourceLocation("forge", "tools/" + forgeToolType));
        }

        // Add Tool Compat tools to tags
        for (String type : ToolCompat.MODDED_TOOL_TYPES.keySet()) {
            for (TCTiers tier : TCTiers.values()) {
                String name = tier.toString().toLowerCase() + "_" + type;
                if (ToolCompat.FORBIDDEN_REGISTRY.contains(name))
                    continue;
                if (Objects.equals(type, "machete")) {
                    getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("nethersdelight", "tools/machetes")))
                            .addOptionalElement(new ResourceLocation(ToolCompat.MODID, name));
                    continue;
                }
                if (Objects.equals(type, "knife")) {
                    getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("farmersdelight", "tools/knives")))
                            .addOptionalElement(new ResourceLocation(ToolCompat.MODID, name));
                    continue;
                }
                getOrCreateRawBuilder(TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", "tools/" + ToolCompat.MODDED_TOOL_TYPES.get(type))))
                        .addOptionalElement(new ResourceLocation(ToolCompat.MODID, name));
            }
        }

    }
}
