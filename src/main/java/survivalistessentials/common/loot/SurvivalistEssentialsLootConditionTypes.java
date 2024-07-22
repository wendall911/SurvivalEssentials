package survivalistessentials.common.loot;

import java.util.function.BiConsumer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import static survivalistessentials.SurvivalistEssentials.loc;

public class SurvivalistEssentialsLootConditionTypes {

    public static final ResourceLocation BLOCK_IS_TAG_ID = loc("block_is_tag");
    public static final LootItemConditionType BLOCK_IS_TAG = new LootItemConditionType(LootItemBlockIsTagCondition.CODEC);

    public static void init(BiConsumer<LootItemConditionType, ResourceLocation> consumer) {
        consumer.accept(BLOCK_IS_TAG, BLOCK_IS_TAG_ID);
    }

}
