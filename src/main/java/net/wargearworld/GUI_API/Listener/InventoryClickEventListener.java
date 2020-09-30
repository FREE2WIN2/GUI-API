package net.wargearworld.GUI_API.Listener;

import net.wargearworld.GUI_API.GUI.ArgumentList;
import net.wargearworld.GUI_API.GUI_API;
import net.wargearworld.GUI_API.Items.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickEventListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getView().getTitle() == null || event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta() || event.getClickedInventory() != event.getView().getTopInventory())
            return;

        ArgumentList list = new ArgumentList(event);
        Item clicked = GUI_API.openedGUIs.get(list.getPlayer().getUniqueId()).getItem(list);
        if (clicked != null) {
            event.setCancelled(clicked.execute(list));
        } else {
            event.setCancelled(true);
        }

    }
}
