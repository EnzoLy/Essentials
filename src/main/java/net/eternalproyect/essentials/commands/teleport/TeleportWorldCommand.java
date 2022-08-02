package net.eternalproyect.essentials.commands.teleport;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.eternalproyect.essentials.profile.Profile;
import net.eternalproyect.essentials.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

@CommandAlias("tpworld|world")
@CommandPermission("eternal.tpworld")
public class TeleportWorldCommand extends BaseCommand {

	@Default
	public void execute(Player player, String worldName) {
		World world = Bukkit.getWorld(worldName);
		Profile playerProfile = Profile.getByUuid(player.getUniqueId());

		if (world == null) {
			player.sendMessage(CC.RED + "A world with that name does not exist.");
		} else {
			player.teleport(world.getSpawnLocation());
			playerProfile.setLastLocation(player.getLocation());
			player.sendMessage(CC.GOLD + "Teleported you to " + world.getName());
		}
	}

}
