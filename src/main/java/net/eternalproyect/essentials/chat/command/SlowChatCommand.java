package net.eternalproyect.essentials.chat.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
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

@CommandAlias("slowchat")
public class SlowChatCommand extends BaseCommand {

	@Default
	@CommandPermission("eternal.staff.slowchat")
	public void execute(CommandSender sender) {
		Essentials.getInstance().getChat().togglePublicChatDelay();

		String senderName;

		if (sender instanceof Player) {
			senderName = EssentialsAPI.getColorOfPlayer(((Player)sender)) + sender.getName();
		} else {
			senderName = ChatColor.DARK_RED + "Console";
		}

		String context = Essentials.getInstance().getChat().getDelayTime() == 1 ? "" : "s";

		if (Essentials.getInstance().getChat().isPublicChatDelayed()) {
			Bukkit.getOnlinePlayers().forEach(other -> {
				Profile profile = Profile.getProfiles().get(other.getUniqueId());
				other.sendMessage(Locale.DELAY_CHAT_ENABLED_BROADCAST.format(profile.getLocale(), senderName,
						Essentials.getInstance().getChat().getDelayTime(), context));
			});
		} else {
			Bukkit.getOnlinePlayers().forEach(other -> {
				Profile profile = Profile.getProfiles().get(other.getUniqueId());
				other.sendMessage(Locale.DELAY_CHAT_DISABLED_BROADCAST.format(profile.getLocale(), senderName));
			});
		}
	}

	@Default
	@CommandPermission("zoot.staff.slowchat")
	@CommandCompletion("@range:1-60")
	public void execute(CommandSender sender, Integer seconds) {
		if (seconds < 0 || seconds > 60) {
			sender.sendMessage(ChatColor.RED + "A delay can only be between 1-60 seconds.");
			return;
		}

		String context = seconds == 1 ? "" : "s";

		sender.sendMessage(ChatColor.GREEN + "You have updated the chat delay to " + seconds + " second" + context + ".");
		Essentials.getInstance().getChat().setDelayTime(seconds);
	}

}
