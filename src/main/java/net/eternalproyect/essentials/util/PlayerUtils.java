package net.eternalproyect.essentials.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerUtils {

    public static List<Player> staffOnline(){
        return Bukkit.getOnlinePlayers()
                .stream()
                .filter(player -> player.hasPermission("eternal.staff"))
                .collect(Collectors.toList());
    }

}
