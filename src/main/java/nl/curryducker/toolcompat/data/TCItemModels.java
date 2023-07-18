package nl.curryducker.toolcompat.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nl.curryducker.toolcompat.ToolCompat;

import java.util.Objects;

public class TCItemModels extends ItemModelProvider {
    public TCItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ToolCompat.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (String type : ToolCompat.MODDED_TOOL_TYPES.keySet())
            unavailable(type);

        for (RegistryObject<? extends Item> entry : ToolCompat.REGISTRY) {
            if ((Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(entry.get())).getPath()).endsWith("snow_shovel")) {
                snowShovelItem(entry.get());
            } else {
                for (String type : ToolCompat.MODDED_TOOL_TYPES.keySet())
                    if ((Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(entry.get())).getPath()).endsWith(type))
                        simpleItem(entry.get(), type);
            }
        }

    }

    private ItemModelBuilder simpleItem(Item item, String type) {
        return withExistingParent(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath(),
                new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(ToolCompat.MODID, "item/" + Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath()))
                .override()
                .predicate(new ResourceLocation(ToolCompat.MODID, "unavailable"), 1F)
                .model(new ModelFile.ExistingModelFile(new ResourceLocation(ToolCompat.MODID, "item/unavailable/" + type), existingFileHelper))
                .end();
    }

    private ItemModelBuilder snowShovelItem(Item item) {
        return withExistingParent(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath(),
                new ResourceLocation(ToolCompat.MODID, "item/datagen_pls_work/snow_shovel"))
                .texture("0", new ResourceLocation(ToolCompat.MODID, "item/" + Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath()))
                .texture("particle", new ResourceLocation(ToolCompat.MODID, "item/" + Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath()))
                .override()
                .predicate(new ResourceLocation(ToolCompat.MODID, "unavailable"), 1F)
                .model(new ModelFile.ExistingModelFile(new ResourceLocation(ToolCompat.MODID, "item/unavailable/snow_shovel"), existingFileHelper))
                .end();

    }

    private ItemModelBuilder unavailable(String type) {
        return withExistingParent("item/unavailable/" + type,
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ToolCompat.MODID, "item/unavailable/" + type));

    }
}
