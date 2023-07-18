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

import java.util.function.Supplier;

public enum TCTiers implements Tier {
    COPPER(131, 5.0F, 4.0F, 2, 11, () -> Ingredient.of(forgeItemTag("ingots/copper")), forgeItemTag("ingots/copper"), "create_sa", "copperized"),
    ZINC(250, 7.0F, 3.0F, 2, 11, () -> Ingredient.of(forgeItemTag("ingots/zinc")), forgeItemTag("ingots/zinc"), "create_sa"),
    BRASS(450, 8.0F, 7.0F, 2, 11, () -> Ingredient.of(forgeItemTag("ingots/brass")), forgeItemTag("ingots/brass"), "create_sa"),
    ICE(250, 6.0F, 2.0F, 2, 14, () -> Ingredient.of(TCItemTags.ICE_TOOL_INGREDIENT), TCItemTags.ICE_TOOL_INGREDIENT, "frosted_friends"),
    STEEL(1000, 7.0F, 3.0F, 2, 11, () -> Ingredient.of(forgeItemTag("ingots/steel")), forgeItemTag("ingots/steel"), Tiers.IRON, "alloyed");


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
}
