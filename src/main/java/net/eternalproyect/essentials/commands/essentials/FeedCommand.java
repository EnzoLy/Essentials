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

@CommandAlias("feed|food")
@CommandPermission("eternal.feed")
public class FeedCommand extends BaseCommand {

    @Default
    public void feed(Player player){
        player.setFoodLevel(20);
        player.setSaturation(10);
        player.updateInventory();
        player.sendMessage(CC.GOLD + "You have been fed.");
    }

    @Default
    @CommandCompletion("@players")
    public void feed(Player player, OnlinePlayer onlinePlayer){
        Player target = onlinePlayer.getPlayer();

        Profile playerProfile = Profile.getByUuid(player.getUniqueId());


        if (target == null) {
            player.sendMessage(Locale.PLAYER_NOT_FOUND.format(playerProfile.getLocale()));
            return;
        }

        target.setFoodLevel(20);
        target.setSaturation(10);
        target.updateInventory();
        target.sendMessage(CC.GOLD + "You have been fed by " + player.getName() + ".");
    }

}
