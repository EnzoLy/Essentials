package net.eternalproyect.essentials.commands.teleport;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import net.eternalproyect.essentials.Locale;
import net.eternalproyect.essentials.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

    @CommandAlias("tpall|teleportall")
    @CommandPermission("eternal.staff.command.tpall")
    public class TeleportAll extends BaseCommand {

        @Default
        @CommandCompletion("@players")
        public void tpall(Player player, OnlinePlayer onlinePlayer) {

            Profile playerProfile = Profile.getByUuid(player.getUniqueId());
            if (Bukkit.getServer().getOnlinePlayers().isEmpty()) {
                player.sendMessage(Locale.NO_PLAYERS.format(playerProfile.getLocale()));
                return;
            }

            for (Player all : Bukkit.getServer().getOnlinePlayers()) {
                Profile targetProfile = Profile.getByUuid(all.getUniqueId());
                all.teleport(player);
                targetProfile.setLastLocation(all.getLocation());
            }
        }
    }