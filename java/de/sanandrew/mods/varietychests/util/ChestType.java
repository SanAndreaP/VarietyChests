/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.util;

import de.sanandrew.core.manpack.util.NbtTypes;
import de.sanandrew.core.manpack.util.SAPUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ChestType
{
    public static final ChestTypeList types = new ChestTypeList();
    public static final ChestType NULL_TYPE = new ChestType("NULL_CHEST", new ResourceLocation("NULL_CHEST"), new ResourceLocation("NULL_CHEST"), null);

    public static void registerChestType(String name, ResourceLocation textureSingle, ResourceLocation textureDouble, ItemStack craftingItem) {
        types.put(name, new ChestType(name, textureSingle, textureDouble, craftingItem));
    }

    public static ChestType getType(String name) {
        ChestType type = types.get(name);
        if( type == null ) {
            return NULL_TYPE;
        }

        return type;
    }

    public static String[] getTypeNames() {
        return types.keySet().toArray(new String[types.size()]);
    }

    public static ChestType getType(ItemStack stack) {
        if( !stack.hasTagCompound() || !stack.getTagCompound().hasKey(VarietyChests.NBT_CHEST_TYPE, NbtTypes.NBT_STRING) ) {
            return NULL_TYPE;
        }

        return getType(stack.getTagCompound().getString(VarietyChests.NBT_CHEST_TYPE));
    }

    public static ChestType getTypeFromCraftingMaterial(ItemStack stack) {
        for( ChestType type : types.values() ) {
            if( SAPUtils.areStacksEqualWithWCV(stack, type.crfItem) ) {
                return type;
            }
        }

        return NULL_TYPE;
    }

    public static ItemStack getNewItemStackFromType(Block block, String type, int count) {
        NBTTagCompound nbt = new NBTTagCompound();
        ItemStack stack = new ItemStack(Item.getItemFromBlock(block), count);

        nbt.setString(VarietyChests.NBT_CHEST_TYPE, type);
        stack.setTagCompound(nbt);

        return stack;
    }

    public final ResourceLocation textureSng;
    public final ResourceLocation textureDbl;
    public final String name;
    public final ItemStack crfItem;

    private ChestType(String par0, ResourceLocation par1, ResourceLocation par2, ItemStack stack) {
        this.textureSng = par1;
        this.textureDbl = par2;
        this.name = par0;
        this.crfItem = stack;
    }

    private static class ChestTypeList
            extends HashMap<String, ChestType> {
        private ChestTypeList() {
            super();
        }

        @Override
        public ChestType remove(Object key) {
            throw new UnsupportedOperationException("You can not remove any chest types!");
        }

        @Override
        public ChestType put(String key, ChestType value) {
            if( this.containsKey(key) ) {
                throw new UnsupportedOperationException(String.format("Duplicate chest type! Tried to add %s with the ID of %s!", value.name, key));
            } else {
                return super.put(key, value);
            }
        }

        @Override
        public void putAll(Map<? extends String, ? extends ChestType> m) {
            for( Entry<? extends String, ? extends ChestType> entry : m.entrySet() ) {
                if( this.containsKey(entry.getKey()) ) {
                    throw new UnsupportedOperationException(String.format("Duplicate chest type! Tried to add %s with the ID of %s!", entry.getValue().name, entry.getKey()));
                }
            }

            super.putAll(m);
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("You can not remove any chest types!");
        }
    }
}
