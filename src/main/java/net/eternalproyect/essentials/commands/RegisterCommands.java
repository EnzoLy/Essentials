package net.eternalproyect.essentials.commands;

import net.eternalproyect.essentials.Essentials;
import net.eternalproyect.essentials.chat.command.ClearChatCommand;
import net.eternalproyect.essentials.chat.command.MuteChatCommand;
import net.eternalproyect.essentials.chat.command.SlowChatCommand;
import net.eternalproyect.essentials.commands.economy.BalanceCommand;
import net.eternalproyect.essentials.commands.economy.EconomyCommand;
import net.eternalproyect.essentials.commands.economy.PayCommand;
import net.eternalproyect.essentials.commands.essentials.*;
import net.eternalproyect.essentials.commands.inventory.*;
import net.eternalproyect.essentials.commands.teleport.*;
import net.eternalproyect.essentials.profile.conversation.command.MessageCommand;
import net.eternalproyect.essentials.profile.conversation.command.ReplyCommand;
import net.eternalproyect.essentials.profile.conversation.command.SocialSpyCommand;
import net.eternalproyect.essentials.profile.option.command.*;

import java.util.Arrays;

public class RegisterCommands {

    public RegisterCommands(){
        Arrays.asList(
                new MessageCommand(),
                new ReplyCommand(),
                new SocialSpyCommand(),
                new MuteChatCommand(),
                new ClearChatCommand(),
                new SlowChatCommand(),
                new Gamemode(),
                new BroadcastCommand(),
                new HealCommand(),
                new NightCommand(),
                new SetSlotsCommand(),
                new SudoAllCommand(),
                new SudoCommand(),
                new SunsetCommand(),
                new DayCommand(),
                new ExpCommand(),
                new FlyCommand(),
                new HatCommand(),
                //Options Commands
                new OptionsCommand(),
                new LangCommand(),
                new ToggleGlobalChatCommand(),
                new TogglePrivateMessagesCommand(),
                new ToggleSoundsCommand(),
                //Inventory Commands
                new ClearCommand(),
                new RenameCommand(),
                new MoreCommand(),
                new CopyInventoryCommand(),
                new SkullCommand(),
				new InvseeCommand(),
                //Teleport Commands
                new TeleportWorldCommand(),
                new BackCommand(),
                new Teleport(),
                new TeleportHere(),
                new TeleportAll(),
                //Economy Commands
                new BalanceCommand(),
                new PayCommand(),
                new EconomyCommand()
        ).forEach(Essentials.getInstance().getManager()::registerCommand);
    }

}
