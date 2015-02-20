/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.util.modcompat.ebxl;

import de.sanandrew.core.manpack.util.modcompatibility.IModInitHelper;
import de.sanandrew.mods.varietychests.util.ChestType;
import extrabiomes.api.Stuff;
import extrabiomes.module.fabrica.block.BlockCustomWood.BlockType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class EbxlIntegration
        implements IModInitHelper
{
    private static final String RES_FOLDER = "ebxl_varietychests";

    @Override
    public void preInitialize() {
        ChestType.registerChestType("redwood", new ResourceLocation(RES_FOLDER, "textures/entity/chest/redwood.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/redwood_double.png"),
                                    new ItemStack(Stuff.planks.get(), 1, BlockType.REDWOOD.metadata())
        );
        ChestType.registerChestType("fir", new ResourceLocation(RES_FOLDER, "textures/entity/chest/fir.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/fir_double.png"),
                                    new ItemStack(Stuff.planks.get(), 1, BlockType.FIR.metadata())
        );
        ChestType.registerChestType("acacia_exbl", new ResourceLocation(RES_FOLDER, "textures/entity/chest/acacia_exbl.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/acacia_exbl_double.png"),
                                    new ItemStack(Stuff.planks.get(), 1, BlockType.ACACIA.metadata())
        );
        ChestType.registerChestType("cypress", new ResourceLocation(RES_FOLDER, "textures/entity/chest/cypress.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/cypress_double.png"),
                                    new ItemStack(Stuff.planks.get(), 1, BlockType.CYPRESS.metadata())
        );
        ChestType.registerChestType("japanesemaple", new ResourceLocation(RES_FOLDER, "textures/entity/chest/japanesemaple.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/japanesemaple_double.png"),
                                    new ItemStack(Stuff.planks.get(), 1, BlockType.JAPANESE_MAPLE.metadata())
        );
        ChestType.registerChestType("rainboweucalyptus", new ResourceLocation(RES_FOLDER, "textures/entity/chest/rainboweucalyptus.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/rainboweucalyptus_double.png"),
                                    new ItemStack(Stuff.planks.get(), 1, BlockType.RAINBOW_EUCALYPTUS.metadata())
        );
        ChestType.registerChestType("autumn", new ResourceLocation(RES_FOLDER, "textures/entity/chest/autumn.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/autumn_double.png"),
                                    new ItemStack(Stuff.planks.get(), 1, BlockType.AUTUMN.metadata())
        );
        ChestType.registerChestType("baldcypress", new ResourceLocation(RES_FOLDER, "textures/entity/chest/baldcypress.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/baldcypress_double.png"),
                                    new ItemStack(Stuff.planks.get(), 1, BlockType.BALD_CYPRESS.metadata())
        );
        ChestType.registerChestType("sakura", new ResourceLocation(RES_FOLDER, "textures/entity/chest/sakura.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/sakura_double.png"),
                                    new ItemStack(Stuff.planks.get(), 1, BlockType.SAKURA_BLOSSOM.metadata())
        );
    }

    @Override
    public void initialize() {

    }

    @Override
    public void postInitialize() {

    }
}
