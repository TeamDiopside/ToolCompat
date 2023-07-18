package nl.curryducker.toolcompat.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import nl.curryducker.toolcompat.ToolCompat;
import org.jetbrains.annotations.Nullable;

public class TCBlockTags extends BlockTagsProvider {
    public TCBlockTags(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, ToolCompat.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
    }
}
