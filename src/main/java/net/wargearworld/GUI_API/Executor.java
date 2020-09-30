package net.wargearworld.GUI_API;

public interface Executor<S> {
    /**
     *  Syntax: s->{return Cancelled}
     * @param arg The ArgumentList
     * @return If the InventoryClickEvent should be Cancelled by clicking
     */
    boolean call(S arg);
}
