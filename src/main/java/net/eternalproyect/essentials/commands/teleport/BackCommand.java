package net.eternalproyect.essentials.commands.teleport;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.eternalproyect.essentials.profile.Profile;
import org.bukkit.entity.Player;

@CommandAlias("back")
@CommandPermission("eternal.command.back")
public class BackCommand extends BaseCommand {

    @Default
    public void back(Player player){
        Profile playerProfile = Profile.getByUuid(player.getUniqueId());
        player.teleport(playerProfile.getLastLocation());
    }

}
