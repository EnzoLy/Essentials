package net.eternalproyect.essentials.commands.inventory;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import net.eternalproyect.essentials.Locale;
import net.eternalproyect.essentials.profile.Profile;
import net.eternalproyect.essentials.profile.inventory.ViewPlayerMenu;
import org.bukkit.entity.Player;

@CommandAlias("invsee")
@CommandPermission("eternal.staff.command.invsee")
public class InvseeCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    public void invsee(Player player, OnlinePlayer onlinePlayer){
        Player target = onlinePlayer.getPlayer();
        Profile playerProfile = Profile.getByUuid(player.getUniqueId());

        if (target == null) {
            player.sendMessage(Locale.PLAYER_NOT_FOUND.format(playerProfile.getLocale()));
            return;
        }

        new ViewPlayerMenu(target).openMenu(player);
    }

}
