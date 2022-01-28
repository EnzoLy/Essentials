package net.eternalproyect.essentials.commands.essentials;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.eternalproyect.essentials.util.CC;
import org.bukkit.entity.Player;

@CommandAlias("night")
public class NightCommand extends BaseCommand {

	@Default
	public void execute(Player player) {
		player.getWorld().setTime(18000L);
		player.sendMessage(CC.GREEN + "It's now night time.");
	}

}
