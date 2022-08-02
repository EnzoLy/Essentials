package net.eternalproyect.essentials.commands.inventory;

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
import org.bukkit.inventory.ItemStack;

@CommandAlias("clearinv|clear|ci")
@CommandPermission("eternal.staff.command.clearinv")
public class ClearCommand extends BaseCommand {

	@Default
	public void execute(Player player) {
		player.getInventory().setContents(new ItemStack[36]);
		player.getInventory().setArmorContents(new ItemStack[4]);
		player.updateInventory();
		player.sendMessage(CC.GOLD + "You cleared your inventory.");
	}

	@Default
	@CommandCompletion("@players")
	public void execute(CommandSender sender, OnlinePlayer onlinePlayer) {
		Player player = onlinePlayer.getPlayer();

		if (!player.isOnline()) {
			sender.sendMessage(Locale.PLAYER_NOT_FOUND.format(LanguageConfigurationFileLocale.ENGLISH));
			return;
		}
		player.getInventory().setContents(new ItemStack[36]);
		player.getInventory().setArmorContents(new ItemStack[4]);
		player.updateInventory();
		player.sendMessage(CC.GOLD + "Your inventory has been cleared by " + sender.getName());
	}

}
