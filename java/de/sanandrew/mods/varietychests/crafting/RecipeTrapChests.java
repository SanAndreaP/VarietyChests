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

public class RecipeTrapChests
        implements IRecipe
{
    @Override
    public boolean matches(InventoryCrafting inventoryCrafting, World world) {
        boolean hasTripwire = false;
        boolean hasChest = false;

        for( int slotId = 0; slotId < 9; slotId++) {
            ItemStack slot = inventoryCrafting.getStackInSlot(slotId);
            if(slot != null) {
                if( slot.getItem() == Item.getItemFromBlock(Blocks.tripwire_hook) ) {
                    if( hasTripwire ) {
                        return false;
                    } else {
                        hasTripwire = true;
                    }
                } else if( ChestType.getType(slot) != ChestType.NULL_TYPE ) {
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

        return hasChest && hasTripwire;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
        for( int slotId = 0; slotId < 9; slotId++) {
            ItemStack slot = inventoryCrafting.getStackInSlot(slotId);
            if( slot != null ) {
                if( ChestType.getType(slot) != ChestType.NULL_TYPE ) {
                    return ChestType.getNewItemStackFromType(VarietyChests.customTrapChest, ChestType.getType(slot).name, 1);
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
