package nl.curryducker.toolcompat.data;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.*;
import net.minecraftforge.registries.ForgeRegistries;
import nl.curryducker.toolcompat.ToolCompat;
import nl.curryducker.toolcompat.tools.TCTiers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import static nl.curryducker.toolcompat.tools.TCTiers.forgeItemTag;

public class TCRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public TCRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    // This is hell, don't even try to understand it

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        for (TCTiers tier : TCTiers.values()) {
            // Tiers that require smithing don't need a shaped recipe
            if (ToolCompat.SMITHING_TIERS.contains(tier))
                continue;
            // Add mods to loading conditions
            ModLoadedCondition[] conditions = new ModLoadedCondition[tier.getMaterialMods().length];
            for (String mod : tier.getMaterialMods())
                conditions[Arrays.stream(tier.getMaterialMods()).toList().indexOf(mod)] = new ModLoadedCondition(mod);
            // Add actual recipes
            shaped(pFinishedRecipeConsumer, "machete",
                    tier,
                    Map.of(
                            'S', Ingredient.of(Items.STICK),
                            'M', tier.getRepairIngredient()
                    ),
                    List.of(
                            "  M",
                            " M ",
                            "S  "
                    ),
                    tier.getRepairTag(),
                    "nethersdelight", conditions
            );
            shaped(pFinishedRecipeConsumer, "knife",
                    tier,
                    Map.of(
                            'S', Ingredient.of(Items.STICK),
                            'M', tier.getRepairIngredient()
                    ),
                    List.of(
                            "M",
                            "S"
                    ),
                    tier.getRepairTag(),
                    "farmersdelight", conditions
            );
            shaped(pFinishedRecipeConsumer, "snow_shovel",
                    tier,
                    Map.of(
                            'S', Ingredient.of(Items.STICK),
                            'M', tier.getRepairIngredient(),
                            'H', Ingredient.of(TagKey.create(Registry.ITEM.key(), new ResourceLocation(ToolCompat.MODID, tier.toString().toLowerCase() + "_snow_shovel_ingredient")))
                    ),
                    List.of("M",
                            "H",
                            "S"
                    ),
                    TagKey.create(Registry.ITEM.key(), new ResourceLocation(ToolCompat.MODID, tier.toString().toLowerCase() + "_snow_shovel_ingredient")),
                    "frosted_friends", conditions
            );

            shaped(pFinishedRecipeConsumer, "scythe",
                    tier,
                    Map.of(
                            'S', Ingredient.of(Items.STICK),
                            'M', tier.getRepairIngredient()
                    ),
                    List.of("MMM",
                            " SM",
                            " S "
                    ),
                    tier.getRepairTag(),
                    "simple_weapons", conditions
            );
            shaped(pFinishedRecipeConsumer, "greatsword",
                    tier,
                    Map.of(
                            'S', Ingredient.of(Items.STICK),
                            'M', tier.getRepairIngredient()
                    ),
                    List.of(" M ",
                            " M ",
                            "MSM"
                    ),
                    tier.getRepairTag(),
                    "simple_weapons", conditions
            );
            shaped(pFinishedRecipeConsumer, "spear",
                    tier,
                    Map.of(
                            'S', Ingredient.of(Items.STICK),
                            'M', tier.getRepairIngredient()
                    ),
                    List.of(" MM",
                            " SM",
                            "S  "
                    ),
                    tier.getRepairTag(),
                    "simple_weapons", conditions
            );
            shaped(pFinishedRecipeConsumer, "dagger",
                    tier,
                    Map.of(
                            'S', Ingredient.of(Items.STICK),
                            'M', tier.getRepairIngredient()
                    ),
                    List.of(" M",
                            "S "
                    ),
                    tier.getRepairTag(),
                    "simple_weapons", conditions
            );
            shaped(pFinishedRecipeConsumer, "katar",
                    tier,
                    Map.of(
                            'S', Ingredient.of(Items.STICK),
                            'M', tier.getRepairIngredient()
                    ),
                    List.of("SM",
                            " S"
                    ),
                    tier.getRepairTag(),
                    "simple_weapons", conditions
            );
            shaped(pFinishedRecipeConsumer, "scimitar",
                    tier,
                    Map.of(
                            'S', Ingredient.of(Items.STICK),
                            'M', tier.getRepairIngredient()
                    ),
                    List.of(" M",
                            "M ",
                            "S "
                    ),
                    tier.getRepairTag(),
                    "simple_weapons", conditions
            );
            shaped(pFinishedRecipeConsumer, "crowbill",
                    tier,
                    Map.of(
                            'S', Ingredient.of(Items.STICK),
                            'M', tier.getRepairIngredient()
                    ),
                    List.of(" MM",
                            "MS ",
                            " S "
                    ),
                    tier.getRepairTag(),
                    "simple_weapons", conditions
            );
            shaped(pFinishedRecipeConsumer, "katana",
                    tier,
                    Map.of(
                            'S', Ingredient.of(Items.STICK),
                            'M', tier.getRepairIngredient()
                    ),
                    List.of("  M",
                            " M ",
                            "S  "
                    ),
                    tier.getRepairTag(),
                    "simple_weapons", conditions
            );
        }
        for (String type : ToolCompat.MODDED_TOOL_TYPES.keySet()) {
            for (TCTiers tier : ToolCompat.SMITHING_TIERS) {
                ModLoadedCondition[] conditions = new ModLoadedCondition[tier.getMaterialMods().length];
                for (String mod : tier.getMaterialMods())
                    conditions[Arrays.stream(tier.getMaterialMods()).toList().indexOf(mod)] = new ModLoadedCondition(mod);
                smithing(pFinishedRecipeConsumer, type,
                        tier,
                        Ingredient.of(TagKey.create(Registry.ITEM.key(), new ResourceLocation(ToolCompat.MODID, tier.toString().toLowerCase() + "_" + type + "_ingredient"))),
                        tier.getRepairTag(),
                        ToolCompat.TYPE_ID_MAP.get(type), conditions
                );
            }
        }
    }

    public static void shaped(Consumer<FinishedRecipe> pFinishedRecipeConsumer, String type, TCTiers tier, Map<Character, Ingredient> ingredients, List<String> pattern, TagKey<Item> advancementsTag, String toolmod, ICondition[] conditions) {
        String name = tier.toString().toLowerCase() + "_" + type;
        if (ToolCompat.FORBIDDEN_REGISTRY.contains(name))
            return;
        ConditionalRecipe.builder().addCondition(new AndCondition(new ModLoadedCondition(toolmod), new OrCondition(conditions)))
                .addRecipe(new ShapedRecipeBuilder.Result(
                        new ResourceLocation(ToolCompat.MODID, name),
                        Objects.requireNonNull(byName(ToolCompat.MODID, name)),
                        1,
                        ToolCompat.MODDED_TOOL_TYPES.get(type),
                        pattern,
                        ingredients,
                        Advancement.Builder.advancement()
                                .addCriterion("has_material", inventoryTrigger(ItemPredicate.Builder.item().of(advancementsTag).build()))
                                .parent(new ResourceLocation("recipes/root"))
                                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(new ResourceLocation(ToolCompat.MODID, name)))
                                .rewards(AdvancementRewards.Builder.recipe(new ResourceLocation(ToolCompat.MODID, name)))
                                .requirements(RequirementsStrategy.OR),
                        new ResourceLocation(ToolCompat.MODID, "recipes/toolcompat/" + name)))
                .generateAdvancement(new ResourceLocation(ToolCompat.MODID, "recipes/toolcompat/" + name))
                .build(pFinishedRecipeConsumer, new ResourceLocation(ToolCompat.MODID, name));
    }

    public static void smithing(Consumer<FinishedRecipe> pFinishedRecipeConsumer, String type, TCTiers tier, Ingredient base, TagKey<Item> advancementsTag, String toolmod, ICondition[] conditions) {
        String name = tier.toString().toLowerCase() + "_" + type;
        if (ToolCompat.FORBIDDEN_REGISTRY.contains(name))
            return;
        ConditionalRecipe.builder().addCondition(new AndCondition(new ModLoadedCondition(toolmod), new OrCondition(conditions)))
                .addRecipe(new UpgradeRecipeBuilder.Result(
                        new ResourceLocation(ToolCompat.MODID, name),
                        RecipeSerializer.SMITHING,
                        base,
                        getSmithingIngredient(tier),
                        Objects.requireNonNull(byName(ToolCompat.MODID, name)),
                        Advancement.Builder.advancement()
                                .addCriterion("has_material", inventoryTrigger(ItemPredicate.Builder.item().of(advancementsTag).build()))
                                .parent(new ResourceLocation("recipes/root"))
                                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(new ResourceLocation(ToolCompat.MODID, name)))
                                .rewards(AdvancementRewards.Builder.recipe(new ResourceLocation(ToolCompat.MODID, name)))
                                .requirements(RequirementsStrategy.OR),
                        new ResourceLocation(ToolCompat.MODID, "recipes/toolcompat/" + name)))
                .generateAdvancement(new ResourceLocation(ToolCompat.MODID, "recipes/toolcompat/" + name))
                .build(pFinishedRecipeConsumer, new ResourceLocation(ToolCompat.MODID, name));
    }

    public static Ingredient getSmithingIngredient(TCTiers tier) {
        if (tier.toString().equalsIgnoreCase("pendorite")) {
            return Ingredient.of(forgeItemTag("ingots/pendorite"));
        } else if (tier.toString().equalsIgnoreCase("rose_gold")) {
            return Ingredient.of(byName("additionaladditions", "rose_gold_alloy"));
        } else if (tier.toString().equalsIgnoreCase("gilded_netherite")) {
            return Ingredient.of(byName("additionaladditions", "gold_ring"));
        } else {
            return tier.getRepairIngredient();
        }
    }


    // Util Methods
    public static @Nullable Item byName(String namespace, String path) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(namespace, path));
    }

}
