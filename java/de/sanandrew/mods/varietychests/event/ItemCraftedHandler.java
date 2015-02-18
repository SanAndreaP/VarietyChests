/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP, SilverChiren and CliffracerX
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import de.sanandrew.mods.varietychests.util.VarietyChests;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCraftedHandler
{
    @SubscribeEvent
    public void onItemCrafted(ItemCraftedEvent event) {
        ItemStack disassembleItem = null;
        int firstNullSlot = -1;

        for( int i = 0; i < event.craftMatrix.getSizeInventory(); i++ ) {
            ItemStack slotStack = event.craftMatrix.getStackInSlot(i);
            if( disassembleItem != null ) {
                if( slotStack != null ) {
                    return;
                }
            } else if( slotStack != null ) {
                if( slotStack.getItem() == Item.getItemFromBlock(VarietyChests.customGlowingChest) ) {
                    disassembleItem = new ItemStack(Blocks.glowstone, 1);
                } else if( slotStack.getItem() == Item.getItemFromBlock(VarietyChests.customTrapChest)
                           || slotStack.getItem() == Item.getItemFromBlock(Blocks.trapped_chest) )
                {
                    disassembleItem = new ItemStack(Blocks.tripwire_hook, 1);
                }
            } else if( firstNullSlot < 0 ) {
                firstNullSlot = i;
            }
        }


        if( disassembleItem != null ) {
            if( !event.player.inventory.addItemStackToInventory(disassembleItem) ) {
                if( firstNullSlot >= 0 ) {
                    event.craftMatrix.setInventorySlotContents(firstNullSlot, disassembleItem);
                } else {
                    event.player.dropPlayerItemWithRandomChoice(disassembleItem, false);
                }
            }
        }
    }
}
