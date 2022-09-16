package survivalistessentials.loot;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Suppliers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

import net.minecraftforge.registries.RegistryObject;
import survivalistessentials.common.SurvivalistEssentialsModule;

public class SurvivalistEssentialsLootTables extends SurvivalistEssentialsModule {

    public static RegistryObject<Codec<LootTableModifier>> ADD_LOOT = LOOT_MODIFIER_REGISTRY.register("add_loot", LootTableModifier.CODEC_SUPPLIER);

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
        @Nonnull
        protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
            generatedLoot.add(stack.copy());

            return generatedLoot;
        }

        @Override
        public Codec<? extends IGlobalLootModifier> codec() {
            return CODEC_SUPPLIER.get();
        }

    }

}
