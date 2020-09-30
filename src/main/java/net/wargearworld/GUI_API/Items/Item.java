package net.wargearworld.GUI_API.Items;

import net.wargearworld.GUI_API.Executor;
import net.wargearworld.GUI_API.GUI.ArgumentList;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public abstract class Item {

    protected Executor<ArgumentList> executor;
    protected String name;
    protected List<String> lore = new ArrayList<>();
    protected HashMap<Enchantment,Integer> enchantments = new HashMap<>();
    protected List<ItemFlag> itemFlags = new ArrayList<>();
    protected Material material;
    protected int amount;
    public Item(Material material, String name, int amount) {
        this.name = name;
        this.material = material;
        this.amount = amount;
    }

    public Item(Material material, String name, int amount,Executor<ArgumentList> executor) {
        this.name = name;
        this.material = material;
        this.executor = executor;
        this.amount = amount;
    }

    public abstract ItemStack build();
    public abstract ItemStack getItemStack();


    public boolean execute(ArgumentList s){
        if(executor == null)
            return false;
        return executor.call(s);
    }

    public Item addEnchantment(Enchantment enchantment, int level) {
        enchantments.put(enchantment,level);
        return this;
    }

    public Item setExecutor(Executor<ArgumentList> executor) {
        this.executor = executor;
        return this;
    }

    public Item setItemFlags(List<ItemFlag> itemFlags) {
        this.itemFlags = itemFlags;
        return this;
    }
    public Item addItemFLags(ItemFlag... itemFlags) {
        this.itemFlags = Arrays.asList(itemFlags);
        return this;
    }

    public Item setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public Item addLore(String lore) {
        if(this.lore == null)
            this.lore = new ArrayList<>();
        this.lore.add(lore);
        return this;
    }

    public Item setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }


}
