package net.eternalproyect.essentials.profile.inventory;

import lombok.AllArgsConstructor;
import net.eternalproyect.essentials.util.*;
import net.eternalproyect.essentials.util.menu.Button;
import net.eternalproyect.essentials.util.menu.Menu;
import net.eternalproyect.essentials.util.menu.button.DisplayButton;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.*;

public class ViewPlayerMenu extends Menu {

	private Player target;

	public ViewPlayerMenu(Player target) {
		this.target = target;
		setAutoUpdate(true);
	}

	@Override
	public String getTitle(Player player) {
		return CC.GOLD + this.target.getName() + "'s Inventory";
	}

	@Override
	public Map<Integer, Button> getButtons(Player player) {
		final Map<Integer, Button> buttons = new HashMap<>();

		if (player == null) {
			return buttons;
		}

		final ItemStack[] fixedContents = InventoryUtil.fixInventoryOrder(this.target.getInventory().getContents());

		for (int i = 0; i < fixedContents.length; i++) {
			final ItemStack itemStack = fixedContents[i];

			if (itemStack == null || itemStack.getType() == Material.AIR) {
				continue;
			}

			buttons.put(i, new DisplayButton(itemStack, true));
		}
		
		for (int i = 36; i < 46; i++) {
			final ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);

			buttons.put(i, new DisplayButton(itemStack, true));
		}

		for (int i = 0; i < this.target.getInventory().getArmorContents().length; i++) {
			ItemStack itemStack = this.target.getInventory().getArmorContents()[i];

			if (itemStack != null && itemStack.getType() != Material.AIR) {
				buttons.put(48 - i, new DisplayButton(itemStack, true));
			}
		}

		buttons.put(
				49,
				new DisplayButton(target.getInventory().getItemInOffHand(), true)
		);

		int pos = 50;
		buttons.put(
				pos++,
				new LocationButton()
		);
		buttons.put(
				pos++,
				new HealthButton(this.target.getHealth() == 0 ? 0 : (int) Math.round(this.target.getHealth() / 2))
		);
		buttons.put(pos++, new HungerButton(this.target.getFoodLevel()));
		buttons.put(pos, new EffectsButton(this.target.getActivePotionEffects()));

		return buttons;
	}

	@Override
	public boolean isAutoUpdate() {
		return true;
	}

	@AllArgsConstructor
	private class HealthButton extends Button {

		private int health;

		@Override
		public ItemStack getButtonItem(Player player) {
			return new ItemBuilder(Material.MELON)
					.name(CC.YELLOW + CC.BOLD + "Health: " + CC.PINK + this.health + "/10 " + CC.UNICODE_HEART)
					.amount(this.health == 0 ? 1 : this.health)
					.build();
		}

	}

	@AllArgsConstructor
	private class HungerButton extends Button {

		private int hunger;

		@Override
		public ItemStack getButtonItem(Player player) {
			return new ItemBuilder(Material.COOKED_BEEF)
					.name(CC.YELLOW + CC.BOLD + "Hunger: " + CC.PINK + this.hunger + "/20")
					.amount(this.hunger == 0 ? 1 : this.hunger)
					.build();
		}

	}

	@AllArgsConstructor
	private class LocationButton extends Button {

		@Override
		public ItemStack getButtonItem(Player player) {
			return new ItemBuilder(Material.PAPER)
					.name(CC.YELLOW + CC.BOLD + "World: " + CC.GREEN + player.getWorld().getName())
					.lore(CC.YELLOW + "X: " + player.getLocation().getBlockX())
					.lore(CC.YELLOW + "Y: " + player.getLocation().getBlockY())
					.lore(CC.YELLOW + "Z: " + player.getLocation().getBlockZ())
					.build();
		}

	}

	@AllArgsConstructor
	private class EffectsButton extends Button {

		private Collection<PotionEffect> effects;

		@Override
		public ItemStack getButtonItem(Player player) {
			final ItemBuilder
					builder = new ItemBuilder(Material.POTION).name(CC.YELLOW + CC.BOLD + "Potion Effects");

			if (this.effects.isEmpty()) {
				builder.lore(CC.GRAY + "No effects");
			} else {
				final List<String> lore = new ArrayList<>();

				this.effects.forEach(effect -> {
					final String name = PotionUtil.getName(effect.getType()) + " " + (effect.getAmplifier() + 1);
					final String duration = " (" + TimeUtil.millisToTimer((effect.getDuration() / 20) * 1000) + ")";

					lore.add(CC.PINK + name + duration);
				});

				builder.lore(lore);
			}

			return builder.build();
		}

	}

}
