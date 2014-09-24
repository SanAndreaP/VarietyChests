/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.tileentity;

import de.sanandrew.mods.varietychests.block.BlockCustomChest;
import de.sanandrew.mods.varietychests.util.ChestType;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityCustomChest
        extends TileEntityChest
{
    public String chestType = ChestType.NULL_TYPE.name;

    public TileEntityCustomChest() {
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setString("type", this.chestType);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.chestType = nbt.getString("type");
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("type", this.chestType);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.chestType = pkt.func_148857_g().getString("type");
    }

    @Override
    public void checkForAdjacentChests() {
        if( !this.adjacentChestChecked ) {
            this.adjacentChestChecked = true;
            this.adjacentChestZNeg = null;
            this.adjacentChestXPos = null;
            this.adjacentChestXNeg = null;
            this.adjacentChestZPos = null;

            if( this.isSameChest(this.xCoord - 1, this.yCoord, this.zCoord) ) {
                this.adjacentChestXNeg = (TileEntityCustomChest) this.worldObj.getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
            }

            if( this.isSameChest(this.xCoord + 1, this.yCoord, this.zCoord) ) {
                this.adjacentChestXPos = (TileEntityCustomChest) this.worldObj.getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
            }

            if( this.isSameChest(this.xCoord, this.yCoord, this.zCoord - 1) ) {
                this.adjacentChestZNeg = (TileEntityCustomChest) this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
            }

            if( this.isSameChest(this.xCoord, this.yCoord, this.zCoord + 1) ) {
                this.adjacentChestZPos = (TileEntityCustomChest) this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
            }

            if( this.adjacentChestZNeg != null ) {
                ((TileEntityCustomChest) this.adjacentChestZNeg).updateChestChecked(this, 0);
            }

            if( this.adjacentChestZPos != null ) {
                ((TileEntityCustomChest) this.adjacentChestZPos).updateChestChecked(this, 2);
            }

            if( this.adjacentChestXPos != null ) {
                ((TileEntityCustomChest) this.adjacentChestXPos).updateChestChecked(this, 1);
            }

            if( this.adjacentChestXNeg != null ) {
                ((TileEntityCustomChest) this.adjacentChestXNeg).updateChestChecked(this, 3);
            }
        }
    }

    @Override
    public int func_145980_j() {
        return this.chestType.hashCode();
    }

    public ChestType getChestType() {
        return ChestType.getType(this.chestType);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(this.xCoord - 1, this.yCoord, this.zCoord - 1, this.xCoord + 2, this.yCoord + 2, this.zCoord + 2);
    }

    private boolean isSameChest(int x, int y, int z) {
        if( this.worldObj == null ) {
            return false;
        } else {
            Block block = this.worldObj.getBlock(x, y, z);
            return block instanceof BlockCustomChest && ((BlockCustomChest) block).getChestType(this.worldObj, x, y, z) == this.chestType;
        }
    }

    private void updateChestChecked(TileEntityCustomChest chest, int side) {
        if( chest.isInvalid() ) {
            this.adjacentChestChecked = false;
        } else if( this.adjacentChestChecked ) {
            switch( side ) {
                case 0:
                    if( this.adjacentChestZPos != chest ) {
                        this.adjacentChestChecked = false;
                    }

                    break;
                case 1:
                    if( this.adjacentChestXNeg != chest ) {
                        this.adjacentChestChecked = false;
                    }

                    break;
                case 2:
                    if( this.adjacentChestZNeg != chest ) {
                        this.adjacentChestChecked = false;
                    }

                    break;
                case 3:
                    if( this.adjacentChestXPos != chest ) {
                        this.adjacentChestChecked = false;
                    }

                    break;
            }
        }
    }
}
