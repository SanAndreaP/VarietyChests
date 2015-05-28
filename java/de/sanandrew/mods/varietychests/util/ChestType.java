/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.util;

import de.sanandrew.core.manpack.util.EnumNbtTypes;
import de.sanandrew.core.manpack.util.helpers.SAPUtils;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChestType
{
    public static final ChestTypeList TYPES = new ChestTypeList();
    private static final List<ChestType> TYPE_LIST = new ArrayList<>();
    private static final List<String> NAME_LIST = new ArrayList<>();
    public static final ChestType NULL_TYPE = new ChestType("NULL_CHEST", new ResourceLocation("NULL_CHEST"), new ResourceLocation("NULL_CHEST"), null);
    public static final ChestType ORIGINAL = registerChestType("original", new ResourceLocation("minecraft", "textures/entity/chest/normal.png"),
                                                               new ResourceLocation("minecraft", "textures/entity/chest/normal_double.png"),
                                                               new ItemStack(Blocks.planks, 1, 0));

    public static ChestType registerChestType(String name, ResourceLocation textureSingle, ResourceLocation textureDouble, ItemStack craftingItem) {
        ChestType newType = new ChestType(name, textureSingle, textureDouble, craftingItem);
        TYPES.put(name, newType);
        TYPE_LIST.add(newType);
        NAME_LIST.add(name);
        return newType;
    }

    public static ChestType getType(String name) {
        ChestType type = TYPES.get(name);
        if( type == null ) {
            return NULL_TYPE;
        }

        return type;
    }

    public static String[] getTypeNames() {
        return NAME_LIST.toArray(new String[NAME_LIST.size()]);
    }

    public static ChestType[] getTypes() {
        return TYPE_LIST.toArray(new ChestType[TYPE_LIST.size()]);
    }

    public static ChestType getType(ItemStack stack) {
        if( !stack.hasTagCompound() || !stack.getTagCompound().hasKey(VarietyChests.NBT_CHEST_TYPE, EnumNbtTypes.NBT_STRING.ordinal()) ) {
            return NULL_TYPE;
        }

        return getType(stack.getTagCompound().getString(VarietyChests.NBT_CHEST_TYPE));
    }

    public static ChestType getTypeFromCraftingMaterial(ItemStack stack) {
        for( ChestType type : TYPE_LIST ) {
            if( SAPUtils.areStacksEqual(stack, type.crfItem, false) ) {
                return type;
            }
        }

        return NULL_TYPE;
    }

    public static ItemStack getNewItemStackFromType(Block block, ChestType type, int count) {
        NBTTagCompound nbt = new NBTTagCompound();
        ItemStack stack = new ItemStack(Item.getItemFromBlock(block), count);

        nbt.setString(VarietyChests.NBT_CHEST_TYPE, type.name);
        stack.setTagCompound(nbt);

        return stack;
    }

    public static void registerDefaultTypes() {
        ChestType.registerChestType("spruce", new ResourceLocation(VarietyChests.MOD_ID, "textures/entity/chest/spruce.png"),
                                    new ResourceLocation(VarietyChests.MOD_ID, "textures/entity/chest/spruce_double.png"),
                                    new ItemStack(Blocks.planks, 1, 1)
        );
        ChestType.registerChestType("birch", new ResourceLocation(VarietyChests.MOD_ID, "textures/entity/chest/birch.png"),
                                    new ResourceLocation(VarietyChests.MOD_ID, "textures/entity/chest/birch_double.png"),
                                    new ItemStack(Blocks.planks, 1, 2)
        );
        ChestType.registerChestType("jungle", new ResourceLocation(VarietyChests.MOD_ID, "textures/entity/chest/jungle.png"),
                                    new ResourceLocation(VarietyChests.MOD_ID, "textures/entity/chest/jungle_double.png"),
                                    new ItemStack(Blocks.planks, 1, 3)
        );
        ChestType.registerChestType("acacia", new ResourceLocation(VarietyChests.MOD_ID, "textures/entity/chest/acacia.png"),
                                    new ResourceLocation(VarietyChests.MOD_ID, "textures/entity/chest/acacia_double.png"),
                                    new ItemStack(Blocks.planks, 1, 4)
        );
        ChestType.registerChestType("darkoak", new ResourceLocation(VarietyChests.MOD_ID, "textures/entity/chest/darkoak.png"),
                                    new ResourceLocation(VarietyChests.MOD_ID, "textures/entity/chest/darkoak_double.png"),
                                    new ItemStack(Blocks.planks, 1, 5)
        );
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

    private static class ChestTypeList extends HashMap<String, ChestType>
    {
        private static final long serialVersionUID = -4333757139071524471L;

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
                    throw new UnsupportedOperationException(String.format("Duplicate chest type! Tried to add %s with the ID of %s!", entry.getValue().name,
                                                                          entry.getKey()));
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
