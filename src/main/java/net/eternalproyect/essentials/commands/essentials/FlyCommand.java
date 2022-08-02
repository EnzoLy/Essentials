package net.eternalproyect.essentials.commands.essentials;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import net.eternalproyect.essentials.Locale;
import net.eternalproyect.essentials.util.CC;
import net.eternalproyect.essentials.profile.Profile;
import org.bukkit.entity.Player;


@CommandAlias("fly")
@CommandPermission("eternal.command.fly")
public class FlyCommand extends BaseCommand {

    @Default
    public void fly(Player player){
        if(player.getAllowFlight()){
            player.setAllowFlight(false);
			player.sendMessage(CC.YELLOW + "You are no longer flying.");
        }else{
            player.setAllowFlight(true);
			player.sendMessage(CC.YELLOW + "You are now flying.");
        }
    }

    @Default
    @CommandCompletion("@players")
    public void fly(Player player, OnlinePlayer onlinePlayer){
        Player target = onlinePlayer.getPlayer();
        Profile playerProfile = Profile.getByUuid(player.getUniqueId());

        if (target == null) {
            player.sendMessage(Locale.PLAYER_NOT_FOUND.format(playerProfile.getLocale()));
            return;
        }
        if(target.getAllowFlight()){
            target.setAllowFlight(false);
			player.sendMessage(CC.YELLOW + "You are no longer flying.");
        }else{
            target.setAllowFlight(true);
			player.sendMessage(CC.YELLOW + "You are now flying.");
        }
    }

}
