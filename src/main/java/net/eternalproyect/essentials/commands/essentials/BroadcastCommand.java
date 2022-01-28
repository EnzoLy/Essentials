package net.eternalproyect.essentials.commands.essentials;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.eternalproyect.essentials.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

@CommandAlias("broadcast|bc")
@CommandPermission("eternal.staff.command.broadcast")
public class BroadcastCommand extends BaseCommand {

	@Default
	public void execute(CommandSender sender, String broadcast) {
		String message = broadcast.replaceAll("(&([a-f0-9l-or]))", "\u00A7$2");
		Bukkit.broadcastMessage(CC.translate("&6[Broadcast] &r" + message));
	}

}
