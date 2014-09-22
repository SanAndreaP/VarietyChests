/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP, SilverChiren and CliffracerX
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.block;

import de.sanandrew.mods.varietychests.tileentity.TileEntityCustomChest;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;

public class BlockCustomChest
        extends BlockChest
{
    public BlockCustomChest() {
        super(-1);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
        int myType = this.getChestType(blockAccess, x, y, z);

        if( this.getChestType(blockAccess, x, y, z - 1) == myType ) {
            this.setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
        } else if( this.getChestType(blockAccess, x, y, z + 1) == myType ) {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
        } else if( this.getChestType(blockAccess, x - 1, y, z) == myType ) {
            this.setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        } else if( this.getChestType(blockAccess, x + 1, y, z) == myType ) {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
        } else {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        int zNegType = this.getChestType(world, x, y, z - 1);
        int zPosType = this.getChestType(world, x, y, z + 1);
        int xNegType = this.getChestType(world, x - 1, y, z);
        int xPosType = this.getChestType(world, x + 1, y, z);

        int myType = stack.getItemDamage();

        byte newMeta = 0;
        int rotation = MathHelper.floor_double((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if( rotation == 0 ) {
            newMeta = 2;
        }

        if( rotation == 1 ) {
            newMeta = 5;
        }

        if( rotation == 2 ) {
            newMeta = 3;
        }

        if( rotation == 3 ) {
            newMeta = 4;
        }

        if( zNegType != myType && zPosType != myType && xNegType != myType && xPosType != myType ) {
            world.setBlockMetadataWithNotify(x, y, z, newMeta, 3);
        } else {
            if( zNegType == myType || zPosType == myType ) {
                if( newMeta != 4 && newMeta != 5 ) {
                    newMeta = (byte) (world.getBlock(x - 1, y, z).func_149730_j() ? 5 : 4);
                }

                if( zNegType == myType ) {
                    world.setBlockMetadataWithNotify(x, y, z - 1, newMeta, 3);
                } else {
                    world.setBlockMetadataWithNotify(x, y, z + 1, newMeta, 3);
                }

                world.setBlockMetadataWithNotify(x, y, z, newMeta, 3);
            }

            if( xNegType == myType || xPosType == myType ) {
                if( newMeta != 2 && newMeta != 3 ) {
                    newMeta = (byte) (world.getBlock(x, y, z - 1).func_149730_j() ? 3 : 2);
                }

                if( xNegType == myType ) {
                    world.setBlockMetadataWithNotify(x - 1, y, z, newMeta, 3);
                } else {
                    world.setBlockMetadataWithNotify(x + 1, y, z, newMeta, 3);
                }

                world.setBlockMetadataWithNotify(x, y, z, newMeta, 3);
            }
        }


        ((TileEntityCustomChest) world.getTileEntity(x, y, z)).chestType = stack.getItemDamage();

        if( stack.hasDisplayName() ) {
            ((TileEntityCustomChest) world.getTileEntity(x, y, z)).func_145976_a(stack.getDisplayName());
        }
    }

    @Override
    public void func_149954_e(World world, int x, int y, int z) {
        // better code within onBlockPlacedBy
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> items = new ArrayList<>();
        items.add(new ItemStack(this, 1, this.getChestType(world, x, y, z)));
        return items;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return true; // this is handled by the ItemBlockCustomChest class
    }

    @Override
    public IInventory func_149951_m(World world, int x, int y, int z) {
        IInventory object = (IInventory) world.getTileEntity(x, y, z);

        int myType = this.getChestType(world, x, y, z);
        boolean isZNegTypeIdent = this.getChestType(world, x, y, z - 1) == myType;
        boolean isZPosTypeIdent = this.getChestType(world, x, y, z + 1) == myType;
        boolean isXNegTypeIdent = this.getChestType(world, x - 1, y, z) == myType;
        boolean isXPosTypeIdent = this.getChestType(world, x + 1, y, z) == myType;

        if( object == null ) {
            return null;
        } else if( world.isSideSolid(x, y + 1, z, DOWN) ) {
            return null;
        } else if( isOcelotSittingOnChest(world, x, y, z) ) {
            return null;
        } else if( isXNegTypeIdent && (world.isSideSolid(x - 1, y + 1, z, DOWN) || isOcelotSittingOnChest(world, x - 1, y, z)) ) {
            return null;
        } else if( isXPosTypeIdent && (world.isSideSolid(x + 1, y + 1, z, DOWN) || isOcelotSittingOnChest(world, x + 1, y, z)) ) {
            return null;
        } else if( isZNegTypeIdent && (world.isSideSolid(x, y + 1, z - 1, DOWN) || isOcelotSittingOnChest(world, x, y, z - 1)) ) {
            return null;
        } else if( isZPosTypeIdent && (world.isSideSolid(x, y + 1, z + 1, DOWN) || isOcelotSittingOnChest(world, x, y, z + 1)) ) {
            return null;
        } else {
            if( isXNegTypeIdent ) {
                object = new InventoryLargeChest("container.chestDouble", (TileEntityChest) world.getTileEntity(x - 1, y, z), object);
            } else if( isXPosTypeIdent ) {
                object = new InventoryLargeChest("container.chestDouble", object, (TileEntityChest) world.getTileEntity(x + 1, y, z));
            } else if( isZNegTypeIdent ) {
                object = new InventoryLargeChest("container.chestDouble", (TileEntityChest) world.getTileEntity(x, y, z - 1), object);
            } else if( isZPosTypeIdent ) {
                object = new InventoryLargeChest("container.chestDouble", object, (TileEntityChest) world.getTileEntity(x, y, z + 1));
            }

            return object;
        }
    }

    private static boolean isOcelotSittingOnChest(World world, int x, int y, int z) {
        Iterator iterator = world.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getBoundingBox((double) x, (double) (y + 1), (double) z,
                                                                                                         (double) (x + 1), (double) (y + 2), (double) (z + 1))
        ).iterator();

        EntityOcelot entityocelot;

        do {
            if( !iterator.hasNext() ) {
                return false;
            }

            Entity entity = (Entity) iterator.next();
            entityocelot = (EntityOcelot) entity;
        } while( !entityocelot.isSitting() );

        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityCustomChest();
    }

    @Override
    public boolean canProvidePower() {
        return false;
    }

    public int getChestType(IBlockAccess blockAccess, int x, int y, int z) {
        if( blockAccess.getBlock(x, y, z) == this ) {
            TileEntity te = blockAccess.getTileEntity(x, y, z);
            if( te instanceof TileEntityCustomChest ) {
                return ((TileEntityCustomChest) te).chestType;
            }
        }

        return -1;
    }
}
