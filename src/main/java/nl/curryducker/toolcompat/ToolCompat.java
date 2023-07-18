package nl.curryducker.toolcompat;

import com.mojang.logging.LogUtils;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nl.curryducker.toolcompat.config.TCClientConfig;
import nl.curryducker.toolcompat.config.TCConfigScreen;
import nl.curryducker.toolcompat.registry.FD;
import nl.curryducker.toolcompat.registry.FF;
import nl.curryducker.toolcompat.registry.ND;
import nl.curryducker.toolcompat.tools.TCItemProperties;
import nl.curryducker.toolcompat.tools.TCTiers;
import nl.curryducker.toolcompat.tools.UnavailableItem;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ToolCompat.MODID)
public class ToolCompat {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "toolcompat";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ToolCompat.MODID);

    public ToolCompat() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ITEMS.register(modEventBus);

        registerItems();

        modEventBus.addListener(this::commonSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, TCClientConfig.SPEC, "toolcompat-client.toml");
        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> new TCConfigScreen(parent))
        );

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static List<RegistryObject<? extends Item>> REGISTRY = new ArrayList<>();
    public static List<String> FORBIDDEN_REGISTRY = List.of(
            "steel_knife",

            "ice_snow_shovel",

            "pendorite_scythe",
            "pendorite_greatsword",
            "pendorite_spear",
            "pendorite_dagger",
            "pendorite_katar",
            "pendorite_scimitar",
            "pendorite_crowbill",
            "pendorite_katana",

            "warden_scythe",
            "warden_greatsword",
            "warden_spear",
            "warden_dagger",
            "warden_katar",
            "warden_scimitar",
            "warden_crowbill",
            "warden_katana",

            "adamantite_scythe",
            "adamantite_greatsword",
            "adamantite_spear",
            "adamantite_dagger",
            "adamantite_katar",
            "adamantite_scimitar",
            "adamantite_crowbill",
            "adamantite_katana",

            "electrum_knife",
            "electrum_machete",
            "electrum_shield",
            "electrum_scythe",
            "electrum_greatsword",
            "electrum_spear",
            "electrum_dagger",
            "electrum_katar",
            "electrum_scimitar",
            "electrum_crowbill",
            "electrum_katana",

            "tungsten_shield",
            "tungsten_hammer",

            "chorundum_shield",
            "chorundum_hammer"
    );
    public static List<String> FORGE_TOOL_TYPES = List.of("axes", "hoes", "knives", "machetes", "pickaxes", "shovels", "snow_shovels", "swords");
    public static Map<String, String> MODDED_TOOL_TYPES = Map.of(
            "knife", "knives",
            "machete", "machetes",
            "snow_shovel", "snow_shovels"
    );
    public static Map<String, String> ID_NAME_MAP = Map.of(
            "farmersdelight", "Farmer's Delight",
            "nethersdelight", "Nether's Delight",
            "frosted_friends", "Frosted Friends",
            "create_sa", "Create Stuff & Additions",
            "copperized", "Copperized",
            "alloyed", "Create: Alloyed"
    );
    public static Map<String, String> TYPE_ID_MAP = Map.of(
            "knife", "farmersdelight",
            "machete", "nethersdelight",
            "snow_shovel", "frosted_friends"
    );
    public static List<TCTiers> SMITHING_TIERS = List.of(TCTiers.STEEL);

    private void registerItems() {
        if (ModList.get().isLoaded("farmersdelight")) {
            FD.register();
        } else {
            registerUnavailable("knife", FD.MOD_ID);
        }
        if (ModList.get().isLoaded("frosted_friends")) {
            FF.register();
        } else {
            registerUnavailable("snow_shovel", FF.MOD_ID);
        }
        if (ModList.get().isLoaded("nethersdelight")) {
            ND.register();
        } else {
            registerUnavailable("machete", ND.MOD_ID);
        }
    }


    private void registerUnavailable(String toolType, String toolmod) {
        for (TCTiers tier : TCTiers.values()) {
            String reg_name = tier.toString().toLowerCase() + "_" + toolType;
            if (FORBIDDEN_REGISTRY.contains(reg_name))
                continue;
            ToolCompat.REGISTRY.add(ITEMS.register(reg_name, () -> new UnavailableItem(new Item.Properties().tab(TAB), toolmod, tier.getMaterialMods())));
        }
    }

    public static <T extends Item> RegistryObject<T> registerItem(String toolmod, String[] materialMods, String name, Supplier<T> item) {
        for (String mod : materialMods) {
            if (ModList.get().isLoaded(mod)) {
                return ITEMS.register(name, item);
            }
        }
        return ITEMS.register(name, () -> (T) new UnavailableItem(new Item.Properties().tab(TAB), toolmod, materialMods));
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            TCItemProperties.addCustomItemProperties();
        }
    }

    // Creative Tab
    public static CreativeModeTab TAB = new ToolCompatItemGroup("toolcompat");

    public static class ToolCompatItemGroup extends CreativeModeTab {
        public ToolCompatItemGroup(String label) {
            super(label);
        }

        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(Items.IRON_PICKAXE);
        }

        @Override
        public void fillItemList(@NotNull NonNullList<ItemStack> pItems) {
            for(RegistryObject<? extends Item> entry : REGISTRY)
                if (!(entry.get() instanceof UnavailableItem))
                    entry.get().fillItemCategory(this, pItems);
        }
    }
}
