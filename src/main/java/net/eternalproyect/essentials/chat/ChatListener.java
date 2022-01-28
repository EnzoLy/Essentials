package net.eternalproyect.essentials.chat;

import net.eternalproyect.essentials.Essentials;
import net.eternalproyect.essentials.EssentialsAPI;
import net.eternalproyect.essentials.Locale;
import net.eternalproyect.essentials.chat.event.ChatAttemptEvent;
import net.eternalproyect.essentials.profile.Profile;
import net.eternalproyect.essentials.util.CC;
import net.eternalproyect.essentials.util.TimeUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	private Essentials plugin = Essentials.getInstance();

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
		ChatAttempt chatAttempt = Essentials.getInstance().getChat().attemptChatMessage(event.getPlayer(), event.getMessage());
		//ChatAttemptEvent chatAttemptEvent = new ChatAttemptEvent(event.getPlayer(), chatAttempt, event.getMessage());

		//Bukkit.getServer().getPluginManager().callEvent(chatAttemptEvent);

		//if (!chatAttemptEvent.isCancelled()) {
			switch (chatAttempt.getResponse()) {
				case ALLOWED: {

					Player player = event.getPlayer();

					Profile profile = Profile.getByUuid(player.getUniqueId());

					event.setFormat(CC.translate(plugin.getConfiguration().getString("chat.format")
							.replace("{player_name}", event.getPlayer().getName())
							.replace("{rank}", plugin.getVchat().getPlayerPrefix(event.getPlayer()))
							.replace("{arrow}",
											EssentialsAPI.getColorOfPlayer(player)
									+ "»")
							.replace("{msg}", event.getMessage().replace("%", "%%")))
					);
				}
				break;
				case MESSAGE_FILTERED: {
					event.setCancelled(true);
					chatAttempt.getFilterFlagged().punish(event.getPlayer());
				}
				break;
				/*case PLAYER_MUTED: {
					event.setCancelled(true);

					if (chatAttempt.getPunishment().isPermanent()) {
						event.getPlayer().sendMessage(CC.RED + "You are muted for forever.");
					} else {
						event.getPlayer().sendMessage(CC.RED + "You are muted for another " +
								chatAttempt.getPunishment().getTimeRemaining() + ".");
					}
				}
				break;*/
				case CHAT_MUTED: {
					event.setCancelled(true);
					event.getPlayer().sendMessage(CC.RED + "The public chat is currently muted.");
				}
				break;
				case CHAT_DELAYED: {
					Profile profile = Profile.getProfiles().get(event.getPlayer().getUniqueId());
					event.setCancelled(true);
					event.getPlayer().sendMessage(Locale.CHAT_DELAYED.format(profile.getLocale(),
							TimeUtil.millisToSeconds((long) chatAttempt.getValue())) + " seconds");
				}
				break;
			}
		//}

		if (chatAttempt.getResponse() == ChatAttempt.Response.ALLOWED) {
			event.getRecipients().removeIf(player -> {
				Profile profile = Profile.getProfiles().get(player.getUniqueId());
				return profile != null && !profile.getOptions().publicChatEnabled();
			});
		}
	}

}
