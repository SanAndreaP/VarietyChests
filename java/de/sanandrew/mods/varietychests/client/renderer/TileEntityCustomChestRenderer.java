/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.client.renderer;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.sanandrew.mods.varietychests.block.BlockCustomChest;
import de.sanandrew.mods.varietychests.client.util.Textures;
import de.sanandrew.mods.varietychests.tileentity.TileEntityCustomChest;
import de.sanandrew.mods.varietychests.util.VarietyChests;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class TileEntityCustomChestRenderer
        extends TileEntitySpecialRenderer
{
    private ModelChest modelSingleChest = new ModelChest();
    private ModelChest modelDoubleChest = new ModelLargeChest();

    public TileEntityCustomChestRenderer() {
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partTicks) {
        this.renderChestAt((TileEntityCustomChest) tile, x, y, z, partTicks, 0);
        if( tile.blockType == VarietyChests.customGlowingChest || tile.blockType == VarietyChests.customTrapChest ) {
            this.renderChestAt((TileEntityCustomChest) tile, x, y, z, partTicks, 1);
        }
    }

    public void renderChestAt(TileEntityCustomChest chest, double x, double y, double z, float partTicks, int pass) {
        int meta;

        if( !chest.hasWorldObj() ) {
            meta = 0;
        } else {
            Block block = chest.getBlockType();
            meta = chest.getBlockMetadata();

            if( block instanceof BlockCustomChest && meta == 0 ) {
                try {
                    ((BlockCustomChest) block).func_149954_e(chest.getWorldObj(), chest.xCoord, chest.yCoord, chest.zCoord);
                } catch( ClassCastException e ) {
                    FMLLog.severe("Attempted to render a chest at %d,  %d, %d that was not a chest", chest.xCoord, chest.yCoord, chest.zCoord);
                }

                meta = chest.getBlockMetadata();
            }

            chest.checkForAdjacentChests();
        }

        if( chest.adjacentChestZNeg == null && chest.adjacentChestXNeg == null ) {
            ModelChest modelchest;

            if( chest.adjacentChestXPos == null && chest.adjacentChestZPos == null ) {
                modelchest = this.modelSingleChest;

                if( pass == 0 ) {
                    this.bindTexture(chest.getChestType().textureSng);
                } else {
                    if( chest.blockType == VarietyChests.customGlowingChest ) {
                        this.bindTexture(Textures.CHEST_GLOWMAP_SNG);
                    } else if( chest.blockType == VarietyChests.customTrapChest ) {
                        this.bindTexture(Textures.CHEST_TRAPMAP_SNG);
                    }
                }

            } else {
                modelchest = this.modelDoubleChest;

                if( pass == 0 ) {
                    this.bindTexture(chest.getChestType().textureDbl);
                } else {
                    if( chest.blockType == VarietyChests.customGlowingChest ) {
                        this.bindTexture(Textures.CHEST_GLOWMAP_DBL);
                    } else if( chest.blockType == VarietyChests.customTrapChest ) {
                        this.bindTexture(Textures.CHEST_TRAPMAP_DBL);
                    }
                }
            }

            GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            short short1 = 0;

            if( meta == 2 ) {
                short1 = 180;
            }

            if( meta == 3 ) {
                short1 = 0;
            }

            if( meta == 4 ) {
                short1 = 90;
            }

            if( meta == 5 ) {
                short1 = -90;
            }

            if( meta == 2 && chest.adjacentChestXPos != null ) {
                GL11.glTranslatef(1.0F, 0.0F, 0.0F);
            }

            if( meta == 5 && chest.adjacentChestZPos != null ) {
                GL11.glTranslatef(0.0F, 0.0F, -1.0F);
            }

            GL11.glRotatef((float) short1, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            float f1 = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * partTicks;
            float f2;

            if( chest.adjacentChestZNeg != null ) {
                f2 = chest.adjacentChestZNeg.prevLidAngle + (chest.adjacentChestZNeg.lidAngle - chest.adjacentChestZNeg.prevLidAngle) * partTicks;

                if( f2 > f1 ) {
                    f1 = f2;
                }
            }

            if( chest.adjacentChestXNeg != null ) {
                f2 = chest.adjacentChestXNeg.prevLidAngle + (chest.adjacentChestXNeg.lidAngle - chest.adjacentChestXNeg.prevLidAngle) * partTicks;

                if( f2 > f1 ) {
                    f1 = f2;
                }
            }

            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            modelchest.chestLid.rotateAngleX = -(f1 * (float) Math.PI / 2.0F);
            if( pass == 1 ) {
                GL11.glEnable(GL11.GL_BLEND);
                OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
                modelchest.renderAll();
                GL11.glDisable(GL11.GL_BLEND);
            } else {
                modelchest.renderAll();
            }
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
