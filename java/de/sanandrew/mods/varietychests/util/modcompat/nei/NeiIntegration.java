/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.util.modcompat.nei;

import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;
import de.sanandrew.core.manpack.util.modcompatibility.IModInitHelper;

public class NeiIntegration
    implements IModInitHelper
{
    @Override
    public void preInitialize() {
        GuiCraftingRecipe.registerRecipeHandler(VcRecipeHandler.INSTANCE);
        GuiUsageRecipe.registerUsageHandler(VcRecipeHandler.INSTANCE);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void postInitialize() {

    }
}
