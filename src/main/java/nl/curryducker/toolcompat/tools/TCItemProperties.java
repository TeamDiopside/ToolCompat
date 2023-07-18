package nl.curryducker.toolcompat.tools;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import nl.curryducker.toolcompat.ToolCompat;

public class TCItemProperties {

    public static void addCustomItemProperties() {
        for (RegistryObject<? extends Item> entry : ToolCompat.REGISTRY) {
            makeUnavailable(entry.get());
        }
    }

    private static void makeUnavailable(Item item) {
        ItemProperties.register(item, new ResourceLocation(ToolCompat.MODID,"unavailable"), (itemStack, clientLevel, livingEntity, p_174638_) -> (itemStack.getItem() instanceof UnavailableItem) ? 1.0F : 0.0F);
    }
}
