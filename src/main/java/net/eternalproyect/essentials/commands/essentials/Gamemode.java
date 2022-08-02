package net.eternalproyect.essentials.commands.essentials;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.google.common.collect.ImmutableList;
import com.qrakn.phoenix.lang.file.language.LanguageConfigurationFileLocale;
import net.eternalproyect.essentials.Essentials;
import net.eternalproyect.essentials.Locale;
import net.eternalproyect.essentials.util.CC;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("gm|gamemode")
@CommandPermission("eternal.staff.command.gamemode")
public class Gamemode extends BaseCommand {

    private Essentials plugin = Essentials.getInstance();

    public Gamemode(){
        plugin.getManager().getCommandCompletions().registerCompletion("gamemode",
                c-> ImmutableList.of("creative", "survival", "spectator", "adventure", "c", "s", "sp","a"));
    }

    @Default
    @HelpCommand
    @CommandCompletion("@gamemode")
    public void onGamemode(Player player, CommandHelp help){
        help.showHelp();
    }

    @Subcommand("creative|c")
    public void onCreative(Player player){
        player.setGameMode(GameMode.CREATIVE);
        player.updateInventory();
        player.sendMessage(CC.GOLD + "You updated your game mode.");
    }
    @Default
    @CommandCompletion("@rage:0-3")
    public void onCreative(Player player, Integer number){
        player.setGameMode(GameMode.getByValue(number));
        player.updateInventory();
        player.sendMessage(CC.GOLD + "You updated your game mode.");
    }

    @Subcommand("creative|c")
    @CommandCompletion("@players")
    @CommandPermission("eternal.staff.command.gamemode.other")
    public void onCreative(CommandSender sender, OnlinePlayer onlinePlayer){
        Player target = onlinePlayer.getPlayer();

        if (target == null) {
            sender.sendMessage(Locale.PLAYER_NOT_FOUND.format(LanguageConfigurationFileLocale.ENGLISH));
            return;
        }

        target.setGameMode(GameMode.CREATIVE);
        target.updateInventory();
        target.sendMessage(CC.GOLD + "Your game mode has been updated by " + sender.getName());
    }

    @Subcommand("survival|s")
    public void onSuvival(Player player){
        player.setGameMode(GameMode.SURVIVAL);
        player.updateInventory();
        player.sendMessage(CC.GOLD + "You updated your game mode.");
    }

    @Subcommand("survival|s")
    @CommandCompletion("@players")
    @CommandPermission("eternal.staff.command.gamemode.other")
    public void onSurvival(CommandSender sender, OnlinePlayer onlinePlayer){
        Player target = onlinePlayer.getPlayer();

        if (target == null) {
            sender.sendMessage(Locale.PLAYER_NOT_FOUND.format(LanguageConfigurationFileLocale.ENGLISH));
            return;
        }

        target.setGameMode(GameMode.SURVIVAL);
        target.updateInventory();
        target.sendMessage(CC.GOLD + "Your game mode has been updated by " + sender.getName());
    }

    @Subcommand("adventure|a")
    public void onAdeventure(Player player){
        player.setGameMode(GameMode.ADVENTURE);
        player.updateInventory();
        player.sendMessage(CC.GOLD + "You updated your game mode.");
    }

    @Subcommand("adventure|a")
    @CommandCompletion("@players")
    @CommandPermission("eternal.staff.command.gamemode.other")
    public void onAdventure(CommandSender sender, OnlinePlayer onlinePlayer){
        Player target = onlinePlayer.getPlayer();

        if (target == null) {
            sender.sendMessage(Locale.PLAYER_NOT_FOUND.format(LanguageConfigurationFileLocale.ENGLISH));
            return;
        }

        target.setGameMode(GameMode.ADVENTURE);
        target.updateInventory();
        target.sendMessage(CC.GOLD + "Your game mode has been updated by " + sender.getName());
    }

    @Subcommand("spectator|sp")
    public void onSpectate(Player player){
        player.setGameMode(GameMode.SPECTATOR);
        player.updateInventory();
        player.sendMessage(CC.GOLD + "You updated your game mode.");
    }

    @Subcommand("spectator|sp")
    @CommandCompletion("@players")
    @CommandPermission("eternal.staff.command.gamemode.other")
    public void onSpectate(CommandSender sender, OnlinePlayer onlinePlayer){
        Player target = onlinePlayer.getPlayer();

        if (target == null) {
            sender.sendMessage(Locale.PLAYER_NOT_FOUND.format(LanguageConfigurationFileLocale.ENGLISH));
            return;
        }

        target.setGameMode(GameMode.SPECTATOR);
        target.updateInventory();
        target.sendMessage(CC.GOLD + "Your game mode has been updated by " + sender.getName());
    }
}
