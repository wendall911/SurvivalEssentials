package survivalistessentials.data.client.patchouli;

import java.util.function.Consumer;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import xyz.brassgoggledcoders.patchouliprovider.BookBuilder;
import xyz.brassgoggledcoders.patchouliprovider.CategoryBuilder;
import xyz.brassgoggledcoders.patchouliprovider.PatchouliBookProvider;

import survivalistessentials.items.SurvivalistEssentialsItems;
import survivalistessentials.world.SurvivalistEssentialsWorld;
import survivalistessentials.SurvivalistEssentials;

public class ModBookProvider extends PatchouliBookProvider {

    private int categorySortNum = -1;
    private int entrySortNum = -1;

    public ModBookProvider(DataGenerator gen) {
        super(gen, SurvivalistEssentials.MODID, "en_us");
    }

    @Override
    protected void addBooks(Consumer<BookBuilder> consumer) {
        String bookName = "item.survivalistessentials.book";
        String landingText = "info.survivalistessentials.book.intro";
        String subTitle = "info.survivalistessentials.book.subtitle";

        BookBuilder bookBuilder = createBookBuilder("book", bookName, landingText)
            .setSubtitle(subTitle)
            .setCustomBookItem(new ItemStack(SurvivalistEssentialsItems.BOOK))
            .setCreativeTab(SurvivalistEssentials.MODID + ".items")
            .setModel(SurvivalistEssentials.MODID + ":book")
            .setDontGenerateBook(true)
            .setShowProgress(false)
            .setUseBlockyFont(true)
            .setI18n(true);

        bookBuilder = addGettingStarted(bookBuilder).build();
        bookBuilder = addTools(bookBuilder).build();
        bookBuilder = addHealth(bookBuilder).build();

        bookBuilder.build(consumer);
    }

    public CategoryBuilder addGettingStarted(BookBuilder bookBuilder) {
        return bookBuilder.addCategory(
            "getting_started",
            "info.survivalistessentials.book.getting_started.name",
            "info.survivalistessentials.book.getting_started.desc",
            new ItemStack(SurvivalistEssentialsWorld.ROCK_STONE)
        )
        .setSortnum(getCategorySortNum())
        .addEntry(
            "getting_started/materials",
            "info.survivalistessentials.book.getting_started.materials.name",
            new ItemStack(SurvivalistEssentialsWorld.ROCK_STONE)
        )
        .setSortnum(getEntrySortNum())
        .addSpotlightPage(new ItemStack(SurvivalistEssentialsWorld.STONE_LOOSE_ROCK))
            .setTitle("info.survivalistessentials.book.getting_started.materials.gather_stones.title")
            .setText("info.survivalistessentials.book.getting_started.materials.gather_stones.desc").build()
        .addSpotlightPage(new ItemStack(Items.OAK_LEAVES))
            .setTitle("info.survivalistessentials.book.getting_started.materials.gather_sticks.title")
            .setText("info.survivalistessentials.book.getting_started.materials.gather_sticks.desc").build()
        .addSpotlightPage(new ItemStack(SurvivalistEssentialsItems.FLINT_SHARD))
            .setTitle("info.survivalistessentials.book.getting_started.materials.flint_shards.title")
            .setText("info.survivalistessentials.book.getting_started.materials.flint_shards.desc").build()
        .addSpotlightPage(new ItemStack(SurvivalistEssentialsItems.PLANT_FIBER))
            .setTitle("info.survivalistessentials.book.getting_started.materials.plant_fiber.title")
            .setText("info.survivalistessentials.book.getting_started.materials.plant_fiber.desc").build()
        .addCraftingPage(new ResourceLocation(SurvivalistEssentials.MODID, "plant_string"))
            .setTitle("info.survivalistessentials.book.getting_started.materials.plant_string.title")
            .setText("info.survivalistessentials.book.getting_started.materials.plant_string.desc").build()
        .build();
    }

