
package net.wargearworld.GUI_API;

import net.wargearworld.GUI_API.GUI.GUI;
import net.wargearworld.GUI_API.Listener.InventoryClickEventListener;
import net.wargearworld.GUI_API.Listener.InventoryCloseEventListener;
import net.wargearworld.StringGetter.IStringGetter;
import net.wargearworld.StringGetter.Language;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;

public class GUI_API {

    public static HashMap<UUID,GUI> openedGUIs;
    private static IStringGetter stringGetter;
    public GUI_API(Plugin plugin, IStringGetter stringGetter) {
        openedGUIs = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(new InventoryClickEventListener(),plugin);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseEventListener(),plugin);
        this.stringGetter = stringGetter;
    }

       public static String getString(Language lang, String key, String... args){
        return stringGetter.getString(lang,key,args);
    }
}
