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

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

import survivalistessentials.common.HarvestBlock;
import survivalistessentials.common.TagManager;
import survivalistessentials.config.ConfigHandler;
import survivalistessentials.data.integration.ModIntegration;
import survivalistessentials.mixin.AbstractBlockStateAccessor;
import survivalistessentials.sound.Sounds;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.util.CarryOnHelper;
import survivalistessentials.util.Chat;
import survivalistessentials.util.ItemUse;
import survivalistessentials.util.ToolType;

@Mod.EventBusSubscriber(modid = SurvivalistEssentials.MODID)
public class HarvestEventHandler {

    private static final Map<Player, BlockPos> harvestAttempts = new HashMap<>();
    private static Block spellHitBlock = null;
    private static int breakBlockStep = 0;

    @SubscribeEvent
    public static void tagUpdate(TagsUpdatedEvent event) {
        HarvestBlock.setup();
    }

    @SubscribeEvent
    public static void breakBlock(BlockEvent.BreakEvent event) {
        final LevelAccessor level = event.getLevel();
        final BlockPos pos = event.getPos();
        final BlockState state = level.getBlockState(pos);
        final Player player = event.getPlayer();
        final ToolType expectedToolType = HarvestBlock.BLOCK_TOOL_TYPES.getOrDefault(state.getBlock(), ToolType.NONE);
        boolean cancel = false;
        boolean alwaysBreakable = state.is(TagManager.Blocks.ALWAYS_BREAKABLE) ||
                ItemUse.isAlwaysBreakable(state);

        if (ModList.get().isLoaded(ModIntegration.CARRYON_MODID)) {
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

        event.setCanceled(cancel);
    }

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        Entity projectile = event.getEntity();

        if (projectile.toString().toLowerCase().contains("spell")) {
            Vec3 position = projectile.position();
            Vec3 nextPosition = position.add(projectile.getDeltaMovement());

            BlockHitResult hitresult = projectile.level().clip(new ClipContext(position, nextPosition, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, projectile));
            BlockPos pos = hitresult.getBlockPos();

            spellHitBlock = projectile.level().getBlockState(new BlockPos(pos)).getBlock();
        }
    }

    @SubscribeEvent
    public static void harvestCheckEvent(PlayerEvent.HarvestCheck event) {
        final Player player = event.getEntity();
        final BlockState state = event.getTargetBlock();

        if (!player.isCreative()) {
            final ItemStack handStack = getHandStack(player, state);
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
        final Player player = event.getEntity();
        final Optional<BlockPos> pos = event.getPosition();

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
            event.setNewSpeed(slowdown);
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
