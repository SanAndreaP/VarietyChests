/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP, SilverChiren and CliffracerX
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.util;

import com.google.common.collect.Maps;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class ChestTypes
{
    public static final Map<Integer, ChestTypes> types = Maps.newHashMap();
    private static int nextId = 0;

    public static int registerChestType(int id, ResourceLocation textureSingle, ResourceLocation textureDouble, String unlocalizedName, ItemStack chestItem,
                                        ItemStack particleTexture) {
        if( id < 0 ) {
            id = nextId++;
        }

        types.put(id, new ChestTypes(id, textureSingle, textureDouble, unlocalizedName));

        return id;
    }

    public final int id;
    public final ResourceLocation textureSng;
    public final ResourceLocation textureDbl;
    public final String unlocName;

    private ChestTypes(int par0, ResourceLocation par1, ResourceLocation par2, String par3) {
        this.id = par0;
        this.textureSng = par1;
        this.textureDbl = par2;
        this.unlocName = par3;
    }
}
