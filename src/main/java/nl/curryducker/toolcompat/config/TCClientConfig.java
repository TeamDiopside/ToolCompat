package nl.curryducker.toolcompat.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class TCClientConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> SHOW_UNAVAILABLE_IN_JEI;

    static {
        BUILDER.push("Tool Compat Client Configs");

        SHOW_UNAVAILABLE_IN_JEI = BUILDER.comment("Should Unavailable Tools Be Visible In JEI/REI/EMI?")
                .define("Show Unavailable In JEI", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
