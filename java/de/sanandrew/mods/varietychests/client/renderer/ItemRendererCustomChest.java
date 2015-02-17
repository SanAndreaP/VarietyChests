/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.sanandrew.mods.varietychests.tileentity.TileEntityCustomChest;
import de.sanandrew.mods.varietychests.util.ChestType;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class ItemRendererCustomChest
        implements IItemRenderer
{
    private TileEntityCustomChest chest = new TileEntityCustomChest();

    @Override
    public boolean handleRenderType(ItemStack stack, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack stack, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {
        GL11.glPushMatrix();
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);

        if( type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON ) {
            GL11.glTranslatef(-1.0F, 0.0F, -0.0F);
        } else {
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        }

        this.chest.blockType = Block.getBlockFromItem(stack.getItem());
        this.chest.chestType = ChestType.getType(stack);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        TileEntityRendererDispatcher.instance.renderTileEntityAt(this.chest, 0.0D, 0.0D, 0.0D, 0.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }
}
