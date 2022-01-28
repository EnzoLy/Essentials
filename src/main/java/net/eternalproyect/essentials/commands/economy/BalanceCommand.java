package net.eternalproyect.essentials.commands.economy;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.eternalproyect.essentials.profile.Profile;
import net.eternalproyect.essentials.util.CC;
import org.bukkit.entity.Player;

@CommandAlias("balance|money")
public class BalanceCommand extends BaseCommand {

    @Default
    public void balance(Player player){
        Profile playerProfile = Profile.getByUuid(player.getUniqueId());
        player.sendMessage(CC.translate("&aYour balance is " + playerProfile.getBalance()));
    }

}
