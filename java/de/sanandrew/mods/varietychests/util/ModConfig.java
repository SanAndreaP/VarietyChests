/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.util;

import net.minecraftforge.common.config.Configuration;

public final class ModConfig
{
    public static Configuration configuration;

    public static void syncConfig() {

        if( configuration.hasChanged() ) {
            configuration.save();
        }
    }
}
