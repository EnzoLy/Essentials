package net.eternalproyect.essentials.commands.essentials;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.eternalproyect.essentials.Essentials;
import net.eternalproyect.essentials.util.BukkitReflection;
import net.eternalproyect.essentials.util.CC;
import org.bukkit.command.CommandSender;

@CommandAlias("setslots")
@CommandPermission("eternal.staff.command.setslots")
public class SetSlotsCommand extends BaseCommand {

	@Default
	@CommandCompletion("@range:1-500")
	public void execute(CommandSender sender, int slots) {
		BukkitReflection.setMaxPlayers(Essentials.getInstance().getServer(), slots);
		sender.sendMessage(CC.GOLD + "You set the max slots to " + slots + ".");
	}

}
