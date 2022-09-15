package survivalistessentials.event;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModList;

import survivalistessentials.common.HarvestBlock;
import survivalistessentials.common.TagManager;
import survivalistessentials.config.ConfigHandler;
import survivalistessentials.mixin.AbstractBlockStateAccessor;
import survivalistessentials.sound.Sounds;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.util.Chat;
import survivalistessentials.util.ItemUse;
import survivalistessentials.util.ToolType;

@Mod.EventBusSubscriber(modid = SurvivalistEssentials.MODID)
public class HarvestEventHandler {

    public static final Map<Player, BlockPos> harvestAttempts = new HashMap<>();

    @SubscribeEvent
    public static void breakBlock(BlockEvent.BreakEvent event) {
        final LevelAccessor level = event.getWorld();
        final BlockPos pos = event.getPos();
        final BlockState state = level.getBlockState(pos);
        final Player player = event.getPlayer() != null ? event.getPlayer() : null;
        final ToolType expectedToolType = HarvestBlock.BLOCK_TOOL_TYPES.getOrDefault(state.getBlock(), ToolType.NONE);
        boolean cancel = false;
        boolean alwaysBreakable = state.is(TagManager.Blocks.ALWAYS_BREAKABLE) ||
                ItemUse.isAlwaysBreakable(state);

        if (player == null) return;

        if (ModList.get().isLoaded("carryon")) {
            final ItemStack handStack = player.getMainHandItem();
            final ItemStack offhandStack = player.getOffhandItem();

            if (handStack.isEmpty() && offhandStack.isEmpty()
                    && tschipp.carryon.client.keybinds.CarryOnKeybinds.isKeyPressed(player)) {
                alwaysBreakable = true;
            }
        }

        if (!alwaysBreakable && !player.isCreative()) {
            if (expectedToolType != ToolType.NONE) {
                final ItemStack handStack = player.getMainHandItem();
                boolean correctTool = ItemUse.isCorrectTool(state, player, handStack);
                boolean isAllowedItem = ItemUse.isAllowedTool(handStack);

                if (!isAllowedItem) {
                    cancel = true;
                    if (!player.getLevel().isClientSide && ConfigHandler.Client.enableFailSound()) {
                        level.playSound(null, player.getOnPos(), Sounds.TOOL_FAIL.get(), SoundSource.BLOCKS, 0.6F, 1.0F);
                    }
                }

                if (isAllowedItem && !correctTool) {
                    cancel = true;

                    if (harvestAttempts.containsKey(player)
                            || harvestAttempts.get(player) == null
                            || !harvestAttempts.get(player).equals(pos)) {

                        harvestAttempts.put(player, pos);

                        Chat.sendMessage(player, Chat.WRONG_TOOL, expectedToolType.toString().toLowerCase(), false);
                    }
                    else {
                        Chat.sendMessage(player, Chat.WARNING);
                        Chat.sendMessage(player, Chat.SARCASTIC_WRONG_TOOL, expectedToolType.toString().toLowerCase(), true);
                        player.hurt(DamageSource.GENERIC, 0.1f);
                    }
                }

            }
        }

        event.setCanceled(cancel);
    }

    @SubscribeEvent
    public static void harvestCheckEvent(PlayerEvent.HarvestCheck event) {
        final Player player = event.getPlayer() != null ? event.getPlayer() : null;
        final BlockState state = event.getTargetBlock();

        if (player != null && !player.isCreative()) {
            final ItemStack handStack = player.getMainHandItem();
            final boolean correctTool = ItemUse.isCorrectTool(state, player, handStack);
            final ToolType expectedToolType = HarvestBlock.BLOCK_TOOL_TYPES.getOrDefault(state.getBlock(), ToolType.NONE);
            boolean canHarvest = event.canHarvest()
                    || ItemUse.alwaysDrops(state)
                    || expectedToolType == ToolType.NONE;

            if (!canHarvest) {
                final boolean isOre = state.is(Tags.Blocks.ORES) || state.is(Tags.Blocks.OBSIDIAN);

                if (isOre && expectedToolType == ToolType.PICKAXE) {
                    canHarvest = (correctTool && handStack.isCorrectToolForDrops(state));
                }
                else {
                    canHarvest = correctTool || handStack.isCorrectToolForDrops(state);
                }
            }

            event.setCanHarvest(canHarvest);
        }
    }

    // Controls the slow mining speed of blocks that aren't the right tool
    @SubscribeEvent
    public static void slowMining(PlayerEvent.BreakSpeed event) {
        final Player player = event.getPlayer() != null ? event.getPlayer() : null;

        if (player == null) return;

        final Level level = player.getLevel();
        final BlockPos pos = event.getPos();
        final BlockState state = level.getBlockState(pos);
        final float destroySpeed = ((AbstractBlockStateAccessor) state).getDestroySpeed();
        float slowdown = destroySpeed;
        final ToolType expectedToolType = HarvestBlock.BLOCK_TOOL_TYPES.getOrDefault(state.getBlock(), ToolType.NONE);

        if (!player.isCreative() && expectedToolType != ToolType.NONE) {
            ItemStack handStack = player.getMainHandItem();
            boolean correctTool = ItemUse.isCorrectTool(state, player, handStack);
            boolean alwaysBreakable = state.is(TagManager.Blocks.ALWAYS_BREAKABLE);
            boolean isAllowedItem = ItemUse.isAllowedTool(handStack);

            if (!alwaysBreakable) {
                if (!isAllowedItem) {
                    slowdown = ConfigHandler.Common.slowDownSpeed() / 2;
                }
                else if (!correctTool) {
                    slowdown = ConfigHandler.Common.slowDownSpeed();
                }
            }
            else {
                if (!correctTool) {
                    slowdown = ConfigHandler.Common.slowDownSpeed();
                }
                else if (!isAllowedItem) {
                    slowdown = ConfigHandler.Common.slowDownSpeed() / 2;
                }
            }
        }

        if (slowdown != destroySpeed) {
            event.setNewSpeed(slowdown);
        }
    }

}
