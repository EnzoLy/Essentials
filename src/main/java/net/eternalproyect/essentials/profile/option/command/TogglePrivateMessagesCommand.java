package net.eternalproyect.essentials.profile.option.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.eternalproyect.essentials.Locale;
import net.eternalproyect.essentials.profile.Profile;
import org.bukkit.entity.Player;

@CommandAlias("togglepm|togglepms|tpm|tpms")
public class TogglePrivateMessagesCommand extends BaseCommand {

    @Default
    public void execute(Player player) {
        Profile profile = Profile.getByUuid(player.getUniqueId());
        profile.getOptions().receivingNewConversations(!profile.getOptions().receivingNewConversations());
        profile.getConversations().expireAllConversations();

        if (profile.getOptions().receivingNewConversations()) {
            player.sendMessage(Locale.OPTIONS_PRIVATE_MESSAGES_ENABLED.format(profile.getLocale()));
        } else {
            player.sendMessage(Locale.OPTIONS_PRIVATE_MESSAGES_DISABLED.format(profile.getLocale()));
        }
    }

}
