package survivalistessentials.common.loot;

import org.jetbrains.annotations.NotNull;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public record LootItemBlockIsTagCondition(TagKey<Block> tag) implements LootItemCondition {

    public static final LootItemConditionType LOOT_ITEM_BLOCK_IS_TAG = new LootItemConditionType(LootItemBlockIsTagCondition.CODEC);
    public static final MapCodec<LootItemBlockIsTagCondition> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
        TagKey.codec(Registries.BLOCK).fieldOf("tag").forGetter(LootItemBlockIsTagCondition::tag)
    ).apply(builder, LootItemBlockIsTagCondition::new));

    public static LootItemBlockIsTagCondition isTag(TagKey<Block> tag) {
        return new LootItemBlockIsTagCondition(tag);
    }

    @Override
    public @NotNull LootItemConditionType getType() {
        return SurvivalistEssentialsLootConditionTypes.BLOCK_IS_TAG;
    }

    @Override
    public boolean test(LootContext lootContext) {
        BlockState state = lootContext.getParamOrNull(LootContextParams.BLOCK_STATE);
        return state != null && state.is(this.tag);
    }

}
