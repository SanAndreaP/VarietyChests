/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.util.modcompat.nei;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.ShapedRecipeHandler.CachedShapedRecipe;
import de.sanandrew.mods.varietychests.util.ChestType;
import de.sanandrew.mods.varietychests.util.VarietyChests;
import de.sanandrew.mods.varietychests.util.VcConfig;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class NeiRecipesTrapChests
{
    private static final Item TRAP_CHEST_ITEM = Item.getItemFromBlock(VarietyChests.customTrapChest);
    private static final Item CUST_CHEST_ITEM = Item.getItemFromBlock(VarietyChests.customChest);
    private static final Item CHEST_ITEM = Item.getItemFromBlock(Blocks.chest);
    private static final Item TRIPHOOK_ITEM = Item.getItemFromBlock(Blocks.tripwire_hook);

    public static void loadCraftingRecipes(VcRecipeHandler rHandler, ItemStack result) {
        if( result.getItem() == TRAP_CHEST_ITEM ) {
            ChestType baseType = ChestType.getType(result);
            ItemStack[] recipe = new ItemStack[2];

            recipe[0] = new ItemStack(Blocks.tripwire_hook);
            recipe[1] = ChestType.getNewItemStackFromType(VarietyChests.customChest, baseType, 1);

            CachedShapedRecipe csRecipe = rHandler.new CachedShapedRecipe(2, 1, recipe, result);
            csRecipe.computeVisuals();
            rHandler.arecipes.add(csRecipe);
        } else if( result.getItem() == CUST_CHEST_ITEM && VcConfig.disassembleRecipes ) {
            ChestType baseType = ChestType.getType(result);
            ItemStack[] recipe = new ItemStack[1];

            recipe[0] = ChestType.getNewItemStackFromType(VarietyChests.customTrapChest, baseType, 1);

            CachedShapedRecipe csRecipe = rHandler.new CachedShapedRecipe(1, 1, recipe, result);
            csRecipe.computeVisuals();
            rHandler.arecipes.add(csRecipe);
        } else if( result.getItem() == CHEST_ITEM && VcConfig.disassembleRecipes ) {
            ItemStack[] recipe = new ItemStack[1];

            recipe[0] = new ItemStack(Blocks.trapped_chest);

            CachedShapedRecipe csRecipe = rHandler.new CachedShapedRecipe(1, 1, recipe, result);
            csRecipe.computeVisuals();
            rHandler.arecipes.add(csRecipe);
        } else if( result.getItem() == TRIPHOOK_ITEM && VcConfig.disassembleRecipes ) {
            loadTripwireRecipe(rHandler);
        }
    }

    public static void loadAllCraftingRecipes(VcRecipeHandler rHandler) {
        for( ChestType type : ChestType.getTypes() ) {
            if( type != ChestType.ORIGINAL ) {
                loadCraftingRecipes(rHandler, ChestType.getNewItemStackFromType(VarietyChests.customTrapChest, type, 1));
            }
        }
    }

    public static void loadUsageRecipes(VcRecipeHandler rHandler, ItemStack ingredient) {
        if( ingredient.getItem() == CUST_CHEST_ITEM ) {
            loadCraftingRecipes(rHandler, ChestType.getNewItemStackFromType(VarietyChests.customTrapChest, ChestType.getType(ingredient), 1));
        } else if( ingredient.getItem() == TRIPHOOK_ITEM ) {
            loadAllCraftingRecipes(rHandler);
        } else if( ingredient.getItem() == TRAP_CHEST_ITEM && VcConfig.disassembleRecipes ) {
            loadCraftingRecipes(rHandler, ChestType.getNewItemStackFromType(VarietyChests.customChest, ChestType.getType(ingredient), 1));
            loadTripwireRecipe(rHandler);
        } else if( ingredient.getItem() == Item.getItemFromBlock(Blocks.trapped_chest) && VcConfig.disassembleRecipes ) {
            loadCraftingRecipes(rHandler, new ItemStack(Blocks.chest));
            loadTripwireRecipe(rHandler);
        }
    }

    private static void loadTripwireRecipe(VcRecipeHandler rHandler) {
        ItemStack[] recipe = new ItemStack[1];
        List<ItemStack> mutations = new ArrayList<>();

        recipe[0] = new ItemStack(Blocks.chest);

        ItemStack newResult = new ItemStack(Blocks.tripwire_hook);
        CachedShapedRecipe csRecipe = rHandler.new CachedShapedRecipe(1, 1, recipe, newResult);

        for( ChestType type : ChestType.getTypes() ) {
            if( type.crfItem != null && type != ChestType.NULL_TYPE ) {
                mutations.add(type == ChestType.ORIGINAL ? new ItemStack(Blocks.trapped_chest)
                                                         : ChestType.getNewItemStackFromType(VarietyChests.customTrapChest, type, 1));
            }
        }

        PositionedStack origPosStack = csRecipe.ingredients.get(0);
        csRecipe.ingredients.set(0, new PositionedStack(mutations, origPosStack.relx, origPosStack.rely));

        csRecipe.computeVisuals();
        rHandler.arecipes.add(csRecipe);
    }
}
