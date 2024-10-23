package survivalistessentials.data.loot;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import net.minecraft.core.HolderGetter;
import org.jetbrains.annotations.NotNull;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemEnchantmentsPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.ItemSubPredicates;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;

import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import survivalistessentials.common.loot.LootItemBlockIsTagCondition;
import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.loot.SurvivalistEssentialsLootTables;
import survivalistessentials.SurvivalistEssentials;
import survivalistessentials.common.TagManager;

public class GlobalLootModifier extends GlobalLootModifierProvider {

    private static HolderGetter<Item> itemHolderGetter;
    private static HolderGetter<EntityType<?>> entityTypeHolderGetter;

    public GlobalLootModifier(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) throws ExecutionException, InterruptedException {
        super(packOutput, lookupProvider, SurvivalistEssentials.MODID);
    }

    @Override
    public @NotNull String getName() {
        return "SurvivalistEssentials - Global Loot Modifier";
    }

    @Override
    protected void start() {
        itemHolderGetter = registries.lookupOrThrow(Registries.ITEM);
        entityTypeHolderGetter = registries.lookupOrThrow(Registries.ENTITY_TYPE);

        addPlantFiberDrops(TagManager.Blocks.FIBER_PLANTS, "fiber_plants");
        addStickDrops(BlockTags.LEAVES, "leaves");

        addToolLoot(
            BuiltInLootTables.VILLAGE_TOOLSMITH.location(),
            "village_toolsmith_crude_knife",
            SurvivalistEssentialsItems.CRUDE_KNIFE
        );
        addToolLoot(
            BuiltInLootTables.VILLAGE_TOOLSMITH.location(),
            "village_toolsmith_crude_hatchet",
            SurvivalistEssentialsItems.CRUDE_HATCHET
        );
        addToolLoot(
            BuiltInLootTables.VILLAGE_TOOLSMITH.location(),
            "village_toolsmith_crude_saw",
            SurvivalistEssentialsItems.CRUDE_SAW
        );
        addRareLoot(
            BuiltInLootTables.VILLAGE_TOOLSMITH.location(),
            "village_fisher_wooden_cup",
            SurvivalistEssentialsItems.WOODEN_CUP
        );
        addRareLoot(
            BuiltInLootTables.BURIED_TREASURE.location(),
            "buried_treasure_wooden_cup",
            SurvivalistEssentialsItems.WOODEN_CUP
        );
        addRareLoot(
            BuiltInLootTables.SHIPWRECK_TREASURE.location(),
            "shipwreck_treasure_wooden_cup",
            SurvivalistEssentialsItems.WOODEN_CUP
        );
        addRareLoot(
            BuiltInLootTables.FISHING_TREASURE.location(),
            "fishing_treasure_wooden_cup",
            SurvivalistEssentialsItems.WOODEN_CUP
        );

    }

    public void addPlantFiberDrops(TagKey<Block> tag, String name) {
        this.add(
            "plant_fiber_from_" + name,
            new SurvivalistEssentialsLootTables.LootTableModifier(
                createKnifeChanceCondition(0.16F, tag),
                new ItemStack(SurvivalistEssentialsItems.PLANT_FIBER)
            )
        );
    }

    public void addStickDrops(TagKey<Block> tag, String name) {
        this.add(
            "stick_drops_from_" + name,
            new SurvivalistEssentialsLootTables.LootTableModifier(
                createKnifeChanceCondition(0.16F, tag),
                new ItemStack(Items.STICK)
            )
        );

        this.add(
            "extra_stick_drops_from_" + name,
            new SurvivalistEssentialsLootTables.LootTableModifier(
                createExtraStickDropConditions(0.16F, tag),
                new ItemStack(Items.STICK)
            )
        );
    }

    public void addToolLoot(ResourceLocation loc, String name, Item item) {
        this.add(
            "tool_loot_" + name,
            new SurvivalistEssentialsLootTables.LootTableModifier(
                createResourceChanceCondition(0.05F, loc),
                new ItemStack(item)
            )
        );
    }

    public void addRareLoot(ResourceLocation loc, String name, Item item) {
        this.add(
            "rare_loot_" + name,
            new SurvivalistEssentialsLootTables.LootTableModifier(
                createResourceChanceCondition(0.01F, loc),
                new ItemStack(item)
            )
        );
    }

    public static LootItemCondition[] createKnifeChanceCondition(float chance, TagKey<Block> tag) {
        return new LootItemCondition[] {
            LootItemRandomChanceCondition.randomChance(chance).build(),
            MatchTool.toolMatches(ItemPredicate.Builder.item().of(itemHolderGetter, TagManager.Items.KNIFE_TOOLS)).build(),
            LootItemBlockIsTagCondition.isTag(tag)
        };
    }

    private LootItemCondition.Builder hasSilkTouch() {
        HolderLookup.RegistryLookup<Enchantment> enchantmentRegistryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        return MatchTool.toolMatches(
            ItemPredicate.Builder.item()
                .withSubPredicate(
                    ItemSubPredicates.ENCHANTMENTS,
                    ItemEnchantmentsPredicate.enchantments(
                        List.of(
                            new EnchantmentPredicate(
                                enchantmentRegistryLookup.getOrThrow(Enchantments.SILK_TOUCH),
                                MinMaxBounds.Ints.atLeast(1)
                            )
                        )
                    )
                )
        );
    }

    /**
     * Returns a list of Conditions where a player must have broken the block without silk touch and/or shears like items, with the specified chance
     * Provided by Insane96 <delvillano.alberto@gmail.com>
     */
    public LootItemCondition[] createExtraStickDropConditions(float chance, TagKey<Block> tag) {
        return new LootItemCondition[] {
            LootItemRandomChanceCondition.randomChance(chance).build(),
            LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(entityTypeHolderGetter, EntityType.PLAYER)).build(),
            LootItemBlockIsTagCondition.isTag(tag),
            hasSilkTouch().invert().build(),
            MatchTool.toolMatches(ItemPredicate.Builder.item().of(itemHolderGetter, TagManager.Items.SHEAR_TOOLS)).invert().build()
        };
    }

    public static LootItemCondition[] createResourceChanceCondition(float chance, ResourceLocation loc) {
        return new LootItemCondition[] {
            LootItemRandomChanceCondition.randomChance(chance).build(),
            LootTableIdCondition.builder(loc).build()
        };
    }

}
