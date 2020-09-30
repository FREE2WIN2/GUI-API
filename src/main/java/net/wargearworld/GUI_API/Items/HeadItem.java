package net.wargearworld.GUI_API.Items;

import net.wargearworld.GUI_API.GUI.ArgumentList;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Map;

public class HeadItem extends Item{
    private String owner;
    ItemStack itemStack;
    public HeadItem(Material material, String name, int amount) {
        super(material, name, amount);
    }

    public void setOwner(String owner){
        this.owner = owner;
    }

    @SuppressWarnings("deprecation")
    @Override
    public ItemStack build() {
        itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        meta.setLore(lore);
        for(ItemFlag flag:itemFlags){
            meta.addItemFlags(flag);
        }
        meta.setDisplayName(name);
        meta.setOwner(owner);
        itemStack.setItemMeta(meta);
        for(Map.Entry<Enchantment, Integer> ench:enchantments.entrySet()){
            itemStack.addUnsafeEnchantment(ench.getKey(),ench.getValue());
        }
        itemStack.setAmount(amount);
        return itemStack;
    }

    @Override
    public ItemStack getItemStack() {
        return null;
    }
}
