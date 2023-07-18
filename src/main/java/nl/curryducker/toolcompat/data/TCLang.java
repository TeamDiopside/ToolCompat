package nl.curryducker.toolcompat.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nl.curryducker.toolcompat.ToolCompat;

import java.util.Objects;

public class TCLang extends LanguageProvider {
    public TCLang(DataGenerator gen, String locale) {
        super(gen, ToolCompat.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        for (RegistryObject<? extends Item> entry : ToolCompat.REGISTRY) {
            this.add("item.toolcompat." + (Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(entry.get())).getPath()),
                    capitalizeWord((Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(entry.get())).getPath()).replaceAll("_", " ")));
        }
        this.add("itemGroup.toolcompat", "Tool Compat");
        this.add("toolcompat.unavailable", "This item is unavailable. Please install the required mods to obtain it.");
        this.add("toolcompat.toolmod", "Tool Mod");
        this.add("toolcompat.materialmod", "Material Mod");
        this.add("toolcompat.materialmods", "Material Mods (Only 1 required)");
        this.add("toolcompat.config.title", "Tool Compat Client Config");
        this.add("toolcompat.config.jei_available", "Show Unavailable In JEI");
    }

    public static String capitalizeWord(String str){
        String[] words = str.split(" ");
        StringBuilder capitalizeWord = new StringBuilder();
        for(String w : words){
            String first = w.substring(0,1);
            String afterfirst = w.substring(1);
            capitalizeWord.append(first.toUpperCase()).append(afterfirst).append(" ");
        }
        return capitalizeWord.toString().trim();
    }
}
