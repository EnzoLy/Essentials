package net.eternalproyect.essentials.commands.essentials;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import net.eternalproyect.essentials.Locale;
import net.eternalproyect.essentials.profile.Profile;
import net.eternalproyect.essentials.util.CC;
import org.bukkit.entity.Player;

@CommandAlias("xp|exp")
@CommandPermission("eternal.exp")
public class ExpCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    public void exp(Player player, OnlinePlayer onlinePlayer, Integer number){

        Player target = onlinePlayer.getPlayer();
        Profile playerProfile = Profile.getByUuid(player.getUniqueId());

        if (target == null) {
            player.sendMessage(Locale.PLAYER_NOT_FOUND.format(playerProfile.getLocale()));
            return;
        }
        target.giveExp(number);
    }
}