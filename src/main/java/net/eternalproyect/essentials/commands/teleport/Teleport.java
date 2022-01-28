package net.eternalproyect.essentials.commands.teleport;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import net.eternalproyect.essentials.Locale;
import net.eternalproyect.essentials.profile.Profile;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

@CommandAlias("teleport|tp")
@CommandPermission("eternal.command.teleport")
public class Teleport extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    public void teleport(Player player, OnlinePlayer onlinePlayer){

        Player target = onlinePlayer.getPlayer();
        Profile playerProfile = Profile.getByUuid(player.getUniqueId());

        if (target == null) {
            player.sendMessage(Locale.PLAYER_NOT_FOUND.format(playerProfile.getLocale()));
            return;
        }

        player.teleport(target, PlayerTeleportEvent.TeleportCause.COMMAND);
        playerProfile.setLastLocation(player.getLocation());
    }

    @Default
    @CommandPermission("eternal.staff.command.teleport.coords")
    public void teleport(Player player, Double x, Double y, Double z){
        Profile playerProfile = Profile.getByUuid(player.getUniqueId());

        final Location targetALocation2 = player.getLocation();

        targetALocation2.setX(x);
        targetALocation2.setY(y);
        targetALocation2.setZ(z);
        player.teleport(targetALocation2, PlayerTeleportEvent.TeleportCause.COMMAND);
        playerProfile.setLastLocation(player.getLocation());
    }

    @Default
    @CommandCompletion("@players")
    @CommandPermission("eternal.staff.command.teleport.other")
    public void teleport(OnlinePlayer onlinePlayer, Player player){

        Player target = onlinePlayer.getPlayer();
        Profile playerProfile = Profile.getByUuid(player.getUniqueId());

        if (target == null) {
            player.sendMessage(Locale.PLAYER_NOT_FOUND.format(playerProfile.getLocale()));
            return;
        }
        Profile targetProfile = Profile.getByUuid(target.getUniqueId());

        target.teleport(player, PlayerTeleportEvent.TeleportCause.COMMAND);
        targetProfile.setLastLocation(target.getLocation());

    }

    @Default
    @CommandCompletion("@players")
    @CommandPermission("eternal.staff.command.teleport.coords.other")
    public void teleport(OnlinePlayer onlinePlayer, Player player, Double x, Double y, Double z){
        Profile playerProfile = Profile.getByUuid(player.getUniqueId());
        Player target = onlinePlayer.getPlayer();
        if (target == null) {
            player.sendMessage(Locale.PLAYER_NOT_FOUND.format(playerProfile.getLocale()));
            return;
        }
        Profile targetProfile = Profile.getByUuid(target.getUniqueId());

        final Location targetALocation2 = target.getLocation();

        targetALocation2.setX(x);
        targetALocation2.setY(y);
        targetALocation2.setZ(z);
        target.teleport(targetALocation2, PlayerTeleportEvent.TeleportCause.COMMAND);
        targetProfile.setLastLocation(target.getLocation());
    }

}
