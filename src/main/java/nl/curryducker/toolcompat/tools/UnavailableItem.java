package nl.curryducker.toolcompat.tools;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;
import nl.curryducker.toolcompat.ToolCompat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class UnavailableItem extends Item {
    private final String toolMod;
    private final String toolModId;
    private final String[] materialMods;
    private final String[] materialModIds;

    public UnavailableItem(Properties pProperties, String toolMod, String[] materialMods) {
        super(pProperties);
        this.toolMod = ToolCompat.ID_NAME_MAP.get(toolMod);
        this.toolModId = toolMod;
        this.materialMods = new String[materialMods.length];
        this.materialModIds = materialMods;
        for (String mod : materialMods)
            this.materialMods[Arrays.stream(materialMods).toList().indexOf(mod)] = getColor(mod) + ToolCompat.ID_NAME_MAP.get(mod);
    }

    private static String getColor(String mod) {
        return ModList.get().isLoaded(mod) ? "§r§n§a" : "§r§n§c";
    }
    private static ChatFormatting getColorFormat(String... mods) {
        for (String mod : mods)
            if (ModList.get().isLoaded(mod))
                return ChatFormatting.GREEN;
        return ChatFormatting.RED;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("toolcompat.unavailable").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        pTooltipComponents.add(Component.translatable("toolcompat.toolmod").withStyle(getColorFormat(toolModId)).withStyle(ChatFormatting.UNDERLINE).append("§r§7 | " + getColor(toolModId) + toolMod));
        pTooltipComponents.add(Component.translatable(materialMods.length == 1 ? "toolcompat.materialmod" : "toolcompat.materialmods").withStyle(getColorFormat(materialModIds)).withStyle(ChatFormatting.UNDERLINE).append("§r§7 | " + String.join("§r§7, ", materialMods)));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
