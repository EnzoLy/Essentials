package net.eternalproyect.essentials.commands.inventory;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.eternalproyect.essentials.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("skull")
@CommandPermission("eternal.staff.command.skull")
public class SkullCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    public void skull(Player player, String name){
        ItemStack itemStack = new ItemBuilder(Material.PLAYER_HEAD).setOwner(name).build();
        player.getInventory().addItem(itemStack);
    }

}
