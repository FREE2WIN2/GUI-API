package net.wargearworld.GUI_API.Listener;


import net.wargearworld.GUI_API.GUI_API;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseEventListener implements Listener {
    @EventHandler
    public void onInvClose(InventoryCloseEvent event) {
        if (event.getReason() != InventoryCloseEvent.Reason.OPEN_NEW) {
            GUI_API.openedGUIs.remove(event.getPlayer().getUniqueId());
        }
    }
}
