/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
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

public class RecipeGlowingChestDisassemble
        implements IRecipe
{
    @Override
    public boolean matches(InventoryCrafting inventoryCrafting, World world) {
        boolean hasChest = false;
        for( int i = 0; i < inventoryCrafting.getSizeInventory(); i++ ) {
            ItemStack slotStack = inventoryCrafting.getStackInSlot(i);
            if( hasChest ) {
                if( slotStack != null ) {
                    return false;
                }
            } else if( slotStack != null && slotStack.getItem() == Item.getItemFromBlock(VarietyChests.customGlowingChest) ) {
                hasChest = true;
            }
        }

        return true;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
        for( int i = 0; i < inventoryCrafting.getSizeInventory(); i++ ) {
            ItemStack slotStack = inventoryCrafting.getStackInSlot(i);
            if( slotStack != null && slotStack.getItem() == Item.getItemFromBlock(VarietyChests.customGlowingChest) ) {
                ChestType type = ChestType.getType(slotStack);
                if( type == ChestType.ORIGINAL ) {
                    return new ItemStack(Blocks.chest, 1);
                }

                return ChestType.getNewItemStackFromType(VarietyChests.customChest, type, 1);
            }
        }

        return null;
    }

    @Override
    public int getRecipeSize() {
        return 1;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }
}
