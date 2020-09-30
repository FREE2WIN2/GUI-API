package net.wargearworld.GUI_API.Items;

import net.wargearworld.GUI_API.GUI.ArgumentList;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class DefaultItem extends Item {

    ItemStack itemStack;
    public DefaultItem(Material material, String name, int amount) {
        super(material, name, amount);
    }

    @Override
    public ItemStack build() {
        itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(lore);
//        for(ItemFlag flag:itemFlags){
//            meta.addItemFlags(flag);
//        }
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
        for(Map.Entry<Enchantment, Integer> ench:enchantments.entrySet()){
            itemStack.addUnsafeEnchantment(ench.getKey(),ench.getValue());
        }
        itemStack.setAmount(amount);
        return itemStack;
    }

    @Override
    public ItemStack getItemStack() {
        if(itemStack == null)
            return build();
        return itemStack;
    }

}
