package net.eternalproyect.essentials.profile.option.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import com.google.common.collect.ImmutableList;
import net.eternalproyect.essentials.Essentials;
import net.eternalproyect.essentials.Locale;
import net.eternalproyect.essentials.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("lang|lenguaje|idioma")
public class LangCommand extends BaseCommand {

    public LangCommand(){
        Essentials.getInstance().getManager().getCommandCompletions().registerCompletion("lang", c-> ImmutableList.of("es", "en"));
    }

    @Default
    @CommandCompletion("@lang")
    public void excute(Player player, String lang){
        Profile profile = Profile.getByUuid(player.getUniqueId());
		profile.setLocale(lang);
        Bukkit.getConsoleSender().sendMessage(lang);
        player.sendMessage(Locale.OPTIONS_LANG_CHANGE.format(profile.getLocale(), lang));
    }

}
