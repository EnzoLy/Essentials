package net.eternalproyect.essentials.commands.inventory;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.eternalproyect.essentials.util.CC;
import org.bukkit.entity.Player;

@CommandAlias("more")
@CommandPermission("eternal.staff.command.more")
public class MoreCommand extends BaseCommand {

	@Default
	public void execute(Player player) {
		if (player.getItemInHand() == null) {
			player.sendMessage(CC.RED + "There is nothing in your hand.");
			return;
		}

		player.getItemInHand().setAmount(64);
		player.updateInventory();
		player.sendMessage(CC.GREEN + "You gave yourself more of the item in your hand.");
	}

	@Default
	@CommandCompletion("@range:1-64")
	public void execute(Player player, Integer amount) {
		if (player.getItemInHand() == null) {
			player.sendMessage(CC.RED + "There is nothing in your hand.");
			return;
		}

		player.getItemInHand().setAmount(amount);
		player.updateInventory();
		player.sendMessage(CC.GREEN + "You gave yourself more of the item in your hand.");
	}

}
