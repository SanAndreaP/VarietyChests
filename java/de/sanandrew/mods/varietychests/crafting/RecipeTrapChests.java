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

public class RecipeTrapChests
        implements IRecipe
{
    private final Item itemCustomChest = Item.getItemFromBlock(VarietyChests.customChest);
    private final Item itemTripwireHook = Item.getItemFromBlock(Blocks.tripwire_hook);

    @Override
    public boolean matches(InventoryCrafting inventoryCrafting, World world) {
        boolean hasChest = false;
        boolean hasTripHook = false;

        for( int slotId = 0; slotId < 9; slotId++) {
            ItemStack slot = inventoryCrafting.getStackInSlot(slotId);
            if( slot != null ) {
                if( slotId % 3 > 0 ) {
                    if( !hasTripHook && slot.getItem() == itemTripwireHook ) {
                        hasTripHook = true;
                        continue;
                    }

                    ItemStack prevSlot = inventoryCrafting.getStackInSlot(slotId - 1);
                    if( prevSlot != null && slot.getItem() == itemCustomChest && ChestType.getType(slot) != ChestType.NULL_TYPE
                        && prevSlot.getItem() == itemTripwireHook)
                    {
                        if( hasChest ) {
                            return false;
                        }

                        hasChest = true;
                    } else {
                        return false;
                    }
                }
            }
        }

        return hasChest;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
        for( int slotId = 0; slotId < 9; slotId++) {
            ItemStack slot = inventoryCrafting.getStackInSlot(slotId);
            if( slot != null && ChestType.getType(slot) != ChestType.NULL_TYPE ) {
                return ChestType.getNewItemStackFromType(VarietyChests.customTrapChest, ChestType.getType(slot), 1);
            }
        }

        return null;
    }

    @Override
    public int getRecipeSize() {
        return 2;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ChestType.getNewItemStackFromType(VarietyChests.customTrapChest, ChestType.ORIGINAL, 1);
    }
}
