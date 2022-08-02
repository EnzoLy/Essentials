package net.eternalproyect.essentials.profile.option.menu;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import net.eternalproyect.essentials.util.CC;
import net.eternalproyect.essentials.util.ItemBuilder;
import net.eternalproyect.essentials.util.TextSplitter;
import net.eternalproyect.essentials.util.menu.Button;
import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public abstract class ProfileOptionButton extends Button {

	@Override
	public ItemStack getButtonItem(Player player) {
		ItemBuilder itemBuilder = new ItemBuilder(isEnabled(player) ? getEnabledItem(player) : getDisabledItem(player));

		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.addAll(TextSplitter.split(40, getDescription(player), CC.GRAY, " "));
		lore.add("");
		lore.add((isEnabled(player) ? CC.BLUE + StringEscapeUtils.unescapeJava(" » ") : "    ") + "&e" + getEnabledOption(player));
		lore.add((!isEnabled(player) ? CC.BLUE + StringEscapeUtils.unescapeJava(" » ") : "    ") + "&e" + getDisabledOption(player));
		lore.add("");
		lore.add("&eClick to toggle this option.");

		return itemBuilder.name(getOptionName(player))
		                  .lore(lore)
		                  .build();
	}

	public abstract ItemStack getEnabledItem(Player player);

	public abstract ItemStack getDisabledItem(Player player);

	public abstract String getOptionName(Player player);

	public abstract String getDescription(Player player);

	public abstract String getEnabledOption(Player player);

	public abstract String getDisabledOption(Player player);

	public abstract boolean isEnabled(Player player);

	@Override
	public boolean shouldUpdate(Player player, ClickType clickType) {
		return true;
	}

}
