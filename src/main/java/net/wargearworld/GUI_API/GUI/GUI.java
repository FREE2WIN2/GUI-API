package net.wargearworld.GUI_API.GUI;

import com.sun.jdi.connect.Connector;
import net.wargearworld.GUI_API.Executor;
import net.wargearworld.GUI_API.GUI_API;
import net.wargearworld.GUI_API.Items.DefaultItem;
import net.wargearworld.GUI_API.Items.Item;
import net.wargearworld.GUI_API.Items.ItemBuilder;
import net.wargearworld.GUI_API.Items.ItemType;
import net.wargearworld.StringGetter.Language;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class GUI {
    HashMap<Integer, HashMap<Predicate<Player>, Item>> items = new HashMap<>();
    private int size;
    private String title;
    private ItemStack filler;
    private GUIType type;

    public GUI(GUIType type, int size, String title) {
        this.type = type;
        this.size = size;
        this.title = title;
    }

    public GUI(GUIType type, int size, String title, Item filler) {
        this(type, size, title);
        this.filler = filler.getItemStack();
    }

    /**
     * @param index     The index in which the Item will be placed. Starts with 0 to size - 1
     * @param predicate The Requirement for setting this item into the GUI.
     * @param item      The Item to place if the predicate is true.
     */
    public void setItem(int index, Predicate<Player> predicate, Item item) {
        HashMap<Predicate<Player>, Item> entry = new HashMap<Predicate<Player>, Item>();
        entry.put(predicate, item);
        items.put(index, entry);
    }

    /**
     * @param index The index in which the Item will be placed. Starts with 0 to size - 1
     * @param item  The Item to place (predicate = true).
     */
    public void setItem(int index, Item item) {
        setItem(index, s -> {
            return true;
        }, item);
    }

    /**
     * Generates an Item for add in one Line. But no Flags, Enchantsments, Lore possible. For that create first an Item with that and user setItem(index,predicate,item)
     *
     * @param index     The index in which the Item will be placed. Starts with 0 to size - 1
     * @param predicate The Requirement for setting this item into the GUI.
     * @param executor  The Method whioch hould be executed by CLicking the Item.
     * @param amount    The amount of the Item
     * @param mat       The Material of the Item
     * @param lang      The Language of the Player
     * @param nameKey   The name of the key in the lang file
     * @param args      The args to replace for name
     */
    public void setItem(int index, Predicate<Player> predicate, Executor<ArgumentList> executor, int amount, Material mat, Language lang, String nameKey, String... args) {
        Item item = ItemBuilder.build(executor, mat, amount, ItemType.DEFAULT, lang, nameKey, args);
        setItem(index, predicate, item);
    }

    /**
     * @param filler The Filler Itemstack which will be placed everywhere no Item is declared.
     */
    public void setFiller(Item filler) {
        this.filler = filler.getItemStack();
    }

    /**
     * @param p opens this GUI to an Player
     */
    public void open(Player p) {
        GUI_API.openedGUIs.put(p.getUniqueId(), this);
        Inventory inv = Bukkit.createInventory(null, size, title);

        for (int i = 0; i < size; i++) {
            ItemStack is = getItemStack(p, i);
            if(is == null)
                continue;
            inv.setItem(i, is);
        }
        System.out.println("test");
    p.openInventory(inv);
    }

    /**
     * @param s Argumentlist of the ClickEvent
     * @return THe clicked Item
     */
    public Item getItem(ArgumentList s) {
        for (Map.Entry<Predicate<Player>, Item> entry : items.get(s.getClickedIndex()).entrySet()) {
            if (entry.getKey().test(s.getPlayer())) {
                return entry.getValue();
            }
        }
        return null;
    }

    private ItemStack getItemStack(Player p, int index) {
        HashMap<Predicate<Player>,Item> map = items.get(index);
        if(map == null)
            return filler;
        for (Map.Entry<Predicate<Player>, Item> entry : map.entrySet()) {
            if (entry.getKey().test(p)) {
                return entry.getValue().getItemStack();
            }
        }
        return filler;
    }
}
