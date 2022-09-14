package survivalessentials.items;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;

import net.minecraftforge.common.Tags;

public class ItemTiers {

    public static final Tier FLINT_TIER = new ItemTier().setMaxUses(20).setEfficiency(1.5F)
        .setAttackDamage(0.5F).setRepairMat(Items.FLINT);
    public static final Tier STONE_TIER = new ItemTier().setMaxUses(5).setEfficiency(1.5F)
        .setAttackDamage(0.5F).setRepairMat(Items.COBBLESTONE);
    public static final Tier IRON_TIER = new ItemTier().setMaxUses(100).setEfficiency(1.5F)
        .setAttackDamage(0.0F).setRepairMat(Tags.Items.INGOTS_IRON);
    public static final Tier DIAMOND_TIER = new ItemTier().setMaxUses(300).setEfficiency(1.5F)
        .setAttackDamage(0.0F).setRepairMat(Items.DIAMOND);
    public static final Tier NO_TIER = new ItemTier().setMaxUses(1).setEfficiency(0.0F)
            .setAttackDamage(0.0F);

}
