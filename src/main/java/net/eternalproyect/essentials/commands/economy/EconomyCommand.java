package net.eternalproyect.essentials.commands.economy;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.qrakn.phoenix.lang.file.language.LanguageConfigurationFileLocale;
import net.eternalproyect.essentials.Locale;
import net.eternalproyect.essentials.profile.Profile;
import org.bukkit.entity.Player;

@CommandAlias("eco|economy")
@CommandPermission("eternal.staff.command.economy")
public class EconomyCommand extends BaseCommand {

    @Default
    public void economy(Player player, CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("give|add")
    public void give(Player player, OnlinePlayer onlinePlayer, Double number){
        Player target = onlinePlayer.getPlayer();

        if (target == null) {
            player.sendMessage(Locale.PLAYER_NOT_FOUND.format(LanguageConfigurationFileLocale.ENGLISH));
            return;
        }

        Profile profile = Profile.getByUuid(player.getUniqueId());
        Profile targetProfile = Profile.getByUuid(target.getUniqueId());

        targetProfile.addBalance(number);

        player.sendMessage(Locale.ECONOMY_PAYMENT_MADE.format(profile.getLocale(), number, target.getName()));
        target.sendMessage(Locale.ECONOMY_PAYMENT_RECEIVED.format(targetProfile.getLocale(), number, player.getName()));
    }

    @Subcommand("subtract|remove")
    public void subtract(Player player, OnlinePlayer onlinePlayer, Double number){
        Player target = onlinePlayer.getPlayer();

        if (target == null) {
            player.sendMessage(Locale.PLAYER_NOT_FOUND.format(LanguageConfigurationFileLocale.ENGLISH));
            return;
        }

        Profile profile = Profile.getByUuid(player.getUniqueId());
        Profile targetProfile = Profile.getByUuid(target.getUniqueId());

        targetProfile.subtractBalance(number);

        player.sendMessage(Locale.ECONOMY_SUBTRACT_BALANCE.format(profile.getLocale(), number, target.getName()));
        //target.sendMessage(Locale.ECONOMY_PAYMENT_RECEIVED.format(targetProfile.getLocale(), number, player.getName()));
    }

}
