package net.eternalproyect.essentials.profile.option.menu;

import java.util.HashMap;
import java.util.Map;

import net.eternalproyect.essentials.profile.option.event.OptionsOpenedEvent;
import net.eternalproyect.essentials.profile.option.menu.button.PrivateChatOptionButton;
import net.eternalproyect.essentials.profile.option.menu.button.PrivateChatSoundsOptionButton;
import net.eternalproyect.essentials.profile.option.menu.button.PublicChatOptionButton;
import net.eternalproyect.essentials.util.menu.Button;
import net.eternalproyect.essentials.util.menu.Menu;
import org.bukkit.entity.Player;

public class ProfileOptionsMenu extends Menu {

	@Override
	public String getTitle(Player player) {
		return "&6&lOptions";
	}

	@Override
	public Map<Integer, Button> getButtons(Player player) {
		Map<Integer, Button> buttons = new HashMap<>();
		buttons.put(buttons.size(), new PublicChatOptionButton());
		buttons.put(buttons.size(), new PrivateChatOptionButton());
		buttons.put(buttons.size(), new PrivateChatSoundsOptionButton());

		OptionsOpenedEvent event = new OptionsOpenedEvent(player);
		event.call();

		if (!event.getButtons().isEmpty()) {
			for (ProfileOptionButton button : event.getButtons()) {
				buttons.put(buttons.size(), button);
			}
		}

		return buttons;
	}

}
