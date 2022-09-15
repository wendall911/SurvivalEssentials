/*
 * Derived from the No Tree Punching mod by AlcatrazEscapee.
 * https://github.com/alcatrazEscapee/no-tree-punching/tree/1.18.x/src/main/java/com/alcatrazescapee/notreepunching/mixin
 * Work under copyright. See the project LICENSE.md for details.
 */

package survivalistessentials.mixin;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BlockBehaviour.class)
public interface AbstractBlockAccessor {

    /**
     * Gets the properties from the block in order to mutate it
     */
    @Accessor("properties")
    BlockBehaviour.Properties getProperties();

    /**
     * This is required as when adding tool types to block's based on material, we cannot discriminate against the individual block state as the tool type field does not allow for that. So we query the original material rather than each block state.
     */
    @Accessor("material")
    Material getMaterial();

}
