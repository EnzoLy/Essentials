package net.eternalproyect.essentials.profile.conversation.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import net.eternalproyect.essentials.profile.Profile;
import net.eternalproyect.essentials.profile.conversation.Conversation;
import net.eternalproyect.essentials.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("r|reply")
public class ReplyCommand extends BaseCommand {

    @Default
    public void reply(Player player, String message){
        Profile playerProfile = Profile.getByUuid(player.getUniqueId());
        Conversation conversation = playerProfile.getConversations().getLastRepliedConversation();

        if (conversation != null) {
            if (conversation.validate()) {
                conversation.sendMessage(player, Bukkit.getPlayer(conversation.getPartner(player.getUniqueId())), message);
            } else {
                player.sendMessage(CC.RED + "You can no longer reply to that player.");
            }
        } else {
            player.sendMessage(CC.RED + "You have nobody to reply to.");
        }
    }

}
