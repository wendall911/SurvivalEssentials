/*
 * Derived from the No Tree Punching mod by AlcatrazEscapee.
 * https://github.com/alcatrazEscapee/no-tree-punching/tree/1.18.x/src/main/java/com/alcatrazescapee/notreepunching/mixin
 * Work under copyright. See the project LICENSE.md for details.
 */

package survivalistessentials.mixin;

import net.minecraft.world.level.block.state.BlockBehaviour;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BlockBehaviour.class)
public interface AbstractBlockAccessor {

    /**
     * Gets the properties from the block in order to mutate it
     */
    @Accessor("properties")
    BlockBehaviour.Properties getProperties();

}
