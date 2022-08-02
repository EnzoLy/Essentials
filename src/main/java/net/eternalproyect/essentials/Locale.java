package net.eternalproyect.essentials;

import com.qrakn.phoenix.lang.file.language.LanguageConfigurationFileLocale;
import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;

import java.text.MessageFormat;

@AllArgsConstructor
public enum Locale {

    FAILED_TO_LOAD_PROFILE("common_errors.failed_to_load_profile"),
    PLAYER_NOT_FOUND("common_errors.player_not_found"),
    NO_PLAYERS("common_errors.no_players"),


    OPTIONS_PRIVATE_MESSAGES_ENABLED("options.private_messages_enabled"),
    OPTIONS_PRIVATE_MESSAGES_DISABLED("options.private_messages_disabled"),

    OPTIONS_MENU_PRIVATE_MESSAGE_DESCRIPTION("options.menu.pm.private_message_description"),
    OPTIONS_MENU_PRIVATE_MESSAGE_ENABLE("options.menu.pm.private_message_enable"),
    OPTIONS_MENU_PRIVATE_MESSAGE_DISABLE("options.menu.pm.private_message_disable"),
    OPTIONS_MENU_PRIVATE_MESSAGE_NAME("options.menu.pm.private_message_name"),

    OPTIONS_PRIVATE_MESSAGE_SOUND_ENABLED("options.private_message_sounds_enabled"),
    OPTIONS_PRIVATE_MESSAGE_SOUND_DISABLED("options.private_message_sounds_disabled"),

    OPTIONS_MENU_PRIVATE_MESSAGE_SOUND_DESCRIPTION("options.menu.sound.private_message_sounds_description"),
    OPTIONS_MENU_PRIVATE_MESSAGE_SOUND_ENABLE("options.menu.sound.private_message_sounds_enable"),
    OPTIONS_MENU_PRIVATE_MESSAGE_SOUND_DISABLE("options.menu.sound.private_message_sounds_disable"),
    OPTIONS_MENU_PRIVATE_MESSAGE_SOUND_NAME("options.menu.sound.private_message_sounds_name"),

    OPTIONS_GLOBAL_CHAT_ENABLED("options.global_chat_enabled"),
    OPTIONS_GLOBAL_CHAT_DISABLED("options.global_chat_disabled"),

    OPTIONS_MENU_GLOBAL_CHAT_DESCRIPTION("options.menu.chat.global_chat_description"),
    OPTIONS_MENU_GLOBAL_CHAT_ENABLE("options.menu.chat.global_chat_enable"),
    OPTIONS_MENU_GLOBAL_CHAT_DISABLE("options.menu.chat.global_chat_disable"),
    OPTIONS_MENU_GLOBAL_CHAT_NAME("options.menu.chat.global_chat_name"),

    CLEAR_CHAT_BROADCAST("chat.clear_chat_broadcast"),
    MUTE_CHAT_BROADCAST("chat.mute_chat_broadcast"),
    DELAY_CHAT_ENABLED_BROADCAST("chat.delay_chat_enabled_broadcast"),
    DELAY_CHAT_DISABLED_BROADCAST("chat.delay_chat_disabled_broadcast"),

    CHAT_DELAYED("chat.chat_delayed"),

    OPTIONS_LANG_CHANGE("options.lang_change"),

    ECONOMY_INSUFFICIENT_MONEY("economy.insufficient_money"),
    ECONOMY_PAYMENT_MADE("economy.payment_made"),
    ECONOMY_PAYMENT_RECEIVED("economy.payment_received"),
    ECONOMY_SUBTRACT_BALANCE("economy.subtract_balance");

    private String path;

    public String format(LanguageConfigurationFileLocale locale, Object... objects) {
        return new MessageFormat(ChatColor.translateAlternateColorCodes('&',
                Essentials.getInstance().getLang().getString(path, locale))).format(objects);
    }

}
