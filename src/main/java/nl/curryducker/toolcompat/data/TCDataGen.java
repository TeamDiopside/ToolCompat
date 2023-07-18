package nl.curryducker.toolcompat.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nl.curryducker.toolcompat.ToolCompat;

@Mod.EventBusSubscriber(modid = ToolCompat.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TCDataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(true, new TCRecipeProvider(gen));
        BlockTagsProvider blockTagsProvider = new TCBlockTags(gen, existingFileHelper);
        gen.addProvider(true, blockTagsProvider);
        gen.addProvider(true, new TCItemTags(gen, blockTagsProvider, existingFileHelper));
        gen.addProvider(true, new TCItemModels(gen, existingFileHelper));
        gen.addProvider(true, new TCLang(gen, "en_us"));
    }
}
