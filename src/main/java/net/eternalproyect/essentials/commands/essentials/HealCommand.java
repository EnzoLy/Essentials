package net.eternalproyect.essentials.commands.essentials;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.qrakn.phoenix.lang.file.language.LanguageConfigurationFileLocale;
import net.eternalproyect.essentials.Locale;
import net.eternalproyect.essentials.util.CC;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("heal")
@CommandPermission("eternal.command.heal")
public class HealCommand extends BaseCommand {

	@Default
	public void execute(Player player) {
		player.setHealth(20.0);
		player.setFoodLevel(20);
		player.setSaturation(5.0F);
		player.updateInventory();
		player.sendMessage(CC.GOLD + "You healed yourself.");
	}

	@Default
	@CommandCompletion("@players")
	@CommandPermission("eternal.staff.command.heal.others")
	public void execute(CommandSender sender, OnlinePlayer onlinePlayer) {

		Player player = onlinePlayer.getPlayer();

		if (player == null) {
			sender.sendMessage(Locale.PLAYER_NOT_FOUND.format(LanguageConfigurationFileLocale.ENGLISH));
			return;
		}

		player.setHealth(20.0);
		player.setFoodLevel(20);
		player.setSaturation(5.0F);
		player.updateInventory();
		player.sendMessage(CC.GOLD + "You have been healed by " + sender.getName());
	}

}
