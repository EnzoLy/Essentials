package net.eternalproyect.essentials.profile.conversation.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.eternalproyect.essentials.profile.Profile;
import net.eternalproyect.essentials.util.CC;
import org.bukkit.entity.Player;

@CommandAlias("socialspy|spy")
@CommandPermission("eternal.staff.command.socialspy")
public class SocialSpyCommand extends BaseCommand {

    @Default
    public void spy(Player player){
        Profile profile = Profile.getByUuid(player.getUniqueId());

        profile.setSpy(!profile.isSpy());

        player.sendMessage(CC.translate("&eSocial spy " + (profile.isSpy() ? "activated" : "desactivated")));
    }

}
