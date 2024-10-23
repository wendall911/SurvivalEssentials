package survivalistessentials.items;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;

import survivalistessentials.common.TagManager;

public record ToolMaterials() {

    public static final ToolMaterial FLINT =
        //new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 20, 1.5F, 0.5F, 1, TagManager.Items.FLINT_TOOL_MATERIALS);
            new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 20, 1.5F, 0.5F, 1, ItemTags.STONE_TOOL_MATERIALS);
    public static final ToolMaterial STONE =
        new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 5, 1.5F, 0.5F, 1, ItemTags.STONE_TOOL_MATERIALS);
    public static final ToolMaterial IRON =
        new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 320, 1.5F, 0.0F, 1, ItemTags.IRON_TOOL_MATERIALS);
    public static final ToolMaterial DIAMOND =
        new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 640, 1.5F, 0.0F, 1, ItemTags.DIAMOND_TOOL_MATERIALS);
    public static final ToolMaterial NONE =
        new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 1, 0.0F, 0.0F, 1, ItemTags.WOODEN_TOOL_MATERIALS);

}
