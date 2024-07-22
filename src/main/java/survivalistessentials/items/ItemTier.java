package survivalistessentials.items;

import org.jetbrains.annotations.NotNull;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public class ItemTier implements Tier {
    private int uses = 0;
    private float speed = 0.0F;
    private float attackDamageBonus = 0.0F;
    private int enchantability = 0;
    private Item repairIngredientItem = Items.BARRIER;
    private TagKey<Item> repairIngredientTag;
    private TagKey<Block> incorrectBlocksForDrops = BlockTags.INCORRECT_FOR_WOODEN_TOOL;

    public ItemTier() {}

    public ItemTier setMaxUses(int maxUses) {
        this.uses = maxUses;

        return this;
    }

    public ItemTier setEfficiency(float eff) {
        this.speed = eff;

        return this;
    }

    public ItemTier setAttackDamage(float dmg) {
        this.attackDamageBonus = dmg;

        return this;
    }

    public ItemTier setEnchantability(int ench) {
        this.enchantability = ench;

        return this;
    }

    public ItemTier setRepairMat(TagKey<Item> tag) {
        this.repairIngredientTag = tag;

        return this;
    }

    public ItemTier setRepairMat(Item item) {
        this.repairIngredientItem = item;

        return this;
    }

    public void setIncorrectBlocksForDrops(TagKey<Block> incorrectBlocksForDrops) {
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamageBonus;
    }

    @Override
    public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
        return this.incorrectBlocksForDrops;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    @NotNull
    public Ingredient getRepairIngredient() {
        if (this.repairIngredientTag != null) {
            return Ingredient.of(this.repairIngredientTag);
        }
        else {
            return Ingredient.of(this.repairIngredientItem);
        }
    }

}
