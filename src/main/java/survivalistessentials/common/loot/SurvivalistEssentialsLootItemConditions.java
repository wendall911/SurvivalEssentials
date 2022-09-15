package survivalistessentials.common.loot;

import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import survivalistessentials.SurvivalistEssentials;

public class SurvivalistEssentialsLootItemConditions {

    private static final DeferredRegister<LootItemConditionType> LOOT_ITEM_CONDITION_TYPE_DEFERRED_REGISTER =
            DeferredRegister.create(Registry.LOOT_CONDITION_TYPE.key(), SurvivalistEssentials.MODID);

    public static final RegistryObject<LootItemConditionType> IS_TAG = LOOT_ITEM_CONDITION_TYPE_DEFERRED_REGISTER.register("is_tag",
            () -> new LootItemConditionType(new LootItemBlockIsTagCondition.Serializer()));

    public static void init(IEventBus bus) {
        LOOT_ITEM_CONDITION_TYPE_DEFERRED_REGISTER.register(bus);
    }

}
