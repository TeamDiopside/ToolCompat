package nl.curryducker.toolcompat.plugins;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import nl.curryducker.toolcompat.config.TCClientConfig;
import nl.curryducker.toolcompat.ToolCompat;
import nl.curryducker.toolcompat.tools.UnavailableItem;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class TCJEIPlugin implements IModPlugin{

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(ToolCompat.MODID, "jei_plugin");
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        List<ItemStack> unavailableItems = new ArrayList<>();
        for (RegistryObject<? extends Item> entry : ToolCompat.REGISTRY)
            if (entry.get() instanceof UnavailableItem)
                unavailableItems.add(new ItemStack(entry.get()));

        if (!TCClientConfig.SHOW_UNAVAILABLE_IN_JEI.get())
            jeiRuntime.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, unavailableItems);
    }
}
