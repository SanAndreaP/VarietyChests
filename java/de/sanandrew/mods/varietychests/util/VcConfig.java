/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.util;

import net.minecraftforge.common.config.Configuration;

public final class VcConfig
{
    public static Configuration configuration;

    public static boolean extraBiomesXlTypes = true;
    public static boolean biomesOPlentyTypes = true;
    public static boolean neiCraftingCompat = true;
    public static boolean wailaOverlayCompat = true;
    public static boolean disassembleRecipes = true;

    public static void syncConfig() {
        extraBiomesXlTypes = configuration.getBoolean("ExtraBiomesXL Types", Configuration.CATEGORY_GENERAL, extraBiomesXlTypes,
                                                      "Adds Types for all woods added by ExtraBiomesXL (only when EBXL is available).");
        biomesOPlentyTypes = configuration.getBoolean("Biomes O' Plenty Types", Configuration.CATEGORY_GENERAL, biomesOPlentyTypes,
                                                      "Adds Types for all woods added by Biomes O' Plenty (only when BOP is available).");
        neiCraftingCompat = configuration.getBoolean("NEI Crafting Recipes Fix", Configuration.CATEGORY_GENERAL, neiCraftingCompat,
                                                     "Allows NotEnoughItems to show all recipes added by this mod (only when NEI is available)");
        wailaOverlayCompat = configuration.getBoolean("WAILA Overlay Fix", Configuration.CATEGORY_GENERAL, wailaOverlayCompat,
                                                     "Allows WAILA to properly render the Ingame-Overlay for the VC-Chests (only when WAILA is available)");
        disassembleRecipes = configuration.getBoolean("Add Chest Disassembly", Configuration.CATEGORY_GENERAL, disassembleRecipes,
                                                      "Allows disassembly of irregular chests (glowing, trapped, etc.) into regular chests and the used material.\n" +
                                                      "Note: Also allows for vanilla trapped chests to be disassembled!");

        if( configuration.hasChanged() ) {
            configuration.save();
        }
    }
}
