package net.eternalproyect.essentials.chat.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.eternalproyect.essentials.Essentials;
import net.eternalproyect.essentials.EssentialsAPI;
import net.eternalproyect.essentials.Locale;
import net.eternalproyect.essentials.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("clearchat|cc")
public class ClearChatCommand extends BaseCommand {

	@Default
	@CommandPermission("eternal.staff.command.clearchat")
	public void execute(CommandSender sender) {
		String[] strings = new String[101];

		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.hasPermission("eternal.staff")) {
				if (Essentials.getInstance().getConfiguration().getBoolean("chat.clear_chat_for_staff")) {
					player.sendMessage(strings);
				}
			} else {
				player.sendMessage(strings);
			}
		}

		String senderName;

		if (sender instanceof Player) {
			senderName = EssentialsAPI.getColorOfPlayer(((Player)sender)) + sender.getName();
		} else {
			senderName = ChatColor.DARK_RED + "Console";
		}

		Bukkit.getOnlinePlayers().forEach(other -> {
			Profile profile = Profile.getProfiles().get(other.getUniqueId());
			other.sendMessage(Locale.CLEAR_CHAT_BROADCAST.format(profile.getLocale(), senderName));
		});
	}

}
