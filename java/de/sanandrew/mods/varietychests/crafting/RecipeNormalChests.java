/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP, SilverChiren and CliffracerX
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.crafting;

import de.sanandrew.core.manpack.util.SAPUtils;
import de.sanandrew.mods.varietychests.util.ChestType;
import de.sanandrew.mods.varietychests.util.VarietyChests;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipeNormalChests
        implements IRecipe
{
    @Override
    public boolean matches(InventoryCrafting inventoryCrafting, World world) {
        ItemStack wood = null;
        for( int slotId = 0; slotId < 9; slotId++ ) {
            ItemStack slot = inventoryCrafting.getStackInSlot(slotId);
            if( slot == null ) {
                return false;
            }

            if( slotId == 4 ) {
                Block block = Block.getBlockFromItem(slot.getItem());
                if( block != Blocks.chest && block != VarietyChests.customChest ) {
                    return false;
                }
            } else {
                if( wood == null ) {
                    ChestType type = ChestType.getTypeFromCraftingMaterial(slot);
                    if( type == ChestType.NULL_TYPE ) {
                        return false;
                    }

                    wood = slot;
                } else {
                    if( !SAPUtils.areStacksEqualWithWCV(wood, slot) ) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
        ChestType type = ChestType.getTypeFromCraftingMaterial(inventoryCrafting.getStackInSlot(0));

        if( type.name.equals("original") ) {
            return new ItemStack(Blocks.chest, 2);
        }

        return ChestType.getNewItemStackFromType(VarietyChests.customChest, type.name, 2);
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
