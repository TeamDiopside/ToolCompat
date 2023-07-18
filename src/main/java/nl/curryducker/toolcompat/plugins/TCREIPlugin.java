package nl.curryducker.toolcompat.plugins;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.entry.EntryRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.forge.REIPluginClient;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import nl.curryducker.toolcompat.config.TCClientConfig;
import nl.curryducker.toolcompat.ToolCompat;
import nl.curryducker.toolcompat.tools.UnavailableItem;

@REIPluginClient
public class TCREIPlugin implements REIClientPlugin {

    @Override
    public void registerEntries(EntryRegistry registry) {
        for (RegistryObject<? extends Item> entry : ToolCompat.REGISTRY)
            if (entry.get() instanceof UnavailableItem && !TCClientConfig.SHOW_UNAVAILABLE_IN_JEI.get())
                registry.removeEntry(EntryStacks.of(entry.get()));
    }
}
