package survivalessentials.util;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import survivalessentials.common.TagManager;

/**
 * This represents a primary tool that must be present on a given block
 * Blocks can be added to any number of tags to determine their validity with such a tool
 * However, all blocks have a fallback 'tool type' which is known to NTP
 * This is because when we check if a block is harvestable by an item, we have no way of knowing if that block is harvestable by *any* item.
 */
public enum ToolType {

    PICKAXE(TagManager.Items.PICKAXE_TOOLS),
    AXE(TagManager.Items.AXE_TOOLS),
    SHOVEL(TagManager.Items.SHOVEL_TOOLS),
    HOE(TagManager.Items.HOE_TOOLS),
    SHARP(TagManager.Items.SHARP_TOOLS),
    NONE(null);

    @Nullable private final TagKey<Item> tag;

    ToolType(@Nullable TagKey<Item> tag) {
        this.tag = tag;
    }

    public boolean is(Item item) {
        ItemStack stack = new ItemStack(item);
        AtomicBoolean hasKey = new AtomicBoolean(false);

        stack.getTags().takeWhile((TagKey<Item> n) -> {
            return !hasKey.get();
        }).filter(tagKey -> tag == tagKey).map(tagKey -> true).forEach(hasKey::set);

        return this.tag != null && hasKey.get();
    }

}
