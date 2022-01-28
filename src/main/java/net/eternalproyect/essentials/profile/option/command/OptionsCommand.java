package net.eternalproyect.essentials.profile.option.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.eternalproyect.essentials.profile.option.menu.ProfileOptionsMenu;
import org.bukkit.entity.Player;

@CommandAlias("options|settings")
public class OptionsCommand extends BaseCommand {

	@Default
	public void execute(Player player) {
		new ProfileOptionsMenu().openMenu(player);
	}

}
