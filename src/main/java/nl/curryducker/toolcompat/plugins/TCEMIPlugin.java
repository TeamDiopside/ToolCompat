package nl.curryducker.toolcompat.plugins;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import nl.curryducker.toolcompat.config.TCClientConfig;
import nl.curryducker.toolcompat.ToolCompat;
import nl.curryducker.toolcompat.tools.UnavailableItem;

@EmiEntrypoint
public class TCEMIPlugin implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        for (RegistryObject<? extends Item> entry : ToolCompat.REGISTRY)
            if (entry.get() instanceof UnavailableItem && !TCClientConfig.SHOW_UNAVAILABLE_IN_JEI.get())
                registry.removeEmiStacks(EmiStack.of(entry.get()));
    }
}
