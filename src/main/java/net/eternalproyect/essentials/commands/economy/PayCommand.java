package net.eternalproyect.essentials.commands.economy;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.qrakn.phoenix.lang.file.language.LanguageConfigurationFileLocale;
import net.eternalproyect.essentials.Locale;
import net.eternalproyect.essentials.profile.Profile;
import org.bukkit.entity.Player;

@CommandAlias("pay")
public class PayCommand extends BaseCommand {

    @Default
	@CommandCompletion("@players")
    public void pay(Player player, OnlinePlayer onlinePlayer, Double number){
        Player target = onlinePlayer.getPlayer();

        if (target == null) {
            player.sendMessage(Locale.PLAYER_NOT_FOUND.format(LanguageConfigurationFileLocale.ENGLISH));
            return;
        }

        Profile profile = Profile.getByUuid(player.getUniqueId());
        Profile targetProfile = Profile.getByUuid(target.getUniqueId());

        if(profile.getBalance() < number){
            player.sendMessage(Locale.ECONOMY_INSUFFICIENT_MONEY.format(profile.getLocale()));
            return;
        }

        profile.subtractBalance(number);
        targetProfile.addBalance(number);

        player.sendMessage(Locale.ECONOMY_PAYMENT_MADE.format(profile.getLocale(), number, target.getName()));
        target.sendMessage(Locale.ECONOMY_PAYMENT_RECEIVED.format(targetProfile.getLocale(), number, player.getName()));
    }


}
