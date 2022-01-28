package net.eternalproyect.essentials.profile.option.menu.button;

import net.eternalproyect.essentials.Locale;
import net.eternalproyect.essentials.profile.Profile;
import net.eternalproyect.essentials.profile.option.menu.ProfileOptionButton;
import net.eternalproyect.essentials.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class PrivateChatSoundsOptionButton extends ProfileOptionButton {

	@Override
	public String getOptionName(Player player) {
		Profile profile = Profile.getByUuid(player.getUniqueId());
		return Locale.OPTIONS_MENU_PRIVATE_MESSAGE_SOUND_NAME.format(profile.getLocale());
	}

	@Override
	public ItemStack getEnabledItem(Player player) {
		return new ItemBuilder(Material.NOTE_BLOCK).build();
	}

	@Override
	public ItemStack getDisabledItem(Player player) {
		return new ItemBuilder(Material.NOTE_BLOCK).build();
	}

	@Override
	public String getDescription(Player player) {
		Profile profile = Profile.getByUuid(player.getUniqueId());
		return Locale.OPTIONS_MENU_PRIVATE_MESSAGE_SOUND_DESCRIPTION.format(profile.getLocale());
	}

	@Override
	public String getEnabledOption(Player player) {
		Profile profile = Profile.getByUuid(player.getUniqueId());
		return Locale.OPTIONS_MENU_PRIVATE_MESSAGE_SOUND_ENABLE.format(profile.getLocale());
	}

	@Override
	public String getDisabledOption(Player player) {
		Profile profile = Profile.getByUuid(player.getUniqueId());
		return Locale.OPTIONS_MENU_PRIVATE_MESSAGE_SOUND_DISABLE.format(profile.getLocale());
	}

	@Override
	public boolean isEnabled(Player player) {
		return Profile.getProfiles().get(player.getUniqueId()).getOptions().playingMessageSounds();
	}

	@Override
	public void clicked(Player player, ClickType clickType) {
		Profile profile = Profile.getProfiles().get(player.getUniqueId());
		profile.getOptions().playingMessageSounds(!profile.getOptions().playingMessageSounds());
	}

}
