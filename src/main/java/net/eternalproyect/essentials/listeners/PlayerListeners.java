package net.eternalproyect.essentials.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        if(player.hasPermission("eternal.keepinventory")){
            event.setKeepInventory(true);
            event.setKeepLevel(true);
        }
    }

}
