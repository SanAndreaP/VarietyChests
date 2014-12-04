/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.addons.exblvarietychests.util;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import de.sanandrew.mods.varietychests.util.ChestType;
import extrabiomes.api.Stuff;
import extrabiomes.module.fabrica.block.BlockCustomWood.BlockType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@Mod(modid = ExBiomesXlVarietyChests.MOD_ID, version = ExBiomesXlVarietyChests.VERSION, name = "Variety Chests EXBL AddOn",
     dependencies = "required-after:varietychests@[1.1.1,);required-after:ExtrabiomesXL")
public final class ExBiomesXlVarietyChests
{
    public static final String MOD_ID = "exblvarchests";
    public static final String VERSION = "1.0.0";

    @Instance(MOD_ID)
    public static ExBiomesXlVarietyChests instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ChestType.registerChestType("redwood", new ResourceLocation(MOD_ID, "textures/entity/chest/redwood.png"),
                                    new ResourceLocation(MOD_ID, "textures/entity/chest/redwood_double.png"),
                                    new ItemStack(Stuff.planks.get(), 1, BlockType.REDWOOD.metadata())
        );
        ChestType.registerChestType("fir", new ResourceLocation(MOD_ID, "textures/entity/chest/fir.png"),
                                    new ResourceLocation(MOD_ID, "textures/entity/chest/fir_double.png"),
                                    new ItemStack(Stuff.planks.get(), 1, BlockType.FIR.metadata())
        );
        ChestType.registerChestType("acacia_exbl", new ResourceLocation(MOD_ID, "textures/entity/chest/acacia_exbl.png"),
                                    new ResourceLocation(MOD_ID, "textures/entity/chest/acacia_exbl_double.png"),
                                    new ItemStack(Stuff.planks.get(), 1, BlockType.ACACIA.metadata())
        );
        ChestType.registerChestType("cypress", new ResourceLocation(MOD_ID, "textures/entity/chest/cypress.png"),
                                    new ResourceLocation(MOD_ID, "textures/entity/chest/cypress_double.png"),
                                    new ItemStack(Stuff.planks.get(), 1, BlockType.CYPRESS.metadata())
        );
        ChestType.registerChestType("japanesemaple", new ResourceLocation(MOD_ID, "textures/entity/chest/japanesemaple.png"),
                                    new ResourceLocation(MOD_ID, "textures/entity/chest/japanesemaple_double.png"),
                                    new ItemStack(Stuff.planks.get(), 1, BlockType.JAPANESE_MAPLE.metadata())
        );
        ChestType.registerChestType("rainboweucalyptus", new ResourceLocation(MOD_ID, "textures/entity/chest/rainboweucalyptus.png"),
                                    new ResourceLocation(MOD_ID, "textures/entity/chest/rainboweucalyptus_double.png"),
                                    new ItemStack(Stuff.planks.get(), 1, BlockType.RAINBOW_EUCALYPTUS.metadata())
        );
        ChestType.registerChestType("autumn", new ResourceLocation(MOD_ID, "textures/entity/chest/autumn.png"),
                                    new ResourceLocation(MOD_ID, "textures/entity/chest/autumn_double.png"),
                                    new ItemStack(Stuff.planks.get(), 1, BlockType.AUTUMN.metadata())
        );
        ChestType.registerChestType("baldcypress", new ResourceLocation(MOD_ID, "textures/entity/chest/baldcypress.png"),
                                    new ResourceLocation(MOD_ID, "textures/entity/chest/baldcypress_double.png"),
                                    new ItemStack(Stuff.planks.get(), 1, BlockType.BALD_CYPRESS.metadata())
        );
        ChestType.registerChestType("sakura", new ResourceLocation(MOD_ID, "textures/entity/chest/sakura.png"),
                                    new ResourceLocation(MOD_ID, "textures/entity/chest/sakura_double.png"),
                                    new ItemStack(Stuff.planks.get(), 1, BlockType.SAKURA_BLOSSOM.metadata())
        );
    }
}
