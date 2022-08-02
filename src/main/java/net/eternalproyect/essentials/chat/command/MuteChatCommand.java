package net.eternalproyect.essentials.chat.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.qrakn.phoenix.lang.file.language.LanguageConfigurationFileLocale;
import net.eternalproyect.essentials.Essentials;
import net.eternalproyect.essentials.EssentialsAPI;
import net.eternalproyect.essentials.Locale;
import net.eternalproyect.essentials.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("mutechat")
public class MuteChatCommand extends BaseCommand {

	@Default
	@CommandPermission("eternal.staff.command.mutechat")
	public void execute(CommandSender sender) {
		Essentials.getInstance().getChat().togglePublicChatMute();

		String senderName;

		if (sender instanceof Player) {
			senderName = EssentialsAPI.getColorOfPlayer(((Player)sender)) + sender.getName();
		} else {
			senderName = ChatColor.DARK_RED + "Console";
		}

		String context;
		if(Profile.getByUuid(((Player)sender).getUniqueId()).getLocale() == LanguageConfigurationFileLocale.ENGLISH){
			context = Essentials.getInstance().getChat().isPublicChatMuted() ? "muted" : "unmuted";
		}else{
			context = Essentials.getInstance().getChat().isPublicChatMuted() ? "muteado" : "desmuteado";
		}

		Bukkit.getOnlinePlayers().forEach(other -> {
			Profile profile = Profile.getProfiles().get(other.getUniqueId());
			other.sendMessage(Locale.MUTE_CHAT_BROADCAST.format(profile.getLocale(), context, senderName));
		});
	}

}
