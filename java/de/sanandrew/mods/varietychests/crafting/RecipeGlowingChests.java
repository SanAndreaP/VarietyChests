/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP, SilverChiren and CliffracerX
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.crafting;

import de.sanandrew.mods.varietychests.util.ChestType;
import de.sanandrew.mods.varietychests.util.VarietyChests;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipeGlowingChests
        implements IRecipe
{
    @Override
    public boolean matches(InventoryCrafting inventoryCrafting, World world) {
        boolean hasGlowstone = false;
        boolean hasChest = false;

        for( int slotId = 0; slotId < 9; slotId++) {
            ItemStack slot = inventoryCrafting.getStackInSlot(slotId);
            if(slot != null) {
                if( slot.getItem() == Item.getItemFromBlock(Blocks.glowstone) ) {
                    if( hasGlowstone ) {
                        return false;
                    } else {
                        hasGlowstone = true;
                    }
                } else if( ChestType.getType(slot) != ChestType.NULL_TYPE || slot.getItem() == Item.getItemFromBlock(Blocks.chest) ) {
                    if( hasChest ) {
                        return false;
                    } else {
                        hasChest = true;
                    }
                } else {
                    return false;
                }
            }
        }

        return hasChest && hasGlowstone;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
        for( int slotId = 0; slotId < 9; slotId++) {
            ItemStack slot = inventoryCrafting.getStackInSlot(slotId);
            if( slot != null ) {
                if( slot.getItem() == Item.getItemFromBlock(Blocks.chest) ) {
                    return ChestType.getNewItemStackFromType(VarietyChests.customGlowingChest, "original", 1);
                } else if( ChestType.getType(slot) != ChestType.NULL_TYPE ) {
                    return ChestType.getNewItemStackFromType(VarietyChests.customGlowingChest, ChestType.getType(slot).name, 1);
                }
            }
        }

        return null;
    }

    @Override
    public int getRecipeSize() {
        return 9;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }
}
