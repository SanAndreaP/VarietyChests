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

public class RecipeGlowingChests
        implements IRecipe
{
    private final Item itemCustomChest = Item.getItemFromBlock(VarietyChests.customChest);
    private final Item itemChest = Item.getItemFromBlock(Blocks.chest);
    private final Item itemGlowstoneB = Item.getItemFromBlock(Blocks.glowstone);

    @Override
    public boolean matches(InventoryCrafting inventoryCrafting, World world) {
        boolean hasGlowstone = false;
        boolean hasChest = false;

        for( int slotId = 0; slotId < 9; slotId++) {
            ItemStack slot = inventoryCrafting.getStackInSlot(slotId);
            if( slot != null ) {
                if( slot.getItem() == itemGlowstoneB ) {
                    if( hasGlowstone ) {
                        return false;
                    }

                    hasGlowstone = true;
                } else if( (slot.getItem() == itemCustomChest && ChestType.getType(slot) != ChestType.NULL_TYPE) || slot.getItem() == itemChest ) {
                    if( hasChest ) {
                        return false;
                    }

                    hasChest = true;
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
                    return ChestType.getNewItemStackFromType(VarietyChests.customGlowingChest, ChestType.ORIGINAL, 1);
                } else if( ChestType.getType(slot) != ChestType.NULL_TYPE ) {
                    return ChestType.getNewItemStackFromType(VarietyChests.customGlowingChest, ChestType.getType(slot), 1);
                }
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
        return null;
    }
}
