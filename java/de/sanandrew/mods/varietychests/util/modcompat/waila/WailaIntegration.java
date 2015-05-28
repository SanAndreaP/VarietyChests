/**
 * ****************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP, SilverChiren and CliffracerX
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 * *****************************************************************************************************************
 */
package de.sanandrew.mods.varietychests.util.modcompat.waila;

import de.sanandrew.core.manpack.util.modcompatibility.IModInitHelper;
import de.sanandrew.mods.varietychests.block.BlockCustomChest;
import mcp.mobius.waila.api.impl.ModuleRegistrar;

public class WailaIntegration
        implements IModInitHelper
{
    @Override
    public void preInitialize() {
        VcWailaDataProvider vcdp = new VcWailaDataProvider();
        ModuleRegistrar.instance().registerBodyProvider(vcdp, BlockCustomChest.class);
        ModuleRegistrar.instance().registerStackProvider(vcdp, BlockCustomChest.class);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void postInitialize() {

    }
}
