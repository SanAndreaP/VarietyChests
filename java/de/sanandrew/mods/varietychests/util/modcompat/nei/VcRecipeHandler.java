/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.util.modcompat.nei;


import codechicken.nei.recipe.ShapedRecipeHandler;
import de.sanandrew.core.manpack.util.helpers.SAPUtils;
import de.sanandrew.mods.varietychests.util.VarietyChests;
import net.minecraft.item.ItemStack;

public class VcRecipeHandler
        extends ShapedRecipeHandler
{
    public static final VcRecipeHandler INSTANCE = new VcRecipeHandler();

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        NeiRecipesPlainChests.loadCraftingRecipes(this, result);
        NeiRecipesGlowChests.loadCraftingRecipes(this, result);
        NeiRecipesTrapChests.loadCraftingRecipes(this, result);
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if( outputId.equals("crafting") && getClass() == VcRecipeHandler.class ) {
            NeiRecipesPlainChests.loadAllCraftingRecipes(this);
            NeiRecipesGlowChests.loadAllCraftingRecipes(this);
            NeiRecipesTrapChests.loadAllCraftingRecipes(this);
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        NeiRecipesPlainChests.loadUsageRecipes(this, ingredient);
        NeiRecipesGlowChests.loadUsageRecipes(this, ingredient);
        NeiRecipesTrapChests.loadUsageRecipes(this, ingredient);
    }

    @Override
    public void loadUsageRecipes(String inputId, Object... ingredients) {
        if( inputId.equals("item") && getClass() == VcRecipeHandler.class ) {
            NeiRecipesPlainChests.loadUsageRecipes(this, (ItemStack) ingredients[0]);
            NeiRecipesGlowChests.loadUsageRecipes(this, (ItemStack) ingredients[0]);
            NeiRecipesTrapChests.loadUsageRecipes(this, (ItemStack) ingredients[0]);
        } else {
            super.loadUsageRecipes(inputId, ingredients);
        }
    }

    @Override
    public String getRecipeName() {
        return SAPUtils.translate(VarietyChests.MOD_ID + ":nei.recipes");
    }
}
