package nl.curryducker.toolcompat.config;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class TCConfigScreen extends Screen {
    private static final int OPTIONS_LIST_TOP_HEIGHT = 24;
    private static final int OPTIONS_LIST_BOTTOM_OFFSET = 32;
    private static final int OPTIONS_LIST_ITEM_HEIGHT = 25;
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 20;
    private static final int DONE_BUTTON_TOP_OFFSET = 26;

    private final Screen parentScreen;
    private OptionsList optionsRowList;
    private static final int TITLE_HEIGHT = 8;

    public TCConfigScreen(Screen parentScreen) {
        super(Component.translatable("toolcompat.config.title"));
        this.parentScreen = parentScreen;
    }

    @Override
    protected void init() {
        this.optionsRowList = new OptionsList(
                this.getMinecraft(), this.width, this.height,
                OPTIONS_LIST_TOP_HEIGHT,
                this.height - OPTIONS_LIST_BOTTOM_OFFSET,
                OPTIONS_LIST_ITEM_HEIGHT
        );

        OptionInstance<Boolean> optionInstance = OptionInstance.createBoolean(
                "toolcompat.config.jei_available",
                OptionInstance.noTooltip(),
                TCClientConfig.SHOW_UNAVAILABLE_IN_JEI.get(),
                TCClientConfig.SHOW_UNAVAILABLE_IN_JEI::set
        );
        this.optionsRowList.addBig(optionInstance);
        addWidget(this.optionsRowList);

        addRenderableWidget(new Button(
                (this.width - BUTTON_WIDTH) / 2,
                this.height - DONE_BUTTON_TOP_OFFSET,
                BUTTON_WIDTH, BUTTON_HEIGHT,
                Component.translatable("gui.done"),
                button -> this.onClose()
        ));
    }

    @Override
    protected void renderTooltip(PoseStack pPoseStack, ItemStack pItemStack, int pMouseX, int pMouseY) {
        super.renderTooltip(pPoseStack, pItemStack, pMouseX, pMouseY);
    }

    @Override
    public void render(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        this.optionsRowList.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        drawCenteredString(pPoseStack, this.font, this.title.getString(), this.width / 2, TITLE_HEIGHT, 0xFFFFFF);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public void onClose() {
        this.getMinecraft().setScreen(parentScreen);
    }
}
