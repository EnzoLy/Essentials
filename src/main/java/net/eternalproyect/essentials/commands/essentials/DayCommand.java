package net.eternalproyect.essentials.commands.essentials;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.eternalproyect.essentials.util.CC;
import org.bukkit.entity.Player;

@CommandAlias("day")
@CommandPermission("eternal.command.day")
public class DayCommand extends BaseCommand {

	@Default
	public void execute(Player player) {
		player.getWorld().setTime(6000L);
		player.sendMessage(CC.GREEN + "It's now day time.");
	}

}
