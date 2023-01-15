package nl.curryducker.toolcompat.tools;

import com.molybdenum.alloyed.common.item.ModItemGroup;
import com.molybdenum.alloyed.common.item.ModItemTiers;
import net.mcreator.createstuffadditions.init.CreateSaModTabs;
import net.mcreator.frostedfriends.init.FrostedFriendsModTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nl.curryducker.toolcompat.ToolCompat;
import nl.curryducker.toolcompat.tools.ModTiers;
import nl.curryducker.toolcompat.tools.SnowShovelItem;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.item.MacheteItem;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.function.Supplier;

public class ToolItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ToolCompat.MODID);

    /**
     * Items
     */

    public static final RegistryObject<Item> ICE_KNIFE = registerItem("farmersdelight", "frosted_friends","ice_knife",
            () -> new KnifeItem(ModTiers.ICE, 0.5F, -2.0F, new Item.Properties().tab(FrostedFriendsModTabs.TAB_FROSTED_FRIENDS_TAB)));

    public static final RegistryObject<Item> COPPER_SNOW_SHOVEL = registerItem("frosted_friends", "create_sa", "copper_snow_shovel",
            () -> new SnowShovelItem(ModTiers.COPPER, 1.0F, -2.8F, new Item.Properties().tab(CreateSaModTabs.TAB_CREATE_STUFF_ADDITIONS_TAB)));

    public static final RegistryObject<Item> ZINC_SNOW_SHOVEL = registerItem("frosted_friends", "create_sa", "zinc_snow_shovel",
            () -> new SnowShovelItem(ModTiers.ZINC, 1.0F, -2.8F, new Item.Properties().tab(CreateSaModTabs.TAB_CREATE_STUFF_ADDITIONS_TAB)));

    public static final RegistryObject<Item> BRASS_SNOW_SHOVEL = registerItem("frosted_friends", "create_sa", "brass_snow_shovel",
            () -> new SnowShovelItem(ModTiers.BRASS, 1.0F, -2.8F, new Item.Properties().tab(CreateSaModTabs.TAB_CREATE_STUFF_ADDITIONS_TAB)));

    public static final RegistryObject<Item> STEEL_SNOW_SHOVEL = registerItem("frosted_friends", "alloyed", "steel_snow_shovel",
            () -> new SnowShovelItem(ModItemTiers.STEEL, 1.0F, -2.8F, new Item.Properties().tab(ModItemGroup.MAIN_GROUP)));

    public static final RegistryObject<Item> COPPER_MACHETE = registerItem("nethersdelight", "create_sa", "copper_machete",
            () -> new MacheteItem(ModTiers.COPPER, 2, -2.6F, new Item.Properties().tab(NethersDelight.CREATIVE_TAB)));

    public static final RegistryObject<Item> ZINC_MACHETE = registerItem("nethersdelight", "create_sa", "zinc_machete",
            () -> new MacheteItem(ModTiers.ZINC, 2, -2.6F, new Item.Properties().tab(NethersDelight.CREATIVE_TAB)));

    public static final RegistryObject<Item> BRASS_MACHETE = registerItem("nethersdelight", "create_sa", "brass_machete",
            () -> new MacheteItem(ModTiers.BRASS, 2, -2.6F, new Item.Properties().tab(NethersDelight.CREATIVE_TAB)));

    public static final RegistryObject<Item> STEEL_MACHETE = registerItem("nethersdelight", "alloyed", "steel_machete",
            () -> new MacheteItem(ModItemTiers.STEEL, 2, -2.6F, new Item.Properties().tab(NethersDelight.CREATIVE_TAB)));

    public static final RegistryObject<Item> ICE_MACHETE = registerItem("nethersdelight", "frosted_friends", "ice_machete",
            () -> new MacheteItem(ModTiers.ICE, 2, -2.6F, new Item.Properties().tab(NethersDelight.CREATIVE_TAB)));

    /**
     * Methods
     */

    private static <T extends Item> RegistryObject<T> registerItem(String toolMod, String materialMod, String name, Supplier<T> item) {
        RegistryObject<T> toReturn = null;
        if (ModList.get().isLoaded(toolMod) && ModList.get().isLoaded(materialMod)) {
            toReturn = ITEMS.register(name, item);
        }
        return toReturn;
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
