package nl.curryducker.toolcompat.tools;

import net.mcreator.frostedfriends.init.FrostedFriendsModItems;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModTiers implements Tier {
    COPPER(131, 5.0F, 4.0F, 2, 11, () -> Ingredient.of(Items.COPPER_INGOT)),
    ZINC(250, 7.0F, 3.0F, 2, 11, () -> Ingredient.of(Items.IRON_INGOT)),
    BRASS(450, 8.0F, 7.0F, 2, 11, () -> Ingredient.of(Items.IRON_INGOT)),
    ICE(250, 6.0F, 2.0F, 2, 14, () -> Ingredient.of(FrostedFriendsModItems.PACKED_ICE_CUBES.get()));

    private final int level;
    private final int uses;
    private final float speed;
    private final float attackDamageBonus;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    ModTiers(int uses, float speed, float attackDamageBonus, int level, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.uses = uses;
        this.speed = speed;
        this.attackDamageBonus = attackDamageBonus;
        this.level = level;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
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
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
