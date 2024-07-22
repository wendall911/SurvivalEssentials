package survivalistessentials.items.item;

import org.jetbrains.annotations.NotNull;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.neoforged.fml.ModList;

import survivalistessentials.data.integration.ModIntegration;

import static survivalistessentials.SurvivalistEssentials.loc;

public class SurvivalistEssentialsBook extends Item {

    ResourceLocation book;

    public SurvivalistEssentialsBook(Properties pProperties, String bookId) {
        super(pProperties);

        this.book = loc(bookId);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player playerIn, @NotNull InteractionHand handIn) {
        if (ModList.get().isLoaded(ModIntegration.PATCHOULI_MODID)) {
            if (level.isClientSide()) {
                // TODO re-enable once Patchouli is available
                //vazkii.patchouli.api.PatchouliAPI.get().openBookGUI(book);
            }
        }

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, playerIn.getItemInHand(handIn));
    }

}
