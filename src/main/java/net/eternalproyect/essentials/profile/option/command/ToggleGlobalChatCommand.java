package net.eternalproyect.essentials.profile.option.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.eternalproyect.essentials.Locale;
import net.eternalproyect.essentials.profile.Profile;
import org.bukkit.entity.Player;

@CommandAlias("toggleglobalchat|tgc|togglepublicchat|tpc")
public class ToggleGlobalChatCommand extends BaseCommand {

    @Default
    public void execute(Player player) {
        Profile profile = Profile.getByUuid(player.getUniqueId());
        profile.getOptions().publicChatEnabled(!profile.getOptions().publicChatEnabled());

        if (profile.getOptions().publicChatEnabled()) {
            player.sendMessage(Locale.OPTIONS_GLOBAL_CHAT_ENABLED.format(profile.getLocale()));
        } else {
            player.sendMessage(Locale.OPTIONS_GLOBAL_CHAT_DISABLED.format(profile.getLocale()));
        }
    }

}
