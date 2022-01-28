package net.eternalproyect.essentials.commands.inventory;

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

@CommandAlias("copyinv")
@CommandPermission("eternal.staff.command.copyinv")
public class CopyInventoryCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    public void copyInv(Player player, OnlinePlayer onlinePlayer){
        Player target = onlinePlayer.getPlayer();
        Profile playerProfile = Profile.getByUuid(player.getUniqueId());

        if (target == null) {
            player.sendMessage(Locale.PLAYER_NOT_FOUND.format(playerProfile.getLocale()));
            return;
        }

        player.getInventory().setContents(target.getInventory().getContents());
        player.getInventory().setArmorContents(target.getInventory().getArmorContents());
        player.sendMessage(CC.YELLOW + "You have copied the inventory of " + target.getName());
    }

}
