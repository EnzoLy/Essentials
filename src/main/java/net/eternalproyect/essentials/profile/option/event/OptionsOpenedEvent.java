package net.eternalproyect.essentials.profile.option.event;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.eternalproyect.essentials.profile.option.menu.ProfileOptionButton;
import net.eternalproyect.essentials.util.BaseEvent;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
@Getter
public class OptionsOpenedEvent extends BaseEvent {

	private final Player player;
	private List<ProfileOptionButton> buttons = new ArrayList<>();

}
