package net.eternalproyect.essentials.commands.inventory;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.eternalproyect.essentials.util.CC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@CommandAlias("rename")
@CommandPermission("eternal.staff.command.rename")
public class RenameCommand extends BaseCommand {

	@Default
	public void execute(Player player, String name) {
		if (player.getItemInHand().getType() != Material.AIR) {
			ItemStack itemStack = player.getItemInHand();
			ItemMeta itemMeta = itemStack.getItemMeta();
			itemMeta.setDisplayName(CC.translate(name));
			itemStack.setItemMeta(itemMeta);

			player.updateInventory();
			player.sendMessage(CC.GREEN + "You renamed the item in your hand.");
		} else {
			player.sendMessage(CC.RED + "There is nothing in your hand.");
		}
	}

}
