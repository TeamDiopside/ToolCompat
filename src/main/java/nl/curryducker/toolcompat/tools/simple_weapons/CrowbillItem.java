package nl.curryducker.toolcompat.tools.simple_weapons;

import net.mcreator.simpleweapons.procedures.LowCrowbillOnHitProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class CrowbillItem extends SwordItem {

    public CrowbillItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        LowCrowbillOnHitProcedure.execute(entity.level, entity);
        return retval;
    }

    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.literal("§3Crippling"));
        list.add(Component.literal("§8Inflict Slowness II for 1s"));
        list.add(Component.literal("§9Armor Piercing I"));
        list.add(Component.literal("§8+3 Damage against armored targets"));
    }
}
