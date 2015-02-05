/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP, SilverChiren and CliffracerX
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import de.sanandrew.mods.varietychests.tileentity.TileEntityCustomChest;
import de.sanandrew.mods.varietychests.util.ChestType;
import de.sanandrew.mods.varietychests.util.VarietyChests;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PopulatePostHandler
{
    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void onChunkPopulatePost(PopulateChunkEvent.Post event) {
        if( !event.world.isRemote ) {
            // copy chunk TE list (it gets modified during iteration, causing ConcurrentModificationExceptions w/o copying)
            Map tileList = new HashMap(event.world.getChunkFromChunkCoords(event.chunkX, event.chunkZ).chunkTileEntityMap);

            // iterte through the chunks copied TE list
            for( TileEntity te : (Collection<TileEntity>) tileList.values() ) {
                if( te.getClass().equals(TileEntityChest.class) ) {
                    TileEntityChest chest = (TileEntityChest) te;
                    String[] typeNames = ChestType.getTypeNames();
                    int origDirection = event.world.getBlockMetadata(chest.xCoord, chest.yCoord, chest.zCoord);

                    // preemptively removing the chest TE, so it doesn't cause drops
                    event.world.removeTileEntity(chest.xCoord, chest.yCoord, chest.zCoord);
                    // replace original chest with mine
                    event.world.setBlock(chest.xCoord, chest.yCoord, chest.zCoord, VarietyChests.customChest, origDirection, 2);

                    // grab new TE instance and set type
                    TileEntityCustomChest custChest = (TileEntityCustomChest) event.world.getTileEntity(chest.xCoord, chest.yCoord, chest.zCoord);
                    custChest.chestType = typeNames[event.rand.nextInt(typeNames.length)];

                    // fill new inventory with the items the old chest had
                    for( int i = 0; i < chest.getSizeInventory(); i++ ) {
                        custChest.setInventorySlotContents(i, chest.getStackInSlot(i));
                    }

                    // tell the world I've changed the chest
                    custChest.markDirty();
                    event.world.markBlockForUpdate(chest.xCoord, chest.yCoord, chest.zCoord);
                }
            }
        }
    }
}
