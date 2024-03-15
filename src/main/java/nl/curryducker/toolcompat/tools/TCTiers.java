package nl.curryducker.toolcompat.tools;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;
import nl.curryducker.toolcompat.data.TCItemTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public enum TCTiers implements Tier {
    COPPER(131, 5.0F, 4.0F, 2, 11, () -> Ingredient.of(forgeItemTag("ingots/copper")), forgeItemTag("ingots/copper"), "create_sa", "copperized"),
    ZINC(250, 7.0F, 3.0F, 2, 11, () -> Ingredient.of(forgeItemTag("ingots/zinc")), forgeItemTag("ingots/zinc"), "create_sa"),
    BRASS(450, 8.0F, 7.0F, 2, 11, () -> Ingredient.of(forgeItemTag("ingots/brass")), forgeItemTag("ingots/brass"), "create_sa"),
    ICE(250, 6.0F, 2.0F, 2, 14, () -> Ingredient.of(TCItemTags.ICE_TOOL_INGREDIENT), TCItemTags.ICE_TOOL_INGREDIENT, "frosted_friends"),
    STEEL(1000, 7.0F, 3.0F, 2, 11, () -> Ingredient.of(forgeItemTag("ingots/steel")), forgeItemTag("ingots/steel"), Tiers.IRON, "alloyed"),
    DIOPSIDE(1661, 6.0F, 5.0F, 3, 14, () -> Ingredient.of(forgeItemTag("gems/diopside")), forgeItemTag("gems/diopside"), "blue_skies"),
    AQUITE(270, 6.0F, 2.0F, 2, 32, () -> Ingredient.of(forgeItemTag("gems/aquite")), forgeItemTag("gems/aquite"), "blue_skies"),
    PYROPE(200, 11.0F, 1.5F, 1, 10, () -> Ingredient.of(forgeItemTag("gems/pyrope")), forgeItemTag("gems/pyrope"), "blue_skies"),
    CHAROITE(1561, 8.0F, 3.0F, 3, 10, () -> Ingredient.of(forgeItemTag("gems/charoite")), forgeItemTag("gems/charoite"), "blue_skies"),
    HORIZONITE(250, 8.0F, 2.0F, 3, 10, () -> Ingredient.of(forgeItemTag("ingots/horizonite")), forgeItemTag("ingots/horizonite"), "blue_skies"),
    PENDORITE(2500, 10.0F, 4.0F, 5, 15, () -> Ingredient.of(TCItemTags.PENDORITE_REPAIR_INGREDIENT), TCItemTags.PENDORITE_REPAIR_INGREDIENT, Tiers.NETHERITE, "byg"),
    WARDEN(2519, 10.0F, 5.0F, 5, 18, () -> Ingredient.of(TCItemTags.WARDEN_REPAIR_INGREDIENT), TCItemTags.WARDEN_REPAIR_INGREDIENT, Tiers.NETHERITE, "deeperdarker"),
    TUNGSTEN(750, 11.0F, 4.0F, 3, 2, () -> Ingredient.of(forgeItemTag("ingots/tungsten")), forgeItemTag("ingots/tungsten"), "stalwart_dungeons"),
    CHORUNDUM(2021, 11.0F, 5.0F, 4, 28, () -> Ingredient.of(forgeItemTag("gems/chorundum")), forgeItemTag("gems/chorundum"), "stalwart_dungeons"),
    ROSE_GOLD(900, 9.0F, 2.0F, 2, 17, () -> Ingredient.of(forgeItemTag("ingots/copper")), forgeItemTag("ingots/copper"), Tiers.IRON, "additionaladditions"),
    GILDED_NETHERITE(2031, 10.0F, 2.0F, 4, 20, () -> Ingredient.of(forgeItemTag("ingots/netherite")), forgeItemTag("ingots/netherite"), Tiers.NETHERITE, "additionaladditions"),
    ADAMANTITE(1561, 14.0F, 5.0F, 5, 15, () -> Ingredient.of(forgeItemTag("ingots/adamantite")), forgeItemTag("ingots/adamantite"), Tiers.IRON, "enlightened_end"),
    ELECTRUM(1561, 11.0F, 3.0F, 4, 18, () -> Ingredient.of(forgeItemTag("ingots/electrum")), forgeItemTag("ingots/electrum"), Tiers.DIAMOND, "oreganized");


    private final int level;
    private final int uses;
    private final float speed;
    private final float attackDamageBonus;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;
    private final TagKey<Item> repairTag;
    private final String[] materialMods;
    private Tier smithingIngredientTier = Tiers.IRON;

    TCTiers(int uses, float speed, float attackDamageBonus, int level, int enchantmentValue, Supplier<Ingredient> repairIngredient, TagKey<Item> repairTag, String... materialMod) {
        this.uses = uses;
        this.speed = speed;
        this.attackDamageBonus = attackDamageBonus;
        this.level = level;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
        this.repairTag = repairTag;
        this.materialMods = materialMod;
    }

    TCTiers(int uses, float speed, float attackDamageBonus, int level, int enchantmentValue, Supplier<Ingredient> repairIngredient, TagKey<Item> repairTag, Tier smithingIngredientTier, String... materialMod) {
        this.uses = uses;
        this.speed = speed;
        this.attackDamageBonus = attackDamageBonus;
        this.level = level;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
        this.repairTag = repairTag;
        this.smithingIngredientTier = smithingIngredientTier;
        this.materialMods = materialMod;
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamageBonus;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public TagKey<Item> getRepairTag() {
        return repairTag;
    }

    public String[] getMaterialMods() {
        return materialMods;
    }

    public Tier getSmithingIngredientTier() {
        return smithingIngredientTier;
    }

    public static TagKey<Item> forgeItemTag(String path) {
        return TagKey.create(Registry.ITEM.key(), new ResourceLocation("forge", path));
    }

    public static @Nullable Item byName(String namespace, String path) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(namespace, path));
    }
}
