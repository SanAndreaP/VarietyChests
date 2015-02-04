/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.client.util;

import cpw.mods.fml.client.registry.ClientRegistry;
import de.sanandrew.mods.varietychests.client.renderer.ItemRendererCustomChest;
import de.sanandrew.mods.varietychests.client.renderer.TileEntityCustomChestRenderer;
import de.sanandrew.mods.varietychests.tileentity.TileEntityCustomChest;
import de.sanandrew.mods.varietychests.util.CommonProxy;
import de.sanandrew.mods.varietychests.util.VarietyChests;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy
        extends CommonProxy
{
    @Override
    public void preInit() {
        super.preInit();

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCustomChest.class, new TileEntityCustomChestRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(VarietyChests.customChest), new ItemRendererCustomChest());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(VarietyChests.customGlowingChest), new ItemRendererCustomChest());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(VarietyChests.customTrapChest), new ItemRendererCustomChest());
    }
}
