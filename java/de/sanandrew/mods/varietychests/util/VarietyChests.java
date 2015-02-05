/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.util;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import de.sanandrew.core.manpack.managers.SAPUpdateManager;
import de.sanandrew.core.manpack.managers.SAPUpdateManager.Version;
import de.sanandrew.core.manpack.util.helpers.SAPUtils;
import de.sanandrew.mods.varietychests.block.BlockCustomChest;
import de.sanandrew.mods.varietychests.block.BlockCustomGlowingChest;
import de.sanandrew.mods.varietychests.block.BlockCustomTrapChest;
import de.sanandrew.mods.varietychests.crafting.RecipeGlowingChests;
import de.sanandrew.mods.varietychests.crafting.RecipeNormalChests;
import de.sanandrew.mods.varietychests.crafting.RecipeTrapChests;
import de.sanandrew.mods.varietychests.event.PopulatePostHandler;
import de.sanandrew.mods.varietychests.item.ItemBlockCustomChest;
import de.sanandrew.mods.varietychests.tileentity.TileEntityCustomChest;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

@Mod(modid = VarietyChests.MOD_ID, version = VarietyChests.VERSION, name = "Variety Chests", dependencies = "required-after:sapmanpack@[2.4.0,)")
public final class VarietyChests
{
    public static final String MOD_ID = "varietychests";
    public static final String VERSION = "1.2.0";
    public static final String PROXY_CLIENT = "de.sanandrew.mods.varietychests.client.util.ClientProxy";
    public static final String PROXY_SERVER = "de.sanandrew.mods.varietychests.util.CommonProxy";

    public static final String NBT_CHEST_TYPE = "chestType";

    public static Block customChest;
    public static Block customGlowingChest;
    public static Block customTrapChest;

    public static CreativeTabs creativeTab = new CreativeTabVarietyChests();

    @Instance(MOD_ID)
    public static VarietyChests instance;

    @SidedProxy(clientSide = PROXY_CLIENT, serverSide = PROXY_SERVER, modId = MOD_ID)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        SAPUpdateManager.createUpdateManager("Variety Chests", new Version(VERSION), "https://raw.githubusercontent.com/SanAndreasP/VarietyChests/master/update.json",
                                             "http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/2227062", event.getSourceFile());

        customChest = new BlockCustomChest();
        customChest.setBlockName(MOD_ID + ":customChest");

        customGlowingChest = new BlockCustomGlowingChest();
        customGlowingChest.setBlockName(MOD_ID + ":customGlowingChest");

        customTrapChest = new BlockCustomTrapChest();
        customTrapChest.setBlockName(MOD_ID + ":customTrapChest");

        SAPUtils.registerBlockWithItem(customChest, ItemBlockCustomChest.class);
        SAPUtils.registerBlockWithItem(customGlowingChest, ItemBlockCustomChest.class);
        SAPUtils.registerBlockWithItem(customTrapChest, ItemBlockCustomChest.class);

        GameRegistry.registerTileEntity(TileEntityCustomChest.class, MOD_ID + ":customChestTE");

        proxy.preInit();

        OreDictionary.registerOre("chestWood", customChest);
        OreDictionary.registerOre("chestWood", customGlowingChest);
        OreDictionary.registerOre("chestWood", customTrapChest);

        registerChestTypes();

        MinecraftForge.EVENT_BUS.register(new PopulatePostHandler());
    }

    @EventHandler
    @SuppressWarnings("unchecked")
    public void postInit(FMLPostInitializationEvent event) {
        RecipeSorter.register(VarietyChests.MOD_ID + ":recipe_cchest", RecipeNormalChests.class, Category.SHAPED, "after:minecraft:shaped");
        RecipeSorter.register(VarietyChests.MOD_ID + ":recipe_glowcchest", RecipeGlowingChests.class, Category.SHAPELESS, "after:minecraft:shapeless");
        RecipeSorter.register(VarietyChests.MOD_ID + ":recipe_trapcchest", RecipeTrapChests.class, Category.SHAPELESS, "after:minecraft:shapeless");

        CraftingManager.getInstance().getRecipeList().add(new RecipeNormalChests());
        CraftingManager.getInstance().getRecipeList().add(new RecipeGlowingChests());
        CraftingManager.getInstance().getRecipeList().add(new RecipeTrapChests());
    }

    private static void registerChestTypes() {
        ChestType.registerChestType("spruce", new ResourceLocation(MOD_ID, "textures/entity/chest/spruce.png"),
                                    new ResourceLocation(MOD_ID, "textures/entity/chest/spruce_double.png"),
                                    new ItemStack(Blocks.planks, 1, 1)
        );
        ChestType.registerChestType("birch", new ResourceLocation(MOD_ID, "textures/entity/chest/birch.png"),
                                    new ResourceLocation(MOD_ID, "textures/entity/chest/birch_double.png"),
                                    new ItemStack(Blocks.planks, 1, 2)
        );
        ChestType.registerChestType("jungle", new ResourceLocation(MOD_ID, "textures/entity/chest/jungle.png"),
                                    new ResourceLocation(MOD_ID, "textures/entity/chest/jungle_double.png"),
                                    new ItemStack(Blocks.planks, 1, 3)
        );
        ChestType.registerChestType("acacia", new ResourceLocation(MOD_ID, "textures/entity/chest/acacia.png"),
                                    new ResourceLocation(MOD_ID, "textures/entity/chest/acacia_double.png"),
                                    new ItemStack(Blocks.planks, 1, 4)
        );
        ChestType.registerChestType("darkoak", new ResourceLocation(MOD_ID, "textures/entity/chest/darkoak.png"),
                                    new ResourceLocation(MOD_ID, "textures/entity/chest/darkoak_double.png"),
                                    new ItemStack(Blocks.planks, 1, 5)
        );
        ChestType.registerChestType("original", new ResourceLocation("minecraft", "textures/entity/chest/normal.png"),
                                    new ResourceLocation("minecraft", "textures/entity/chest/normal_double.png"),
                                    new ItemStack(Blocks.planks, 1, 0)
        );
    }
}
