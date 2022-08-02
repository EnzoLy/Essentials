package net.eternalproyect.essentials.commands.essentials;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.qrakn.phoenix.lang.file.language.LanguageConfigurationFileLocale;
import net.eternalproyect.essentials.Locale;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("sudo")
@CommandPermission("eternal.staff.command.sudo")
public class SudoCommand extends BaseCommand {

	@Default
	@CommandCompletion("@players")
	public void execute(CommandSender sender, OnlinePlayer onlinePlayer, String chat) {

		Player target = onlinePlayer.getPlayer();

		if (target == null) {
			sender.sendMessage(Locale.PLAYER_NOT_FOUND.format(LanguageConfigurationFileLocale.ENGLISH));
			return;
		}

		target.chat(chat);
		sender.sendMessage(ChatColor.GREEN + "Forced target to chat!");
	}

}
