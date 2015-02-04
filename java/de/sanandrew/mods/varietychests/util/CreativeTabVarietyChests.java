/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.varietychests.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.sanandrew.mods.claysoldiers.util.RegistryItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabVarietyChests
        extends CreativeTabs
{
    private ItemStack[] tabIcons;

    public CreativeTabVarietyChests() {
        super(VarietyChests.MOD_ID + ":vcm_tab");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {
        if( this.tabIcons == null ) {
            String[] types = ChestType.getTypeNames();
            this.tabIcons = new ItemStack[types.length];
            for( int i = 0; i < types.length; i++ ) {
                this.tabIcons[i] = ChestType.getNewItemStackFromType(VarietyChests.customChest, types[i], 1);
            }
        }

        return this.tabIcons[(int) (System.currentTimeMillis() / 4250) % this.tabIcons.length];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return RegistryItems.dollSoldier;
    }
}
