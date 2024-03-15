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
import nl.curryducker.toolcompat.registry.*;
import nl.curryducker.toolcompat.tools.TCItemProperties;
import nl.curryducker.toolcompat.tools.TCTiers;
import nl.curryducker.toolcompat.tools.UnavailableItem;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static nl.curryducker.toolcompat.registry.ItemProperties.getProperties;

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

            "adamantite_knife",
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
            "electrum_katana"
    );
    public static List<String> FORGE_TOOL_TYPES = List.of(
            "axes",
            "hoes",
            "knives",
            "machetes",
            "pickaxes",
            "shovels",
            "snow_shovels",
            "swords",
            "scythe",
            "greatsword",
            "spear",
            "dagger",
            "katar",
            "scimitar",
            "crowbill",
            "katana"
    );
    public static Map<String, String> MODDED_TOOL_TYPES = Map.ofEntries(
            Map.entry("knife", "knives"),
            Map.entry("machete", "machetes"),
            Map.entry("snow_shovel", "snow_shovels"),
            Map.entry("scythe", "scythes"),
            Map.entry("greatsword", "greatswords"),
            Map.entry("spear", "spears"),
            Map.entry("dagger", "daggers"),
            Map.entry("katar", "katars"),
            Map.entry("scimitar", "scimitars"),
            Map.entry("crowbill", "crowbills"),
            Map.entry("katana", "katanas")
    );
    public static Map<String, String> ID_NAME_MAP = Map.ofEntries(
            Map.entry("farmersdelight", "Farmer's Delight"),
            Map.entry("nethersdelight", "Nether's Delight"),
            Map.entry("frosted_friends", "Frosted Friends"),
            Map.entry("create_sa", "Create Stuff & Additions"),
            Map.entry("copperized", "Copperized"),
            Map.entry("alloyed", "Create: Alloyed"),
            Map.entry("simple_weapons", "Simple Weapons for Better Combat"),
            Map.entry("blue_skies", "Blue Skies"),
            Map.entry("byg", "Oh The Biomes You'll Go"),
            Map.entry("deeperdarker", "Deeper and Darker"),
            Map.entry("stalwart_dungeons", "Stalwart Dungeons"),
            Map.entry("additionaladditions", "Additional Additions"),
            Map.entry("enlightened_end", "Enlightend"),
            Map.entry("oreganized", "Oreganized")
    );
    public static Map<String, String> TYPE_ID_MAP = Map.ofEntries(
            Map.entry("knife", "farmersdelight"),
            Map.entry("machete", "nethersdelight"),
            Map.entry("snow_shovel", "frosted_friends"),
            Map.entry("scythe", "simple_weapons"),
            Map.entry("greatsword", "simple_weapons"),
            Map.entry("spear", "simple_weapons"),
            Map.entry("dagger", "simple_weapons"),
            Map.entry("katar", "simple_weapons"),
            Map.entry("scimitar", "simple_weapons"),
            Map.entry("crowbill", "simple_weapons"),
            Map.entry("katana", "simple_weapons")
    );

    public static List<TCTiers> SMITHING_TIERS = List.of(TCTiers.STEEL, TCTiers.PENDORITE, TCTiers.WARDEN, TCTiers.ROSE_GOLD, TCTiers.GILDED_NETHERITE, TCTiers.ADAMANTITE, TCTiers.ELECTRUM);

    private void registerItems() {
        if (ModList.get().isLoaded("farmersdelight")) {
            FD.register();
        } else {
            registerUnavailable("knife", "farmersdelight");
        }
        if (ModList.get().isLoaded("frosted_friends")) {
            FF.register();
        } else {
            registerUnavailable("snow_shovel", "frosted_friends");
        }
        if (ModList.get().isLoaded("nethersdelight")) {
            ND.register();
        } else {
            registerUnavailable("machete", "nethersdelight");
        }
        if (ModList.get().isLoaded("simple_weapons")) {
            SWBC.register();
        } else {
            registerUnavailable("scythe", "simple_weapons");
            registerUnavailable("greatsword", "simple_weapons");
            registerUnavailable("spear", "simple_weapons");
            registerUnavailable("dagger", "simple_weapons");
            registerUnavailable("katar", "simple_weapons");
            registerUnavailable("scimitar", "simple_weapons");
            registerUnavailable("crowbill", "simple_weapons");
            registerUnavailable("katana", "simple_weapons");
        }

    }


    private void registerUnavailable(String toolType, String toolmod) {
        for (TCTiers tier : TCTiers.values()) {
            String reg_name = tier.toString().toLowerCase() + "_" + toolType;
            if (FORBIDDEN_REGISTRY.contains(reg_name))
                continue;
            ToolCompat.REGISTRY.add(ITEMS.register(reg_name, () -> new UnavailableItem(getProperties(tier), toolmod, tier.getMaterialMods())));
        }
    }

    public static <T extends Item> RegistryObject<T> registerItem(String toolmod, String[] materialMods, String name, Supplier<T> item, TCTiers tier) {
        for (String mod : materialMods) {
            if (ModList.get().isLoaded(mod)) {
                return ITEMS.register(name, item);
            }
        }
        return ITEMS.register(name, () -> (T) new UnavailableItem(getProperties(tier), toolmod, materialMods));
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
