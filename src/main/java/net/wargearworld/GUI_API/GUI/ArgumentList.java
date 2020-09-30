package net.wargearworld.GUI_API.GUI;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ArgumentList {
    private ItemStack clicked;
    private String title;
    private Player player;
    private int clickedIndex;

    public ArgumentList(InventoryClickEvent event) {
        this.clicked = event.getCurrentItem();
        this.player = (Player) event.getWhoClicked();
        this.title = event.getView().getTitle();
        this.clickedIndex = event.getSlot();
    }

    public int getClickedIndex() {
        return clickedIndex;
    }

    public Player getPlayer() {
        return player;
    }

    public String getTitle() {
        return title;
    }

    public ItemStack getClicked() {
        return clicked;
    }
}
