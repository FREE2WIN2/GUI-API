package net.wargearworld.GUI_API.Items;

import net.wargearworld.GUI_API.Executor;
import net.wargearworld.GUI_API.GUI.ArgumentList;
import net.wargearworld.GUI_API.GUI_API;
import net.wargearworld.StringGetter.Language;
import org.bukkit.Material;

public class ItemBuilder {


    public static Item build(Executor<ArgumentList> executor, Material mat, int amount, ItemType type, Language lang, String nameKey, String[] args) {
        String name = GUI_API.getString(lang,nameKey,args);
        switch (type){
            case HEAD:
                return new HeadItem(mat,name,amount).setExecutor(executor);
            case DEFAULT:
                return new DefaultItem(mat,name,amount).setExecutor(executor);
        }
        return null;
    }
    public static Item build(Executor<ArgumentList> executor, Material mat, int amount, ItemType type, String name) {
        switch (type){
            case HEAD:
                return new HeadItem(mat,name,amount).setExecutor(executor);
            case DEFAULT:
                return new DefaultItem(mat,name,amount).setExecutor(executor);
        }
        return null;
    }
}
