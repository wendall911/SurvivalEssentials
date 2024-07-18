package survivalistessentials.event;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import survivalistessentials.common.HarvestBlock;
import survivalistessentials.common.TagManager;
import survivalistessentials.config.ConfigHandler;
import survivalistessentials.data.integration.ModIntegration;
import survivalistessentials.mixin.AbstractBlockStateAccessor;
import survivalistessentials.platform.Services;
import survivalistessentials.sound.Sounds;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.util.CarryOnHelper;
import survivalistessentials.util.Chat;
import survivalistessentials.util.ItemUse;
import survivalistessentials.util.ToolType;

public class HarvestEventHandler {

    private static final Map<Player, BlockPos> harvestAttempts = new HashMap<>();
    private static Block spellHitBlock = null;
    private static int breakBlockStep = 0;

    public static void tagUpdate() {
        HarvestBlock.setup();
    }

    public static void breakBlock(LevelAccessor level, BlockPos pos, Player player, Function<Boolean> callback) {
        final BlockState state = level.getBlockState(pos);
        final ToolType expectedToolType = HarvestBlock.BLOCK_TOOL_TYPES.getOrDefault(state.getBlock(), ToolType.NONE);
        boolean cancel = false;
        boolean alwaysBreakable = state.is(TagManager.Blocks.ALWAYS_BREAKABLE) ||
                ItemUse.isAlwaysBreakable(state);

        if (Services.PLATFORM.isModLoaded(ModIntegration.CARRYON_MODID)) {
            final ItemStack handStack = player.getMainHandItem();
            final ItemStack offhandStack = player.getOffhandItem();

            if (handStack.isEmpty() && offhandStack.isEmpty()) {
                if (CarryOnHelper.isKeyPressed(player)) {
                    alwaysBreakable = true;
                }
            }
        }

        if (!alwaysBreakable && !player.isCreative()) {
            if (expectedToolType != ToolType.NONE) {
                final ItemStack handStack = getHandStack(player, state);
                boolean correctTool = ItemUse.isCorrectTool(state, player, handStack);
                boolean isAllowedTool = ItemUse.isAllowedTool(handStack);
                String toolClass = ItemUse.getToolClass(handStack);

                if (toolClass.equals("unknown") || (isAllowedTool && !correctTool)) {
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
                        player.hurt(player.damageSources().generic(), 0.1f);
                    }

                    if (!toolClass.equals("unknown") && !player.level().isClientSide && ConfigHandler.Client.enableFailSound()) {
                        level.playSound(null, player.getOnPos(), Sounds.TOOL_FAIL.get(), SoundSource.PLAYERS, 0.6F, 1.0F);
                    }
                }
                else {
                    // Reset spell hit
                    if (spellHitBlock != null) {
                        if (breakBlockStep == 1) {
                            breakBlockStep = -1;
                            spellHitBlock = null;
                        }
                        breakBlockStep++;
                    }
                }

            }
        }

        callback.apply(cancel);
    }

    public static void onProjectileImpact(Entity projectile) {
        if (projectile.toString().toLowerCase().contains("spell")) {
            Vec3 position = projectile.position();
            Vec3 nextPosition = position.add(projectile.getDeltaMovement());

            BlockHitResult hitresult = projectile.level().clip(new ClipContext(position, nextPosition, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, projectile));
            BlockPos pos = hitresult.getBlockPos();

            spellHitBlock = projectile.level().getBlockState(new BlockPos(pos)).getBlock();
        }
    }

    public static void harvestCheckEvent(Player player, BlockState state, boolean harvest, Function<Boolean> setCanHarvest) {
        if (!player.isCreative()) {
            final ItemStack handStack = getHandStack(player, state);
            final boolean correctTool = ItemUse.isCorrectTool(state, player, handStack);
            final ToolType expectedToolType = HarvestBlock.BLOCK_TOOL_TYPES.getOrDefault(state.getBlock(), ToolType.NONE);
            boolean canHarvest = harvest
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

            setCanHarvest.apply(canHarvest);
        }
    }

    // Controls the slow mining speed of blocks that aren't the right tool
    public static void slowMining(Player player, Optional<BlockPos> pos, Function<Float> setSlowdown) {
        if (pos.isEmpty()) return;

        final Level level = player.level();
        final BlockState state = level.getBlockState(pos.get());
        final float destroySpeed = ((AbstractBlockStateAccessor) state).getDestroySpeed();
        float slowdown = destroySpeed;
        final ToolType expectedToolType = HarvestBlock.BLOCK_TOOL_TYPES.getOrDefault(state.getBlock(), ToolType.NONE);

        if (!player.isCreative() && expectedToolType != ToolType.NONE) {
            ItemStack handStack = getHandStack(player, state);
            boolean correctTool = ItemUse.isCorrectTool(state, player, handStack);
            boolean alwaysBreakable = state.is(TagManager.Blocks.ALWAYS_BREAKABLE);
            boolean isAllowedTool = ItemUse.isAllowedTool(handStack);

            if (!alwaysBreakable) {
                if (!isAllowedTool) {
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
                else if (!isAllowedTool) {
                    slowdown = ConfigHandler.Common.slowDownSpeed() / 2;
                }
            }
        }

        if (slowdown != destroySpeed) {
            setSlowdown.apply(slowdown);
        }
    }

    private static ItemStack getHandStack(Player player, BlockState blockState) {
        ItemStack stack = player.getMainHandItem();

        if (spellHitBlock != null && spellHitBlock.equals(blockState.getBlock())) {
            String toolClass = ItemUse.getToolClass(stack);
            if (toolClass != "spell" && toolClass != "cad") {
                stack = player.getOffhandItem();
            }
        }

        return stack;
    }

}
