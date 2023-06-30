package survivalistessentials.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraftforge.fml.ModList;

import net.minecraftforge.registries.ForgeRegistries;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.common.HarvestBlock;
import survivalistessentials.common.TagManager;
import survivalistessentials.config.ConfigHandler;
import survivalistessentials.mixin.AbstractBlockStateAccessor;

public class ItemUse {

    private static final Map<String, String> toolsMap = new HashMap<>();

    private static final List<String> TOOL_TYPES = new ArrayList<>(
        Arrays.asList(
            "pickaxe",
            "pickadze",
            "bow",
            "crossbow",
            "axe",
            "hoe",
            "mattock",
            "kama",
            "shears",
            "shovel",
            "sword",
            "weapon",
            "hammer",
            "wirecutter",
            "wrench",
            "drill",
            "building",
            "revolver",
            "saw",
            "crook",
            "spell"
        )
    );
    
    public static void init() {
        toolsMap.clear();

        for (String item : ConfigHandler.Common.getItems()) {
            String[] nameParts = item.split("-");
            String toolType = nameParts[0];

            if (TOOL_TYPES.contains(toolType)) {
                toolsMap.put(nameParts[1], nameParts[0]);
            }
        }
    }

    public static boolean isAllowedTool(ItemStack stack) {
        String itemName = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(stack.getItem())).getPath();
        String modid = getModId(itemName);
        boolean hasTag = hasTag(stack);

        if (ConfigHandler.Common.invertListToWhitelist()) {
            // Whitelisted
            return hasTag || ConfigHandler.Common.getMods().contains(modid)
                    || toolsMap.get(itemName) != null;
        }
        else {
            // Blacklisted
            return !hasTag && !ConfigHandler.Common.getMods().contains(modid) && toolsMap.get(itemName) == null;
        }
    }

    public static String getModId(Block block) {
        return getModId(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).toString());
    }

    public static String getModId(ItemStack stack) {
        return getModId(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(stack.getItem())).toString());
    }

    public static String getModId(String name) {
        String[] nameParts = name.split(":");

        return nameParts.length == 2 ? nameParts[0] : name;
    }

    public static boolean alwaysDrops(BlockState state) {
        if (((AbstractBlockStateAccessor) state).getDestroySpeed() == 0) {
            return true;
        }
        else {
            return state.is(TagManager.Blocks.ALWAYS_DROPS);
        }
    }

    public static String getToolClass(ItemStack stack) {
        String itemName = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(stack.getItem())).toString();
        String type = toolsMap.get(itemName);

        if (type == null) {
            String[] nameParts = itemName.split("[^a-z]+");

            for (String toolType : TOOL_TYPES) {
                if (itemName.contains(toolType)
                        && Arrays.asList(nameParts).contains(toolType)) {
                    type = toolType;
                }
            }
        }

        return Objects.requireNonNullElse(type, "unknown");
    }

    public static boolean isCorrectToolType(String type, ItemStack handStack) {
        boolean isCorrectToolType = false;
        String toolClass = getToolClass(handStack);

        switch (type) {
            case "pickaxe" -> isCorrectToolType = toolClass.equals(type)
                    || toolClass.equals("drill")
                    || toolClass.equals("pickadze")
                    || toolClass.equals("building")
                    || toolClass.equals("hammer")
                    || toolClass.equals("spell")
                    || ToolType.PICKAXE.is(handStack.getItem());
            case "axe" -> isCorrectToolType = toolClass.equals(type)
                    || toolClass.equals("mattock")
                    || toolClass.equals("building")
                    || toolClass.equals("spell")
                    || ToolType.AXE.is(handStack.getItem());
            case "shovel" -> isCorrectToolType = toolClass.equals(type)
                    || toolClass.equals("mattock")
                    || toolClass.equals("drill")
                    || toolClass.equals("pickadze")
                    || toolClass.equals("building")
                    || toolClass.equals("spell")
                    || ToolType.SHOVEL.is(handStack.getItem());
            case "hoe" -> isCorrectToolType = toolClass.equals(type)
                    || toolClass.equals("mattock")
                    || toolClass.equals("building")
                    || toolClass.equals("crook")
                    || toolClass.equals("spell")
                    || ToolType.HOE.is(handStack.getItem());
        }

        return isCorrectToolType;
    }

    public static boolean isAlwaysBreakable(BlockState state) {
        if (((AbstractBlockStateAccessor) state).getDestroySpeed() == 0) {
            if (!ModList.get().isLoaded("dynamictrees")) {
                return true;
            }
            else {
                return !state.is(TagManager.Blocks.BRANCHES);
            }
        }

        return false;
    }

    public static boolean isCorrectTool(BlockState state, Player player, ItemStack handStack) {
        // Always allow destroySpeed == 0
        if (isAlwaysBreakable(state)) {
            return true;
        }

        // Check tagged tool uses
        if (state.is(BlockTags.MINEABLE_WITH_PICKAXE) && isCorrectToolType("pickaxe", handStack)) {
            return true;
        }
        else if (state.is(BlockTags.MINEABLE_WITH_AXE) && isCorrectToolType("axe", handStack)) {
            return true;
        }
        else if (state.is(BlockTags.MINEABLE_WITH_SHOVEL) && isCorrectToolType("shovel", handStack)) {
            return true;
        }
        else if (state.is(BlockTags.MINEABLE_WITH_HOE) && isCorrectToolType("hoe", handStack)) {
            return true;
        }

        final ToolType expectedToolType = HarvestBlock.BLOCK_TOOL_TYPES.getOrDefault(state.getBlock(), ToolType.NONE);

        // No expected tool type, so we have to return true because we don't know otherwise
        if (expectedToolType == ToolType.NONE) {
            return true;
        }

        // Now, we need to infer if the current item is of a given tool type. Try two things:
        final ToolType inferredToolType = HarvestBlock.ITEM_TOOL_TYPES.getOrDefault(handStack.getItem(), ToolType.NONE);

        if (inferredToolType == expectedToolType) {
            return true; // Correct tool type found!
        }

        // Otherwise, we check if the expected tool type can identify this item as it's tool
        return expectedToolType.is(handStack.getItem());
    }

    public static boolean isArmor(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem;
    }

    public static boolean isAllowedArmor(ItemStack stack) {
        String itemName = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(stack.getItem())).toString();
        String modid = getModId(itemName);
        boolean hasTag = hasTag(stack);

        if (ConfigHandler.Common.invertListToWhitelist()) {
            // Whitelisted
            return hasTag || ConfigHandler.Common.armorMods().contains(modid)
                || ConfigHandler.Common.armorItems().contains(itemName);
        }
        else {
            // Blacklisted
            return !hasTag && !ConfigHandler.Common.armorMods().contains(modid) && !ConfigHandler.Common.armorItems().contains(itemName);
        }
    }

    public static boolean hasTag(ItemStack stack) {
        CompoundTag tags = stack.getTag();
        boolean hasTag = false;

        if (tags != null) {
            hasTag = ConfigHandler.Common.tagList().stream().anyMatch(tags::contains);
        }

        return hasTag;
    }

}
