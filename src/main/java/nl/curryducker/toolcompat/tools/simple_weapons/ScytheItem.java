package nl.curryducker.toolcompat.tools.simple_weapons;

import net.mcreator.simpleweapons.procedures.IronScytheBleedProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class ScytheItem extends SwordItem {

    public ScytheItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    public boolean hurtEnemy(ItemStack itemStack, LivingEntity entity, LivingEntity sourceEntity) {
        boolean retval = super.hurtEnemy(itemStack, entity, sourceEntity);
        IronScytheBleedProcedure.execute(entity);
        return retval;
    }

    public void appendHoverText(ItemStack itemStack, Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemStack, level, list, flag);
        list.add(Component.literal("§cBleed II"));
        list.add(Component.literal("§8Inflicts Bleed for 3s"));
    }
}
