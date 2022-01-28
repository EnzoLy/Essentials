package net.eternalproyect.essentials.commands.teleport;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import net.eternalproyect.essentials.Locale;
import net.eternalproyect.essentials.profile.Profile;
import org.bukkit.entity.Player;

@CommandAlias("tphere|teleporthere")
@CommandPermission("eternal.staff.command.tphere")
public class TeleportHere extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    public void tphere(Player player, OnlinePlayer onlinePlayer){
        Player target = onlinePlayer.getPlayer();
        Profile playerProfile = Profile.getByUuid(player.getUniqueId());

        if (target == null) {
            player.sendMessage(Locale.PLAYER_NOT_FOUND.format(playerProfile.getLocale()));
            return;
        }

        Profile targetProfile = Profile.getByUuid(target.getUniqueId());

        target.teleport(player);
        targetProfile.setLastLocation(target.getLocation());
    }

}
