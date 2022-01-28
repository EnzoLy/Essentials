package net.eternalproyect.essentials.commands.essentials;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("sudoall")
@CommandPermission("eternal.staff.command.sudo")
public class SudoAllCommand extends BaseCommand {

	@Default
	public void execute(CommandSender sender, String chat) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (!player.equals(sender)) {
				continue;
			}

			player.chat(chat);
		}

		sender.sendMessage(ChatColor.GREEN + "Forced all players to chat!");
	}

}
