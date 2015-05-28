/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.util.modcompat.bop;

import biomesoplenty.api.content.BOPCBlocks;
import de.sanandrew.core.manpack.util.modcompatibility.IModInitHelper;
import de.sanandrew.mods.varietychests.util.ChestType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BopIntegration
        implements IModInitHelper
{
    private static final String RES_FOLDER = "bop_varietychests";

    @Override
    public void preInitialize() {
        ChestType.registerChestType("bop_sacredoak", new ResourceLocation(RES_FOLDER, "textures/entity/chest/sacredoak.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/sacredoak_double.png"),
                                    new ItemStack(BOPCBlocks.planks, 1, 0)
        );
        ChestType.registerChestType("bop_cherry", new ResourceLocation(RES_FOLDER, "textures/entity/chest/cherry.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/cherry_double.png"),
                                    new ItemStack(BOPCBlocks.planks, 1, 1)
        );
        ChestType.registerChestType("bop_dark", new ResourceLocation(RES_FOLDER, "textures/entity/chest/dark.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/dark_double.png"),
                                    new ItemStack(BOPCBlocks.planks, 1, 2)
        );
        ChestType.registerChestType("bop_fir", new ResourceLocation(RES_FOLDER, "textures/entity/chest/fir.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/fir_double.png"),
                                    new ItemStack(BOPCBlocks.planks, 1, 3)
        );
        ChestType.registerChestType("bop_ethereal", new ResourceLocation(RES_FOLDER, "textures/entity/chest/ethereal.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/ethereal_double.png"),
                                    new ItemStack(BOPCBlocks.planks, 1, 4)
        );
        ChestType.registerChestType("bop_magic", new ResourceLocation(RES_FOLDER, "textures/entity/chest/magic.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/magic_double.png"),
                                    new ItemStack(BOPCBlocks.planks, 1, 5)
        );
        ChestType.registerChestType("bop_mangrove", new ResourceLocation(RES_FOLDER, "textures/entity/chest/mangrove.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/mangrove_double.png"),
                                    new ItemStack(BOPCBlocks.planks, 1, 6)
        );
        ChestType.registerChestType("bop_palm", new ResourceLocation(RES_FOLDER, "textures/entity/chest/palm.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/palm_double.png"),
                                    new ItemStack(BOPCBlocks.planks, 1, 7)
        );
        ChestType.registerChestType("bop_redwood", new ResourceLocation(RES_FOLDER, "textures/entity/chest/redwood.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/redwood_double.png"),
                                    new ItemStack(BOPCBlocks.planks, 1, 8)
        );
        ChestType.registerChestType("bop_willow", new ResourceLocation(RES_FOLDER, "textures/entity/chest/willow.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/willow_double.png"),
                                    new ItemStack(BOPCBlocks.planks, 1, 9)
        );
        ChestType.registerChestType("bop_bamboo", new ResourceLocation(RES_FOLDER, "textures/entity/chest/bamboo.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/bamboo_double.png"),
                                    new ItemStack(BOPCBlocks.planks, 1, 10)
        );
        ChestType.registerChestType("bop_pine", new ResourceLocation(RES_FOLDER, "textures/entity/chest/pine.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/pine_double.png"),
                                    new ItemStack(BOPCBlocks.planks, 1, 11)
        );
        ChestType.registerChestType("bop_hellbark", new ResourceLocation(RES_FOLDER, "textures/entity/chest/hell_bark.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/hell_bark_double.png"),
                                    new ItemStack(BOPCBlocks.planks, 1, 12)
        );
        ChestType.registerChestType("bop_jacaranda", new ResourceLocation(RES_FOLDER, "textures/entity/chest/jacaranda.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/jacaranda_double.png"),
                                    new ItemStack(BOPCBlocks.planks, 1, 13)
        );
        ChestType.registerChestType("bop_mahogany", new ResourceLocation(RES_FOLDER, "textures/entity/chest/mahogany.png"),
                                    new ResourceLocation(RES_FOLDER, "textures/entity/chest/mahogany_double.png"),
                                    new ItemStack(BOPCBlocks.planks, 1, 14)
        );
    }

    @Override
    public void initialize() {

    }

    @Override
    public void postInitialize() {

    }
}
