/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.crafting;

import de.sanandrew.core.manpack.util.helpers.SAPUtils;
import de.sanandrew.mods.varietychests.util.ChestType;
import de.sanandrew.mods.varietychests.util.VarietyChests;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipePlainChests
        implements IRecipe
{
    @Override
    public boolean matches(InventoryCrafting inventoryCrafting, World world) {
        ItemStack wood = null;
        boolean foundChest = false;

        for( int slotId = 0; slotId < inventoryCrafting.getSizeInventory(); slotId++ ) {
            ItemStack slot = inventoryCrafting.getStackInSlot(slotId);
            if( slot == null ) {
                return false;
            }

            if( slotId == 4 ) {
                Block block = Block.getBlockFromItem(slot.getItem());
                if( block != Blocks.chest && block != VarietyChests.customChest ) {
                    return false;
                } else {
                    foundChest = true;
                }
            } else {
                if( wood == null ) {
                    ChestType type = ChestType.getTypeFromCraftingMaterial(slot);
                    if( type == ChestType.NULL_TYPE ) {
                        return false;
                    }

                    wood = slot;
                } else {
                    if( !SAPUtils.areStacksEqual(wood, slot, false) ) {
                        return false;
                    }
                }
            }
        }

        return foundChest;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
        ChestType type = ChestType.getTypeFromCraftingMaterial(inventoryCrafting.getStackInSlot(0));

        if( type == ChestType.ORIGINAL ) {
            return new ItemStack(Blocks.chest, 2);
        }

        return ChestType.getNewItemStackFromType(VarietyChests.customChest, type, 2);
    }

    @Override
    public int getRecipeSize() {
        return 9;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ChestType.getNewItemStackFromType(VarietyChests.customChest, ChestType.ORIGINAL, 1);
    }
}
