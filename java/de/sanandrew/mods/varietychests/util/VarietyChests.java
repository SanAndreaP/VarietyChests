package de.sanandrew.mods.varietychests.util;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import de.sanandrew.core.manpack.util.SAPUtils;
import de.sanandrew.mods.varietychests.block.BlockCustomChest;
import de.sanandrew.mods.varietychests.client.ItemRendererCustomChest;
import de.sanandrew.mods.varietychests.client.TileEntityCustomChestRenderer;
import de.sanandrew.mods.varietychests.item.ItemBlockCustomChest;
import de.sanandrew.mods.varietychests.tileentity.TileEntityCustomChest;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

@Mod(modid = VarietyChests.MODID, version = VarietyChests.VERSION)
public class VarietyChests
{
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";

    public static Block customChest;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        customChest = new BlockCustomChest();
        customChest.setBlockName(MODID + ":customChest");

        SAPUtils.registerBlockWithItem(customChest, ItemBlockCustomChest.class);

        GameRegistry.registerTileEntity(TileEntityCustomChest.class, MODID + ":customChestTE");

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCustomChest.class, new TileEntityCustomChestRenderer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(customChest), new ItemRendererCustomChest());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }
}
