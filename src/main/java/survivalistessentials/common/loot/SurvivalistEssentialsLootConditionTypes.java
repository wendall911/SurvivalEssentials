package survivalistessentials.common.loot;

import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import net.neoforged.neoforge.registries.DeferredHolder;

import survivalistessentials.common.SurvivalistEssentialsModule;

public class SurvivalistEssentialsLootConditionTypes extends SurvivalistEssentialsModule {

    public static final DeferredHolder<LootItemConditionType, LootItemConditionType> BLOCK_IS_TAG = LOOT_ITEM_CONDITION_TYPES.register("block_is_tag", () -> new LootItemConditionType(LootItemBlockIsTagCondition.CODEC));

    public static void init() {}

}
