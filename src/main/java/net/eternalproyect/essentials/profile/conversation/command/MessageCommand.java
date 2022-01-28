package net.eternalproyect.essentials.profile.conversation.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.contexts.OnlinePlayer;
import net.eternalproyect.essentials.Locale;
import net.eternalproyect.essentials.profile.Profile;
import net.eternalproyect.essentials.profile.conversation.Conversation;
import net.eternalproyect.essentials.util.CC;
import org.bukkit.entity.Player;

@CommandAlias("msg|message|tell|whisper|t")
public class MessageCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    public void message(Player player, OnlinePlayer targetOnline, String message){
	
		Player target = targetOnline.getPlayer();
        Profile playerProfile = Profile.getByUuid(player.getUniqueId());

        if (player.equals(target)) {
            player.sendMessage(CC.RED + "You cannot message yourself!");
            return;
        }

        if (target == null) {
            player.sendMessage(Locale.PLAYER_NOT_FOUND.format(playerProfile.getLocale()));
            return;
        }

        Profile targetProfile = Profile.getByUuid(target.getUniqueId());

        if (targetProfile.getConversations().canBeMessagedBy(player)) {
            Conversation conversation = playerProfile.getConversations().getOrCreateConversation(target);

            if (conversation.validate()) {
                conversation.sendMessage(player, target, message);
            } else {
                player.sendMessage(CC.RED + "That player is not receiving new conversations right now.");
            }
        } else {
            player.sendMessage(CC.RED + "That player is not receiving new conversations right now.");
        }
    }

}
