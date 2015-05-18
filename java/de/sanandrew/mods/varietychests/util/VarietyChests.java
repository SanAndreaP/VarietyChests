/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.util;

import cpw.mods.fml.common.FMLCommonHandler;
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
import de.sanandrew.core.manpack.util.modcompatibility.ModInitHelperInst;
import de.sanandrew.mods.varietychests.block.BlockCustomChest;
import de.sanandrew.mods.varietychests.block.BlockCustomGlowingChest;
import de.sanandrew.mods.varietychests.block.BlockCustomTrapChest;
import de.sanandrew.mods.varietychests.crafting.RecipeAdvChestDisassemble;
import de.sanandrew.mods.varietychests.crafting.RecipeGlowingChests;
import de.sanandrew.mods.varietychests.crafting.RecipePlainChests;
import de.sanandrew.mods.varietychests.crafting.RecipeTrapChests;
import de.sanandrew.mods.varietychests.event.ItemCraftedHandler;
import de.sanandrew.mods.varietychests.event.PopulatePostHandler;
import de.sanandrew.mods.varietychests.item.ItemBlockCustomChest;
import de.sanandrew.mods.varietychests.tileentity.TileEntityCustomChest;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

@Mod(modid = VarietyChests.MOD_ID, version = VarietyChests.VERSION, name = "Variety Chests", dependencies = VarietyChests.MOD_DEPS)
public final class VarietyChests
{
    public static final String MOD_ID = "varietychests";
    public static final String MOD_DEPS = "required-after:sapmanpack@[2.4.0,);after:ExtrabiomesXL;after:NotEnoughItems";
    public static final String VERSION = "1.2.1";
    public static final String PROXY_CLIENT = "de.sanandrew.mods.varietychests.client.util.ClientProxy";
    public static final String PROXY_SERVER = "de.sanandrew.mods.varietychests.util.CommonProxy";

    public static final String NBT_CHEST_TYPE = "chestType";

    public static Block customChest;
    public static Block customGlowingChest;
    public static Block customTrapChest;

    public static CreativeTabs creativeTab = new VcCreativeTab();

    @Instance(MOD_ID)
    public static VarietyChests instance;

    @SidedProxy(clientSide = PROXY_CLIENT, serverSide = PROXY_SERVER, modId = MOD_ID)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        VcConfig.configuration = new Configuration(event.getSuggestedConfigurationFile(), "1.0");
        VcConfig.syncConfig();

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

        ChestType.registerDefaultTypes();

        MinecraftForge.EVENT_BUS.register(new PopulatePostHandler());

        if( VcConfig.disassembleRecipes ) {
            FMLCommonHandler.instance().bus().register(new ItemCraftedHandler());
        }

        if( VcConfig.neiCraftingCompat ) {
            ModInitHelperInst.loadWhenModAvailable("NotEnoughItems", "de.sanandrew.mods.varietychests.util.modcompat.nei.NeiIntegration").preInitialize();
        }

        if( VcConfig.extraBiomesXlTypes ) {
            ModInitHelperInst.loadWhenModAvailable("ExtrabiomesXL", "de.sanandrew.mods.varietychests.util.modcompat.ebxl.EbxlIntegration").preInitialize();
        }
    }

    @EventHandler
    @SuppressWarnings("unchecked")
    public void postInit(FMLPostInitializationEvent event) {
        RecipeSorter.register(VarietyChests.MOD_ID + ":recipe_cchest", RecipePlainChests.class, Category.SHAPED, "after:minecraft:shaped");
        RecipeSorter.register(VarietyChests.MOD_ID + ":recipe_glowcchest", RecipeGlowingChests.class, Category.SHAPELESS, "after:minecraft:shapeless");
        RecipeSorter.register(VarietyChests.MOD_ID + ":recipe_trapcchest", RecipeTrapChests.class, Category.SHAPELESS, "after:minecraft:shapeless");
        CraftingManager.getInstance().getRecipeList().add(new RecipePlainChests());
        CraftingManager.getInstance().getRecipeList().add(new RecipeGlowingChests());
        CraftingManager.getInstance().getRecipeList().add(new RecipeTrapChests());

        if( VcConfig.disassembleRecipes ) {
            RecipeSorter.register(VarietyChests.MOD_ID + ":recipe_cchest_disasmb", RecipeAdvChestDisassemble.class, Category.SHAPELESS, "after:minecraft:shapeless");
            CraftingManager.getInstance().getRecipeList().add(new RecipeAdvChestDisassemble());
        }
    }
}
