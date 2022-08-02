package net.eternalproyect.essentials.profile.conversation;

import com.qrakn.phoenix.lang.file.type.BasicConfigurationFile;
import lombok.Getter;
import net.eternalproyect.essentials.Essentials;
import net.eternalproyect.essentials.EssentialsAPI;
import net.eternalproyect.essentials.profile.Profile;
import net.eternalproyect.essentials.util.CC;
import net.eternalproyect.essentials.util.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Conversation {

	private Essentials plugin = Essentials.getInstance();
	private BasicConfigurationFile config = plugin.getConfiguration();

	@Getter private final UUID initiatedBy;
	@Getter private final UUID target;
	@Getter private long lastMessageSentAt;
	@Getter private UUID lastMessageSentBy;

	public Conversation(UUID initiatedBy, UUID target) {
		this.initiatedBy = initiatedBy;
		this.target = target;
		this.lastMessageSentAt = System.currentTimeMillis();

		Profile initiatorProfile = Profile.getByUuid(initiatedBy);
		initiatorProfile.getConversations().getConversations().put(target, this);

		Profile targetProfile = Profile.getByUuid(target);
		targetProfile.getConversations().getConversations().put(initiatedBy, this);
	}

	public void sendMessage(Player sender, Player target, String message) {

		Profile targetProfile = Profile.getByUuid(target.getUniqueId());

		sender.sendMessage(CC.translate(config.getString("conversation.send_message")
				.replace("{name}", EssentialsAPI.getColorOfPlayer(target) + target.getName())
				.replace("{msg}", message)));

		if (targetProfile.getOptions().playingMessageSounds()) {
			target.playSound(target.getLocation(), Sound.ENTITY_ARROW_HIT, 5.0F, 5.0F);
		}

		target.sendMessage(CC.translate(config.getString("conversation.receive_message")
				.replace("{name}", EssentialsAPI.getColorOfPlayer(sender) + sender.getName())
				.replace("{msg}", message)));

		PlayerUtils.staffOnline().forEach(staff -> {
			Profile staffProfile = Profile.getByUuid(staff.getUniqueId());
			if(staffProfile.isSpy()){
				staff.sendMessage(CC.translate(config.getString("conversation.spy_format")
						.replace("{from}", EssentialsAPI.getColorOfPlayer(sender) + sender.getName())
						.replace("{to}", EssentialsAPI.getColorOfPlayer(target) + target.getName())
						.replace("{msg}", message)));
			}
		});

		lastMessageSentAt = System.currentTimeMillis();
		lastMessageSentBy = sender.getUniqueId();
	}

	public boolean validate() {
		Player initiator = Bukkit.getPlayer(initiatedBy);

		if (initiator == null || !initiator.isOnline()) {
			destroy();
			return false;
		}

		Player target = Bukkit.getPlayer(this.target);

		if (target == null || !target.isOnline()) {
			destroy();
			return false;
		}

		return true;
	}

	public void destroy() {
		for (Player player : new Player[] { Bukkit.getPlayer(initiatedBy), Bukkit.getPlayer(target) }) {
			if (player != null && player.isOnline()) {
				Profile profile = Profile.getByUuid(player.getUniqueId());
				profile.getConversations().getConversations().remove(getPartner(player.getUniqueId()));
			}
		}
	}

	public UUID getPartner(UUID compareWith) {
		if (initiatedBy.equals(compareWith)) {
			return target;
		} else if (target.equals(compareWith)) {
			return initiatedBy;
		}

		return null;
	}

}
