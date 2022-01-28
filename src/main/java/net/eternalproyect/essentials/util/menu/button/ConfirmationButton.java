package net.eternalproyect.essentials.util.menu.button;

import lombok.AllArgsConstructor;
import net.eternalproyect.essentials.util.callback.TypeCallback;
import net.eternalproyect.essentials.util.menu.Button;
import net.eternalproyect.essentials.util.menu.Menu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@AllArgsConstructor
public class ConfirmationButton extends Button {

	private boolean confirm;
	private TypeCallback<Boolean> callback;
	private boolean closeAfterResponse;

	@Override
	public ItemStack getButtonItem(Player player) {
		ItemStack itemStack =  this.confirm ? new ItemStack(Material.GREEN_WOOL, 1) :
				new ItemStack(Material.RED_WOOL, 1);
		ItemMeta itemMeta = itemStack.getItemMeta();

		itemMeta.setDisplayName(this.confirm ? ChatColor.GREEN + "Confirm" : ChatColor.RED + "Cancel");
		itemStack.setItemMeta(itemMeta);

		return itemStack;
	}

	@Override
	public void clicked(Player player, ClickType clickType) {
		if (this.confirm) {
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 20f, 0.1f);
		} else {
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 20f, 0.1F);
		}

		if (this.closeAfterResponse) {
			Menu menu = Menu.currentlyOpenedMenus.get(player.getName());

			if (menu != null) {
				menu.setClosedByMenu(true);
			}

			player.closeInventory();
		}

		this.callback.callback(this.confirm);
	}

}
