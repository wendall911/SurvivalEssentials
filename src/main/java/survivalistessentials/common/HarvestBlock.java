/*
 * Derived from the No Tree Punching mod by AlcatrazEscapee.
 * https://github.com/alcatrazEscapee/no-tree-punching/blob/1.18.x/src/main/java/com/alcatrazescapee/notreepunching/util/HarvestBlockHandler.java
 * Work under copyright. See the project LICENSE.md for details.
 */

package survivalistessentials.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

import survivalistessentials.config.ConfigHandler;
import survivalistessentials.mixin.AbstractBlockAccessor;
import survivalistessentials.mixin.AbstractBlockStateAccessor;
import survivalistessentials.mixin.DiggerItemAccessor;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.util.ItemUse;
import survivalistessentials.util.ToolType;

public final class HarvestBlock {

    public static final Map<Block, ToolType> BLOCK_TOOL_TYPES = new HashMap<>();
    public static final Map<Item, ToolType> ITEM_TOOL_TYPES = new HashMap<>();

    public static void init() {
        BuiltInRegistries.BLOCK.forEach(block -> {
            final AbstractBlockAccessor blockAccess = (AbstractBlockAccessor) block;
            final BlockBehaviour.Properties settings = blockAccess.getProperties();

            // Forcefully set everything to require a tool
            // Need to do both the block settings and the block state since the value is copied there for every state
            settings.requiresCorrectToolForDrops();

            for (BlockState state : block.getStateDefinition().getPossibleStates()) {
                AbstractBlockStateAccessor abstractState = (AbstractBlockStateAccessor) state;

                abstractState.setRequiresCorrectToolForDrops(true);
            }
        });

    }

    public static void setup() {
        BLOCK_TOOL_TYPES.clear();

        final Map<ToolType, List<Block>> unknownToolTypeBlocks = new HashMap<>();

        BuiltInRegistries.BLOCK.forEach(block -> {
            if (ConfigHandler.Common.blockWhitelistMods().contains(ItemUse.getModId(block))) {
                BLOCK_TOOL_TYPES.put(block, ToolType.NONE);

                return;
            }

            // Infer a primary tool type for the block.
            ToolType primary = null;

            if (block.defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE)) {
                primary = ToolType.PICKAXE;
            }
            else if (block.defaultBlockState().is(BlockTags.MINEABLE_WITH_AXE)) {
                primary = ToolType.AXE;
            }
            else if (block.defaultBlockState().is(BlockTags.MINEABLE_WITH_SHOVEL)) {
                primary = ToolType.SHOVEL;
            }
            else if (block.defaultBlockState().is(BlockTags.MINEABLE_WITH_HOE)) {
                primary = ToolType.HOE;
            }
            else if (block.defaultBlockState().is(TagManager.Blocks.MINEABLE_WITH_SHARP)) {
                primary = ToolType.SHARP;
            }

            for (BlockState state : block.getStateDefinition().getPossibleStates()) {
                AbstractBlockStateAccessor abstractState = (AbstractBlockStateAccessor) state;

                if (primary == null) {
                    if (abstractState.getDestroySpeed() <= 0 || abstractState.getPushReaction() == PushReaction.DESTROY) {
                        primary = ToolType.NONE;
                    }
                }
            }

            if (primary != null && primary != ToolType.NONE) {
                BLOCK_TOOL_TYPES.put(block, primary);
            }

            if (primary == null) {
                // Unknown tool type. Collect and log it later
                unknownToolTypeBlocks.computeIfAbsent(ToolType.NONE, k -> new ArrayList<>()).add(block);
            }
        });

        BuiltInRegistries.ITEM.forEach(item -> {
            if (item instanceof DiggerItem digger) {
                final ToolType toolType = toolTypeForMineableTag(((DiggerItemAccessor) digger).getBlocks());

                if (toolType != ToolType.NONE) {
                    ITEM_TOOL_TYPES.put(item, toolType);
                }
            }
            else if (item instanceof SwordItem || item instanceof ShearsItem) {
                ITEM_TOOL_TYPES.put(item, ToolType.SHARP);
            }
        });

        if (!unknownToolTypeBlocks.isEmpty()) {
            SurvivalistEssentials.LOGGER.debug("Unable to infer primary tools for %s blocks with unknown ToolType. These blocks will not enforce correct tool.", unknownToolTypeBlocks.values().stream().mapToInt(Collection::size).sum());
            unknownToolTypeBlocks
                .forEach((toolType, blocks) -> {
                    blocks.forEach((block) -> {
                        SurvivalistEssentials.LOGGER.debug("%s, %s", toolType, block);
                        BLOCK_TOOL_TYPES.put(block, toolType);
                    });
                });
        }
    }

    private static ToolType toolTypeForMineableTag(TagKey<Block> tag) {
        if (tag == BlockTags.MINEABLE_WITH_PICKAXE) {
            return ToolType.PICKAXE;
        }
        else if (tag == BlockTags.MINEABLE_WITH_AXE) {
            return ToolType.AXE;
        }
        else if (tag == BlockTags.MINEABLE_WITH_SHOVEL) {
            return ToolType.SHOVEL;
        }
        else if (tag == BlockTags.MINEABLE_WITH_HOE) {
            return ToolType.HOE;
        }
        else if (tag == TagManager.Blocks.MINEABLE_WITH_SHARP) {
            return ToolType.SHARP;
        }

        return ToolType.NONE;
    }

}
