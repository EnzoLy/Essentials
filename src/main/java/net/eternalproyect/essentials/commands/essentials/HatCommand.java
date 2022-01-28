package net.eternalproyect.essentials.commands.essentials;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.qrakn.phoenix.lang.file.language.LanguageConfigurationFileLocale;
import net.eternalproyect.essentials.Locale;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@CommandAlias("hat")
@CommandPermission("eternal.staff.command.hat")
public class HatCommand extends BaseCommand {

    @Default
    public void hat(Player player){
        if(player.getInventory().getItemInMainHand().getType() == Material.AIR){
            return;
        }

        player.getInventory().setHelmet(player.getInventory().getItemInMainHand());
    }

    @Default
    public void hat(Player player, OnlinePlayer onlinePlayer){

        Player target = onlinePlayer.getPlayer();

        if (target == null) {
            player.sendMessage(Locale.PLAYER_NOT_FOUND.format(LanguageConfigurationFileLocale.ENGLISH));
            return;
        }

        if(player.getInventory().getItemInMainHand().getType() == Material.AIR){
            return;
        }

        target.getInventory().setHelmet(player.getInventory().getItemInMainHand());
    }

}
