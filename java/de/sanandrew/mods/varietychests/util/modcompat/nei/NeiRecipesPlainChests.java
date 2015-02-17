/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.util.modcompat.nei;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.ShapedRecipeHandler.CachedShapedRecipe;
import de.sanandrew.core.manpack.util.helpers.SAPUtils;
import de.sanandrew.mods.varietychests.util.ChestType;
import de.sanandrew.mods.varietychests.util.VarietyChests;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class NeiRecipesPlainChests
{
    public static void loadCraftingRecipes(VcRecipeHandler rHandler, ItemStack result) {
        if( result.getItem() == Item.getItemFromBlock(VarietyChests.customChest) ) {
            ChestType baseType = ChestType.getType(result);
            ItemStack[] recipe = new ItemStack[9];
            List<ItemStack> mutations = new ArrayList<>();

            recipe[4] = new ItemStack(Blocks.chest);
            for( int i = 0; i < 9; i++ ) {
                if( i != 4 ) {
                    recipe[i] = baseType.crfItem.copy();
                }
            }

            ItemStack newResult = result.copy();
            newResult.stackSize = 2;
            CachedShapedRecipe csRecipe = rHandler.new CachedShapedRecipe(3, 3, recipe, newResult);

            mutations.add(new ItemStack(Blocks.chest));

            for( ChestType type : ChestType.getTypes() ) {
                if( type.crfItem != null && type != ChestType.NULL_TYPE && !type.name.equals("original") ) {
                    mutations.add(ChestType.getNewItemStackFromType(VarietyChests.customChest, type, 1));
                }
            }

            PositionedStack origPosStack = csRecipe.ingredients.get(4);
            csRecipe.ingredients.set(4, new PositionedStack(mutations, origPosStack.relx, origPosStack.rely));

            csRecipe.computeVisuals();
            rHandler.arecipes.add(csRecipe);
        }
    }

    public static void loadAllCraftingRecipes(VcRecipeHandler rHandler) {
        for( ChestType type : ChestType.getTypes() ) {
            loadCraftingRecipes(rHandler, ChestType.getNewItemStackFromType(VarietyChests.customChest, type, 1));
        }
    }

    public static void loadUsageRecipes(VcRecipeHandler rHandler, ItemStack ingredient) {
        if( ingredient.getItem() == Item.getItemFromBlock(VarietyChests.customChest) ) {
            ChestType baseType = ChestType.getType(ingredient);
            ItemStack[] recipe = new ItemStack[9];

            recipe[4] = ChestType.getNewItemStackFromType(VarietyChests.customChest, baseType, 1);

            for( ChestType type : ChestType.getTypes() ) {
                if( type.crfItem != null && type != ChestType.NULL_TYPE ) {
                    for( int i = 0; i < 9; i++ ) {
                        if( i != 4 ) {
                            recipe[i] = type.crfItem.copy();
                        }
                    }

                    CachedShapedRecipe csRecipe;
                    if( type.name.equals("original") ) {
                        csRecipe = rHandler.new CachedShapedRecipe(3, 3, recipe.clone(), new ItemStack(Blocks.chest, 2));
                    } else {
                        csRecipe = rHandler.new CachedShapedRecipe(3, 3, recipe.clone(), ChestType.getNewItemStackFromType(VarietyChests.customChest, type, 2));
                    }
                    csRecipe.computeVisuals();
                    rHandler.arecipes.add(csRecipe);
                }
            }
        } else if( ingredient.getItem() == Item.getItemFromBlock(Blocks.chest) ) {
            ItemStack[] recipe = new ItemStack[9];

            recipe[4] = new ItemStack(Blocks.chest);

            for( ChestType type : ChestType.getTypes() ) {
                if( type.crfItem != null && type != ChestType.NULL_TYPE && !type.name.equals("original") ) {
                    for( int i = 0; i < 9; i++ ) {
                        if( i != 4 ) {
                            recipe[i] = type.crfItem.copy();
                        }
                    }

                    CachedShapedRecipe csRecipe = rHandler.new CachedShapedRecipe(3, 3, recipe.clone(), ChestType.getNewItemStackFromType(VarietyChests.customChest,
                                                                                                                                          type, 2));
                    csRecipe.computeVisuals();
                    rHandler.arecipes.add(csRecipe);
                }
            }
        } else if( ingredient.getItem() == Item.getItemFromBlock(Blocks.planks) ) {
            for( ChestType type : ChestType.getTypes() ) {
                if( SAPUtils.areStacksEqual(type.crfItem, ingredient, false) ) {
                    ItemStack[] recipe = new ItemStack[9];
                    List<ItemStack> mutations = new ArrayList<>();

                    recipe[4] = new ItemStack(Blocks.chest);
                    for( int i = 0; i < 9; i++ ) {
                        if( i != 4 ) {
                            recipe[i] = type.crfItem.copy();
                        }
                    }

                    CachedShapedRecipe csRecipe = rHandler.new CachedShapedRecipe(3, 3, recipe, ChestType.getNewItemStackFromType(VarietyChests.customChest, type, 2));

                    mutations.add(new ItemStack(Blocks.chest));

                    for( ChestType typeIngredient : ChestType.getTypes() ) {
                        if( typeIngredient.crfItem != null && typeIngredient != ChestType.NULL_TYPE && !typeIngredient.name.equals("original") ) {
                            mutations.add(ChestType.getNewItemStackFromType(VarietyChests.customChest, typeIngredient, 1));
                        }
                    }

                    PositionedStack origPosStack = csRecipe.ingredients.get(4);
                    csRecipe.ingredients.set(4, new PositionedStack(mutations, origPosStack.relx, origPosStack.rely));

                    csRecipe.computeVisuals();
                    rHandler.arecipes.add(csRecipe);

                    break;
                }
            }
        }
    }
}
