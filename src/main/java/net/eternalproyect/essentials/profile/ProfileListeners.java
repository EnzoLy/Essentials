package net.eternalproyect.essentials.profile;

import com.qrakn.phoenix.lang.file.language.LanguageConfigurationFileLocale;
import net.eternalproyect.essentials.Essentials;
import net.eternalproyect.essentials.Locale;
import net.eternalproyect.essentials.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ProfileListeners implements Listener {

    private static Essentials plugin = Essentials.getInstance();

    @EventHandler
    public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        Player player = Bukkit.getPlayer(event.getUniqueId());

        // Need to check if player is still logged in when receiving another login attempt
        // This happens when a player using a custom client can access the server list while in-game (and reconnecting)
        if (player != null && player.isOnline()) {
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            event.setKickMessage(CC.RED + "You tried to login too quickly after disconnecting.\nTry again in a few seconds.");
            plugin.getServer().getScheduler().runTask(plugin, () -> player.kickPlayer(CC.RED + "Duplicate login kick"));
            return;
        }

        Profile profile = null;
        try {
            profile = new Profile(event.getName(), event.getUniqueId());

            if (!profile.isLoaded()) {
                event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
                event.setKickMessage(Locale.FAILED_TO_LOAD_PROFILE.format(LanguageConfigurationFileLocale.getByName("es")));
                return;
            }

            profile.setName(event.getName());

            if (profile.getFirstSeen() == null) {
                profile.setFirstSeen(System.currentTimeMillis());
            }

            profile.setLastLogin(System.currentTimeMillis());

            if (profile.getCurrentAddress() == null) {
                profile.setCurrentAddress(event.getAddress().getHostAddress());
            }

            if (!profile.getIpAddresses().contains(event.getAddress().getHostAddress())) {
                profile.getIpAddresses().add(event.getAddress().getHostAddress());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        if (profile == null || !profile.isLoaded()) {
            event.setKickMessage(Locale.FAILED_TO_LOAD_PROFILE.format(LanguageConfigurationFileLocale.getByName("es")));
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            return;
        }

        Profile.getProfiles().put(profile.getUuid(), profile);
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Profile playerProfile = Profile.getByUuid(event.getPlayer().getUniqueId());
        playerProfile.setLastLocation(event.getPlayer().getLocation());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Profile profile = Profile.getProfiles().remove(event.getPlayer().getUniqueId());
        profile.setLastLogin(System.currentTimeMillis());

        if (profile.isLoaded()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    profile.save();
                }
            }.runTaskAsynchronously(Essentials.getInstance());
        }
    }

}