    public CategoryBuilder addTools(BookBuilder bookBuilder) {
        return bookBuilder.addCategory(
            "tools",
            "info.survivalistessentials.book.tools.name",
            "info.survivalistessentials.book.tools.desc",
            new ItemStack(SurvivalistEssentialsItems.SHARP_SAW)
        )
        .setSortnum(getCategorySortNum())
        .addEntry(
            "tools/crude_tools",
            "info.survivalistessentials.book.tools.crude_tools.name",
            new ItemStack(SurvivalistEssentialsItems.CRUDE_SAW)
        )
        .setSortnum(getEntrySortNum())
        .addCraftingPage(new ResourceLocation(SurvivalistEssentials.MODID, "crude_knife"))
            .setTitle("item.survivalistessentials.crude_knife")
            .setText("info.survivalistessentials.book.tools.crude_tools.knife.desc").build()
        .addCraftingPage(new ResourceLocation(SurvivalistEssentials.MODID, "flint_shard"))
            .setTitle("info.survivalistessentials.book.tools.crude_tools.knife_recipes.name")
            .setText("info.survivalistessentials.book.tools.crude_tools.knife_recipes.desc").build()
        .addCraftingPage(new ResourceLocation(SurvivalistEssentials.MODID, "crude_hatchet"))
            .setTitle("item.survivalistessentials.crude_hatchet")
            .setText("info.survivalistessentials.book.tools.crude_tools.hatchet.desc").build()
        .addCraftingPage(new ResourceLocation(SurvivalistEssentials.MODID, "crude_saw_blade"))
            .setTitle("item.survivalistessentials.crude_saw_blade")
            .setText("info.survivalistessentials.book.tools.crude_tools.crude_saw_blade.desc").build()
        .addCraftingPage(new ResourceLocation(SurvivalistEssentials.MODID, "saw_handle"))
            .setTitle("item.survivalistessentials.saw_handle")
            .setText("info.survivalistessentials.book.tools.crude_tools.saw_handle.desc").build()
        .addCraftingPage(new ResourceLocation(SurvivalistEssentials.MODID, "crude_saw"))
            .setTitle("item.survivalistessentials.crude_saw")
            .setText("info.survivalistessentials.book.tools.crude_tools.crude_saw.desc").build()
        .addCraftingPage(new ResourceLocation("minecraft:oak_planks"))
            .setTitle("info.survivalistessentials.book.tools.crude_tools.planks.name")
            .setText("info.survivalistessentials.book.tools.crude_tools.planks.desc").build()
        .addCraftingPage(new ResourceLocation("stick"))
            .setTitle("info.survivalistessentials.book.tools.crude_tools.sticks.name")
            .setText("info.survivalistessentials.book.tools.crude_tools.sticks.desc").build()
        .build()
        .addEntry(
            "tools/improved_tools",
            "info.survivalistessentials.book.tools.improved_tools.name",
            new ItemStack(SurvivalistEssentialsItems.SHARP_SAW_BLADE)
        )
        .setSortnum(getEntrySortNum())
        .addSpotlightPage(new ItemStack(SurvivalistEssentialsItems.SHARP_SAW))
            .setText("info.survivalistessentials.book.tools.improved_tools.intro")
            .setTitle("info.survivalistessentials.book.tools.improved_tools.subtitle").build()
        .addCraftingPage(new ResourceLocation(SurvivalistEssentials.MODID, "basic_saw"))
            .setRecipe2(new ResourceLocation(SurvivalistEssentials.MODID, "basic_saw_blade")).build()
        .addCraftingPage(new ResourceLocation(SurvivalistEssentials.MODID, "sharp_saw"))
            .setRecipe2(new ResourceLocation(SurvivalistEssentials.MODID, "sharp_saw_blade")).build()
        .addCraftingPage(new ResourceLocation(SurvivalistEssentials.MODID, "basic_knife"))
            .setRecipe2(new ResourceLocation(SurvivalistEssentials.MODID, "sharp_knife")).build()
        .build();
    }

    public CategoryBuilder addHealth(BookBuilder bookBuilder) {
        return bookBuilder.addCategory(
            "health",
            "info.survivalistessentials.book.health.name",
            "info.survivalistessentials.book.health.desc",
            new ItemStack(SurvivalistEssentialsItems.BANDAGE)
        )
        .setSortnum(getCategorySortNum())
        .addEntry(
            "health/ingredients",
            "info.survivalistessentials.book.health.ingredients.name",
            new ItemStack(SurvivalistEssentialsItems.CLOTH)
        )
        .setSortnum(getEntrySortNum())
        .addSpotlightPage(new ItemStack(SurvivalistEssentialsItems.CLOTH))
            .setText("info.survivalistessentials.book.health.ingredients.desc")
            .setTitle("info.survivalistessentials.book.health.ingredients.subtitle").build()
        .addCraftingPage(new ResourceLocation(SurvivalistEssentials.MODID, "cloth"))
            .setTitle("item.survivalistessentials.cloth")
            .setText("info.survivalistessentials.book.health.ingredients.cloth.desc").build()
        .addCraftingPage(new ResourceLocation(SurvivalistEssentials.MODID, "mortar_and_pestle"))
            .setTitle("item.survivalistessentials.mortar_and_pestle")
            .setText("info.survivalistessentials.book.health.ingredients.mortar_and_pestle.desc").build()
        .addCraftingPage(new ResourceLocation(SurvivalistEssentials.MODID, "plant_paste"))
            .setTitle("item.survivalistessentials.plant_paste")
            .setText("info.survivalistessentials.book.health.ingredients.plant_paste.desc").build()
        .addCraftingPage(new ResourceLocation(SurvivalistEssentials.MODID, "ointment"))
            .setTitle("item.survivalistessentials.ointment")
            .setText("info.survivalistessentials.book.health.ingredients.ointment.desc").build().build()
        .addEntry(
            "health/bandages",
            "info.survivalistessentials.book.health.bandages.name",
            new ItemStack(SurvivalistEssentialsItems.BANDAGE)
        )
        .setSortnum(getEntrySortNum())
        .addSpotlightPage(new ItemStack(SurvivalistEssentialsItems.BANDAGE))
            .setText("info.survivalistessentials.book.health.bandages.desc")
            .setTitle("info.survivalistessentials.book.health.bandages.subtitle").build()
        .addCraftingPage(new ResourceLocation(SurvivalistEssentials.MODID, "crude_bandage"))
            .setTitle("item.survivalistessentials.crude_bandage")
            .setText("info.survivalistessentials.book.health.bandages.crude_bandage.desc").build()
        .addCraftingPage(new ResourceLocation(SurvivalistEssentials.MODID, "bandage"))
            .setTitle("item.survivalistessentials.bandage")
            .setText("info.survivalistessentials.book.health.bandages.bandage.desc").build()
        .build();
    }

    public int getCategorySortNum() {
        return ++categorySortNum;
    }

    public int getEntrySortNum() {
        return ++entrySortNum;
    }

}
