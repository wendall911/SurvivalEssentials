package survivalistessentials.loot;

import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

import com.google.common.base.Suppliers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;

import survivalistessentials.common.SurvivalistEssentialsModule;

public class SurvivalistEssentialsLootTables extends SurvivalistEssentialsModule {

    public static DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<LootTableModifier>> ADD_LOOT = LOOT_MODIFIER_REGISTRY.register("add_loot", LootTableModifier.CODEC_SUPPLIER);

    public static void init() {}

    public static class LootTableModifier extends LootModifier {

        public static final Supplier<Codec<LootTableModifier>> CODEC_SUPPLIER = Suppliers.memoize(() -> RecordCodecBuilder.create(inst ->
                codecStart(inst)
                        .and(ItemStack.CODEC.fieldOf("additional").forGetter(LootTableModifier::getStack))
                        .apply(inst, LootTableModifier::new)));

        private final ItemStack stack;

        public LootTableModifier(LootItemCondition[] conditionsIn, ItemStack itemStack) {
            super(conditionsIn);

            this.stack = itemStack;
        }

        public LootItemCondition[] getConditions() {
            return this.conditions;
        }

        public ItemStack getStack() {
            return stack;
        }

        @Override
        @NotNull
        protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, @NotNull LootContext context) {
            generatedLoot.add(stack.copy());

            return generatedLoot;
        }

        @Override
        public @NotNull Codec<? extends IGlobalLootModifier> codec() {
            return ADD_LOOT.get();
        }

    }

}
